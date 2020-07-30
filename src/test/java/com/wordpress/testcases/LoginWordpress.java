package com.wordpress.testcases;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.wordpress.pages.BaseClass;
import com.wordpress.pages.LoginPage;
import com.wordpress.pages.Posts;
import com.wordpress.utility.Utility;

public class LoginWordpress extends BaseClass {

	@Test
	public void wordpressLogin() {

		logger = report.startTest("TC_Wordpress_001", "Create New Post");
		logger.log(LogStatus.INFO, "Wordpress Application Initalized");

		LoginPage loginpage = PageFactory.initElements(driver, LoginPage.class);
		loginpage.loginToWordpress(excelreader.getUserName("Admin User"), excelreader.getPassword("Admin User"));

		if (driver.getTitle().equalsIgnoreCase("Dashboard ‹ Wordpress Demo Site at Demo.Center — WordPress")) {
			logger.log(LogStatus.PASS, "Logged in to the Wordpress");
			logger.log(LogStatus.PASS, logger.addScreenCapture(Utility.captureScreenshot(driver, "WordpressLogin")));
		} else {
			logger.log(LogStatus.FAIL, "Wordpress Application Login Failed");
			logger.log(LogStatus.FAIL, logger.addScreenCapture(Utility.captureScreenshot(driver, "WordpressLogin")));
		}

		logger.log(LogStatus.INFO, "Navigating to Add New Post");
		logger.log(LogStatus.INFO, "Creating New Post");

		Posts posts = PageFactory.initElements(driver, Posts.class);
		posts.addNewPosts();

		report.endTest(logger);

	}

}
