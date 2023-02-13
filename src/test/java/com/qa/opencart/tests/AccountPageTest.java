package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.utils.Constants;

public class AccountPageTest extends BaseTest {

	// To reach the Accounts Page What is the Pre_Condition?
	// A:Precondiiton for Accout page is
	// Launch the Browser ente the Url name & Password then User Should Logged in

	// Q:Who will Login Now ???? Some One will Login ?
	// Q:How do u handle that ?

	// This is related to Only AccountPage related Precondiiton Case

	@BeforeClass
	public void accPageSetup() {
		// This below line returns new Page Object Can we store in AccountsPage Ref=>
		// accountsPage;
		// Here LoginPage doLogin() with Account Page Couple together ...
		// This is Two Level Chaining In up coming session we can see three level & four
		// Level Chaining ...
		AccountsPage accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));

	}

	@Test(priority = 1)
	public void accPageTitleTest() {
		String actTitile = accountsPage.getAccountTitlePage();
		System.out.println("Actual Title is" + actTitile);
		Assert.assertEquals(actTitile, Constants.ACCOUNT_PAGE_TITLE);

	}

	@Test(priority = 2)
	public void accPageHeaderTest() {

		String act_Header = accountsPage.getAccountsPageHeader();
		System.out.println(act_Header);
		Assert.assertEquals(act_Header, Constants.ACCOUNT_PAGE_HEADER);
	}

	@Test(priority = 3)
	public void logoutLinkExist() {
		Assert.assertTrue(accountsPage.isLogoutLinkExist());

	}

	@Test(priority = 4)
	public void accountSctionsText() {

		List<String> actAccSecList = accountsPage.getAccountSecList();
		// So how will u Asser this ?
		// Do we need to create expected list so that we will compare both lists ?
		List<String> expList = Constants.getExpectedAccSecList();
		Assert.assertEquals(actAccSecList, Constants.getExpectedAccSecList());
		// Assert.assertEquals() == Compare the Actual vs Exp
	}

	// Q:will u provide @DataProvider for every @Test
	// @DataProvider means -- testDataProvider
	// A: No when we required then only we will create it ...

	// The @Test method that wants to receive data from this DataProviderneeds to
	// use a dataProvider name equals to the name of this annotation.
	@DataProvider
	public Object[][] productData() {

		return new Object[][] { { "MacBook Pro" }, { "Apple" }, { "Samsung" } };
		// It's behave like Excel 1_Column 3_Rows ,Later we will use Apache Poi Excel
	}

	// we will establish a Connection from DaatProvider to @Test Simple call it
	@Test(priority = 5, dataProvider = "productData") // Here I need testData who will provide testdata for me ...
	public void searchTest(String productName) {
		accountsPage.doSerach("MaBookpro"); // Productname this is Constant or testdata?

	} // A: This is testData we will discusss it later

}
