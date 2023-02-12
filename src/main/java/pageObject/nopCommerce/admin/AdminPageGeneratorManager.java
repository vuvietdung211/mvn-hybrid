package pageObject.nopCommerce.admin;

import org.openqa.selenium.WebDriver;

public class AdminPageGeneratorManager {
		public static ProductDetailPO getAdminProductDetailPage(WebDriver driver) {
			return new ProductDetailPO(driver);
		}
		public static ProductSearchPO getAdminProductSearchPage(WebDriver driver) {
			return new ProductSearchPO(driver);
		}
		
		public static LoginPO getAdminLoginPage(WebDriver driver) {
			return new LoginPO(driver);
		}
		public static DashboardPO getAdminDashboardPage(WebDriver driver) {
			return new DashboardPO(driver);
		}
}
