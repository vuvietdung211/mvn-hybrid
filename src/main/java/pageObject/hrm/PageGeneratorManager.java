package pageObject.hrm;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
	WebDriver driver;
	
	public static LoginPageObject getLoginPage(WebDriver driver) {
		return new LoginPageObject(driver);
		
	}
	
	public static EmployeeListPO getEmployeeList(WebDriver driver) {
		return new EmployeeListPO(driver);
		
	}
	
	public static PersonalDetailPO getPersonalDetailPO(WebDriver driver) {
		return new PersonalDetailPO(driver);
		
	}
	
	public static DashBoardPO getDashboardPO(WebDriver driver) {
		return new DashBoardPO(driver);
		
	}
	public static MyInfoPO getMyInfoPO(WebDriver driver) {
		return new MyInfoPO(driver);
		
	}

}
