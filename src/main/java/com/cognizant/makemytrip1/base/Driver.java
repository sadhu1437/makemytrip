package com.cognizant.makemytrip1.base;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions; // <-- Import added
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class Driver {
    public static WebDriver driver;
    public static WebDriverWait wait;
    public static final String url = "https://www.makemytrip.com/";

    @BeforeSuite
    public void setupDriver() {
        // --- THIS IS THE FIX ---
        // Setup ChromeOptions to start the browser maximized
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications"); // Also useful to block popups

        // Initialize the driver with the options
        driver = new ChromeDriver(options);
        
        // Removed the failing line: driver.manage().window().maximize();

        // Set an implicit wait for better stability
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        
        driver.get(url);
        
        // Increased wait time slightly for more stability
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // --- IMPROVEMENT ---
        // Added a try-catch block to robustly handle the login modal.
        // If the modal doesn't appear, the tests won't fail at setup.
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@data-cy='closeModal']"))).click();
            System.out.println("Login modal closed.");
        } catch (TimeoutException e) {
            System.out.println("Login modal did not appear. Continuing...");
        }
        
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
