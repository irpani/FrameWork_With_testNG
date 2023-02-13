package com.qa.opencart.Factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {

	// I'm going to create 2 private var to read the Properties

	private Properties prop;
	private ChromeOptions co;
	private FirefoxOptions fo;

	// Optins maanager will read the properties from Config.properties
	// So I need prop here
	// So What Should I do here ? Create Constructor for Option'sManager
	// Pass the prop referece

	public OptionsManager(Properties prop) {

		this.prop = prop;

	}

	// This getChromeOptions() method called By Whom? (A): DriverFactory
	public ChromeOptions getChromeOptions() {
		co = new ChromeOptions();
		if (Boolean.parseBoolean(prop.getProperty("headless"))) {
			co.addArguments("--headless");
		}
		if (Boolean.parseBoolean(prop.getProperty("incognito"))) {
			co.addArguments("--incognito");
		}
		return co;
	}

	public FirefoxOptions getFirefoxOptions() {
		fo = new FirefoxOptions();
		if (Boolean.parseBoolean(prop.getProperty("headless"))) {
			fo.addArguments("--headless");
		}
		if (Boolean.parseBoolean(prop.getProperty("incognito"))) {
			fo.addArguments("--incognito");
		}
		return fo;
	}

}
