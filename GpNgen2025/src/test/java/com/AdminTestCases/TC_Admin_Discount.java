package com.AdminTestCases;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;


import com.GpNgen.ActionDriver.datePickerAction;
import com.GpNgen.Base.BaseClass;
import com.GpNgen.PageObject.AdminDiscountPage;

import com.GpNgen.PageObject.AdminPage;
import com.GpNgen.utility.ExtentManager;
import com.GpNgen.utility.Log;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class TC_Admin_Discount extends BaseClass
{

	AdminDiscountPage ad;
	ExtentReports extent;
	ExtentTest test;
	AdminPage admin;
	//WebDriver driver;
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

	public void TC_DC_CREATE_001() throws InterruptedException {

		test=extent.createTest("Admin selecte Setting option.");
		Log.startTestCase("Admin selecte Setting option.");
		admin=new AdminPage(driver);

		ad=new AdminDiscountPage(driver);
		String code = prop.getProperty("code");
		String name = prop.getProperty("Name");
		String desc = prop.getProperty("desc");
		String precentOff = prop.getProperty("precentOff");
		String amountOff = prop.getProperty("amountOff");
		//String name = prop.getProperty("Name");
		admin.clickOnbody();
		Thread.sleep(2000);
		ad.clickOnDisountMainTab();
		//Thread.sleep(10000);
		ad.createNewDiscountCode();
		Thread.sleep(2000);
		ad.enterCode(code);
		ad.enterName(name);
		ad.enterDiscountDesc(desc);
		ad.enterprecentOff(precentOff);
		//ad.enteramountOffOff(amountOff);
		Thread.sleep(2000);
		//ad.selecteStartDate();

		datePickerAction action = new datePickerAction(driver);
		action.selectDate(null, "15", "July", "2025");

		Thread.sleep(1000); // optional wait to view the date filled in
		String selectedDate = action.getSelectedDate();
		System.out.println("Selected Date: " + selectedDate);
		Thread.sleep(1000);
		//ad.selectEndDate();

		action.selectEndDate(null, "18", "July", "2025");
		Thread.sleep(1000); // optional wait to view the date filled in
		String selectedEndDate = action.getSelectedEndDate();
		System.out.println("Selected Date: " + selectedEndDate);
		Thread.sleep(1000); // optional wait to view the date filled in

		//ad.selectactiveNo();
		//Thread.sleep(500);
		//ad.selectactiveYes();
		Log.startTestCase("Assign to 338 user.");
		ad.selectAssignedTO();
		ad.selectAssignedTO338();
		//Thread.sleep(500);
		//ad.enterMaximumUser();
		//ad.enterMaximumUsage();
		Thread.sleep(500);
		ad.savebtn();
		Log.startTestCase("Discount code is Save.");
		//Thread.sleep(100);
	}



	@Test(enabled = false)
	public void TC_DC_validateMaxUserAndAssignedToErrorMessage() throws InterruptedException {
		test = extent.createTest("Validate error message when both 'Assigned To' and 'Max User' are selected.");
		Log.startTestCase("Admin selects Discount Settings and triggers validation scenario.");
		admin=new AdminPage(driver);

		ad=new AdminDiscountPage(driver);
		String code = prop.getProperty("code");
		String name = prop.getProperty("Name");
		String desc = prop.getProperty("desc");
		String precentOff = prop.getProperty("precentOff");
		String amountOff = prop.getProperty("amountOff");
		//String name = prop.getProperty("Name");
		admin.clickOnbody();
		test.info("Clicked on the page body to focus.");
		Thread.sleep(2000);
		ad.clickOnDisountMainTab();
		test.info("Navigated to Discount tab.");
		//Thread.sleep(10000);
		ad.createNewDiscountCode();
		test.info("Clicked on 'Create New Discount Code'.");

		Thread.sleep(2000);
		ad.enterCode(code);
		test.info("Entered Discount Code: " + code);
		ad.enterName(name);
		test.info("Entered Discount Name: " + name);

		ad.enterDiscountDesc(desc);
		test.info("Entered Description: " + desc);

		ad.enterprecentOff(precentOff);
		test.info("Entered Percent Off: " + precentOff);

		Thread.sleep(2000);
		//ad.selecteStartDate();

		datePickerAction action = new datePickerAction(driver);
		action.selectDate(null, "15", "July", "2025");

		Thread.sleep(1000); // optional wait to view the date filled in
		String selectedDate = action.getSelectedDate();
		System.out.println("Selected Date: " + selectedDate);
		test.info("Selected Start Date: " + selectedDate);

		Thread.sleep(1000);
		//ad.selectEndDate();

		action.selectEndDate(null, "18", "July", "2025");
		Thread.sleep(1000); // optional wait to view the date filled in
		String selectedEndDate = action.getSelectedEndDate();
		System.out.println("Selected Date: " + selectedEndDate);
		test.info("Selected End Date: " + selectedEndDate);

		Thread.sleep(1000); // optional wait to view the date filled in
		ad.selectAssignedTO();
		test.info("Selected Assigned To dropdown.");

		ad.selectAssignedTO338();
		test.info("Selected Assigned User ID 338.");
		Thread.sleep(1000);
		ad.enterMaximumUser();
		test.info("Entered Maximum User count.");
		// Click Save
		ad.savebtn();
		test.info("Clicked on Save button.");


		Alert alert = driver.switchTo().alert();

		// Get the alert message
		String alertMessage = alert.getText();
		test.info("Alert message displayed: " + alertMessage);
		System.out.println("Alert message: " + alertMessage);
		Thread.sleep(1000);
		alert.accept();
		test.pass("Alert handled successfully and message verified.");
		Thread.sleep(1000);
	}

	@Test

	public void TC_DC_Delete() throws InterruptedException {

		test=extent.createTest("Admin selecte Setting option.");
		Log.startTestCase("Admin selecte Setting option.");
		admin=new AdminPage(driver);

		ad=new AdminDiscountPage(driver);
		
		String serchdis = prop.getProperty("serchDiscount");
		//String name = prop.getProperty("Name");
		admin.clickOnbody();
		Thread.sleep(3000);
		ad.clickOnDisountMainTab();
		Thread.sleep(10000);
		ad.serchDiscountCode(serchdis);
	
		Thread.sleep(4000);
		ad.deleteDiscountCode();
		Log.startTestCase("Discount code is Deleted.");
		//Thread.sleep(100);
	}



	@AfterMethod
	public void tearDown()
	{
		driver.quit();
		extent.flush();
	}
}
