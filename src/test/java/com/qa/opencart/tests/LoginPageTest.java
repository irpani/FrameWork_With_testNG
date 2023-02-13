package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.utils.Constants;

//First Rule Of Automation Testing is validate the Happy path scenario's 
//Automation Not for Every time Negative testcases ...
//If your Negative Scenario's are more complex better do it manually ...
//Automation prurpose imp Business cases we will automate that ....
public class LoginPageTest extends BaseTest {

	// I don't want to write driver Api's hers in this test class
	// we should write Assertions in this test class Cross check the Expected Vs
	// Actual

	// I want to call all the methods Of LoginPageClass How to call non static
	// methods?
	// With Object Creation We call the loginPage class methods ...
	// Where exactly I create Objec fo that class ?

	@Test(priority = 1)
	public void loginPageTitleTest() {
		String actTitle = loginPage.getLoginPageTitle();
		System.out.println("Actual Title is.." + actTitle);// I'm getting a title how can I verify
		Assert.assertEquals(actTitle, Constants.LOGIN_PAGE_TITLE);
	}

	@Test(priority = 2)
	public void loginPageUrlTest() {

		Assert.assertTrue(loginPage.loginpageURL());

	}

	@Test(priority = 3)
	public void forgotPwdLinkTest() {

		boolean flag = loginPage.isforgotpwdlinkExist();
		Assert.assertTrue(flag);
	}

	@Test(priority = 4)
	public void regLinkTest() {

		Assert.assertTrue(loginPage.isReglinkExist());
	}

	// This method returns void How can we validate this -> we can see next session
	// **** when ever we clicked no Button/Link It navigating to other page then the
	// method responsibity to give the next landing page *****

	@Test(priority = 5)
	public void loginTest() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(accountsPage.getAccountTitlePage(), Constants.ACCOUNT_PAGE_TITLE);
		// So After landing on LoginPage -- Account page I just validate the title of
		// new page So we Conclude that we are on Account page Now
	}

}

// @Test(priority = 5) So this is the case Correct Usename & Password What about
// (0).Correct username <---> Correct Password --- Already Handled .....
// What about these Cases ????
// (1).Wrong Userame <---> Correct Password
// (2).Correct username <---> Wrong password
// (3).Wrong Username <---> Wrong Password

// See 2 Practices Generally to follow
// first thing is that u can write one more @Test Annotation for -ve case Second
// one
// Second thing Create a seperate class for this {Liyan also follow this ....}

// I will create a Seperate class for negative Scenario's first is for Positive
// case
// we already seen @Test 5