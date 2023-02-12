package com.nopcommerce.user;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import commons.BaseTest;
import pageObject.nopCommerce.portal.PageGeneratorManager;
import pageObject.nopCommerce.portal.UserHomePageObject;
import pageObject.nopCommerce.portal.UserLogInPageObject;
import pageObject.nopCommerce.portal.UserRegisterPageObject;
import reportConfig.ExtentManager;


public class  Level_13_Register_Login_Log_Extent_Report extends BaseTest{
	@Parameters({"browser" , "url"}) 
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		homePage = PageGeneratorManager.getUserHomePage(driver);
		
		emailAddress = "auto" + getRamdomNumber() + "@mail.vn";
		firstName = "automation";
		lastName = "fc";
		password = "123456";
		
	}

	@Test
	public void User_01_Register(Method method) {
		ExtentManager.startTest(method.getName(), "User_01_Register");
		ExtentManager.getTest().log(Status.INFO, "User_01_Register - Step 01: Click to register link");
		registerPage =  homePage.clickToRegisterLink();
		
		ExtentManager.getTest().log(Status.INFO, "User_01_Register - Step 02: Enter to First Name textbox with value " + firstName);
		registerPage.inputToFirstNameTextBox(firstName);
		
		ExtentManager.getTest().log(Status.INFO, "User_01_Register - Step 03: Enter to Last Name textbox with value " + lastName);
		registerPage.inputToLastNameTextBox(lastName);
		
		ExtentManager.getTest().log(Status.INFO, "User_01_Register - Step 04: Enter to Email textbox with value " + emailAddress);
		registerPage.inputToEmailTextBox(emailAddress);
		
		ExtentManager.getTest().log(Status.INFO, "User_01_Register - Step 05: Enter to Password textbox with value " + password);
		registerPage.inputToPasswordTextBox(password);
		
		ExtentManager.getTest().log(Status.INFO, "User_01_Register - Step 06: Enter to Confirm Password textbox with value " + password);
		registerPage.inputToConfirmPasswordTextBox(password);
		
		ExtentManager.getTest().log(Status.INFO, "User_01_Register - Step 07: Click to Register button");
		registerPage.clickToRegisterButton();
		
		ExtentManager.getTest().log(Status.INFO, "User_01_Register - Step 08: Verify success message displayed");
		verifyEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed..");
		
	
		
	}
	
	@Test
	public void User_02_Login(Method method) {
		ExtentManager.startTest(method.getName(), "User_02_Login");
		ExtentManager.getTest().log(Status.INFO, "User_02_Login - Step 1: Click to Login link");
		loginPage = homePage.openLoginPage();
		
		ExtentManager.getTest().log(Status.INFO, "User_02_Login - Step 2: Enter to Email textbox with value" + emailAddress);
		loginPage.inputToEmailTextBox(emailAddress);
		
		ExtentManager.getTest().log(Status.INFO, "User_02_Login - Step 3: Enter to Password textbox with value " + password);
		loginPage.inputToPasswordTextBox(password);

		ExtentManager.getTest().log(Status.INFO, "User_02_Login - Step 4: Click to Login Button");
		homePage = loginPage.clickToLoginButton();
		
		ExtentManager.getTest().log(Status.INFO, "User_02_Login - Step 5: Verify My Account link is displayed");
		verifyTrue(homePage.isMyAccountLinkDisplayed());
		
	}
	

	@AfterClass
	public void afterClass() {
		
		
		driver.quit();
	}
	private WebDriver driver;
	private String emailAddress, firstName, lastName, password;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private UserLogInPageObject loginPage;


}
