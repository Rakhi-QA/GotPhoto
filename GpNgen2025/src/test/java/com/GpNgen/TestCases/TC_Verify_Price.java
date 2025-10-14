package com.GpNgen.TestCases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.GpNgen.Base.BaseClass;
import com.GpNgen.PageObject.FullCompositePage;
import com.GpNgen.utility.ExtentManager;
import com.GpNgen.utility.Log;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class TC_Verify_Price extends BaseClass
{
	FullCompositePage fc;
	ExtentReports extent;
	ExtentTest test;
	@BeforeMethod
	public void setup()
	{
		launchApp();
		generateCheckoutUrl();
	}
	
	@BeforeSuite
    public void setUpReport() {
        extent = ExtentManager.getInstance();
    }
	@Test(enabled = false)
	public void TC_verify_Price_For_NT() throws InterruptedException 
	{
		
		
		
		fc=new FullCompositePage(driver);
		
		fc.ClickonNoTeam();
		Thread.sleep(5000);
		fc.clickonignoreTeam();
		Thread.sleep(1000);
		Log.info("STD price -");
		fc.checkNoteamImagePrice();
	}
	
	
	
	@Test
	public void TC_verify_Price_For_Crop() throws InterruptedException 
	{
		
		
		test=extent.createTest("Verify Price calculation for “3/4 Crop” or “Full Length Centering”");
		Log.startTestCase("Verify Price calculation for “3/4 Crop” or “Full Length Centering”");
		
		fc=new FullCompositePage(driver);
		Thread.sleep(100);
		test.info("Photographer click on Extract Imgaes.");
		fc.clickOnextractImage();
		test.info("Photographer Select 3/4 crop option.");
		fc.clickOnpngCrop(test);
		
		Log.info("3/4 Crop price -");
		test.info("3/4 Crop price ");
		fc.printCropPrice(test);
	}
	
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
	
	
}
