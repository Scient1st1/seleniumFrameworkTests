package Levan.SeleniumFramework.testComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Levan.SeleniumFramework.resources.ExtentReportNg;

public class ListenersDemo extends BaseTest implements ITestListener {
	
	ExtentReports extent = ExtentReportNg.getReportObject(); //coming from ExtentREportNG class
	ExtentTest test;
	ThreadLocal<ExtentTest> tl = new ThreadLocal(); //Each object has its own thread not overriden;
	public void onTestStart(ITestResult result) {
		
		test =extent.createTest(result.getMethod().getMethodName());
		tl.set(test); //unique thead id for parallel runs
	}

	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, "Test Passed");
	}

	public void onTestFailure(ITestResult result) {
//		test.log(Status.FAIL, "test failed");
		tl.get().fail(result.getThrowable()); //prints error message;
//		get driver from result object 
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		screenshot
		String filePAth = null;
		try {
			filePAth = getScreenshot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tl.get().addScreenCaptureFromPath(filePAth, result.getMethod().getMethodName());
	}

	public void onTestSkipped(ITestResult result) {
		// not implemented
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// not implemented
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		onTestFailure(result);
	}

	public void onStart(ITestContext context) {
		// not implemented
	}

	public void onFinish(ITestContext context) {
		extent.flush();
	}
}
