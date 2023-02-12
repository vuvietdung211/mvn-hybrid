package pageObject.nopCommerce.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.nopCommerce.admin.ProductDetailPageUI;

public class ProductDetailPO extends BasePage {
	WebDriver driver;

	public ProductDetailPO(WebDriver driver) {
		this.driver = driver;
	}

	public static void main(String[] args) {
	}

	public void clickToExpandPanelByName(String panelName) {
		// get tag i attribute
		// if not contains (fa-plus)
		// if contains click button
		waitForElementVisible(driver, ProductDetailPageUI.TOOGLE_ICON_BY_CARD_NAME, panelName);
		String toogleIconStatus = getElementAttribute(driver, ProductDetailPageUI.TOOGLE_ICON_BY_CARD_NAME, "class", panelName);
		if (toogleIconStatus.contains("fa-plus")) {
			waitForElementClickable(driver, ProductDetailPageUI.TOOGLE_ICON_BY_CARD_NAME, panelName);
			clickToElement(driver, ProductDetailPageUI.TOOGLE_ICON_BY_CARD_NAME, panelName);
		}
	}


	public void inputToAltTextbox(String imageAlt) {
		waitForElementVisible(driver, ProductDetailPageUI.ALT_TEXTBOX_ADD_NEW);
		sendkeyToElement(driver, ProductDetailPageUI.ALT_TEXTBOX_ADD_NEW, imageAlt);
	}

	public boolean isPictureUpLoadSuccessByFileName(String fileName) {
		fileName = fileName.split("\\.")[0];
		waitForElementVisible(driver, ProductDetailPageUI.PICTURE_IMAGE_ADD_NEW_BY_FILE_NAME, fileName);
		return isElementDisplayed(driver, ProductDetailPageUI.PICTURE_IMAGE_ADD_NEW_BY_FILE_NAME, fileName);
	}

	public void inputToTitleTextbox(String imageTitle) {
		waitForElementVisible(driver, ProductDetailPageUI.TITLE_TEXTBOX_ADD_NEW);
		sendkeyToElement(driver, ProductDetailPageUI.TITLE_TEXTBOX_ADD_NEW, imageTitle);
		
	}

	public void clickToUpDownInDislayOrderTextbox(String button) {
		waitForElementClickable(driver, ProductDetailPageUI.DISPLAY_ORDER_TEXTBOX_UP_DOWN, button);
		clickToElement(driver, ProductDetailPageUI.DISPLAY_ORDER_TEXTBOX_UP_DOWN, button);
		
	}

	public void clickToAddProductPicture() {
		waitForElementClickable(driver, ProductDetailPageUI.ADD_PRODUCT_PICTURE_BUTTON);
		clickToElement(driver, ProductDetailPageUI.ADD_PRODUCT_PICTURE_BUTTON);
	}

	public boolean isPictrureImageDisplayed(String imageName, String displayOrder, String iamgeALt, String imageTitle) {
		imageName = imageName.replace(" ", "-").toLowerCase();
		waitForAllElementVisible(driver, ProductDetailPageUI.PICTURE_TABLE_BY_NAME_ORDER_ALT_TITLE, imageName, displayOrder, iamgeALt, imageTitle);
		return isElementDisplayed(driver, ProductDetailPageUI.PICTURE_TABLE_BY_NAME_ORDER_ALT_TITLE, imageName, displayOrder, iamgeALt, imageTitle);
	}

	public ProductSearchPO clickToSaveButton() {
		waitForElementClickable(driver, ProductDetailPageUI.SAVE_BUTTON);
		clickToElement(driver, ProductDetailPageUI.SAVE_BUTTON);
		return AdminPageGeneratorManager.getAdminProductSearchPage(driver);
	}

	public void clickToDeleteButtonAtPictureName(String productTitle) {
		waitForElementClickable(driver, ProductDetailPageUI.DELETE_BUTTON_BY_IMAGE_TITLE, productTitle);
		clickToElement(driver, ProductDetailPageUI.DELETE_BUTTON_BY_IMAGE_TITLE, productTitle);
		sleepInSecond(2);
		acceptAlert(driver);
		sleepInSecond(2);
	}

	
}
