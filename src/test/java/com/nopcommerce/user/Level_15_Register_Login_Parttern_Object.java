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
public class Level_15_Register_Login_Parttern_Object extends BaseTest {
	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		log.info("Pre-Condition - STEP 01: Open browser '" + browserName + "' and  navigate to '" + appUrl + "' ");
		driver = getBrowserDriver(browserName, appUrl);
		homePage = PageGeneratorManager.getUserHomePage(driver);

		emailAddress = "auto" + getRamdomNumber() + "@mail.vn";
		firstName = "automation";
		lastName = "fc";
		password = "123456";
		date = "21";
		month = "January";
		year = "1991";
	}

	@Story("Register")
	@Severity(SeverityLevel.NORMAL)
	@Description("Register to system and check log in success")
	@Test
	public void User_01_Register_Login() {
		log.info("User_01_Register - Step 01: Click to register link");
		homePage.openHeaderPageByName(driver, "Register");
		registerPage = PageGeneratorManager.getUserRegisterPage(driver);

		log.info("User_01_Register - Step 02: Click to male radio button");
		registerPage.clickToGenderRadioButtonByID(driver, "gender-male");

		log.info("User_01_Register - Step 03: Enter to First Name textbox with value " + firstName);
		registerPage.inputToTextboxByID(driver, "FirstName", "Jhon");

		log.info("User_01_Register - Step 04: Enter to Last Name textbox with value " + lastName);
		registerPage.inputToTextboxByID(driver, "LastName", "Terry");

		log.info("User_01_Register - Step 05: Select item in Date dropdown with value " + date);
		registerPage.selectDropDownByName(driver, "DateOfBirthDay", date);
		
		log.info("User_01_Register - Step 06: Select item in Month dropdown with value " + month);
		registerPage.selectDropDownByName(driver, "DateOfBirthMonth", month);
		
		log.info("User_01_Register - Step 07: Select item in Year dropdown with value " + year);
		registerPage.selectDropDownByName(driver, "DateOfBirthYear", year);

		log.info("User_01_Register - Step 08: Enter to Email textbox with value " + emailAddress);
		registerPage.inputToTextboxByID(driver, "Email", emailAddress);

		log.info("User_01_Register - Step 09: Enter to Password textbox with value " + password);
		registerPage.inputToTextboxByID(driver, "Password", password);

		log.info("User_01_Register - Step 10: Enter to Confirm Password textbox with value " + password);
		registerPage.inputToTextboxByID(driver, "ConfirmPassword", password);

		log.info("User_01_Register - Step 11: Click to Register button");
		registerPage.openHeaderPageByName(driver, "Register");

		log.info("User_01_Register - Step 12: Verify success message displayed");
		verifyEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

		log.info("User_01_Register - Step 13: Click to Logout link");
		registerPage.openHeaderPageByName(driver, "Log out");
		homePage = PageGeneratorManager.getUserHomePage(driver);
		
		
		
	}

	@Story("Log in")
	@Severity(SeverityLevel.NORMAL)
	@Description("Log in to system with valid data")
	@Test
	public void User_02_Login() {
		log.info("User_02_Login - Step 1: Click to Login link");
		homePage.openHeaderPageByName(driver, "Log in");
		loginPage = PageGeneratorManager.getUserLoginPage(driver);

		log.info("User_02_Login - Step 2: Enter to Email textbox with value" + emailAddress);
		loginPage.inputToTextboxByID(driver, "Email", emailAddress);

		log.info("User_02_Login - Step 3: Enter to Password textbox with value " + password);
		loginPage.inputToTextboxByID(driver, "Password", password);

		log.info("User_02_Login - Step 4: Click to Login Button");
		loginPage.clickToButtonByText(driver, "Log in");
		homePage = PageGeneratorManager.getUserHomePage(driver);

		log.info("User_02_Login - Step 5: Verify My Account link is displayed");
		verifyFalse(homePage.isMyAccountLinkDisplayed());

	}

	@AfterClass
	public void afterClass() {
		log.info("Post-Condition - STEP 01: Close browser");
		driver.quit();
	}

	private WebDriver driver;
	private String emailAddress, firstName, lastName, password, date, month, year;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLogInPageObject loginPage;

}
