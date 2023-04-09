package com.nopcommerce.admin;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.IEnvironment;
import pageObject.nopCommerce.admin.AdminPageGeneratorManager;
import pageObject.nopCommerce.admin.DashboardPO;
import pageObject.nopCommerce.admin.LoginPO;
import pageObject.nopCommerce.admin.ProductDetailPO;
import pageObject.nopCommerce.admin.ProductSearchPO;


public class  Level_25_Environment_Owner extends BaseTest{
	IEnvironment env;
	String loginPageUrl, userID, password;
	@Parameters({"browser", "server"})
	@BeforeClass
	public void beforeClass(String browserName, String serverName) {
		
		String environment = System.getProperty("EVN");
		System.out.println("Server name run by command line = " + environment);
		ConfigFactory.setProperty("env", environment);
		env = ConfigFactory.create(IEnvironment.class);
		
		driver = getBrowserDriver(browserName, env.url());
		
		loginPage = AdminPageGeneratorManager.getAdminLoginPage(driver);
		
		loginPage.inputToEmailTextbox("admin@yourstore.com");
		loginPage.inputToPasswordTextbox("admin");
		dashboardPage = loginPage.clickToLoginButton();
		
		dashboardPage.openSubMenuPageByName(driver, "Catalog", "Products");
		productSearchPage = AdminPageGeneratorManager.getAdminProductSearchPage(driver);
		
		productSearchPage.enterToProductNameTextbox(productName);
		
		productSearchPage.clickToSearchButton();
		
		productDetailPage = productSearchPage.clickToEditButtonByProductName(productName);
		  
	}

	@Test
	public void Admin_01_Upload_File() {
		productDetailPage.clickToExpandPanelByName("Pictures");
		productDetailPage.deleteAllPictureAfterUpload(driver);
		productDetailPage.uploadFileAtCardName(driver, "pictures", productNBImg);
		
		Assert.assertTrue(productDetailPage.isPictureUpLoadSuccessByFileName(productNBImg));
	
		productDetailPage.inputToAltTextbox(productNBAlt);
		productDetailPage.inputToTitleTextbox(productNBTitle);
		productDetailPage.clickToUpDownInDislayOrderTextbox("Increase");
		
		productDetailPage.clickToAddProductPicture(); 
	
		Assert.assertTrue(productDetailPage.isPictrureImageDisplayed(productName, productNBOrder, productNBAlt, productNBTitle));
	
		productSearchPage = productDetailPage.clickToSaveButton();
		
		Assert.assertTrue(productSearchPage.isSuccessMessageDisplayed("The product has been updated successfully."));
	
		productSearchPage.enterToProductNameTextbox("Adobe Photoshop CS4");
		
		productSearchPage.clickToSearchButton();
		
		Assert.assertTrue(productSearchPage.isPictrureImageUpdated(productName, productName));
	
		productDetailPage = productSearchPage.clickToEditButtonByProductName(productName);
		
		productDetailPage.clickToExpandPanelByName("Pictures");
		
		productDetailPage.clickToDeleteButtonAtPictureName(productNBTitle); // accept alert
		
		Assert.assertTrue(productDetailPage.isMessageDisplayedInEmptyTable(driver, "productpictures"));
		
		productSearchPage = productDetailPage.clickToSaveButton();
		
		productSearchPage.enterToProductNameTextbox("Adobe Photoshop CS4");
		
		productSearchPage.clickToSearchButton();
		
		Assert.assertTrue(productSearchPage.isPictrureImageUpdated("default-image",productName));
		
	}

	@AfterClass
	public void afterClass() {

		driver.quit();
	}
	private WebDriver driver;
	LoginPO loginPage;
	DashboardPO dashboardPage;
	ProductSearchPO productSearchPage;
	ProductDetailPO productDetailPage;
	String productName = "Adobe Photoshop CS4";
	String productNBImg = "ninhbinh.jpg";
	String productNBAlt = "NB Alt";
	String productNBTitle = "NB Title";
	String productNBOrder = "1";
	
}
