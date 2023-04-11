package com.qa.opencart.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;

public class WindowHandle_Utility {

	private WebDriver driver;

	public WebDriver getParentwindow(String windowTitle) {
		return driver.switchTo().window(windowTitle);

	}

	public boolean getRighrwindow(String windowTitle) {
		Set<String> handles = driver.getWindowHandles();
		List<String> hList = new ArrayList<String>();
		for (String e : hList) {
			String title = driver.switchTo().window(e).getTitle();

			if (title.contains(windowTitle)) {
				System.out.println("Found the right window");
				return true;
			}
		}

		return false;
	}

}
