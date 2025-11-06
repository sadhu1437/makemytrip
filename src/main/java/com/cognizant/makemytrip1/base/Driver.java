package com.cognizant.makemytrip1.base;

import java.io.File;
import java.time.Duration;
import org.openqa.selenium.io.FileHandler;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

public class Driver {
    public static WebDriver driver;
    public static WebDriverWait wait;
    public static final String url = "https://www.makemytrip.com/";

    @BeforeSuite
    public void setupDriver() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@data-cy='closeModal']"))).click();
        System.out.println("Browser launched and MakeMyTrip homepage opened.");
    }
    

    @AfterSuite
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("Browser closed after suite execution.");
        }
    }
}
