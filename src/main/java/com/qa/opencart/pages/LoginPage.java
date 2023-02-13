package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.utils.Constants;
import com.qa.opencart.utils.Element_Util;
//_____________Case (1):______________
//When Ever you are Creating Any page class Either login page ,Reg Page,Checkout page ,Add To Cart Page

//(1).First thing is Initialize it's own Private webDriver ...
//(2).How do u Initiaize this Driver? with the help Of Constructor
//(3).Private By Locator's
//(4).Page Actions
//Conclusion -- See the Page Structure Did we write any Assertion here  ? A: No
//Assertion is the Property Of testNg Right So never Right  Assertions in Page Acttion Methods

//____________Case(2):  Element Util_____________
//(1).In Login Page How Can I use here in  Element Util ?? doclick(),doSendkeys(),doDisplay() ..
//methods in this page So how will u use those methods Of element util ?
//(2).WE Have to create the Object of the Element Util then give driver to the Constructor

public class LoginPage {

	// (1).Declare Private driver
	private WebDriver driver; // Initialize it's Own Private webDriver ...
	private Element_Util eleUtil;

	// (2).How do u Initiaize this Driver? (A):With Constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new Element_Util(driver); // And supply the same driver ,
		// here element util got driver Which driver? This driver is pointing to chrome
		// driver
		// The same driver given to this guy..
	}
	// we don't write super() here becase we don't have super class of login class
	// So y do u want super keyword here ?
	// (3).Private By Locator's [Let me create Private By Locator's]

	private By email = By.name("email");
	private By password = By.name("password");
	private By login = By.linkText("Forgotten Password");
	private By register = By.linkText("Register");
	private By forgotpwdlink = By.linkText("Forgotten Password");
	private By errorMessage = By.cssSelector("div.alert.alert-danger.alert-dismisaable");

	// (4).Page Actions Note:Assertions never return under this Page Action Methods
	public String getLoginPageTitle() {

		return eleUtil.doGetTitle(Constants.LOGIN_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
	}

	public boolean loginpageURL() {

		return eleUtil.waitForURLToContain(Constants.LOGIN_PAGE_URL_FRACTION, Constants.DEFAULT_TIME_OUT);
	}

	public boolean isforgotpwdlinkExist() {
		return eleUtil.doIsDisplayed(forgotpwdlink);
	}

	public boolean isReglinkExist() {
		return eleUtil.doIsDisplayed(register);
	}

	// **** when ever we clicked no Button/Link It navigating to other page then the
	// method responsibity to give the next landing page *****
	// This is called Method Chaining Concept
	// So here dologin() method responsibility to give the next Landing Page Object
	public AccountsPage doLogin(String un, String pwd) {
		System.out.println("username" + un + "Password" + pwd);
		eleUtil.doSendkeys(email, un);
		eleUtil.doSendkeys(password, pwd);
		eleUtil.doClick(login);
		return new AccountsPage(driver);
		// this method Should return Object Of the Next landing page
		// How would I write the Object here ?
		// =>return new AccountsPage(driver)
	}

	// I don't want to disturb above method this is for Wrong username & password ..
	public boolean doLoginWithWrongCredentials(String un, String pwd) {
		System.out.println("Try to login with wrong credentials " + "username" + un + "Password" + pwd);
		eleUtil.doSendkeys(email, un);
		eleUtil.doSendkeys(password, pwd);
		eleUtil.doClick(login);
		String errorMsg = eleUtil.doGetText(errorMessage);
		System.out.println(errorMsg);
		if (errorMsg.contains(Constants.LOGIN_ERROR_MESSG)) {
			System.out.println("Login is Not Successfull");
			return false;
		}
		return true;
		// this method Should return Object Of the Next landing page
		// How would I write the Object here ?
		// =>return new AccountsPage(driver)
	}

	public RegistrationsPage goToRegisterPage() {
		eleUtil.doClick(register);

		return new RegistrationsPage(driver); // This is TDD Approch where One page Landing On another Page
		// Here from Login Page Clicked On Reg Link It will navigating to the
		// RegistrationPage ...
	}

}
