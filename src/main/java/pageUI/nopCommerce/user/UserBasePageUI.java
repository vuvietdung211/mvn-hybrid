package pageUI.nopCommerce.user;

public class UserBasePageUI {
	public static final String ADRESS_LINK = "xpath=//a[text()='Addresses']";
	public static final String CUSTOMER_INFO_LINK = "xpath=//a[text()='Customer info']";
	public static final String REWARD_POINT_LINK = "xpath=//a[text()='Reward points']";
	public static final String CHANGE_PASSWORD_LINK = "xpath=//a[text()='Change password']";
	public static final String MY_PRODUCT_REVIEW_LINK = "xpath=//a[text()='My product reviews']";
	
	public static final String DYNAMIC_PAGE_AT_MY_ACCOUNT_AREA = "xpath=//div[contains(@class,'account-navigation')]//a[text()='%s']";
	
	public static final String LOG_OUT_LINK_AT_USER = "xpath=//a[@class='ico-logout']";
	public static final String LOG_OUT_LINK_AT_ADMIN = "xpath=//a[text()='Logout']";
	
	public static final String DYNAMIC_TEXTBOX_BY_ID = "xpath=//input[@id='%s']";
	public static final String DYNAMIC_PAGE_HEADER = "xpath=//div[@class='header']//a[text()='%s']";
	public static final String DYNAMIC_PAGE_FOOTER = "xpath=//div[@class='footer']//a[text()='%s']";
	public static final String DYNAMIC_RADIO_BUTTON_BY_ID = "xpath=//input[@id='%s']";
	public static final String DYNAMIC_DROPDOWN_BY_NAME = "xpath=//select[@name='%s']";
	public static final String DYNAMIC_BUTTON_BY_TEXT = "xpath=//button[text()='%s']";
	
	
}
