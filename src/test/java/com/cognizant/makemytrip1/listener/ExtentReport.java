package com.cognizant.makemytrip1.listener;
 
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
 
public class ExtentReport {
	
	public static ExtentReports reports;
	public static ExtentTest test;
	
	
	public static void createAReport() {
		reports = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter(System.getProperty("user.dir") + "/Report/reportProjectEventListener.html");		
		reports.attachReporter(spark);
	}
	
	public static void createTest(String name) {
		test = reports.createTest(name);
	}
	
	public static void endReport() {
		reports.flush();
	}
}
//// 
//
//import org.testng.ITestContext;
//import org.testng.ITestListener;
//import org.testng.ITestResult;
//
//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.ExtentTest;
//import com.aventstack.extentreports.Status;
//import com.aventstack.extentreports.reporter.ExtentSparkReporter;
//import com.aventstack.extentreports.reporter.configuration.Theme;
//
//public class ExtentReport implements ITestListener {
//	public ExtentSparkReporter sparkReporter;
//	public ExtentReports extent;
//	public ExtentTest test;
//	
//	public void onStart(ITestContext context) {
//		sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/Report/reportProjectEventListener.html");
//		
//		sparkReporter.config().setDocumentTitle("Automation Report");
//		sparkReporter.config().setReportName("Functional Testing");
//		sparkReporter.config().setTheme(Theme.DARK);
//		
//		extent = new ExtentReports();
//		extent.attachReporter(sparkReporter);
//		
//		extent.setSystemInfo("Computer Name", "localhost");
//		extent.setSystemInfo("Environment", "QA");
//		extent.setSystemInfo("Tester Name", "Sandeep");
//		extent.setSystemInfo("os", "Windows10");
//		extent.setSystemInfo("Browser name", "Chrome");
//		
//	}
//	
//	public void onTestSuccess(ITestResult result) {
//		test = extent.createTest(result.getName());
//		test.log(Status.PASS, "Test Case Passed is:" + result.getName());
//	}
//	
//	public void onTestFailure(ITestResult result) {
//		test = extent.createTest(result.getName());
//		test.log(Status.FAIL, "Test case Failed is:" + result.getName());
//		test.log(Status.FAIL, "Test case FAILED cause is:" + result.getThrowable());
//	}
//	
//	public void onTestSkipped(ITestResult result) {
//		test = extent.createTest(result.getName());
//		test.log(Status.SKIP, "Test case skipped" + result.getThrowable());
//	}
//}