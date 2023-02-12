package pageObject.nopCommerce.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.nopCommerce.admin.ProductSearchPageUI;

public class ProductSearchPO extends BasePage{
	WebDriver driver;

	public ProductSearchPO(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isSuccessMessageDisplayed(String messageName) {
		waitForAllElementVisible(driver, ProductSearchPageUI.SUCCESS_MESSAGE_NAME, messageName);
		return isElementDisplayed(driver, ProductSearchPageUI.SUCCESS_MESSAGE_NAME, messageName);
	}

	public void enterToProductNameTextbox(String productName) {
		waitForElementVisible(driver, ProductSearchPageUI.PRODUCT_NAME_TEXTBOX);
		sendkeyToElement(driver, ProductSearchPageUI.PRODUCT_NAME_TEXTBOX, productName);
	}

	public void clickToSearchButton() {
		waitForElementClickable(driver, ProductSearchPageUI.SEARCH_BUTTON);
		clickToElement(driver, ProductSearchPageUI.SEARCH_BUTTON);
	}

	public boolean isPictrureImageUpdated(String productImageName, String productName) {
		productImageName = productImageName.replace(" ", "-").toLowerCase();
		waitForAllElementVisible(driver, ProductSearchPageUI.PRODUCT_IMAGE_BY_PRODUCT_NAME, productName, productImageName);
		return isElementDisplayed(driver, ProductSearchPageUI.PRODUCT_IMAGE_BY_PRODUCT_NAME, productName, productImageName);
	}

	public ProductDetailPO clickToEditButtonByProductName(String productName) {
		waitForElementClickable(driver,  ProductSearchPageUI.EDIT_BUTTON_BY_PRODUCT_NAME, productName);
		clickToElement(driver,  ProductSearchPageUI.EDIT_BUTTON_BY_PRODUCT_NAME, productName);
		return AdminPageGeneratorManager.getAdminProductDetailPage(driver);
	}
}
