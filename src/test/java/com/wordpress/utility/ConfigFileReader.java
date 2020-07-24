/**
 * 
 */
package com.wordpress.utility;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * @author Ashwin BM
 *
 */
public class ConfigFileReader {

	Properties properties;

	public ConfigFileReader() {
		try {
			File configFile = new File("./Configuration/Config.property");
			FileInputStream configfis = new FileInputStream(configFile);
			properties = new Properties();
			properties.load(configfis);
		} catch (Exception e) {
			System.out.println("Error while reading the Config File: " + e.getMessage());
		}
	}

	public String getExcelPath() {
		return properties.getProperty("ExcelPath");
	}

	public String getChromeDriverPath() {
		return properties.getProperty("ChromeDriverPath");
	}

	public String getAppURL() {
		return properties.getProperty("URL");
	}

	public String getExcelSheetName() {
		return properties.getProperty("ExcelSheetname");
	}

	public String getBrowser() {
		return properties.getProperty("Browser");
	}

}
