package com.cognizant.makemytrip1.utils;

import java.io.File;
import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;

public class ScreenshotUtil {

    public static void capture(WebDriver driver, String fileName) {
        try {
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File folder = new File("screenshots");
            if (!folder.exists()) folder.mkdir();

            File destination = new File(folder, fileName + ".png");
            FileHandler.copy(screenshot, destination);
            System.out.println("Screenshot saved at: " + destination.getAbsolutePath());
        } catch (Exception e) {
            System.out.println("Failed to capture screenshot: " + e.getMessage());
        }
    }
}
