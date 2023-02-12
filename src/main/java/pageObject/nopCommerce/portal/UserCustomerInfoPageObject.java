package pageObject.nopCommerce.portal;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.nopCommerce.user.CustomerInfoPageUI;

public class UserCustomerInfoPageObject extends BasePage{
	WebDriver driver;

	public UserCustomerInfoPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToNewsletterCheckbox() {
		waitForElementClickable(driver,CustomerInfoPageUI.NEWSLETTER_CHECKBOX);
		clickToElement(driver, CustomerInfoPageUI.NEWSLETTER_CHECKBOX);
	}
}
