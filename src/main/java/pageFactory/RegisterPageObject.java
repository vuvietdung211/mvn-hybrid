package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commons.BasePageFactory;

public class RegisterPageObject extends BasePageFactory {
	private WebDriver driver;

	public RegisterPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(id = "register-button")
	private WebElement registerButton; 
	
	@FindBy(id = "FirstName")
	private WebElement firstNameTextbox;
	
	@FindBy(id = "LastName")
	private WebElement lastNameTextbox; 
	
	@FindBy(id = "Email")
	private WebElement emailTextbox; 
	
	@FindBy(id = "Password")
	private WebElement passwordTextbox; 
	
	@FindBy(id = "ConfirmPassword")
	private WebElement confirmPasswordTextbox; 
	
	@FindBy(id = "FirstName-error")
	private WebElement firstNameErrorMessage; 
	
	@FindBy(id = "LastName-error")
	private WebElement lastNameErrorMessage; 
	
	@FindBy(id = "Email-error")
	private WebElement emailErrorMessage; 
	
	@FindBy(id = "Password-error")
	private WebElement passwordErrorMessage; 
	
	@FindBy(id = "ConfirmPassword-error")
	private WebElement confirmpasswordErrorMessage; 
	
	@FindBy(css = "div.result")
	private WebElement registerSuccessMessage; 
	
	@FindBy(xpath = "//a[@class='ico-logout']")
	private WebElement logoutButton; 
	
	@FindBy(xpath = "//div[contains(@class,'message-error')]//li")
	private WebElement existingEmailErrorMessage; 
	
	public void clickToRegisterButton() {
		waitForElementClickable(driver, registerButton);
		clickToElement(driver, registerButton);
	}
	
	public void clickToLogOutButton() {
		waitForElementClickable(driver, logoutButton);
		clickToElement(driver, logoutButton);
	}
	
	public void inputToFirstNameTextBox(String firstName) {
		waitForElementVisible(driver, firstNameTextbox);
		sendkeyToElement(driver, firstNameTextbox, firstName);
	}

	public void inputToLastNameTextBox(String lastName) {
		waitForElementVisible(driver, lastNameTextbox);
		sendkeyToElement(driver, lastNameTextbox, lastName);
	}

	public void inputToEmailTextBox(String emailAddress) {
		waitForElementVisible(driver, emailTextbox);
		sendkeyToElement(driver, emailTextbox, emailAddress);	
	}
	
	public void inputToPasswordTextBox(String password) {
		waitForElementVisible(driver, passwordTextbox);
		sendkeyToElement(driver, passwordTextbox, password);
	}
	
	public void inputToConfirmPasswordTextBox(String confirmPassword) {
		waitForElementVisible(driver, confirmPasswordTextbox);
		sendkeyToElement(driver, confirmPasswordTextbox, confirmPassword);
	}
	
	public String getErrorMessageAtPasswordTextBox() {
		waitForElementVisible(driver, passwordErrorMessage);
		return getElementText(driver, passwordErrorMessage);
	}

	public String getErrorMessageAtConfirmPasswordTextBox() {
		waitForElementVisible(driver, confirmpasswordErrorMessage);
		return getElementText(driver, confirmpasswordErrorMessage);
	}

	public String getErrorExistingEmailMessage() {
		waitForElementVisible(driver, existingEmailErrorMessage);
		return getElementText(driver, existingEmailErrorMessage);
	}

	public String getErrorMessageAtEmailTextBox() {
		waitForElementVisible(driver, emailErrorMessage);
		return getElementText(driver, emailErrorMessage);
	}

	public String getErrorMessageAtFirstNameTextBox() {
		waitForElementVisible(driver, firstNameErrorMessage);
		return getElementText(driver, firstNameErrorMessage);
	}

	public String getErrorMessageAtLastNameTextBox() {
		waitForElementVisible(driver, lastNameErrorMessage);
		return getElementText(driver, lastNameErrorMessage);
	}
	
	public String getRegisterSuccessMessage() {
		waitForElementVisible(driver, registerSuccessMessage);
		return getElementText(driver, registerSuccessMessage);
	}
}
