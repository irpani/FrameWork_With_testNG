package com.qa.opencart.utils;

import java.util.ArrayList;
import java.util.List;

public class Constants {

	public static final String LOGIN_PAGE_TITLE = "Account Login";
	public static final String LOGIN_ERROR_MESSG = "No match for EmailAdress and/or password";
	public static final String ACCOUNT_PAGE_TITLE = "My Account";
	public static final String ACCOUNT_PAGE_HEADER = "Your Store";
	public static final String LOGIN_PAGE_URL_FRACTION = "account/login";
	public static final int DEFAULT_TIME_OUT = 5;

	public static final String REGISTER_SHEET_NAME = "registration";

	public static List<String> getExpectedAccSecList() {

		List<String> explist = new ArrayList<String>();

		explist.add("My Account");
		explist.add("My Orders");
		explist.add("My Affliate Account");
		explist.add("NewsLetter");
		return explist;
	}

}
