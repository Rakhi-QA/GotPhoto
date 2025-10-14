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

public class TC_001_002_003_Verify_Price extends BaseClass {
	
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
	
	
	@Test(enabled = false)
	public void verify_Price_Team_STD() throws InterruptedException {
		
		test=extent.createTest("Team Price for Standard Team Build ");
		Log.startTestCase("Verify the price for the team when the photographer selects the Standard option.");
		test.info("Verify the price of the team when the photographer selects the Standard Team option.");
		fc=new FullCompositePage(driver);
		
		Thread.sleep(500);
		fc.ClickonSTD();
		test.info("Standard Team Build  option is selcted .");
		
		String price=fc.checkTeamPriceForStandard();
		
		test.info("Standard Team Build price is : "+ price);
		
		String expectedPrice="1.65";
		test.info("Price is display .");
	    if(price.equals(expectedPrice)) {
	       
	    	 test.pass("✅ Correct Price is displayed for Virtual Riser Team Build.");
	    } else {
	        
	    	test.fail("❌ Actual  Price is  : '" + expectedPrice + "' but found: '" + price + "'");
	    }
		
		
	}
	
	@Test(enabled = false)
	public void verify_Price_Team_Virtual() throws InterruptedException {
		
		test=extent.createTest("Team Price for Virtual Riser Team Build ");
		Log.startTestCase("Verify the price for the team when the photographer selects the Virtual Riser Team Build option.");
		test.info("Verify the price of the team when the photographer selects the Virtual Riser Team Build option.");
		fc=new FullCompositePage(driver);
		
		Thread.sleep(500);
		fc.ClickonVirtual();
		test.info("Virtual Riser Team Build  option is selcted .");
		
		String price=fc.checkTeamPriceForStandard();
		
		test.info("Virtual Riser Team Build price is : "+ price);
		
		String expectedPrice="1";
		test.info("Price is display .");
	    if(price.equals(expectedPrice)) {
	       
	    	 test.pass("✅ Correct Price is displayed for Virtual Riser Team Build.");
	    } else {
	        
	    	test.fail("❌ Actual  Price is  : '" + expectedPrice + "' but found: '" + price + "'");
	    }
		
		
	}
	
	
	@Test
	public void verify_Price_Team_NoTeaml() throws InterruptedException {
		
		test=extent.createTest("Team Price for NO Team option ");
		Log.startTestCase("Verify the price for the team when the photographer selects the NO Team  option.");
		test.info("Verify the price of the team when the photographer selects the NO Team option.");
		fc=new FullCompositePage(driver);
		fc.ClickonNoTeam();
		Thread.sleep(100);
		//fc.ClickonSTD();
		
		
		fc.clickonignoreTeam();
		Thread.sleep(50);
		test.info("NO Team  option is selcted .");
		
		String price=fc.checkTeamPriceForStandard();
		
		test.info("NO Team price is : "+ price);
		
		String expectedPrice="0";
		test.info("Price is display .");
	    if(price.equals(expectedPrice)) {
	       
	    	 test.pass("✅ Correct Price is displayed for NO team.");
	    } else {
	        
	    	test.fail("❌ Actual  Price is  : '" + expectedPrice + "' but found: '" + price + "'");
	    }
	}
	
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
		extent.flush();
	}

}
