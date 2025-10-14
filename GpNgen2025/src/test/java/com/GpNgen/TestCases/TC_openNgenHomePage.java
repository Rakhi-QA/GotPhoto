package com.GpNgen.TestCases;

import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.GpNgen.Base.BaseClass;
import com.GpNgen.PageObject.nextGenIndexPage;
import com.GpNgen.utility.ExtentManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class TC_openNgenHomePage extends BaseClass
{
	
	ExtentReports extent;
	ExtentTest test;
	nextGenIndexPage NGPS;
	
	@BeforeSuite
    public void setUpReport() {
        extent = ExtentManager.getInstance();
    }
	
	@BeforeMethod
	public void setup()
	{
		launchApp();
		//openGoogle();
		
	}
	
	
	@Test
	public void TC_OpenNgenHomePage() throws Throwable
	{
		NGPS=new nextGenIndexPage(driver);
		
		//NGPS.openGoogle();
		Thread.sleep(100);
		NGPS.serch();
		NGPS.clickOnFirstResult();
		Thread.sleep(100);
	}
	
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
		extent.flush();
	}

}
