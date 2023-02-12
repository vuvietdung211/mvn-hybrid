package pageObject.hrm;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUI.hrm.PersonalDetailPageUI;

public class PersonalDetailPO extends BasePage{
	WebDriver driver;

	public PersonalDetailPO(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToChangePhotoImage() {
		waitForElementClickable(driver, PersonalDetailPageUI.AVATAR_IMAGE);
		clickToElement(driver, PersonalDetailPageUI.AVATAR_IMAGE);
		
	}

	public boolean isUploadAvatarSuccessMessageDisplayed() {
		waitForElementVisible(driver, PersonalDetailPageUI.UPLOAD_AVATAR_SUCCESS_MESSAGE);
		return isElementDisplayed(driver, PersonalDetailPageUI.UPLOAD_AVATAR_SUCCESS_MESSAGE);
	}

	public boolean isNewAvatarImageDisplayed() {
		waitForElementClickable(driver, PersonalDetailPageUI.AVATAR_IMAGE);
		int imageWidth = Integer.parseInt(getElementAttribute(driver, PersonalDetailPageUI.AVATAR_IMAGE, "width"));
		int imageheight = Integer.parseInt(getElementAttribute(driver, PersonalDetailPageUI.AVATAR_IMAGE, "height"));
		return (imageWidth != 200 || imageheight != 200);
	}
	
}
