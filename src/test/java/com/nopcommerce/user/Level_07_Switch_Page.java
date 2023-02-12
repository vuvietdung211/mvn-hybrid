package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObject.nopCommerce.portal.PageGeneratorManager;
import pageObject.nopCommerce.portal.UserAddressPageObject;
import pageObject.nopCommerce.portal.UserChangePasswordPageObject;
import pageObject.nopCommerce.portal.UserCustomerInfoPageObject;
import pageObject.nopCommerce.portal.UserHomePageObject;
import pageObject.nopCommerce.portal.UserLogInPageObject;
import pageObject.nopCommerce.portal.UserMyProductReviewPageObject;
import pageObject.nopCommerce.portal.UserRegisterPageObject;
import pageObject.nopCommerce.portal.UserRewardPointPageObject;


public class  Level_07_Switch_Page extends BaseTest{
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		System.out.println("Run on " + browserName);
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getUserHomePage(driver);
		
		emailAddress = "auto" + getRamdomNumber() + "@mail.vn";
		firstName = "automation";
		lastName = "fc";
		password = "123456";
		
	}

	@Test
	public void User_01_Register_Login() {
		registerPage =  homePage.clickToRegisterLink();
		registerPage.inputToFirstNameTextBox(firstName);
		registerPage.inputToLastNameTextBox(lastName);
		registerPage.inputToEmailTextBox(emailAddress);
		registerPage.inputToPasswordTextBox(password);
		registerPage.inputToConfirmPasswordTextBox(password);
		registerPage.clickToRegisterButton();
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
		
		homePage = registerPage.clickToLogOutLink();
		
		loginPage = homePage.openLoginPage();
		
		loginPage.inputToEmailTextBox(emailAddress);
		loginPage.inputToPasswordTextBox(password);

		homePage = loginPage.clickToLoginButton();
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
		
		myAccountPage = homePage.clickToMyAccountLink();
		myAccountPage.clickToNewsletterCheckbox();
	}

	@Test
	public void User_02_Dynamic_Page() {
		addressPage = myAccountPage.openAddressPage(driver);
		changePassword = addressPage.openChangePasswordPage(driver);
		customerInfo = changePassword.openCustomerInfoPage(driver);
		rewardPoint = customerInfo.openRewardPointPage(driver);
		customerInfo = rewardPoint.openCustomerInfoPage(driver);
		myProductReview = customerInfo.openMyProductReviewPage(driver);
		addressPage = myProductReview.openAddressPage(driver);
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
	private UserCustomerInfoPageObject myAccountPage;
	private UserAddressPageObject addressPage;
	private UserChangePasswordPageObject changePassword;
	private UserCustomerInfoPageObject customerInfo;
	private UserMyProductReviewPageObject myProductReview;
	private UserRewardPointPageObject rewardPoint;


}
