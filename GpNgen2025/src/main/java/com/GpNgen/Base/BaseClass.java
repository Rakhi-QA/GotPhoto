package com.GpNgen.Base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.GpNgen.ActionDriver.Action;
import com.GpNgen.Base.APIpostReq3.JobData;
import com.GpNgen.PageObject.AdminPage;
import com.GpNgen.utility.APIutils;
import com.GpNgen.utility.ExtentManager;
import com.GpNgen.utility.GlobalData;
import com.GpNgen.utility.createNewLink;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.github.dockerjava.api.model.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public static Properties prop;
	public static WebDriver driver;

	public static String finalURL;
	public static String finalURL1;

	public static String finalURL2;



	@BeforeSuite
	public void beforeSuites() {
		DOMConfigurator.configure("log4j.xml");
	}


	@BeforeSuite
	public void setupReport() {
		ExtentManager.getInstance(); // Initialize Extent Report
	}

	@BeforeTest
	public void loadConfig() {
		try {
			prop = new Properties();
			System.out.println("Super Constructor invoked");
			FileInputStream fis;

			fis = new FileInputStream(System.getProperty("user.dir") + "\\config\\config.properties");

			prop.load(fis);
			System.out.println("driver: " + driver);
		} catch (FileNotFoundException e) {
			e.printStackTrace();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void launchApp()
	{
		try {
			String browserName = prop.getProperty("browser").toLowerCase();  // Normalize to lowercase

			switch (browserName) {
				case "chrome":
					WebDriverManager.chromedriver().setup();
					driver = new ChromeDriver();
					break;

				case "firefox":
					WebDriverManager.firefoxdriver().setup();
					FirefoxOptions options = new FirefoxOptions();
					options.setCapability("moz:webdriverClick", true);  // Helps avoid click issues in Firefox
					options.setAcceptInsecureCerts(true);               // Accept SSL certs if needed
					driver = new FirefoxDriver(options);
					break;

				case "ie":
					WebDriverManager.iedriver().setup();
					driver = new InternetExplorerDriver();
					break;

				default:
					throw new RuntimeException("❌ Browser not supported: " + browserName);
			}

			// Common setup for all browsers
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));

			System.out.println("✅ Browser launched successfully: " + browserName);
		} catch (Exception e) {
			System.out.println("❌ Failed to launch browser: " + e.getMessage());
			e.printStackTrace();
			throw new RuntimeException("❌ launchApp() failed due to: " + e.getMessage());
		}
	}


	public static String getCardNumber() {
		return prop.getProperty("CardNumber");
	}

	public static String getCVV() {
		return prop.getProperty("CVV");
	}

	public static String getFirstName() {
		return prop.getProperty("FirstName");
	}

	public static String getLastName() {
		return prop.getProperty("LastName");
	}



	public void generateCheckoutUrl() {
		APIpostRequest.JobData data = APIpostRequest.sendCreateJobRequest();
		GlobalData.job_Name = data.jobName;
		GlobalData.job_Id = data.jobId;
		finalURL = data.checkoutUrl;

		// Logging output
		System.out.println("✅ Job Name: " + GlobalData.job_Name);
		System.out.println("✅ Job ID: " + GlobalData.job_Id);
		System.out.println("✅ Checkout URL: " + finalURL);

		// Navigate to checkout URL if everything is available
		if (driver != null && finalURL != null) {
			driver.get(finalURL);
			
		} else {
			System.out.println("❌ WebDriver or URL is null!");
		}
	}


	public void generateCheckoutUrl2() {
		APIpostReq2.JobData data= APIpostReq2.sendCreateJobRequest2();
		GlobalData.job_Name = data.jobName;
		GlobalData.job_Id = data.jobId;
		finalURL = data.checkoutUrl;

		// Logging output
		System.out.println("✅ Job Name: " + GlobalData.job_Name);
		System.out.println("✅ Job ID: " + GlobalData.job_Id);
		System.out.println("✅ Checkout URL: " + finalURL);

		// Navigate to checkout URL if everything is available
		if (driver != null && finalURL != null) {
			driver.get(finalURL);
		} else {
			System.out.println("❌ WebDriver or URL is null!");
		}
	}

	public void generateCheckoutUrl3() {
		JobData data = APIpostReq3.sendCreateJobRequest3();
		GlobalData.job_Name = data.jobName;
		GlobalData.job_Id = data.jobId;
		finalURL = data.checkoutUrl;

		// Logging output
		System.out.println("✅ Job Name: " + GlobalData.job_Name);
		System.out.println("✅ Job ID: " + GlobalData.job_Id);
		System.out.println("✅ Checkout URL: " + finalURL);

		// Navigate to checkout URL if everything is available
		if (driver != null && finalURL != null) {
			driver.get(finalURL);
		} else {
			System.out.println("❌ WebDriver or URL is null!");
		}
	}



	public void openAdminPage()
	{
		AdminPage ad=new AdminPage(driver);
		driver.get(prop.getProperty("adminURL"));
		WebElement adminID = driver.findElement(By.xpath("//*[@type='text']"));
		WebElement adminPWD = driver.findElement(By.xpath("//*[@id='password']"));
		WebElement loginButton   = driver.findElement(By.xpath("//*[@name='login']"));
		adminID.sendKeys(prop.getProperty("adminid"));
		adminPWD.sendKeys(prop.getProperty("adminpwd"));
		loginButton.click();
	}

}
