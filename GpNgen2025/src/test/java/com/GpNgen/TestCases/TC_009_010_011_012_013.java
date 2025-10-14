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

public class TC_009_010_011_012_013 extends BaseClass{
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
	public void Verify_dropdown_appears_for_Single_Bg() {
		test=extent.createTest("Verify dropdown appears for “Single Template” option ");
		Log.startTestCase("Verify dropdown appears for “Single Template” option");
		
		fc=new FullCompositePage(driver);
		
		fc.ClickonSingleTemp();
		//fc.ClikOnTempDropDown(test);
		
		test.info("Verify that DropDown option is visible on click of  Single Template for Organization.");
		fc.dropDownOptionIsVisibleOrNot(test);
	}
	
	
	
	@Test(enabled = false)
	public void Verify_PopUp_Appers_For_Diff_Tem() {
		test=extent.createTest("Verify popup appears for “Different Templates” option");
		Log.startTestCase("Verify popup appears for “Different Templates” option");
		
		fc=new FullCompositePage(driver);
		
		fc.clcikonDiffTem();
		//fc.ClikOnTempDropDown(test);
		
		test.info("Verify that Popup box is visible on click of different Template.");
		fc.clickPopUpwindow(test);
	}
	
	
	@Test
	public void Verify_Cancle_BTN_For_DiffTemp() throws InterruptedException {
		test=extent.createTest("Verify “Cancel” button behavior in popup");
		Log.startTestCase("Verify “Cancel” button behavior in popup");
		
		fc=new FullCompositePage(driver);
		
		fc.clcikonDiffTem();
		//fc.ClikOnTempDropDown(test);
		Thread.sleep(100);
		test.info("Verify “Cancel” button behavior in popup.");
		fc.clickOnCancleButton();
		
		test.info("PopUp box should be close .");
		Thread.sleep(100);
		
		test.info("All Background Template option should be unchecked.");
		fc.check_STemp_selectedOrNot(test);
		fc.check_DTemp_selectedOrNot(test);
		fc.check_customBG_selectedOrNot(test);
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
		extent.flush();
	}

}
