package pageFactory.nopCommerce;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commons.BasePageFactory;

public class LoginPageObject extends BasePageFactory {
	private WebDriver driver;

	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//button[contains(@class,'login-button'")
	private WebElement loginButton;
	
	@FindBy(xpath = "//span[@id='Email-error']")
	private WebElement emailErrorMessge;
	
	@FindBy(xpath = "//input[@id='Email']")
	private WebElement emailTextbox;
	
	@FindBy(xpath = "//input[@id='Password']")
	private WebElement passwordTextbox;
	
	@FindBy(xpath = "//div[contains(@class, 'validation-summary-errors')]")
	private WebElement validationSummaryErrorMessage;
	
	public void clickToLoginButton() {
		waitForElementVisible(driver, loginButton);
		clickToElement(driver, loginButton);

	}

	public void inputToEmailTextbox(String email) {
		waitForElementVisible(driver, emailTextbox);
		sendKeyToElement(driver, emailTextbox, email);
	}

	public String getErrorMessageAtEmailTextbox() {
		waitForElementVisible(driver, emailErrorMessge);
		return getElementText(driver, emailErrorMessge);
	}

	public void inputToPasswordTextbox(String password) {
		waitForElementVisible(driver, passwordTextbox);
		sendKeyToElement(driver, passwordTextbox, password);
		
	}

	public String getValidationSummaryErrorMessage() {
		waitForElementVisible(driver, validationSummaryErrorMessage);
		return getElementText(driver, validationSummaryErrorMessage);
	}
}
