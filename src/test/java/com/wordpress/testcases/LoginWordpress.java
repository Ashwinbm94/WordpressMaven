package com.wordpress.testcases;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import com.wordpress.pages.BaseClass;
import com.wordpress.pages.LoginPage;

public class LoginWordpress extends BaseClass {

	@Test
	public void wordpressLogin() {

		LoginPage loginpage = PageFactory.initElements(driver, LoginPage.class);
		loginpage.loginToWordpress(excelreader.getUserName("Admin User"), excelreader.getPassword("Admin User"));

	}

}
