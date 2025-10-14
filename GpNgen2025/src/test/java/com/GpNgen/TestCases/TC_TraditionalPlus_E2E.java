package com.GpNgen.TestCases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.GpNgen.Base.BaseClass;
import com.GpNgen.PageObject.FullCompositePage;
import com.GpNgen.utility.Log;
import com.aventstack.extentreports.ExtentTest;

public class TC_TraditionalPlus_E2E extends BaseClass 
{
	FullCompositePage fc;
	@BeforeMethod
	public void setup()
	{
		launchApp();
		generateCheckoutUrl();
	}
	
	@Test
	public void TC_traditionalPlus_Std_E2E(ExtentTest test) throws Throwable
	{
		//Log.startTestCase("FullComposite ");
		Log.info("User Select Traditional Plus .");
		fc=new FullCompositePage(driver);
		fc.clickOnTraditionalPlusoption();
		Thread.sleep(1000);
		
		Log.info("User Select Ignore team images  .");
		fc.clickOntraditionalignoreTeamImage();
		
		Thread.sleep(2000);
		//Log.info("User Select MM.");
		//fc.ClickonA1_45V();
		//fc.ClickonA1_MM();
		Thread.sleep(1000);
		Log.info("User Select Single template.");
		fc.ClickonSingleTemp();
		fc.ClikOnTempDropDown(test);
		Thread.sleep(1000);
		
		String[] path= {"C:\\Users\\Tiu User (Rakhi)\\Desktop\\logo\\wp4012099.jpg"};
		fc.clickOnuploadReferenceFile(path);
		Thread.sleep(1000);
		//fc.ClickOnSingleTempforTraditional();
		
		Log.info("User Upload 2 team files ");
		String[] Filepaths= {"C:\\Users\\Tiu User (Rakhi)\\Desktop\\TEAM Name Auto Selection\\New folder\\KANSAS CITY 7.jpg",
		        "C:\\Users\\Tiu User (Rakhi)\\Desktop\\TEAM Name Auto Selection\\New folder\\YANKEES.jpg"};
		
		Thread.sleep(1000);
		fc.clickOnUploadTeamsPhotos(Filepaths);
		Thread.sleep(1000);
		
		Log.info("User Team upload button ");
		fc.clickOnuploadTeamPhotoBtn();
		Thread.sleep(10000);
		Log.info("User Select teams ");
		fc.selectTeamName1();
		fc.selectTeamName2();
		Thread.sleep(500);
		Log.info("User click on save button");
		fc.clickOnSaveTeam();
		Thread.sleep(1000);
		Log.info("User select Unique color ");
		fc.clickOnuniqueColor();
		Log.info("User select Coloe Correction ");
		fc.clickOncolorCorrection();
		Log.info("User select Discount code  ");
		fc.clickOndicount();
		fc.clickOnredeem();
		Log.info("User select Terms condition  ");
		fc.clickOnterms();
		Log.info("==============Order placed ============  ");
		fc.clickOncheckOut();
		
        
	}
	
	
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
}
