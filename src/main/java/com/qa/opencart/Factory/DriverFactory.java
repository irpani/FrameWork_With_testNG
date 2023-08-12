package com.qa.opencart.Factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
//Our Priject is Maven Project ...
//Env Setup testcase Execution  ??????
//we should not Execute our testScripts from Eclipse,So What Should I do in this case
//Maven has the Capability run the testcases from Commandline ..
//I don't want to maintain my env in code base That is main trick here So what Should I do
//

//***Driver Factory is  Responsible to Geerate the Driver .....
//Instead Of giving Normal driver ,give the ThreadLocal Driver
//Q:How will u get ThreadLocal driver ?
//A: It's very simple 
public class DriverFactory {

	public WebDriver driver;
	public Properties prop;
	public static String highlight;
	public OptionsManager optionsManager;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>(); // webDriver typ Of
	// There are 2 methods now 1 is Set() method & 1 is get() method
	// How do u set the tlDriver ???

	public WebDriver int_driver(Properties prop) {
		String browserName = prop.getProperty("browser");
		// System.out.println("Browser Name is " + browserName);
		highlight = prop.getProperty("highlight");
		optionsManager = new OptionsManager(prop);
		if (browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			// driver = new ChromeDriver(optionsManager.getChromeOptions());
			// Below line meaning Threadlocal driver please setup the ChromeDriver
			// I'm initializing the Chrome driver with the help of tldriver now ,driver
			// pointin toChrome Driver only ..
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));

		}

		else if (browserName.equals("firiefox")) {
			WebDriverManager.firefoxdriver().setup();
			// driver = new FirefoxDriver(optionsManager.getFirefoxOptions());
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
		}

		else if (browserName.equals("safari")) {
			WebDriverManager.safaridriver().setup();

		} else {
			System.out.println("Please pass the Right Browser" + browserName);
		}

		/*
		 * driver.manage().window().fullscreen(); driver.manage().deleteAllCookies();
		 * driver.get(prop.getProperty("url"));
		 */

		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		getDriver().get(prop.getProperty("url"));
		return getDriver();
	}

	/**
	 * getDriver() => It will return a thread localcopy of the webDriver
	 * 
	 * @return
	 * 
	 */
	public static synchronized WebDriver getDriver() {

		return tlDriver.get();
	}

	// ***** Env Set up********
	// Right Now below we are reading the Property from Config.property
	// In Our Company we will maintain Couple Of env So How will we do that ?
	// So what we have to do is here ? we write a logic here ...
	/**
	 * This method is used to Initialize the Properties This will return Properties
	 * prop reference
	 */
	// To initialize properties We must create object of Properties class from JAVA
	// This method will return a prop
	public Properties init_prop() {

		prop = new Properties();
		FileInputStream ip = null;
		// FileInputStream ip=new FileInputStream(file);
		// Here we should give file path
		// Where exactly your config.properties Available,So I have to pass the address
		// of
		// config.properties
		// This Env we will run from CommandLine Today I will Show U how to run from
		// maven
		// note:System.setProperty("",""); It is used to set the commandline property
		// Q:I want Env How will I get Env?
		// A: I created 1 var string envName which is taking from Command line
		String envName = System.getProperty("env"); // It is used to get the Command line argument
		// This env may be qa/dev/stage/prod
		// Assume env=null where exactly Test cases working ?
		if (envName == null) {
			System.out.println("Running On PROD in that case");
			try {
				// This line take the address of Config.properties ...
				ip = new FileInputStream("src/test/resources/config/config.properties");

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		// If Some Env is Coming What we will do now
		else {
			System.out.println("Running On Specific Env" + envName);

			try {
				switch (envName.toLowerCase()) {
				case "dev":
					ip = new FileInputStream("src/test/resources/config/dev.config.properties");
					break;
				case "qa":
					ip = new FileInputStream("src/test/resources/config/qa.config.properties");
					break;
				case "stage":
					ip = new FileInputStream("src/test/resources/config/stage.config.properties");
					break;
				case "uat":
					ip = new FileInputStream("src/test/resources/config/uat.config.properties");

					break;

				default:
					System.out.println("Please Pass the Right Env");
					break;
				}
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			}

		}
		// Now Properties file will load in which ip stream ... dev/qa/stage/uat/prod ?
		try {
			prop.load(ip);
		} catch (IOException e) {

			e.printStackTrace();
		}
		return prop;
	}

	/*
	 * Take ScrennShot
	 *
	 */
	// Which method u have to use to get the driver
	// Yesterday we created getdriver() method righr which gives us
	// Threadlocaldriver
	// If multiple threads running the testcases multiple failures are coming then
	// take the screenshot seperately with the driver
	// So give me the local copy of the driver
	public String getScreenshot() {
		// getDriver() -- // It will give us the threadlocal copy
		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		// here we are converting webdriver to takeScreenShot
		// What type of screen shot u want to generate
		// I want to transfer the file to my own path
		String path = System.getProperty("user.dir") + "/screenshots" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {

			e.printStackTrace();
		}
		return path;
	}

}

// prop.load(ip);// while Loading we my get exception so added anothe catch{}
// also