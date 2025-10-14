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

public class TC_JobScenarios_FullComposite_CompJobProcess extends BaseClass
{
		//========================FULL COMPOSITE -ORDER placced by using this option (100 percent dicount,Save card,Enter Card details) =======================
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
		generateCheckoutUrl();
		
	}
	@Test(enabled = false)
	public void check_FC_STD_With_50Off_With_SaveCard() throws Throwable
	{
		test=extent.createTest("Full composite order --> Standard -->Order placed by using 50% Discount ad Save Card option.");
		Log.startTestCase("FullComposite with Standard");
		test.info("User Select Full Composite .");
		fc=new FullCompositePage(driver);
		try {
		Log.info("User Select Standard");
		
		fc.ClickonSTD();
		
		test.info("User Select Alternet graphics ");
		fc.ClickonA1_45V();
		fc.ClickonA1_45TV();
		fc.ClickonA1_55();
		
		test.info("=======================User Select Single template ===============================");
		fc.ClickonSingleTemp();
		fc.ClikOnTempDropDown(test);
		
		test.info("===========================User Select Extract image============================  ");
		fc.clickOnextractImage();
		fc.clickOnextractIndividualImage(test);
		fc.click_ON_3_4_Crop(test);
		fc.clickOnpngTeamAddOn(test);
		//fc.clickOnuniqueColor();
		fc.clickOnsingleColor();
		fc.clickOnthemeColor();
		fc.clickOncolorCorrection();
		//Log.info("==================User using discount code to place order======================== ");
		fc.clickOn50dicount();
		fc.clickOnredeem();
		
		fc.clickOnterms();
		Log.info("======================User Save Card ================================== ");
		fc.clickOnuseSaveCard();
		Thread.sleep(1000);
		fc.ClikOnCardNumber(test);
		fc.clickOncheckOut();	
		//Assert.assertTrue(result);	
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
	
	
	
	@Test
	public void check_FC_With_Virtual_50Off_With_EnterCard() throws Throwable
	{
		test=extent.createTest("Full composite order --> Virtual Raise -->Order placed by Enter Card Details.");
		Log.startTestCase("FullComposite ");
		test.info("User Select Full Composite .");
		fc=new FullCompositePage(driver);
		try {
		Log.info("User Select Standard");
		//fc.ClickonSTD();
		fc.ClickonVirtual();
		test.info("User Select Alternet graphics ");
		fc.ClickonA1_45V();
		fc.ClickonA1_45TV();
		fc.ClickonA1_55();
		
		test.info("=======================User Select Single template ===============================");
		fc.ClickonSingleTemp();
		fc.ClikOnTempDropDown(test);
		
		test.info("===========================User Select Extract image============================  ");
		fc.clickOnextractImage();
		fc.clickOnextractIndividualImage(test);
		fc.click_ON_3_4_Crop(test);
		fc.clickOnpngTeamAddOn(test);
		//fc.clickOnuniqueColor();
		fc.clickOnsingleColor();
		fc.clickOnthemeColor();
		fc.clickOncolorCorrection();
		//Log.info("==================User using discount code to place order======================== ");
		fc.clickOn50dicount();
		Thread.sleep(500);
		fc.clickOnredeem();
		Thread.sleep(500);
		fc.clickOnterms();
		Thread.sleep(1000);
		
		Log.info("======================Use ENTER Card ================================== ");
		fc.clickOnEnterCardInfo();
		fc.enterCardNumber();
		fc.selectMonth(test);
		fc.selectYear(test);
		fc.enterCVV();
		fc.enterfirstName();
		fc.enterLastname();
		fc.clickOncardType(test);
		//fc.validateAllCardFieldsOneByOne(test);
		
		Thread.sleep(500);
		fc.clickOncheckOut();	
		//Assert.assertTrue(result);	
		// ============================ FTP Upload ==========================================
		test.info("✅ Transferring images to FTP input/photos folde");
		 boolean uploaded = FTPImageUpload.createFolderAndUpload(GlobalData.job_Name);
		 Assert.assertTrue(uploaded, "❌ Image upload failed!");
		 Thread.sleep(60000);
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

	    } catch (Exception e) {
	        test.fail("❌ Test failed due to exception: " + e.getMessage());
	        e.printStackTrace();
	        Assert.fail("Test encountered an exception.");
	    }
	}
	
	
	@Test(enabled = false)
	public void check_FC_With_NO_Teaam_Ignore() throws Throwable
	{
		test=extent.createTest("Full composite order --> No team with ignore team image -->Order placed by Enter Card Details.");
		Log.startTestCase("FullComposite ");
		test.info("User Select Full Composite .");
		fc=new FullCompositePage(driver);
		try {
		Log.info("User Select Standard");
		//fc.ClickonSTD();
		fc.ClickonNoTeam();
		Thread.sleep(1000);
		fc.clickonignoreTeam();
		Thread.sleep(2000);
		test.info("User Select Alternet graphics ");
		fc.ClickonA1_45V();
		fc.ClickonA1_45TV();
		fc.ClickonA1_55();
		Thread.sleep(500);
		test.info("=======================User Select Single template ===============================");
		fc.ClickonSingleTemp();
		fc.ClikOnTempDropDown(test);
		
		test.info("===========================User Select Extract image============================  ");
		fc.clickOnextractImage();
		fc.clickOnextractIndividualImage(test);
		fc.click_ON_3_4_Crop(test);
		fc.clickOnpngTeamAddOn(test);
		//fc.clickOnuniqueColor();
		fc.clickOnsingleColor();
		fc.clickOnthemeColor();
		fc.clickOncolorCorrection();
		Thread.sleep(1000);
		//Log.info("==================User using discount code to place order======================== ");
		fc.clickOndicount();
		Thread.sleep(500);
		fc.clickOnredeem();
		Thread.sleep(500);
		fc.clickOnterms();
		Thread.sleep(1000);
		fc.clickOncheckOut();	
		//Assert.assertTrue(result);
		
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
		System.out.println("✅ Job Created: " + GlobalData.job_Name);
		}catch (Exception e) {
			String screenshotPath = ScreenshotUtil.captureScreenshot(driver, "TC_Failure");
		    test.fail("Test Failed. Exception: " + e.getMessage(),
		        MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
           
        }
	}
	
	
	@Test(enabled = false)
	public void check_FC_With_NO_Teaam_Move() throws Throwable
	{
		test=extent.createTest("Full composite order --> No team with ignore team image -->Order placed by Enter Card Details.");
		Log.startTestCase("FullComposite ");
		test.info("User Select Full Composite .");
		fc=new FullCompositePage(driver);
		try {
		Log.info("User Select Standard");
		//fc.ClickonSTD();
		fc.ClickonNoTeam();
		Thread.sleep(1000);
		fc.clickonmoveTeamImage();
		Thread.sleep(2000);
		test.info("User Select Alternet graphics ");
		fc.ClickonA1_45V();
		fc.ClickonA1_45TV();
		fc.ClickonA1_55();
		Thread.sleep(500);
		test.info("=======================User Select Single template ===============================");
		fc.ClickonSingleTemp();
		fc.ClikOnTempDropDown(test);
		
		test.info("===========================User Select Extract image============================  ");
		fc.clickOnextractImage();
		fc.clickOnextractIndividualImage(test);
		fc.click_ON_3_4_Crop(test);
		fc.clickOnpngTeamAddOn(test);
		//fc.clickOnuniqueColor();
		fc.clickOnsingleColor();
		fc.clickOnthemeColor();
		fc.clickOncolorCorrection();
		Thread.sleep(1000);
		//Log.info("==================User using discount code to place order======================== ");
		fc.clickOndicount();
		Thread.sleep(500);
		fc.clickOnredeem();
		Thread.sleep(500);
		
		fc.clickOnterms();
		Thread.sleep(1000);
		fc.clickOncheckOut();	
		//Assert.assertTrue(result);	
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
