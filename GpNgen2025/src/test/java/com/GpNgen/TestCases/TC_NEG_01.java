package com.GpNgen.TestCases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
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

public class TC_NEG_01 extends BaseClass
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
	public void MM_selected_but_no_tm_imag_uploaded() throws InterruptedException 
	{
		test=extent.createTest("Photographer select MM but no team image uploaded");
		Log.startTestCase("TC_NEG_01 ");
		fc=new FullCompositePage(driver);
		test.info("User Select Traditional option.");
		Thread.sleep(100);
		test.info("User Click on Traditional option.");
		fc.clickOnTraditionalOption();
		Thread.sleep(500);
		test.info("User Click on Ignore team image option.");
		fc.clickOntraditionalignoreTeamImage();
		Thread.sleep(50);
		test.info("User Click on MM graphics.");
		fc.ClickonA1_MM();
		//Thread.sleep(1000);
		Thread.sleep(50);
		test.info("User click on terms.");
		fc.clickOnterms();
		Thread.sleep(50);
		test.info("User click on Checkout button .");
		fc.clickOncheckOut();
		
		//Thread.sleep(200);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	    WebElement validationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'You have not saved team Photos for Team. Please save team photos.')]")));
		Thread.sleep(50);
	    String actualMessage = validationMessage.getText();
	    
	    String expectedMessage = "You have not saved team Photos for Team. Please save team photos.";
	    System.out.println("Validation Meaage = " + actualMessage);
	    
	    test.info("Validation message is display .");
	    if(actualMessage.equals(expectedMessage)) {
	       // System.out.println("✅ Popup message is displayed correctly.");
	    	 test.pass("✅ Correct popup message is displayed.");
	    } else {
	        //System.out.println("❌ Popup message mismatch!");
	    	test.fail("❌ Popup message mismatch. Expected: '" + expectedMessage + "' but found: '" + actualMessage + "'");
	    }

	}
	
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
		extent.flush();
	}

	
}
