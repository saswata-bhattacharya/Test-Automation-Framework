package com.ui.tests;

import static com.constants.Browser.*;

import static org.testng.Assert.*;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.ui.pages.HomePage;
import com.ui.pages.LoginPage;
import com.ui.pojo.User;

public class LoginTests {

	HomePage homePage;

	@BeforeMethod(description = "Load HomePage of the application")
	public void setUp() {
		homePage = new HomePage(CHROME);
	}

	@Test(description = "Verifies a valid user is able to login in application", groups = { "E2E",
			"Sanity" }, dataProviderClass = com.ui.dataproviders.LoginDataProvider.class, dataProvider = "LoginTestDataProvider")
	public void loginTest(User user) {

		assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName(),
				"Saswata Bhattacharya");
	}

}
