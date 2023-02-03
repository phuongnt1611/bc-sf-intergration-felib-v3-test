package boostcommerce.integration.search;

import java.lang.reflect.Method;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObject.boostcommerce.frontend.FELoginPageObject;
import pageObject.boostcommerce.frontend.FEStoreCollectionsPageObject;
import pageObject.boostcommerce.frontend.FEStoreHomePageObject;
import pageObject.boostcommerce.frontend.FEStoreSearchResultPageObject;
import reportConfig.ExtentTestManager;
import utilities.AdminSettingsHelper;
import utilities.Environment;

public class Search_02_InstantSearchWidget_Disable_DisplaySettings extends BaseTest {
	WebDriver driver;
	private FEStoreHomePageObject frontEndHomePage;
	private FELoginPageObject frontEndLoginPage;
	private FEStoreCollectionsPageObject frontEndCollectionsPage;
	private FEStoreSearchResultPageObject frontEndSearchResultPage;
	Environment environment;
	
	@Parameters({ "browser", "environment" })
	@BeforeClass
	public void beforeClass(String browserName, String environmentName) {
        ConfigFactory.setProperty("env", environmentName);
        environment = ConfigFactory.create(Environment.class);
        
        log.info("===== START TESTING ===TC: Search_01_InstantSearchWidget_Enable_DisplaySettings ======");
        
        log.info("Precondition - Step 1- Call admin setting's API in order to disable all `Instant search widget/Display Settings/ Product suggestion display'");
		AdminSettingsHelper.updateAdminSettings(environment.admin_settings_url_string(), environment.admin_settings_authToken(), "testdata/UpdateAdminSettings.json", "Search_02.beforeClass");
		
        log.info("Precondition - Step 2: Open front end store");
        driver = openBrowserDriver(browserName, environment.fe_store_preview_url());
        frontEndLoginPage = PageGeneratorManager.getFELoginPage(driver);
        
        log.info("Precondition - Step 3: Input password and enter");
        frontEndLoginPage.inputToPasswordAndClickEnter(environment.fe_store_password());
        
        log.info("Precondition - Step 4: Navigate to homepage");
        frontEndHomePage = PageGeneratorManager.getFEStoreHomePage(driver);
        
        log.info("Precondition- Step 5: Wait 2m for updating all display settings");
        sleepInSecond(120);
        
        log.info("Precondition - Step 6: Refresh home page to wait all display settings are updated on FE store");
        frontEndHomePage.refreshCurrentPage(driver);
        
   
	}
	
	@Test
	public void ISW_012_DisplaySettings_Disable_OutOfStockProducts(Method method) {
		ExtentTestManager.startTest(method.getName(), "Check out stock products suggestion is undisplay when disable toggle 'Out of stock product'");
		ExtentTestManager.getTest().log(Status.INFO, " ISW_012 - Step 01: Verify that the homepage is opened");
        verifyEquals(frontEndHomePage.getTitleHomePage(), "bc-dev-v2-norah-03");
        
    	ExtentTestManager.getTest().log(Status.INFO, "ISW_012 - Step 02: Click and input search keyword in ISW on search page");
		frontEndHomePage.clickAndInputSearchKeywordInISW(driver, "Alpha Industries MA-1 Reversible Bomber Jacket");
		
		ExtentTestManager.getTest().log(Status.INFO, "ISW_012 - Step 03: Verify ISW is displayed");
		verifyTrue(frontEndHomePage.isInstantSearchWidgetDisplayed(driver));
		
		ExtentTestManager.getTest().log(Status.INFO, "ISW_012 - Step 04: Verify that the out of product is undisplayed in ISW");

		verifyFalse(frontEndHomePage.isOutOfProductsSessionDisplayed("Alpha Industries MA-1 Reversible Bomber Jacket"));
		ExtentTestManager.endTest();
	}
	
	@Test
	public void ISW_013_DisplaySettings_Disable_ProductVender(Method method) {
		ExtentTestManager.startTest(method.getName(), "Check vendor products suggestion is undisplays when disable toggle 'Product vendor'");
		ExtentTestManager.getTest().log(Status.INFO, " ISW_013 - Step 01: Verify that the homepage is opened");
        verifyEquals(frontEndHomePage.getTitleHomePage(), "bc-dev-v2-norah-03");
        
        ExtentTestManager.getTest().log(Status.INFO, "ISW_013 - Step 02: Click and input search keyword in ISW on search page");
		frontEndHomePage.clickAndInputSearchKeywordInISW(driver, "Gorgeous Linen Watch");
		
		ExtentTestManager.getTest().log(Status.INFO, "ISW_013 - Step 03: Verify ISW is displayed");
		verifyTrue(frontEndHomePage.isInstantSearchWidgetDisplayed(driver));
		
		ExtentTestManager.getTest().log(Status.INFO, "ISW_013 - Step 04: Verify 'Products' session is displayed");
		verifyTrue(frontEndHomePage.isContentTypeHeaderDisplayed(driver, "Products"));
		
		ExtentTestManager.getTest().log(Status.INFO, "ISW_013 - Step 05: Verify that the 'Product vendor' is undisplayed in ISW");
		verifyFalse(frontEndHomePage.isProductVendorDisplayed("Gorgeous Linen Watch","Terry-Jacobi"));
	}
	
	@Test
	public void ISW_014_DisplaySettings_Disable_ProductPrice(Method method) {
		ExtentTestManager.startTest(method.getName(), "Check price products suggestion is undisplay when disable toggle 'Product price'");
		ExtentTestManager.getTest().log(Status.INFO, "ISW_014 - Step 01: Verify that the homepage is opened");
        verifyEquals(frontEndHomePage.getTitleHomePage(), "bc-dev-v2-norah-03");
        
        ExtentTestManager.getTest().log(Status.INFO, "ISW_014 - Step 02: Click and input search keyword in ISW on search page");
		frontEndHomePage.clickAndInputSearchKeywordInISW(driver, "Gorgeous Linen Watch");
		
		ExtentTestManager.getTest().log(Status.INFO, "ISW_014 - Step 03: Verify ISW is displayed");
		verifyTrue(frontEndHomePage.isInstantSearchWidgetDisplayed(driver));
		
		ExtentTestManager.getTest().log(Status.INFO, "ISW_014 - Step 04: Verify 'Products' session is displayed");
		verifyTrue(frontEndHomePage.isContentTypeHeaderDisplayed(driver, "Products"));
		
		ExtentTestManager.getTest().log(Status.INFO, "ISW_014 - Step 05: Verify that the 'Product price' is undisplayed in ISW");
		verifyFalse(frontEndHomePage.isProductPriceDisplayed("Gorgeous Linen Watch","5 VND"));
	}
	
	@Test
	public void ISW_015_DisplaySettings_ProductImage(Method method) {
		ExtentTestManager.startTest(method.getName(), "Check product image is undisplay when disable toggle 'Product image'");
		ExtentTestManager.getTest().log(Status.INFO, " ISW_015 - Step 01: Verify that the homepage is opened");
        verifyEquals(frontEndHomePage.getTitleHomePage(), "bc-dev-v2-norah-03");
        
        ExtentTestManager.getTest().log(Status.INFO, "ISW_015 - Step 02: Click and input search keyword in ISW on search page");
		frontEndHomePage.clickAndInputSearchKeywordInISW(driver, "Gorgeous Linen Watch");
		
		ExtentTestManager.getTest().log(Status.INFO, "ISW_015 - Step 03: Verify ISW is displayed");
		verifyTrue(frontEndHomePage.isInstantSearchWidgetDisplayed(driver));
		
		ExtentTestManager.getTest().log(Status.INFO, "ISW_015 - Step 04: Verify 'Products' session is displayed");
		verifyTrue(frontEndHomePage.isContentTypeHeaderDisplayed(driver, "Products"));
		
		ExtentTestManager.getTest().log(Status.INFO, "ISW_015 - Step 05: Verify that the 'Product price' is undisplayed in ISW");
		verifyFalse(frontEndHomePage.isProductImageDisplayed("Gorgeous Linen Watch","Gorgeous Linen Watch"));
	}
	
	@Test
	public void ISW_016_DisplaySettings_ProductSalePrice(Method method) {
		ExtentTestManager.startTest(method.getName(), "Check sale price product sale price is undisplay when disable toggle 'Product sale price'");
		ExtentTestManager.getTest().log(Status.INFO, " ISW_016 - Step 01: Verify that the homepage is opened");
        verifyEquals(frontEndHomePage.getTitleHomePage(), "bc-dev-v2-norah-03");
        
        ExtentTestManager.getTest().log(Status.INFO, "ISW_016 - Step 02: Click and input search keyword in ISW on search page");
		frontEndHomePage.clickAndInputSearchKeywordInISW(driver, "Gorgeous Linen Watch");
		
		ExtentTestManager.getTest().log(Status.INFO, "ISW_016 - Step 03: Verify ISW is displayed");
		verifyTrue(frontEndHomePage.isInstantSearchWidgetDisplayed(driver));
		
		ExtentTestManager.getTest().log(Status.INFO, "ISW_016 - Step 04: Verify 'Products' session is displayed");
		verifyTrue(frontEndHomePage.isContentTypeHeaderDisplayed(driver, "Products"));
		
		ExtentTestManager.getTest().log(Status.INFO, "ISW_016 - Step 05: Verify that the 'Product sale price' is undisplayed in ISW");
		verifyFalse(frontEndHomePage.isProductSalePriceDisplayed("Ergonomic Linen Watch"));
	}
	
	@Test
	public void ISW_017_DisplaySettings_Disable_ProductSKU(Method method) {
		  ExtentTestManager.startTest(method.getName(), "Check sale price products SKU not display when disable toggle 'Product SKU'");
			
			ExtentTestManager.getTest().log(Status.INFO, "ISW_017 - Step 01: Verify that the homepage is opened");
	        verifyEquals(frontEndHomePage.getTitleHomePage(), "bc-dev-v2-norah-03");
	        
	        ExtentTestManager.getTest().log(Status.INFO, "ISW_017 - Step 02: Click and input search keyword in ISW on search page");
			frontEndHomePage.clickAndInputSearchKeywordInISW(driver, "Gorgeous Linen Watch");
			
			ExtentTestManager.getTest().log(Status.INFO, "ISW_017 - Step 03: Verify ISW is displayed");
			verifyTrue(frontEndHomePage.isInstantSearchWidgetDisplayed(driver));
			
			ExtentTestManager.getTest().log(Status.INFO, "ISW_017 - Step 04: Verify 'Products' session is displayed");
			verifyTrue(frontEndHomePage.isContentTypeHeaderDisplayed(driver, "Products"));
			
			ExtentTestManager.getTest().log(Status.INFO, "ISW_017 - Step 05: Verify that the 'Product SKU' is undisplayed in ISW");
			verifyFalse(frontEndHomePage.isProductSKUDisplayed("Ergonomic Linen Watch", "ergonomic-linen-watch-small"));
	}
	
	@Test
	public void ISW_018_DisplaySettings_Disable_PopularSuggestions(Method method) {
		ExtentTestManager.startTest(method.getName(), "Check 'ISW - popular suggestion displayed  ");
		ExtentTestManager.getTest().log(Status.INFO, "ISW_018- Step 01: Call admin setting's API in order to disable all `Instant search widget/Display Settings: Popular suggestion, Collections, Blog & Pages'");
		AdminSettingsHelper.updateAdminSettings(environment.admin_settings_url_string(), environment.admin_settings_authToken(), "testdata/UpdateAdminSettings.json", "Search_02.ISW_018");
		
		ExtentTestManager.getTest().log(Status.INFO, "ISW_018- Step 02: Wait 2m for updating all display settings");
        sleepInSecond(180);
        
		ExtentTestManager.getTest().log(Status.INFO, "ISW_018 - Step 03: Refresh home page to wait all display settings are updated on FE store ");
		frontEndHomePage = PageGeneratorManager.getFEStoreHomePage(driver);
		frontEndHomePage.refreshCurrentPage(driver);
		
		ExtentTestManager.getTest().log(Status.INFO, " ISW_018 - Step 04: Verify that the homepage is opened");
        verifyEquals(frontEndHomePage.getTitleHomePage(), "bc-dev-v2-norah-03");
	
		ExtentTestManager.getTest().log(Status.INFO, "ISW_002 - Step 05: Click and input search keyword in ISW on home page");
		frontEndHomePage.clickAndInputSearchKeywordInISW(driver, "plastic");
		
		ExtentTestManager.getTest().log(Status.INFO, "ISW_002 - Step 06: Verify ISW is displayed");
		verifyTrue(frontEndHomePage.isInstantSearchWidgetDisplayed(driver));
		
		ExtentTestManager.getTest().log(Status.INFO, "ISW_002 - Step 07: Verify 'Popular Suggestions' session is undisplayed");
		verifyFalse(frontEndHomePage.isContentTypeHeaderDisplayed(driver, "Popular suggestions"));
		
		ExtentTestManager.endTest();
	}
	
	@Test
	public void ISW_019_DisplaySettings_Disable_Collections(Method method) {
		ExtentTestManager.startTest(method.getName(), "Check 'ISW - collections displayed  ");
		ExtentTestManager.getTest().log(Status.INFO, "ISW_019 - Step 01: Verify that the homepage is opened");
        verifyEquals(frontEndHomePage.getTitleHomePage(), "bc-dev-v2-norah-03");
        
		ExtentTestManager.getTest().log(Status.INFO, "ISW_019 - Step 02: Click and input search keyword in ISW on search page");
		frontEndHomePage.clickAndInputSearchKeywordInISW(driver, "Dress");
		
		ExtentTestManager.getTest().log(Status.INFO, "ISW_019 - Step 03: Verify ISW is displayed");
		verifyTrue(frontEndHomePage.isInstantSearchWidgetDisplayed(driver));
		
		ExtentTestManager.getTest().log(Status.INFO, "ISW_019 - Step 04: Verify 'Collections' session is displayed");
		verifyFalse(frontEndHomePage.isContentTypeHeaderDisplayed(driver, "Collections"));
		ExtentTestManager.endTest();
	}

	@Test
	public void ISW_020_DisplaySettings_Disable_BlogsAndPages(Method method) {
		ExtentTestManager.startTest(method.getName(), "Check 'ISW - blogs& pages displayed ");
		ExtentTestManager.getTest().log(Status.INFO, "ISW_021 - Step 02: Verify that the homepage is opened");
        verifyEquals(frontEndHomePage.getTitleHomePage(), "bc-dev-v2-norah-03");
		
		ExtentTestManager.getTest().log(Status.INFO, "ISW_021 - Step 01: Click and input search keyword in ISW on search page");
		frontEndHomePage.clickAndInputSearchKeywordInISW(driver, "Preview");
		
		ExtentTestManager.getTest().log(Status.INFO, "ISW_021 - Step 02: Verify ISW is displayed");
		verifyTrue(frontEndHomePage.isInstantSearchWidgetDisplayed(driver));
		
		ExtentTestManager.getTest().log(Status.INFO, "ISW_021 - Step 03: Verify 'Blog & Pages' session is displayed");
		verifyFalse(frontEndHomePage.isContentTypeHeaderDisplayed(driver, "Blog & Pages"));
		ExtentTestManager.endTest();
	}
	
	@Test
	public void ISW_021_DisplaySettings_Disable_Products(Method method) {
		ExtentTestManager.startTest(method.getName(), "Check 'ISW - Products displayed ");
		ExtentTestManager.getTest().log(Status.INFO, "ISW_021- Step 01: Call admin setting's API in order to disable all `Instant search widget/Display Settings: Popular suggestion, Collections, Blog & Pages'");
		AdminSettingsHelper.updateAdminSettings(environment.admin_settings_url_string(), environment.admin_settings_authToken(), "testdata/UpdateAdminSettings.json", "Search_02.ISW_021");
		
		ExtentTestManager.getTest().log(Status.INFO, "ISW_021- Step 02: Wait 2m for updating all display settings and refresh page");
        sleepInSecond(180);
        frontEndHomePage.refreshCurrentPage(driver);
        
		ExtentTestManager.getTest().log(Status.INFO, "ISW_021 - Step 03: Verify that the homepage is opened");
        verifyEquals(frontEndHomePage.getTitleHomePage(), "bc-dev-v2-norah-03");
		
		ExtentTestManager.getTest().log(Status.INFO, "ISW_021 - Step 04: Click and input search keyword in ISW on search page");
		frontEndHomePage.clickAndInputSearchKeywordInISW(driver, "keyboard");
		
		ExtentTestManager.getTest().log(Status.INFO, "ISW_021 - Step 05: Verify ISW is displayed");
		verifyTrue(frontEndHomePage.isInstantSearchWidgetDisplayed(driver));
		
		ExtentTestManager.getTest().log(Status.INFO, "ISW_021 - Step 06: Verify 'Products' session is undisplayed");
		verifyFalse(frontEndHomePage.isContentTypeHeaderDisplayed(driver, "Products"));
		ExtentTestManager.endTest();
	}
	
	@Test
	public void ISW_022_Disable_Toggle_ISW(Method method) {
		ExtentTestManager.startTest(method.getName(), "Check ISW when disable toggle 'Enable Instant search widget");
		
		ExtentTestManager.getTest().log(Status.INFO, "ISW_022- Step 01: Call admin setting's API in order to disable all `Instant search widget/Display Settings: Popular suggestion, Collections, products, Blog & Pages'");
		AdminSettingsHelper.updateAdminSettings(environment.admin_settings_url_string(), environment.admin_settings_authToken(), "testdata/UpdateAdminSettings.json", "Search_02.ISW_022");
		
		ExtentTestManager.getTest().log(Status.INFO, "ISW_022- Step 02: Wait 2m for updating all display settings and refresh page");
        sleepInSecond(180);
        frontEndHomePage.refreshCurrentPage(driver);
        
		ExtentTestManager.getTest().log(Status.INFO, "ISW_022 - Step 03: Open the url for FE store - home page");
		frontEndHomePage = PageGeneratorManager.getFEStoreHomePage(driver);
		
		ExtentTestManager.getTest().log(Status.INFO, "ISW_022 - Step 04: Verify that the homepage is opened");
        verifyEquals(frontEndHomePage.getTitleHomePage(), "bc-dev-v2-norah-03");
		
		ExtentTestManager.getTest().log(Status.INFO, "ISW_022 - Step 05: Verify element not in DOM: the ISW of PFS app is not in DOM");
		verifyTrue(frontEndHomePage.isInstantSearchWidgetUndisplayed(driver));
		
		ExtentTestManager.getTest().log(Status.INFO, "ISW_022 - Step 06: Navigate to collection all page");
		frontEndCollectionsPage= (FEStoreCollectionsPageObject) frontEndHomePage.openDynamicPage(driver, "All Products");
		
		ExtentTestManager.getTest().log(Status.INFO, "ISW_022 - Step 07: Verify the collection page is displayed correctly ");
		verifyTrue(frontEndCollectionsPage.isCollectionsAllPageDisplayed());

		ExtentTestManager.getTest().log(Status.INFO, "ISW_022 - Step 08: Verify element not in DOM: the ISW of PFS app is not in DOM");
		verifyTrue(frontEndCollectionsPage.isInstantSearchWidgetUndisplayed(driver));
		
		ExtentTestManager.getTest().log(Status.INFO, "ISW_022 - Step 09: Navigate to 'search' page");
		frontEndSearchResultPage = (FEStoreSearchResultPageObject) frontEndCollectionsPage.openDynamicUrl(driver, environment.fe_store_url(), "/search");
		
		ExtentTestManager.getTest().log(Status.INFO, "ISW_022 - Step 10: Verify 'search' page is displayed correctly");
		verifyTrue(frontEndSearchResultPage.isSearchPageDisplayed());
		
		ExtentTestManager.getTest().log(Status.INFO, "ISW_022 - Step 11: Verify element not in DOM: the ISW of PFS app is not in DOM");
		verifyTrue(frontEndSearchResultPage.isInstantSearchWidgetUndisplayed(driver));
		
		ExtentTestManager.endTest();
	}
	
	@AfterClass
    public void afterClass() {
        log.info("===== END TESTING ======");
        log.info("Close browser and driver");
       closeBrowserAndDriver(driver);
    }
}

