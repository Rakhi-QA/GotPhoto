package com.GpNgen.TestCases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.GpNgen.Base.BaseClass;
import com.GpNgen.PageObject.FullCompositePage;
import com.GpNgen.utility.ExtentManager;
import com.GpNgen.utility.Log;
import com.GpNgen.utility.ScreenshotUtil;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.util.Assert;




public class TC_FC_001 extends BaseClass
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
		generateCheckoutUrl();
		
	}
	@Test
	public void verifyFC_std() throws Throwable
	{
		test=extent.createTest("verifyFC_std");
		Log.startTestCase("FullComposite ");
		test.info("User Select Full Composite .");
		fc=new FullCompositePage(driver);
		try {
		Log.info("User Select Standard");
		fc.ClickonSTD();
		
		test.info("=======================User Select Single template ===============================");
		fc.ClickonSingleTemp();
		fc.ClikOnTempDropDown(test);
		Thread.sleep(500);
		test.info("===========================User Select Extract image============================  ");
		fc.clickOnextractImage();
		Thread.sleep(500);
		fc.clickOnextractIndividualImage(test);
		fc.click_ON_3_4_Crop(test);
		fc.clickOnpngTeamAddOn(test);
		//fc.clickOnuniqueColor();
		Thread.sleep(500);
		fc.clickOnsingleColor();
		fc.clickOnthemeColor();
		Thread.sleep(500);
		fc.clickOncolorCorrection();
		
		Thread.sleep(1000);
		fc.clickOnterms();
		Log.info("======================User Save Card ================================== ");
		fc.clickOnuseSaveCard();
		Thread.sleep(1000);
		fc.ClikOnCardNumber(test);
		Thread.sleep(500);
		fc.clickOncheckOut();	
		//Assert.assertTrue(result);	
		test.pass("Test case executed successfully.");
		}catch (Exception e) {
			String screenshotPath = ScreenshotUtil.captureScreenshot(driver, "TC_Failure");
		    test.fail("Test Failed. Exception: " + e.getMessage(),
		        MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
           
        }
	}

	@Test
	public void FC_vitual() throws Throwable
	{
		Log.startTestCase("FullComposite ");
		fc=new FullCompositePage(driver);
		Log.info("User Select Virtual Raiser .");
		fc.ClickonVirtual();
		Thread.sleep(500);
		
		fc.ClickonSingleTemp();
		fc.ClikOnTempDropDown(test);
		fc.clickOnextractImage();
		fc.clickOnextractIndividualImage(test);
		fc.click_ON_3_4_Crop(test);
		fc.clickOnpngTeamAddOn(test);
		fc.clickOnuniqueColor();
		//fc.clickOnsingleColor();
		//fc.clickOnthemeColor();
		fc.clickOncolorCorrection();
		//===========Discount Code===========================
		fc.clickOndicount();
		fc.clickOnredeem();
		Thread.sleep(500);
		fc.clickOnterms();
		Thread.sleep(500);
		fc.clickOncheckOut();
	}
	
	@Test
	public void TC_FC_NoTeam_Ignore_Team_Images_EndToEnd() throws Throwable
	{
		
		
		Log.startTestCase("FullComposite ");
		fc=new FullCompositePage(driver);
		Log.info("User Select No Team .");
		fc.ClickonNoTeam();
		Thread.sleep(1000);
		fc.clickonignoreTeam();
		Thread.sleep(2000);
		/*Log.info("User Select Graphics option .");
		fc.ClickonA1_45V();
		fc.ClickonA1_45TV();
		fc.ClickonA1_55();*/
		Log.info("User Select Single temp .");
		fc.ClickonSingleTemp();
		fc.ClikOnTempDropDown(test);
		Log.info("User Select Extract images .");
		fc.clickOnextractImage();
		fc.clickOnextractIndividualImage(test);
		fc.click_ON_3_4_Crop(test);
		fc.clickOnpngTeamAddOn(test);
		//fc.clickOnuniqueColor();
		fc.clickOnsingleColor();
		fc.clickOnthemeColor();
		Log.info("User Select Color Color Correction .");
		fc.clickOncolorCorrection();
		Log.info("User Select Discount code  .");
		Thread.sleep(1000);
		fc.clickOndicount();
		fc.clickOnredeem();
		Thread.sleep(500);
		fc.clickOnterms();
		Thread.sleep(1000);
		fc.clickOncheckOut();
		Thread.sleep(500);
	}
	
	@Test
	public void TC_FC_NoTeam_MOve_To_Individual_option_EndToEnd() throws Throwable
	{
		Log.startTestCase("FullComposite ");
		fc=new FullCompositePage(driver);
		Log.info("User Select No Team .");
		fc.ClickonNoTeam();
		Thread.sleep(1000);
		fc.clickonmoveTeamImage();
		Thread.sleep(2000);
		/*Log.info("User Select Graphics option .");
		fc.ClickonA1_45V();
		fc.ClickonA1_45TV();
		fc.ClickonA1_55();*/
		Log.info("User Select Single temp .");
		fc.ClickonSingleTemp();
		fc.ClikOnTempDropDown(test);
		Log.info("User Select Extract images .");
		fc.clickOnextractImage();
		fc.clickOnextractIndividualImage(test);
		fc.click_ON_3_4_Crop(test);
		fc.clickOnpngTeamAddOn(test);
		//fc.clickOnuniqueColor();
		fc.clickOnsingleColor();
		fc.clickOnthemeColor();
		Log.info("User Select Color Color Correction .");
		fc.clickOncolorCorrection();
		Log.info("User Select Discount code  .");
		Thread.sleep(1000);
		fc.clickOndicount();
		fc.clickOnredeem();
		Thread.sleep(500);
		fc.clickOnterms();
		Thread.sleep(1000);
		fc.clickOncheckOut();
		Thread.sleep(500);
	}
	
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
		extent.flush();
	}
	
	
}
