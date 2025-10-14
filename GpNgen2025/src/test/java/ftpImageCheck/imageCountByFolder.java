package ftpImageCheck;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class imageCountByFolder {
	 public static void main(String[] args) {
	        final String server = "production.nextgenphotosolutions.com";
	        final int port = 21;
	        final String user = "prodngps@production.nextgenphotosolutions.com";
	        final String pass = "productionFTP@2017";

	        Scanner sc = new Scanner(System.in);
	        System.out.print("📅 Enter date (yyyy-MM-dd): ");
	        String dateStr = sc.nextLine().trim();
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

	            // Resolve which path actually exists: "/<date>" or "<date>"
	            String basePath = resolveExistingDatePath(ftp, dateStr);
	            if (basePath == null) {
	                System.out.println("❌ Date folder not found: /" + dateStr + " (tried with and without leading slash)");
	                return;
	            }

	            // Robustly list child entries of date folder
	            FTPFile[] children = safeListFiles(ftp, basePath);
	            if (children.length == 0) {
	                System.out.println("⚠ No entries inside: " + basePath);
	                return;
	            }

	            int totalOrders = 0;
	            int grandJpg = 0;
	            int grandPng = 0;
	            int grandTotal = 0;

	            for (FTPFile e : children) {
	                if (!isDirectory(ftp, basePath, e)) continue;

	                String orderId = e.getName();
	                if (orderId == null || orderId.equals(".") || orderId.equals("..")) continue;
	                if (!orderId.startsWith("NGPS")) continue; // only NGPS jobs

	                totalOrders++;
	                String orderPath = join(basePath, orderId);

	                // Find GotPhoto / GotPhoto_PNG case-insensitively
	                String gotPhotoPath    = findChildDirIgnoreCase(ftp, orderPath, "GotPhoto");
	                String gotPhotoPngPath = findChildDirIgnoreCase(ftp, orderPath, "GotPhoto_PNG");

	                int jpgCount = (gotPhotoPath    != null) ? countImagesRecursively(ftp, gotPhotoPath)    : 0;
	                int pngCount = (gotPhotoPngPath != null) ? countImagesRecursively(ftp, gotPhotoPngPath) : 0;
	                int orderTotal = jpgCount + pngCount;

	                grandJpg += jpgCount;
	                grandPng += pngCount;
	                grandTotal += orderTotal;

	                System.out.println("➡ Order: " + orderId +
	                        " | GotPhoto (JPG): " + jpgCount +
	                        " | GotPhoto_PNG (PNG): " + pngCount +
	                        " | Total: " + orderTotal);
	            }

	            System.out.println("📌 NGPS Orders on " + dateStr + ": " + totalOrders +
	                    " | JPG: " + grandJpg +
	                    " | PNG: " + grandPng +
	                    " | Grand Total: " + grandTotal);

	            ftp.logout();
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        } finally {
	            try { ftp.disconnect(); } catch (IOException ignored) {}
	        }
	    }

	    // ---------- Helpers (robust listing & counting) ----------

	    // Try "/<date>" then "<date>" to handle different FTP roots
	    private static String resolveExistingDatePath(FTPClient ftp, String date) throws IOException {
	        String withSlash = "/" + date;
	        if (existsAndIsDirectory(ftp, withSlash)) return withSlash;
	        if (existsAndIsDirectory(ftp, date)) {
	            return date.startsWith("/") ? date : "/" + date;
	        }
	        return null;
	    }

	    private static boolean existsAndIsDirectory(FTPClient ftp, String path) throws IOException {
	        String cwd = ftp.printWorkingDirectory();
	        boolean ok = ftp.changeWorkingDirectory(path);
	        if (ok) {
	            ftp.changeWorkingDirectory(cwd);
	            return true;
	        }
	        return false;
	    }

	    /** Robust listing: direct list; if empty, cd then list; fallback NLST/MLST. */
	    private static FTPFile[] safeListFiles(FTPClient ftp, String path) throws IOException {
	        FTPFile[] direct = ftp.listFiles(path);
	        if (direct != null && direct.length > 0) return direct;

	        String cwd = ftp.printWorkingDirectory();
	        if (ftp.changeWorkingDirectory(path)) {
	            FTPFile[] inside = ftp.listFiles();
	            ftp.changeWorkingDirectory(cwd);
	            if (inside != null && inside.length > 0) return inside;
	        }

	        String[] names = ftp.listNames(path);
	        if (names == null || names.length == 0) return new FTPFile[0];

	        List<FTPFile> recovered = new ArrayList<>();
	        for (String name : names) {
	            String full = join(path, name);
	            FTPFile meta = ftp.mlistFile(full);
	            if (meta == null) {
	                FTPFile f = new FTPFile();
	                f.setName(name);
	                recovered.add(f);
	            } else {
	                meta.setName(name);
	                recovered.add(meta);
	            }
	        }
	        return recovered.toArray(new FTPFile[0]);
	    }

	    /** Determine directory/file reliably; if unknown, probe by cd. */
	    private static boolean isDirectory(FTPClient ftp, String parentPath, FTPFile e) throws IOException {
	        if (e.isDirectory()) return true;
	        if (e.isFile()) return false;

	        String full = join(parentPath, e.getName());
	        String cwd = ftp.printWorkingDirectory();
	        if (ftp.changeWorkingDirectory(full)) {
	            ftp.changeWorkingDirectory(cwd);
	            return true;
	        }
	        return false;
	    }

	    /** Join FTP paths safely */
	    private static String join(String a, String b) {
	        if (a.endsWith("/")) return a + b;
	        return a + "/" + b;
	    }

	    /** Find a direct child directory under parent, case-insensitively. */
	    private static String findChildDirIgnoreCase(FTPClient ftp, String parentPath, String targetName) throws IOException {
	        FTPFile[] children = safeListFiles(ftp, parentPath);
	        for (FTPFile c : children) {
	            String name = c.getName();
	            if (name == null || name.equals(".") || name.equals("..")) continue;

	            boolean dir = c.isDirectory();
	            if (!dir) {
	                // probe by cd if type unknown
	                String full = join(parentPath, name);
	                String cwd = ftp.printWorkingDirectory();
	                if (ftp.changeWorkingDirectory(full)) {
	                    dir = true;
	                    ftp.changeWorkingDirectory(cwd);
	                }
	            }
	            if (dir && name.equalsIgnoreCase(targetName)) {
	                return join(parentPath, name); // keep actual case
	            }
	        }
	        return null;
	    }

	    /** Recursively count images in path (case-insensitive extensions). */
	    private static int countImagesRecursively(FTPClient ftp, String path) throws IOException {
	        int sum = 0;
	        FTPFile[] entries = safeListFiles(ftp, path);
	        if (entries.length == 0) return 0;

	        for (FTPFile e : entries) {
	            String name = e.getName();
	            if (name == null || name.equals(".") || name.equals("..")) continue;

	            String full = join(path, name);
	            boolean dir = e.isDirectory();
	            boolean file = e.isFile();

	            if (!dir && !file) {
	                String cwd = ftp.printWorkingDirectory();
	                if (ftp.changeWorkingDirectory(full)) {
	                    dir = true;
	                    ftp.changeWorkingDirectory(cwd);
	                } else {
	                    file = true; // treat as file if cannot cd
	                }
	            }

	            if (dir) {
	                sum += countImagesRecursively(ftp, full);
	            } else {
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
