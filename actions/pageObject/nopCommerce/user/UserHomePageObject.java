package pageObject.nopCommerce.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.nopCommerce.user.HomePageUI;

public class UserHomePageObject extends BasePage {
	private WebDriver driver;

	public UserHomePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public UserRegisterPageObject clickToRegisterLink() {
		waitForElementVisible(driver, HomePageUI.REGISTER_LINK);
		clickToElement(driver, HomePageUI.REGISTER_LINK);
		return PageGeneratorManager.getUserRegisterPage(driver);
	}

	public UserLoginPageObject clickToLoginLink() {
		waitForElementVisible(driver, HomePageUI.LOGIN_LINK);
		clickToElement(driver, HomePageUI.LOGIN_LINK);
		return PageGeneratorManager.getUserLoginPage(driver);
	}

	public boolean isMyAccountLinkDisplayed() {
		waitForElementVisible(driver, HomePageUI.My_ACCOUNT_LINK);
		return isElementDisplayed(driver, HomePageUI.My_ACCOUNT_LINK);

	}
	
	public UserCustomerInforPageObject clickToMyAccountLink() {
		waitForElementVisible(driver, HomePageUI.My_ACCOUNT_LINK);
		clickToElement(driver, HomePageUI.My_ACCOUNT_LINK);
		return PageGeneratorManager.getUserCustomerInforPage(driver);

	}
	
	public UserCustomerInforPageObject openMyAccountPage() {
		waitForElementVisible(driver, HomePageUI.My_ACCOUNT_LINK);
		clickToElement(driver, HomePageUI.My_ACCOUNT_LINK);
		return PageGeneratorManager.getUserCustomerInforPage(driver);
	}
	
}
