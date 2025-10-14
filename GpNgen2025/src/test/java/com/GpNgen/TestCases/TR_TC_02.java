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

public class TR_TC_02 extends BaseClass
{
	FullCompositePage fc;
	ExtentReports extent;
	ExtentTest test;
	
	@BeforeMethod
	public void setup()
	{
		launchApp();
		generateCheckoutUrl3();
	}
	
	@BeforeSuite
    public void setUpReport() {
        extent = ExtentManager.getInstance();
    }
	
	
	@Test
	public void No_popup_without_TM_image() throws InterruptedException {
		
		
		test=extent.createTest("When the photographer selects Traditional and Traditional Plus options, no pop-up box appears if team images are present.");
		Log.startTestCase("Verify that when the photographer selects Traditional and Traditional Plus options, no pop-up box appears if team images are present.");
		
		fc=new FullCompositePage(driver);
		
		test.info("User selects the Traditional option, and no team images are present in the table.");
		fc.clickOnTraditionalOption();
		Thread.sleep(100);
		test.info("Which ALT option is visble when user select traditional job.");
		
		fc.check_45V_VisibleorEnabled(test);
	}
	
	
	
	
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
		extent.flush();
	}
	
	
}
