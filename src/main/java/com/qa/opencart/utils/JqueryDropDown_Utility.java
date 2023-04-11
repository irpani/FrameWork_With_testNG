package com.qa.opencart.utils;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JqueryDropDown_Utility {

	public WebDriver driver;

	// (1).DropDown Single Value Selection
	// selectByChoice(choices, "choice 6 2 3");

	// (2). DropDown Multi Value Selection
	// selectByChoice(choices, "choice 2 1", "choice 6 2 3");

	// (3).DropDown ALL Value Selection
	// selectByChoice(choices, "all");

	// (4).DropDown When I try to pass the wrong value // pending from Naveen
	// selectByChoice(choices, "choice 100");

	public void selectByChoice(By Locator, String... value) {
		List<WebElement> choicelist = driver.findElements(Locator);
		if (!value[0].equalsIgnoreCase("all")) {

			for (WebElement e : choicelist) {
				String text = e.getText();
				System.out.println(text);

				for (int i = 0; i < value.length; i++) {
					if (text.equals(value[i])) {
						e.click();
						break;
					}

				}

			}

		}
		// Select for All Elements Logic:
		else {

			try {
				for (WebElement e : choicelist) {
					e.click();
				}
			} catch (ElementNotInteractableException e) {
				System.out.println("all choices are over ...");
			}

		}

	}

}
