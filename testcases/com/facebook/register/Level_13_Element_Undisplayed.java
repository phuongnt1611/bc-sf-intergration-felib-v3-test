package com.facebook.register;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;

import pageObjects.facebook.LoginPageObject;
import pageObjects.facebook.PageGeneratorManager;

public class Level_13_Element_Undisplayed extends BaseTest {
	WebDriver driver;
	private LoginPageObject loginPage;

	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@Parameters({ "browser", "url" })
	@BeforeClass
	public void beforeClass(String browserName, String appUrl) {
		driver = getBrowserDriver(browserName, appUrl);
		// Khởi tạo login page lên
		loginPage = PageGeneratorManager.getLoginPage(driver);

	}

	@Test
	public void TC_01_Verify_Element_Displayed() {
		loginPage.clickToCreateNewAccountButton();
		verifyTrue(loginPage.isEmailAddressTextBoxDisplayed());

	}

	@Test
	public void TC_02_Verify_Element_Undisplayed_In_DOM() {
		// Verify False - cho hàm trả về là dispalyed
		verifyFalse(loginPage.isConfirmEmailAddressTextboxDisplayed());

		// Verify True - mong đợi confirm email displayed
		loginPage.enterToEmailAddressTextbox("automation@gmail.com");
		loginPage.sleepInSecond(3);
		verifyTrue(loginPage.isConfirmEmailAddressTextboxDisplayed());

		// Xoá dữ liệu trong textbox đi => confirm email textbox mất
		loginPage.enterToEmailAddressTextbox("");
		loginPage.sleepInSecond(3);
		verifyFalse(loginPage.isConfirmEmailAddressTextboxDisplayed());

	}

	@Test
	public void TC_03_Verify_Element_Undisplayed_Not_In_DOM() {
		loginPage.clickCloseIconAtRegisterForm();
		loginPage.sleepInSecond(3);

		// Hàm isDisplayed: bản chất ko kiểm tra được 1 element ko có trong DOM
		// Vì nó chưa pass qua được hàm getWebELement()
		// 1 - Nos sẽ chờ hết timeout của implicit: 30s
		// 2 - Nó sẽ đánh fail testcase tại đúng step này luôn
		// 3 - ko chạy cách step còn lại nữa

		// Verify False = mong đợi Confirm Email Undisplayed(false)
		// isDisplayed: Bản chất ko kiểm tra 1 element ko có trong DOM được
		//verifyFalse(loginPage.isConfirmEmailAddressTextboxDisplayed());
		
		// Nhưng lại gặp issue => mất thời gian để verify 1 element ko hiển thị khi ko có trong DOM
		verifyTrue(loginPage.isConfirmEmailAddressTextboxUndisplayed());

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
