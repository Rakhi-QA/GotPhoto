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

//==========================Full composite MM option should be disabled if “No Team Images” is selected=============
public class TC_007 extends BaseClass
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
	
	public void vrifyFC_noTeam_MM_EnableOrDisable() {
		
		test=extent.createTest("Verify Full composite -MM option should be disabled or not if “No Team Images” is selected ");
		Log.startTestCase("Verify Full composite -MM option should be disabled or not if “No Team Images” is selected");
		
		fc=new FullCompositePage(driver);
		
		fc.verifyFCisSelected(test);
		
		test.info("Photographer select FullComposite >> No team option.");
		fc.ClickonNoTeam();
		fc.clickonignoreTeam();
		fc.check_MM_VisibleorEnabled(test);
		
		test.info("Verify that MM option is enable or disable");
		fc.check_A1_MM_EnableOrDisable(test);
		fc.check_A2_MM_EnableOrDisable(test);
		fc.check_A3_MM_EnableOrDisable(test);
		fc.check_A4_MM_EnableOrDisable(test);
		fc.check_A5_MM_EnableOrDisable(test);
		
		
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
		extent.flush();
	}
}
