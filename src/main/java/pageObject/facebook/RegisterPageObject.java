package pageObject.facebook;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.facebook.RegisterPageUI;

public class RegisterPageObject extends BasePage{
	WebDriver driver;

	public RegisterPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isEmailTextboxDisplayed() {
		waitForElementVisible(driver, RegisterPageUI.EMAIL_TEXTBOX);
		return isElementDisplayed(driver, RegisterPageUI.EMAIL_TEXTBOX);
	}

	public void enterToEmailTextbox(String valueTextbox) {
		waitForElementVisible(driver, RegisterPageUI.EMAIL_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.EMAIL_TEXTBOX, valueTextbox);
	}

	public boolean isConfirmEmailTextboxDisplayed() {
		return isElementDisplayed(driver, RegisterPageUI.CONFIRM_EMAIL_TEXTBOX);
	}

	public boolean isLoginButtonDisplayed() {
		return isElementDisplayed(driver, RegisterPageUI.LOGIN_BUTTON);
	}

	public boolean isLoginButtonUnDisplayed() {
		waitForElementInvisible(driver, RegisterPageUI.LOGIN_BUTTON);
		return isElementUndisplayed(driver, RegisterPageUI.LOGIN_BUTTON);
	}
}
