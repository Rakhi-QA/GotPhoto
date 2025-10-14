package com.GpNgen.TestCases;

import org.openqa.selenium.support.ui.Sleeper;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.GpNgen.Base.BaseClass;
import com.GpNgen.PageObject.FullCompositePage;
import com.GpNgen.utility.Log;

public class TC_Traditional_Move_to_Individual extends BaseClass
{
	FullCompositePage fc;
	@BeforeMethod
	public void setup()
	{
		launchApp();
		generateCheckoutUrl();
	}
	
	//==========================TRADITIONAL JOB >> MOVE TEAM IMAG >> DIFFERENT TEMPLATE==============================================
	
	@Test
	public void traditionalMoveToInd() throws Throwable {
		fc=new FullCompositePage(driver);
		fc.clickOnTraditionalOption();
		Thread.sleep(1000);
		fc.clickOntraditionalMoveTeamImage();
		Thread.sleep(1000);
		fc.ClickonA1_45V();
		fc.ClickonA2_MM();
		fc.clcikonDiffTem();
		Thread.sleep(100);
		fc.selectDiffTempForTeam1(null);
		fc.selectDiffTempForTeam2(null);
		fc.clickOnSaveButton();
		Thread.sleep(1000);
		String[] Filepaths= {"C:\\Users\\Tiu User (Rakhi)\\Desktop\\TEAM Name Auto Selection\\New folder\\KANSAS CITY 7.jpg",
        "C:\\Users\\Tiu User (Rakhi)\\Desktop\\TEAM Name Auto Selection\\New folder\\YANKEES.jpg"};

Thread.sleep(2000);
		fc.clickOnUploadTeamsPhotos(Filepaths);
		
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
