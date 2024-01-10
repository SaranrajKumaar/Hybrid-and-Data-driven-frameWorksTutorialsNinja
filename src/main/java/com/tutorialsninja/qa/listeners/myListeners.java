package com.tutorialsninja.qa.listeners;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.tutorialsninja.qa.utlils.ExtentReporter;
import com.tutorialsninja.qa.utlils.utilities;

public class myListeners implements ITestListener {

	ExtentReports extentreport;
	ExtentTest extentTest;
	String testName;

	@Override
	public void onStart(ITestContext context) {

		extentreport = ExtentReporter.generateExtentReport();

	}

	@Override
	public void onTestStart(ITestResult result) {

		testName = result.getName();
		extentTest = extentreport.createTest(testName);
		extentTest.log(Status.INFO, testName + "Started executing");

	}

	@Override
	public void onTestSuccess(ITestResult result) {

		extentTest.log(Status.PASS, testName + "Got Successfull Executed");

	}

	@Override
	public void onTestFailure(ITestResult result) {


		System.out.println("Screen shot Taking");
		// retervie the driver object.

		WebDriver driver = null;
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException  e) {
			e.printStackTrace();
		}

		/**File srcScreenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String desinationScreenShotPath = (".//Screenshots//" + testName + ".png");
		
		try {
			FileUtils.copyFile(srcScreenshot, new File(desinationScreenShotPath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}**/
		String desinationScreenShotPath= utilities.captureScreenShot(driver, result.getName());
		extentTest.addScreenCaptureFromPath(desinationScreenShotPath);
		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.FAIL, testName + "Got failed");

	}

	@Override
	public void onTestSkipped(ITestResult result) {

		extentTest.log(Status.INFO, result.getThrowable());
		extentTest.log(Status.SKIP, testName + "Got Skipped");

		System.out.println(result.getThrowable());

	}

	@Override
	public void onFinish(ITestContext context) {
		extentreport.flush();
		
		//Manual opening 
		String pathOfExtendReportpath ="C:\\QAFOX\\HybridFrameworksTestng\\tutorialsninjaproject\\test-output\\ExtentReports\\extentReport.html";
		File Extendreportfile =new File(pathOfExtendReportpath);
		try {
			Desktop.getDesktop().browse(Extendreportfile.toURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	

}
