package pageObjects.facebook;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.facebook.LoginPageUI;

public class LoginPageObject extends BasePage {
	WebDriver driver;
	
	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
		
	}

	public void clickToCreateNewAccountButton() {
		waitForElementClickable(driver, LoginPageUI.CREATE_NEW_ACCOUNT_BUTTON);
		clickToElement(driver, LoginPageUI.CREATE_NEW_ACCOUNT_BUTTON);	
	}

	public boolean isEmailAddressTextBoxDisplayed() {
		waitForElementVisible(driver, LoginPageUI.MOBILE_NUMBER_OR_EMAIL_ADDRESS);
		return isElementDisplayed(driver, LoginPageUI.MOBILE_NUMBER_OR_EMAIL_ADDRESS);
	}

	public void enterToEmailAddressTextbox(String emailAddress) {
		waitForElementVisible(driver, LoginPageUI.MOBILE_NUMBER_OR_EMAIL_ADDRESS);
		sendKeyToElement(driver,LoginPageUI.MOBILE_NUMBER_OR_EMAIL_ADDRESS, emailAddress);
		
	}

	public boolean isConfirmEmailAddressTextboxDisplayed() {
		return isElementDisplayed(driver, LoginPageUI.CONFIRM_MOBILE_NUMBER_OR_EMAIL_ADDRESS);
			}

	public void clickCloseIconAtRegisterForm() {
		waitForElementClickable(driver, LoginPageUI.CLOSE_ICON);
		clickToElement(driver, LoginPageUI.CLOSE_ICON);
		
	}

	public boolean isConfirmEmailAddressTextboxUndisplayed() {
		
		return isElementUndisplayed(driver, LoginPageUI.CONFIRM_MOBILE_NUMBER_OR_EMAIL_ADDRESS);
	}

}
