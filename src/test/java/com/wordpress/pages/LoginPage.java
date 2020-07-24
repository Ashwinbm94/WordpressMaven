package com.wordpress.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {

	WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		System.out.println("LoginPage: PageFactory is initialized");

	}

	@FindBy(xpath = "//input[@id='user_login']")
	WebElement userID;

	@FindBy(xpath = "//input[@id='user_pass']")
	WebElement pwd;

	@FindBy(xpath = "//input[@id='wp-submit']")
	WebElement login;

	public void loginToWordpress(String username, String password) {

		userID.sendKeys(username);
		pwd.sendKeys(password);
		login.click();
	}

}
