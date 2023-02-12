package com.nopcommerce.common;

import java.util.Set;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import commons.BaseTest;
import pageObject.nopCommerce.portal.PageGeneratorManager;
import pageObject.nopCommerce.portal.UserHomePageObject;
import pageObject.nopCommerce.portal.UserLogInPageObject;
import pageObject.nopCommerce.portal.UserRegisterPageObject;

public class  Common_01_Login extends BaseTest{
	
	private WebDriver driver;
	private String emailAddress, firstName, lastName, password;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLogInPageObject loginPage;
	public static Set<Cookie> loginPageCookie;
	
	@Parameters({"browser" , "url"})
	@BeforeTest
	public void beforeTest(String browserName, String appUrl) {
		log.info("Pre-Condition - STEP 01: Open browser '" + browserName + "' and  navigate to '" + appUrl + "' ");
		driver = getBrowserDriver(browserName, appUrl);
		homePage = PageGeneratorManager.getUserHomePage(driver);
		
		emailAddress = "auto" + getRamdomNumber() + "@mail.vn";
		firstName = "automation";
		lastName = "fc";
		password = "123456";
		
		log.info("Common_01_Login - Step 01: Click to register link");
		registerPage =  homePage.clickToRegisterLink();
		
		log.info("Common_01_Login - Step 02: Enter to First Name textbox with value " + firstName);
		registerPage.inputToFirstNameTextBox(firstName);
		
		log.info("Common_01_Login - Step 03: Enter to Last Name textbox with value " + lastName);
		registerPage.inputToLastNameTextBox(lastName);
		
		log.info("Common_01_Login - Step 04: Enter to Email textbox with value " + emailAddress);
		registerPage.inputToEmailTextBox(emailAddress);
		
		log.info("Common_01_Login - Step 05: Enter to Password textbox with value " + password);
		registerPage.inputToPasswordTextBox(password);
		
		log.info("Common_01_Login - Step 06: Enter to Confirm Password textbox with value " + password);
		registerPage.inputToConfirmPasswordTextBox(password);
		
		log.info("Common_01_Login - Step 07: Click to Register button");
		registerPage.clickToRegisterButton();
		
		log.info("Common_01_Login - Step 08: Verify success message displayed");
		verifyEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
		
		log.info("Common_01_Login - Step 09: Click to Logout link");
		homePage = registerPage.clickToLogOutLink();
		
		log.info("Common_01_Login - Step 10: Click to Login link");
		loginPage = homePage.openLoginPage();
		
		log.info("Common_01_Login - Step 11: Enter to Email textbox with value" + emailAddress);
		loginPage.inputToEmailTextBox(emailAddress);
		
		log.info("Common_01_Login - Step 12: Enter to Password textbox with value " + password);
		loginPage.inputToPasswordTextBox(password);

		log.info("Common_01_Login - Step 13: Click to Login Button");
		homePage = loginPage.clickToLoginButton();
		
		log.info("Common_01_Login - Step 14: Verify My Account link is displayed");
		verifyFalse(homePage.isMyAccountLinkDisplayed());
		
		log.info("Common_01_Login - Step 15: Get all cookie");
		loginPageCookie = homePage.getAllCookies(driver);
		
		log.info("Post-Condition - Step 16: Close browser");
		driver.quit();
	}

}