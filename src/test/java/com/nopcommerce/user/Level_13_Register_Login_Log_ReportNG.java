package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import pageObject.nopCommerce.portal.PageGeneratorManager;
import pageObject.nopCommerce.portal.UserHomePageObject;
import pageObject.nopCommerce.portal.UserLogInPageObject;
import pageObject.nopCommerce.portal.UserRegisterPageObject;

@Epic("Web")
@Feature("User")
public class  Level_13_Register_Login_Log_ReportNG extends BaseTest{
	@Parameters({"browser" , "url"})
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		log.info("Pre-Condition - STEP 01: Open browser '" + browserName + "' and  navigate to '" + appUrl + "' ");
		driver = getBrowserDriver(browserName, appUrl);
		homePage = PageGeneratorManager.getUserHomePage(driver);
		
		emailAddress = "auto" + getRamdomNumber() + "@mail.vn";
		firstName = "automation";
		lastName = "fc";
		password = "123456";
		
	}
	@Story("Register")
	@Severity(SeverityLevel.NORMAL)
	@Description("Register to system and check log in success")
	@Test
	public void User_01_Register_Login() {
		log.info("User_01_Register - Step 01: Click to register link");
		registerPage =  homePage.clickToRegisterLink();
		
		log.info("User_01_Register - Step 02: Enter to First Name textbox with value " + firstName);
		registerPage.inputToFirstNameTextBox(firstName);
		
		log.info("User_01_Register - Step 03: Enter to Last Name textbox with value " + lastName);
		registerPage.inputToLastNameTextBox(lastName);
		
		log.info("User_01_Register - Step 04: Enter to Email textbox with value " + emailAddress);
		registerPage.inputToEmailTextBox(emailAddress);
		
		log.info("User_01_Register - Step 05: Enter to Password textbox with value " + password);
		registerPage.inputToPasswordTextBox(password);
		
		log.info("User_01_Register - Step 06: Enter to Confirm Password textbox with value " + password);
		registerPage.inputToConfirmPasswordTextBox(password);
		
		log.info("User_01_Register - Step 07: Click to Register button");
		registerPage.clickToRegisterButton();
		
		log.info("User_01_Register - Step 08: Verify success message displayed");
		verifyEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
		
		log.info("User_01_Register - Step 09: Click to Logout link");
		homePage = registerPage.clickToLogOutLink();
		
	}
	
	@Story("Log in")
	@Severity(SeverityLevel.NORMAL)
	@Description("Log in to system with valid data")
	@Test
	public void User_02_Login() {
		log.info("User_02_Login - Step 1: Click to Login link");
		loginPage = homePage.openLoginPage();
		
		log.info("User_02_Login - Step 2: Enter to Email textbox with value" + emailAddress);
		loginPage.inputToEmailTextBox(emailAddress);
		
		log.info("User_02_Login - Step 3: Enter to Password textbox with value " + password);
		loginPage.inputToPasswordTextBox(password);

		log.info("User_02_Login - Step 4: Click to Login Button");
		homePage = loginPage.clickToLoginButton();
		
		log.info("User_02_Login - Step 5: Verify My Account link is displayed");
		verifyFalse(homePage.isMyAccountLinkDisplayed());
		
	}
	

	@AfterClass
	public void afterClass() {
		log.info("Post-Condition - STEP 01: Close browser");
		driver.quit();
	}
	private WebDriver driver;
	private String emailAddress, firstName, lastName, password;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLogInPageObject loginPage;


}
