package com.nopcommerce.user;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageFactory.HomePageObject;
import pageFactory.LogInPageObject;
import pageFactory.RegisterPageObject;

public class  Level_05_Page_Object extends BaseTest{
	private WebDriver driver;
	private String existingEmail, notFoundEmail, invalidEmail, firstName, lastName, password;
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private LogInPageObject loginPage;
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		System.out.println("Run on " + browserName);
		driver = getBrowserDriver(browserName);
		homePage = new HomePageObject(driver);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("https://demo.nopcommerce.com/");
		
		existingEmail = "auto" + getRamdomNumber() + "@mail.vn";
		notFoundEmail = "auto" + getRamdomNumber() + "@mail.net";
		invalidEmail = "auto" + getRamdomNumber() + "net.vn";
		firstName = "automation";
		lastName = "fc";
		password = "123456";
		
		System.out.println("Pre-condition - Step 1 - Click to register link");
		homePage.clickToRegisterLink();
		// Khởi tạo trang register
		registerPage = new RegisterPageObject(driver);
		
		System.out.println("Pre-condition  - Step 2 - Input to required field");
		registerPage.inputToFirstNameTextBox(firstName);
		registerPage.inputToLastNameTextBox(lastName);
		registerPage.inputToEmailTextBox(existingEmail);
		registerPage.inputToPasswordTextBox(password);
		registerPage.inputToConfirmPasswordTextBox(password);

		System.out.println("Pre-condition  - Step 3 - Click to register button ");
		registerPage.clickToRegisterButton();

		System.out.println("Pre-condition  - Step 4 - Verify register success message displayed - ");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

		System.out.println("Pre-condition  - Step 5 - Click to log out button ");
		registerPage.clickToLogOutButton();
		// Click log out sẽ quay về trang Homepage
		homePage = new HomePageObject(driver);
	}

	@Test
	public void Login_01_Empty_Data() {
		System.out.println("Login_01 - Step 1- Click to login link");
		homePage.clickToLoginLink();
		loginPage = new LogInPageObject(driver);
		System.out.println("Login_01 - Step 2 - Click to login button");
		loginPage.clickToLoginButton();
		
		System.out.println("Login_01 - Step 3 - Verify error message displayed");
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextBox(), "Please enter your email");
		
		
	}

	@Test
	public void Login_02_Invalid_Email() {
		System.out.println("Login_02 - Step 1 - Click to login link");
		homePage.clickToLoginLink();
		loginPage = new LogInPageObject(driver);
		
		System.out.println("Login_02 - Step 2 - Input to required field");
		loginPage.inputToEmailTextBox(invalidEmail);
		loginPage.inputToPasswordTextBox(password);
		
		System.out.println("Login_02 - Step 3 - Click to login button");
		loginPage.clickToLoginButton();

		System.out.println("Login_02 - Step 4 - Verify error message displayed");
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextBox(), "Wrong email");
	}

	@Test
	public void Login_03_Email_Not_Found() {
		System.out.println("Login_03 - Step 1 - Click to login link");
		homePage.clickToLoginLink();
		loginPage = new LogInPageObject(driver);
		
		System.out.println("Login_03 - Step 1 - Input to required field");
		loginPage.inputToEmailTextBox(notFoundEmail);
		loginPage.inputToPasswordTextBox(password);

		System.out.println("Login_03 - Step 3 - Click to login button");
		loginPage.clickToLoginButton();
		
		System.out.println("Login_03 - Step 4 - Verify error message displayed");
		Assert.assertEquals(loginPage.getErrorMessageAtHeaderTextBox(), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");
	}

	@Test
	public void Login_04_Existing_Email_Empty_Password() {
		
		System.out.println("Login_04 - Step 1 - Click to login link ");
		homePage.clickToLoginLink();
		loginPage = new LogInPageObject(driver);
		
		System.out.println("Login_04 - Step 2 - Input to required field ");
		loginPage.inputToEmailTextBox(existingEmail);
		loginPage.inputToPasswordTextBox("");

		System.out.println("Login_04 - Step 3 - Click to login button");
		loginPage.clickToLoginButton();

		System.out.println("Login_04 - Step 4 - Verify error message displayed");
		Assert.assertEquals(loginPage.getErrorMessageAtHeaderTextBox(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");

	}

	@Test
	public void Login_05_Existing_Email_Incorrect_Password() {

		System.out.println("Login_05 - Step 1 - Click to login link");
		homePage.clickToLoginLink();
		loginPage = new LogInPageObject(driver);
		
		System.out.println("Login_05 - Step 2 - Input to required field");
		loginPage.inputToEmailTextBox(existingEmail);
		loginPage.inputToPasswordTextBox(existingEmail);

		System.out.println("Login_05 - Step 3 - Click to login button");
		loginPage.clickToLoginButton();

		System.out.println("Login_05 - Step 4 - Verify error message displayed");
		Assert.assertEquals(loginPage.getErrorMessageAtHeaderTextBox(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");

	}

	@Test
	public void Login_06_Valid_Email_Password() {
		System.out.println("Login_06 - Step 1 - Click to login link - ");
		homePage.clickToLoginLink();

		System.out.println("Login_05 - Step 2 - Input to required field - ");
		loginPage.inputToEmailTextBox(existingEmail);
		loginPage.inputToPasswordTextBox(password);

		System.out.println("Login_05 - Step 3 - Click to login button - ");
		loginPage.clickToLoginButton();
		homePage = new HomePageObject(driver);
		
		System.out.println("Login_05 - Step 4 - Verify my account displayed - ");
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
	}

	@AfterClass
	public void afterClass() {

		driver.quit();
	}

	public int getRamdomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}

}
