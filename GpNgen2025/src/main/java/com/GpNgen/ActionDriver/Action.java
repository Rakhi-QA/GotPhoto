package com.GpNgen.ActionDriver;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections4.map.StaticBucketMap;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hpsf.Date;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.GpNgen.Base.BaseClass;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;


public class Action extends BaseClass {
	public static void scrollByVisibiltyOfElement(WebDriver driver, WebElement ele) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView({block: 'center'})", ele);
	}

	public static void click(WebDriver ldriver, WebElement locatorName) {
		Actions act = new Actions(ldriver);
		act.moveToElement(locatorName).click().build().perform();

	}
	
	
	

	public static boolean findElement(WebDriver ldriver, WebElement ele) {
		boolean flag = false;
		try {
			ele.isDisplayed();
			flag = true;
		} catch (Exception e) {
			flag = false;
		} finally {
			if (flag) {
				System.out.println("Element  is found ");
			} else {
				System.out.println("Element is not found");
			}
		}
		return flag;

	}
	
	public static boolean isElementVisibleAndEnabled(WebDriver driver, WebElement element, ExtentTest test,String elementName) {
	    try {
	        boolean isVisible = element.isDisplayed();
	        boolean isEnabled = element.isEnabled();

	        if (isVisible && isEnabled) {
	            test.pass("Element is visible and enabled: " + elementName);
	            return true;
	        } else {
	            test.warning("Element is present but not fully interactable (Visible: " + isVisible + ", Enabled: " + isEnabled + "): " + elementName);
	            return false;
	        }
	    } catch (NoSuchElementException e) {
	        test.fail("Element not found: " + elementName);
	        return false;
	    } catch (Exception e) {
	        test.fail("Exception while checking element state: " + e.getMessage());
	        return false;
	    }
	}

	
	/*public static void checkIfCheckboxIsEnabled(WebElement checkbox,String label) {
        if (checkbox.isEnabled()) {
        	
        	//test.log(Status.PASS, label + " is selected.");
            System.out.println(label + " is selected.");
            System.out.println("Checkbox is ENABLED.");
            
        } else {
            System.out.println("Checkbox is DISABLED.");
        }
    }*/
	
	
	public static void checkIfCheckboxIsEnabled(WebElement element, String elementName, ExtentTest test) {
        if (element.isEnabled()) {
            System.out.println(elementName + " is ENABLED.");
            test.pass(elementName + " is ENABLED.");
        } else {
            System.out.println(elementName + " is DISABLED.");
            test.fail(elementName + " is DISABLED.");
        }

        if (element.isSelected()) {
            System.out.println(elementName + " is SELECTED.");
            test.info("Selected option: " + elementName);
        } else {
            System.out.println(elementName + " is NOT SELECTED.");
            test.info(elementName + " is NOT SELECTED.");
        }
    }

	public static boolean isElementClickable(WebDriver driver, WebElement element, ExtentTest test) {
        boolean flag = false;
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            flag = true;
            test.pass("Element is clickable");
        } catch (Exception e) {
            flag = false;
            test.fail("Element is not clickable");
        }
        return flag;
    }
	
	
	
	public static boolean isDisplayed(WebDriver ldriver, WebElement ele, ExtentTest test) {
		boolean flag = false;
		flag = findElement(ldriver, ele);
		if (flag) {
			flag = ele.isDisplayed();
			if (flag) {
				test.pass("Element is displayed");
				System.out.println("Element is displayed");
			} else {
				test.fail("Element is not displayed");
				System.out.println("Elelment is not displayed");
			}
		}

		else {
			System.out.println("Not Displayed");
		}
		return flag;

	}

	public static boolean isSelected(WebDriver ldriver, WebElement ele,ExtentTest test, String label) {
		boolean flag = false;
		try {
	        flag = findElement(driver, ele);  // this should ensure visibility or presence

	        if (flag) {
	            if (ele.isSelected()) {
	                test.log(Status.PASS, label + " is selected.");
	                System.out.println(label + " is selected.");
	                return true;
	            } else {
	                test.log(Status.INFO, label + " is NOT selected.");
	                System.out.println(label + " is NOT selected.");
	                return false;
	            }
	        } else {
	            test.log(Status.WARNING, label + " element not found.");
	            System.out.println(label + " element not found.");
	        }
	    } catch (Exception e) {
	        test.log(Status.FAIL, "Exception while checking selection status for " + label + ": " + e.getMessage());
	    }

	    return false;

	}

	
	
	
	public static String getText(WebDriver driver, WebElement element) {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.visibilityOf(element));
	    return element.getText();
	}
	
	public static void type(WebElement element, String value) {
        element.clear();
        element.sendKeys(value);
    }

	public static boolean selectBysendKeys(WebElement ele, String value) throws Throwable {
		boolean flag = false;
		try {
			ele.sendKeys(value);
			flag = true;
			return true;
		} catch (Exception e) {

			return false;
		} finally {
			if (flag) {
				System.out.println("Select value from dropdown");
			} else {
				System.out.println("Not Selected value from the DropDown");
			}
		}
	}

	public static boolean selectByIndex(WebElement element, int index) {
		boolean flag = false;
		try {
			Select s = new Select(element);
			s.selectByIndex(index);
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (flag) {
				System.out.println("Option selected by Index");
			} else {
				System.out.println("Option not selected by Index");
			}
		}
	}

	public static boolean selectByValue(WebElement element, String value, ExtentTest test) {
	    boolean flag = false;
	    try {
	        Select s = new Select(element);
	        s.selectByValue(value);
	        flag = true;
	        test.pass("Option selected by value: " + value);
	        return true;
	    } catch (Exception e) {
	        test.fail("Failed to select option by value: " + value + ". Exception: " + e.getMessage());
	        return false;
	    } finally {
	        if (flag) {
	            test.info("Selection attempt finished - option was selected.");
	        } else {
	            test.warning("Selection attempt finished - option was not selected.");
	        }
	    }
	}


	public static boolean selectByVisibleText(String visibletext, WebElement ele) {
		boolean flag = false;
		try {
			Select s = new Select(ele);
			s.selectByVisibleText(visibletext);
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (flag) {
				System.out.println("Option selected by VisibleText");
			} else {
				System.out.println("Option not selected by VisibleText");
			}
		}
	}

	public boolean mouseHoverByJavaScript(WebElement ele) {
		boolean flag = false;
		try {
			WebElement mo = ele;
			String javaScript = "var evObj = document.createEvent('MouseEvents');"
					+ "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
					+ "arguments[0].dispatchEvent(evObj);";
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript(javaScript, mo);
			flag = true;
			return true;
		}

		catch (Exception e) {

			return false;
		} finally {
			if (flag) {
				System.out.println("MouseOver Action is performed");
			} else {
				System.out.println("MouseOver Action is not performed");
			}
		}
	}

	public boolean launchUrl(WebDriver driver, String url) {
		boolean flag = false;
		try {
			driver.navigate().to(url);
			flag = true;
			return true;
		} catch (Exception e) {
			return false;
		} finally {
			if (flag) {
				System.out.println("Successfully launched \"" + url + "\"");
			} else {
				System.out.println("Failed to launch \"" + url + "\"");
			}
		}
	}

	public String getTitle(WebDriver driver) {
		boolean flag = false;

		String text = driver.getTitle();
		if (flag) {
			System.out.println("Title of the page is: \"" + text + "\"");
		}
		return text;
	}

	public String getCurrentURL(WebDriver driver) {
		boolean flag = false;

		String text = driver.getCurrentUrl();
		if (flag) {
			System.out.println("Current URL is: \"" + text + "\"");
		}
		return text;
	}
	
	public static void waitForElementVisible(WebDriver driver, WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        wait.until(ExpectedConditions.visibilityOf(element));
    }
	

	public void fluentWait(WebDriver driver, WebElement element, int timeOut) {
		Wait<WebDriver> wait = null;
		try {
			wait = new FluentWait<WebDriver>((WebDriver) driver).withTimeout(Duration.ofSeconds(20))
					.pollingEvery(Duration.ofSeconds(2)).ignoring(Exception.class);
			wait.until(ExpectedConditions.visibilityOf(element));
			element.click();
		} catch (Exception e) {
		}
	}

	public static void implicitWait(WebDriver driver, int timeOut) {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public void explicitWait(WebDriver driver, WebElement element, Duration timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, timeOut);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static void pageLoadTimeOut(WebDriver driver, int timeOut) {
		driver.manage().timeouts().pageLoadTimeout(timeOut, TimeUnit.SECONDS);
	}

	public String screenShot(WebDriver driver, String filename) {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File source = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "\\ScreenShots\\" + filename + "_" + dateName + ".png";

		try {
			FileUtils.copyFile(source, new File(destination));
		} catch (Exception e) {
			e.getMessage();
		}
		// This new path for jenkins
		String newImageString = "http://localhost:8082/job/MyStoreProject/ws/MyStoreProject/ScreenShots/" + filename
				+ "_" + dateName + ".png";
		return newImageString;
	}

	public String getCurrentTime() {
		String currentDate = new SimpleDateFormat("yyyy-MM-dd-hhmmss").format(new Date());
		return currentDate;
	}
	
	
	public static  void uloadFileUsingRobotClass(String[] filepath)
	{
		try {
			// Copy file path to clipboard
			String combinedPaths = String.join("\n", filepath);
			StringSelection selection=new StringSelection(combinedPaths);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
			
			Robot r=new Robot();
			r.delay(1000);
			
			r.keyPress(KeyEvent.VK_CONTROL);
			r.keyPress(KeyEvent.VK_V);
			r.keyRelease(KeyEvent.VK_V);
			r.keyRelease(KeyEvent.VK_CONTROL);
			
			r.delay(500);
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
			
		} catch (AWTException e) {
			
			e.printStackTrace();
		}
		
	}
	
	
	
	public static void uploadMultipleFiles(WebElement uploadElement, String[] filePaths) {
        try {
            // Join all file paths using newline separator
            String files = String.join("\n", filePaths);

            // Send to the input element
            uploadElement.sendKeys(files);

        } catch (Exception e) {
            System.out.println("Error while uploading files: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public void waitForElementToBeVisible(By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));}
	
	
	public void readJobDataFieldWise(WebDriver driver, WebElement table, ExtentTest test) {
	    List<WebElement> rows = table.findElements(By.xpath("/html[1]/body[1]/section[1]/div[1]/div[1]/form[1]/div[1]/div[1]/div[3]/div[1]/div[1]/div[2]"));

	    if (rows.isEmpty()) {
	        test.fail("No data found in table.");
	        return;
	    }

	    for (WebElement row : rows) {
	        List<WebElement> cells = row.findElements(By.tagName("td"));

	        // Defensive check
	        if (cells.size() < 5) continue;

	        // Read fields based on column index
	        String firstName = cells.get(0).getText();
	        String lastName = cells.get(1).getText();
	        String jersey = cells.get(2).getText();
	        String teamName = cells.get(3).getText();
	        String image1 = cells.get(4).getText(); // Optional - may be empty

	        // Print/log
	        System.out.println("First Name: " + firstName);
	        System.out.println("Last Name: " + lastName);
	        System.out.println("Jersey: " + jersey);
	        System.out.println("Team Name: " + teamName);
	        System.out.println("Image1: " + image1);
	        System.out.println("---------------");

	        test.info("Job Row → First Name: " + firstName + " | Team: " + teamName + " | Jersey: " + jersey);
	    }
	}

	

	
		
	}
	
	
	
	
	
