package pageObject.liveGuru;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.liveGuru.HomePageUI;

public class HomePageObject extends BasePage{
	WebDriver driver;
	public HomePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public LogInPageObject clickToMyAccountLink() {
		waitForElementClickable(driver, HomePageUI.ACCOUNT_LINK);
		clickToElement(driver, HomePageUI.ACCOUNT_LINK);
		waitForElementClickable(driver, HomePageUI.MY_ACCOUNT_LINK);
		clickToElement(driver, HomePageUI.MY_ACCOUNT_LINK);
		return PageGeneratorManager.getLogInPageObject(driver);
	}

	public LogInPageObject clickToLoginLink() {
		waitForElementClickable(driver, HomePageUI.ACCOUNT_LINK);
		clickToElement(driver, HomePageUI.ACCOUNT_LINK);
		waitForElementClickable(driver, HomePageUI.LOGIN_LINK);
		clickToElement(driver, HomePageUI.LOGIN_LINK);
		return PageGeneratorManager.getLogInPageObject(driver);
	}

}
