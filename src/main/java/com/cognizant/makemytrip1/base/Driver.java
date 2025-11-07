package com.cognizant.makemytrip1.base;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions; // Import this
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

// Import WebDriverManager
import io.github.bonigarcia.wdm.WebDriverManager;

public class Driver {
    public static WebDriver driver;
    public static WebDriverWait wait;
    public static final String url = "https://www.makemytrip.com/";

    @BeforeSuite
    public void setupDriver() {
        
        // 1. Setup WebDriverManager
        // This automatically downloads and sets up the correct ChromeDriver
        WebDriverManager.chromedriver().setup(); 

        // 2. Setup ChromeOptions
        ChromeOptions options = new ChromeOptions();
        
        // --- These options are CRITICAL for Jenkins ---
        options.addArguments("--headless"); // Run browser in the background
        options.addArguments("--no-sandbox"); // Required for Linux/Docker environments
        options.addArguments("--disable-dev-shm-usage"); // Prevents out-of-memory errors
        options.addArguments("--window-size=1920,1080"); // FIX: Set a large screen size
        
        // --- Optional but recommended ---
        options.addArguments("--disable-notifications"); // Disables browser popups
        
        // 3. Initialize the driver with the options
        driver = new ChromeDriver(options);
        
        // 4. Set waits
        // Implicit wait: Wait for elements to be found (good for general stability)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        // Explicit wait: Used for specific conditions
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); 

        // 5. Open the URL
        driver.get(url);
        
        // 6. Handle the login modal (now safer)
        try {
            // We still use an explicit wait here because the modal is a special case
            WebDriverWait modalWait = new WebDriverWait(driver, Duration.ofSeconds(5));
            modalWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@data-cy='closeModal']"))).click();
            System.out.println("Login modal closed.");
        } catch (TimeoutException e) {
            // This is good! It means the modal didn't appear, and we can continue.
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
