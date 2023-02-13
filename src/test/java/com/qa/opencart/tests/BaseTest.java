package com.qa.opencart.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.Factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegistrationsPage;
import com.qa.opencart.pages.SearchResultsPage;

public class BaseTest {
	DriverFactory df;
	Properties prop;
	WebDriver driver;
	// _____All Page References I will Maintained here ______
	LoginPage loginPage;
	AccountsPage accountsPage;
	SearchResultsPage searchResultPage;
	ProductInfoPage productInfoPage;
	RegistrationsPage registrationPage;
	SoftAssert softassert;

	@BeforeTest // This Base test will give us the Login Page ....
	public void setup() {

		df = new DriverFactory(); // Create Object Of driver Factory
		prop = df.init_prop(); // After Creation Of object Start calling the methods in it
		driver = df.int_driver(prop); // Call the method this method retuntype webDriver so assigned it
		loginPage = new LoginPage(driver); // Create Object Of LoginPage --> class
	} // -> is Global Precondition for All Scenario's

	@AfterTest
	public void tearDown() {

		driver.quit();

	}

}
