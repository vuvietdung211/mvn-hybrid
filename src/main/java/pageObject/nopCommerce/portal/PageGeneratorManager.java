package pageObject.nopCommerce.portal;

import org.openqa.selenium.WebDriver;

import pageObject.nopCommerce.admin.DashboardPO;
import pageObject.nopCommerce.admin.LoginPO;

public class PageGeneratorManager {
	public static UserHomePageObject getUserHomePage(WebDriver driver) {
		return new UserHomePageObject(driver);
	}
	public static UserLogInPageObject getUserLoginPage(WebDriver driver) {
		return new UserLogInPageObject(driver);
	}
	public static UserRegisterPageObject getUserRegisterPage(WebDriver driver) {
		return new UserRegisterPageObject(driver);
	}
	public static UserAddressPageObject getUserAddressPage(WebDriver driver) {
		return new UserAddressPageObject(driver);
	}
	public static UserChangePasswordPageObject getUserChangePasswordPage(WebDriver driver) {
		return new UserChangePasswordPageObject(driver);
	}
	public static UserCustomerInfoPageObject getUserCustomerInfoPage(WebDriver driver) {
		return new UserCustomerInfoPageObject(driver);
	}
	public static UserMyProductReviewPageObject getUserMyProductReviewPage(WebDriver driver) {
		return new UserMyProductReviewPageObject(driver);
	}
	public static UserRewardPointPageObject getUserRewardPointPage(WebDriver driver) {
		return new UserRewardPointPageObject(driver);
	}
	public static LoginPO getAdminLoginPage(WebDriver driver) {
		return new LoginPO(driver);
	}
	public static DashboardPO getAdminDashboardPage(WebDriver driver) {
		return new DashboardPO(driver);
	}
	
	
}
