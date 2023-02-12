package pageObject.facebook;

import org.openqa.selenium.WebDriver;

public class PageGeneratorManager {
	
	public static RegisterPageObject getRegisterPage(WebDriver driver) {
		return new RegisterPageObject(driver);
	}

}
