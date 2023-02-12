package pageObject.nopCommerce.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.nopCommerce.admin.DashboardUI;

public class DashboardPO extends BasePage{
	WebDriver driver;

	public DashboardPO(WebDriver driver) {
		this.driver = driver;
	}
	public boolean isDashboardDisplayed() {
		waitForElementVisible(driver, DashboardUI.DASHBOARD_HEADER);
		return isElementDisplayed(driver, DashboardUI.DASHBOARD_HEADER);
	}
	
}
