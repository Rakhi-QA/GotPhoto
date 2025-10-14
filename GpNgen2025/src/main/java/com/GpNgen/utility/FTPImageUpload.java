package com.GpNgen.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.net.ftp.FTPClient;

public class FTPImageUpload 
{
	private static final String HOST = "staging.production.nextgenphotosolutions.com";
    private static final String USER = "imageprocessing@staging.production.nextgenphotosolutions.com";
    private static final String PASS = "5Z6$7I*L7Z-k";
    
   /* private static final String HOST = "production.nextgenphotosolutions.com";
    private static final String USER = "imageprongp@production.nextgenphotosolutions.com";
    private static final String PASS = "imgprocessing@2017";*/
   
    public static boolean createFolderAndUpload(String jobName) {
    	FTPClient ftpClient = new FTPClient();
    	//String localPath = "C:/Users/Tiu User (Rakhi)/Desktop/GP postman images/";
		boolean allUploaded = true;
        
     // List of images with local path and file name
        String[][] images = {
            { "C:/Users/Tiu User (Rakhi)/Desktop/GP postman images/GPImgaesAutoScript/A.jpg", "A.jpg" },
            { "C:/Users/Tiu User (Rakhi)/Desktop/GP postman images/GPImgaesAutoScript/B.jpg", "B.jpg" },
            { "C:/Users/Tiu User (Rakhi)/Desktop/GP postman images/GPImgaesAutoScript/C.jpg", "C.jpg" },
            { "C:/Users/Tiu User (Rakhi)/Desktop/GP postman images/GPImgaesAutoScript/D.jpg", "D.jpg" },
            { "C:/Users/Tiu User (Rakhi)/Desktop/GP postman images/GPImgaesAutoScript/E.jpg", "E.jpg" },
            { "C:/Users/Tiu User (Rakhi)/Desktop/GP postman images/GPImgaesAutoScript/F.jpg", "F.jpg" },
            { "C:/Users/Tiu User (Rakhi)/Desktop/GP postman images/GPImgaesAutoScript/G.jpg", "G.jpg" },
            { "C:/Users/Tiu User (Rakhi)/Desktop/GP postman images/GPImgaesAutoScript/H.jpg", "H.jpg" },
            { "C:/Users/Tiu User (Rakhi)/Desktop/GP postman images/GPImgaesAutoScript/I.jpg", "I.jpg" }
        };
        
        String remoteFileName = "/gotphoto/input/" + jobName + "/photos"; 
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
            String[] folders = remoteFileName.split("/");
            String currentPath = "";
            for (String folder : folders) {
                if (!folder.isEmpty()) {
                    currentPath += "/" + folder;
                    ftpClient.makeDirectory(currentPath); // creates if not exist
                    ftpClient.changeWorkingDirectory(currentPath);
                }
            }
            for (String[] image : images) {
                String localPath1 = image[0];
                String remoteFileName1 = image[1];

                File localFile = new File(localPath1);
                if (!localFile.exists()) {
                    System.out.println("⚠️ File not found: " + localPath1);
                    allUploaded = false;
                    continue;
                }
               try ( FileInputStream inputStream = new FileInputStream(localFile)){
                boolean uploaded = ftpClient.storeFile(remoteFileName1, inputStream);
                inputStream.close();

                if (uploaded) {
                    System.out.println("✅ Uploaded: " + remoteFileName1);
                } else {
                    System.out.println("❌ Failed to upload: " + remoteFileName1);
                    allUploaded = false;
                }
               }
            }

        } catch (IOException e) {
        	System.out.println("❌ FTP Exception: " + e.getMessage());
            allUploaded = false;
        } finally {
            try {
                if (ftpClient.isConnected()) {
                    ftpClient.logout();
                    ftpClient.disconnect();
                }
            } catch (IOException ignored) {}
        }

        return allUploaded;
    }
        
    }
