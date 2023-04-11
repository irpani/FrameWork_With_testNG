package com.qa.opencart.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Browser_Utility {

	// This method responsible for only launch the Browser =>It will expect the
	// Browser

	public WebDriver driver;

	// Better to write Documet As well ... for the Generic Mehods
	/**
	 * This method used to launch the Browser On this Of Browser name
	 * 
	 * @param browser
	 * @return this will return the driver
	 */

	public WebDriver launchBrowser(String browser) {

		if (browser.equalsIgnoreCase("Chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}

		else if (browser.equalsIgnoreCase("Firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("Opera")) {
			WebDriverManager.operadriver().setup();
			driver = new OperaDriver();

		} else {
			System.out.println("Browser Not Matched");
		}
		return driver; // In upcoming Chapters I will tell u where exactly we are using this driver

	}

	/**
	 * 
	 * @param url
	 */
	public void enterUrl(String url) {
		// Validation Points
		if (url == null) {
			System.out.println("URL is null ...");
			return;
		}
		// Validation Points
		if (url.indexOf("http") == -1) {
			System.out.println("http missing in URL");
			return;
		}

		driver.get(url);

	}

	/**
	 * @param none
	 * @return
	 */ // This will return the CurrentApp Url

	public String getAppCurrentUrl() {

		return driver.getCurrentUrl();
	}

	/**
	 * @param none
	 * 			@return// This will return the getPageTitle
	 */
	public String getPageTitle() {

		return driver.getTitle();
	}

	public void closeBrowser() {
		driver.close();

	}
}