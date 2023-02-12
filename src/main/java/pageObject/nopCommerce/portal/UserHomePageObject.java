package pageObject.nopCommerce.portal;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import io.qameta.allure.Step;
import pageUI.nopCommerce.user.HomePageUI;

public class UserHomePageObject extends BasePage {
	private WebDriver driver;
	
	public UserHomePageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	@Step("Click to Register link")
	public UserRegisterPageObject clickToRegisterLink() {
		waitForElementClickable(driver, HomePageUI.REGISTER_LINK);
		clickToElement(driver, HomePageUI.REGISTER_LINK);
		return PageGeneratorManager.getUserRegisterPage(driver);
	}

	@Step("Open Login page")
	public UserLogInPageObject openLoginPage() {
		waitForElementClickable(driver, HomePageUI.LOGIN_LINK);
		clickToElement(driver, HomePageUI.LOGIN_LINK);
		return PageGeneratorManager.getUserLoginPage(driver);
	}

	@Step("Get message at Home page")
	public String getMessageAtHomePage() {
		waitForElementVisible(driver, HomePageUI.WELCOME_TITLE);
		return getElementText(driver, HomePageUI.WELCOME_TITLE);
	}

	@Step("Verify My Account link is displayed")
	public boolean isMyAccountLinkDisplayed() {
		waitForElementVisible(driver, HomePageUI.MY_ACCOUNT_LINK);
		return isElementDisplayed(driver, HomePageUI.MY_ACCOUNT_LINK);
	}

	@Step("Click to My Account link")
	public UserCustomerInfoPageObject clickToMyAccountLink() {
		waitForElementVisible(driver, HomePageUI.MY_ACCOUNT_LINK);
		clickToElement(driver, HomePageUI.MY_ACCOUNT_LINK);
		return PageGeneratorManager.getUserCustomerInfoPage(driver);
	}




 

}
