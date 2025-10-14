package com.AdminTestCases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.GpNgen.Base.BaseClass;
import com.GpNgen.PageObject.AdminPage;
import com.GpNgen.PageObject.FullCompositePage;
import com.GpNgen.utility.ExtentManager;
import com.GpNgen.utility.Log;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class TC_Admin_Login_Page extends BaseClass
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


	//============Valid Data Login Admin Page================================== 
	@Test(enabled = false)

	public void adminLoginPage() throws InterruptedException {

		test=extent.createTest("Verify Login Page ");
		Log.startTestCase("Verify Login Page");

		admin=new AdminPage(driver);

		String username = prop.getProperty("adminid");
		String password = prop.getProperty("adminpwd");


		admin.enterEmailID(username);
		admin.enterPassword(password);
		admin.clickOnLogin();

		Thread.sleep(100);
	}

	
	//============Invalid Data Login Admin Page================================== 
		@Test

		public void adminLoginInvalid() throws InterruptedException {

			test=extent.createTest("Verify Login Page ");
			Log.startTestCase("Verify Login Page");

			admin=new AdminPage(driver);

			String username = prop.getProperty("adminid1");
			String password = prop.getProperty("adminpwd1");


			admin.enterEmailID(username);
			admin.enterPassword(password);
			admin.clickOnLogin();
			Thread.sleep(100);
			// Get the actual error message from UI
		    String actualMessage = admin.getLoginErrorMessage();
		    System.out.println(actualMessage);
		    String expectedMessage = "Your username or password is incorrect.";
		    // Assertion
		    Assert.assertEquals(actualMessage, expectedMessage, "Validation message not matched!");
			//Thread.sleep(100);
		}







	@AfterMethod
	public void tearDown()
	{
		driver.quit();
		extent.flush();
	}
}
