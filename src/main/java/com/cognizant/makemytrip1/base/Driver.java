package com.cognizant.makemytrip1.base;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

public class Driver {
    public static WebDriver driver;
    public static WebDriverWait wait;
    public static final String url = "https://www.makemytrip.com/";
    public static final String hubURL = "http://10.194.187.144:4444/wd/hub"; // Hub endpoint

    @BeforeSuite
    public void setupDriver() {
        try {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName("chrome"); // or "firefox" if your node supports it

            driver = new RemoteWebDriver(new URL(hubURL), capabilities);
            driver.manage().window().maximize();
            driver.get(url);

            wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@data-cy='closeModal']"))).click();

            System.out.println("Browser launched via Selenium Grid and MakeMyTrip homepage opened.");
        } catch (MalformedURLException e) {
            System.err.println("Invalid Hub URL: " + hubURL);
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Error during driver setup: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
