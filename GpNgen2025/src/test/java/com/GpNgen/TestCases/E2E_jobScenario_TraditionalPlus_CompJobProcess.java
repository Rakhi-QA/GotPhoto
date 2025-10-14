package com.GpNgen.TestCases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.GpNgen.Base.BaseClass;
import com.GpNgen.PageObject.FullCompositePage;
import com.GpNgen.utility.ExtentManager;
import com.GpNgen.utility.FTPImageUpload;
import com.GpNgen.utility.GlobalData;
import com.GpNgen.utility.ImageTransferUtil;
import com.GpNgen.utility.Log;
import com.GpNgen.utility.ScreenshotUtil;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;


//=======Traditional Plus with Ignore Team option is present  End to End job Complete Job Process==============================

public class E2E_jobScenario_TraditionalPlus_CompJobProcess extends BaseClass{

	
	FullCompositePage fc;
	ExtentReports extent;
	ExtentTest test;
	
	@BeforeMethod
	public void setup()
	{
		launchApp();
		generateCheckoutUrl();
	}
	
	
	@BeforeTest
    public void setUpReport() {
        extent = ExtentManager.getInstance();
    }
	
	
	
	@Test()
	public void place_TraditionalPlus_Job_With_NoTeamImage() throws Throwable {
		
		
		test = extent.createTest("Traditional Plus Job → No Images → Extract → Upload → Save → Checkout → FTP & API");
	    Log.startTestCase("Verify that Traditional Plus job is placed with no images present");

	    fc = new FullCompositePage(driver);

	    try {
	        test.info("Photographer selects 'Traditional Plus' option.");
	        fc.clickOnTraditionalPlusoption();
	 
	        test.info("User selects 'No Team → Ignore' option.");
	        fc.clickonignoreTeam();
	       // Thread.sleep(100);

	        test.info("Graphics options displayed for Traditional Plus job:");
	        fc.check_45V_VisibleorEnabled(test);
	        fc.check_45TV_VisibleorEnabled(test);
	        fc.check_55_VisibleorEnabled(test);
	        fc.check_55T_VisibleorEnabled(test);
	        fc.check_MM_VisibleorEnabled(test);
	        fc.check_SV_VisibleorEnabled(test);
	        fc.check_12TV_VisibleorEnabled(test);

	        test.info("Default options validation for Traditional Plus:");
	        fc.check_A1_45V_isSelectedOrNot(test);
	        fc.check_A2_45V_isSelectedOrNot(test);
	        fc.check_A1_45TV_isSelectedOrNot(test);
	        fc.check_A2_45TV_isSelectedOrNot(test);
	        fc.check_A1_MM_isSelectedOrNot(test);
	        fc.check_A2_MM_isSelectedOrNot(test);
	        Thread.sleep(100);

	        test.info("User selects Background Template.");
	        Log.info("===== Selecting Background Template =====");
	        fc.ClickonSingleTemp();
	        Thread.sleep(100);
	        fc.ClickOnSingleTempforTraditionalPlus(test);

	        test.info("Upload team photo section check.");
	        fc.check_uploadTeamsPhoto_EnableOrDisable(test);

	        test.info("Uploading 2 team photos.");
	        String[] filePaths = {
	            "C:\\Users\\Tiu User (Rakhi)\\Desktop\\TEAM Name Auto Selection\\New folder\\YANKEES.jpg",
	            "C:\\Users\\Tiu User (Rakhi)\\Desktop\\TEAM Name Auto Selection\\New folder\\Team.jpg"
	        };
	        fc.clickOnUploadTeamsPhotos(filePaths);

	        test.info("Clicking upload button for team photos.");
	        fc.clickOnuploadTeamPhotoBtn();
	        Thread.sleep(10000);

	        test.info("Photographer selects team names.");
	        fc.selectTeamName1();
	        fc.selectTeamName2();
	        Thread.sleep(500);

	        test.info("User clicks 'Save Team' button.");
	        fc.clickOnSaveTeam();
	        Thread.sleep(1000);

	        test.info("Photographer clicks on 'Extracted Image' button.");
	        fc.clickOnextractImage();
	        Thread.sleep(100);

	        test.info("User performs PNG crop operations.");
	        fc.clickOnfullLengthCentering(test);
	        fc.clickOncentered(test);
	        Thread.sleep(200);

	        test.info("User selects 'Unique Color' option.");
	        fc.clickOnuniqueColor();
	        Thread.sleep(300);

	        test.info("User uploads reference file.");
	        String[] referenceFiles = {
	            "C:\\Users\\Tiu User (Rakhi)\\Desktop\\TEAM Name Auto Selection\\New folder\\referenceFilepaths.jpg"
	        };
	        fc.clickOnuploadReferenceFile(referenceFiles);

	        test.info("User uploads logos.");
	        String[] logoFiles1 = {
	            "C:\\Users\\Tiu User (Rakhi)\\Desktop\\TEAM Name Auto Selection\\New folder\\logo.jpg"
	        };
	        String[] logoFiles2 = {
	            "C:\\Users\\Tiu User (Rakhi)\\Desktop\\logo\\wp4012099.jpg"
	        };
	        fc.clickOnuploadReferenceFile(logoFiles1);
	        fc.clickOnuploadReferenceFile(logoFiles2);
	        Thread.sleep(1000);

	        test.info("User selects 'Color Correction' option.");
	        fc.clickOncolorCorrection();

	        test.info("User applies 50% discount code.");
	        fc.clickOndicount();
	        fc.clickOnredeem();
	        Thread.sleep(500);

	        test.info("User agrees to Terms & Conditions.");
	        fc.clickOnterms();
	        Thread.sleep(500);

	        test.pass("✅ Test steps executed successfully.");
	        test.info("Clicking 'Checkout' to place order.");
	        fc.clickOncheckOut();
	        Log.info("============== Order placed ============ ");

	        // ========================== FTP Upload ===============================
	        test.info("✅ Transferring images to FTP 'input/photos' folder.");
	        boolean uploaded = FTPImageUpload.createFolderAndUpload(GlobalData.job_Name);
	        Assert.assertTrue(uploaded, "❌ FTP Upload failed.");
	        Thread.sleep(10000);

	        // ========================== Confirm API ==============================
	        test.info("✅ Triggering API 2 to transfer images from input → cam1.");
	        String finalURL = GlobalData.checkoutUrl;
	        String jobId = null;

	        if (finalURL != null && finalURL.contains("jobno=")) {
	            try {
	                String[] parts = finalURL.split("jobno=");
	                jobId = parts[1].split("&")[0];
	                test.info("✅ Extracted Job ID: " + jobId);
	            } catch (Exception e) {
	                test.fail("❌ Failed to parse Job ID from URL: " + e.getMessage());
	            }
	        } else {
	            test.fail("❌ Job ID not found in the checkout URL.");
	        }

	        if (jobId != null) {
	            ImageTransferUtil.confirmImageTransfer(jobId);
	            test.pass("✅ API 2 (confirm image transfer) called successfully for Job ID: " + jobId);
	        } else {
	            test.fail("❌ Skipping API 2: Job ID is null.");
	        }

	    } catch (Exception e) {
	        String screenshotPath = ScreenshotUtil.captureScreenshot(driver, "TC_Failure");
	        test.fail("❌ Test failed with exception: " + e.getMessage(),
	                MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
	        Log.error("❌ Exception occurred during test execution: ");
	        throw e;
	    }
	}
	
	
	@Test
	public void place_TraditionalPlus_Job_With_NoTeamImage_SaveCard() throws Throwable {
		
		
		test = extent.createTest("Traditional Plus job is placed with no images present.");
	    Log.startTestCase("Verify that Traditional Plus job is placed with no images present.");

	    try {
	        fc = new FullCompositePage(driver);

	        // Step 1: Select Traditional Plus Option
	        test.info("📌 Photographer selects Traditional Plus option.");
	        fc.clickOnTraditionalPlusoption();

	        // Step 2: Verify default selections
	        test.info("✔️ 'No Team' option is selected by default.");
	        test.info("✔️ List of Alternate Graphics options is visible for Traditional Plus job.");
	        
	        // Step 3: Validate Alternate Graphics visibility
	        fc.check_45V_VisibleorEnabled(test);
	        fc.check_45TV_VisibleorEnabled(test);
	        fc.check_55_VisibleorEnabled(test);
	        fc.check_55T_VisibleorEnabled(test);
	        fc.check_MM_VisibleorEnabled(test);
	        fc.check_SV_VisibleorEnabled(test);
	        fc.check_12TV_VisibleorEnabled(test);

	        // Step 4: Validate default selections
	        test.info("✔️ By default, these graphics options are selected:");
	        fc.check_A1_45V_isSelectedOrNot(test);
	        fc.check_A2_45V_isSelectedOrNot(test);
	        fc.check_A1_45TV_isSelectedOrNot(test);
	        fc.check_A2_45TV_isSelectedOrNot(test);
	        fc.check_A1_MM_isSelectedOrNot(test);
	        fc.check_A2_MM_isSelectedOrNot(test);

	        // Step 5: Select Background template
	        test.info("🎨 Selecting background template.");
	        fc.ClickonSingleTemp();
	        fc.ClickOnSingleTempforTraditionalPlus(test);

	        // Step 6: Upload Team Photos
	        test.info("📤 Uploading team photos.");
	        fc.check_uploadTeamsPhoto_EnableOrDisable(test);

	        String[] teamPhotos = {
	            "C:\\Users\\Tiu User (Rakhi)\\Desktop\\TEAM Name Auto Selection\\New folder\\KANSAS CITY 7.jpg",
	            "C:\\Users\\Tiu User (Rakhi)\\Desktop\\TEAM Name Auto Selection\\New folder\\YANKEES.jpg",
	            "C:\\Users\\Tiu User (Rakhi)\\Desktop\\TEAM Name Auto Selection\\New folder\\Team.jpg"
	        };
	        fc.clickOnUploadTeamsPhotos(teamPhotos);
	        fc.clickOnuploadTeamPhotoBtn();

	        Thread.sleep(10000);

	        // Step 7: Select Team Names
	        test.info("👥 Selecting team names.");
	        fc.selectTeamName1();
	        fc.selectTeamName2();
	        fc.selectTeamName3();

	        fc.clickOnSaveTeam();
	        Thread.sleep(1000);

	        // Step 8: Extraction and Cropping
	        test.info("🔍 Extracting and cropping images.");
	        fc.clickOnextractImage();
	        Thread.sleep(100);
	        fc.clickOnfullLengthCentering(test);
	        fc.clickOncentered(test);
	        Thread.sleep(100);

	        // Step 9: Upload Reference Files and Logo
	        test.info("📌 Selecting unique color and uploading reference/logo files.");
	        fc.clickOnuniqueColor();

	        String[] referenceFiles = {
	            "C:\\Users\\Tiu User (Rakhi)\\Desktop\\TEAM Name Auto Selection\\New folder\\referenceFilepaths.jpg"
	        };
	        fc.clickOnuploadReferenceFile(referenceFiles);

	        String[] logoFiles = {
	            "C:\\Users\\Tiu User (Rakhi)\\Desktop\\TEAM Name Auto Selection\\New folder\\logo.jpg",
	            "C:\\Users\\Tiu User (Rakhi)\\Desktop\\logo\\wp4012099.jpg"
	        };
	        for (String file : logoFiles) {
	            fc.clickOnuploadReferenceFile(new String[]{file});
	        }
	        Thread.sleep(1000);

	        // Step 10: Apply Color Correction
	        test.info("🎨 Applying color correction.");
	        fc.clickOncolorCorrection();

	        // Step 11: Agree to Terms & Use Saved Card
	        test.info("✅ Accepting terms and using saved card.");
	        fc.clickOnterms();
	        fc.clickOnuseSaveCard();
	        Thread.sleep(1000);
	        fc.ClikOnCardNumber(test);
	        Thread.sleep(500);

	        // Step 12: Final Checkout
	        test.info("🛒 Clicking on checkout.");
	        fc.clickOncheckOut();

	        // Step 13: FTP Upload
	        test.info("📁 Transferring images to FTP → input/photos folder.");
	        boolean uploaded = FTPImageUpload.createFolderAndUpload(GlobalData.job_Name);
	        Assert.assertTrue(uploaded, "❌ Image upload failed!");
	        Thread.sleep(60000);

	        // Step 14: Trigger API to confirm image transfer
	        test.info("📡 Triggering second API: Transfer images from input → cam1.");

	        String finalURL = GlobalData.checkoutUrl;
	        String jobId = null;

	        if (finalURL != null && finalURL.contains("jobno=")) {
	            try {
	                String[] parts = finalURL.split("jobno=");
	                jobId = parts[1].split("&")[0];
	                test.info("✅ Extracted Job ID: " + jobId);
	            } catch (Exception e) {
	                test.fail("❌ Failed to parse Job ID from URL: " + e.getMessage());
	            }
	        } else {
	            test.fail("❌ Job ID not found in the checkout URL.");
	        }

	        if (jobId != null) {
	            ImageTransferUtil.confirmImageTransfer(jobId);
	            test.pass("✅ Image transfer API called successfully for Job ID: " + jobId);
	        } else {
	            test.fail("❌ Skipping API call: Job ID is null.");
	        }

	        // Final Result
	        test.pass("✅ Traditional Plus job is placed successfully without images.");
	        Log.info("============== Order placed successfully ==============");

	    } catch (Exception e) {
	        test.fail("❌ Test failed due to exception: " + e.getMessage());
	        e.printStackTrace();
	        Assert.fail("Test encountered an exception.");
	    }
			}
			
	
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
		extent.flush();
	}
}
