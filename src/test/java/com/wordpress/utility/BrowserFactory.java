/**
 * 
 */
package com.wordpress.utility;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * @author Ashwin BM
 *
 */
public class BrowserFactory {

	public static WebDriver startApplication(WebDriver driver, String browser, String url) {

		ConfigFileReader config = new ConfigFileReader();

		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", config.getChromeDriverPath());
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("ie")) {

		}
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(config.getAppURL());
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		return driver;
	}

	public static void quitBrowser(WebDriver driver) {
		driver.quit();

	}

}
