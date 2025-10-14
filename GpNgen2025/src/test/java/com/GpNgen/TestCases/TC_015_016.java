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

public class TC_015_016 extends BaseClass{
	
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
	
	
	//=================Verify “Extracted Images” checkbox is clickable--TC015---===============================================
	
	@Test(enabled = false)
	public void extractImgClickableOrNot_TC015() {
		
		test=extent.createTest("Verify “Extracted Images” checkbox is clickable or not ");
		Log.startTestCase("Verify “Extracted Images” checkbox is clickable or not");
		
		fc=new FullCompositePage(driver);
		test.info("Extrated Image option is clickable or not ");
		fc.check_ExtractedImage_isClickable(test);
	}

//=================Verify sub-options appear after selecting “Extracted Images”-TC016---=====================================
	@Test(enabled = false)
	public void onClickextractImgSubOptionIsVisibleOrNot_TC016() {
		
		test=extent.createTest("Verify sub-options appear after selecting “Extracted Images”");
		Log.startTestCase("Verify sub-options appear after selecting “Extracted Images”");
		
		fc=new FullCompositePage(driver);
		test.info("Extrated Image option is Selected.");
		fc.clickOnextractImage();
		
		test.info("Verify that the sub-option becomes visible when the Extracted Image option is clicked..");
		fc.extractTeamoptionIsVisibleOrNot(test);
		fc.extractIndividualoptionIsVisibleOrNot(test);
		fc.extractBothoptionIsVisibleOrNot(test);
		
		test.info("Verify that Individual option is bydefault selected.");
		fc.extractIndividualoptionIsSelectedOrNot(test);
	}
	
	
	//=======================Show PNG Crop options after selecting Extracted Images--TC018----==========================
	@Test(enabled = false)
	public void onClickextractImgPNGCropIsVisibleOrNot_TC018() throws InterruptedException {
		
		test=extent.createTest("Verify PNG Crop options after selecting Extracted Images");
		Log.startTestCase("Verify PNG Crop options after selecting Extracted Images");
		
		fc=new FullCompositePage(driver);
		test.info("Extrated Image option is Selected.");
		fc.clickOnextractImage();
		
		test.info("Verify PNG Crop options is visible.");
		fc.check_PNGcrop_VisibleOrNot(test);
		
		Thread.sleep(500);
		test.info("Verify PNG Crop Sub options is visible.");
		fc.check_crop_EnableOrDisable(test);
		fc.check_fullLengthCenter_EnableOrDisable(test);
		fc.check_centered_EnableOrDisable(test);
		
	}
	//=======================Show Sub options after selecting Full Length Centering----==========================
	
	
	
	@Test(enabled = false)
	public void verifyFullLengthcenteringSubOption() throws InterruptedException {
		
		test=extent.createTest("Verify Sub options after selecting Full Length Centering");
		Log.startTestCase("Verify Sub options after selecting Full Length Centering");
		
		fc=new FullCompositePage(driver);
		test.info("Extrated Image option is Selected.");
		fc.clickOnextractImage();
		
		test.info("Verify PNG Crop options is visible.");
		fc.check_PNGcrop_VisibleOrNot(test);
		
		Thread.sleep(500);
		test.info("Verify PNG Crop Sub options is visible.");
		fc.check_centered_EnableOrDisable(test);
		fc.check_topWeighted_EnableOrDisable(test);
		fc.check_bottomWeighted_EnableOrDisable(test);
		
	}
	
	
	//=======================Require at least one selection in PNG Crop when sub-option selected_TC019---==========================
	
	
	@Test
	public void verifyValidationMessageTC019() throws InterruptedException {
		
		test=extent.createTest("Verify Sub options after selecting Full Length Centering");
		Log.startTestCase("Verify Sub options after selecting Full Length Centering");
		
		fc=new FullCompositePage(driver);
		test.info("Extrated Image option is Selected.");
		fc.clickOnextractImage();
		
		test.info("Verify PNG Crop options is visible.");
		fc.check_PNGcrop_VisibleOrNot(test);
		
		Thread.sleep(500);
		test.info("Verify PNG Crop Sub options is visible.");
		fc.check_centered_EnableOrDisable(test);
		fc.check_topWeighted_EnableOrDisable(test);
		fc.check_bottomWeighted_EnableOrDisable(test);
		
		
		Thread.sleep(200);
		fc.clickOnterms();
		fc.clickOncheckOut();
		Thread.sleep(200);
		test.info("Validation message should be display if  Photographer not selecte any PNG crop option");
		fc.check_ValidationMsg(test);
		
		
		
	}
	
	
	
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
		extent.flush();
	}

}
