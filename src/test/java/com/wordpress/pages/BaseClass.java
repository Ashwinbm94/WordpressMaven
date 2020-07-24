package com.wordpress.pages;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.wordpress.utility.BrowserFactory;
import com.wordpress.utility.ConfigFileReader;
import com.wordpress.utility.ExcelFileReader;

public class BaseClass {
	public WebDriver driver;
	public ExcelFileReader excelreader = new ExcelFileReader();

	@BeforeClass
	public void setup() {
		ConfigFileReader reader = new ConfigFileReader();
		driver = BrowserFactory.startApplication(driver, "chrome", reader.getAppURL());
	}

	@AfterClass
	public void tearDown() {
		BrowserFactory.quitBrowser(driver);
		excelreader.closeExcelWB();
	}

}
