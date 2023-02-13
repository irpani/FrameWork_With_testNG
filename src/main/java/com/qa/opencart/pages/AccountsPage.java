package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.Element_Util;

public class AccountsPage {

	// When Ever you are Creating Any page class Either login page ,Reg
	// Page,Checkout page ,Add To Cart Page

	// (1).First thing is Initialize it's own Private webDriver ...
	// (2).How do u Initiaize this Driver? with the help Of Constructor
	// (3).Private By Locator's[What are the different elemnts involved in Accouts
	// Page]
	// (4).Page Actions
	// (1).
	private WebDriver driver;
	private Element_Util eleUtil;

	// (2).
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new Element_Util(driver); // the same driver given to Element Util Page
	}

	// (3).Page Locator's [What are the different elemnts involved in Accouts Page
	private By header = By.cssSelector("div#logo a");
	private By accountsSelections = By.cssSelector("div#content h2");
	private By searchField = By.name("search");
	private By searchButton = By.cssSelector("div#search button");
	private By logoutLink = By.linkText("Logout");

	// (4).Page Actions
	public String getAccountTitlePage() {

		return eleUtil.doGetTitle(Constants.ACCOUNT_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);

	}

	public String getAccountsPageHeader() {

		return eleUtil.doGetText(header);

	}

	// Then what else u want to verify
	public boolean isLogoutLinkExist() {
		return eleUtil.isElementExist(logoutLink);

	}

	public void logout() {

		if (isLogoutLinkExist()) {
			eleUtil.doClick(logoutLink);
		}

	}

	public List<String> getAccountSecList() {

		List<WebElement> accSecList = eleUtil.waitForElementsToBeVisible(accountsSelections, 10);
		List<String> accSecValList = new ArrayList<String>();
		for (WebElement e : accSecList) {
			String text = e.getText();
			accSecValList.add(text);
		}
		return accSecValList;
	}

	public boolean searchExist() {

		return eleUtil.isElementExist(searchField);

	}

	// For performing search I need some productname
	public SearchResultsPage doSerach(String productName) {
		System.out.println("Searching the product" + productName);
		eleUtil.doSendkeys(searchField, productName);
		eleUtil.doClick(searchButton);
		return new SearchResultsPage(driver);
	}
	// After Searching It will land On anther Page
	// This is my search Results page =>searchResultsPage.java
	// Q: Tell me one thing where I should create locator for this Page ?
	// Do I have to maintain these locators in Account Page (or)
	// new Page searchResultsPage.java???
	// A: we Should Maintain New Page for this

}
