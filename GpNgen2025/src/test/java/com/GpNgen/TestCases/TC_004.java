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

public class TC_004 extends BaseClass
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

	public void verify_default_selected_Graphics_opt_PageLoad() {

		test=extent.createTest("Verify which Alternate graphics option is selected by default on page load ");
		Log.startTestCase("Verify which Alternate graphics option is selected by default on page load  =  TC_NEG_04 ");
		fc=new FullCompositePage(driver);

		fc.verifyFCisSelected(test);

		test.info("Team Options is selected by default on page load..");
		fc.verifyStadardOptionisSelected(test);
		fc.verifyVirtualRaiserOptionisSelected(test);
		fc.verifyNoTeamOptionisSelected(test);


		test.info("Alternate Pose Graphic option is selected by default on page load..");
		fc.check_A1_45V_isSelectedOrNot(test);
		fc.check_A1_45TV_isSelectedOrNot(test);
		fc.check_A1_MM_isSelectedOrNot(test);







	}




	@AfterMethod
	public void tearDown()
	{
		driver.quit();
		extent.flush();
	}

}
