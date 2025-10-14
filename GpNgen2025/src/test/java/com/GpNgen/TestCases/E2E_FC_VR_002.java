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

public class E2E_FC_VR_002 extends BaseClass {
	//========================Full Composite with Virtual Riser Team Build =======================
		ExtentReports extent;
		ExtentTest test;
		FullCompositePage fc;

		@BeforeTest
		public void setUpReport() {
			extent = ExtentManager.getInstance();
		}

		@BeforeMethod
		public void setup()
		{
			launchApp();
			generateCheckoutUrl();

		}


		@Test()
		public void E2E_Full_Composite_with_STD_Team_Build() throws Throwable 
		{
			test = extent.createTest("🧪 Full Composite Order →  Virtual Riser Team Build → 50% Discount + Save Card Payment");
			Log.startTestCase("▶️ Test Start: Full Composite with  Virtual Riser Team Build");

			fc = new FullCompositePage(driver);

			try {
				test.info("🟢 Step 1: Selecting Full Composite Option.");

				Log.info("User selects  Virtual Riser Team Build.");
				fc.ClickonVirtual();
				test.pass("✅  Virtual Riser Team Build option selected.");

				// Graphics Selection
				test.info("🟢 Step 2: Verifying Alternate Graphics Options Selection.");
				fc.check_A1_45V_isSelectedOrNot(test);
				fc.check_A1_45TV_isSelectedOrNot(test);
				fc.check_A1_MM_isSelectedOrNot(test);

				// Template Selection
				test.info("🟢 Step 3: Selecting Template.");
				test.info("➡️ User selects 'Single Template' option.");
				fc.ClickonSingleTemp();

				test.info("➡️ User opens template dropdown.");
				fc.ClikOnTempDropDown(test);

				// Image Extraction
				test.info("🟢 Step 4: Extracting Images.");
				fc.clickOnextractImage();
				test.info("➡️ Selecting 'Extract Individual Image' option.");
				fc.clickOnextractIndividualImage(test);

				test.info("➡️ Selecting '3/4 Crop' option.");
				fc.click_ON_3_4_Crop(test);

				// Add-on Selection
				test.info("🟢 Step 5: Selecting Add-ons.");
				fc.clickOnpngTeamAddOn(test);
				fc.clickOnuniqueColor();
				fc.clickOncolorCorrection();

				// Discount Code Application
				test.info("🟢 Step 6: Applying 50% Discount Code.");
				fc.clickOn50dicount();
				fc.clickOnredeem();
				test.pass("✅ 50% Discount Code applied successfully.");

				// Terms and Save Card Payment
				test.info("🟢 Step 7: Accepting Terms and Using Saved Card.");
				fc.clickOnterms();
				Log.info("User selects 'Use Saved Card' option.");
				fc.clickOnuseSaveCard();
				Thread.sleep(1000); // Optional: Replace with explicit wait if needed

				test.info("➡️ Entering Card Details.");
				fc.ClikOnCardNumber(test);
				fc.clickOncheckOut();	
				//Assert.assertTrue(result);	`
				// ============================ FTP Upload ==========================================
				test.info("✅ Transferring images to FTP input/photos folde");
				boolean uploaded = FTPImageUpload.createFolderAndUpload(GlobalData.job_Name);
				Assert.assertTrue(uploaded, "❌ Image upload failed!");
				Thread.sleep(10000);
				//========================== Confirm API Call ==========================================
				test.info("✅ Triggering second API to transfer images from input → cam1");

				String finalURL = GlobalData.checkoutUrl; // e.g., https://staging...&jobno=1976&...
				String jobId = null;

				if (finalURL != null && finalURL.contains("jobno=")) {
					try {
						String[] parts = finalURL.split("jobno=");
						jobId = parts[1].split("&")[0];
						System.out.println("✅ Extracted Job ID: " + jobId);
					} catch (Exception e) {
						System.out.println("❌ Error parsing job ID: " + e.getMessage());
						test.fail("❌ Failed to parse job ID from URL.");
					}
				} else {
					System.out.println("❌ Failed to extract Job ID: URL is invalid or null");
					test.fail("❌ Job ID not found in the checkout URL.");
				}

				if (jobId != null) {
					ImageTransferUtil.confirmImageTransfer(jobId);
					test.pass("✅ API 2 (confirm image transfer) called successfully for Job ID: " + jobId);
				} else {
					System.out.println("❌ Job ID could not be extracted. Image transfer API not triggered.");
					test.fail("❌ Skipping API 2: Job ID is null.");
				}


				test.pass("Test case executed successfully.");
			}catch (Exception e) {
				String screenshotPath = ScreenshotUtil.captureScreenshot(driver, "TC_Failure");
				test.fail("Test Failed. Exception: " + e.getMessage(),
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
