package commons;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObject.hrm.DashBoardPO;
import pageObject.hrm.LoginPageObject;
import pageObject.nopCommerce.admin.LoginPO;
import pageObject.nopCommerce.portal.PageGeneratorManager;
import pageObject.nopCommerce.portal.UserAddressPageObject;
import pageObject.nopCommerce.portal.UserChangePasswordPageObject;
import pageObject.nopCommerce.portal.UserCustomerInfoPageObject;
import pageObject.nopCommerce.portal.UserHomePageObject;
import pageObject.nopCommerce.portal.UserMyProductReviewPageObject;
import pageObject.nopCommerce.portal.UserRewardPointPageObject;
import pageUI.hrm.BasePageUI;
import pageUI.nopCommerce.admin.AdminBasePageUI;
import pageUI.nopCommerce.user.UserBasePageUI;

public class BasePage {
	public static BasePage getBasePageObject() {
		return new BasePage();
	}

	public void openPageUrl(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}

	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public String getPageSourceCode(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public Set<Cookie> getAllCookies(WebDriver driver) {
		return driver.manage().getCookies();
	}

	public void setAllCookies(WebDriver driver, Set<Cookie> allCookies) {
		for (Cookie cookie : allCookies) {
			driver.manage().addCookie(cookie);
		}

	}

	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}

	public void fowardToPage(WebDriver driver) {
		driver.navigate().forward();
	}

	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	public Alert waitForAlertPresence(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, 30);
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}

	public void acceptAlert(WebDriver driver) {
		waitForAlertPresence(driver).accept();
	}

	public void cancelAlert(WebDriver driver) {
		waitForAlertPresence(driver).dismiss();
	}

	public String getAlertText(WebDriver driver) {
		return waitForAlertPresence(driver).getText();
	}

	public void sendKeyToAlert(WebDriver driver, String textValue) {
		waitForAlertPresence(driver).sendKeys(textValue);
	}

	public void switchtoWindowByID(WebDriver driver, String windowPageID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String window : allWindows) {
			if (window.equals(windowPageID)) {
				driver.switchTo().window(window);
				break;
			}
		}
	}

	public void switchtoWindowByPageTitleID(WebDriver driver, String expectedpageTitle) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String window : allWindows) {
			driver.switchTo().window(window);
			sleepInSecond(1);
			String actualPageTitle = driver.getTitle().trim();
			if (actualPageTitle.equals(expectedpageTitle)) {
				break;
			}
		}
	}

	public void closeAllWindowByPageWithoutParent(WebDriver driver, String parentpageID) {
		Set<String> allWindows = driver.getWindowHandles();
		for (String window : allWindows) {
			if (!window.equals(parentpageID)) {
				driver.switchTo().window(window);
				driver.close();
			}
		}
		driver.switchTo().window(parentpageID);
		sleepInSecond(2);

	}

	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private WebElement getWebElement(WebDriver driver, String locatorType) {
		return driver.findElement(getByLocator(locatorType));
	}

	private WebElement getWebElement(WebDriver driver, String locatorType, String... dynamicValues) {
		return driver.findElement(getByLocator(getDynamicXpath(locatorType, dynamicValues)));
	}

	private List<WebElement> getListWebElements(WebDriver driver, String locatorType) {
		return driver.findElements(getByLocator(locatorType));
	}

//	private By getByXpath(String locatorType) {
//		return By.xpath(locatorType);
//	}

	private By getByLocator(String locatorType) {
		By by = null;
		if (locatorType.startsWith("id=") || locatorType.startsWith("ID=") || locatorType.startsWith("Id=")) {
			by = By.id(locatorType.substring(3));
		} else if (locatorType.startsWith("class=") || locatorType.startsWith("CLASS=")
				|| locatorType.startsWith("Class=")) {
			by = By.className(locatorType.substring(6));
		} else if (locatorType.startsWith("name=") || locatorType.startsWith("NAME=")
				|| locatorType.startsWith("Name=")) {
			by = By.name(locatorType.substring(5));
		} else if (locatorType.startsWith("xpath=") || locatorType.startsWith("XPATH=")
				|| locatorType.startsWith("Xpath=") || locatorType.startsWith("XPath=")) {
			by = By.xpath(locatorType.substring(6));
		} else if (locatorType.startsWith("css=") || locatorType.startsWith("CSS=") || locatorType.startsWith("Css=")) {
			by = By.cssSelector(locatorType.substring(4));
		} else {
			throw new RuntimeException("Locator type is not supported");
		}
		return by;

	}

	private String getDynamicXpath(String locatorType, String... values) {
		if (locatorType.startsWith("xpath=") || locatorType.startsWith("XPATH=") || locatorType.startsWith("Xpath=")
				|| locatorType.startsWith("XPath=")) {
			locatorType = String.format(locatorType, (Object[]) values);
		}
		return locatorType;

	}

	public void clickToElement(WebDriver driver, String locatorType) {
		getWebElement(driver, locatorType).click();
	}

	public void clickToElement(WebDriver driver, String locatorType, String... dynamicValues) {
		getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).click();
	}

	public void sendkeyToElement(WebDriver driver, String locatorType, String textValue) {
		WebElement element = getWebElement(driver, locatorType);
		element.clear();
		element.sendKeys(textValue);
	}

	public void sendkeyToElement(WebDriver driver, String locatorType, String textValue, String... dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		element.clear();
		element.sendKeys(textValue);
	}

	public void pressKeyToElement(WebDriver driver, String locatorType, Keys key) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver, locatorType), key).perform();
	}

	public void pressKeyToElement(WebDriver driver, String locatorType, Keys key, String... dynamicValues) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)), key).perform();
	}

	public void selectItemInDefaultDropdown(WebDriver driver, String locatorType, String textItem) {
		Select select = new Select(getWebElement(driver, locatorType));
		select.selectByValue(textItem);
	}

	public void selectItemInDefaultDropdown(WebDriver driver, String locatorType, String textItem,
			String... dynamicValues) {
		Select select = new Select(getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)));
		select.selectByValue(textItem);
	}

	public String getFirstSelectedItemInDefaultDropDown(WebDriver driver, String locatorType) {
		Select select = new Select(getWebElement(driver, locatorType));
		return select.getFirstSelectedOption().getText();
	}

	public String getFirstSelectedItemInDefaultDropDown(WebDriver driver, String locatorType, String... dynamicValues) {
		Select select = new Select(getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)));
		return select.getFirstSelectedOption().getText();
	}

	public boolean isDropdownMutiple(WebDriver driver, String locatorType) {
		Select select = new Select(getWebElement(driver, locatorType));
		return select.isMultiple();
	}

	public void selectItemInCustomDropdown(WebDriver driver, String parentXpath, String childItemXpath,
			String expectedItem) {
		getWebElement(driver, parentXpath).click();
		sleepInSecond(1);

		WebDriverWait explicitWait = new WebDriverWait(driver, 15);
		List<WebElement> allItems = explicitWait
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childItemXpath)));

		for (WebElement item : allItems) {
			if (item.getText().trim().equals(expectedItem)) {
				JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
				jsExecutor.executeScript("arguments[0].scrollIntoView(true);", item);
				sleepInSecond(1);

				item.click();
				sleepInSecond(1);
				break;
			}
		}
	}

	public String getElementAttribute(WebDriver driver, String locatorType, String attributeName) {
		return getWebElement(driver, locatorType).getAttribute(attributeName);
	}

	public String getElementAttribute(WebDriver driver, String locatorType, String attributeName,
			String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).getAttribute(attributeName);
	}

	public String getElementText(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).getText().trim();
	}

	public String getElementText(WebDriver driver, String locatorType, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).getText().trim();
	}

	public String getElementCssValue(WebDriver driver, String locatorType, String propertyName) {
		return getWebElement(driver, locatorType).getCssValue(propertyName);
	}

	public String getElementCssValue(WebDriver driver, String locatorType, String propertyName,
			String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).getCssValue(propertyName);
	}

	public String getHexaColorFromRGBA(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}

	public int getElementSize(WebDriver driver, String locatorType) {
		return getListWebElements(driver, locatorType).size();
	}

	public int getElementSize(WebDriver driver, String locatorType, String... dynamicValues) {
		return getListWebElements(driver, getDynamicXpath(locatorType, dynamicValues)).size();
	}

	public void checkToDefaultCheckBoxRadio(WebDriver driver, String locatorType) {
		WebElement element = getWebElement(driver, locatorType);
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void checkToDefaultCheckBoxRadio(WebDriver driver, String locatorType, String... dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void uncheckToDefaultCheckBox(WebDriver driver, String locatorType) {
		WebElement element = getWebElement(driver, locatorType);
		if (element.isSelected()) {
			element.click();
		}
	}

	public void uncheckToDefaultCheckBox(WebDriver driver, String locatorType, String... dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		if (element.isSelected()) {
			element.click();
		}
	}

	public boolean isElementDisplayed(WebDriver driver, String locatorType) {
		try {
			// Displayed: Visible on UI + in DOM
			// Undisplayed: Invisible on UI + in DOM
			return getWebElement(driver, locatorType).isDisplayed();
		} catch (Exception e) {
			// Undisplayed: Invisible on UI + Not in DOM
			return false;
		}
	}

	public boolean isElementDisplayed(WebDriver driver, String locatorType, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).isDisplayed();
	}

	public boolean isElementUndisplayed(WebDriver driver, String locator) {
		System.out.println("Start time = " + new Date().toString());
		overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
		List<WebElement> elements = getListWebElements(driver, locator);
		overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
		if (elements.size() == 0) {
			System.out.println("Element not in DOM");
			System.out.println("End time = " + new Date().toString());
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			System.out.println("Element in DOM but not visible/ displayed");
			System.out.println("End time = " + new Date().toString());
			return true;
		} else {
			System.out.println("Element in DOM and visible");
			return false;
		}
	}

	public void overrideGlobalTimeout(WebDriver driver, long timeout) {
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}

	public boolean isElementEnabled(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isEnabled();
	}

	public boolean isElementEnabled(WebDriver driver, String locatorType, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).isEnabled();
	}

	public boolean isElementSelected(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isSelected();
	}

	public void switchToFrameIframe(WebDriver driver, String locatorType) {
		driver.switchTo().frame(getWebElement(driver, locatorType));
	}

	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	public void hoverMouseToElement(WebDriver driver, String locatorType) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, locatorType)).perform();
	}

	public void hoverMouseToElement(WebDriver driver, String locatorType, String... dynamicValues) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, getDynamicXpath(locatorType, dynamicValues))).perform();
	}

	public void scrollToBottomPage(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void highlightElement(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, locatorType);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				"border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				originalStyle);
	}

	public void highlightElement(WebDriver driver, String locatorType, String... dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				"border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, locatorType));
	}

	public void clickToElementByJS(WebDriver driver, String locatorType, String... dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();",
				getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)));
	}

	public void scrollToElement(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locatorType));
	}

	public void removeAttributeInDOM(WebDriver driver, String locatorType, String attributeRemove) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');",
				getWebElement(driver, locatorType));
	}

	public boolean isJQueryAjaxLoadedSuccess(WebDriver driver) {
		WebDriverWait explicit = new WebDriverWait(driver, 30);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {

				return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
			}
		};
		return explicit.until(jQueryLoad);
	}

	public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, 30);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};

		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	public String getElementValidationMessage(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;",
				getWebElement(driver, locatorType));
	}

	public boolean isImageLoaded(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
				getWebElement(driver, locatorType));
		if (status) {
			return true;
		} else {
			return false;
		}
	}

	public void waitForElementVisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locatorType)));
	}

	public void waitForElementVisible(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions
				.visibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}

	public void waitForAllElementVisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locatorType)));
	}

	public void waitForAllElementVisible(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions
				.visibilityOfAllElementsLocatedBy(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}

	public void waitForElementInvisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.SHORT_TIMEOUT);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
	}

	public void waitForElementInvisible(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions
				.invisibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}

	public void waitForAllElementInvisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getListWebElements(driver, locatorType)));
	}

	public void waitForAllElementInvisible(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions
				.invisibilityOfAllElements(getListWebElements(driver, getDynamicXpath(locatorType, dynamicValues))));
	}

	public void waitForElementClickable(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(locatorType)));
	}

	public void waitForElementClickable(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		explicitWait.until(
				ExpectedConditions.elementToBeClickable(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}

	// User NopCOmmerce

	public UserAddressPageObject openAddressPage(WebDriver driver) {
		waitForElementClickable(driver, UserBasePageUI.ADRESS_LINK);
		clickToElement(driver, UserBasePageUI.ADRESS_LINK);
		return PageGeneratorManager.getUserAddressPage(driver);
	}

	public UserChangePasswordPageObject openChangePasswordPage(WebDriver driver) {
		waitForElementClickable(driver, UserBasePageUI.CHANGE_PASSWORD_LINK);
		clickToElement(driver, UserBasePageUI.CHANGE_PASSWORD_LINK);
		return PageGeneratorManager.getUserChangePasswordPage(driver);
	}

	public UserCustomerInfoPageObject openCustomerInfoPage(WebDriver driver) {
		waitForElementClickable(driver, UserBasePageUI.CUSTOMER_INFO_LINK);
		clickToElement(driver, UserBasePageUI.CUSTOMER_INFO_LINK);
		return PageGeneratorManager.getUserCustomerInfoPage(driver);
	}

	public UserMyProductReviewPageObject openMyProductReviewPage(WebDriver driver) {
		waitForElementClickable(driver, UserBasePageUI.MY_PRODUCT_REVIEW_LINK);
		clickToElement(driver, UserBasePageUI.MY_PRODUCT_REVIEW_LINK);
		return PageGeneratorManager.getUserMyProductReviewPage(driver);
	}

	public UserRewardPointPageObject openRewardPointPage(WebDriver driver) {
		waitForElementClickable(driver, UserBasePageUI.REWARD_POINT_LINK);
		clickToElement(driver, UserBasePageUI.REWARD_POINT_LINK);
		return PageGeneratorManager.getUserRewardPointPage(driver);
	}

	public BasePage openPageAtMyAccountByName(WebDriver driver, String pageName) {
		waitForElementClickable(driver, UserBasePageUI.DYNAMIC_PAGE_AT_MY_ACCOUNT_AREA, pageName);
		clickToElement(driver, UserBasePageUI.DYNAMIC_PAGE_AT_MY_ACCOUNT_AREA, pageName);
		switch (pageName) {
		case "Addresses":
			return PageGeneratorManager.getUserAddressPage(driver);
		case "Customer info":
			return PageGeneratorManager.getUserCustomerInfoPage(driver);
		case "Reward points":
			return PageGeneratorManager.getUserRewardPointPage(driver);
		case "Change password":
			return PageGeneratorManager.getUserChangePasswordPage(driver);
		case "My product reviews":
			return PageGeneratorManager.getUserMyProductReviewPage(driver);
		default:
			throw new RuntimeException("Invalid page name at My Account");
		}

	}

	public void openPageAtMyAccountByPageName(WebDriver driver, String pageName) {
		waitForElementClickable(driver, UserBasePageUI.DYNAMIC_PAGE_AT_MY_ACCOUNT_AREA, pageName);
		clickToElement(driver, UserBasePageUI.DYNAMIC_PAGE_AT_MY_ACCOUNT_AREA, pageName);

	}

	public UserHomePageObject clickToLogOutLinkByUser(WebDriver driver) {
		waitForElementClickable(driver, UserBasePageUI.LOG_OUT_LINK_AT_USER);
		clickToElement(driver, UserBasePageUI.LOG_OUT_LINK_AT_USER);
		return PageGeneratorManager.getUserHomePage(driver);
	}

	public LoginPO clickToLogOutLinkByAdmin(WebDriver driver) {
		waitForElementClickable(driver, UserBasePageUI.LOG_OUT_LINK_AT_ADMIN);
		clickToElement(driver, UserBasePageUI.LOG_OUT_LINK_AT_ADMIN);
		return PageGeneratorManager.getAdminLoginPage(driver);
	}

	// Admin NopCommerce
	public void openSubMenuPageByName(WebDriver driver, String menuPageName, String submenuPageName) {
		waitForElementClickable(driver, AdminBasePageUI.MENU_LINK_BY_NAME, menuPageName);
		clickToElement(driver, AdminBasePageUI.MENU_LINK_BY_NAME, menuPageName);

		waitForElementClickable(driver, AdminBasePageUI.SUB_MENU_LINK_BY_NAME, submenuPageName);
		clickToElement(driver, AdminBasePageUI.SUB_MENU_LINK_BY_NAME, submenuPageName);
	}

	public void uploadFileAtCardName(WebDriver driver, String cardName, String... fileNames) {
		String filePath = GlobalConstants.UPLOAD_FOLDER_PATH;
		String fullFileName = "";
		for (String file : fileNames) {
			fullFileName = fullFileName + filePath + file + "\n";
		}
		fullFileName = fullFileName.trim();
		getWebElement(driver, AdminBasePageUI.UPLOAD_FILE_BY_CARD_NAME, cardName).sendKeys(fullFileName);

	}

	public boolean isMessageDisplayedInEmptyTable(WebDriver driver, String tableName) {
		waitForElementVisible(driver, AdminBasePageUI.NO_DATA_MESSAGE_BY_TABLE_NAME, tableName);
		return isElementDisplayed(driver, AdminBasePageUI.NO_DATA_MESSAGE_BY_TABLE_NAME, tableName);
	}

	public void deleteAllPictureAfterUpload(WebDriver driver) {
		List<WebElement> deleteButtons = getListWebElements(driver, AdminBasePageUI.DELETE_BUTTONS_IN_PICTURE);
		for (WebElement deletebutton : deleteButtons) {
			deletebutton.click();
			sleepInSecond(2);
			acceptAlert(driver);
			sleepInSecond(2);
		}

	}

	// Parttern Object
	public void inputToTextboxByID(WebDriver driver, String valueTextbox, String textboxID) {
		waitForElementVisible(driver, UserBasePageUI.DYNAMIC_TEXTBOX_BY_ID, textboxID);
		sendkeyToElement(driver, UserBasePageUI.DYNAMIC_TEXTBOX_BY_ID, valueTextbox, textboxID);
	}

	public void openHeaderPageByName(WebDriver driver, String headerText) {
		waitForElementClickable(driver, UserBasePageUI.DYNAMIC_PAGE_HEADER, headerText);
		clickToElement(driver, UserBasePageUI.DYNAMIC_PAGE_HEADER, headerText);
	}

	public void clickToGenderRadioButtonByID(WebDriver driver, String valueRadioButtonID) {
		waitForElementClickable(driver, UserBasePageUI.DYNAMIC_RADIO_BUTTON_BY_ID, valueRadioButtonID);
		clickToElement(driver, UserBasePageUI.DYNAMIC_RADIO_BUTTON_BY_ID, valueRadioButtonID);
	}

	public void selectDropDownByName(WebDriver driver, String dropdownName, String itemText) {
		selectItemInDefaultDropdown(driver, UserBasePageUI.DYNAMIC_DROPDOWN_BY_NAME, itemText, dropdownName);
	}

	public void clickToButtonByText(WebDriver driver, String buttonText) {
		waitForElementClickable(driver, UserBasePageUI.DYNAMIC_BUTTON_BY_TEXT, buttonText);
		clickToElement(driver, UserBasePageUI.DYNAMIC_BUTTON_BY_TEXT, buttonText);
	}

	// HRM CHILD SUB MENU
	public void openChildSubMenuByPageName(WebDriver driver, String menuPageName, String subMenuPageName,
			String childSubMenuPageName) {
		waitForElementVisible(driver, BasePageUI.MENU_BY_PAGE_NAME, menuPageName);
		clickToElement(driver, BasePageUI.MENU_BY_PAGE_NAME, menuPageName);

		waitForElementVisible(driver, BasePageUI.MENU_BY_PAGE_NAME, subMenuPageName);
		hoverMouseToElement(driver, BasePageUI.MENU_BY_PAGE_NAME, subMenuPageName);

		waitForElementVisible(driver, BasePageUI.MENU_BY_PAGE_NAME, childSubMenuPageName);
		clickToElement(driver, BasePageUI.MENU_BY_PAGE_NAME, childSubMenuPageName);
	}

	// OPEN SUB MENU
	public void openSubMenuPage(WebDriver driver, String menuPageName, String subMenuPageName) {
		waitForElementVisible(driver, BasePageUI.MENU_BY_PAGE_NAME, menuPageName);
		clickToElement(driver, BasePageUI.MENU_BY_PAGE_NAME, menuPageName);

		waitForElementVisible(driver, BasePageUI.MENU_BY_PAGE_NAME, subMenuPageName);
		clickToElement(driver, BasePageUI.MENU_BY_PAGE_NAME, subMenuPageName);
	}

	// OPEN MENU
	public void openMenuPage(WebDriver driver, String menuPageName) {
		waitForElementVisible(driver, BasePageUI.MENU_BY_PAGE_NAME, menuPageName);
		clickToElement(driver, BasePageUI.MENU_BY_PAGE_NAME, menuPageName);
	}

	// CLICK TO BUTTON BY ID
	public void clickToButtonByID(WebDriver driver, String buttonID) {
		waitForElementClickable(driver, BasePageUI.BUTTON_BY_ID, buttonID);
		clickToElement(driver, BasePageUI.BUTTON_BY_ID, buttonID);
	}

	// ENTER TO TEXTBOX BY ID
	public void enterToTextboxByID(WebDriver driver, String value, String textboxID) {
		waitForAllElementVisible(driver, BasePageUI.TEXTBOX_BY_ID, textboxID);
		sendkeyToElement(driver, BasePageUI.TEXTBOX_BY_ID, value, textboxID);
	}

	// GET TEXTBOX VALUE BY ID
	public void getValueTextboxByID(WebDriver driver, String attributeName, String textboxID) {
		waitForAllElementVisible(driver, BasePageUI.TEXTBOX_BY_ID, textboxID);
		getElementAttribute(driver, BasePageUI.TEXTBOX_BY_ID, attributeName, textboxID);
	}

	// SELECT DROPDOWN BY ID
	public void selectDropdownByID(WebDriver driver, String textItem, String dropdownID) {
		waitForElementClickable(driver, BasePageUI.DROPDOWN_BY_ID, dropdownID);
		selectItemInDefaultDropdown(driver, BasePageUI.DROPDOWN_BY_ID, textItem, dropdownID);
	}

	// CHECK TO CHECKBOX BY ID
	public void checkToCheckBoxByLabel(WebDriver driver, String checkboxID) {
		waitForElementClickable(driver, BasePageUI.CHECKBOX_BY_LABEL, checkboxID);
		checkToDefaultCheckBoxRadio(driver, BasePageUI.CHECKBOX_BY_LABEL, checkboxID);
	}

	// CHECK TO RADIO BUTTON BY ID
	public void checkToRadioButtonByLabel(WebDriver driver, String radioButtonID) {
		waitForElementClickable(driver, BasePageUI.RADIO_BUTTON_BY_LABEL, radioButtonID);
		checkToDefaultCheckBoxRadio(driver, BasePageUI.RADIO_BUTTON_BY_LABEL, radioButtonID);
	}

	// CHECK INFO TABLE
	public String getValueInTableIDAtRowColumnIndex(WebDriver driver, String tableID, String headerName,
			String rowIndex) {
		int columnIndex = getElementSize(driver, BasePageUI.TABLE_HEADER_BY_ID_AND_NAME, tableID, headerName) + 1;
		waitForElementVisible(driver, BasePageUI.TABLE_ROW_BY_COLUMN_INDEX_AND_ROW_INDEX, tableID, rowIndex,
				String.valueOf(columnIndex));
		return getElementText(driver, BasePageUI.TABLE_ROW_BY_COLUMN_INDEX_AND_ROW_INDEX, tableID, rowIndex,
				String.valueOf(columnIndex));
	}

	// LOG IN TO SYSTEM
	public DashBoardPO loginToSystem(WebDriver driver, String userName, String password) {
		waitForElementVisible(driver, BasePageUI.USER_LOGIN_TEXTBOX);
		sendkeyToElement(driver, BasePageUI.USER_LOGIN_TEXTBOX, userName);
		sendkeyToElement(driver, BasePageUI.PASSWORD_LOGIN_TEXTBOX, password);
		clickToElement(driver, BasePageUI.LOGIN_BUTTON);
		return pageObject.hrm.PageGeneratorManager.getDashboardPO(driver);
		
	}

	// LOG OUT TO SYSTEM
	public LoginPageObject logoutToSystem(WebDriver driver) {
		waitForElementClickable(driver, BasePageUI.WELCOME_USER_LINK);
		clickToElement(driver, BasePageUI.WELCOME_USER_LINK);

		waitForElementClickable(driver, BasePageUI.LOGOUT_LINK);
		clickToElement(driver, BasePageUI.LOGOUT_LINK);
		return pageObject.hrm.PageGeneratorManager.getLoginPage(driver);
	}

	// CHECK ANY FIELD ENABLE
	public void isFieldEnabledByName(WebDriver driver, String fieldID) {
		waitForElementVisible(driver, BasePageUI.ANY_FIELD_BY_ID, fieldID);
		isElementEnabled(driver, BasePageUI.ANY_FIELD_BY_ID, fieldID);
	}
	
	// UPLOAD FILE HRM
	public void uploadImage(WebDriver driver, String filePath) {
		getWebElement(driver, BasePageUI.UPLOAD_FILE).sendKeys(filePath);
		
	}
}
