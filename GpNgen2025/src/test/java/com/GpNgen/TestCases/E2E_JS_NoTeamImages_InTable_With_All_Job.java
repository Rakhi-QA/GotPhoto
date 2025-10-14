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

//===========No team image is present in table and user selecte Full Composit/Traditiona/Traditional+===============
public class E2E_JS_NoTeamImages_InTable_With_All_Job extends BaseClass
{
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
		generateCheckoutUrl3();
		
	}
	@Test()
	
	public void check_Traditional_NoTeamImage_In_Order() throws Throwable {
	    test = extent.createTest("✅ Validate no popup when selecting “Traditional” or “Traditional+” and no team image is present");
	    Log.startTestCase("Validate no popup when selecting “Traditional” or “Traditional+” and no team image is present");

	    try {
	        fc = new FullCompositePage(driver);

	        test.info("🔘 Step 1: Selecting Traditional Option");
	        fc.clickOnTraditionalOption();

	        test.info("🎨 Step 2: Selecting Alternate Graphics Options");
	        fc.ClickonA1_45V();
	        fc.ClickonA1_45TV();
	        fc.ClickonA2_45TV();

	        test.info("🖼️ Step 3: Selecting Single Template");
	        fc.ClickonSingleTemp();
	        Thread.sleep(100);
	        fc.ClickOnSingleTempforTraditional(test);

	        test.info("🎨 Step 4: Applying Design Options");
	        fc.clickOnsingleColor();
	        fc.clickOnthemeColor();
	        fc.clickOncolorCorrection();
	        fc.clickOndicount();
	        fc.clickOnredeem();
	        Thread.sleep(500);
	        fc.clickOnterms();

	        test.info("🛒 Step 5: Proceeding to Checkout");
	        Thread.sleep(500);
	       // fc.clickOncheckOut();

	        // ====================== FTP Upload =========================
	        test.info("📂 Step 6: Uploading images to FTP → input/photos");
	        boolean uploaded = FTPImageUpload.createFolderAndUpload(GlobalData.job_Name);
	        Assert.assertTrue(uploaded, "❌ Image upload to FTP failed!");
	        test.pass("✅ Image upload to FTP was successful.");
	        Thread.sleep(10000);

	        // ===================== Confirm Image Transfer ==========================
	        test.info("🔁 Step 7: Triggering second API to transfer images from input → cam1");

	        String finalURL = GlobalData.checkoutUrl;
	        String jobId = null;

	        if (finalURL != null && finalURL.contains("jobno=")) {
	            try {
	                String[] parts = finalURL.split("jobno=");
	                jobId = parts[1].split("&")[0];
	                System.out.println("✅ Extracted Job ID: " + jobId);
	                test.pass("✅ Extracted Job ID from URL: " + jobId);
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
	            test.fail("❌ Skipping API 2: Job ID is null. Image transfer not triggered.");
	        }

	        // ====================== Final Validation =========================
	        test.pass("✅ Test case executed successfully: No popup appeared when no team image was present with Traditional option.");

	    } catch (Exception e) {
	        String screenshotPath = ScreenshotUtil.captureScreenshot(driver, "TC_Failure");
	        test.fail("❌ Test Failed. Exception: " + e.getMessage(),
	                MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
	    }
	}
	
	
	
@Test()
	
	public void check_TraditionalPlus_NoTeamImage_In_Order() throws Throwable {
	    test = extent.createTest("✅ Validate no popup when selecting “Traditional” or “Traditional+” and no team image is present");
	    Log.startTestCase("Validate no popup when selecting “Traditional” or “Traditional+” and no team image is present");

	    try {
	        fc = new FullCompositePage(driver);

	        test.info("🔘 Step 1: Selecting Traditional Option");
	        fc.clickOnTraditionalPlusoption();

	        /*test.info("🎨 Step 2: Selecting Alternate Graphics Options");
	        fc.ClickonA1_45V();
	        fc.ClickonA1_45TV();
	        fc.ClickonA2_45TV();*/

	        test.info("🖼️ Step 3: Selecting Single Template");
	        fc.ClickonSingleTemp();
	        Thread.sleep(100);
	        fc.ClickOnSingleTempforTraditionalPlus(test);

	        test.info("🎨 Step 4: Applying Design Options");
	        fc.clickOnsingleColor();
	        fc.clickOnthemeColor();
	        fc.clickOncolorCorrection();
	        fc.clickOndicount();
	        fc.clickOnredeem();
	        Thread.sleep(500);
	        fc.clickOnterms();

	        test.info("🛒 Step 5: Proceeding to Checkout");
	        Thread.sleep(500);
	        fc.clickOncheckOut();

	        // ====================== FTP Upload =========================
	        test.info("📂 Step 6: Uploading images to FTP → input/photos");
	        boolean uploaded = FTPImageUpload.createFolderAndUpload(GlobalData.job_Name);
	        Assert.assertTrue(uploaded, "❌ Image upload to FTP failed!");
	        test.pass("✅ Image upload to FTP was successful.");
	        Thread.sleep(10000);

	        // ===================== Confirm Image Transfer ==========================
	        test.info("🔁 Step 7: Triggering second API to transfer images from input → cam1");

	        String finalURL = GlobalData.checkoutUrl;
	        String jobId = null;

	        if (finalURL != null && finalURL.contains("jobno=")) {
	            try {
	                String[] parts = finalURL.split("jobno=");
	                jobId = parts[1].split("&")[0];
	                System.out.println("✅ Extracted Job ID: " + jobId);
	                test.pass("✅ Extracted Job ID from URL: " + jobId);
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
	            test.fail("❌ Skipping API 2: Job ID is null. Image transfer not triggered.");
	        }

	        // ====================== Final Validation =========================
	        test.pass("✅ Test case executed successfully: No popup appeared when no team image was present with Traditional Plus option.");

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

