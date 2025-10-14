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


//=================Select FC, make changes, then select Traditional job 
//							this message should be display Message: "This page is asking you to confirm
public class TR_TC_01 extends BaseClass
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
	
	
	@Test
	public void confirmation_popup_After_change_option() throws InterruptedException 
	{
		test=extent.createTest("Verify that after  Change the option  confirmation pop-up");
		Log.startTestCase("Verify that after change the option Message should be display: \"This page is asking you to confirm...\"");
		fc=new FullCompositePage(driver);
		
		test.info("User Select Standrd  option.");
		fc.ClickonSTD();
		Thread.sleep(1000);
		
		test.info("User Select Traditional  option.");
		fc.clickOnTraditionalOption();
		
		test.info("Popup message After change the option .");
		
		fc.confirmPopupMessageAfterChangeOption();
		String message=fc.confirmPopupMessageAfterChangeOption();
		test.info("Popup message: " + message);
		
		test.info("Photographer select cancle option.");
		fc.cancleButton();
		
		fc.verifyFullCompositeOptionIsSelected();
	}
	
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
		extent.flush();
	}
	
	

}
