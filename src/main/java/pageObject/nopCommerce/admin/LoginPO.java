package pageObject.nopCommerce.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageObject.nopCommerce.portal.PageGeneratorManager;
import pageUI.nopCommerce.admin.LoginPageUI;

public class LoginPO extends BasePage{
	WebDriver driver;

	public LoginPO(WebDriver driver) {
		this.driver = driver;
	}
	
	public void inputToEmailTextbox(String emailAdress) {
		waitForElementVisible(driver, LoginPageUI.EMAIL_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.EMAIL_TEXTBOX, emailAdress);
	}
	public void inputToPasswordTextbox(String password) {
		waitForElementVisible(driver, LoginPageUI.PASSWORD_TEXTBOX);
		sendkeyToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, password);
	}
	public DashboardPO clickToLoginButton() {
		waitForElementClickable(driver, LoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, LoginPageUI.LOGIN_BUTTON);
		return PageGeneratorManager.getAdminDashboardPage(driver);
	}

	public DashboardPO loginAsAdmin(String emailAdress, String password) {
		inputToEmailTextbox(emailAdress);
		inputToPasswordTextbox(password);
		return clickToLoginButton();
	}
		
}
