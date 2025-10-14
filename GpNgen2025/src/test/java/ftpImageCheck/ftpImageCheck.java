package ftpImageCheck;

import java.io.IOException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class ftpImageCheck 
{
	public static void main(String[] args) {
        String server = "production.nextgenphotosolutions.com";
        int port = 21;
        String user = "prodngps@production.nextgenphotosolutions.com";
        String pass = "productionFTP@2017";

        String teamImagesPath  = "/2025-04-14/NGPS24720/Clinton-Middle-School/Team Images";

        FTPClient ftp = new FTPClient();

        try {
            ftp.connect(server, port);
            ftp.login(user, pass);
            ftp.enterLocalPassiveMode();
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            try { ftp.setAutodetectUTF8(true); } catch (Throwable ignored) {}

            System.out.println("✅ Connected to FTP");

            // Go to Team Images folder first (handles spaces reliably)
            if (!ftp.changeWorkingDirectory(teamImagesPath)) {
                System.out.println("❌ Could not change directory: " + teamImagesPath);
                return;
            }

            FTPFile[] entries = ftp.listFiles();
            int grandTotal = 0;

            // Iterate team folders (first level)
            for (FTPFile entry : entries) {
                if (entry.isDirectory()) {
                    String name = entry.getName();
                    if (name.equals(".") || name.equals("..")) continue;

                    // Enter team folder
                    if (ftp.changeWorkingDirectory(name)) {
                        int teamCount = countImagesHereAndBelow(ftp);
                        System.out.println("📂 " + name + " → " + teamCount + " images");
                        grandTotal += teamCount;

                        // Go back to Team Images
                        ftp.changeToParentDirectory();
                    }
                }
            }

            System.out.println("📸 Total images in all team folders: " + grandTotal);

            ftp.logout();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try { ftp.disconnect(); } catch (IOException ignored) {}
            }
        }
    }

    // Recursively count images from the CURRENT directory downwards.
    private static int countImagesHereAndBelow(FTPClient ftp) throws IOException {
        int count = 0;
        FTPFile[] files = ftp.listFiles();

        for (FTPFile f : files) {
            String name = f.getName();
            if (f.isFile()) {
                String lower = name.toLowerCase();
                if (lower.endsWith(".jpg") || lower.endsWith(".jpeg")
                        || lower.endsWith(".png") || lower.endsWith(".gif")) {
                    count++;
                }
            } else if (f.isDirectory()) {
                if (name.equals(".") || name.equals("..")) continue;
                if (ftp.changeWorkingDirectory(name)) {
                    count += countImagesHereAndBelow(ftp); // go deeper
                    ftp.changeToParentDirectory();         // come back up
                }
            }
        }
        return count;
    }
}
