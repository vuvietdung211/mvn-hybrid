package pageObject.nopCommerce.portal;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import io.qameta.allure.Step;
import pageUI.nopCommerce.user.RegisterPageUI;

public class UserRegisterPageObject extends BasePage{
	private WebDriver driver;
	
	public UserRegisterPageObject(WebDriver driver) {
		this.driver = driver;
	}

	@Step("Click to Register button")
	public void clickToRegisterButton() {
		waitForElementClickable(driver, RegisterPageUI.REGISTER_BUTTON);
		clickToElement(driver, RegisterPageUI.REGISTER_BUTTON);
	}

	@Step("Click to Logout link")
	public UserHomePageObject clickToLogOutLink() {
		waitForElementClickable(driver, RegisterPageUI.LOG_OUT_LINK);
		if (driver.toString().contains("chrome")) {
			sleepInSecond(3);
		}
		clickToElement(driver, RegisterPageUI.LOG_OUT_LINK);
		return  PageGeneratorManager.getUserHomePage(driver);
	}

	@Step("Input to First Name textbox with value {0}")
	public void inputToFirstNameTextBox(String firstName) {
		waitForElementVisible(driver, RegisterPageUI.FIRST_NAME_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.FIRST_NAME_TEXTBOX, firstName);
	}

	@Step("Input to Last Name textbox with value {0}")
	public void inputToLastNameTextBox(String lastName) {
		waitForElementVisible(driver, RegisterPageUI.LAST_NAME_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.LAST_NAME_TEXTBOX, lastName);
	}

	@Step("Input to Email textbox with value {0}")
	public void inputToEmailTextBox(String emailAddress) {
		waitForElementVisible(driver, RegisterPageUI.EMAIL_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.EMAIL_TEXTBOX, emailAddress);	
	}

	@Step("Input to Password textbox with value {0}")
	public void inputToPasswordTextBox(String password) {
		waitForElementVisible(driver, RegisterPageUI.PASSWORD_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.PASSWORD_TEXTBOX, password);
	}

	@Step("Input to Confirm Password textbox with value {0}")
	public void inputToConfirmPasswordTextBox(String confirmPassword) {
		waitForElementVisible(driver, RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX);
		sendkeyToElement(driver, RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX, confirmPassword);
	}

	@Step("Get error message at password textbox")
	public String getErrorMessageAtPasswordTextBox() {
		waitForElementVisible(driver, RegisterPageUI.PASSWORD_ERROR_MESSAGE);
		return getElementText(driver, RegisterPageUI.PASSWORD_ERROR_MESSAGE);
	}

	@Step("Click to Register link")
	public String getErrorMessageAtConfirmPasswordTextBox() {
		waitForElementVisible(driver, RegisterPageUI.CONFIRM_PASSWORD_ERROR_MESSAGE);
		return getElementText(driver, RegisterPageUI.CONFIRM_PASSWORD_ERROR_MESSAGE);
	}

	@Step("Get error message at existing email message")
	public String getErrorExistingEmailMessage() {
		waitForElementVisible(driver, RegisterPageUI.EXISTING_EMAIL_ERROR_MESSAGE);
		return getElementText(driver, RegisterPageUI.EXISTING_EMAIL_ERROR_MESSAGE);
	}

	@Step("Get error message at email textbox")
	public String getErrorMessageAtEmailTextBox() {
		waitForElementVisible(driver, RegisterPageUI.EMAIL_ERROR_MESSAGE);
		return getElementText(driver, RegisterPageUI.EMAIL_ERROR_MESSAGE);
	}

	@Step("Get error message at error first name textbox")
	public String getErrorMessageAtFirstNameTextBox() {
		waitForElementVisible(driver, RegisterPageUI.FIRST_NAME_ERROR_MESSAGE);
		return getElementText(driver, RegisterPageUI.FIRST_NAME_ERROR_MESSAGE);
	}

	@Step("Get error message at error last name textbox")
	public String getErrorMessageAtLastNameTextBox() {
		waitForElementVisible(driver, RegisterPageUI.LAST_NAME_ERROR_MESSAGE);
		return getElementText(driver, RegisterPageUI.LAST_NAME_ERROR_MESSAGE);
	}

	@Step("Verify success message displayed")
	public String getRegisterSuccessMessage() {
		waitForElementVisible(driver, RegisterPageUI.REGISTER_SUCCESS_MESSAGE);
		return getElementText(driver, RegisterPageUI.REGISTER_SUCCESS_MESSAGE);
	}

	

	
}
