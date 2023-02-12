package pageObject.liveGuru;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
	public static HomePageObject getHomePageObject(WebDriver driver) {
		return new HomePageObject(driver);
	}
	public static LogInPageObject getLogInPageObject(WebDriver driver) {
		return new LogInPageObject(driver);
	}
	public static MyDashboardPageObject getMyDashBoardPageObject(WebDriver driver) {
		return new MyDashboardPageObject(driver);
	}
	public static RegisterPageObject getRegisterPageObject(WebDriver driver) {
		return new RegisterPageObject(driver);
	}
}
