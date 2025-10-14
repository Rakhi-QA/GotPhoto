package com.GpNgen.PageObject;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.GpNgen.ActionDriver.Action;
import com.GpNgen.ActionDriver.datePickerAction;
import com.GpNgen.Base.BaseClass;

public class AdminDiscountPage extends BaseClass
{

	WebDriver driver;

	public AdminDiscountPage(WebDriver driver) 
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//=======================Discount==================================================
	@FindBy(xpath = "//*[@href='/discounts/listdiscount']")
	WebElement discountMainTab;

	public void clickOnDisountMainTab() {
		Action.scrollByVisibiltyOfElement(driver, discountMainTab);
		discountMainTab.click();
	}

	//=======================Create New Discount==================================================
	@FindBy(xpath = "(//img[@title='Add New'])[1]")
	WebElement createNewDiscount;

	public void createNewDiscountCode() {
		Action.waitForElementVisible(driver, createNewDiscount, 50);
		 //wait.until(ExpectedConditions.visibilityOf(createNewDiscount));
		createNewDiscount.click();
	}

	//===========CODE============
	@FindBy(xpath = "//*[@id='DiscountDiscountCode']")
	WebElement enterCode;

	public void enterCode(String c) {
		Action.scrollByVisibiltyOfElement(driver, enterCode);
		enterCode.sendKeys(c);
	}

	//===========Name============
	@FindBy(xpath = "//*[@id='DiscountDiscountName']")
	WebElement enterName;

	public void enterName(String n) {
		Action.scrollByVisibiltyOfElement(driver, enterName);
		enterName.sendKeys(n);
	}

	//===========Description============
	@FindBy(xpath = "//*[@id='DiscountDiscountDesc']")
	WebElement discountDesc;

	public void enterDiscountDesc(String d) {
		Action.scrollByVisibiltyOfElement(driver, discountDesc);
		discountDesc.sendKeys(d);
	}	

	//===========Percentage Off============
	@FindBy(xpath = "//*[@id='DiscountDiscountRate']")
	WebElement precentOff;

	public void enterprecentOff(String p) {
		Action.scrollByVisibiltyOfElement(driver, precentOff);
		precentOff.sendKeys(p);
	}

	//===========Amount Off============
	@FindBy(xpath = "//*[@id='DiscountDiscountAmount']")
	WebElement amountOff;

	public void enteramountOffOff(String a) {
		Action.scrollByVisibiltyOfElement(driver, amountOff);
		amountOff.sendKeys(a);
	}


	//===========Start date============
	@FindBy(xpath = "//*[@id='datepicker']")
    WebElement dateInput;

    @FindBy(xpath = "//span[@class='ui-datepicker-month']")
    public WebElement monthLabel;

    @FindBy(xpath = "//span[@class='ui-datepicker-year']")
    public WebElement yearLabel;

    @FindBy(xpath = "//a[@title='Next']")
    public WebElement nextButton;

    @FindBy(xpath = "//table[@class='ui-datepicker-calendar']//a")
    List<WebElement> calendarDates;

    public WebElement getDateInput() {
        return dateInput;
    }

    public WebElement getMonthLabel() {
        return monthLabel;
    }

    public WebElement getYearLabel() {
        return yearLabel;
    }

    public WebElement getNextButton() {
        return nextButton;
    }

    public List<WebElement> getAllDates() {
        return calendarDates;
    }

    public String getSelectedDate() {
        return dateInput.getAttribute("value");
    }
	
	
  //===========END  date============
  	@FindBy(xpath = "//*[@id='datepicker1']")
      WebElement endDateInput;

   public WebElement selectEndDate() {
	   Action.scrollByVisibiltyOfElement(driver, endDateInput);
	   return endDateInput;
   }
  	
   public String getSelectedEndDate() {
       return endDateInput.getAttribute("value");
   }
	
	//===========END date============
	@FindBy(xpath = "//*[@id='datepicker1']")
	WebElement endDatePicker;

	public void selectEndDatePicker(String ed) {
		Action.scrollByVisibiltyOfElement(driver, endDatePicker);
		endDatePicker.sendKeys(ed);
	}


	//===========Active============
	@FindBy(xpath = "//*[@id='UserField1']")
	WebElement activeYes;

	public void selectactiveYes() {
		Action.scrollByVisibiltyOfElement(driver, activeYes);
		activeYes.click();;
	}

	@FindBy(xpath = "//*[@id='UserField2']")
	WebElement activeNo;

	public void selectactiveNo() {
		Action.scrollByVisibiltyOfElement(driver, activeNo);
		activeNo.click();;
	}




	//===========Number of Times Used:============
	@FindBy(xpath = "//*[@id='DiscountDiscountNumberOfTimeUsed']")
	WebElement numberOfTimeUsed;

	public void numberOfTimeUsed() {
		Action.scrollByVisibiltyOfElement(driver, numberOfTimeUsed);
		numberOfTimeUsed.click();
	}

	//===========Assigned To============
	@FindBy(xpath = "//*[@id='testSelect1_input']")
	WebElement assignedTO;

	public void selectAssignedTO() {
		Action.scrollByVisibiltyOfElement(driver, assignedTO);
		assignedTO.click();
		assignedTO.sendKeys("338");
	}
	@FindBy(xpath = "//*[@data-val='338']")
	WebElement assignedTO338;

	public void selectAssignedTO338() {
		Action.scrollByVisibiltyOfElement(driver, assignedTO338);
		//assignedTO.click();
		//assignedTO.sendKeys("338");
		assignedTO338.click();
	}

	
	
	
	//===========Maximum User:============
	@FindBy(xpath = "//*[@id='DiscountDiscountMaxUser']")
	WebElement maximumUser;

	public void enterMaximumUser() {
		Action.scrollByVisibiltyOfElement(driver, maximumUser);
		maximumUser.click();
		maximumUser.sendKeys("10");
	}


	//===========Maximum Usage============
	@FindBy(xpath = "//*[@id='DiscountDiscountMaxUsage']")
	WebElement maximumUsage;

	public void enterMaximumUsage() {
		Action.scrollByVisibiltyOfElement(driver, maximumUsage);
		maximumUsage.click();
		maximumUsage.sendKeys("5");
	}




	//===========Min. Order Value============
	@FindBy(xpath = "//*[@id='DiscountDiscountMinOrderValue']")
	WebElement minimumOrderValue;

	public void enterMinimumOrderValue() {
		Action.scrollByVisibiltyOfElement(driver, minimumOrderValue);
		minimumOrderValue.click();
		minimumOrderValue.sendKeys("1");
	}



	//===========Max. Discount Value============
	@FindBy(xpath = "//*[@id='DiscountDiscountMaxDisValue']")
	WebElement maxOrderValue;

	public void enterMaxOrderValue() {
		Action.scrollByVisibiltyOfElement(driver, maxOrderValue);
		maxOrderValue.click();
	}


	//===========1st Order Only============
	@FindBy(xpath = "(//input[@id='UserField1'])[2]")
	WebElement yes;

	public void yesOrderOnly() {
		Action.scrollByVisibiltyOfElement(driver, yes);
		yes.click();
	}



	//===========1st Order Only============
	@FindBy(xpath = "(//input[@id='UserField2'])[2]")
	WebElement no;

	public void noOrderOnly() {
		Action.scrollByVisibiltyOfElement(driver, no);
		no.click();
	}
	//=======================SAVE  Discount Code==================================================
		@FindBy(xpath = "//*[@type='submit']")
		WebElement save;

		public void savebtn() {
			Action.scrollByVisibiltyOfElement(driver, save);
			save.click();
		}
	
	
	
	//=======================Edit/Update  Discount Code==================================================
	@FindBy(xpath = "(//img[@title='Edit'])[1]")
	WebElement updateDiscount;

	public void updateDiscountCode() {
		Action.scrollByVisibiltyOfElement(driver, updateDiscount);
		updateDiscount.click();
	}


	//=======================Delete  Discount Code==================================================
	@FindBy(xpath = "/html[1]/body[1]/div[3]/div[1]/div[1]/div[1]/form[1]/div[2]/table[1]/tbody[1]/tr[1]/td[11]/a[3]/img[1]")
	WebElement deleteDiscount;

	public void deleteDiscountCode() {
		Action.scrollByVisibiltyOfElement(driver, deleteDiscount);
		deleteDiscount.click();
	}

	//=======================SERCH option  Discount Code==================================================
		@FindBy(xpath = "//*[@value='code']")
		WebElement serchDiscount;

		public void serchDiscountCode(String serch) {
			Action.scrollByVisibiltyOfElement(driver, serchDiscount);
			serchDiscount.click();
			serchDiscount.sendKeys(serch);
		}


}
