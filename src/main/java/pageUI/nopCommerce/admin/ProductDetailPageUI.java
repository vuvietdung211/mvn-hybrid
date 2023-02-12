package pageUI.nopCommerce.admin;

public class ProductDetailPageUI {
	public static final String TOOGLE_ICON_BY_CARD_NAME = "xpath=//div[@class='card-title' and contains(string(),'%s')]/following-sibling::div//i";
	public static final String PICTURE_IMAGE_ADD_NEW_BY_FILE_NAME = "xpath=//div[@class='upload-picture-block']//img[contains(@src,'%s')]";
	public static final String ALT_TEXTBOX_ADD_NEW = "xpath=//input[@id='AddPictureModel_OverrideAltAttribute']";
	public static final String TITLE_TEXTBOX_ADD_NEW = "xpath=//input[@id='AddPictureModel_OverrideTitleAttribute']";
	public static final String DISPLAY_ORDER_TEXTBOX_UP_DOWN = "xpath=//input[@id='AddPictureModel_DisplayOrder']/following-sibling::span/span[@aria-label='%s value']";
	public static final String ADD_PRODUCT_PICTURE_BUTTON = "xpath=//button[@id='addProductPicture']";
	public static final String PICTURE_TABLE_BY_NAME_ORDER_ALT_TITLE = "xpath=//a[contains(@href,'%s')]/parent::td/following-sibling::td[contains(@data-columnname,'DisplayOrder') and text()='%s']/following-sibling::td[contains(@data-columnname,'OverrideAltAttribute') and text()='%s']/following-sibling::td[contains(@data-columnname,'OverrideTitleAttribute') and text()='%s']";
	public static final String SAVE_BUTTON = "xpath=//button[@name='save']";
	public static final String DELETE_BUTTON_BY_IMAGE_TITLE = "xpath=//td[text()='%s']/following-sibling::td/a[contains(string(),'Delete')]";
	
}
