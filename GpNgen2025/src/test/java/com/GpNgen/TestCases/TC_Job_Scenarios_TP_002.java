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

//====================Traditional plus job- with team images>> MOve to team image  with 50% Discount=============================

public class TC_Job_Scenarios_TP_002 extends BaseClass{

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
	public void traditionalPlusJob_WithTMImage_MoveToTmImage() throws InterruptedException {
		
		
		test=extent.createTest("Traditional plus job- with team images>> MOve to team image  with 50% Discount");
		Log.startTestCase("Traditional plus job- with team images>> MOve to team image  with 50% Discount");
		fc=new FullCompositePage(driver);
		fc.clickOnTraditionalPlusoption();
		Thread.sleep(100);
		
		fc.clickOntraditionalMoveTeamImage();
		
	}
	
	
	
	
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
}
