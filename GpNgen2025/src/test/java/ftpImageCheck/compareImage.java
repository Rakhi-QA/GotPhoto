package ftpImageCheck;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;

public class compareImage {
	public static void main(String[] args) {
        String server = "production.nextgenphotosolutions.com";
        int port = 21;
        String user = "prodngps@production.nextgenphotosolutions.com";
        String pass = "productionFTP@2017";

        String jpgBase = "/2025-07-27/NGPS26208/Western-Reserve";
        String pngBase = "/2025-07-27/NGPS26208/Western-Reserve_PNG";

        FTPClient ftp = new FTPClient();

        try {
            ftp.connect(server, port);
            ftp.login(user, pass);
            ftp.enterLocalPassiveMode();
            ftp.setFileType(FTP.BINARY_FILE_TYPE);
            ftp.setAutodetectUTF8(true);

            System.out.println("✅ Connected to FTP");

            Map<String, Integer> jpgCounts = getFileCounts(ftp, jpgBase, ".jpg");
            Map<String, Integer> pngCounts = getFileCounts(ftp, pngBase, ".png");

            System.out.println("\n📊 JPG vs PNG Counts:");
            System.out.println("-------------------------------------------------");
            System.out.printf("%-30s %-10s %-10s %-10s%n", "Team Folder", "JPG", "PNG", "Diff");

            for (String team : jpgCounts.keySet()) {
                int jpg = jpgCounts.getOrDefault(team, 0);
                int png = pngCounts.getOrDefault(team, 0);
                int diff = png - jpg;
                System.out.printf("%-30s %-10d %-10d %-+10d%n", team, jpg, png, diff);
            }

            ftp.logout();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (ftp.isConnected()) {
                try { ftp.disconnect(); } catch (IOException ignored) {}
            }
        }
    }

    private static Map<String, Integer> getFileCounts(FTPClient ftp, String baseFolder, String extension) throws IOException {
        Map<String, Integer> counts = new HashMap<>();

        if (!ftp.changeWorkingDirectory(baseFolder)) {
            System.out.println("❌ Folder not found: " + baseFolder);
            return counts;
        }

        FTPFile[] teamDirs = ftp.listFiles();
        for (FTPFile teamDir : teamDirs) {
            if (teamDir.isDirectory() && !teamDir.getName().equals(".") && !teamDir.getName().equals("..")) {
                String teamName = teamDir.getName();
                String teamPath = baseFolder + "/" + teamName;
                counts.put(teamName, countFilesWithExtension(ftp, teamPath, extension));
            }
        }
        return counts;
    }

    private static int countFilesWithExtension(FTPClient ftp, String folder, String extension) throws IOException {
        int count = 0;
        if (!ftp.changeWorkingDirectory(folder)) {
            return count;
        }
        FTPFile[] files = ftp.listFiles();
        for (FTPFile file : files) {
            if (file.isFile() && file.getName().toLowerCase().endsWith(extension)) {
                count++;
            }
        }
        return count;
    }
}
