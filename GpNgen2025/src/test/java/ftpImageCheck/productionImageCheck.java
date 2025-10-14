package ftpImageCheck;

import java.io.IOException;


import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class productionImageCheck{

	public static void main(String[] args) {
        String server = "production.nextgenphotosolutions.com";
        int port = 21;
        String user = "prodngps@production.nextgenphotosolutions.com";
        String pass = "productionFTP@2017";

        String folderPath = "/2025-08-04/NGPS26328/GotPhoto_PNG/Individual Images"; // Path to your folder

        FTPClient ftpClient = new FTPClient();

        try {
            ftpClient.connect(server, port);
            ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode();

            System.out.println("✅ Connected to FTP");

            // Navigate to the folder
            if (ftpClient.changeWorkingDirectory(folderPath)) {
                FTPFile[] files = ftpClient.listFiles();

                int imageCount = 0;
                for (FTPFile file : files) {
                    if (file.isFile()) {
                        String name = file.getName().toLowerCase();
                        if (name.endsWith(".jpg") || name.endsWith(".jpeg") ||
                            name.endsWith(".png") || name.endsWith(".gif")) {
                            imageCount++;
                        }
                    }
                }

                System.out.println("📸 Total images in '" + folderPath + "': " + imageCount);
            } else {
                System.out.println("❌ Could not change directory: " + folderPath);
            }

            ftpClient.logout();
            ftpClient.disconnect();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
