package com.AdminTestCases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.GpNgen.ActionDriver.Action;
import com.GpNgen.Base.BaseClass;
import com.GpNgen.PageObject.AdminPage;
import com.GpNgen.utility.ExtentManager;
import com.GpNgen.utility.Log;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class TC_Admin_MainMenu extends BaseClass
{
	AdminPage admin;
	ExtentReports extent;
	ExtentTest test;


	@BeforeMethod
	public void setup()
	{
		launchApp();
		openAdminPage();
	}

	@BeforeSuite
	public void setUpReport() {
		extent = ExtentManager.getInstance();
	}



	@Test(enabled = false)

	public void TC_UI_001() throws InterruptedException {

		test=extent.createTest("Admin selecte Setting option.");
		Log.startTestCase("Admin selecte Setting option.");

		admin=new AdminPage(driver);
		Thread.sleep(2000);
		admin.clickOnbody();
		admin.clickOnsetting();
		Thread.sleep(1000);
		admin.clickOnBackgroudTemp();


	}

	//===============Add New Background Templpate============================================

	@Test(enabled = false)

	public void TC_BT_001() throws InterruptedException {

		test=extent.createTest("Admin Add Background Template.");
		Log.startTestCase("Admin Add Background Template.");

		admin=new AdminPage(driver);
		Thread.sleep(1000);
		admin.clickOnbody();
		//Thread.sleep(1000);
		test.info("User Select Setting Tab ");
		admin.clickOnsetting();
		Thread.sleep(1000);
		test.info("User Select Background Template");
		admin.clickOnBackgroudTemp();
		Thread.sleep(1000);
		test.info("User Crete Or Add new Background Template");
		admin.clickOnAdd();
		Thread.sleep(1000);
		test.info("User enter Background Template");
		admin.enterBGtempName();
		//Thread.sleep(2000);
		//test.info("User Select Apply to all option");
		//admin.clickOnapplyToAllCheckBox();
		test.info("User Select Full Lenght option");
		admin.clickOnFullLengthTemp();
		Thread.sleep(500);
		test.info("User Click on  Save button");
		admin.clickOnSave();
		test.info("New Background Template is created");
		test.pass("Background Template is added Successfully ");

	}

	//===============EDIT New Background Templpate============================================

	@Test(enabled = false)

	public void TC_BT_002() throws InterruptedException {

		test=extent.createTest("Admin EDIT Background Template option.");
		Log.startTestCase("Admin EDIT Background Template option.");

		admin=new AdminPage(driver);
		String bg = prop.getProperty("bgTemp");
		Thread.sleep(1000);
		admin.clickOnbody();
		//Thread.sleep(1000);
		test.info("User Select Setting Tab ");
		admin.clickOnsetting();
		Thread.sleep(1000);
		test.info("User Select Background Template");
		admin.clickOnBackgroudTemp();
		Thread.sleep(1000);
		admin.bgTempSerchBox(bg);
		Thread.sleep(1000);
		test.info("User EDIT new Background Template");
		admin.clickOnEdit();
		Thread.sleep(1000);
		test.info("User select Full Length Template.");
		admin.clickOnFullLengthTemp();
		Thread.sleep(1000);
		test.info("User Click on  Save button");
		admin.clickOnSave();
		test.info(" Background Template is Updated");
		test.pass("Background Template is Updated Successfully ");

	}

	//===============Delete New Background Templpate============================================

	@Test(enabled = false)

	public void TC_BT_003() throws InterruptedException {

		test=extent.createTest("Admin Delete Background Template .");
		Log.startTestCase("Admin Delete Background Template option.");

		admin=new AdminPage(driver);
		String bg = prop.getProperty("bgTemp");
		Thread.sleep(1000);
		admin.clickOnbody();
		//Thread.sleep(1000);
		test.info("User Select Setting Tab ");
		admin.clickOnsetting();
		Thread.sleep(1000);
		test.info("User Select Background Template");
		admin.clickOnBackgroudTemp();
		Thread.sleep(2000);
		admin.bgTempSerchBox(bg);
		Thread.sleep(2000);
		admin.clickOnDelete();
		Thread.sleep(1000);
		String actualMessage = admin.BgDeleteMessage();
		String expectedMessage = "Background Template deleted";

		Assert.assertEquals(actualMessage, expectedMessage, "Delete success message did not match!");

		test.info("User Delete new Background Template");

		Thread.sleep(1000);


		if (actualMessage.equals(expectedMessage)) {
			test.pass("Success message displayed correctly after delete");
		} else {
			test.fail("Expected: " + expectedMessage + ", but got: " + actualMessage);
		}

		test.info(" Background Template is Deleted");
		test.pass("Background Template is Updated Successfully ");

	}




	//===============Apply to all button Background Template============================================

	@Test(enabled = false)

	public void TC_BT_004() throws InterruptedException {

		test=extent.createTest("Admin click on Apply to All img link.");
		Log.startTestCase("Admin click on Apply to All img link.");

		admin=new AdminPage(driver);
		String bg = prop.getProperty("bgTemp");
		Thread.sleep(1000);
		admin.clickOnbody();
		//Thread.sleep(1000);
		test.info("User Select Setting Tab ");
		admin.clickOnsetting();
		Thread.sleep(1000);
		test.info("User Select Background Template");
		admin.clickOnBackgroudTemp();
		Thread.sleep(2000);
		admin.bgTempSerchBox(bg);
		Thread.sleep(2000);
		admin.clickOnapplyToAllImage();
		Thread.sleep(1000);
		String actualMessage = admin.BgTempForAll();
		String expectedMessage = "Background Template has been added to all photographers";

		Assert.assertEquals(actualMessage, expectedMessage, "Apply to all success message is match!");

		test.info("Background Template Has be add to all ");

		Thread.sleep(1000);


		if (actualMessage.equals(expectedMessage)) {
			test.pass("Success message displayed correctly after Click on Apply to all");
		} else {
			test.fail("Expected: " + expectedMessage + ", but got: " + actualMessage);
		}

		test.info(" Admin click on Apply to All img link");
		test.pass("Admin click on Apply to All img link");

	}
	//===============PNG File Background Template============================================
	
	@Test

	public void TC_BT_005() throws InterruptedException {

		test=extent.createTest("Admin Added PNG option for background template.");
		Log.startTestCase("Admin Added PNG option for background template.");

		admin=new AdminPage(driver);
		String bg = prop.getProperty("bgTemp");
		Thread.sleep(1000);
		admin.clickOnbody();
		//Thread.sleep(1000);
		test.info("User Select Setting Tab ");
		admin.clickOnsetting();
		Thread.sleep(1000);
		test.info("User Select Background Template");
		admin.clickOnBackgroudTemp();
		Thread.sleep(2000);
		admin.bgTempSerchBox(bg);
		Thread.sleep(2000);
		admin.clickOnpngFile();
		Thread.sleep(1000);
		String actualMessage = admin.validationAddPNGoption();
		String expectedMessage = "Added PNG option for background template";

		Assert.assertEquals(actualMessage, expectedMessage, "PNG   message ");

		test.info("Added PNG option for background template");

		Thread.sleep(1000);


		if (actualMessage.equals(expectedMessage)) {
			test.pass("Success message displayed correctly after Click on PNG file");
		} else {
			test.fail("Expected: " + expectedMessage + ", but got: " + actualMessage);
		}

		test.info(" Added PNG option for background template");
		test.pass("Added PNG option for background template");

	}
	

	@AfterMethod
	public void tearDown()
	{
		driver.quit();
		extent.flush();
	}
}
