package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObject.nopCommerce.user.UserAddressPageObject;
import pageObject.nopCommerce.user.UserCustomerInforPageObject;
import pageObject.nopCommerce.user.UserHomePageObject;
import pageObject.nopCommerce.user.UserLoginPageObject;
import pageObject.nopCommerce.user.UserMyProductReviewPageObject;
import pageObject.nopCommerce.user.UserRegisterPageObject;
import pageObject.nopCommerce.user.UserRewardPointPageObject;

public class User_09_Dynamic_Locator extends BaseTest {
	WebDriver driver;
	private UserHomePageObject homePage;
	private UserLoginPageObject loginPage;
	private UserRegisterPageObject registerPage;
	private UserCustomerInforPageObject customerInforPage;
	private UserAddressPageObject addresssPage;
	private UserRewardPointPageObject rewardPointPage;
	private UserMyProductReviewPageObject myProductReviewPage;

	private String firstName, lastName, existingEmail, invalidEmail, notFoundEmail;
	private String password, incorrectPassword;

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
		invalidEmail = "124@";
		existingEmail = "afc" + generateFakeNumber() + "@gmail.com";
		notFoundEmail = "afc" + generateFakeNumber() + "@mail.com";
		password = "123456";
		incorrectPassword = "1236787";

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
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

		System.out.println(" User_02 - Step 01: Click to Login link");
		loginPage = homePage.clickToLoginLink();

		System.out.println(" User_02 - Step 02: Input a existing email to email textbox");
		loginPage.inputToEmailTextbox(existingEmail);

		System.out.println(" User_02 - Step 03: Input correct password");
		loginPage.inputToPasswordTextbox(password);

		System.out.println(" User_02 - Step 04: Click to Login button");

		loginPage.clickToLoginButton();

		System.out.println(" User_02 - Step 04: Verify error message displayed");
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());

		customerInforPage = homePage.clickToMyAccountLink();
		Assert.assertTrue(customerInforPage.isCustomerInforPageDisplayed());

	}

	@Test
	public void User_02_Dynamic_Page() {

		addresssPage = customerInforPage.openAddressPage(driver);

		// address => my product review
		myProductReviewPage = addresssPage.openMyProductReviewPage(driver);

		// my product review => reward point
		rewardPointPage = myProductReviewPage.openRewardPointPage(driver);

		// Reward point => address
		addresssPage = rewardPointPage.openAddressPage(driver);

		// address => reward point
		rewardPointPage = addresssPage.openRewardPointPage(driver);

		// reward point => my product review
		myProductReviewPage = rewardPointPage.openMyProductReviewPage(driver);

		// My product review => Address
		addresssPage = myProductReviewPage.openAddressPage(driver);

		// address => Customer infor

	}

	@Test
	public void User_03_Dynamic_Page_01() {

		// address => my product review
		myProductReviewPage = (UserMyProductReviewPageObject) addresssPage.openPagesAtMyAccountByPageName(driver,
				"My product reviews");

		// my product review => reward point
		rewardPointPage = (UserRewardPointPageObject) myProductReviewPage.openPagesAtMyAccountByPageName(driver,
				"Reward points");

		// Reward point => address
		addresssPage = (UserAddressPageObject) rewardPointPage.openPagesAtMyAccountByPageName(driver, "Addresses");

		// address => reward point
		rewardPointPage = (UserRewardPointPageObject) addresssPage.openPagesAtMyAccountByPageName(driver,
				"Reward points");

		// reward point => my product review
		myProductReviewPage = (UserMyProductReviewPageObject) rewardPointPage.openPagesAtMyAccountByPageName(driver,
				"My product reviews");

		// My product review => Address
		addresssPage = (UserAddressPageObject) myProductReviewPage.openPagesAtMyAccountByPageName(driver, "Addresses");

		// Address => Customer infor
		customerInforPage = (UserCustomerInforPageObject) addresssPage.openPagesAtMyAccountByPageName(driver,
				"Customer info");

		// cus

	}

	@Test
	public void User_03_Dynamic_Page_02() {

		customerInforPage.openPagesAtMyAccountByPageNameWay2(driver, "My product reviews");
		myProductReviewPage = PageGeneratorManager.getUserMyProductReviewPage(driver);

		// my product review => reward point
		myProductReviewPage.openPagesAtMyAccountByPageNameWay2(driver, "Reward points");
		rewardPointPage = PageGeneratorManager.getUserRewardPointPage(driver);

		// Reward point => address
		rewardPointPage.openPagesAtMyAccountByPageNameWay2(driver,"Addresses");
		addresssPage = PageGeneratorManager.getUserAddressPage(driver);
		

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
