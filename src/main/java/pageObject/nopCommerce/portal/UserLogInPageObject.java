package pageObject.nopCommerce.portal;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import io.qameta.allure.Step;
import pageUI.nopCommerce.user.LogInPageUI;

public class UserLogInPageObject extends BasePage {
	private WebDriver driver;
		
	public UserLogInPageObject(WebDriver driver) {
		this.driver = driver;
	}

	@Step("Click to Login button")
	public UserHomePageObject clickToLoginButton() {
		waitForElementClickable(driver, LogInPageUI.LOGIN_BUTTON);
		clickToElement(driver, LogInPageUI.LOGIN_BUTTON);
		 return PageGeneratorManager.getUserHomePage(driver);
	}

	@Step("Get error message at email textbox")
	public String getErrorMessageAtEmailTextBox() {
		waitForElementVisible(driver, LogInPageUI.EMAIL_ERROR_MESSAGE);
		return getElementText(driver, LogInPageUI.EMAIL_ERROR_MESSAGE);
	}

	@Step("Input to Email textbox with value {0}")
	public void inputToEmailTextBox(String invalidEmail) {
		waitForElementVisible(driver, LogInPageUI.EMAIL_TEXTBOX);
		sendkeyToElement(driver, LogInPageUI.EMAIL_TEXTBOX, invalidEmail);
	}

	@Step("Input to password textbox with value {0}")
	public void inputToPasswordTextBox(String password) {
		waitForElementVisible(driver, LogInPageUI.PASSWORD_TEXTBOX);
		sendkeyToElement(driver, LogInPageUI.PASSWORD_TEXTBOX, password);
	}

	@Step("Get error message at header textbox")
	public String getErrorMessageAtHeaderTextBox() {
		waitForAllElementVisible(driver, LogInPageUI.HEADER_ERROR_MESSAGE);
		return getElementText(driver, LogInPageUI.HEADER_ERROR_MESSAGE);
	}

	@Step("Log in to system with value {0} and {1}")
	public UserHomePageObject loginAsUser(String emailadress, String password) {
		inputToEmailTextBox(emailadress);
		inputToPasswordTextBox(password);
		return clickToLoginButton();
	}

	public UserHomePageObject openHomePage() {
		waitForElementClickable(driver, LogInPageUI.HOMEPAGE_IMG);
		clickToElement(driver, LogInPageUI.HOMEPAGE_IMG);
		return PageGeneratorManager.getUserHomePage(driver);
	}

}
