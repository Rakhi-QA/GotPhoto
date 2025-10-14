package com.GpNgen.utility;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.FileInputStream;
import java.io.IOException;
public class NoUse_FTP_Connection_WithUpload {
	
	   /* public static boolean uploadImageToFTP(String localFilePath, String remoteFilePath) {
	    	String server = "staging.production.nextgenphotosolutions.com";
	        int port = 21;
	        String user = "imageprocessing@staging.production.nextgenphotosolutions.com";
	        String pass = "5Z6$7I*L7Z-k";
	        FTPClient ftpClient = new FTPClient();

	        try {
	            System.out.println("🚀 Connecting to FTP...");
	            ftpClient.connect(server, port);
	            System.out.println("✅ Connected to: " + server);

	            boolean loginSuccess = ftpClient.login(user, pass);
	            System.out.println("🔐 Login successful? " + loginSuccess);
	            if (!loginSuccess) {
	                System.out.println("❌ Login failed — check username/password!");
	                return false;
	            }

	            ftpClient.enterLocalPassiveMode();
	            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

	            FileInputStream inputStream = new FileInputStream(localFilePath);
	            System.out.println("📤 Uploading: " + localFilePath + " to " + remoteFilePath);

	            boolean uploadSuccess = ftpClient.storeFile(remoteFilePath, inputStream);
	            inputStream.close();
	            System.out.println("✅ Upload success? " + uploadSuccess);

	            ftpClient.logout();
	            ftpClient.disconnect();

	            return uploadSuccess;

	        } catch (IOException ex) {
	            System.out.println("❌ Upload failed due to exception: " + ex.getMessage());
	            ex.printStackTrace();
	            return false;
	        }
	    }*/
	
	
	
	private static final String HOST = "staging.production.nextgenphotosolutions.com";
    private static final String USER = "imageprocessing@staging.production.nextgenphotosolutions.com";
    private static final String PASS = "5Z6$7I*L7Z-k";
   
    public static boolean createFolderAndUpload(String localPath, String remoteFolderPath, String remoteFileName) {
        FTPClient ftpClient = new FTPClient();
        try {
            ftpClient.connect(HOST);
            if (!ftpClient.login(USER, PASS)) 
            {
                System.out.println("❌ Login failed. Please check FTP credentials.");
                return false;
            }
            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);

            
         // Create nested folders if they don't exist
            String[] folders = remoteFolderPath.split("/");
            String currentPath = "";
            for (String folder : folders) {
                if (!folder.isEmpty()) {
                    currentPath += "/" + folder;
                    ftpClient.makeDirectory(currentPath); // creates if not exist
                    ftpClient.changeWorkingDirectory(currentPath);
                }
            }

            // Upload the file
            FileInputStream inputStream = new FileInputStream(localPath);
            boolean uploaded = ftpClient.storeFile(remoteFileName, inputStream);
            inputStream.close();

            if (uploaded) {
                System.out.println("✅ File uploaded successfully to: " + remoteFolderPath + "/" + remoteFileName);
            } else {
                System.out.println("❌ File upload failed.");
            }
            return uploaded;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ignored) {}
        }
            
           
    }}



