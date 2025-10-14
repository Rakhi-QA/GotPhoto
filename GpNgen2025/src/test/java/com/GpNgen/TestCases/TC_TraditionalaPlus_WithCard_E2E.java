package com.GpNgen.TestCases;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.GpNgen.Base.BaseClass;
import com.GpNgen.PageObject.FullCompositePage;
import com.GpNgen.utility.ExtentManager;
import com.GpNgen.utility.Log;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class TC_TraditionalaPlus_WithCard_E2E extends BaseClass
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
	public void TC_traditionalPlus_With_EnterCard_E2E() throws Throwable
	{
		//Log.startTestCase("FullComposite ");
		Log.info("User Select Traditional Plus .");
		fc=new FullCompositePage(driver);
		fc.clickOnTraditionalPlusoption();
		Thread.sleep(1000);
		
		Log.info("User Select Ignore team images  .");
		fc.clickOntraditionalignoreTeamImage();
		
		//Log.info("User Select MM.");
		//fc.ClickonA1_45V();
		//fc.ClickonA1_MM();
		Thread.sleep(100);
		Log.info("User Select Single template.");
		fc.ClickonSingleTemp();
		fc.ClikOnTempDropDown(test);
		
		
		Thread.sleep(100);
		//fc.ClickOnSingleTempforTraditional();
		
		Log.info("========================Upload Team photos================================");
		test.info("Upload Team photos");
		fc.check_uploadTeamsPhoto_EnableOrDisable(test);
		
		String[] Filepaths= {"C:\\Users\\Tiu User (Rakhi)\\Desktop\\TEAM Name Auto Selection\\New folder\\KANSAS CITY 7.jpg",
        "C:\\Users\\Tiu User (Rakhi)\\Desktop\\TEAM Name Auto Selection\\New folder\\YANKEES.jpg"};
		
		fc.clickOnUploadTeamsPhotos(Filepaths);
		
		Log.info("=======================Click on Upload Team photos button================================");
		test.info("Click on Upload Team photos button.");
		fc.clickOnuploadTeamPhotoBtn();
		
		Thread.sleep(10000);
		Log.info("==========Photographer Select teams ======================");
		
		fc.selectTeamName1();
		fc.selectTeamName2();
		//fc.selectTeamName3();
		
		Thread.sleep(500);
		Log.info("=====================User click on save button===================");
		fc.clickOnSaveTeam();
		Thread.sleep(1000);
		
		
		Log.info("User select Coloe Correction ");
		fc.clickOncolorCorrection();
		Log.info("User select Discount code  ");
		fc.clickOndicount();
		fc.clickOnredeem();
		Log.info("User select Terms condition  ");
		fc.clickOnterms();
		
		
		/*Log.info("======================User Save Card ================================== ");
		fc.clickOnuseSaveCard();
		Thread.sleep(1000);
		fc.ClikOnCardNumber(test);
		
		
		/*Log.info("======================Use ENTER Card ================================== ");
		fc.clickOnEnterCardInfo();
		fc.enterCardNumber();
		fc.selectMonth();
		fc.selectYear();
		fc.enterCVV();
		fc.enterfirstName();
		fc.enterLastname();
		//fc.clickOncardType();*/
		fc.clickOncheckOut();	
		//Assert.assertTrue(result);	
		test.pass("Test case executed successfully.");
		
		
		Log.info("==============Order placed ============  ");
		//fc.clickOncheckOut();

}
}