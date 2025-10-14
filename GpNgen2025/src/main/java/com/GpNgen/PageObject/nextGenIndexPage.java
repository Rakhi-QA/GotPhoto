package com.GpNgen.PageObject;

import java.time.Duration;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.GpNgen.ActionDriver.Action;
import com.GpNgen.Base.BaseClass;

public class nextGenIndexPage extends BaseClass
{
WebDriver driver;
public nextGenIndexPage(WebDriver driver) 
{
	this.driver = driver;
	PageFactory.initElements(driver, this);
}

@FindBy(xpath = "//*[@id='APjFqb']")
WebElement googleSerch;

public void serch() throws Throwable
{
	Action.scrollByVisibiltyOfElement(driver, googleSerch);
	//Action.selectByVisibleText( "next gen photography",googleSerch);
	//Action.click(driver, googleSerch);
	
	googleSerch.sendKeys("next gen photography");
	//googleSerch.submit();
	googleSerch.sendKeys(Keys.ENTER);
	
	
}

@FindBy(xpath = "(//div[@class='pcTkSc'])[1]")
WebElement firstResult;

public void clickOnFirstResult() {
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    wait.until(ExpectedConditions.visibilityOf(firstResult));
    
    Action.scrollByVisibiltyOfElement(driver, firstResult);
    Action.click(driver, firstResult);
}

}
