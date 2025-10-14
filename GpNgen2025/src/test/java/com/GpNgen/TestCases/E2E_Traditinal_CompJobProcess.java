package com.GpNgen.TestCases;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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

public class E2E_Traditinal_CompJobProcess extends BaseClass
{
	//=============TRADITIONAL JOB (Save Card ,Enter Card Details,100OFF Discount,HalfDiscount Half payment method)===================
	FullCompositePage fc;
	ExtentReports extent;
	ExtentTest test;
	
	
	@BeforeSuite
    public void setUpReport() {
        extent = ExtentManager.getInstance();
    }
	@BeforeMethod
	public void setup()
	{
		launchApp();
		generateCheckoutUrl();
	}
	
	@Test
	public void check_Traditional_Job_Ignore_With_50OFF_SaveCard() throws Throwable
	{
		 test = extent.createTest("Traditional Job → Ignore Team Images → MM Template → Order placed with Enter Card.");
		    Log.startTestCase("Traditional Job with Ignore Team Images");

		    fc = new FullCompositePage(driver);

		    try {
		        // ========================== Job Setup ==========================
		        test.info("📌 User selects 'Traditional' job type.");
		        fc.clickOnTraditionalOption();
		        Thread.sleep(2000);

		        test.info("📌 User chooses 'Ignore Team Images' option.");
		        Log.info("User selected 'Ignore Team Images'.");
		        fc.clickOntraditionalignoreTeamImage();
		        Thread.sleep(2000);

		        // ========================== Template Selection ==========================
		        test.info("🧩 User selects MM template.");
		        fc.ClickonA1_MM();
		        Thread.sleep(1000);

		        test.info("📐 User selects 'Single Template' for MM.");
		        fc.ClickonSingleTemp();
		        Thread.sleep(1000);
		        fc.ClickOnSingleTempforTraditional(test);

		        // ========================== Upload Team Images ==========================
		        test.info("📁 Uploading 2 team images.");
		        Log.info("Uploading team images to platform.");
		        String[] filePaths = {
		            "C:\\Users\\Tiu User (Rakhi)\\Desktop\\TEAM Name Auto Selection\\New folder\\KANSAS CITY 7.jpg",
		            "C:\\Users\\Tiu User (Rakhi)\\Desktop\\TEAM Name Auto Selection\\New folder\\YANKEES.jpg"
		        };
		        Thread.sleep(2000);
		        fc.clickOnUploadTeamsPhotos(filePaths);
		        Thread.sleep(1000);

		        test.info("📤 Clicking 'Upload' button for team images.");
		        fc.clickOnuploadTeamPhotoBtn();
		        Thread.sleep(10000);

		        test.info("🧾 Selecting team names from uploaded images.");
		        fc.selectTeamName1();
		        fc.selectTeamName2();
		        Thread.sleep(500);

		        test.info("💾 Clicking 'Save Team' to confirm selection.");
		        fc.clickOnSaveTeam();
		        Thread.sleep(1000);

		        // ========================== Add-ons ==========================
		        test.info("🎨 Selecting 'Unique Color' and 'Color Correction' add-ons.");
		        fc.clickOnuniqueColor();
		        fc.clickOncolorCorrection();
		        Thread.sleep(500);

		        // ========================== Terms & Payment ==========================
		        test.info("📜 Accepting Terms & Conditions.");
		        fc.clickOnterms();

		        test.info("💳 Entering card details for payment.");
		        Log.info("Entering manual card details for payment.");
		        fc.clickOnEnterCardInfo();
		        fc.enterCardNumber();
		        fc.selectMonth(test);
		        fc.selectYear(test);
		        fc.enterCVV();
		        fc.enterfirstName();
		        fc.enterLastname();

		        test.info("🛒 Clicking 'Checkout' to place the order.");
		        fc.clickOncheckOut();
		        Thread.sleep(1000);
		        test.pass("✅ Order placed successfully.");
		        Log.info("✅ Order placed successfully.");

		        // ========================== FTP Upload ==========================
		        test.info("📤 Uploading images to FTP 'input/photos' for job: " + GlobalData.job_Name);
		        boolean uploaded = FTPImageUpload.createFolderAndUpload(GlobalData.job_Name);
		        Assert.assertTrue(uploaded, "❌ FTP Upload failed.");
		        Thread.sleep(10000);

		        // ========================== API 2 Confirmation ==========================
		        test.info("🔁 Triggering API 2 to transfer images from input → cam1.");
		        String finalURL = GlobalData.checkoutUrl;
		        String jobId = null;

		        if (finalURL != null && finalURL.contains("jobno=")) {
		            try {
		                String[] parts = finalURL.split("jobno=");
		                jobId = parts[1].split("&")[0];
		                test.info("🔍 Extracted Job ID: " + jobId);
		            } catch (Exception e) {
		                test.fail("❌ Failed to parse Job ID from URL: " + e.getMessage());
		            }
		        } else {
		            test.fail("❌ Job ID not found in the checkout URL.");
		        }

		        if (jobId != null) {
		            ImageTransferUtil.confirmImageTransfer(jobId);
		            test.pass("✅ API 2 (Confirm Image Transfer) called successfully for Job ID: " + jobId);
		        } else {
		            test.fail("❌ API 2 skipped: Job ID is null.");
		        }

		    } catch (Exception e) {
		        String screenshotPath = ScreenshotUtil.captureScreenshot(driver, "TC_Failure");
		        test.fail("❌ Test failed with exception: " + e.getMessage(),
		            MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
		        Log.error("❌ Exception occurred: " + e.getMessage());
		        throw e;
		    }

		    Log.endTestCase("Traditional Job with Ignore Team Images");}
	
	
	
	
	
	
	@Test
	public void check_Traditional_Job_With_MoveToInd_100OFF() throws Throwable
	{
		test = extent.createTest("Traditional Job → Move to Individual Images → MM Template → Order with Discount");
	    Log.startTestCase("Traditional Job with Move to Individual Images");

	    fc = new FullCompositePage(driver);

	    try {
	        // ========================== Job Selection ==========================
	        test.info("📌 Selecting job type: 'Traditional'");
	        fc.clickOnTraditionalOption();
	        Thread.sleep(2000);

	        test.info("📌 Selecting option: 'Move to Individual Images'");
	        fc.ClickonSTD();
	        fc.clickOntraditionalMoveTeamImage();
	        Thread.sleep(2000);

	        // ========================== Template Selection ==========================
	        test.info("🎨 Selecting MM Templates: A1 MM and A2 45TV");
	        fc.ClickonA1_MM();
	        fc.ClickonA2_45TV();
	        Thread.sleep(1000);

	        test.info("📐 Selecting Single Template for MM");
	        fc.ClickonSingleTemp();
	        Thread.sleep(1000);
	        fc.ClickOnSingleTempforTraditional(test);

	        // ========================== Upload Team Images ==========================
	        test.info("📁 Uploading 2 team images");
	        Log.info("Uploading team images...");
	        String[] filepaths = {
	            "C:\\Users\\Tiu User (Rakhi)\\Desktop\\TEAM Name Auto Selection\\New folder\\KANSAS CITY 7.jpg",
	            "C:\\Users\\Tiu User (Rakhi)\\Desktop\\TEAM Name Auto Selection\\New folder\\YANKEES.jpg"
	        };
	        Thread.sleep(2000);
	        fc.clickOnUploadTeamsPhotos(filepaths);
	        Thread.sleep(1000);

	        test.info("📤 Clicking 'Upload' button for team images");
	        fc.clickOnuploadTeamPhotoBtn();
	        Thread.sleep(10000);

	        test.info("📌 Selecting uploaded team names");
	        fc.selectTeamName1();
	        fc.selectTeamName2();
	        Thread.sleep(500);

	        test.info("💾 Clicking 'Save Team' button");
	        fc.clickOnSaveTeam();
	        Thread.sleep(1000);

	        // ========================== Add-ons ==========================
	        test.info("🎨 Selecting Add-ons: 'Unique Color' and 'Color Correction'");
	        fc.clickOnuniqueColor();
	        fc.clickOncolorCorrection();

	        // ========================== Discount Code ==========================
	        test.info("💸 Applying Discount Code");
	        fc.clickOndicount();
	        Thread.sleep(500);
	        fc.clickOnredeem();
	        Thread.sleep(500);

	        // ========================== Checkout ==========================
	        test.info("📜 Agreeing to Terms & Conditions");
	        fc.clickOnterms();
	        Thread.sleep(1000);

	        test.info("🛒 Proceeding to Checkout");
	        fc.clickOncheckOut();

	        test.pass("✅ Order placed successfully");
	        Log.info("🎉 Order placed and checkout completed");

	        // ========================== FTP Upload ==========================
	        test.info("📁 Uploading images to FTP: 'input/photos' for job: " + GlobalData.job_Name);
	        boolean uploaded = FTPImageUpload.createFolderAndUpload(GlobalData.job_Name);
	        Assert.assertTrue(uploaded, "❌ FTP Upload failed.");
	        Thread.sleep(10000);

	        // ========================== API 2 Confirmation ==========================
	        test.info("🔁 Triggering API 2 to transfer images from input → cam1");
	        String finalURL = GlobalData.checkoutUrl;
	        String jobId = null;

	        if (finalURL != null && finalURL.contains("jobno=")) {
	            try {
	                String[] parts = finalURL.split("jobno=");
	                jobId = parts[1].split("&")[0];
	                test.info("🔍 Extracted Job ID: " + jobId);
	            } catch (Exception e) {
	                test.fail("❌ Failed to parse Job ID from URL: " + e.getMessage());
	            }
	        } else {
	            test.fail("❌ Job ID not found in checkout URL.");
	        }

	        if (jobId != null) {
	            ImageTransferUtil.confirmImageTransfer(jobId);
	            test.pass("✅ API 2 triggered successfully for Job ID: " + jobId);
	        } else {
	            test.fail("❌ API 2 skipped: Job ID is null.");
	        }

	        Log.endTestCase("Traditional Job with Move to Individual Images");

	    } catch (Exception e) {
	        String screenshotPath = ScreenshotUtil.captureScreenshot(driver, "TC_Failure");
	        test.fail("❌ Test failed with exception: " + e.getMessage(),
	                MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
	        Log.error("❌ Exception during execution: " + e.getMessage());
	        throw e;
	    }
	}
	
	
	@Test
	public void check_Traditional_Job_Ignore_With_EnterCard() throws Throwable
	{
		test = extent.createTest("Traditional Job → Ignore Individual Images → MM Template → Save Card Payment → FTP & API Flow");
		Log.startTestCase("Traditional Job with Ignore Individual Images");

		fc = new FullCompositePage(driver);

		try {
		    // ========================== Job Type Selection ==========================
		    test.info("📌 Selecting job type: <b>Traditional</b>");
		    fc.clickOnTraditionalOption();
		    Thread.sleep(2000);

		    test.info("📌 Selecting option: <b>Ignore Team Images</b>");
		    fc.clickOntraditionalignoreTeamImage();
		    Thread.sleep(2000);

		    // ========================== Template Selection ==========================
		    test.info("🖼️ Selecting MM template: <b>A1-MM</b>");
		    fc.ClickonA1_MM();
		    Thread.sleep(1000);

		    test.info("🖼️ Selecting MM layout: <b>Single Template</b>");
		    fc.ClickonSingleTemp();
		    Thread.sleep(1000);
		    fc.ClickOnSingleTempforTraditional(test);

		    
		 // ========================== Upload Team Images ==========================
	        test.info("📁 Uploading 2 team images");
	        Log.info("Uploading team images...");
	        String[] filepaths = {
	            "C:\\Users\\Tiu User (Rakhi)\\Desktop\\TEAM Name Auto Selection\\New folder\\KANSAS CITY 7.jpg",
	            "C:\\Users\\Tiu User (Rakhi)\\Desktop\\TEAM Name Auto Selection\\New folder\\YANKEES.jpg"
	        };
	        Thread.sleep(2000);
	        fc.clickOnUploadTeamsPhotos(filepaths);
	        Thread.sleep(1000);

	        test.info("📤 Clicking 'Upload' button for team images");
	        fc.clickOnuploadTeamPhotoBtn();
	        Thread.sleep(10000);

	        test.info("📌 Selecting uploaded team names");
	        fc.selectTeamName1();
	        fc.selectTeamName2();
	        Thread.sleep(500);

	        test.info("💾 Clicking 'Save Team' button");
	        fc.clickOnSaveTeam();
	        Thread.sleep(1000);

		    // ========================== Add-ons =====================================
		    test.info("🎨 Selecting Add-ons: <b>Unique Color</b> and <b>Color Correction</b>");
		    fc.clickOnuniqueColor();
		    fc.clickOncolorCorrection();

		    // ========================== Discount =====================================
		    test.info("💸 Applying <b>50% Discount Code</b>");
		    fc.clickOn50dicount();
		    fc.clickOnredeem();

		    // ========================== Payment ======================================
		    test.info("📜 Accepting <b>Terms and Conditions</b>");
		    fc.clickOnterms();

		    test.info("💳 Selecting <b>Save Card</b> payment method");
		    fc.clickOnuseSaveCard();
		    Thread.sleep(1000);

		    test.info("💳 Choosing saved card for payment");
		    fc.ClikOnCardNumber(test);

		    test.info("🛒 Clicking on <b>Checkout</b> to place the order");
		    fc.clickOncheckOut();

		    test.pass("✅ <b>Order placed successfully.</b>");
		    Log.info("🎉 Order placed successfully.");

		    // ========================== FTP Upload ==================================
		    test.info("📤 Uploading images to FTP folder: <b>input/photos</b> for job <b>" + GlobalData.job_Name + "</b>");
		    boolean uploaded = FTPImageUpload.createFolderAndUpload(GlobalData.job_Name);
		    Assert.assertTrue(uploaded, "❌ FTP Upload failed.");
		    Thread.sleep(10000);

		    // ========================== Confirm API Transfer =========================
		    test.info("🔁 Triggering <b>API 2</b> to transfer images from <b>input → cam1</b>");
		    String finalURL = GlobalData.checkoutUrl;
		    String jobId = null;

		    if (finalURL != null && finalURL.contains("jobno=")) {
		        try {
		            String[] parts = finalURL.split("jobno=");
		            jobId = parts[1].split("&")[0];
		            test.info("🔍 Extracted <b>Job ID</b>: " + jobId);
		        } catch (Exception e) {
		            test.fail("❌ Failed to extract Job ID: " + e.getMessage());
		        }
		    } else {
		        test.fail("❌ <b>Job ID not found</b> in checkout URL.");
		    }

		    if (jobId != null) {
		        ImageTransferUtil.confirmImageTransfer(jobId);
		        test.pass("✅ <b>API 2</b> (Image Transfer Confirmation) successful for Job ID: " + jobId);
		    } else {
		        test.fail("❌ Skipped API 2: <b>Job ID is null</b>");
		    }

		    Log.endTestCase("Traditional Job with Ignore Individual Images");

		} catch (Exception e) {
		    String screenshotPath = ScreenshotUtil.captureScreenshot(driver, "TC_Failure");
		    test.fail("❌ <b>Test failed</b> with exception: " + e.getMessage(),
		        MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
		    Log.error("❌ Exception during test: " + e.getMessage());
		    throw e;}
	}
	
	
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
		extent.flush();
	}
	
}
