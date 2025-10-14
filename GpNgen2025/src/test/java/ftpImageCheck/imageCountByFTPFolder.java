package ftpImageCheck;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class imageCountByFTPFolder {
	private static final boolean DEBUG = false;

    public static void main(String[] args) {
        final String server = "production.nextgenphotosolutions.com";
        final int port = 21;
        final String user = "prodngps@production.nextgenphotosolutions.com";
        final String pass = "productionFTP@2017";

        Scanner sc = new Scanner(System.in);
        System.out.print("📅 Enter date (yyyy-MM-dd): ");
        String date = sc.nextLine().trim();
        sc.close();

        FTPClient ftp = new FTPClient();
        try {
            ftp.setControlEncoding("UTF-8");
            ftp.connect(server, port);
            ftp.enterLocalPassiveMode();
            if (!ftp.login(user, pass)) {
                System.out.println("❌ FTP login failed");
                return;
            }
            ftp.setListHiddenFiles(true);

            // find working date path (handles leading slash or not)
            String basePath = resolveExistingDatePath(ftp, date);
            if (basePath == null) {
                System.out.println("❌ Date folder not found: /" + date);
                return;
            }

            if (DEBUG) System.out.println("🔍 Using base path: " + basePath);

            FTPFile[] dateChildren = safeListFiles(ftp, basePath);
            if (dateChildren.length == 0) {
                System.out.println("⚠ No entries inside: " + basePath);
                return;
            }

            int totalOrders = 0;
            int grandJpg = 0, grandPng = 0;

            for (FTPFile entry : dateChildren) {
                // only directories considered orders
                if (!isDirectory(ftp, basePath, entry)) continue;

                String orderId = entry.getName();
                if (orderId == null || orderId.equals(".") || orderId.equals("..")) continue;
                if (!orderId.startsWith("NGPS")) continue; // only NGPS series

                totalOrders++;
                String orderPath = join(basePath, orderId);
                if (DEBUG) System.out.println("\n🔎 Order found: " + orderId + " -> " + orderPath);

                int jpgCount = 0;
                int pngCount = 0;

                // get direct children of order folder
                FTPFile[] orderChildren = safeListFiles(ftp, orderPath);

                // if files directly under orderPath, count them as well
                // (classify by extension: .png -> png else jpg)
                for (FTPFile oc : orderChildren) {
                    String name = oc.getName();
                    if (name == null || name.equals(".") || name.equals("..")) continue;
                    String full = join(orderPath, name);

                    boolean ocIsDir = oc.isDirectory();
                    boolean ocIsFile = oc.isFile();

                    // probe if server didn't supply type
                    if (!ocIsDir && !ocIsFile) {
                        ocIsDir = existsAndIsDirectory(ftp, full);
                    }

                    if (!ocIsDir && ocIsFile) {
                        // file directly under order folder
                        if (isImageFileName(name)) {
                            if (name.toLowerCase().endsWith(".png")) pngCount++; else jpgCount++;
                            if (DEBUG) System.out.println("   📄 direct file counted: " + full);
                        }
                    }
                }

                // For each child folder, classify and count
                for (FTPFile child : orderChildren) {
                    String childName = child.getName();
                    if (childName == null || childName.equals(".") || childName.equals("..")) continue;
                    String childFull = join(orderPath, childName);

                    boolean childIsDir = child.isDirectory();
                    if (!childIsDir) {
                        // if not known, probe
                        childIsDir = existsAndIsDirectory(ftp, childFull);
                    }
                    if (!childIsDir) continue; // skip files (handled above)

                    // If folder name ends with "_PNG" -> PNG folder
                    if (childName.toUpperCase().endsWith("_PNG")) {
                        int c = countImagesRecursively(ftp, childFull);
                        pngCount += c;
                        if (DEBUG) System.out.println("   📁 PNG folder: " + childFull + " -> " + c);
                    } else {
                        // Treat it as JPG-type folder (merge all random-named JPG folders)
                        int c = countImagesRecursively(ftp, childFull);
                        jpgCount += c;
                        if (DEBUG) System.out.println("   📁 JPG folder: " + childFull + " -> " + c);
                    }
                }

                grandJpg += jpgCount;
                grandPng += pngCount;
                int orderTotal = jpgCount + pngCount;

                System.out.println("➡ Order: " + orderId +
                        " | JPG: " + jpgCount +
                        " | PNG: " + pngCount +
                        " | Total: " + orderTotal);
            }

            System.out.println("📌 NGPS Orders on " + date + ": " + totalOrders +
                    " | JPG_SUM: " + grandJpg + " | PNG_SUM: " + grandPng +
                    " | GRAND_TOTAL: " + (grandJpg + grandPng));

            ftp.logout();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try { if (ftp.isConnected()) ftp.disconnect(); } catch (IOException ignored) {}
        }
    }

    // ---------- Helpers ----------

    // Try "/date" then "date"
    private static String resolveExistingDatePath(FTPClient ftp, String date) throws IOException {
        String withSlash = date.startsWith("/") ? date : "/" + date;
        if (existsAndIsDirectory(ftp, withSlash)) return withSlash;
        String without = date.startsWith("/") ? date.substring(1) : date;
        if (existsAndIsDirectory(ftp, without)) return "/" + without;
        return null;
    }

    private static boolean existsAndIsDirectory(FTPClient ftp, String path) throws IOException {
        String cwd = ftp.printWorkingDirectory();
        boolean ok = ftp.changeWorkingDirectory(path);
        if (ok) ftp.changeWorkingDirectory(cwd);
        return ok;
    }

    // Robust listing with fallbacks: listFiles(path) -> cd+list -> listNames+mlistFile fallback
    private static FTPFile[] safeListFiles(FTPClient ftp, String path) throws IOException {
        FTPFile[] files = null;
        try {
            files = ftp.listFiles(path);
            if (files != null && files.length > 0) return files;
        } catch (Exception ignored) {}

        // try cd then list
        String cwd = ftp.printWorkingDirectory();
        if (ftp.changeWorkingDirectory(path)) {
            try {
                FTPFile[] inside = ftp.listFiles();
                if (inside != null && inside.length > 0) {
                    ftp.changeWorkingDirectory(cwd);
                    return inside;
                }
            } catch (Exception ignored) {}
            ftp.changeWorkingDirectory(cwd);
        }

        // fallback to listNames + mlistFile
        String[] names = ftp.listNames(path);
        if (names == null || names.length == 0) return new FTPFile[0];

        List<FTPFile> recovered = new ArrayList<>();
        for (String nm : names) {
            String full = nm;
            // if NLST returned relative names, try to build full path
            if (!nm.startsWith("/") && !path.endsWith("/")) full = path + "/" + nm;
            FTPFile meta = null;
            try { meta = ftp.mlistFile(full); } catch (Exception ignored) {}
            if (meta == null) {
                FTPFile f = new FTPFile();
                f.setName(nm);
                recovered.add(f);
            } else {
                meta.setName(meta.getName() == null ? nm : meta.getName());
                recovered.add(meta);
            }
        }
        return recovered.toArray(new FTPFile[0]);
    }

    // determine if entry is a directory (probes when necessary)
    private static boolean isDirectory(FTPClient ftp, String parentPath, FTPFile entry) throws IOException {
        if (entry.isDirectory()) return true;
        if (entry.isFile()) return false;

        String full = join(parentPath, entry.getName());
        return existsAndIsDirectory(ftp, full);
    }

    private static String join(String a, String b) {
        if (a == null) return b;
        if (a.endsWith("/")) return a + b;
        return a + "/" + b;
    }

    // Count images recursively under path (handles servers that don't report type properly)
    private static int countImagesRecursively(FTPClient ftp, String path) throws IOException {
        int sum = 0;
        FTPFile[] entries = safeListFiles(ftp, path);
        if (entries.length == 0) return 0;

        for (FTPFile e : entries) {
            String name = e.getName();
            if (name == null || name.equals(".") || name.equals("..")) continue;
            String full = join(path, name);

            boolean isDir = e.isDirectory();
            boolean isFile = e.isFile();

            if (!isDir && !isFile) {
                if (existsAndIsDirectory(ftp, full)) {
                    isDir = true;
                } else {
                    isFile = true;
                }
            }

            if (isDir) {
                sum += countImagesRecursively(ftp, full);
            } else { // file
                if (isImageFileName(name)) sum++;
            }
        }
        return sum;
    }

    private static boolean isImageFileName(String name) {
        String lower = name.toLowerCase();
        return lower.endsWith(".jpg") || lower.endsWith(".jpeg") ||
               lower.endsWith(".png") || lower.endsWith(".tif") ||
               lower.endsWith(".tiff");
    }
}
