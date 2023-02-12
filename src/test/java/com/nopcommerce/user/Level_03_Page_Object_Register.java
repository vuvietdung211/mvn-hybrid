package com.nopcommerce.user;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObject.nopCommerce.portal.UserHomePageObject;
import pageObject.nopCommerce.portal.UserRegisterPageObject;

public class Level_03_Page_Object_Register {
	private WebDriver driver;
	private String projectPath = System.getProperty("user.dir");
	private String emailAddress, firstName, lastName, password;
	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		 homePage = new UserHomePageObject(driver);
		 
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.get("https://demo.nopcommerce.com/");
		
		emailAddress = "auto" + getRamdomNumber() + "@mail.vn";
		firstName = "automation";
		lastName = "fc";
		password = "123456";
		
	}

	@Test
	public void Register_01_Empty_Data() {
		System.out.println("Register_01 - Step 1 - Click to register link ");
		homePage.clickToRegisterLink();
		registerPage = new UserRegisterPageObject(driver);
		
		System.out.println("Register_01 - Step 2- Click to register button ");
		registerPage.clickToRegisterButton();

		System.out.println("Register_01 - Step 3 - Verify error message displayed");
		Assert.assertEquals(registerPage.getErrorMessageAtFirstNameTextBox(), "First name is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtLastNameTextBox(), "Last name is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtEmailTextBox(), "Email is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextBox(), "Password is required.");
		Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextBox(), "Password is required.");
	}

	@Test
	public void Register_02_Invalid_Email() {
		System.out.println("Register_02 - Step 1 - Click to register link");
		homePage.clickToRegisterLink();
		registerPage = new UserRegisterPageObject(driver);
		
		System.out.println("Register_02 - Step 2 - Input to required field ");
		registerPage.inputToFirstNameTextBox(firstName);
		registerPage.inputToLastNameTextBox(lastName);
		registerPage.inputToEmailTextBox("123@!@#");
		registerPage.inputToPasswordTextBox(password);
		registerPage.inputToConfirmPasswordTextBox(password);

		System.out.println("Register_02 - Step 3 - Click to register button ");
		registerPage.clickToRegisterButton();

		System.out.println("Register_02 - Step 4- Verify error message displayed");
		Assert.assertEquals(registerPage.getErrorMessageAtEmailTextBox(), "Wrong email");
	}

	@Test
	public void Register_03_Success() {
		System.out.println("Register_03 - Step 1- Click to register link ");
		homePage.clickToRegisterLink();
		registerPage = new UserRegisterPageObject(driver);
		
		System.out.println("Register_03 - Step 2 - Input to required field");
		registerPage.inputToFirstNameTextBox(firstName);
		registerPage.inputToLastNameTextBox(lastName);
		registerPage.inputToEmailTextBox(emailAddress);
		registerPage.inputToPasswordTextBox(password);
		registerPage.inputToConfirmPasswordTextBox(password);

		System.out.println("Register_03 - Step 3- Click to register button");
		registerPage.clickToRegisterButton();

		System.out.println("Register_03 - Step 4- Verify register success message displayed");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

		System.out.println("Register_03 - Step 5- Click to log out button");
		registerPage.clickToLogOutLink();
	}

	@Test
	public void Register_04_Existing_Email() {
		System.out.println("Register_04 - Step 1- Click to register link ");
		homePage.clickToRegisterLink();
		registerPage = new UserRegisterPageObject(driver);
		
		System.out.println("Register_04 - Step 2- Input to required field");
		registerPage.inputToFirstNameTextBox(firstName);
		registerPage.inputToLastNameTextBox(lastName);
		registerPage.inputToEmailTextBox(emailAddress);
		registerPage.inputToPasswordTextBox(password);
		registerPage.inputToConfirmPasswordTextBox(password);

		System.out.println("Register_04 - Step 3- Click to register button");
		registerPage.clickToRegisterButton();

		System.out.println("Register_04 - Step 4- Verify error exist email message displayed");
		Assert.assertEquals(registerPage.getErrorExistingEmailMessage(), "The specified email already exists");

	}

	@Test
	public void Register_05_Password_Less_Than_6_Char() {

		System.out.println("Register_05 - Step 1 - Click to register link ");
		homePage.clickToRegisterLink();
		registerPage = new UserRegisterPageObject(driver);
		
		System.out.println("Register_05 - Step 2 - Input to required field ");
		registerPage.inputToFirstNameTextBox(firstName);
		registerPage.inputToLastNameTextBox(lastName);
		registerPage.inputToEmailTextBox(emailAddress);
		registerPage.inputToPasswordTextBox("123");
		registerPage.inputToConfirmPasswordTextBox("123");

		System.out.println("Register_05 - Step 3 - Click to register button");
		registerPage.clickToRegisterButton();

		System.out.println("Register_05 - Step 4 - Verify error exist email message displayed");
		Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextBox(), "Password must meet the following rules:\nmust have at least 6 characters");

	}

	@Test
	public void Register_06_Invalid_Confirm_Password() {
		System.out.println("Register_06 - Step 1 - Click to register link ");
		homePage.clickToRegisterLink();
		registerPage = new UserRegisterPageObject(driver);
		
		System.out.println("Register_06 - Step 2 - Input to required field");
		registerPage.inputToFirstNameTextBox(firstName);
		registerPage.inputToLastNameTextBox(lastName);
		registerPage.inputToEmailTextBox(emailAddress);
		registerPage.inputToPasswordTextBox(password);
		registerPage.inputToConfirmPasswordTextBox(emailAddress);

		System.out.println("Register_06 - Step 3 - Click to register button");
		registerPage.clickToRegisterButton();
		;

		Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextBox(), "The password and confirmation password do not match.");

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
