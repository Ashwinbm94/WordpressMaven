package com.wordpress.pages;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.wordpress.utility.BrowserFactory;
import com.wordpress.utility.ConfigFileReader;
import com.wordpress.utility.ExcelFileReader;
import com.wordpress.utility.Utility;

public class BaseClass {
	public WebDriver driver;
	public ExcelFileReader excelreader;
	public ConfigFileReader reader;
	public ExtentReports report;
	public static ExtentTest logger;

	@BeforeSuite
	public void setupSuite() {
		excelreader = new ExcelFileReader();
		reader = new ConfigFileReader();
		report = new ExtentReports(
				System.getProperty("user.dir") + "/ExtentReport/Wordpress" + Utility.getCurrentDateTime() + ".html",
				true);
	}

	@BeforeClass
	public void setup() {
		driver = BrowserFactory.startApplication(driver, "chrome", reader.getAppURL());

	}

	@AfterClass
	public void tearDown() {
		// BrowserFactory.quitBrowser(driver);
		excelreader.closeExcelWB();
	}

	@AfterMethod
	public void tearDownMethod(ITestResult result) {
		if (result.getStatus() == ITestResult.SUCCESS) {
			logger.log(LogStatus.PASS, "Name of the Test Case Passed is: " + result.getName());
			logger.log(LogStatus.PASS, logger.addScreenCapture(Utility.captureScreenshot(driver, result.getName())));
		}
		report.flush();
	}

}
