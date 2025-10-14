package com.GpNgen.TestCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.GpNgen.Base.BaseClass;
import com.GpNgen.utility.createNewLink;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TC_Create_New_Link extends BaseClass {
	
	@Test
    public void testOrderLinkOpening() {
    String orderLink = createNewLink.generateOrderLink();

    System.out.println(orderLink);
    if (orderLink != null) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get("link ="+orderLink);

        System.out.println("✅ Order link opened: " + orderLink);
    } else {
        System.out.println("❌ Order link generation failed.");
    }
}

}
