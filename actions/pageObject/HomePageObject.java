package pageObject;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.HomePageUI;

public class HomePageObject extends BasePage {
	private WebDriver driver;

	public HomePageObject(WebDriver driver) {
		this.driver = driver;
	}

	public RegisterPageObject clickToRegisterLink() {
		waitForElementVisible(driver, HomePageUI.REGISTER_LINK);
		clickToElement(driver, HomePageUI.REGISTER_LINK);
		return PageGeneratorManager.getRegisterPage(driver);
	}

	public LoginPageObject clickToLoginLink() {
		waitForElementVisible(driver, HomePageUI.LOGIN_LINK);
		clickToElement(driver, HomePageUI.LOGIN_LINK);
		return PageGeneratorManager.getLoginPage(driver);
	}

	public boolean isMyAccountLinkDisplayed() {
		waitForElementVisible(driver, HomePageUI.My_ACCOUNT_LINK);
		return isElementDisplayed(driver, HomePageUI.My_ACCOUNT_LINK);

	}
	
	public MyAccountPageObject clickToMyAccountLink() {
		waitForElementVisible(driver, HomePageUI.My_ACCOUNT_LINK);
		clickToElement(driver, HomePageUI.My_ACCOUNT_LINK);
		return PageGeneratorManager.getMyAccountPageObject(driver);

	}

}
