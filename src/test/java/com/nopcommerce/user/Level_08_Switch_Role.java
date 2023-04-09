package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalConstants;
import pageObject.nopCommerce.admin.DashboardPO;
import pageObject.nopCommerce.admin.LoginPO;
import pageObject.nopCommerce.portal.PageGeneratorManager;
import pageObject.nopCommerce.portal.UserCustomerInfoPageObject;
import pageObject.nopCommerce.portal.UserHomePageObject;
import pageObject.nopCommerce.portal.UserLogInPageObject;
import pageObject.nopCommerce.portal.UserRegisterPageObject;


public class  Level_08_Switch_Role extends BaseTest{
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		System.out.println("Run on " + browserName);
		driver = getBrowserDriver(browserName);
		userHomePage = PageGeneratorManager.getUserHomePage(driver);
		
		userEmail = "auto" + getRamdomNumber() + "@mail.vn";
		firstName = "automation";
		lastName = "fc";
		userPassword = "123456";
		adminEmailAdress = "admin@yourstore.com";
		adminPassword = "admin";
	}

	@Test
	public void Role_01_Register_User() {
		userRegisterPage =  userHomePage.clickToRegisterLink();
		userRegisterPage.inputToFirstNameTextBox(firstName);
		userRegisterPage.inputToLastNameTextBox(lastName);
		userRegisterPage.inputToEmailTextBox(userEmail);
		userRegisterPage.inputToPasswordTextBox(userPassword);
		userRegisterPage.inputToConfirmPasswordTextBox(userPassword);
		userRegisterPage.clickToRegisterButton();
		Assert.assertEquals(userRegisterPage.getRegisterSuccessMessage(), "Your registration completed");

		userHomePage = userRegisterPage.clickToLogOutLink();
		
	}

	@Test
	public void Role_02_Login_User_To_Admin() {
		userLoginPage = userHomePage.openLoginPage();
		
		// User role
		userHomePage = userLoginPage.loginAsUser(userEmail, userPassword);
		
		userCustomerInfoPage = userHomePage.clickToMyAccountLink();
		userHomePage = userCustomerInfoPage.clickToLogOutLinkByUser(driver);
		
		userHomePage.openPageUrl(driver, GlobalConstants.DEV_ADMIN_URL);
		adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);
		
		// Admin role
		adminDashboard = adminLoginPage.loginAsAdmin(adminEmailAdress, adminPassword);
		Assert.assertTrue(adminDashboard.isDashboardDisplayed());
		
		// DashboardPage -> click log out -> log in Page (admin)
		adminLoginPage = adminDashboard.clickToLogOutLinkByAdmin(driver);
	}

	@Test
	public void Role_03_Admin_To_User() {
		// Login Page (admin) -> HomePage(user).open URL
		adminLoginPage.openPageUrl(driver, GlobalConstants.DEV_ADMIN_URL);
		userHomePage = PageGeneratorManager.getUserHomePage(driver);
		
		// HomePage -> Login Page(user)
		userLoginPage = userHomePage.openLoginPage();
		
		// Login as User role
		userHomePage = userLoginPage.loginAsUser(userEmail, userPassword);
		Assert.assertTrue(userHomePage.isMyAccountLinkDisplayed());
	}

	@AfterClass
	public void afterClass() {

		driver.quit();
	}
	private WebDriver driver;
	private String userEmail, firstName, lastName, userPassword, adminEmailAdress, adminPassword;
	private UserHomePageObject userHomePage;
	private UserRegisterPageObject userRegisterPage;
	private UserLogInPageObject userLoginPage;
	private UserCustomerInfoPageObject userCustomerInfoPage;
	private LoginPO adminLoginPage;
	private DashboardPO adminDashboard;
	


}
