package com.GpNgen.TestCases;


import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
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

public class E2E_FC_NOP_006 extends BaseClass
{
		//========================NO TEAM >> ALL IND IMAGE IS PRESENT(IMG1---IMG5) =======================
	ExtentReports extent;
	ExtentTest test;
	FullCompositePage fc;
	
	@BeforeSuite
    public void setUpReport() {
        extent = ExtentManager.getInstance();
    }

	@BeforeMethod
	public void setup()
	{
		launchApp();
		generateCheckoutUrl2();
		
	}
	@Test
	public void check_FC_NOTeam_All_Ind_Image_Present() throws Throwable
	{
		test = extent.createTest("✅ Validate popup when selecting 'No Team' and Image5 column has images");
	    Log.startTestCase("Validate popup when selecting 'No Team' and Image5 column has images");

	    try {
	        fc = new FullCompositePage(driver);

	        // Step 1: Select "No Team"
	        test.info("🔘 Step 1: Photographer selects Full Composite → No Team");
	        fc.ClickonNoTeam();
	        // Step 2: Validate popup message and action
	        test.info("🔔 Step 2: Validating popup message for Image5 column");
	        Thread.sleep(500);
	        fc.getImgpresetMessage();  // Capture or assert the popup message
	        fc.clickonignoreTeam();
	        
	        
	        test.pass("✅ Popup displayed correctly and 'Ignore Team Images' button clicked.");

	        // Step 3: Template Selection
	        test.info("🖼️ Step 3: Selecting Single Template for Full Composite");
	        fc.ClickonSingleTemp();
	        fc.ClikOnTempDropDown(test);
	        Thread.sleep(500);

	        // Step 4: Extract Image Flow
	        test.info("🧲 Step 4: Selecting Extract Individual Image options");
	        fc.clickOnextractImage();
	        Thread.sleep(100);
	        fc.clickOnextractIndividualImage(test);
	        Thread.sleep(100);
	        fc.click_ON_3_4_Crop(test);
	        Thread.sleep(100);
	        fc.clickOnpngTeamAddOn(test);
	        Thread.sleep(100);

	        // Step 5: Color and Design Options
	        test.info("🎨 Step 5: Applying design and color options");
	        fc.clickOnuniqueColor();
	        fc.clickOncolorCorrection();

	        // Step 6: Discount Code
	        Log.info("💸 Step 6: Applying 50% discount code and redeeming");
	        fc.clickOn50dicount();
	        fc.clickOnredeem();

	        // Step 7: Terms & Checkout
	        fc.clickOnterms();
	        Log.info("💳 Step 7: Using saved card for payment");
	        fc.clickOnuseSaveCard();
	        Thread.sleep(500);
	        fc.ClikOnCardNumber(test);
	        fc.clickOncheckOut();

	        // ====================== FTP Upload =========================
	        test.info("📂 Step 8: Uploading images to FTP → input/photos");
	        boolean uploaded = FTPImageUpload.createFolderAndUpload(GlobalData.job_Name);
	        Assert.assertTrue(uploaded, "❌ Image upload failed!");
	        test.pass("✅ Images uploaded to FTP successfully.");
	        Thread.sleep(10000);

	        // ===================== Confirm Image Transfer ==========================
	        test.info("🔁 Step 9: Triggering second API to transfer images from input → cam1");

	        String finalURL = GlobalData.checkoutUrl;
	        String jobId = null;

	        if (finalURL != null && finalURL.contains("jobno=")) {
	            try {
	                String[] parts = finalURL.split("jobno=");
	                jobId = parts[1].split("&")[0];
	                System.out.println("✅ Extracted Job ID: " + jobId);
	                test.pass("✅ Job ID extracted from URL: " + jobId);
	            } catch (Exception e) {
	                test.fail("❌ Failed to parse Job ID from URL. Exception: " + e.getMessage());
	            }
	        } else {
	            test.fail("❌ Job ID not found in the checkout URL.");
	        }

	        if (jobId != null) {
	            ImageTransferUtil.confirmImageTransfer(jobId);
	            test.pass("✅ API 2 (Image Transfer Confirmation) called successfully for Job ID: " + jobId);
	        } else {
	            test.fail("❌ Skipping API 2: Job ID is null.");
	        }

	        test.pass("✅ Test case executed successfully: Popup was shown as expected and workflow completed.(FULLCOMPOSITE JOB)");
	        
	    } catch (Exception e) {
	        String screenshotPath = ScreenshotUtil.captureScreenshot(driver, "TC_Failure");
	        test.fail("❌ Test Failed. Exception: " + e.getMessage(),
	                MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
	    }
           
        }
	
	@Test
	public void check_Traditiona_NOTeam_All_Ind_Image_Present() throws Throwable
	{
		test = extent.createTest("✅ Validate popup when selecting 'No Team' and Image5 column has images For Traditional  ");
	    Log.startTestCase("Validate popup when selecting 'No Team' and Image5 column has images For Traditional ");

	    try {
	        fc = new FullCompositePage(driver);
	        fc.clickOnTraditionalOption();
	        
	        // Step 1: Select "No Team"
	        test.info("🔘 Step 1: Photographer selects Traditional  → No Team");
	        
	        // Step 2: Validate popup message and action
	        test.info("🔔 Step 2: Validating popup message for Image5 column");
	        //fc.getImgpresetMessageTP();  // Capture or assert the popup message
	        String popupMessage = fc.getImgpresetMessageT(); // Capture the popup message
	        test.info("📌 Popup Message Displayed: " + popupMessage); // Log it in Extent Report
	        
	        Thread.sleep(500);
	        fc.clickOnIgnoreBTNT();
	        test.pass("✅ Popup displayed correctly and 'Ignore Team Images' button clicked.");
	        fc.ClickonA1_45TV();
	        fc.ClickonA2_45V();
	        fc.ClickonA3_45V();
	        fc.ClickonA4_45V();
	        fc.ClickonA5_45V();
	        test.info("Alternate Pose Graphic Options is selected 45TV,45V");
	        // Step 3: Template Selection
	        test.info("🖼️ Step 3: Selecting Single Template for Traditional ");
	        fc.ClickonSingleTemp();
	        fc.ClickOnSingleTempforTraditional(test);
	        Thread.sleep(500);

	      
	        

	        // Step 5: Color and Design Options
	        test.info("🎨 Step 5: Applying design and color options");
	        fc.clickOnuniqueColor();
	        fc.clickOncolorCorrection();

	        // Step 6: Discount Code
	        Log.info("💸 Step 6: Applying 50% discount code and redeeming");
	        fc.clickOn50dicount();
	        fc.clickOnredeem();

	        // Step 7: Terms & Checkout
	        fc.clickOnterms();
	        Log.info("💳 Step 7: Using saved card for payment");
	        fc.clickOnuseSaveCard();
	        Thread.sleep(500);
	        fc.ClikOnCardNumber(test);
	        fc.clickOncheckOut();

	        // ====================== FTP Upload =========================
	        test.info("📂 Step 8: Uploading images to FTP → input/photos");
	        boolean uploaded = FTPImageUpload.createFolderAndUpload(GlobalData.job_Name);
	        Assert.assertTrue(uploaded, "❌ Image upload failed!");
	        test.pass("✅ Images uploaded to FTP successfully.");
	        Thread.sleep(10000);

	        // ===================== Confirm Image Transfer ==========================
	        test.info("🔁 Step 9: Triggering second API to transfer images from input → cam1");

	        String finalURL = GlobalData.checkoutUrl;
	        String jobId = null;

	        if (finalURL != null && finalURL.contains("jobno=")) {
	            try {
	                String[] parts = finalURL.split("jobno=");
	                jobId = parts[1].split("&")[0];
	                System.out.println("✅ Extracted Job ID: " + jobId);
	                test.pass("✅ Job ID extracted from URL: " + jobId);
	            } catch (Exception e) {
	                test.fail("❌ Failed to parse Job ID from URL. Exception: " + e.getMessage());
	            }
	        } else {
	            test.fail("❌ Job ID not found in the checkout URL.");
	        }

	        if (jobId != null) {
	            ImageTransferUtil.confirmImageTransfer(jobId);
	            test.pass("✅ API 2 (Image Transfer Confirmation) called successfully for Job ID: " + jobId);
	        } else {
	            test.fail("❌ Skipping API 2: Job ID is null.");
	        }

	        test.pass("✅ Test case executed successfully: Popup was shown as expected and workflow completed For Traditional .");
	        
	    } catch (Exception e) {
	        String screenshotPath = ScreenshotUtil.captureScreenshot(driver, "TC_Failure");
	        test.fail("❌ Test Failed. Exception: " + e.getMessage(),
	                MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
	    }
           
        }
	
	@Test
	public void check_TraditionaPlus_NOTeam_All_Ind_Image_Present() throws Throwable
	{
		test = extent.createTest("✅ Validate popup when selecting 'No Team' and Image5 column has images For Traditional Plus ");
	    Log.startTestCase("Validate popup when selecting 'No Team' and Image5 column has images For Traditional Plus");

	    try {
	        fc = new FullCompositePage(driver);
	        fc.clickOnTraditionalPlusoption();
	        
	        // Step 1: Select "No Team"
	        test.info("🔘 Step 1: Photographer selects Traditional Plus → No Team");
	        
	        Thread.sleep(500);

	        // Step 2: Validate popup message and action
	        test.info("🔔 Step 2: Validating popup message for Image5 column");
	        //fc.getImgpresetMessageTP();  // Capture or assert the popup message
	        String popupMessage = fc.getImgpresetMessageTP(); // Capture the popup message
	        test.info("📌 Popup Message Displayed: " + popupMessage); // Log it in Extent Report
	        
	        Thread.sleep(100);
	        fc.clickOnIgnoreBTNTP();
	        test.pass("✅ Popup displayed correctly and 'Ignore Team Images' button clicked.");

	        // Step 3: Template Selection
	        test.info("🖼️ Step 3: Selecting Single Template for Traditional Plus");
	        fc.ClickonSingleTemp();
	        fc.ClickOnSingleTempforTraditionalPlus(test);
	        Thread.sleep(500);

	        test.info("Upload team photo section check.");
	        fc.check_uploadTeamsPhoto_EnableOrDisable(test);

	        test.info("Uploading 2 team photos.");
	        String[] filePaths = {
	            "C:\\Users\\Tiu User (Rakhi)\\Desktop\\TEAM Name Auto Selection\\New folder\\YANKEES.jpg",
	            "C:\\Users\\Tiu User (Rakhi)\\Desktop\\TEAM Name Auto Selection\\New folder\\Team.jpg",
	            "C:\\Users\\Tiu User (Rakhi)\\Desktop\\TEAM Name Auto Selection\\New folder\\KANSAS CITY 7.jpg"
	        };
	        fc.clickOnUploadTeamsPhotos(filePaths);

	        test.info("Clicking upload button for team photos.");
	        fc.clickOnuploadTeamPhotoBtn();
	        Thread.sleep(10000);

	        test.info("Photographer selects team names.");
	        fc.selectTeamName1();
	        fc.selectTeamName2();
	        fc.selectTeamName3();
	        Thread.sleep(500);

	        test.info("User clicks 'Save Team' button.");
	        fc.clickOnSaveTeam();
	        Thread.sleep(1000);
	        
	        // Step 4: Extract Image Flow
	        test.info("🧲 Step 4: Selecting Extract Individual Image options");
	        fc.clickOnextractImage();
	        Thread.sleep(100);
	        fc.clickOnextractIndividualImage(test);
	        Thread.sleep(100);
	        fc.click_ON_3_4_Crop(test);
	        Thread.sleep(100);
	        

	        // Step 5: Color and Design Options
	        test.info("🎨 Step 5: Applying design and color options");
	        fc.clickOnuniqueColor();
	        fc.clickOncolorCorrection();

	        // Step 6: Discount Code
	        Log.info("💸 Step 6: Applying 50% discount code and redeeming");
	        fc.clickOn50dicount();
	        fc.clickOnredeem();

	        // Step 7: Terms & Checkout
	        fc.clickOnterms();
	        Log.info("💳 Step 7: Using saved card for payment");
	        fc.clickOnuseSaveCard();
	        Thread.sleep(500);
	        fc.ClikOnCardNumber(test);
	        fc.clickOncheckOut();

	        // ====================== FTP Upload =========================
	        test.info("📂 Step 8: Uploading images to FTP → input/photos");
	        boolean uploaded = FTPImageUpload.createFolderAndUpload(GlobalData.job_Name);
	        Assert.assertTrue(uploaded, "❌ Image upload failed!");
	        test.pass("✅ Images uploaded to FTP successfully.");
	        Thread.sleep(10000);

	        // ===================== Confirm Image Transfer ==========================
	        test.info("🔁 Step 9: Triggering second API to transfer images from input → cam1");

	        String finalURL = GlobalData.checkoutUrl;
	        String jobId = null;

	        if (finalURL != null && finalURL.contains("jobno=")) {
	            try {
	                String[] parts = finalURL.split("jobno=");
	                jobId = parts[1].split("&")[0];
	                System.out.println("✅ Extracted Job ID: " + jobId);
	                test.pass("✅ Job ID extracted from URL: " + jobId);
	            } catch (Exception e) {
	                test.fail("❌ Failed to parse Job ID from URL. Exception: " + e.getMessage());
	            }
	        } else {
	            test.fail("❌ Job ID not found in the checkout URL.");
	        }

	        if (jobId != null) {
	            ImageTransferUtil.confirmImageTransfer(jobId);
	            test.pass("✅ API 2 (Image Transfer Confirmation) called successfully for Job ID: " + jobId);
	        } else {
	            test.fail("❌ Skipping API 2: Job ID is null.");
	        }

	        test.pass("✅ Test case executed successfully: Popup was shown as expected and workflow completed For Traditional Plus.");
	        
	    } catch (Exception e) {
	        String screenshotPath = ScreenshotUtil.captureScreenshot(driver, "TC_Failure");
	        test.fail("❌ Test Failed. Exception: " + e.getMessage(),
	                MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
	    }
           
        }
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
		extent.flush();
	}
}
