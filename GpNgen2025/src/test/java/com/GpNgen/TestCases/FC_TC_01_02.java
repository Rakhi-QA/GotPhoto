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



//======================No Team option  selected and no team image present=================================

public class FC_TC_01_02 extends BaseClass
{

	FullCompositePage fc;
	ExtentReports extent;
	ExtentTest test;
	
	
	@BeforeMethod
	public void setup()
	{
		launchApp();
		generateCheckoutUrl2();
	}
	
	@BeforeSuite
    public void setUpReport() {
        extent = ExtentManager.getInstance();
    }
	
	@Test(enabled = false)
	public void sel_NoTeamOption_without_upload_TeamImage() throws InterruptedException {
		
		test=extent.createTest("Verify that When no team image is present then No pop-up should appear, MM option should be disabled");
		Log.startTestCase("Verify that When no team image is present then No pop-up should appear, MM option should be disabled ");
		
		fc=new FullCompositePage(driver);
		
		test.info("User Select No team  option.");
		fc.ClickonNoTeam();
		Thread.sleep(500);
		
		test.info("All MM graphics is  disable .");
		
		test.info("Image 1_ MM graphics is  disable .");
		fc.check_A1_MM_EnableOrDisable(test);
		
		test.info("Image 2_ MM graphics is  disable .");
		fc.check_A2_MM_EnableOrDisable(test);
		
		test.info("Image 3_ MM graphics is  disable .");
		fc.check_A3_MM_EnableOrDisable(test);
		
		test.info("Image 4_ MM graphics is  disable .");
		fc.check_A4_MM_EnableOrDisable(test);
		
		test.info("Image 5_ MM graphics is  disable .");
		fc.check_A5_MM_EnableOrDisable(test);
	}
	
	
	
	@Test
	public void sel_STD_without_upload_TeamImage() throws InterruptedException {
		
		test=extent.createTest("Verify that When STD option is selected then  MM option should be Enable ");
		Log.startTestCase("Verify that When STD option is selected then  MM option should be Enable ");
		
		fc=new FullCompositePage(driver);
		
		test.info("User Select STD  option.");
		fc.ClickonSTD();
		Thread.sleep(500);
		
		test.info("All MM graphics is  Enable .");
		
		test.info("Image 1_ MM graphics is  Enable .");
		fc.check_A1_MM_EnableOrDisable(test);
		
		test.info("Image 2_ MM graphics is  Enable .");
		fc.check_A2_MM_EnableOrDisable(test);
		
		test.info("Image 3_ MM graphics is  Enable .");
		fc.check_A3_MM_EnableOrDisable(test);
		
		test.info("Image 4_ MM graphics is  Enable .");
		fc.check_A4_MM_EnableOrDisable(test);
		
		test.info("Image 5_ MM graphics is  Enable .");
		fc.check_A5_MM_EnableOrDisable(test);
	}
	
	
	
	@Test(enabled = false)
	public void sel_Virtual_without_upload_TeamImage() throws InterruptedException {
		
		test=extent.createTest("Verify that When Virtual option is selected then  MM option should be Enable ");
		Log.startTestCase("Verify that When Virtual option is selected then  MM option should be Enable ");
		
		fc=new FullCompositePage(driver);
		
		test.info("User Select Virtual  option.");
		fc.ClickonSTD();
		Thread.sleep(500);
		
		test.info("All MM graphics is  Enable .");
		
		test.info("Image 1_ MM graphics is  Enable .");
		fc.check_A1_MM_EnableOrDisable(test);
		
		test.info("Image 2_ MM graphics is  Enable .");
		fc.check_A2_MM_EnableOrDisable(test);
		
		test.info("Image 3_ MM graphics is  Enable .");
		fc.check_A3_MM_EnableOrDisable(test);
		
		test.info("Image 4_ MM graphics is  Enable .");
		fc.check_A4_MM_EnableOrDisable(test);
		
		test.info("Image 5_ MM graphics is  Enable .");
		fc.check_A5_MM_EnableOrDisable(test);
	}
	
	
	
	
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
		extent.flush();
	}
}
