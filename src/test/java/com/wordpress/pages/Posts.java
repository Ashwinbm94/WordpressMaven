package com.wordpress.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.LogStatus;
import com.wordpress.utility.BrowserFactory;
import com.wordpress.utility.Utility;

public class Posts {
	WebDriver driver;

	public Posts(WebDriver driver) {
		this.driver = driver;
	}

	@FindBy(xpath = "//div[@class='wp-menu-name'][text()='Posts']")
	WebElement posts;

	@FindBy(xpath = "//div[@class='wp-menu-image dashicons-before dashicons-admin-post']")
	WebElement postsIcon;

	@FindBy(xpath = "//li//a[@href='post-new.php'][text()='Add New']")
	WebElement posts_AddNew;

	@FindBy(xpath = "//span[@class='collapse-button-label']")
	WebElement collapseMenu;

	@FindBy(xpath = "//textarea[@id='post-title-0']")
	WebElement postTitle;

	@FindBy(xpath = "//iframe[@id='content_ifr']")
	WebElement contentIFrame;

	@FindBy(xpath = "//p[@class='wp-block-paragraph editor-rich-text__tinymce mce-content-body']")
	WebElement content;

	@FindBy(xpath = "//input[@id='save-post']")
	WebElement saveDraft;

	@FindBy(xpath = "//button[@type='button'][text()='Publish…']")
	WebElement publish;

	@FindBy(xpath = "//div[text()='Post published.']")
	WebElement postPublished;

	public void addNewPosts() {

		BrowserFactory.waitForPageLoad(driver);

		Actions action = new Actions(driver);
		action.moveToElement(postsIcon).perform();
		WebDriverWait wait = new WebDriverWait(driver, 30);
		WebElement addNewPosts = wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("//li//a[@href='post-new.php'][text()='Add New']")));
		addNewPosts.click();
		BrowserFactory.waitForPageLoad(driver);

		postTitle.sendKeys("Testing Add New Post");
		// driver.switchTo().frame(contentIFrame);
		content.sendKeys("This is my first new page");
		// driver.switchTo().defaultContent();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("$(arguments[0]).click();", publish);

		// publish.click();

		if (postPublished.isDisplayed()) {
			BaseClass.logger.log(LogStatus.PASS, "Post Published");
			BaseClass.logger.log(LogStatus.PASS,
					BaseClass.logger.addScreenCapture(Utility.captureScreenshot(driver, "Add New Post")));
		} else {
			BaseClass.logger.log(LogStatus.FAIL, "Unable to publish the Post");
			BaseClass.logger.log(LogStatus.FAIL,
					BaseClass.logger.addScreenCapture(Utility.captureScreenshot(driver, "Add New Post")));
		}

	}

}
