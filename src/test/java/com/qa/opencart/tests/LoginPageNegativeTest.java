package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginPageNegativeTest extends BaseTest {

	@DataProvider
	public Object[][] loginWrongtestData() { // This method return what?A: 2-D Of Object[][]
		return new Object[][] {

				{ "test11@gmail.com", "test@123" }, { "naveenanimation20@gmail.com", "test@123" }, { " ", "test@123" },
				{ "naveenanimation20@gmail.com", " " }, { " ", "" } };

	}

	@Test(dataProvider = "loginWrongtestData")
	public void loginNegativeTest(String username, String password) {

		Assert.assertFalse(loginPage.doLoginWithWrongCredentials(username, password));

	}

}
