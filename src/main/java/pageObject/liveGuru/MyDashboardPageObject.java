package pageObject.liveGuru;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.liveGuru.MyDashBoardUI;

public class MyDashboardPageObject extends BasePage {
	WebDriver driver;
	
	public MyDashboardPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public HomePageObject clickToLogOutButton() {
		waitForElementClickable(driver, MyDashBoardUI.ACCOUNT_LINK);
		clickToElement(driver, MyDashBoardUI.ACCOUNT_LINK);
		waitForElementClickable(driver, MyDashBoardUI.LOGOUT_LINK);
		clickToElement(driver, MyDashBoardUI.LOGOUT_LINK);
		return PageGeneratorManager.getHomePageObject(driver);
	}

	public boolean isMyDashBoardDisplayed() {
		waitForElementVisible(driver, MyDashBoardUI.ACCOUNT_DASHBOARD_LINK);
		return isElementDisplayed(driver, MyDashBoardUI.ACCOUNT_DASHBOARD_LINK);
		
	}
 
}
