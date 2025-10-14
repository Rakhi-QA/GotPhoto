package com.GpNgen.PageObject;


import java.time.Duration;
import java.util.NoSuchElementException;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.GpNgen.ActionDriver.Action;
import com.GpNgen.Base.BaseClass;
import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.JavascriptExecutor;
public class FullCompositePage extends BaseClass {

	WebDriver driver;

	public FullCompositePage(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//=======================SELECT FULL COMPOSITE ==================================================
	@FindBy(xpath = "//*[@id=\"ch_fc\"]")
	WebElement fc;

	public void verifyFCisSelected(ExtentTest test) {
		Action.scrollByVisibiltyOfElement(driver, fc);
		Action.isSelected(driver, fc, test, "Fullcomposite Option is selcted On page Load");
	}


	public void clickOnFullCompositeOption()
	{
		Action.waitForElementVisible(driver, fc, 20);
        Action.scrollByVisibiltyOfElement(driver, fc);
		Action.click(driver, fc); 
	}

	public void verifyFullCompositeOptionIsSelected()
	{
		if (!fc.isSelected()) {
			fc.click();  // only click if not already selected
		}else {System.out.println("fc is selected ");}
	}


	//======================================TRADITIONAL =========================================================

	@FindBy(xpath = "//*[@id=\"ch_td\"]")
	WebElement td;

	public void clickOnTraditionalOption()
	{
		try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	        // Wait until the element is visible and clickable
	        wait.until(ExpectedConditions.visibilityOf(td));
	        wait.until(ExpectedConditions.elementToBeClickable(td));

	        // Scroll into view using JavaScript
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", td);

	        // Click on the element
	        td.click();
	        System.out.println("✅ Traditional option clicked.");
	        
	    } catch (Exception e) {
	        System.out.println("❌ Failed to click Traditional option: " + e.getMessage());
	    }
	}

	public void verifyTraditionalIsSelected()
	{
		Action.waitForElementVisible(driver, td, 5);
		if (!td.isSelected()) {
			td.click();  // only click if not already selected
		}else {System.out.println("Traditional is selected ");}
	}


	public void checkTraditionalIsSelected() {
		if (td.isSelected()) {
			System.out.println("Traditional option  is selected");
		} else {
			System.out.println("Traditional option is NOT selected");
		}
	}



	@FindBy(xpath = "//*[@data-bb-handler=\"cancel\"]")
	WebElement traditionalignoreTeamImage;

	public void clickOntraditionalignoreTeamImage() {
		Action.click(driver, traditionalignoreTeamImage);
	}


	@FindBy(xpath = "//*[@data-bb-handler=\"confirm\"]")
	WebElement traditionalMoveTeamImage;

	public void clickOntraditionalMoveTeamImage() {
		Action.click(driver, traditionalMoveTeamImage);
	}

	//======================================TRADITIONAL PLUS=========================================================

	@FindBy(xpath = "//*[@id=\"ch_tdp\"]")
	WebElement tdp;

	public void clickOnTraditionalPlusoption()
	{
		Action.click(driver, tdp);
	}

	public void verifyTraditionalPlusIsSelected()
	{
		if (!tdp.isSelected()) {
			tdp.click();  // only click if not already selected
		}else {System.out.println("Traditional Plus is selected ");}
	}
	//========================================================= LOGO===========================================================

	@FindBy(xpath = "//*[@src=\"/img/logo.jpg\"]")
	WebElement logo;


	// ====================================================Team Options================================================================

	@FindBy(xpath = "//*[@class=\"col-xs-3 col-sm-3 col-md-3 col-lg-3 thpsrt tddisabled selected\"]")
	WebElement teamOption;

	public void verifyteamOptionisEnableOrDisable(ExtentTest test) {
		Action.scrollByVisibiltyOfElement(driver, teamOption);
		Action.isElementVisibleAndEnabled(driver, teamOption, test, "Team Option");
	}
	
	
	
	//=======================================================STANDARD OPTION ====================================================
	@FindBy(xpath = "//*[@id=\"std_team_up\"]")
	WebElement std;

	public void verifyStadardOptionisSelected(ExtentTest test) {
		Action.scrollByVisibiltyOfElement(driver, std);
		Action.isSelected(driver, std, test, "Standard Option is selcted On page Load or Not");
	}

	public void ClickonSTD() {
		try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.elementToBeClickable(std));  // Wait until clickable

	        Action.scrollByVisibiltyOfElement(driver, std);
	        Action.click(driver, std);
	    } catch (TimeoutException e) {
	        System.out.println("Standard element was not clickable in time.");
	    } catch (Exception e) {
	        System.out.println("Exception while clicking on Standard option: " + e.getMessage());
	    }
	}

	public void verifyStandardSelected()
	{
		if (!std.isSelected()) {
			std.click();  // only click if not already selected
		}else {System.out.println("std is selected ");}
	}


	//=====================================================VIRTUAL OPTION =============================================================

	@FindBy(xpath = "//*[@id=\"virtual_team_up\"]")
	WebElement virtual;

	public void verifyVirtualRaiserOptionisSelected(ExtentTest test) {
		Action.scrollByVisibiltyOfElement(driver, virtual);
		Action.isSelected(driver, virtual, test, "Virtual raiser Option is selcted On page Load or Not");
	}

	public void ClickonVirtual() {
		try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.elementToBeClickable(virtual));  // Wait until clickable

	        Action.scrollByVisibiltyOfElement(driver, virtual);
	        Action.click(driver, virtual);
	    } catch (TimeoutException e) {
	        System.out.println("Virtual option was not clickable within the timeout.");
	    } catch (Exception e) {
	        System.out.println("Exception while clicking on Virtual option: " + e.getMessage());
	    }

	}


	public void verifyVirtualSelected()
	{
		if (!virtual.isSelected()) {
			virtual.click();  // only click if not already selected
		}else {System.out.println("virtual is selected ");}
	}
	//=========================================NO TEAM OPTION ==========================================================
	@FindBy(xpath = "//*[@id=\"no_team_images_up\"]")

	WebElement noTeam;


	public void verifyNoTeamOptionisSelected(ExtentTest test) {
		Action.scrollByVisibiltyOfElement(driver, noTeam);
		Action.isSelected(driver, noTeam, test, "No team Option is selcted On page Load or Not");
	}

	public void ClickonNoTeam() {
		try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	        
	        // Wait until the element is visible and clickable
	        wait.until(ExpectedConditions.visibilityOf(noTeam));
	        wait.until(ExpectedConditions.elementToBeClickable(noTeam));
	        
	        // Scroll to element
	        Action.scrollByVisibiltyOfElement(driver, noTeam);

	        // Try standard click first
	        try {
	            noTeam.click();
	            System.out.println("✅ Clicked No Team button using normal click.");
	        } catch (ElementClickInterceptedException e) {
	            // Fallback to JavaScript click
	            System.out.println("⚠️ Normal click failed, trying JS click...");
	            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", noTeam);
	            System.out.println("✅ Clicked No Team button using JS click.");
	        }

	    } catch (Exception e) {
	        System.out.println("❌ Failed to click on No Team button: " + e.getMessage());
	    }

	}


	public void verifyNoTeamSelected()
	{
		try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	        wait.until(ExpectedConditions.elementToBeClickable(noTeam));

	        if (!noTeam.isSelected()) {
	            noTeam.click();
	            System.out.println("✅ 'No Team' option was not selected. Now clicked.");
	        } else {
	            System.out.println("ℹ️ 'No Team' option is already selected.");
	        }
	    } catch (Exception e) {
	        System.out.println("❌ Error while verifying/selecting 'No Team' option: " + e.getMessage());
	    }
	}

	//=========NO TEAM OPTION WITH Ignore Team Images ======================

	@FindBy(xpath="//*[@id='ignoreTeamImages']")
	WebElement ignoreTeamImage;

	public void clickonignoreTeam() {
		try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	        wait.until(ExpectedConditions.elementToBeClickable(ignoreTeamImage));

	        if (!ignoreTeamImage.isSelected()) {
	            ignoreTeamImage.click();
	            System.out.println("✅ 'Ignore Team Image' option clicked.");
	        } else {
	            System.out.println("ℹ️ 'Ignore Team Image' is already selected.");
	        }
	    } catch (Exception e) {
	        System.out.println("❌ Error clicking 'Ignore Team Image': " + e.getMessage());
	    }
	}

	//=========NO TEAM OPTION WITH MOve Team Images ======================
	@FindBy(xpath="//*[@id='moveTeamImages']")
	WebElement moveTeamImage;

	public void getTextMessage() {
		String msg=moveTeamImage.getText();
		System.out.println(msg);
	}
	
	

	public void clickonmoveTeamImage() {
		try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        
	        // Wait until element is visible and clickable
	        wait.until(ExpectedConditions.visibilityOf(moveTeamImage));
	        wait.until(ExpectedConditions.elementToBeClickable(moveTeamImage));
	        
	        // Scroll to element (optional but recommended for Firefox)
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", moveTeamImage);

	        // Click the element
	        moveTeamImage.click();
	        System.out.println("✅ Clicked on 'Move Team Image' option.");

	    } catch (Exception e) {
	        System.out.println("❌ Failed to click on 'Move Team Image': " + e.getMessage());
	    }
	}
	
	
	//=========NO TEAM OPTION WITH All Individual images is present text message (FULL Composite )======================
	
		@FindBy(xpath="//p[contains(text(),'There are images present in the Image 5 column. What would you like to do?')]")
		WebElement imageIsPresentInall;
	
		
		public void getImgpresetMessage() {
			imageIsPresentInall.getText();
		}
		
		//=========NO TEAM OPTION WITH All Individual images is present text message (Traditional Plus ) ======================
		
			@FindBy(xpath="//div[contains(text(),'You have images in the image5 column. Please ignore Team Images.')]")
			WebElement imageIsPresentInallTP;
		
			
			public String getImgpresetMessageTP() {
				return imageIsPresentInallTP.getText();
			}	
		
			@FindBy(xpath="//*[@data-bb-handler='confirm']")
			WebElement ignoreBTNTP;
		
			
			public void clickOnIgnoreBTNTP() {
				try {
			        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			        wait.until(ExpectedConditions.elementToBeClickable(ignoreBTNTP));
			        ignoreBTNTP.click();
			        System.out.println("✅ Clicked on Ignore Button (TP).");
			    } catch (Exception e) {
			        System.out.println("❌ Failed to click on Ignore Button (TP): " + e.getMessage());
			    }
			}	
			
			
			//=========NO TEAM OPTION WITH All Individual images is present text message (Traditional  ) ======================
			
			@FindBy(xpath="//*[contains(text(),'You have images in the image5 column. Please ignore Team Images.')]")
			WebElement imageIsPresentInallT;
		
			
			public String getImgpresetMessageT() {
				return imageIsPresentInallT.getText();
			}	
		
			@FindBy(xpath="//*[@data-bb-handler='confirm']")
			WebElement ignoreBTNT;
		
			
			public void clickOnIgnoreBTNT() {
				try {
			        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			        wait.until(ExpectedConditions.elementToBeClickable(ignoreBTNT));
			        ignoreBTNT.click();
			        System.out.println("✅ Clicked on Ignore Button (T).");
			    } catch (Exception e) {
			        System.out.println("❌ Failed to click on Ignore Button (T): " + e.getMessage());
			    }
			}		
			
		
	//=======================Total Number of Team Images===============================================

	@FindBy(xpath = "//*[@id='teamonlycost1']")
	WebElement noTeamImagePrice;

	public void checkNoteamImagePrice() {
		Action.scrollByVisibiltyOfElement(driver, noTeamImagePrice);
		System.out.println("Standard team image price is -: "+  noTeamImagePrice.getText());
	}


	// ===================================================Alternate 45V===========================================



	@FindBy(xpath = "(//b[normalize-space()='_45V'])[1]")
	WebElement _45V;

	public void check_45V_VisibleorEnabled(ExtentTest test) {
		Action.scrollByVisibiltyOfElement(driver, _45V);
		Action.isElementVisibleAndEnabled(driver, _45V, test, "_45V is visible");
		/*if(Action.isElementVisibleAndEnabled(driver, _45V, test))
		{
			System.out.println("The 45V option is visible.");
			} else {
			    System.out.println("The 45V option is NOT visible.");

		}*/


	}


	@FindBy(xpath = "//*[@id=\"alt1_45V\"]")
	WebElement A1_45V;

	public void check_A1_45V_isSelectedOrNot(ExtentTest test) {

		Action.scrollByVisibiltyOfElement(driver, A1_45V);
		 // Check if the option is selected
	    if (A1_45V.isSelected()) {
	        test.pass("✅ A1_45V is selected as expected.");
	        System.out.println("A1_45V is selected.");
	    } else {
	        test.fail("❌ A1_45V is NOT selected.");
	        System.out.println("A1_45V is NOT selected.");
	    }
	}

	public void check_A1_45V_EnableOrDisable(ExtentTest test) {
		Action.scrollByVisibiltyOfElement(driver, A1_45V);
		Action.checkIfCheckboxIsEnabled(A1_45V, "Image 1 - 45V", test);
	}

	public void ClickonA1_45V() {
		try {
	        // Scroll into view (your custom action)
	        Action.scrollByVisibiltyOfElement(driver, A1_45V);

	        // Wait for element to be clickable
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.elementToBeClickable(A1_45V));

	        // Click only if not already selected
	        if (!A1_45V.isSelected()) {
	            A1_45V.click();  // or use Action.click(driver, A1_45V); if that handles retries
	            System.out.println("✅ A1_45V was not selected, now clicked.");
	        } else {
	            System.out.println("ℹ️ A1_45V is already selected.");
	        }
	    } catch (Exception e) {
	        System.out.println("❌ Error while clicking A1_45V: " + e.getMessage());
	    }
	}

	@FindBy(xpath = "//*[@id=\"alt2_45V\"]")
	WebElement A2_45V;

	public void check_A2_45V_isSelectedOrNot(ExtentTest test) {

		Action.scrollByVisibiltyOfElement(driver, A2_45V);
		 // Check if the option is selected
	    if (A2_45V.isSelected()) {
	        test.pass("✅ A2_45V is selected as expected.");
	        System.out.println("A2_45V is selected.");
	    } else {
	        test.fail("❌ A2_45V is NOT selected.");
	        System.out.println("A2_45V is NOT selected.");
	    }
	}

	public void check_A2_45V_EnableOrDisable(ExtentTest test) {
		Action.scrollByVisibiltyOfElement(driver, A2_45V);
		Action.checkIfCheckboxIsEnabled(A2_45V, "Image 2 - 45V", test);
	}

	public void ClickonA2_45V() {
		try {
	        // Scroll into view (your custom scroll utility)
	        Action.scrollByVisibiltyOfElement(driver, A2_45V);

	        // Wait until it's clickable
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.elementToBeClickable(A2_45V));

	        // Click only if not already selected
	        if (!A2_45V.isSelected()) {
	            A2_45V.click();  // Or use Action.click(driver, A2_45V);
	            System.out.println("✅ A2_45V was not selected, now clicked.");
	        } else {
	            System.out.println("ℹ️ A2_45V is already selected.");
	        }
	    } catch (Exception e) {
	        System.out.println("❌ Error while clicking A2_45V: " + e.getMessage());
	    }

	}

	@FindBy(xpath = "//*[@id=\"alt3_45V\"]")
	WebElement A3_45V;

	public void check_A3_45V_isSelectedOrNot(ExtentTest test) {

		Action.scrollByVisibiltyOfElement(driver, A3_45V);
		 // Check if the option is selected
	    if (A3_45V.isSelected()) {
	        test.pass("✅ A3_45V is selected as expected.");
	        System.out.println("A3_45V is selected.");
	    } else {
	        test.fail("❌ A3_45V is NOT selected.");
	        System.out.println("A3_45V is NOT selected.");
	    }
	}

	
	public void check_A3_45V_EnableOrDisable(ExtentTest test) {
		Action.scrollByVisibiltyOfElement(driver, A3_45V);
		Action.checkIfCheckboxIsEnabled(A3_45V,"Image 3 - 45V", test);
	}

	public void ClickonA3_45V() {
		try {
	        // Scroll into view using custom action utility
	        Action.scrollByVisibiltyOfElement(driver, A3_45V);

	        // Wait until it's clickable
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.elementToBeClickable(A3_45V));

	        // Click only if not already selected
	        if (!A3_45V.isSelected()) {
	            A3_45V.click();  // Or Action.click(driver, A3_45V); if your utility handles it better
	            System.out.println("✅ A3_45V was not selected, clicked now.");
	        } else {
	            System.out.println("ℹ️ A3_45V is already selected.");
	        }
	    } catch (Exception e) {
	        System.out.println("❌ Error while clicking A3_45V: " + e.getMessage());
	    }
	}


	@FindBy(xpath = "//*[@id=\"alt4_45V\"]")
	WebElement A4_45V;

	
	public void check_A4_45V_isSelectedOrNot(ExtentTest test) {

		Action.scrollByVisibiltyOfElement(driver, A4_45V);
		 // Check if the option is selected
	    if (A4_45V.isSelected()) {
	        test.pass("✅ A4_45V is selected as expected.");
	        System.out.println("A4_45V is selected.");
	    } else {
	        test.fail("❌ A4_45V is NOT selected.");
	        System.out.println("A4_45V is NOT selected.");
	    }
	}


	public void check_A4_45V_EnableOrDisable(ExtentTest test) {
		Action.scrollByVisibiltyOfElement(driver, A4_45V);
		Action.checkIfCheckboxIsEnabled(A4_45V,"Image 4 - 45V", test);
	}

	public void ClickonA4_45V() {
		try {
	        // Scroll into view
	        Action.scrollByVisibiltyOfElement(driver, A4_45V);

	        // Wait until it's clickable
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.elementToBeClickable(A4_45V));

	        // Check if it's already selected
	        if (!A4_45V.isSelected()) {
	            A4_45V.click();  // Or use Action.click(driver, A4_45V); if needed
	            System.out.println("✅ A4_45V was not selected, clicked now.");
	        } else {
	            System.out.println("ℹ️ A4_45V is already selected.");
	        }
	    } catch (Exception e) {
	        System.out.println("❌ Error while clicking A4_45V: " + e.getMessage());
	    }
	}

	@FindBy(xpath = "//*[@id=\"alt5_45V\"]")
	WebElement A5_45V;

	
	public void check_A5_45V_isSelectedOrNot(ExtentTest test) {

		Action.scrollByVisibiltyOfElement(driver, A5_45V);
		 // Check if the option is selected
	    if (A5_45V.isSelected()) {
	        test.pass("✅ A5_45V is selected as expected.");
	        System.out.println("A5_45V is selected.");
	    } else {
	        test.fail("❌ A5_45V is NOT selected.");
	        System.out.println("A5_45V is NOT selected.");
	    }
	}

	
	public void check_A5_45V_EnableOrDisable(ExtentTest test) {
		Action.scrollByVisibiltyOfElement(driver, A5_45V);
		Action.checkIfCheckboxIsEnabled(A5_45V,"Image 5 - 45V", test);
	}

	public void ClickonA5_45V() {
		try {
	        // Scroll the element into view
	        Action.scrollByVisibiltyOfElement(driver, A5_45V);

	        // Wait until the element is clickable
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.elementToBeClickable(A5_45V));

	        // Click only if not selected
	        if (!A5_45V.isSelected()) {
	            A5_45V.click(); // You can use Action.click(driver, A5_45V) if needed
	            System.out.println("✅ A5_45V was not selected. Clicked now.");
	        } else {
	            System.out.println("ℹ️ A5_45V is already selected.");
	        }
	    } catch (Exception e) {
	        System.out.println("❌ Failed to click A5_45V: " + e.getMessage());
	    }

	}

	//========================================================== Alternate _45TV==============================


	@FindBy(xpath = "(//b[normalize-space()='_45TV'])[1]")
	WebElement _45TV;



	public void check_45TV_VisibleorEnabled(ExtentTest test) {
		Action.scrollByVisibiltyOfElement(driver, _45TV);
		Action.isElementVisibleAndEnabled(driver, _45TV, test, "_45TV is visible");
		/*if(Action.isElementVisibleAndEnabled(driver, _45TV, test))
		{
			System.out.println("The 45TV option is visible.");
			} else {
			    System.out.println("The 45TV option is NOT visible.");

		}*/


	}

	@FindBy(xpath = "//*[@id=\"alt1_45TV\"]")
	WebElement A1_45TV;

	public void check_A1_45TV_isSelectedOrNot(ExtentTest test) {

		Action.scrollByVisibiltyOfElement(driver, A1_45TV);
		 // Check if the option is selected
	    if (A1_45TV.isSelected()) {
	        test.pass("✅ A1_45TV is selected as expected.");
	        System.out.println("A1_45TV is selected.");
	    } else {
	        test.fail("❌ A1_45TV is NOT selected.");
	        System.out.println("A1_45TV is NOT selected.");
	    }
	}


	public void check_A1_45TV_EnableOrDisable(ExtentTest test) {
		Action.scrollByVisibiltyOfElement(driver, A1_45TV);
		Action.checkIfCheckboxIsEnabled(A1_45TV,"Image 1 - 45TV", test);
	}

	public void ClickonA1_45TV() {
		try {
	        // Scroll the element into view
	        Action.scrollByVisibiltyOfElement(driver, A1_45TV);

	        // Wait until the element is clickable
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.elementToBeClickable(A1_45TV));

	        // Click only if not selected
	        if (!A1_45TV.isSelected()) {
	            A1_45TV.click(); // or use Action.click(driver, A1_45TV);
	            System.out.println("✅ A1_45TV was not selected. Clicked now.");
	        } else {
	            System.out.println("ℹ️ A1_45TV is already selected.");
	        }
	    } catch (Exception e) {
	        System.out.println("❌ Failed to click A1_45TV: " + e.getMessage());
	    }

	}

	@FindBy(xpath = "//*[@id=\"alt2_45TV\"]")
	WebElement A2_45TV;

	public void check_A2_45TV_isSelectedOrNot(ExtentTest test) {

		Action.scrollByVisibiltyOfElement(driver, A2_45TV);
		if (A2_45TV.isSelected()) {
	        test.pass("✅ A2_45TV is selected as expected.");
	        System.out.println("A2_45TV is selected.");
	    } else {
	        test.fail("❌ A2_45TV is NOT selected.");
	        System.out.println("A2_45TV is NOT selected.");
	    }
	}


	public void check_A2_45TV_EnableOrDisable(ExtentTest test) {
		Action.scrollByVisibiltyOfElement(driver, A2_45TV);
		Action.checkIfCheckboxIsEnabled(A2_45TV,"Image 2 - 45TV", test);
	}

	public void ClickonA2_45TV() {
		try {
	        // Scroll to the element
	        Action.scrollByVisibiltyOfElement(driver, A2_45TV);

	        // Wait until the element is clickable
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.elementToBeClickable(A2_45TV));

	        // Click only if not already selected
	        if (!A2_45TV.isSelected()) {
	            A2_45TV.click();
	            System.out.println("✅ A2_45TV was not selected. Clicked now.");
	        } else {
	            System.out.println("ℹ️ A2_45TV is already selected.");
	        }
	    } catch (Exception e) {
	        System.out.println("❌ Failed to click A2_45TV: " + e.getMessage());
	    }

	}

	@FindBy(xpath = "//*[@id=\"alt3_45TV\"]")
	WebElement A3_45TV;

	
	public void check_A3_45TV_EnableOrDisable(ExtentTest test) {
		Action.scrollByVisibiltyOfElement(driver, A3_45TV);
		Action.checkIfCheckboxIsEnabled(A3_45TV,"Image 3 - 45TV", test);
	}

	public void ClickonA3_45TV() {
		 try {
		        // Scroll into view
		        Action.scrollByVisibiltyOfElement(driver, A3_45TV);

		        // Wait until element is clickable
		        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		        wait.until(ExpectedConditions.elementToBeClickable(A3_45TV));

		        // Click only if not already selected
		        if (!A3_45TV.isSelected()) {
		            A3_45TV.click();
		            System.out.println("✅ A3_45TV was not selected. Clicked now.");
		        } else {
		            System.out.println("ℹ️ A3_45TV is already selected.");
		        }
		    } catch (Exception e) {
		        System.out.println("❌ Failed to click A3_45TV: " + e.getMessage());
		    }

	}

	@FindBy(xpath = "//*[@id=\"alt4_45TV\"]")
	WebElement A4_45TV;
	public void check_A4_45TV_isSelectedOrNot(ExtentTest test) {

		Action.scrollByVisibiltyOfElement(driver, A4_45TV);
		if (A4_45TV.isSelected()) {
	        test.pass("✅ A4_45TV is selected as expected.");
	        System.out.println("A4_45TV is selected.");
	    } else {
	        test.fail("❌ A4_45TV is NOT selected.");
	        System.out.println("A4_45TV is NOT selected.");
	    }
	}
	public void check_A4_45TV_EnableOrDisable(ExtentTest test) {
		Action.scrollByVisibiltyOfElement(driver, A4_45TV);
		Action.checkIfCheckboxIsEnabled(A4_45TV,"Image 4 - 45TV", test);
	}

	public void ClickonA4_45TV() {
		try {
	        // Scroll into view
	        Action.scrollByVisibiltyOfElement(driver, A4_45TV);

	        // Wait until element is clickable
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.elementToBeClickable(A4_45TV));

	        // Click only if not already selected
	        if (!A4_45TV.isSelected()) {
	            A4_45TV.click();
	            System.out.println("✅ A4_45TV was not selected. Clicked now.");
	        } else {
	            System.out.println("ℹ️ A4_45TV is already selected.");
	        }
	    } catch (Exception e) {
	        System.out.println("❌ Failed to click A4_45TV: " + e.getMessage());
	    }

	}


	@FindBy(xpath = "//*[@id=\"alt5_45TV\"]")
	WebElement A5_45TV;

	
	public void check_A5_45TV_isSelectedOrNot(ExtentTest test) {

		Action.scrollByVisibiltyOfElement(driver, A5_45TV);
		if (A5_45TV.isSelected()) {
	        test.pass("✅ A5_45TV is selected as expected.");
	        System.out.println("A5_45TV is selected.");
	    } else {
	        test.fail("❌ A5_45TV is NOT selected.");
	        System.out.println("A5_45TV is NOT selected.");
	    }
	}
	
	public void check_A5_45TV_EnableOrDisable(ExtentTest test) {
		Action.scrollByVisibiltyOfElement(driver, A5_45TV);
		Action.checkIfCheckboxIsEnabled(A5_45TV,"Image 5 - 45TV", test);
	}


	public void ClickonA5_45TV() {
		try {
	        // Scroll element into view
	        Action.scrollByVisibiltyOfElement(driver, A5_45TV);

	        // Explicit wait until element is clickable
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.elementToBeClickable(A5_45TV));

	        // Click only if not already selected
	        if (!A5_45TV.isSelected()) {
	            A5_45TV.click();
	            System.out.println("✅ A5_45TV was not selected. Clicked now.");
	        } else {
	            System.out.println("ℹ️ A5_45TV is already selected.");
	        }
	    } catch (Exception e) {
	        System.out.println("❌ Failed to click A5_45TV: " + e.getMessage());
	    }

	}

	//================================= Alternate _55 ===========================================================

	@FindBy(xpath = "(//b[normalize-space()='_55'])[1]")
	WebElement _55;

	public void check_55_VisibleorEnabled(ExtentTest test) {
		Action.scrollByVisibiltyOfElement(driver, _55);
		Action.isElementVisibleAndEnabled(driver, _55, test, "_55 Is visible");
		/*if(Action.isElementVisibleAndEnabled(driver, _55, test))
		{
			System.out.println("The _55 option is visible.");
			} else {
			    System.out.println("The _55 option is NOT visible.");

		}*/


	}



	@FindBy(xpath = "//*[@id=\"alt1_55\"]")
	WebElement A1_55;

	public void check_A1_55_EnableOrDisable(ExtentTest test) {
		Action.scrollByVisibiltyOfElement(driver, A1_55);
		Action.checkIfCheckboxIsEnabled(A1_55,"Image 1 - 55", test);
	}


	public void ClickonA1_55() {
		try {
	        // Scroll to element
	        Action.scrollByVisibiltyOfElement(driver, A1_55);

	        // Wait until the element is clickable
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.elementToBeClickable(A1_55));

	        // Click only if not already selected
	        if (!A1_55.isSelected()) {
	            A1_55.click();
	            System.out.println("✅ A1_55 clicked successfully.");
	        } else {
	            System.out.println("ℹ️ A1_55 is already selected.");
	        }
	    } catch (Exception e) {
	        System.out.println("❌ Failed to click A1_55: " + e.getMessage());
	    }

	}
	@FindBy(xpath = "//*[@id=\"alt2_55\"]")
	WebElement A2_55;

	public void check_A2_55_EnableOrDisable(ExtentTest test) {
		Action.scrollByVisibiltyOfElement(driver, A2_55);
		Action.checkIfCheckboxIsEnabled(A2_55,"Image 2 - 55", test);
	}

	public void ClickonA2_55() {
		try {
	        // Scroll to the element
	        Action.scrollByVisibiltyOfElement(driver, A2_55);

	        // Wait until it is clickable
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.elementToBeClickable(A2_55));

	        // Click only if not already selected
	        if (!A2_55.isSelected()) {
	            A2_55.click();
	            System.out.println("✅ A2_55 clicked successfully.");
	        } else {
	            System.out.println("ℹ️ A2_55 is already selected.");
	        }
	    } catch (Exception e) {
	        System.out.println("❌ Failed to click A2_55: " + e.getMessage());
	    }
	}

	@FindBy(xpath = "//*[@id=\"alt3_55\"]")
	WebElement A3_55;

	public void check_A3_55_EnableOrDisable(ExtentTest test) {
		Action.scrollByVisibiltyOfElement(driver, A3_55);
		Action.checkIfCheckboxIsEnabled(A3_55,"Image 3 - 55", test);
	}

	public void ClickonA3_55() {
		 try {
		        // Scroll to the A3_55 element
		        Action.scrollByVisibiltyOfElement(driver, A3_55);

		        // Wait until the element is clickable
		        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		        wait.until(ExpectedConditions.elementToBeClickable(A3_55));

		        // Click only if not already selected
		        if (!A3_55.isSelected()) {
		            A3_55.click();
		            System.out.println("✅ A3_55 clicked successfully.");
		        } else {
		            System.out.println("ℹ️ A3_55 is already selected.");
		        }
		    } catch (Exception e) {
		        System.out.println("❌ Failed to click A3_55: " + e.getMessage());
		    }

	}

	@FindBy(xpath = "//*[@id=\"alt4_55\"]")
	WebElement A4_55;

	public void check_A4_55_EnableOrDisable(ExtentTest test) {
		Action.scrollByVisibiltyOfElement(driver, A4_55);
		Action.checkIfCheckboxIsEnabled(A4_55,"Image 4 - 55", test);
	}

	public void ClickonA4_55() {
		try {
	        // Scroll to the element
	        Action.scrollByVisibiltyOfElement(driver, A4_55);

	        // Wait until it's clickable
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.elementToBeClickable(A4_55));

	        // Click only if not selected
	        if (!A4_55.isSelected()) {
	            A4_55.click();
	            System.out.println("✅ A4_55 clicked successfully.");
	        } else {
	            System.out.println("ℹ️ A4_55 is already selected.");
	        }
	    } catch (Exception e) {
	        System.out.println("❌ Failed to click A4_55: " + e.getMessage());
	    }

	}

	@FindBy(xpath = "//*[@id=\"alt5_55\"]")
	WebElement A5_55;

	public void check_A5_55_EnableOrDisable(ExtentTest test) {
		Action.scrollByVisibiltyOfElement(driver, A5_55);
		Action.checkIfCheckboxIsEnabled(A5_55,"Image 5 - 55", test);
	}

	public void ClickonA5_55() {
		try {
	        // Scroll to make element visible
	        Action.scrollByVisibiltyOfElement(driver, A5_55);

	        // Wait until it's clickable
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.elementToBeClickable(A5_55));

	        // Click only if not already selected
	        if (!A5_55.isSelected()) {
	            A5_55.click();
	            System.out.println("✅ A5_55 clicked successfully.");
	        } else {
	            System.out.println("ℹ️ A5_55 is already selected.");
	        }
	    } catch (Exception e) {
	        System.out.println("❌ Failed to click A5_55: " + e.getMessage());
	    }

	}

	//================================================ Alternate _55T ===========================================

	@FindBy(xpath = "(//b[normalize-space()='_55T'])[1]")
	WebElement _55T;

	public void check_55T_VisibleorEnabled(ExtentTest test) {
		Action.scrollByVisibiltyOfElement(driver, _55T);
		Action.isElementVisibleAndEnabled(driver, _55T, test, "_55T is visible");
		/*if(Action.isElementVisibleAndEnabled(driver, _55T, test))
		{
			System.out.println("The _55T option is visible.");
			} else {
			    System.out.println("The _55T option is NOT visible.");

		}*/


	}





	@FindBy(xpath = "//*[@id=\"alt1_55T\"]")
	WebElement A1_55T;

	public void check_A1_55T_EnableOrDisable(ExtentTest test) {
		Action.scrollByVisibiltyOfElement(driver, A1_55T);
		Action.checkIfCheckboxIsEnabled(A1_55T,"Image 1 - 55T", test);
	}

	public void ClickonA1_55T() {
		try {
	        // Scroll to make A1_55T visible
	        Action.scrollByVisibiltyOfElement(driver, A1_55T);

	        // Wait until the element is clickable
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.elementToBeClickable(A1_55T));

	        // Click only if not already selected
	        if (!A1_55T.isSelected()) {
	            A1_55T.click();
	            System.out.println("✅ A1_55T clicked.");
	        } else {
	            System.out.println("ℹ️ A1_55T is already selected.");
	        }

	    } catch (Exception e) {
	        System.out.println("❌ Failed to click A1_55T: " + e.getMessage());
	    }

	}

	@FindBy(xpath = "//*[@id=\"alt2_55T\"]")
	WebElement A2_55T;

	public void check_A2_55T_EnableOrDisable(ExtentTest test) {
		Action.scrollByVisibiltyOfElement(driver, A2_55T);
		Action.checkIfCheckboxIsEnabled(A2_55T,"Image 2 - 55T", test);
	}

	public void ClickonA2_55T() {
		try {
	        // Scroll to ensure the element is visible
	        Action.scrollByVisibiltyOfElement(driver, A2_55T);

	        // Wait until it's clickable
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.elementToBeClickable(A2_55T));

	        // Click only if not already selected
	        if (!A2_55T.isSelected()) {
	            A2_55T.click();
	            System.out.println("✅ A2_55T clicked.");
	        } else {
	            System.out.println("ℹ️ A2_55T is already selected.");
	        }

	    } catch (Exception e) {
	        System.out.println("❌ Failed to click A2_55T: " + e.getMessage());
	    }

	}

	@FindBy(xpath = "//*[@id=\"alt3_55T\"]")
	WebElement A3_55T;

	public void check_A3_55T_EnableOrDisable(ExtentTest test) {
		Action.scrollByVisibiltyOfElement(driver, A3_55T);
		Action.checkIfCheckboxIsEnabled(A3_55T,"Image 3 - 55T", test);
	}

	public void ClickonA3_55T() {
		 try {
		        // Scroll to the element to ensure it's visible
		        Action.scrollByVisibiltyOfElement(driver, A3_55T);

		        // Wait until the element is clickable
		        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		        wait.until(ExpectedConditions.elementToBeClickable(A3_55T));

		        // Click if not already selected
		        if (!A3_55T.isSelected()) {
		            A3_55T.click();
		            System.out.println("✅ A3_55T clicked.");
		        } else {
		            System.out.println("ℹ️ A3_55T is already selected.");
		        }
		    } catch (Exception e) {
		        System.out.println("❌ Failed to click A3_55T: " + e.getMessage());
		    }
	}

	@FindBy(xpath = "//*[@id=\"alt4_55T\"]")
	WebElement A4_55T;

	public void check_A4_55T_EnableOrDisable(ExtentTest test) {
		Action.scrollByVisibiltyOfElement(driver, A4_55T);
		Action.checkIfCheckboxIsEnabled(A4_55T,"Image 4 - 55T", test);
	}

	public void ClickonA4_55T() {
		try {
	        // Scroll to make element visible
	        Action.scrollByVisibiltyOfElement(driver, A4_55T);

	        // Wait until the element is clickable
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.elementToBeClickable(A4_55T));

	        // Click only if not already selected
	        if (!A4_55T.isSelected()) {
	            A4_55T.click();
	            System.out.println("✅ A4_55T checkbox clicked.");
	        } else {
	            System.out.println("ℹ️ A4_55T is already selected.");
	        }
	    } catch (Exception e) {
	        System.out.println("❌ Error while clicking A4_55T: " + e.getMessage());
	    }

	}

	@FindBy(xpath = "//*[@id=\"alt5_55T\"]")
	WebElement A5_55T;

	public void check_A5_55T_EnableOrDisable(ExtentTest test) {
		Action.scrollByVisibiltyOfElement(driver, A5_55T);
		Action.checkIfCheckboxIsEnabled(A5_55T,"Image 5 - 55T", test);
	}


	public void ClickonA5_55T() {
		 try {
		        // Scroll to make element visible
		        Action.scrollByVisibiltyOfElement(driver, A5_55T);

		        // Wait until the checkbox is clickable
		        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		        wait.until(ExpectedConditions.elementToBeClickable(A5_55T));

		        // Click only if not already selected
		        if (!A5_55T.isSelected()) {
		            A5_55T.click();
		            System.out.println("✅ A5_55T checkbox clicked.");
		        } else {
		            System.out.println("ℹ️ A5_55T is already selected.");
		        }
		    } catch (Exception e) {
		        System.out.println("❌ Error while clicking A5_55T: " + e.getMessage());
		    }

	}

	// =================================================Alternate _MM ==========================================
	@FindBy(xpath = "(//b[normalize-space()='_MM'])[1]")
	WebElement _MM;

	public void check_MM_VisibleorEnabled(ExtentTest test) {
		Action.scrollByVisibiltyOfElement(driver, _MM);
		Action.isElementVisibleAndEnabled(driver, _MM, test, "_MM is visible or not");
		/*if(Action.isElementVisibleAndEnabled(driver, _MM, test))
		{
			System.out.println("The _MM option is visible.");
			} else {
			    System.out.println("The _MM option is NOT visible.");

		}*/


	}


	@FindBy(xpath = "//*[@id=\"alt1_MM\"]")
	WebElement A1_MM;


	public void check_A1_MM_isSelectedOrNot(ExtentTest test) {

		Action.scrollByVisibiltyOfElement(driver, A1_MM);

			if (A1_MM.isSelected()) {
		        test.pass("✅ A1_MM is selected as expected.");
		        System.out.println("A1_MM is selected.");
		    } else {
		        test.fail("❌ A1_MM is NOT selected.");
		        System.out.println("A1_MM is NOT selected.");
		    }
		}
	


	public void check_A1_MM_EnableOrDisable(ExtentTest test) {
		Action.scrollByVisibiltyOfElement(driver, A1_MM);
		Action.checkIfCheckboxIsEnabled(A1_MM,"Image 1 - MM", test);
	}


	public void ClickonA1_MM() {
		try {
	        // Scroll to bring element into view
	        Action.scrollByVisibiltyOfElement(driver, A1_MM);

	        // Wait until the element is clickable
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.elementToBeClickable(A1_MM));

	        // Click if not already selected
	        if (!A1_MM.isSelected()) {
	            A1_MM.click();
	            System.out.println("✅ A1_MM checkbox clicked.");
	        } else {
	            System.out.println("ℹ️ A1_MM is already selected.");
	        }
	    } catch (Exception e) {
	        System.out.println("❌ Error clicking A1_MM: " + e.getMessage());
	    }

	}

	@FindBy(xpath = "//*[@id=\"alt2_MM\"]")
	WebElement A2_MM;


	public void check_A2_MM_isSelectedOrNot(ExtentTest test) {

		Action.scrollByVisibiltyOfElement(driver, A2_MM);
		if (A2_MM.isSelected()) {
	        test.pass("✅ A2_MM is selected as expected.");
	        System.out.println("A2_MM is selected.");
	    } else {
	        test.fail("❌ A2_MM is NOT selected.");
	        System.out.println("A2_MM is NOT selected.");
	    }
	}


	public void check_A2_MM_EnableOrDisable(ExtentTest test) {
		Action.scrollByVisibiltyOfElement(driver, A2_MM);
		Action.checkIfCheckboxIsEnabled(A2_MM,"Image 2 - MM", test);
	}


	public void ClickonA2_MM() {
		 try {
		        // Scroll to the element
		        Action.scrollByVisibiltyOfElement(driver, A2_MM);

		        // Wait until clickable
		        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		        wait.until(ExpectedConditions.elementToBeClickable(A2_MM));

		        // Click only if not already selected
		        if (!A2_MM.isSelected()) {
		            A2_MM.click();
		            System.out.println("✅ A2_MM checkbox clicked.");
		        } else {
		            System.out.println("ℹ️ A2_MM is already selected.");
		        }
		    } catch (Exception e) {
		        System.out.println("❌ Error clicking A2_MM: " + e.getMessage());
		    }

	}

	@FindBy(xpath = "//*[@id=\"alt3_MM\"]")
	WebElement A3_MM;

	public void check_A3_MM_isSelectedOrNot(ExtentTest test) {

		Action.scrollByVisibiltyOfElement(driver, A3_MM);
		if (A3_MM.isSelected()) {
	        test.pass("✅ A3_MM is selected as expected.");
	        System.out.println("A3_MM is selected.");
	    } else {
	        test.fail("❌ A3_MM is NOT selected.");
	        System.out.println("A3_MM is NOT selected.");
	    }
	}
	
	public void check_A3_MM_EnableOrDisable(ExtentTest test) {
		Action.scrollByVisibiltyOfElement(driver, A3_MM);
		Action.checkIfCheckboxIsEnabled(A3_MM,"Image 3 - MM", test);
	}

	public void ClickonA3_MM() {
		try {
	        // Scroll to make the element visible
	        Action.scrollByVisibiltyOfElement(driver, A3_MM);

	        // Wait until it is clickable
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.elementToBeClickable(A3_MM));

	        // Click only if it's not already selected
	        if (!A3_MM.isSelected()) {
	            A3_MM.click();
	            System.out.println("✅ A3_MM checkbox clicked.");
	        } else {
	            System.out.println("ℹ️ A3_MM is already selected.");
	        }
	    } catch (Exception e) {
	        System.out.println("❌ Error clicking A3_MM: " + e.getMessage());
	    }
	}

	@FindBy(xpath = "//*[@id=\"alt4_MM\"]")
	WebElement A4_MM;

	public void check_A4_MM_isSelectedOrNot(ExtentTest test) {

		Action.scrollByVisibiltyOfElement(driver, A4_MM);
		if (A4_MM.isSelected()) {
	        test.pass("✅ A4_MM is selected as expected.");
	        System.out.println("A4_MM is selected.");
	    } else {
	        test.fail("❌ A4_MM is NOT selected.");
	        System.out.println("A4_MM is NOT selected.");
	    }
	}
	
	
	public void check_A4_MM_EnableOrDisable(ExtentTest test) {
		Action.scrollByVisibiltyOfElement(driver, A4_MM);
		Action.checkIfCheckboxIsEnabled(A4_MM,"Image 4 - MM", test);
	}

	public void ClickonA4_MM() {
		try {
	        // Scroll to element
	        Action.scrollByVisibiltyOfElement(driver, A4_MM);

	        // Wait until it is clickable
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.elementToBeClickable(A4_MM));

	        // Click only if not selected
	        if (!A4_MM.isSelected()) {
	            A4_MM.click();
	            System.out.println("✅ A4_MM checkbox clicked.");
	        } else {
	            System.out.println("ℹ️ A4_MM is already selected.");
	        }
	    } catch (Exception e) {
	        System.out.println("❌ Error while clicking A4_MM: " + e.getMessage());
	    }

	}

	@FindBy(xpath = "//*[@id=\"alt5_MM\"]")
	WebElement A5_MM;

	public void check_A5_MM_isSelectedOrNot(ExtentTest test) {

		Action.scrollByVisibiltyOfElement(driver, A5_MM);
		if (A5_MM.isSelected()) {
	        test.pass("✅ A5_MM is selected as expected.");
	        System.out.println("A5_MM is selected.");
	    } else {
	        test.fail("❌ A5_MM is NOT selected.");
	        System.out.println("A5_MM is NOT selected.");
	    }
	}
	
	
	public void check_A5_MM_EnableOrDisable(ExtentTest test) {
		Action.scrollByVisibiltyOfElement(driver, A5_MM);
		Action.checkIfCheckboxIsEnabled(A5_MM,"Image 5 - MM", test);
	}

	public void ClickonA5_MM() {
		try {
	        // Scroll to element
	        Action.scrollByVisibiltyOfElement(driver, A5_MM);

	        // Wait until element is clickable
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.elementToBeClickable(A5_MM));

	        // Check and click if not already selected
	        if (!A5_MM.isSelected()) {
	            A5_MM.click();
	            System.out.println("✅ A5_MM checkbox clicked.");
	        } else {
	            System.out.println("ℹ️ A5_MM is already selected.");
	        }
	    } catch (Exception e) {
	        System.out.println("❌ Error while clicking A5_MM: " + e.getMessage());
	    }

	}

	// ===========================================Alternate _SV =====================================================

	@FindBy(xpath = "(//b[normalize-space()='_SV'])[1]")
	WebElement _SV;

	public void check_SV_VisibleorEnabled(ExtentTest test) {
		Action.scrollByVisibiltyOfElement(driver, _SV);
		Action.isElementVisibleAndEnabled(driver, _SV, test, "_SV is visible");
		/*if(Action.isElementVisibleAndEnabled(driver, _SV, test))
		{
			System.out.println("The _SV option is visible.");
			} else {
			    System.out.println("The _SV option is NOT visible.");

		}*/


	}



	@FindBy(xpath = "//*[@id=\"alt1_SV\"]")
	WebElement A1_SV;

	public void check_A1_SV_EnableOrDisable(ExtentTest test) {
		Action.scrollByVisibiltyOfElement(driver, A1_SV);
		Action.checkIfCheckboxIsEnabled(A1_SV,"Image 1 _SV", test);
	}

	public void ClickonA1_SV() {
		 try {
		        Action.scrollByVisibiltyOfElement(driver, A1_SV);

		        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		        wait.until(ExpectedConditions.elementToBeClickable(A1_SV));

		        if (!A1_SV.isSelected()) {
		            A1_SV.click();
		            System.out.println("✅ A1_SV clicked.");
		        } else {
		            System.out.println("ℹ️ A1_SV is already selected.");
		        }
		    } catch (Exception e) {
		        System.out.println("❌ Error while clicking A1_SV: " + e.getMessage());
		    }

	}


	@FindBy(xpath = "//*[@id=\"alt2_SV\"]")
	WebElement A2_SV;

	public void check_A2_SV_EnableOrDisable(ExtentTest test) {
		Action.scrollByVisibiltyOfElement(driver, A2_SV);
		Action.checkIfCheckboxIsEnabled(A2_SV,"Image 2 _SV", test);
	}

	public void ClickonA2_SV() {
		Action.scrollByVisibiltyOfElement(driver, A2_SV);
		if (!A2_SV.isSelected()) {
			A2_SV.click();  // only click if not already selected
		}else {System.out.println("_SV is selected ");}

	}


	@FindBy(xpath = "//*[@id=\"alt3_SV\"]")
	WebElement A3_SV;

	public void check_A3_SV_EnableOrDisable(ExtentTest test) {
		Action.scrollByVisibiltyOfElement(driver, A3_SV);
		Action.checkIfCheckboxIsEnabled(A3_SV,"Image 3 _SV", test);
	}
	public void ClickonA3_SV() {
		try {
	        Action.scrollByVisibiltyOfElement(driver, A3_SV);
	        
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.elementToBeClickable(A3_SV));

	        if (!A3_SV.isSelected()) {
	            A3_SV.click();
	            System.out.println("✅ A3_SV clicked.");
	        } else {
	            System.out.println("ℹ️ A3_SV is already selected.");
	        }
	    } catch (Exception e) {
	        System.out.println("❌ Error while clicking A3_SV: " + e.getMessage());
	    }

	}


	@FindBy(xpath = "//*[@id=\"alt4_SV\"]")
	WebElement A4_SV;
	public void check_A4_SV_EnableOrDisable(ExtentTest test) {
		Action.scrollByVisibiltyOfElement(driver, A4_SV);
		Action.checkIfCheckboxIsEnabled(A4_SV,"Image 4 _SV", test);
	}

	public void ClickonA4_SV() {
		try {
	        Action.scrollByVisibiltyOfElement(driver, A4_SV);
	        
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.elementToBeClickable(A4_SV));

	        if (!A4_SV.isSelected()) {
	            A4_SV.click();
	            System.out.println("✅ A4_SV clicked.");
	        } else {
	            System.out.println("ℹ️ A4_SV is already selected.");
	        }
	    } catch (Exception e) {
	        System.out.println("❌ Error while clicking A4_SV: " + e.getMessage());
	    }

	}


	@FindBy(xpath = "//*[@id=\"alt5_SV\"]")
	WebElement A5_SV;

	public void check_A5_SV_EnableOrDisable(ExtentTest test) {
		Action.scrollByVisibiltyOfElement(driver, A5_SV);
		Action.checkIfCheckboxIsEnabled(A5_SV,"Image 5 _SV", test);
	}
	public void ClickonA5_SV() {
		try {
	        Action.scrollByVisibiltyOfElement(driver, A5_SV);
	        
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	        wait.until(ExpectedConditions.elementToBeClickable(A5_SV));

	        if (!A5_SV.isSelected()) {
	            A5_SV.click();
	            System.out.println("✅ A5_SV clicked.");
	        } else {
	            System.out.println("ℹ️ A5_SV is already selected.");
	        }
	    } catch (Exception e) {
	        System.out.println("❌ Error while clicking A5_SV: " + e.getMessage());
	    }

	}

	// =====================================================Alternate _12TV ============================================

	@FindBy(xpath = "(//b[normalize-space()='_12TV'])[1]")
	WebElement _12TV;

	public void check_12TV_VisibleorEnabled(ExtentTest test) {
		Action.scrollByVisibiltyOfElement(driver, _12TV);
		Action.isElementVisibleAndEnabled(driver, _12TV, test, "_12TV is visible");
		/*if(Action.isElementVisibleAndEnabled(driver, _12TV, test))
		{
			System.out.println("The _12TV option is visible.");
			} else {
			    System.out.println("The _12TV option is NOT visible.");

		}*/


	}



	@FindBy(xpath = "//*[@id=\"alt1_12TV\"]")
	WebElement A1_12TV;

	public void check_A1_12TV_EnableOrDisable(ExtentTest test) {
		Action.scrollByVisibiltyOfElement(driver, A1_12TV);
		Action.checkIfCheckboxIsEnabled(A1_12TV,"Image 1 _12TV", test);
	}
	public void ClickonA1_12TV() {
		Action.scrollByVisibiltyOfElement(driver, A1_12TV);
		if (!A1_12TV.isSelected()) {
			A1_12TV.click();  // only click if not already selected
		}else {System.out.println("A1_12TV is selected ");}

	}

	@FindBy(xpath = "//*[@id=\"alt2_12TV\"]")
	WebElement A2_12TV;
	public void check_A2_12TV_EnableOrDisable(ExtentTest test) {
		Action.scrollByVisibiltyOfElement(driver, A2_12TV);
		Action.checkIfCheckboxIsEnabled(A2_12TV,"Image 2 _12TV", test);
	}

	public void ClickonA2_12TV() {
		Action.scrollByVisibiltyOfElement(driver, A2_12TV);
		if (!A2_12TV.isSelected()) {
			A2_12TV.click();  // only click if not already selected
		}else {System.out.println("A2_12TV is selected ");}

	}

	@FindBy(xpath = "//*[@id=\"alt3_12TV\"]")
	WebElement A3_12TV;

	public void check_A3_12TV_EnableOrDisable(ExtentTest test) {
		Action.scrollByVisibiltyOfElement(driver, A3_12TV);
		Action.checkIfCheckboxIsEnabled(A3_12TV,"Image 3 _12TV", test);
	}
	public void ClickonA3_12TV() {
		Action.scrollByVisibiltyOfElement(driver, A3_12TV);
		if (!A3_12TV.isSelected()) {
			A3_12TV.click();  // only click if not already selected
		}else {System.out.println("A3_12TV is selected ");}

	}
	@FindBy(xpath = "//*[@id=\"alt4_12TV\"]")
	WebElement A4_12TV;

	public void check_A4_12TV_EnableOrDisable(ExtentTest test) {
		Action.scrollByVisibiltyOfElement(driver, A4_12TV);
		Action.checkIfCheckboxIsEnabled(A4_12TV,"Image 4 _12TV", test);
	}
	public void ClickonA4_12TV() {
		Action.scrollByVisibiltyOfElement(driver, A4_12TV);
		if (!A4_12TV.isSelected()) {
			A4_12TV.click();  // only click if not already selected
		}else {System.out.println("A4_12TV is selected ");}

	}
	@FindBy(xpath = "//*[@id=\"alt5_12TV\"]")
	WebElement A5_12TV;

	public void check_A5_12TV_EnableOrDisable(ExtentTest test) {
		Action.scrollByVisibiltyOfElement(driver, A5_12TV);
		Action.checkIfCheckboxIsEnabled(A5_12TV,"Image 5 _12TV", test);
	}
	public void ClickonA5_12TV() {
		Action.scrollByVisibiltyOfElement(driver, A5_12TV);
		if (!A5_12TV.isSelected()) {
			A5_12TV.click();  // only click if not already selected
		}else {System.out.println("A5_12TV is selected ");}

	}

	//=============================Job Name*==============================================================
	@FindBy(xpath = "//*[@name='ordername_alias']")
	WebElement jobName;

	public void check_jobName() {
		Action.scrollByVisibiltyOfElement(driver, jobName);

		System.out.println(jobName.getText());


	}



	//---------------------------------------------- Background Temp-----------------------------------------------------

	@FindBy(xpath = "//*[@id='bgsinglecheck_s']")
	WebElement STemp;


	public void check_STemp_selectedOrNot(ExtentTest test) {
		Action.scrollByVisibiltyOfElement(driver, STemp);
		Action.isSelected(driver, STemp, test, "Single Template ");
		STemp.getText();
	}

	public void check_STemp_EnableOrDisable(ExtentTest test) {
		Action.scrollByVisibiltyOfElement(driver, STemp);
		Action.checkIfCheckboxIsEnabled(STemp, "Standar Background temp", test);
	}
	public void ClickonSingleTemp() {
		try {
	        Action.scrollByVisibiltyOfElement(driver, STemp);

	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	        wait.until(ExpectedConditions.elementToBeClickable(STemp));

	        Action.click(driver, STemp);
	        System.out.println("✅ Single Template (STemp) clicked successfully.");
	    } catch (Exception e) {
	        System.out.println("❌ Error while clicking Single Template (STemp): " + e.getMessage());
	    }


	}

	@FindBy(xpath = "//*[@name='bcktemplete']")
	WebElement selectionOfTemp;

	public void dropDownOptionIsVisibleOrNot(ExtentTest test) {
		Action.scrollByVisibiltyOfElement(driver, selectionOfTemp);
		Action.isDisplayed(driver, selectionOfTemp, test);
	}


	public void ClikOnTempDropDown(ExtentTest test)
	{
		try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

	        // Wait until the dropdown is visible
	        wait.until(ExpectedConditions.visibilityOf(selectionOfTemp));
	        wait.until(ExpectedConditions.elementToBeClickable(selectionOfTemp));

	        // Scroll to the dropdown element
	        Action.scrollByVisibiltyOfElement(driver, selectionOfTemp);

	        // Try selecting value "611"
	        Select select = new Select(selectionOfTemp);
	        select.selectByValue("611");

	        // Get the selected option and log it
	        String selectedText = select.getFirstSelectedOption().getText();
	        test.pass("✅ Selected template: " + selectedText + " (Value: 611)");

	    } catch (NoSuchElementException e) {
	        test.fail("❌ Option with value '611' not found in dropdown.");
	    } catch (TimeoutException e) {
	        test.fail("❌ Template dropdown not visible/clickable in expected time.");
	    } catch (Exception e) {
	        test.fail("❌ Exception while selecting template: " + e.getMessage());
	    }
	}

	public void ClickOnSingleTempforTraditional(ExtentTest test)
	{
		Action.scrollByVisibiltyOfElement(driver, selectionOfTemp);
		Action.selectByValue(selectionOfTemp, "818", test);
		System.out.println(Action.selectByValue(selectionOfTemp, "818", test));
	}

	public void ClickOnSingleTempforTraditionalPlus(ExtentTest test)
	{
		Action.scrollByVisibiltyOfElement(driver, selectionOfTemp);
		Action.selectByValue(selectionOfTemp, "611", test);
		System.out.println(Action.selectByValue(selectionOfTemp, "611", test));
	}



	//============================Different template ========================================


	@FindBy(xpath = "//*[@id='bgsinglecheck_m']")
	WebElement differentTemp;

	public void check_DTemp_selectedOrNot(ExtentTest test) {
		Action.scrollByVisibiltyOfElement(driver, differentTemp);
		Action.isSelected(driver, differentTemp, test, " Different Template");
	}

	public void check_differentTemp_EnableOrDisable(ExtentTest test) {
		Action.scrollByVisibiltyOfElement(driver, differentTemp);
		Action.checkIfCheckboxIsEnabled(differentTemp, "Diff temp", test);
	}

	public void clcikonDiffTem()

	{
		Action.scrollByVisibiltyOfElement(driver, differentTemp);
		Action.click(driver, differentTemp);
	}

	@FindBy(xpath = "(//select[@id='bcktemplete'])[2]")
	WebElement selectTeamtemp1;

	public void selectDiffTempForTeam1(ExtentTest test) {
		//Action.scrollByVisibiltyOfElement(driver, selectTeamtemp1);
		//Action.click(driver, selectTeamtemp1);
		Action.selectByVisibleText("Test_BG_Dropin Temp_A", selectTeamtemp1);
		//Action.selectByValue(selectTeamtemp1, "487");
		System.out.println(Action.selectByValue(selectionOfTemp, "487", test));
	}


	@FindBy(xpath = "(//select[@id='bcktemplete'])[3]")
	WebElement selectTeamtemp2;

	public void selectDiffTempForTeam2(ExtentTest test) {
		//Action.scrollByVisibiltyOfElement(driver, selectTeamtemp2);
		//Action.click(driver, selectTeamtemp2);
		Action.selectByVisibleText("Treditional temp II", selectTeamtemp2);
		//Action.selectByValue(selectTeamtemp2, "611");
		System.out.println(Action.selectByValue(selectionOfTemp, "611", test));
	}


	@FindBy(xpath = "//*[@class='popupclosed btn btn-success btn-action']")
	WebElement saveBTN;

	public void clickOnSaveButton() {
		//Action.scrollByVisibiltyOfElement(driver, saveBTN);
		Action.click(driver, saveBTN);
	}


	@FindBy(xpath = "//*[@class='imran btn btn-success btn-action']")
	WebElement cancleBTN;

	public void clickOnCancleButton() {
		Action.scrollByVisibiltyOfElement(driver, cancleBTN);
		Action.click(driver, cancleBTN);
	}


	@FindBy(xpath = "//*[@aria-labelledby='ui-id-2']")
	WebElement popdisplay;

	public void clickPopUpwindow(ExtentTest test) {
		Action.scrollByVisibiltyOfElement(driver, popdisplay);
		Action.isDisplayed(driver, popdisplay, test);
	}

	//=============================== Custom Background=========================================================

	@FindBy(xpath = "//*[@id='bgsinglecheck_g']")
	WebElement customBG;

	public void check_customBG_selectedOrNot(ExtentTest test) {
		Action.scrollByVisibiltyOfElement(driver, customBG);
		Action.isSelected(driver, customBG, test, "Custom Background ");
	}


	//==============================UPLOAD TEAM PHOTOS===============================================

	@FindBy(xpath = "//*[@id='upload_tp_files']")
	WebElement uploadTeamsPhoto;

	public void check_uploadTeamsPhoto_EnableOrDisable(ExtentTest test) {
		Action.scrollByVisibiltyOfElement(driver, uploadTeamsPhoto);
		Action.checkIfCheckboxIsEnabled(uploadTeamsPhoto, "Upload  TM photo", test);
	}

	public void clickOnUploadTeamsPhotos(String[] filePath)
	{
		Action.scrollByVisibiltyOfElement(driver, uploadTeamsPhoto);
		Action.uploadMultipleFiles(uploadTeamsPhoto, filePath);
	}


	@FindBy(xpath = "//*[@id='upload_tp_filesBtn']")
	WebElement uploadTeamPhotoBtn;

	public void clickOnuploadTeamPhotoBtn()
	{
		Action.click(driver, uploadTeamPhotoBtn);
	}


	@FindBy(xpath = "(//select[@name='upload_photo_team[]'])[1]")
	WebElement selectTeamName1;
	public void selectTeamName1()
	{
		Action.scrollByVisibiltyOfElement(driver, selectTeamName1);
		Action.selectByIndex(selectTeamName1, 1);
	}

	@FindBy(xpath = "(//select[@name='upload_photo_team[]'])[2]")
	WebElement selectTeamName2;
	public void selectTeamName2()
	{
		Action.scrollByVisibiltyOfElement(driver, selectTeamName2);
		Action.selectByIndex(selectTeamName2, 2);
	}

	@FindBy(xpath = "(//select[@id='upload_photo_team_id'])[3]")
	WebElement selectTeamName3;
	public void selectTeamName3()
	{
		Action.scrollByVisibiltyOfElement(driver, selectTeamName3);
		Action.selectByIndex(selectTeamName3, 3);
	}



	@FindBy(xpath = "//*[@onclick='submitData_forTeamUpload()']")
	WebElement saveTeamImage;
	public void clickOnSaveTeam()
	{
		Action.scrollByVisibiltyOfElement(driver, saveTeamImage);
		Action.click(driver, saveTeamImage);
	}


	//====================================Traditional Plus=============================================================

	@FindBy(xpath = "//*[@id=\"bgsinglecheck_m\"]")
	WebElement DTemp;

	// =========================================Extracted images=============================================================
	@FindBy(xpath = "//*[@id=\"extractedimages\"]")
	WebElement extractImage;


	public void check_ExtractedImage_isClickable(ExtentTest test) {
		Action.scrollByVisibiltyOfElement(driver, extractImage);
		Action.isElementClickable(driver, extractImage, test);
	}

	public void check_extractImage_EnableOrDisable(ExtentTest test) {
		Action.scrollByVisibiltyOfElement(driver, extractImage);
		Action.checkIfCheckboxIsEnabled(extractImage, "Extract Image", test);
	}

	public void clickOnextractImage()
	{
		try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

	        // Wait until the element is visible
	        wait.until(ExpectedConditions.visibilityOf(extractImage));

	        // Scroll to the element (optional but useful for Firefox)
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", extractImage);

	        // Wait until the element is clickable
	        wait.until(ExpectedConditions.elementToBeClickable(extractImage));

	        // Click the element
	        extractImage.click();

	    } catch (Exception e) {
	        System.out.println("❌ Failed to click on 'Extract Image': " + e.getMessage());
	    }
	}

	@FindBy(xpath = "//*[@id=\"extractedimagesT\"]")
	WebElement team;

	public void extractTeamoptionIsVisibleOrNot(ExtentTest test) {
		Action.scrollByVisibiltyOfElement(driver, team);
		Action.isElementVisibleAndEnabled(driver, team, test,"Team option is visible on click of Extract image ");
	}


	public void clickOnextractTeamImage(ExtentTest test)
	{

		Action.scrollByVisibiltyOfElement(driver, team);
		Action.isDisplayed(driver, team, test);
		if (!team.isSelected()) {
			team.click();  // only click if not already selected
		}else {System.out.println("team is selected ");}
		Action.click(driver, team);
	}


	@FindBy(xpath = "//*[@id=\"extractedimagesI\"]")
	WebElement ind;

	
	public void extractIndividualoptionIsVisibleOrNot(ExtentTest test) {
		Action.scrollByVisibiltyOfElement(driver, ind);
		Action.isElementVisibleAndEnabled(driver, ind, test,"Individual option is visible on click of Extract image ");
	}
	
	public void extractIndividualoptionIsSelectedOrNot(ExtentTest test) {
		Action.scrollByVisibiltyOfElement(driver, ind);
		Action.isSelected(driver, ind, test, "Individual Option is bydefault Selected");
	}
	
	public void clickOnextractIndividualImage(ExtentTest test)
	{
		 try {
		        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		        // Wait until the element is visible
		        wait.until(ExpectedConditions.visibilityOf(ind));
		        Action.isDisplayed(driver, ind, test);

		        // Scroll to the element using JS (in case it's out of viewport)
		        JavascriptExecutor js = (JavascriptExecutor) driver;
		        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", ind);

		        // Wait until the element is clickable
		        wait.until(ExpectedConditions.elementToBeClickable(ind));

		        if (!ind.isSelected()) {
		            ind.click();  // Click only if not already selected
		            test.pass("✅ 'Extract Individual Image' option selected.");
		        } else {
		            test.info("ℹ️ 'Extract Individual Image' was already selected.");
		        }

		    } catch (Exception e) {
		        test.fail("❌ Failed to click on 'Extract Individual Image': " + e.getMessage());
		        System.out.println("❌ Exception in clickOnextractIndividualImage: " + e.getMessage());
		    }
	}


	@FindBy(xpath = "//*[@id=\"extractedimagesB\"]")
	WebElement both;
	
	public void extractBothoptionIsVisibleOrNot(ExtentTest test) {
		Action.scrollByVisibiltyOfElement(driver, both);
		Action.isElementVisibleAndEnabled(driver, both, test,"Both option is visible on click of Extract image ");
	}
	
	public void clickOnextractBothlImage(ExtentTest test)
	{
		Action.isDisplayed(driver, both, test);
		if (!both.isSelected()) {
			both.click();  // only click if not already selected
		}else {System.out.println("both is selected ");}
		Action.click(driver, both);
	}




	// ========================================================PNG Crop===============================================================

	
	@FindBy(xpath = "//*[@id='cropimages_display_main']")
	WebElement PNGcrop;

	
	public void check_PNGcrop_VisibleOrNot(ExtentTest test) {
		Action.scrollByVisibiltyOfElement(driver, PNGcrop);
		Action.isElementVisibleAndEnabled(driver, PNGcrop, test, "PNG Crop option is visible on clcik of Extract Imgae option.");
	}
	
	
	
	
	@FindBy(xpath = "//*[@id=\"cropimageshalf\"]")
	WebElement crop;

	
	public void click_ON_3_4_Crop(ExtentTest test) {
		try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

	        // Wait for the crop element to be visible
	        wait.until(ExpectedConditions.visibilityOf(crop));

	        // Scroll to the crop element to ensure it's in the viewport
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", crop);

	        // Wait for the crop element to be clickable
	        wait.until(ExpectedConditions.elementToBeClickable(crop));

	        // Click on the crop element
	        crop.click();
	        test.pass("✅ '3/4 Crop' option selected successfully.");

	    } catch (Exception e) {
	        test.fail("❌ Failed to select '3/4 Crop' option: " + e.getMessage());
	        System.out.println("❌ Exception in click_ON_3_4_Crop: " + e.getMessage());
	    }
	}
	
	
	public void check_crop_EnableOrDisable(ExtentTest test) {
		Action.scrollByVisibiltyOfElement(driver, crop);
		Action.checkIfCheckboxIsEnabled(crop, "PNG CROP", test);
	}

	public void clickOnpngCrop(ExtentTest test)
	{
		Action.isDisplayed(driver, crop, test);
		if (!crop.isSelected()) {
			crop.click();  // only click if not already selected
		}else {System.out.println("crop is selected ");}
		Action.click(driver, crop);
	}
	
	@FindBy(xpath = "//*[@id='showcropimageprice']")
	WebElement cropPrice;
	public void printCropPrice(ExtentTest test)
	{
		Action.scrollByVisibiltyOfElement(driver, cropPrice);
		test.pass(cropPrice.getText());
		}
	


	@FindBy(xpath = "//*[@id=\"cropimagesfull\"]")
	WebElement fullLengthCentering;

	
	
	public void check_fullLengthCenter_EnableOrDisable(ExtentTest test) {
		Action.scrollByVisibiltyOfElement(driver, fullLengthCentering);
		Action.checkIfCheckboxIsEnabled(fullLengthCentering, "Full Length Centering", test);
	}
	
	public void clickOnfullLengthCentering(ExtentTest test)
	{
		Action.isDisplayed(driver, fullLengthCentering, test);
		if (!fullLengthCentering.isSelected()) {
			fullLengthCentering.click();  // only click if not already selected
		}else {System.out.println("fullLengthCentering is selected ");}
		//Action.click(driver, fullLengthCentering);
	}

	@FindBy(xpath = "//*[@id=\"cropimagesC\"]")
	WebElement centered;

	public void check_centered_EnableOrDisable(ExtentTest test) {
		Action.scrollByVisibiltyOfElement(driver, centered);
		Action.checkIfCheckboxIsEnabled(centered, "centered", test);
	}
	
	
	public void clickOncentered(ExtentTest test)
	{
		Action.isDisplayed(driver, centered, test);
		if (!centered.isSelected()) {
			centered.click();  // only click if not already selected
		}else {System.out.println("centered is selected ");}
		Action.click(driver, centered);
	}


	@FindBy(xpath = "//*[@id=\"cropimagesT\"]")
	WebElement topWeighted;
	
	public void check_topWeighted_EnableOrDisable(ExtentTest test) {
		Action.scrollByVisibiltyOfElement(driver, topWeighted);
		Action.checkIfCheckboxIsEnabled(topWeighted, "Top Weighted", test);
	}
	
	public void clickOntopWeighted(ExtentTest test)
	{
		Action.isDisplayed(driver, topWeighted, test);
		if (!topWeighted.isSelected()) {
			topWeighted.click();  // only click if not already selected
		}else {System.out.println("topWeighted is selected ");}
		Action.click(driver, topWeighted);
	}


	@FindBy(xpath = "//*[@id=\"cropimagesB\"]")
	WebElement bottomWeighted;
	
	public void check_bottomWeighted_EnableOrDisable(ExtentTest test) {
		Action.scrollByVisibiltyOfElement(driver, bottomWeighted);
		Action.checkIfCheckboxIsEnabled(bottomWeighted, "Bottom Weighted", test);
	}
	
	public void clickOnbottomWeighted(ExtentTest test)
	{
		Action.isDisplayed(driver, bottomWeighted, test);
		if (!bottomWeighted.isSelected()) {
			bottomWeighted.click();  // only click if not already selected
		}else {System.out.println("bottomWeighted is selected ");}
		Action.click(driver, bottomWeighted);
	}


	@FindBy(xpath = "//*[@id=\"cropimagesno\"]")
	WebElement noCrop;

	public void clickOnnoCrop(ExtentTest test)
	{
		Action.isDisplayed(driver, noCrop, test);
		if (!noCrop.isSelected()) {
			noCrop.click();  // only click if not already selected
		}else {System.out.println("noCrop is selected ");}
		Action.click(driver, noCrop);
	}

	//======PNG Crop option validation message==============================
	
	
	@FindBy(xpath = "//*[@id='msg']")
	WebElement msgPNGCrop;

	public void check_ValidationMsg(ExtentTest test) {
		Action.scrollByVisibiltyOfElement(driver, msgPNGCrop);
		//Action.isDisplayed(driver, msgPNGCrop, test);
		test.pass(msgPNGCrop.getText());
	}
	
	
	
	// ===============================PNG Team Add On================================================

	@FindBy(xpath = "//*[@id=\"png_team_add_on\"]")
	WebElement pngTeamAddOn;

	public void check_pngTeamAddOn_EnableOrDisable(ExtentTest test) {
		Action.scrollByVisibiltyOfElement(driver, pngTeamAddOn);
		Action.checkIfCheckboxIsEnabled(pngTeamAddOn, "PNG team Ad on", test);
	}

	

	public void clickOnpngTeamAddOn(ExtentTest test) {
		try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	        // Wait for visibility
	        wait.until(ExpectedConditions.visibilityOf(pngTeamAddOn));

	        // Scroll to the element (centered)
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", pngTeamAddOn);

	        // Wait until clickable
	        wait.until(ExpectedConditions.elementToBeClickable(pngTeamAddOn));

	        // Perform click
	        pngTeamAddOn.click();
	        test.pass("✅ 'PNG Team Add-On' option clicked successfully.");

	    } catch (Exception e) {
	        test.fail("❌ Failed to click 'PNG Team Add-On': " + e.getMessage());
	        System.out.println("❌ Exception in clickOnpngTeamAddOn: " + e.getMessage());
	    }
	}
	
	
	
	public void clickOnpngTeamAddOn1()
	{
		Action.scrollByVisibiltyOfElement(driver, pngTeamAddOn);
		if (!pngTeamAddOn.isSelected()) {
			pngTeamAddOn.click();  // only click if not already selected
		}else {System.out.println("pngTeamAddOn is selected ");}
		Action.click(driver, pngTeamAddOn);
	}


	//================ Unique color for each team in organization=================================

	@FindBy(xpath = "//*[@id=\"teamcolorY\"]")
	WebElement uniqueColor;

	public void check_uniqueColor_EnableOrDisable(ExtentTest test) {
		Action.scrollByVisibiltyOfElement(driver, uniqueColor);
		Action.checkIfCheckboxIsEnabled(uniqueColor, "Unique color", test);
	}

	public void clickOnuniqueColor()
	{
		 try {
		        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		        // Wait for element to be visible
		        wait.until(ExpectedConditions.visibilityOf(uniqueColor));

		        // Scroll into view using JavaScript
		        JavascriptExecutor js = (JavascriptExecutor) driver;
		        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", uniqueColor);

		        // Wait until element is clickable
		        wait.until(ExpectedConditions.elementToBeClickable(uniqueColor));

		        // Click only if not already selected
		        if (!uniqueColor.isSelected()) {
		            uniqueColor.click();
		            System.out.println("✅ uniqueColor was not selected, now clicked.");
		        } else {
		            System.out.println("ℹ️ uniqueColor is already selected.");
		        }

		    } catch (Exception e) {
		        System.out.println("❌ Exception while clicking on uniqueColor: " + e.getMessage());
		    }
	}


	//=========================== Single color for entire organization===========================================

	@FindBy(xpath = "//*[@id=\"teamcolorN\"]")
	WebElement singleColor;

	public void check_singleColor_EnableOrDisable(ExtentTest test) {
		Action.scrollByVisibiltyOfElement(driver, singleColor);
		Action.checkIfCheckboxIsEnabled(singleColor, "Single color", test);
	}

	public void clickOnsingleColor()
	{
		if (!singleColor.isSelected()) {
			A5_12TV.click();  // only click if not already selected
		}else {System.out.println("A5_12TV is selected ");}
		Action.click(driver, singleColor);


	}

	// ================================================Theme Color==================================================

	@FindBy(xpath = "//*[@id=\"themecolor\"]")
	WebElement themeColor;

	public void check_themeColor_EnableOrDisable(ExtentTest test) {
		Action.scrollByVisibiltyOfElement(driver, themeColor);
		Action.checkIfCheckboxIsEnabled(themeColor, "Theme Color", test);
	}

	public void clickOnthemeColor() throws Throwable
	{
		Action.scrollByVisibiltyOfElement(driver, themeColor);
		Action.selectBysendKeys(themeColor, "The is Theme color text box to check check for testing order ");
	}


	// ===============================================Special Requests===============================================

	@FindBy(xpath = "//*[@id=\"spinstructions\"]")
	WebElement specialRequest;

	public void check_specialRequest_EnableOrDisable(ExtentTest test) {
		Action.scrollByVisibiltyOfElement(driver, specialRequest);
		Action.checkIfCheckboxIsEnabled(specialRequest, "Special Request", test);
	}

	public void clickOnspecialRequest()
	{
		Action.click(driver, specialRequest);
	}
	//=========================================== Upload reference files================================================

	@FindBy(xpath = "//*[@id=\"importfiles\"]")
	WebElement uploadReferenceFile;

	public void clickOnuploadReferenceFile(String[] path)
	{
		Action.click(driver, uploadReferenceFile);
		Action.uploadMultipleFiles(uploadReferenceFile, path);
	}


	// =================================================Upload LOGO=========================================================

	@FindBy(xpath = "//*[@id=\"importfiles_logo\"]")
	WebElement uploadlogo;

	public void clickOnuploadlogo()
	{
		Action.click(driver, uploadlogo);
	}

	//================================================ Color Correction=====================================================

	@FindBy(xpath = "//*[@id=\"ccservices\"]")
	WebElement colorCorrection;


	public void clickOncolorCorrection()
	{
		try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	        // Wait for element to be visible
	        wait.until(ExpectedConditions.visibilityOf(colorCorrection));

	        // Scroll into view using JavaScript
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", colorCorrection);

	        // Wait until element is clickable
	        wait.until(ExpectedConditions.elementToBeClickable(colorCorrection));

	        // Click the element
	        colorCorrection.click();
	        System.out.println("✅ colorCorrection clicked successfully.");
	        
	    } catch (Exception e) {
	        System.out.println("❌ Exception while clicking on colorCorrection: " + e.getMessage());
	    }
	}


	//================================================= Discount=============================================================

	@FindBy(xpath = "//*[@id=\"discount_code\"]")
	WebElement dicount;


	public void clickOndicount() throws Throwable
	{
		Action.click(driver, dicount);
		Action.selectBysendKeys(dicount, "100OFF");
	}


	public void clickOn50dicount() throws Throwable
	{
		try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	        // Wait for the discount field to be visible and clickable
	        wait.until(ExpectedConditions.visibilityOf(dicount));
	        wait.until(ExpectedConditions.elementToBeClickable(dicount));

	        // Scroll into view
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", dicount);

	        // Click and send discount code
	        dicount.click();
	        dicount.clear();  // Optional, if field has pre-filled value
	        dicount.sendKeys("50OFF");

	        System.out.println("✅ Discount code '50OFF' entered successfully.");

	    } catch (Exception e) {
	        System.out.println("❌ Failed to enter discount code: " + e.getMessage());
	    }
	}

	@FindBy(xpath = "//*[@id=\"Redeem\"]")
	WebElement redeem;

	public void clickOnredeem()
	{
		try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	        // Wait until the redeem button is visible and clickable
	        wait.until(ExpectedConditions.visibilityOf(redeem));
	        wait.until(ExpectedConditions.elementToBeClickable(redeem));

	        // Scroll into view (optional but helpful)
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", redeem);

	        // Click the button
	        redeem.click();
	        System.out.println("✅ Redeem button clicked successfully.");

	    } catch (Exception e) {
	        System.out.println("❌ Failed to click redeem button: " + e.getMessage());
	    }
	}

	
	
	
	@FindBy(xpath = "//*[@id='msg']")
	WebElement invalidCouponCode;
	
	public String validationCouponCodeMessage() {
		Action.scrollByVisibiltyOfElement(driver, invalidCouponCode);
		return invalidCouponCode.getText().trim();
		
	}
	
	
	
	public String getDiscountErrorMessage() {
        
            
            return invalidCouponCode.getText().trim();
        
    }
	
	//============================================================= Terms========================================================

	@FindBy(xpath = "//*[@id=\"terms\"]")
	WebElement terms;

	public void clickOnterms()
	{
		try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	        // Wait until the checkbox is visible and clickable
	        wait.until(ExpectedConditions.visibilityOf(terms));
	        wait.until(ExpectedConditions.elementToBeClickable(terms));

	        // Scroll to the checkbox
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", terms);

	        // Click the checkbox
	        terms.click();
	        System.out.println("✅ Terms checkbox clicked.");

	    } catch (Exception e) {
	        System.out.println("❌ Failed to click Terms checkbox: " + e.getMessage());
	    }
	}


	// ============================================Select Payment Methods:====================================================

	@FindBy(xpath = "//*[@id=\"existing\"]")
	WebElement useSaveCard;


	public void clickOnuseSaveCard()
	{
		try {
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	        // Wait until the element is visible and clickable
	        wait.until(ExpectedConditions.visibilityOf(useSaveCard));
	        wait.until(ExpectedConditions.elementToBeClickable(useSaveCard));

	        // Scroll into view
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("arguments[0].scrollIntoView({block: 'center'});", useSaveCard);

	        // Click the element
	        useSaveCard.click();
	        System.out.println("✅ 'Use Saved Card' clicked.");

	    } catch (Exception e) {
	        System.out.println("❌ Failed to click 'Use Saved Card': " + e.getMessage());
	    }
	}


	@FindBy(xpath = "//*[@id='vaultid123']")
	WebElement selectCard;
	public void ClikOnCardNumber(ExtentTest test)
	{
		//Action.click(driver, selectionOfTemp);
		Action.selectByValue(selectCard, "CARD-5SA93463JU244373UM32TCDY", test);
		System.out.println(Action.selectByValue(selectCard, "CARD-5SA93463JU244373UM32TCDY", test));
	}



	// =====================================================Enter Card Info============================================================

	@FindBy(xpath = "//*[@id=\"cardinfo\"]")
	WebElement enterCardInfo;
	public void clickOnEnterCardInfo() {
		Action.click(driver, enterCardInfo);
	}


	@FindBy(xpath = "//*[@id=\"cardnumber1\"]")
	WebElement enterCardNumber;

	public void enterCardNumber() {
		String CardNumber=BaseClass.getCardNumber();
		enterCardNumber.sendKeys(CardNumber);		
	}
	
	@FindBy(xpath = "//*[@id=\"month1\"]")
	WebElement month;
	public void selectMonth(ExtentTest test)
	{
		Action.selectByValue(month, "02", test);
	}

	@FindBy(xpath = "//*[@id=\"year1\"]")
	WebElement year;
	public void selectYear(ExtentTest test)
	{
		Action.selectByValue(year, "2026", test);
	}

	@FindBy(xpath = "//*[@id=\"cvv1\"]")
	WebElement cvv;
	public void enterCVV() {
		String cvv1=BaseClass.getCVV();
		cvv.sendKeys(cvv1);
	}

	@FindBy(xpath = "//*[@id=\"name_on_card_first\"]")
	WebElement firstName;
	public void enterfirstName() {
		String Fname=BaseClass.getFirstName();
		firstName.sendKeys(Fname);
	}

	@FindBy(xpath = "//*[@id=\"name_on_card_last\"]")
	WebElement lastName;
	public void enterLastname() {
		String lname=BaseClass.getLastName();
		lastName.sendKeys(lname);
	}


	@FindBy(xpath = "//*[@id=\"cardtype\"]")
	WebElement cardType;

	public void clickOncardType(ExtentTest test)
	{
		Action.selectByValue(cardType, "visa", test);
	}


	@FindBy(xpath = "//*[@id=\"savedetail\"]")
	WebElement saveMycardDetails;

	public void clickOnsaveMycardDetails()
	{
		Action.click(driver, saveMycardDetails);
	}

	@FindBy(xpath = "//*[@id='msg']")
	WebElement validationMessageEnterCardNumber;
	public String getValidationMessage() {
	    try {
	        return validationMessageEnterCardNumber.getText().trim();
	    } catch (Exception e) {
	        return null;
	    }
	}
	
	//================================================================ Checkout========================================

	@FindBy(xpath = "//*[@id=\"btnpaynow\"]")
	WebElement checkOut;

	public boolean clickOncheckOut()
	{
		Action.waitForElementVisible(driver, checkOut, 1000);
		Action.scrollByVisibiltyOfElement(driver, checkOut);
		Action.click(driver, checkOut);
		return true ;
	}


	public void ClickonFc() {
		Action.scrollByVisibiltyOfElement(driver, fc);
		Action.click(driver, fc);

	}

	public boolean validLogo(ExtentTest test) {
		return Action.isDisplayed(driver, logo, test);
	}

	public String getGpNgenTitle() {
		String title = driver.getTitle();
		return title;

	}
	
	
	 // =========================Validation MESSAGE  Section ==============================================

    @FindBy(xpath = "//*[@id='msg']")
    WebElement validationMessage;

    public String getValidationMessageText() {
        try {
            return validationMessage.getText().trim();
        } catch (Exception e) {
            return null;
        }
    }

    public void validateExpectedMessage(String expectedMessage, ExtentTest test) {
        String actualMessage = getValidationMessageText();
        if (actualMessage != null && actualMessage.equalsIgnoreCase(expectedMessage)) {
            test.pass("Validation displayed: " + actualMessage);
        } else {
            test.fail("Expected: '" + expectedMessage + "', but found: '" + actualMessage + "'");
        }
    }

    // Validate all card fields sequentially
    public void validateAllCardFieldsOneByOne(ExtentTest test) throws InterruptedException {
        String[] expectedMessages = {
            "Please enter card number.",
            "Please select month.",
            "Please select year.",
            "Please enter CVV/CVV2.",
            "Please enter first name.",
            "Please enter last name."
        };

        for (String msg : expectedMessages) {
        	clickOncheckOut(); // try to trigger validation
            Thread.sleep(1000); // wait for message
            validateExpectedMessage(msg, test);

            // After each message, fill that field
            switch (msg) {
                case "Card Number is required":
                    enterCardNumber();
                    break;
                case "Expiry month is required":
                    selectMonth(test);
                    break;
                case "Expiry year is required":
                    selectYear(test);
                    break;
                case "CVV is required":
                    enterCVV();
                    break;
                case "First name is required":
                	enterfirstName();
                    break;
                case "Last name is required":
                	enterLastname();
                    break;
            }
        }
    }
	
	


	//=======================================Wrong Card details validation message =========================================
	
	@FindBy(xpath = "//*[@xpath='1']")
	WebElement validCreditCard;

	public String validCreditCardMSG()
	{

		Action.scrollByVisibiltyOfElement(driver, validCreditCard);
		return validCreditCard.getText();
	}
	
	
	//========================================Team Price webelement =========================================

	@FindBy(xpath = "//*[@id='teamonlycost1']")
	WebElement tmAmountSTD;

	public String checkTeamPriceForStandard()
	{

		Action.scrollByVisibiltyOfElement(driver, tmAmountSTD);
		return tmAmountSTD.getText();
	}
	//========================================Total Number of individual images   Count-=========================================

	@FindBy(xpath = "//tbody/tr[34]/td[3]/span[1]")
	WebElement totalNumberOfIndividualImg;

	public String totalNumbOfIndividualImg()
	{

		Action.scrollByVisibiltyOfElement(driver, totalNumberOfIndividualImg);
		return totalNumberOfIndividualImg.getText();
	}

	
	//=====================================Total Number of Team Images   Count===========================
	@FindBy(xpath = "//*[@id='setteamonly1']")
	WebElement totalNumberOfTeamImg;

	public String totalNumbOfTeamImg()
	{

		Action.scrollByVisibiltyOfElement(driver, totalNumberOfTeamImg);
		return totalNumberOfTeamImg.getText();
	}
	
	
	

	//=====================================Total Bundle Sub total Count===========================
	@FindBy(xpath = "//*[@id='bundlesSubTotal1']")
	WebElement totalBundleSubTotal;

	public String printtotalBundleSubTotal()
	{

		Action.scrollByVisibiltyOfElement(driver, totalBundleSubTotal);
		return totalBundleSubTotal.getText();
	}
	
	
	
	//=====================================Total Bundle Additional graphics total===========================
		@FindBy(xpath = "//*[@id='bundle_AddinalTotal_amt_span']")
		WebElement bundle_AddinalTotal_amt;

		public String printbundle_AddinalTotal_amt()
		{

			Action.scrollByVisibiltyOfElement(driver, bundle_AddinalTotal_amt);
			return bundle_AddinalTotal_amt.getText();
		}
	
		
		
		//===================================== Color Correction total===========================
				@FindBy(xpath = "//*[@id='showcc']")
				WebElement colorCorrectionTotal;

				public String printColorCorrectionTotal()
				{

					Action.scrollByVisibiltyOfElement(driver, colorCorrectionTotal);
					return colorCorrectionTotal.getText();
				}
				
	
	//========================================Sub Total-=========================================

	@FindBy(xpath = "//*[@id='subtotal1']")
	WebElement subTotal;

	public String printSubTotal()
	{

		Action.scrollByVisibiltyOfElement(driver, subTotal);
		return subTotal.getText();
	}
	
	
	
	//========================================Discount Amount -=========================================

		@FindBy(xpath = "//*[@id='disctotl']")
		WebElement discountAmount;

		public String printDiscountl()
		{

			Action.scrollByVisibiltyOfElement(driver, discountAmount);
			return discountAmount.getText();
		}
	
		
		
		
		
		//========================================Final Total Amount -=========================================

				@FindBy(xpath = "//*[@id='finaltotl']")
				WebElement finalAmount;

				public String printFinalAmount()
				{

					Action.scrollByVisibiltyOfElement(driver, finalAmount);
					return finalAmount.getText();
				}
	
	//========================================Total Number of MM graphics   Count-=========================================

		@FindBy(xpath = "//*[@id='_MM_span']")
		WebElement countOfMM;

		public String count_Of_MM()
		{

			Action.scrollByVisibiltyOfElement(driver, countOfMM);
			return countOfMM.getText();
		}


	//========================================Total Number of _45V graphics   Count-=========================================

	@FindBy(xpath = "//*[@id='_45V_span']")
	WebElement countOf45V;

	public String count_Of_45V()
	{

		Action.scrollByVisibiltyOfElement(driver, countOf45V);
		return countOf45V.getText();
	}

	//========================================Total Number of _45V graphics   Count-=========================================

	@FindBy(xpath = "//*[@id='_45TV_span']")
	WebElement countOf45TV;

	public String count_Of_45TV()
	{

		Action.scrollByVisibiltyOfElement(driver, countOf45TV);
		return countOf45TV.getText();
	}

	//========================================Total Number of _45V graphics   Count-=========================================

	@FindBy(xpath = "//*[@id='_12TV_span']")
	WebElement countOf12TV;

	public String count_Of_12TV()
	{

		Action.scrollByVisibiltyOfElement(driver, countOf12TV);
		return countOf12TV.getText();
	}

	//========================================Total Number of Alternate graphics   Count-=========================================

	@FindBy(xpath = "//*[@id='total_alt_buld_cunt_span']")
	WebElement totalcountOfALT;

	public String total_count_Of_ALT()
	{

		Action.scrollByVisibiltyOfElement(driver, totalcountOfALT);
		return totalcountOfALT.getText();
	}
	//========================================Total Price of Alternate graphics   Count-=========================================

	@FindBy(xpath = "//*[@id='alt_graphic_price_total_span']")
	WebElement totalPriceOfALT;

	public String total_Price_f_ALT()
	{

		Action.scrollByVisibiltyOfElement(driver, totalPriceOfALT);
		return totalPriceOfALT.getText();
	}

	//====Change confirmation pop-up --Select FC, make changes, then select Traditional job Message: "This page is asking you to confirm..."

	@FindBy(xpath = "//*[@class='bootbox-body']")
	WebElement confirmPopup;

	public String confirmPopupMessageAfterChangeOption()
	{

		Action.scrollByVisibiltyOfElement(driver, confirmPopup);
		return confirmPopup.getText();

	}

	@FindBy(xpath = "//*[@data-bb-handler='cancel']")
	WebElement cancle;

	public void cancleButton()
	{

		Action.scrollByVisibiltyOfElement(driver, cancle);
		Action.click(driver, cancle);
	}


	@FindBy(xpath = "//*[@data-bb-handler='confirm']")
	WebElement confirm;

	public void confirmButton()
	{

		Action.scrollByVisibiltyOfElement(driver, confirm);
		Action.click(driver, confirm);
	}




}
