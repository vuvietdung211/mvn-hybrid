package com.saucelabs.sort;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageObject.sauceLabs.InventoryPageObject;
import pageObject.sauceLabs.LoginPageObject;
import pageObject.sauceLabs.PageGenerator;


public class  Level_17_SauceLabs_Sort extends BaseTest{
	@Parameters({"browser" , "url"})
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		adminUserName = "performance_glitch_user";
		adminPassword = "secret_sauce";
		driver = getBrowserDriver(browserName, appUrl);
		loginPage = PageGenerator.getLoginPage(driver);
		loginPage.enterToTextboxByID(driver, adminUserName, "user-name");
		loginPage.enterToTextboxByID(driver, adminPassword, "password");
		inventoryPage = PageGenerator.getIventoryPage(driver);
	}
	
	@Test
	public void Sort_01_Ascending() {
		inventoryPage.isDataStringSortedAscending(driver, adminPassword);
	}
	

	@Test
	public void Sort_01_Descending() {
		inventoryPage.isDataStringSortedDescending();
			
		
	}
	
	@Test
	public void Employee_03_Update_Info() {
	}
	
	
	
	@AfterClass
	public void afterClass() {
		
		
	}
	WebDriver driver;
	LoginPageObject loginPage;
	InventoryPageObject inventoryPage;
	String adminUserName, adminPassword ;
}
