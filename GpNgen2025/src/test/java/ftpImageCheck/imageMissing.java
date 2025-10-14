package ftpImageCheck;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class imageMissing {

	public static void main(String[] args) {
		final String server = "production.nextgenphotosolutions.com";
        final int port = 21;
        final String user = "prodngps@production.nextgenphotosolutions.com";
        final String pass = "productionFTP@2017";

       

     // 🔹 Fixed paths for this check
        String jpgPath = "/2025-08-18/NGPS26397/West-Branch/Individual Images";
        String pngPath = "/2025-08-18/NGPS26397/West-Branch_PNG/Individual Images";

        FTPClient ftpClient = new FTPClient();

        try {
            ftpClient.connect(server, port);
            ftpClient.login(user, pass);
            ftpClient.enterLocalPassiveMode();

            System.out.println("\n📂 Listing JPG folder: " + jpgPath);
            FTPFile[] jpgFiles = ftpClient.listFiles(jpgPath);
            if (jpgFiles != null) {
                for (FTPFile file : jpgFiles) {
                    System.out.println("JPG Folder Item: " + file.getName());
                }
            }

            System.out.println("\n📂 Listing PNG folder: " + pngPath);
            FTPFile[] pngFiles = ftpClient.listFiles(pngPath);
            if (pngFiles != null) {
                for (FTPFile file : pngFiles) {
                    System.out.println("PNG Folder Item: " + file.getName());
                }
            }

            ftpClient.logout();
            ftpClient.disconnect();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
