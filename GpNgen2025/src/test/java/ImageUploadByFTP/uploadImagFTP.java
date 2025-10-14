package ImageUploadByFTP;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.GpNgen.Base.APIpostRequest;
import com.GpNgen.utility.NoUse_FTP_Connection_WithUpload;
import com.GpNgen.utility.GlobalData;

public class uploadImagFTP {
	
	 /*@Test(enabled = false)
	    public void testImageUploadToFTP() {
		 
		 String jobFolder = "/gotphoto/input/" + GlobalData.jobName + "/camera1/";
		 String[][] imagePaths = {
			        { "C:/Users/Tiu User (Rakhi)/Desktop/GP postman images/GPImgaesAutoScript/img1.jpg", "/250627_7907/camera1/img1.jpg" },
			        { "C:/Users/Tiu User (Rakhi)/Desktop/GP postman images/GPImgaesAutoScript/img2.jpg", "/250627_7907/camera1/img2.jpg" },
			        { "C:/Users/Tiu User (Rakhi)/Desktop/GP postman images/GPImgaesAutoScript/img3.jpg", "/250627_7907/camera1/img3.jpg" },
			        { "C:/Users/Tiu User (Rakhi)/Desktop/GP postman images/GPImgaesAutoScript/img3.jpg", "/250627_7907/camera1/img4.jpg" }
			        // Add more as needed
			    };

		 for (String[] pathPair : imagePaths) {
		        boolean success = FTPUploader.uploadImageToFTP(pathPair[0], pathPair[1]);
		        Assert.assertTrue(success, "Image upload failed for: " + pathPair[0]);
		    }
	    }*/
	
	
	@Test(dependsOnMethods = "check_FC_With_Virtual_50Off_With_EnterCard")
    public void uploadJobImages() {
       
		String jobName = GlobalData.job_Name;
		 Assert.assertNotNull(jobName, "❌ Job name is null! First test case did not run properly.");
		//String jobName = APIpostRequest.sendCreateJobRequest();
        String ftpPath = "/gotphoto/input/" + jobName + "/photos";

        String[][] images = {
            { "C:/Users/Tiu User (Rakhi)/Desktop/GP postman images/GPImgaesAutoScript/img1.jpg", "img1.jpg" },
            { "C:/Users/Tiu User (Rakhi)/Desktop/GP postman images/GPImgaesAutoScript/img2.jpg", "img2.jpg"},
            { "C:/Users/Tiu User (Rakhi)/Desktop/GP postman images/GPImgaesAutoScript/img3.jpg", "img3.jpg" },
            { "C:/Users/Tiu User (Rakhi)/Desktop/GP postman images/GPImgaesAutoScript/img4.jpg", "img4.jpg" }
        };

        for (String[] img : images) {
            boolean success = NoUse_FTP_Connection_WithUpload.createFolderAndUpload(img[0], ftpPath, img[1]);
            Assert.assertTrue(success, "Failed to upload " + img[1]);
        }
        System.out.println("📁 FTP Folder: " + ftpPath);
    }
	
	
	

}
