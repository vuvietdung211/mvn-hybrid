package com.facebook.register;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObject.facebook.PageGeneratorManager;
import pageObject.facebook.RegisterPageObject;



public class  Level_13_Assert_Verify extends BaseTest{
	
	@Parameters({"browser", "url"})
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		registerPage = PageGeneratorManager.getRegisterPage(driver);
	}

	@Test
	public void Register_01_Element_Displayed() {
		// Fail lần 1
		verifyFalse(registerPage.isEmailTextboxDisplayed()); 
		
		registerPage.enterToEmailTextbox("dao@gmail.com");
		verifyFalse(registerPage.isConfirmEmailTextboxDisplayed());
		registerPage.enterToEmailTextbox("");
		registerPage.sleepInSecond(3);
		verifyFalse(registerPage.isConfirmEmailTextboxDisplayed());
		
		verifyFalse(registerPage.isLoginButtonDisplayed());
		
		// Fail lần 2
		verifyFalse(registerPage.isLoginButtonDisplayed());
		
		verifyTrue(registerPage.isLoginButtonUnDisplayed());
	}
	

	@AfterClass
	public void afterClass() {

		driver.quit();
	}
	private WebDriver driver;
	private RegisterPageObject registerPage;

}
