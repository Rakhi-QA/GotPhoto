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

public class TC_Upload_reference_files extends BaseClass
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
	public void select_Upload_reference_files() throws InterruptedException {
		
		fc=new FullCompositePage(driver);
		fc.clickOnTraditionalPlusoption();
		
		Log.info("===========================User select Upload reference files=================================== ");
		
		String[] referenceFilepaths= {"C:\\Users\\Tiu User (Rakhi)\\Desktop\\TEAM Name Auto Selection\\New folder\\referenceFilepaths.jpg"};
		fc.clickOnuploadReferenceFile(referenceFilepaths);
		
		
		Thread.sleep(100);
		Log.info("=========================User select Upload logo=============================================");
		String[] logoFilepaths= {"C:\\Users\\Tiu User (Rakhi)\\Desktop\\TEAM Name Auto Selection\\New folder\\logo.jpg"};
		fc.clickOnuploadReferenceFile(logoFilepaths);
		
	}
	
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
		extent.flush();
	}

}
