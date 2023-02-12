package com.liveguru.user;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObject.liveGuru.HomePageObject;
import pageObject.liveGuru.LogInPageObject;
import pageObject.liveGuru.MyDashboardPageObject;
import pageObject.liveGuru.PageGeneratorManager;
import pageObject.liveGuru.RegisterPageObject;

public class Level_06_Page_Generator_Manager_III extends BaseTest {

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		System.out.println("Run on " + browserName);
		driver = getBrowserDriver(browserName);
		fisrtName = "afc";
		lastName = "vn";
		emailAddress = "afc" + getRamdomNumber() + "@mail.vn";
		password = "123456";
		homePage = PageGeneratorManager.getHomePageObject(driver);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://live.techpanda.org/");
	}

	@Test
	public void User_01_Register_To_System() {
		System.out.println("User_01- Step 1- Click to my account link");
		loginPage = homePage.clickToMyAccountLink();

		System.out.println("User_02- Step 2- Click to create an account link");
		registerPage = loginPage.clickToCreateAccountLink();

		System.out.println("User_02- Step 3 - Input to required field");
		registerPage.inputToFirstNameTextBox(fisrtName);
		registerPage.inputToLastNameTextBox(lastName);
		registerPage.inputToEmailTextBox(emailAddress);
		registerPage.inputToPasswordTextBox(password);
		registerPage.inputToConfirmPasswordTextBox(password);

		System.out.println("User_02- Step 4- Click to register button");
		myDashBoardPage=  registerPage.clickToRegisterButton();

		System.out.println("User_02- Step 5- Verify my dashboard displayed ");
		Assert.assertTrue(myDashBoardPage.isMyDashBoardDisplayed());
		homePage = myDashBoardPage.clickToLogOutButton();
	}

	@Test
	public void User_02_Login_To_System() {
		System.out.println("User_02- Step 1- Click to log in link");
		loginPage= homePage.clickToLoginLink();

		System.out.println("User_02 - Step 2 - Input to required field");
		loginPage.inputToEmailTextBox(emailAddress);
		loginPage.inputToPasswordTextBox(password);
		myDashBoardPage= loginPage.clickToLoginButton();

		System.out.println("User_02 - Step 3- Verify my dashboard displayed ");
		myDashBoardPage.isMyDashBoardDisplayed();
	}

	@AfterClass
	public void afterClass() {

		driver.quit();
	}

	private WebDriver driver;
	String fisrtName, lastName, emailAddress, password;
	private HomePageObject homePage;
	private RegisterPageObject registerPage;
	private LogInPageObject loginPage;
	private MyDashboardPageObject myDashBoardPage;
}
