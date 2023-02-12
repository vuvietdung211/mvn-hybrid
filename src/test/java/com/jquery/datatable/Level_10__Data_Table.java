package com.jquery.datatable;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import org.testng.Assert;

import commons.BaseTest;
import pageObject.jQuery.HomePageObject;
import pageObject.jQuery.PageGeneratorManager;


public class  Level_10__Data_Table extends BaseTest{
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getHomePageObject(driver);
		
	}

	//@Test
	public void Table_01_Paging() {
		homePage.openPageUrl(driver, "https://www.jqueryscript.net/demo/CRUD-Data-Grid-Plugin-jQuery-Quickgrid/");
		
		homePage.openPageByNumber("15");
		Assert.assertTrue(homePage.isPageActiveByNumber("15"));
		
		homePage.openPageByNumber("8");
		Assert.assertTrue(homePage.isPageActiveByNumber("8"));
		
		homePage.openPageByNumber("23");
		Assert.assertTrue(homePage.isPageActiveByNumber("23"));
		
	}

	//@Test
	public void Table_02_Input_Textbox() {
		// input to textbox
		homePage.inputToHeaderTextboxByName("Females", "9496");
		homePage.sleepInSecond(3);
		homePage.refreshCurrentPage(driver);
		
		homePage.inputToHeaderTextboxByName("Country", "Mauritania");
		homePage.sleepInSecond(3);
		homePage.refreshCurrentPage(driver);
		
		homePage.inputToHeaderTextboxByName("Total", "19280");
		homePage.sleepInSecond(3);
		homePage.refreshCurrentPage(driver);
		
	}
	//@Test
	public void Table_03_Actions() {
		
		// Click to icon
		homePage.clickToIconByCountryName("Afghanistan", "remove");
		homePage.sleepInSecond(3);
		homePage.refreshCurrentPage(driver);
		
		homePage.clickToIconByCountryName("Albania", "edit");
		homePage.sleepInSecond(3);
		homePage.refreshCurrentPage(driver);
		
		homePage.clickToIconByCountryName("Albania", "remove");
		homePage.sleepInSecond(3);
		homePage.refreshCurrentPage(driver);
		
	}
	
	//@Test
	public void Table_04_Verify() {
		homePage.inputToHeaderTextboxByName("Country", "Argentina");
		Assert.assertTrue(homePage.isRowValueDisplayed("338282","Argentina","349238","687522"));
		homePage.sleepInSecond(3);
		homePage.refreshCurrentPage(driver);
		
		homePage.inputToHeaderTextboxByName("Country", "Antigua and Barbuda");
		Assert.assertTrue(homePage.isRowValueDisplayed("777","Antigua and Barbuda","803","1580"));
		homePage.sleepInSecond(3);
		homePage.refreshCurrentPage(driver);
		
	}
	@Test
	public void Table_05_Input_To_Row_Textbox() {
		homePage.openPageUrl(driver, "https://www.jqueryscript.net/demo/jQuery-Dynamic-Data-Grid-Plugin-appendGrid/");
		
		homePage.inputToTextboxByNumber("Album", "1", "Black");
		homePage.sleepInSecond(2);
		
	}
	
	@Test
	public void Table_06_Click_To_Icon_At_Row() {
		homePage.clickToIconByRowNumber("1","Insert Row Above");
		homePage.clickToIconByRowNumber("1","Move Down");
		homePage.clickToIconByRowNumber("1","Move Up");
		homePage.clickToIconByRowNumber("1","Move Up");
		
	}

	@AfterClass
	public void afterClass() {

		driver.quit();
	}
	private WebDriver driver;
	private HomePageObject homePage;

}
