package com.cognizant.makemytrip1.base;

import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Driver {
    public static WebDriver driver;
    public static WebDriverWait wait;
    public static final String url = "https://www.makemytrip.com/";

    @BeforeSuite
    public void setupDriver() {
        try {
            WebDriverManager.chromedriver().setup();

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--headless=new"); // Use headless mode for Jenkins
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--disable-gpu");
            options.addArguments("--window-size=1920,1080");

            driver = new ChromeDriver(options);
            driver.get(url);

            wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@data-cy='closeModal']"))).click();

            System.out.println("Browser launched and MakeMyTrip homepage opened.");
        } catch (Exception e) {
            System.err.println("Error during WebDriver setup: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @AfterSuite
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("Browser closed after suite execution.");
        }
    }
}
