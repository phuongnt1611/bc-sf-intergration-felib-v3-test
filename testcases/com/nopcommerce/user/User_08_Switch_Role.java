package com.nopcommerce.user;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageGeneratorManager;
import pageObject.nopCommerce.admin.AdminDashboardPageObject;
import pageObject.nopCommerce.admin.AdminLoginPageObject;
import pageObject.nopCommerce.user.UserAddressPageObject;
import pageObject.nopCommerce.user.UserCustomerInforPageObject;
import pageObject.nopCommerce.user.UserHomePageObject;
import pageObject.nopCommerce.user.UserLoginPageObject;
import pageObject.nopCommerce.user.UserMyProductReviewPageObject;
import pageObject.nopCommerce.user.UserRegisterPageObject;
import pageObject.nopCommerce.user.UserRewardPointPageObject;

public class User_08_Switch_Role extends BaseTest{
	WebDriver driver;
	private UserHomePageObject userHomePage;
	private UserLoginPageObject userLoginPage;
	private UserCustomerInforPageObject userCustomeInforPage;
	private AdminLoginPageObject adminLoginPage;
	private AdminDashboardPageObject adminDashboardPage;
	
	
    private String userEmail, userPassword, adminEmail, adminPassword;
	

	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		userEmail = "phuongn1111@gmail.com";
		userPassword = "123456";
		adminEmail = "admin@yourstore.com";
		adminPassword = "admin";
		driver = getBrowserDriver(browserName);
		userHomePage = PageGeneratorManager.getUserHomePage(driver);
	}
	
	@Test
	public void Role_01_User_Login_To_Admin() {
		userLoginPage = userHomePage.openLoginPage(driver);
		// login as user role
		userLoginPage.loginAsUser(userEmail, userPassword); 
		Assert.assertTrue(userHomePage.isMyAccountLinkDisplayed());	
		
		userCustomeInforPage=  userHomePage.openMyAccountPage();
		
		userHomePage = userCustomeInforPage.clickToLogoutLinkAtUserPage(driver);
		
		userHomePage.openPageUrl(driver, GlobalConstants.ADMIN_PAGE_URL);
		
		adminLoginPage = PageGeneratorManager.getAdminLoginPage(driver);
		
		adminDashboardPage = adminLoginPage.loginAsAdmin(adminEmail, adminPassword);
		Assert.assertTrue(adminDashboardPage.isDashboardHeaderDisplayed());	
	}
	
	@Test
	public void Role_02_Admin_Login_To_User() {
		adminLoginPage.openPageUrl(driver, GlobalConstants.USER_PAGE_URL);
	
		userHomePage = PageGeneratorManager.getUserHomePage(driver);
		
		userLoginPage = userHomePage.openLoginPage(driver);
		
		userHomePage = userLoginPage.loginAsUser(userEmail, userPassword);
		Assert.assertTrue(userHomePage.isMyAccountLinkDisplayed());
			
		 
	}
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
