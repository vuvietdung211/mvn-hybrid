package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commons.BasePageFactory;

public class LogInPageObject extends BasePageFactory{
	private WebDriver driver;

	public LogInPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(xpath = "//button[contains(@class,'login-button')]")
	private WebElement loginButton; 
	
	@FindBy(id = "Email")
	private WebElement emailTextbox; 
	
	@FindBy(id = "Password")
	private WebElement passWordTextBox; 
	
	@FindBy(id = "Email-error")
	private WebElement emailErrorMessage; 
	
	@FindBy(xpath = "//div[contains(@class,'message-error')]")
	private WebElement headerErrorMessage; 
	
	public void clickToLoginButton() {
		waitForElementClickable(driver, loginButton);
		clickToElement(driver, loginButton);
	}

	public String getErrorMessageAtEmailTextBox() {
		waitForElementVisible(driver, emailErrorMessage);
		return getElementText(driver, emailErrorMessage);
	}

	public void inputToEmailTextBox(String invalidEmail) {
		waitForElementVisible(driver, emailTextbox);
		sendkeyToElement(driver, emailTextbox, invalidEmail);
	}

	public void inputToPasswordTextBox(String password) {
		waitForElementVisible(driver, passWordTextBox);
		sendkeyToElement(driver, passWordTextBox, password);
	}

	public String getErrorMessageAtHeaderTextBox() {
		waitForElementVisible(driver, headerErrorMessage);
		return getElementText(driver, headerErrorMessage);
	}

}
