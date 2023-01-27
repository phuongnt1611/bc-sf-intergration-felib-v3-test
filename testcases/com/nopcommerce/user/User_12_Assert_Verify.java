package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObject.nopCommerce.user.UserCustomerInforPageObject;
import pageObject.nopCommerce.user.UserHomePageObject;
import pageObject.nopCommerce.user.UserLoginPageObject;
import pageObject.nopCommerce.user.UserRegisterPageObject;

public class User_12_Assert_Verify extends BaseTest {
	WebDriver driver;
	private UserHomePageObject homePage;
	private UserLoginPageObject loginPage;
	private UserRegisterPageObject registerPage;
	private UserCustomerInforPageObject customerInforPage;
	

	private String firstName, lastName, existingEmail;
	private String password ;

	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {

		driver = getBrowserDriver(browserName);
		homePage = PageGeneratorManager.getUserHomePage(driver);
//		loginPage = new loginPage(driver);
//		registerPage = new RegisterPageObject(driver);

		firstName = "Automation";
		lastName = "FC";
		existingEmail = "afc" + generateFakeNumber() + "@gmail.com";
		password = "123456";

	}

	@Test
	public void User_01_Register_And_Login() {

		System.out.println("Homepage - Step 01: Click to Register link");
		registerPage = homePage.clickToRegisterLink();

		System.out.println("Register Page - Step 02: Input to required fields");
		registerPage.inputToFirstnameTextbox(firstName);
		registerPage.inputToLastnameTextbox(lastName);
		registerPage.inputToEmailTextbox(existingEmail);
		registerPage.inputToPasswordTextbox(password);
		registerPage.inputToConfirmPasswordTextbox(password);

		System.out.println("Register Page - Step 03: Click to Register button");
		registerPage.clickToRegisterButton();

		System.out.println("Register Page - Step 04: Verify success message displayed");
		verifyEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

		System.out.println(" User_02 - Step 01: Click to Login link");
		loginPage = homePage.clickToLoginLink();

		System.out.println(" User_02 - Step 02: Input a existing email to email textbox");
		loginPage.inputToEmailTextbox(existingEmail);

		System.out.println(" User_02 - Step 03: Input correct password");
		loginPage.inputToPasswordTextbox(password);

		System.out.println(" User_02 - Step 04: Click to Login button");

		loginPage.clickToLoginButton();

		System.out.println(" User_02 - Step 04: Verify error message displayed");
		verifyTrue(homePage.isMyAccountLinkDisplayed());

		customerInforPage = homePage.clickToMyAccountLink();
		verifyTrue(customerInforPage.isCustomerInforPageDisplayed());

	}

	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
