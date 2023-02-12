package pageObject.liveGuru;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.liveGuru.LogInPageUI;

public class LogInPageObject extends BasePage {
	WebDriver driver;
	
	public LogInPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public RegisterPageObject clickToCreateAccountLink() {
		waitForElementClickable(driver, LogInPageUI.CREATE_AN_ACCOUNT_BUTTON);
		clickToElement(driver, LogInPageUI.CREATE_AN_ACCOUNT_BUTTON);
		return PageGeneratorManager.getRegisterPageObject(driver);
	}

	public void inputToEmailTextBox(String emailAddress) {
		waitForElementVisible(driver, LogInPageUI.EMAIL_TEXTBOX);
		sendkeyToElement(driver, LogInPageUI.EMAIL_TEXTBOX, emailAddress);
	}

	public void inputToPasswordTextBox(String password) {
		waitForElementVisible(driver, LogInPageUI.PASSWORD_TEXTBOX);
		sendkeyToElement(driver, LogInPageUI.PASSWORD_TEXTBOX, password);
	}

	public MyDashboardPageObject clickToLoginButton() {
		waitForElementClickable(driver, LogInPageUI.LOGIN_BUTTON);
		clickToElement(driver, LogInPageUI.LOGIN_BUTTON);
		return PageGeneratorManager.getMyDashBoardPageObject(driver);
	}

}
