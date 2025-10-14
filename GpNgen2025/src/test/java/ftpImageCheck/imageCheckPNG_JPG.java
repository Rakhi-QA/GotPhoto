package ftpImageCheck;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;


//*************Compare GotPhoto and GotPhoto_PNG folder in FTP********************

public class imageCheckPNG_JPG {
	public static void main(String[] args) {
        String server = "production.nextgenphotosolutions.com";
        int port = 21;
        String user = "prodngps@production.nextgenphotosolutions.com";
        String pass = "productionFTP@2017";

        Scanner scanner = new Scanner(System.in);

        System.out.print("📂 Enter GotPhoto folder path (e.g., /2025-07-27/NGPS26208/Western-Reserve): ");
        String gotPhotoPath = scanner.nextLine().trim();

        System.out.print("📂 Enter GotPhoto_PNG folder path (e.g., /2025-07-27/NGPS26208/Western-Reserve_PNG): ");
        String gotPhotoPngPath = scanner.nextLine().trim();

        FTPClient ftp = new FTPClient();

        try {
            ftp.connect(server, port);
            ftp.login(user, pass);
            ftp.enterLocalPassiveMode();
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            ftp.setAutodetectUTF8(true);

            System.out.println("\n✅ Connected to FTP");

            int jpgCount = countFilesDeep(ftp, gotPhotoPath, ".jpg");
            int pngCount = countFilesDeep(ftp, gotPhotoPngPath, ".png");

            System.out.println("\n📊 GotPhoto vs GotPhoto_PNG Counts:");
            System.out.println("--------------------------------------");
            System.out.printf("GotPhoto (JPG)      : %d%n", jpgCount);
            System.out.printf("GotPhoto_PNG (PNG) : %d%n", pngCount);
            System.out.printf("Difference         : %+d%n", pngCount - jpgCount);

            ftp.logout();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try { ftp.disconnect(); } catch (IOException ignored) {}
            }
        }
    }

    private static int countFilesDeep(FTPClient ftp, String folder, String extension) throws IOException {
        int count = 0;
        if (!ftp.changeWorkingDirectory(folder)) {
            System.out.println("❌ Folder not found: " + folder);
            return count;
        }
        FTPFile[] files = ftp.listFiles();
        for (FTPFile file : files) {
            if (file.isFile() && file.getName().toLowerCase().endsWith(extension)) {
                count++;
            } else if (file.isDirectory() && !file.getName().equals(".") && !file.getName().equals("..")) {
                count += countFilesDeep(ftp, folder + "/" + file.getName(), extension);
            }
        }
        return count;
    }
}
