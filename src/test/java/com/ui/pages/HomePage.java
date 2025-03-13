package com.ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.constants.Browser;
import static com.constants.Env.*;
import com.utility.BrowserUtility;
import com.utility.JSONUtility;

import static com.utility.PropertiesUtil.*;

public final class HomePage extends BrowserUtility{
	
	private static final By SIGN_IN_LINK_LOCATOR = By.xpath("//a[contains(text(),\"Sign in\")]");
	
	public HomePage(Browser browserName) {
		super(browserName);
		//goToWebsite(readProperty(QA, "URL"));
		goToWebsite(JSONUtility.readJSON(QA));
	}
	
	public LoginPage goToLoginPage() { //Page Functions should return something -- void should not be used
		clickOn(SIGN_IN_LINK_LOCATOR);
		LoginPage loginPage = new LoginPage(getDriver());
		return loginPage;
		
	}

}
