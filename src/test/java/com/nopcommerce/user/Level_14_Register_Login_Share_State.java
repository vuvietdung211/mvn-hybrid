package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.nopcommerce.common.Common_01_Login;

import commons.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import pageObject.nopCommerce.portal.PageGeneratorManager;
import pageObject.nopCommerce.portal.UserHomePageObject;
import pageObject.nopCommerce.portal.UserLogInPageObject;
import pageObject.nopCommerce.portal.UserRegisterPageObject;

public class  Level_14_Register_Login_Share_State extends BaseTest{
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
	
	@Test
	public void User_01_Login_To_System() {
		log.info("User_02_Login - Step 1: Click to Login link");
		loginPage = homePage.openLoginPage();
		
		log.info("User_02_Login - Step 2:Set login page cookie");
		loginPage.setAllCookies(driver, Common_01_Login.loginPageCookie);
		loginPage.sleepInSecond(5);
		loginPage.refreshCurrentPage(driver);
		homePage = PageGeneratorManager.getUserHomePage(driver);
		
		log.info("User_02_Login - Step 2: Open home page");
		homePage = loginPage.openHomePage();
		
		log.info("User_02_Login - Step 4: Verify My Account link is displayed");
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
