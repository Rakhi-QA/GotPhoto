package com.GpNgen.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.GpNgen.ActionDriver.Action;
import com.GpNgen.Base.BaseClass;
import com.aventstack.extentreports.ExtentTest;

public class AdminPage extends BaseClass
{
	WebDriver driver;

	public AdminPage(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//=======================Login Page==================================================
	@FindBy(xpath = "//*[@type='text']")
	WebElement emailID;

	public void enterEmailID(String email) {
		Action.scrollByVisibiltyOfElement(driver, emailID);
		emailID.sendKeys(email);
	}

	//========== Password============

	@FindBy(xpath = "//*[@id='password']")
	WebElement password;

	public void enterPassword(String pwd) {
		Action.scrollByVisibiltyOfElement(driver, password);
		password.sendKeys(pwd);
	}

	//==========Login button============

	@FindBy(xpath = "//*[@name='login']")
	WebElement login;

	public void clickOnLogin() {
		Action.scrollByVisibiltyOfElement(driver, login);
		login.click();
	}
	//================================Validation message==========================================
	@FindBy(xpath = "//*[@class='content']")
	WebElement validMessage;

	public void validMessageDisplay() {
		Action.scrollByVisibiltyOfElement(driver, validMessage);
		validMessage.click();
	}
	//================================Validation message==========================================

	@FindBy(xpath = "//*[@class='content']")  // update with your actual locator
	WebElement errorMessage;

	public String getLoginErrorMessage() {
		return errorMessage.getText();
	}


	//========================Body======================================

	@FindBy(xpath = "//*[@id='content']")
	WebElement body;

	public void clickOnbody() {
		Action.scrollByVisibiltyOfElement(driver, body);
		body.click();
	}


	//========================Setting======================================

	@FindBy(xpath = "//a[@href='/Settings/editadmin']")
	WebElement setting;

	public void clickOnsetting() {
		Action.scrollByVisibiltyOfElement(driver, setting);
		setting.click();
	}



	//========================Background Template======================================

	@FindBy(xpath = "//a[@href='/Settings/backgroundtemplate']")
	WebElement bg;

	public void clickOnBackgroudTemp() {
		Action.scrollByVisibiltyOfElement(driver, bg);
		bg.click();
	}



	//========================Background Template Add======================================		


	@FindBy(xpath = "(//img[@title='Add New'])[1]")
	WebElement add;

	public void clickOnAdd() {
		Action.scrollByVisibiltyOfElement(driver, add);
		add.click();
	}

	//========================Background Template Edit======================================	

	@FindBy(xpath = "(//img[@title='Edit'])[1]")
	WebElement edit;

	public void clickOnEdit() {
		Action.scrollByVisibiltyOfElement(driver, edit);
		edit.click();
	}


	//========================Background Template Delete======================================	

	@FindBy(xpath = "//body[1]/div[3]/div[1]/div[1]/div[1]/form[1]/div[2]/table[1]/tbody[1]/tr[1]/td[3]/a[3]/img[1]")
	WebElement delete;

	public void clickOnDelete() {
		Action.scrollByVisibiltyOfElement(driver, delete);
		delete.click();
	}



	//========================Background Template Apply to ALL Image======================================	

	@FindBy(xpath = "(//img[@title='Apply to All'])[1]")
	WebElement applyToAll;

	public void clickOnapplyToAllImage() {
		Action.scrollByVisibiltyOfElement(driver, applyToAll);
		applyToAll.click();
	}		



	//========================Background Template Full Length Template CheckBox======================================	

	@FindBy(xpath = "//input[@id='dpdnisfulllength']")
	WebElement FullLengthTemp;

	public void clickOnFullLengthTemp() {
		Action.scrollByVisibiltyOfElement(driver, FullLengthTemp);
		FullLengthTemp.click();
	}



	//========================Background Template Individual Banner Template CheckBox======================================	

	@FindBy(xpath = "(//*[@id='dpdnisbanner']")
	WebElement IndBannerTemp;

	public void clickOnIndBannerTemp() {
		Action.scrollByVisibiltyOfElement(driver, IndBannerTemp);
		IndBannerTemp.click();
	}



	//========================Background Template Individual Banner Template CheckBox======================================	

	@FindBy(xpath = "(//*[@id='dpdnisdtemplate']")
	WebElement dropInTemp;

	public void clickOndropInTemp() {
		Action.scrollByVisibiltyOfElement(driver, dropInTemp);
		dropInTemp.click();
	}
	//========================Background Template Apply to ALL CheckBox======================================	

	@FindBy(xpath = "//*[@id='dpdnapplytoall']")
	WebElement applyToAllCheckBox;

	public void clickOnapplyToAllCheckBox() {
		Action.scrollByVisibiltyOfElement(driver, applyToAllCheckBox);
		applyToAllCheckBox.click();
	}	


	//========================Background Template SAVE button======================================	

	@FindBy(xpath = "//*[@type='submit']")
	WebElement save;

	public void clickOnSave() {
		Action.scrollByVisibiltyOfElement(driver, save);
		save.click();
	}


	//========================Background Template PNG File ======================================	

	@FindBy(xpath = "/html[1]/body[1]/div[3]/div[1]/div[1]/div[1]/form[1]/div[2]/table[1]/tbody[1]/tr[1]/td[3]/a[5]/img[1]")
	WebElement pngFile;

	public void clickOnpngFile() {
		Action.scrollByVisibiltyOfElement(driver, pngFile);
		pngFile.click();
	}	


	//========================Background Template Name======================================	

	@FindBy(xpath = "//*[@class='textfieldvsm']")
	WebElement BgTempTextBox;

	public void enterBGtempName() {
		Action.scrollByVisibiltyOfElement(driver, BgTempTextBox);
		BgTempTextBox.sendKeys("Test_BG_ApplyToAll_A1");
	}	
	//========================Background Template Serch box ======================================	

	@FindBy(xpath = "//*[@id='search_supportimg_id']")
	WebElement BgTempSerch;

	public void bgTempSerchBox(String bg) {
		Action.scrollByVisibiltyOfElement(driver, BgTempSerch);
		BgTempSerch.click();
		BgTempSerch.sendKeys(bg);
	}	

	//========================Background Template Delete message  ======================================	

	@FindBy(xpath = "//div[text()='Background Template deleted']")
	WebElement BgDelete;

	public String BgDeleteMessage() {
		Action.scrollByVisibiltyOfElement(driver, BgDelete);
		return  BgDelete.getText();

	}	

	//========================Background Template Delete message  ======================================	

	@FindBy(xpath = "//div[text()='Background Template has been added to all photographers']")
	WebElement BgTempForAll;

	public String BgTempForAll() {
		Action.scrollByVisibiltyOfElement(driver, BgTempForAll);
		return  BgTempForAll.getText();

	}

	//========================Background Template Delete message  ======================================	

	@FindBy(xpath = "//div[text()='Background Template has been removed from all photographers']")
	WebElement BgTempRemoveForAll;

	public String BgTempRemoveForAll() {
		Action.scrollByVisibiltyOfElement(driver, BgTempRemoveForAll);
		return  BgTempRemoveForAll.getText();

	}

	//========================Background Template PNG option validation message  ======================================	

	@FindBy(xpath = "//div[text()='Added PNG option for background template ']")
	WebElement addPNGoption;

	public String validationAddPNGoption() {
		Action.scrollByVisibiltyOfElement(driver, addPNGoption);
		return  addPNGoption.getText();

	}


	//========================Background Template Remove PNG option validation message  ======================================	

	@FindBy(xpath = "//div[text()='Removed PNG option for background template ']")
	WebElement removePNGoption;

	public String validationremovePNGoption() {
		Action.scrollByVisibiltyOfElement(driver, removePNGoption);
		return  removePNGoption.getText();

	}




}
