package com.nopcommerce.user;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import pageFactory.nopCommerce.HomePageObject;
import pageFactory.nopCommerce.LoginPageObject;
import pageFactory.nopCommerce.RegisterPageObject;

public class User_05_Page_Factory extends BaseTest {
	WebDriver driver;
	private HomePageObject homePage;
	private LoginPageObject loginPageObject;
	private RegisterPageObject registerPage;
	
	private String firstName, lastName, existingEmail, invalidEmail, notFoundEmail;
	private String password, incorrectPassword;

	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		
		driver = getBrowserDriver(browserName);
		homePage = new HomePageObject(driver);
		
//		loginPageObject = new LoginPageObject(driver);
//		registerPage = new RegisterPageObject(driver);

		firstName = "Automation";
		lastName = "FC";
		invalidEmail = "124@";
		existingEmail = "afc" + generateFakeNumber()+ "@gmail.com";
		notFoundEmail = "afc" + generateFakeNumber()+ "@mail.com";
		password = "123456";
		incorrectPassword="1236787";
		
		
		System.out.println("Homepage - Step 01: Click to Register link");
		homePage.clickToRegisterLink();
		
		registerPage = new RegisterPageObject(driver);

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
		
		System.out.println("Register Page - Step 05: Click to logout link");
		registerPage.clickToLoginLink();
		
		homePage = new HomePageObject(driver);
		

	}

	@Test
	public void Login_01_Empty_Data() {

		System.out.println("Login_01 - Step 01: Click to Login link");
		homePage.clickToLoginLink();
		
		loginPageObject = new LoginPageObject(driver);
		
		System.out.println("Login_01 - Step 02: Click to Login button");
		loginPageObject.clickToLoginButton();
		
		System.out.println("Login_01 - Step 03: Verify error message displayed");
		Assert.assertEquals(loginPageObject.getErrorMessageAtEmailTextbox(),"Please enter your email");
	}

	@Test
	public void Login_02_Invalid_Email() {
		System.out.println("Login_02 - Step 01: Click to Login link");
		homePage.clickToLoginLink();
		
		loginPageObject = new LoginPageObject(driver);
		
		System.out.println("Login_02 - Step 02: Input invalid email to email field");
		loginPageObject.inputToEmailTextbox(invalidEmail);

		System.out.println("Login_02 - Step 03: Click to Login button");
		loginPageObject.clickToLoginButton();

		System.out.println("Login_02 - Step 04: Verify error message displayed");
		Assert.assertEquals(loginPageObject.getErrorMessageAtEmailTextbox(), "Wrong email");
	}

	@Test
	public void Login_03_Email_Not_Found() {
		System.out.println(" Login_03 - Step 01: Click to Login link");
		homePage.clickToLoginLink();
		
		loginPageObject = new LoginPageObject(driver);

		System.out.println(" Login_03 - Step 02: Input a not existing email to email textbox");
		loginPageObject.inputToEmailTextbox(notFoundEmail);
		loginPageObject.inputToPasswordTextbox(password);

		System.out.println(" Login_03 - Step 03: Click to Login button");
		loginPageObject.clickToLoginButton();
		
		System.out.println(" Login_03 - Step 04: Verify error message displayed");
		Assert.assertEquals(loginPageObject.getValidationSummaryErrorMessage(), "Login was unsuccessful. Please correct the errors and try again.\n"
				+ "No customer account found");

	}
	
	@Test
	public void Login_04_Existing_Email_Empty_Password() {
		
		System.out.println(" Login_04 - Step 01: Click to Login link");
		homePage.clickToLoginLink();
		
		loginPageObject = new LoginPageObject(driver);
		
		System.out.println(" Login_04 - Step 02: Input a existing email to email textbox");
		loginPageObject.inputToEmailTextbox(existingEmail);
		
		System.out.println(" Login_04 - Step 03: Leave password textbox is blank");
		loginPageObject.inputToPasswordTextbox("");
		
		System.out.println(" Login_04 - Step 04: Click to Login button");
		loginPageObject.clickToLoginButton();
		
		System.out.println(" Login_04 - Step 05: Verify error message displayed");
		Assert.assertEquals(loginPageObject.getValidationSummaryErrorMessage(), "Login was unsuccessful. Please correct the errors and try again.\n"
				+ "The credentials provided are incorrect");
		
	}

	
	@Test
	public void Login_05_Existing_Email_And_Incorrect_Password() {

		System.out.println(" Login_05 - Step 01: Click to Login link");
		homePage.clickToLoginLink();
		
		loginPageObject = new LoginPageObject(driver);
		
		System.out.println(" Login_05 - Step 02: Input a existing email to email textbox");
		loginPageObject.inputToEmailTextbox(existingEmail);
		
		System.out.println(" Login_05 - Step 02: Input an incorrect password");
		loginPageObject.inputToPasswordTextbox("");
		
		System.out.println(" Login_04 - Step 03: Click to Login button");
		loginPageObject.inputToPasswordTextbox(incorrectPassword);
		
		System.out.println(" Login_04 - Step 04: Click to Login button");

		loginPageObject.clickToLoginButton();
		
		System.out.println(" Login_04 - Step 04: Verify error message displayed");
		Assert.assertEquals(loginPageObject.getValidationSummaryErrorMessage(), "Login was unsuccessful. Please correct the errors and try again.\n"
				+ "The credentials provided are incorrect");
	}
	
	@Test
	public void Login_06_Existing_Email_And_Correct_Password() {
		
		System.out.println(" Login_06 - Step 01: Click to Login link");
		homePage.clickToLoginLink();
		
		loginPageObject = new LoginPageObject(driver);
		
		System.out.println(" Login_06 - Step 02: Input a existing email to email textbox");
		loginPageObject.inputToEmailTextbox(existingEmail);
		
		System.out.println(" Login_06 - Step 02: Input an incorrect password");
		loginPageObject.inputToPasswordTextbox("");
		
		System.out.println(" Login_06 - Step 03: Click to Login button");
		loginPageObject.inputToPasswordTextbox(password);
		
		System.out.println(" Login_06 - Step 04: Click to Login button");

		loginPageObject.clickToLoginButton();
		
		homePage = new HomePageObject(driver);
		
		System.out.println(" Login_06 - Step 04: Verify error message displayed");
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
		
	}
	
	

	public int generateFakeNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
