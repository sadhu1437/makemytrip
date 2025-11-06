//package com.cognizant.makemytrip1.listener;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.WebDriver;
//import org.testng.ITestContext;
//import org.testng.ITestListener;
//import org.testng.ITestResult;
//
//import com.aventstack.extentreports.Status;
//
//public class Listener implements ITestListener {
//
//    WebDriver driver;
//
//    @Override
//    public void onStart(ITestContext context) {
//        ExtentReport.createAReport();  // corrected reference
//    }
//
//    @Override
//    public void onTestStart(ITestResult result) {
//        ExtentReport.createTest(result.getMethod().getMethodName());  // corrected reference
//    }
//
//    @Override
//    public void onTestSuccess(ITestResult result) {
//        ExtentReport.test.log(Status.PASS, "This test passed");
//    }
//
//    @Override
//    public void onTestFailure(ITestResult result) {
//        ExtentReport.test.log(Status.FAIL, "This test failed");
//        driver = (WebDriver) result.getTestContext().getAttribute("driverName");
//        if (driver != null) {
//            String imageData = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
//            ExtentReport.test.addScreenCaptureFromBase64String(imageData);
//        }
//    }
//
//    @Override
//    public void onFinish(ITestContext context) {
//        ExtentReport.endReport();  // corrected reference
//    }
//}

package com.cognizant.makemytrip1.listener;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;

public class Listener implements ITestListener {

    WebDriver driver;

    @Override
    public void onStart(ITestContext context) {
        ExtentReport.createAReport(); // Initialize Extent Report
    }

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        ExtentReport.createTest(testName);

        // ✅ Module-specific descriptions
        if (testName.contains("cabSearch")) {
            ExtentReport.test.log(Status.INFO, "Cab Searching Module: Validates cab availability, fare comparison, and SUV filter.");
        } else if (testName.contains("GiftCard")) {
            ExtentReport.test.log(Status.INFO, "Gift Card Module: Verifies gift card selection, sender details, and error message validation.");
        } else if (testName.contains("hotelBooking")) {
            ExtentReport.test.log(Status.INFO, "Hotel Booking Module: Tests city selection, check-in/check-out dates, and guest configuration.");
        } else {
            ExtentReport.test.log(Status.INFO, "General Test Execution Started.");
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentReport.test.log(Status.PASS, "Test passed successfully.");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentReport.test.log(Status.FAIL, "Test failed. See screenshot below.");

        //Capture screenshot from WebDriver
        driver = (WebDriver) result.getTestContext().getAttribute("driverName");
        if (driver != null) {
            String imageData = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
            ExtentReport.test.addScreenCaptureFromBase64String(imageData, "Failure Screenshot");
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentReport.test.log(Status.SKIP, "Test was skipped.");
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentReport.endReport(); // Finalize Extent Report
    }
}
