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




//=============================Verify default state of background template==========================

public class TC_014 extends BaseClass{
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
	
	
	@Test
	public void Verify_default_state_of_background_temp() {
		test=extent.createTest("Verify default state of background template");
		Log.startTestCase("Verify default state of background template");
		
		fc=new FullCompositePage(driver);
		
		fc.ClickonSingleTemp();
		fc.check_STemp_selectedOrNot(test);
	}
	
	
	
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
		extent.flush();
	}

}
