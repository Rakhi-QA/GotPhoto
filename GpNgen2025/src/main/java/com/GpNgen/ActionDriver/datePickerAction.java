package com.GpNgen.ActionDriver;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.GpNgen.Base.BaseClass;
import com.GpNgen.PageObject.AdminDiscountPage;

public class datePickerAction extends BaseClass{
	
	AdminDiscountPage datePickerPage;
	WebDriver driver;
	
	public datePickerAction(WebDriver driver) {
		this.driver = driver;
        this.datePickerPage = new AdminDiscountPage(driver);
    }

	public void selectDate(WebElement webElement, String day, String targetMonth, String targetYear) throws InterruptedException {
	    // Open calendar
	    datePickerPage.getDateInput().click();

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.visibilityOf(datePickerPage.monthLabel));

	    while (true) {
	        String currentMonth = datePickerPage.monthLabel.getText().trim();
	        String currentYear = datePickerPage.yearLabel.getText().trim();

	        if (currentMonth.equalsIgnoreCase(targetMonth) && currentYear.equals(targetYear)) {
	            break;
	        }

	        // Move calendar forward
	        datePickerPage.nextButton.click();
	        Thread.sleep(500); // short wait between clicks
	    }

	    // Now select the day
	    for (WebElement date : datePickerPage.getAllDates()) {
	        if (!date.getText().isEmpty() && date.getText().equals(day)) {
	            wait.until(ExpectedConditions.elementToBeClickable(date));
	            date.click();
	            break;
	        }
	    }
	}
	
	
	
	public void selectEndDate(WebElement webElement, String day, String targetMonth, String targetYear) throws InterruptedException {
	    // Open calendar
	    datePickerPage.selectEndDate().click();

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.visibilityOf(datePickerPage.monthLabel));

	    while (true) {
	        String currentMonth = datePickerPage.monthLabel.getText().trim();
	        String currentYear = datePickerPage.yearLabel.getText().trim();

	        if (currentMonth.equalsIgnoreCase(targetMonth) && currentYear.equals(targetYear)) {
	            break;
	        }

	        // Move calendar forward
	        datePickerPage.nextButton.click();
	        Thread.sleep(500); // short wait between clicks
	    }

	    // Now select the day
	    for (WebElement date : datePickerPage.getAllDates()) {
	        if (!date.getText().isEmpty() && date.getText().equals(day)) {
	            wait.until(ExpectedConditions.elementToBeClickable(date));
	            date.click();
	            break;
	        }
	    }
	}
	 // ✅ For Start Date
    public void selectStartDate(String day, String month, String year) throws InterruptedException {
    	selectDate(datePickerPage.getDateInput(), day, month, year);
    }

    // ✅ For End Date
    public void selectEndDate(String day, String month, String year) throws InterruptedException {
    	selectEndDate(datePickerPage.selectEndDate(), day, month, year);
    }
    public String getSelectedDate() {
        return datePickerPage.getSelectedDate();
    }

    public String getSelectedEndDate() {
        return datePickerPage.getSelectedEndDate();
    }
    }


