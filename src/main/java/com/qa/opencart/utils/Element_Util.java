package com.qa.opencart.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.Factory.DriverFactory;

public class Element_Util {
	// Element util says I need my own webdriver , U need your own right so take it
	// make it as private so that it is related to this class only

	private WebDriver driver; // here Driver pointing to null,
	private JavaScript_Util jsUtil;
	// So What is the solution here create a Constructor
	// Constructor:-
	// --------------

	public Element_Util(WebDriver driver) {
		this.driver = driver;
		jsUtil = new JavaScript_Util(driver);
	}

	// _____get Element driver.findElement() we can achieve in 3 ways____-
	// (1).
	public WebElement getElement(By Locator) {
		WebElement element = driver.findElement(Locator);
		if (Boolean.parseBoolean(DriverFactory.highlight)) {
			jsUtil.flash(element);

		}
		return element;
	}

	// (2).Using String Locator's
	// (3).
	public WebElement getElement(By Locator, int timeOut) {
		// return driver.findElement(Locator);
		return doPresenceOfElementLocated(Locator, timeOut);
	}

	public void doClear(By Locator) {
		getElement(Locator).clear();

	}

	// _____get Elements driver.findElements() we can achieve in 3 ways____-
	// (1).
	public List<WebElement> getElements(By Locator) {

		return driver.findElements(By.tagName("a"));
	}

	// (2).Using String Locator's

	/*
	 * // ___________(3).Elements Visible______ It's like driver.findElements()
	 * method public List<WebElement> waitForElementsToBeVisible(By Locator, int
	 * timeOut) { WebDriverWait wait = new WebDriverWait(driver,
	 * Duration.ofSeconds(timeOut)); return
	 * wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Locator)); }
	 */

	// click() method we can use 3 ways
	// (1).
	public void doClick(By Locator) {

		getElement(Locator).click();
	}

	// (2).Using String Locator's
	// (3). with some timeinterval
	public void doClick(By Locator, int timeOut) {
		doPresenceOfElementLocated(Locator, timeOut).click();
	}

	// send keys() method we can use 3 ways
	// (1).
	public void doSendkeys(By Locator, String value) {
		doClear(Locator);
		getElement(Locator).sendKeys(value);

		// (2).String Locators

	}
	// (3).
	// ____Waiting Game___
	/*
	 * public void doSendkeys(By locator, int timeout, String value) {
	 * doPresenceOfElementLocated(locator, timeout).sendKeys(value);
	 * 
	 * }
	 */

	public String doGetText(By Locator) {

		return driver.findElement(Locator).getText();
	}

	// Is Element Displayed or not we can check with or with out time Interval
	// method
	// (1).With_out Time interval
	public boolean doIsDisplayed(By Locator) {
		return getElement(Locator).isDisplayed();

	}
	// (2).
	/*
	 * // With Pooling Interval Time: public WebElement waitForElementToBeVisible(By
	 * Locator, int timeOut, long intervalTime) { WebDriverWait wait = new
	 * WebDriverWait(driver, Duration.ofSeconds(timeOut),
	 * Duration.ofMillis(intervalTime)); return
	 * wait.until(ExpectedConditions.visibilityOfElementLocated(Locator)); }
	 */

	public boolean isElementExist(By Locator) {
		int elementCount = getElementsCount(Locator);
		System.out.println("total Elements found");
		if (elementCount >= 1) {

			System.out.println("Element is found" + Locator);
			return true;
		} else {
			return false;
		}
	}

	public int getElementsCount(By Locator) {

		return getElements(Locator).size();
	}

	// GetElement text we can achieve in 2 ways below As Follows
	// (1)p.With_out Time Interval Time:
	public List<String> getElementTextList(By Locator) {
		List<WebElement> eleList = getElements(Locator);
		List<String> eleTextList = new ArrayList<String>();
		for (WebElement e : eleList) {
			String eleText = e.getText();
			if (!eleText.isEmpty()) {
				System.out.println(eleText);
			}
		}
		return eleTextList;
	}

	/*
	 * // (2).With Time Interval Time: public List<String> doElementsgetText(By
	 * Locator, int timeOut) { List<WebElement> list =
	 * waitForElementsToBeVisible(Locator, timeOut); List<String> eletextList = new
	 * ArrayList<String>(); for (WebElement e : list) { String text = e.getText();
	 * eletextList.add(text); } return eletextList; }
	 */

	public String getAttributeValue(By Locator, String attrName) {

		return getElement(Locator).getAttribute(attrName);

	}

	public List<String> getAttributesList(By Locator, String attributeName) {

		List<WebElement> eleList = driver.findElements(Locator);
		List<String> attrList = new ArrayList<String>();
		for (WebElement e : eleList) { // Then do what each & every elemet
			// This is called TestDriven Approch
			String attrvalue = e.getAttribute(attributeName);// here 1 more parameter required which attribute u lookfor
			attrList.add(attrvalue);
		}
		return attrList;
	}

	public void printElementValues(List<String> eleList) {
		for (String e : eleList) {
			System.out.println(e);
		}

	}

	// getElements ---->link text
	public List<String> getLinksTextList(By Locator) {

		List<WebElement> elemList = getElements(Locator);// This is list Of webElments
		List<String> linksTextList = new ArrayList<String>();// This is for list Of Strings
		System.out.println(elemList.size());
		for (WebElement e : elemList) {
			String text = e.getText();
			linksTextList.add(text);

		}
		return linksTextList;
	}

	// Ex: On Google page we are able to seen multiple languages ... Like
	// hindi,Eng,Tamil,Marathi ..etc , So here user clicked on any one of link
	public void clickOnElement(By Locator, String linktext) {

		List<WebElement> langList = getElements(Locator);
		System.out.println(langList.size());
		for (WebElement e : langList) {
			String text = e.getText().trim();
			if (text.equals(linktext)) {
				e.click();
				break;
			}
		}
	}

	// *************************** DropDown Utils************************
	// Generic method for <Select> tag Predefine methods .....
	public void dropDownSelectByIndex(By Locator, int index) {

		Select select = new Select(getElement(Locator));
		select.selectByIndex(index);

	}

	public void dropDownSelectByVisibleText(By Locator, String text) {

		Select select = new Select(getElement(Locator));
		select.selectByVisibleText(text);

	}

	public void dropDownSelectByValue(By Locator, String value) {

		Select select = new Select(getElement(Locator));
		select.selectByValue(value);

	}

	// ****** with Select Tag******** 1 more method=> getOptions()
	// Generic Method to handle to Select any Value from Dropdowns having
	// with <Select> tag
	public void doSelectDropDownValue(By Locator, String value) {
		Select select = new Select(getElement(Locator));

		List<WebElement> optionsList = select.getOptions();
		for (WebElement e : optionsList) {
			String text = e.getText();
			System.out.println(text);
			if (text.equals(value)) {
				e.click();
				break;
			}
		}

	}
	// ****** with_out Select Tag********
	// Generic Method to handle to Seelct any Value from dropdowns having
	// with_out Select tag <Select>

	public void selectDropdownWithOutSelect(By Locator, String value) {
		// List<WebElement> countrylist =
		// driver.findElements(By.xpath("//select[@id='Form_getForm_Country']/option"));
		List<WebElement> optionsList = getElements(Locator);
		for (WebElement e : optionsList) {
			String str = e.getText();
			System.out.println(str);
			if (str.equals(value)) {
				e.click();
				break;
			}

		}

	}

	// @@@@@@@ Jquery DropDown Generic Method @@@@@@

	// (1).for Single Selection
	// (2).MultiSelevtion
	public void selectByChoice(By Locator, String... value) {
		List<WebElement> choicelist = driver.findElements(Locator);
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

	// irpani remember this this method same above One Condition extra added that's
	// all to handle To select multiple values in Dropdown
	// @@@@@@@ Jquery DropDown Generic Method @@@@@@
	// (3).for All Selection
	public void selectByallValues(By Locator, String... value) {
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

	// *******************ACTIONS UTIL **********************************

	// Q:How to deal with Actions ?
	// A:____Simple Logic create webElement then perform Actions _______
	public void doMoveToElement(By Locator) {
		Actions act = new Actions(driver);
		act.moveToElement(getElement(Locator)).build().perform();
	}

	public void doClickOnChildMenu(By parentMenuLocator, By childMenuLocator) throws InterruptedException {

		doMoveToElement(parentMenuLocator);
		Thread.sleep(5000);
		doClick(childMenuLocator);
	}

	// @ ______Action class Sendkeys() method:____
	public void doActionSendkeys(By Locator, String value) {

		Actions act = new Actions(driver);
		act.sendKeys(getElement(Locator), value).build().perform();
	}

	public void doActionSendkeysOnActiveWebElement(By Locator, String value) {

		Actions act = new Actions(driver);
		act.click(getElement(Locator)).sendKeys(value).build().perform();
	}

	// @ ____Action class click() method:____
	public void doActionClick(By Locator) {

		Actions act = new Actions(driver);
		act.click(getElement(Locator)).build().perform();
	}

	public void doActionClickMoveToElement(By Locator) {

		Actions act = new Actions(driver);
		act.moveToElement(getElement(Locator)).click().build().perform();
	}

	// ********Waiting Game *****************
	public void doSendkeys(By locator, int timeOut, String value) {
		doPresenceOfElementLocated(locator, timeOut).sendKeys(value);

	}

	// ______________(1)Presence an Of Element________
	// With_out Pooling Interval Time:
	public WebElement doPresenceOfElementLocated(By Locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.presenceOfElementLocated(Locator));
	}

	// With Pooling Interval Time:
	// Customize Pooling Time also we can write
	// poolingTime/Interval Time both are Equal
	public WebElement doPresenceOfElementLocated(By Locator, int timeOut, long intervalTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofMillis(intervalTime));
		return wait.until(ExpectedConditions.presenceOfElementLocated(Locator));
	}

	// Q: Which One u prefer ? 2(a) or 2(b)
	// A: Always prefer 2(a) because here first created by locator then applied wait
	// condition in 2(b) reverse
	// So always better to use 2(a)
	// ______________(2(a).visibility Of Element________
	// With_out Pooling Interval Time:
	public WebElement waitForElementToBeVisible(By Locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(Locator));
	}

	// With Pooling Interval Time:
	public WebElement waitForElementToBeVisible(By Locator, int timeOut, long intervalTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofMillis(intervalTime));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(Locator));
	}

	// 2(b).Vivibility Of Element with webElement
	public WebElement waitForElementToBeVisibleWithWebElement(By Locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOf(getElement(Locator)));
	}

	// ___________(3).Elements Visible______ It's like driver.findElements() method
	// With_out Pooling Interval Time:
	public List<WebElement> waitForElementsToBeVisible(By Locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Locator));
	}

	// With Pooling Interval Time:
	public List<WebElement> waitForElementsToBeVisible(By Locator, int timeOut, long intervalTime) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofMillis(intervalTime));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(Locator));
	}

	// ________(4).Elements getText ________
	// With_out Pooling Interval Time:
	public List<String> doElementsgetText(By Locator, int timeOut) {
		List<WebElement> list = waitForElementsToBeVisible(Locator, timeOut);
		List<String> eletextList = new ArrayList<String>();
		for (WebElement e : list) {
			String text = e.getText();
			eletextList.add(text);
		}
		return eletextList;
	}

	// With Pooling Interval Time:
	public List<String> doElementsgetText(By Locator, int timeOut, long intervalTime) {
		List<WebElement> list = waitForElementsToBeVisible(Locator, timeOut, intervalTime);
		List<String> eletextList = new ArrayList<String>();
		for (WebElement e : list) {
			String text = e.getText();
			eletextList.add(text);
		}
		return eletextList;
	}

	// ***************Wait For Non webElements*******************
	// NON WEBELEMENTS like URL, Title,alert,Frame .....
	// (1). ________URL __________
	// Validate the Url Contails Or not
	public boolean waitForURLToContain(String urlfraction, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.urlContains(urlfraction));

	}

	// Complete Url Validation
	public boolean waitForURLToBe(String url, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.urlContains(url));

	}

	// (2)._____TITLE ______

	public String doGetTitleWithFraction(String titleFraction, int timeOut) {

		if (waitForTitleContains(titleFraction, timeOut)) {

			return driver.getTitle();
		}
		return null;
	}

	public String doGetTitle(String title, int timeOut) {

		if (waitForTitleToBe(title, timeOut)) {

			return driver.getTitle();
		}
		return null;
	}

	public boolean waitForTitleContains(String titleFraction, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.titleContains(titleFraction));

	}

	public boolean waitForTitleToBe(String title, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.titleIs(title));

	}
	// (3)._________Alert Method____________

	public Alert waitForAlert(int timeOut) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.alertIsPresent());

	}

	public String doAlertText(int timeOut) {
		return waitForAlert(timeOut).getText();

	}

	public void doAlertAccept(int timeOut) {
		waitForAlert(timeOut).accept();

	}

	public void doAlertDismiss(int timeOut) {
		waitForAlert(timeOut).dismiss();

	}

	public void doAlertSendKeys(int timeOut, String value) {
		waitForAlert(timeOut).sendKeys(value);

	}

	// (4).____Frame_____
	// Used this method to Switch On particulr frame
	public void waitForFrameByNameOrID(String nameID, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(nameID));
	}

	public void waitForFrameByNameIndex(int index, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(index));
	}

	public void waitForFrameByFrameLocator(By FrameLocator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(FrameLocator));
	}

	public void waitForFrameByElement(WebElement frameElement, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameElement));
	}

	// (5).____It Could be used for any Locator_____
	// may be link,checkbox,input field ... very very nice Utility ..

	// With_out Pooling Int Time
	public void clickElemenetWhenReady(By Locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		wait.until(ExpectedConditions.visibilityOfElementLocated(Locator)).click();

	}

	// With Pooling Int Time
	public void clickElemenetWhenReady(By Locator, int timeOut, long intTimeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofMinutes(500));
		wait.until(ExpectedConditions.visibilityOfElementLocated(Locator)).click();

	}

	// _____Fluent Wait________
	// Note :Don't pass exceptions as parameter in the .ignoring() not a good
	// approch
	public WebElement waitForElementPresentUsingFluentWait(By Locator, int timeOut, int pollint) {
		// ___Builder Pattern Patch ___ Chaning of methods ..._
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
				.pollingEvery(Duration.ofMillis(pollint)).ignoring(NoSuchElementException.class)
				.withMessage(Err.ELEMENT_NOT_FOUND_ERROR_MESSAGE); // Liyan return like this ...

		return wait.until(ExpectedConditions.presenceOfElementLocated(Locator));

	}

	// we can write WebDriverWait also same like fluentWait
	public WebElement waitForElementUsingWebDriverWait(By Locator, int timeOut, int pollint) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));

		wait.withTimeout(Duration.ofSeconds(timeOut)).pollingEvery(Duration.ofMillis(pollint))
				.ignoring(NoSuchElementException.class).withMessage(Err.ELEMENT_NOT_FOUND_ERROR_MESSAGE);// Liyan
																											// return
																											// like this
																											// ...

		return wait.until(ExpectedConditions.presenceOfElementLocated(Locator));

	}

	// *********************Custom Wait*********************
	// ____With_Out Time poll Interval _____ These 2 methods are powerful than
	// selenium waits
	// I create My own Custom_Wait
	public WebElement retryingElement(By Locator, int timeOut) {

		WebElement element = null;
		int attempts = 0;
		while (attempts < timeOut) {
			try {
				element = getElement(Locator);
				break;
			} catch (NoSuchElementException e) {
				e.printStackTrace();
				System.out.println("Element is not foud in attempt:" + attempts + ":" + Locator);
			}

			try {
				Thread.sleep(500); // Default TimeOut - 500 miiliSec Only ....
			} catch (InterruptedException e1) {

				e1.printStackTrace();
			}
			attempts++;
		}
		// I return customose Exception

		if (element == null) {
			try {
				throw new Exception();
			} catch (Exception e) {
				System.out
						.println("Element is not foud in attempt tried for" + timeOut + "with the interval of:" + 1000);
			}

		}
		return element;

	}

	// ____With Time poll Interval _____
	public WebElement retryingElement(By Locator, int timeOut, long pollingTime) {

		WebElement element = null;
		int attempts = 0;
		while (attempts < timeOut) {
			try {
				element = getElement(Locator);
				break;
			} catch (NoSuchElementException e) {
				e.printStackTrace();
				System.out.println("Element is not foud in attempt:" + attempts + ":" + Locator);
			}

			try {
				Thread.sleep(pollingTime); // Default TimeOut - 500 miiliSec Only ....
			} catch (InterruptedException e1) {

				e1.printStackTrace();
			}
			attempts++;
		}
		// I return customose Exception

		if (element == null) {
			try {
				throw new Exception();
			} catch (Exception e) {
				System.out.println(
						"Element is not foud in attempt tried for" + timeOut + "with the interval of:" + pollingTime);
			}

		}
		return element;

	}

}
