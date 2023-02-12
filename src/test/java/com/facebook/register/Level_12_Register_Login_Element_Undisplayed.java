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



public class  Level_12_Register_Login_Element_Undisplayed extends BaseTest{
	
	@Parameters({"browser", "url"})
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		registerPage = PageGeneratorManager.getRegisterPage(driver);
	}

	@Test
	public void Register_01_Element_Displayed() {
		// Element exsit in Dom / Visible in UI
		Assert.assertTrue(registerPage.isEmailTextboxDisplayed()); 
		
		
	}

	@Test
	public void Register_02_Element_Undisplayed_In_Dom() {
		// Element exsit in Dom / Invisible in UI
		// confirm email textbox displayed
		registerPage.enterToEmailTextbox("dao@gmail.com");
		Assert.assertTrue(registerPage.isConfirmEmailTextboxDisplayed());
		
		//clear email > confirm email undisplayed
		registerPage.enterToEmailTextbox("");
		registerPage.sleepInSecond(3);
		Assert.assertFalse(registerPage.isConfirmEmailTextboxDisplayed());
		
	}
	
	@Test
	public void Register_03_Element_Displayed_Not_In_Dom() {
		// Undisplaysed : Element NOT exsit in Dom / Invisible in UI
		// isDisplayed: false (try catch)
		// Wait max timeout implicitWait
		Assert.assertFalse(registerPage.isLoginButtonDisplayed());
		
		// Khẳng định:
		Assert.assertFalse(registerPage.isLoginButtonDisplayed());
		
		
	}
	
	@Test
	public void Register_04_Element_Displayed_Not_In_Dom() {
		// Undisplaysed : Element NOT exsit in Dom / Invisible in UI
		// findElements
		// Override implicit timeout
		
		//Phủ định:
		Assert.assertTrue(registerPage.isLoginButtonUnDisplayed());
	}

	@AfterClass
	public void afterClass() {

		driver.quit();
	}
	private WebDriver driver;
	private RegisterPageObject registerPage;

}
