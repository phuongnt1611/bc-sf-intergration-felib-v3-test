package com.nopcommerce.user;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import pageObject.HomePageObject;
import pageObject.LoginPageObject;

public class User_04_Login {
	WebDriver driver;
	private HomePageObject homePage;
	private LoginPageObject loginPageObject;
	private String invalidEmail;
	private String password;

	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
		}
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get("https://demo.nopcommerce.com/");
		
		homePage = new HomePageObject(driver);
		loginPageObject = new LoginPageObject(driver);
		invalidEmail = "124@";
		password = "123456";

	}

	@Test
	public void Login_01_Empty_Data() {

		System.out.println("Login_01 - Step 01: Click to Login link");
		homePage.clickToLoginLink();
		
		System.out.println("Login_01 - Step 02: Click to Login button");
		loginPageObject.clickToLoginButton();
		
		System.out.println("Login_01 - Step 03: Verify error message displayed");
		Assert.assertEquals(loginPageObject.getErrorMessageAtEmailTextbox(),"Please enter your email");
	}

	@Test
	public void Login_02_Invalid_Email() {
		System.out.println("Login_02 - Step 01: Click to Login link");
		homePage.clickToLoginLink();
		
		System.out.println("Login_02 - Step 02: Input invalid email to email field");
		loginPageObject.inputToEmailTextbox(invalidEmail);

		System.out.println("Login_02 - Step 03: Click to Login button");
		loginPageObject.clickToLoginButton();

		System.out.println("Login_02 - Step 04: Verify error message displayed");
		Assert.assertEquals(loginPageObject.getErrorMessageAtEmailTextbox(), "Wrong email");
	}

	@Test
	public void Login_03_Not_Exist_Email() {
		System.out.println(" Login_03 - Step 01: Click to Login link");
		homePage.clickToLoginLink();

		System.out.println(" Login_03 - Step 02: Input a not exist email to email textbox");
		loginPageObject.inputToEmailTextbox("123@gmail.com");
		loginPageObject.inputToPasswordTextbox(password);

		System.out.println(" Login_03 - Step 03: Click to Login button");
		loginPageObject.clickToLoginButton();
		
		System.out.println(" Login_03 - Step 04: Verify error message displayed");
		Assert.assertEquals(loginPageObject.getValidationSummaryErrorMessage(), "Login was unsuccessful. Please correct the errors and try again.\n"
				+ "No customer account found");

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
