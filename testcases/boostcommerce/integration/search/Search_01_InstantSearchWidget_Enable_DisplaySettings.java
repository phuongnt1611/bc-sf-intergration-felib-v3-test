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

public class Search_01_InstantSearchWidget_Enable_DisplaySettings extends BaseTest {
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
        
        log.info("Precondition - Step 1: Call admin setting's API in order to enable all `Instant search widget/Display Settings");
        AdminSettingsHelper.updateAdminSettings(environment.admin_settings_url_string(), environment.admin_settings_authToken(), "testdata/UpdateAdminSettings.json", "Search_01.beforeClass");

        log.info("Precondition- Step 2: Open front end store");
        driver = openBrowserDriver(browserName, environment.fe_store_preview_url());
        frontEndLoginPage = PageGeneratorManager.getFELoginPage(driver);
        
        log.info("Precondition - Step 3: Input password and enter");
        frontEndLoginPage.inputToPasswordAndClickEnter(environment.fe_store_password());
        
        log.info("Precondition - Step 4: Navigate to home page");
        frontEndHomePage = PageGeneratorManager.getFEStoreHomePage(driver);
        
        log.info("Precondition- Step 5: Wait 2m for updating all display settings");
        sleepInSecond(120);
        
        log.info("Precondition - Step 6: Refresh home page to wait all display settings are updated on FE store");
        frontEndHomePage.refreshCurrentPage(driver);
       
	} 
	    

	@Test
	public void ISW_001_Enable_Toggle_ISW(Method method) {
		ExtentTestManager.startTest(method.getName(), " Check ISW when enabled toggle 'Enable Instant search widget'");
		ExtentTestManager.getTest().log(Status.INFO, " ISW_001 - Step 01: Verify that the homepage is opened");
        verifyEquals(frontEndHomePage.getTitleHomePage(), "bc-dev-v2-norah-03");
        
    	ExtentTestManager.getTest().log(Status.INFO, "ISW_001 - Step 02: Click and input search keyword in ISW on home page");
		frontEndHomePage.clickAndInputSearchKeywordInISW(driver, "keyboard");

		ExtentTestManager.getTest().log(Status.INFO, "ISW_001 - Step 03: Verify that ISW is displayed");
		verifyTrue(frontEndHomePage.isInstantSearchWidgetDisplayed(driver));

		ExtentTestManager.getTest().log(Status.INFO, "ISW_001 - Step 04: Navigate to collection all page");
		frontEndCollectionsPage= (FEStoreCollectionsPageObject) frontEndHomePage.openDynamicPage(driver, "All Products");

		ExtentTestManager.getTest().log(Status.INFO, "ISW_001 - Step 05: Click and input search keyword in ISW on collection page");
		frontEndCollectionsPage.clickAndInputSearchKeywordInISW(driver, "keyboard");

		ExtentTestManager.getTest().log(Status.INFO, "ISW_001 - Step 06: Verify that ISW is displayed");
		verifyTrue(frontEndCollectionsPage.isInstantSearchWidgetDisplayed(driver));
		
		ExtentTestManager.getTest().log(Status.INFO, "ISW_001 - Step 07: Navigate to 'search' page ");
		frontEndSearchResultPage = (FEStoreSearchResultPageObject) frontEndCollectionsPage.openDynamicUrl(driver, environment.fe_store_url(), "/search");
		
		ExtentTestManager.getTest().log(Status.INFO, "ISW_001 - Step 08: Click and input search keyword in ISW on search page");
		frontEndCollectionsPage.clickAndInputSearchKeywordInISW(driver, "keyboard");
		
		ExtentTestManager.getTest().log(Status.INFO, "ISW_001 - Step 09: Verify that ISW is displayed");
		verifyTrue(frontEndSearchResultPage.isInstantSearchWidgetDisplayed(driver));
		frontEndSearchResultPage.refreshCurrentPage(driver);
		ExtentTestManager.endTest();
	}
	
	@Test
	public void ISW_002_DisplaySettings_Enable_PopularSuggestions(Method method) {
		ExtentTestManager.startTest(method.getName(), "Check 'ISW - popular suggestion displayed  ");

		ExtentTestManager.getTest().log(Status.INFO, "ISW_002 - Step 01: Open the url for FE store - home page ");
		frontEndHomePage = (FEStoreHomePageObject) frontEndSearchResultPage.openDynamicPage(driver, "Home");

		ExtentTestManager.getTest().log(Status.INFO, " ISW_001 - Step 02: Verify that the homepage is opened");
        verifyEquals(frontEndHomePage.getTitleHomePage(), "bc-dev-v2-norah-03");
	
		ExtentTestManager.getTest().log(Status.INFO, "ISW_002 - Step 03: Click and input search keyword in ISW on home page");
		frontEndHomePage.clickAndInputSearchKeywordInISW(driver, "plastic");
		
		ExtentTestManager.getTest().log(Status.INFO, "ISW_002 - Step 04: Verify ISW is displayed");
		verifyTrue(frontEndHomePage.isInstantSearchWidgetDisplayed(driver));
		
		ExtentTestManager.getTest().log(Status.INFO, "ISW_002 - Step 05: Verify 'Popular Suggestions' session is displayed");
		verifyTrue(frontEndHomePage.isContentTypeHeaderDisplayed(driver, "Popular suggestions"));
		
		ExtentTestManager.endTest();

	}
	
	@Test
	public void ISW_003_DisplaySettings_Enable_Collections(Method method) {
		ExtentTestManager.startTest(method.getName(), "Check 'ISW - collections displayed  ");
		ExtentTestManager.getTest().log(Status.INFO, " ISW_001 - Step 02: Verify that the homepage is opened");
        verifyEquals(frontEndHomePage.getTitleHomePage(), "bc-dev-v2-norah-03");
        
		ExtentTestManager.getTest().log(Status.INFO, "ISW_003 - Step 03: Click and input search keyword in ISW on search page");
		frontEndHomePage.clickAndInputSearchKeywordInISW(driver, "Dress");
		
		ExtentTestManager.getTest().log(Status.INFO, "ISW_003 - Step 04: Verify ISW is displayed");
		verifyTrue(frontEndHomePage.isInstantSearchWidgetDisplayed(driver));
		
		ExtentTestManager.getTest().log(Status.INFO, "ISW_003 - Step 05: Verify 'Collections' session is displayed");
		verifyTrue(frontEndHomePage.isContentTypeHeaderDisplayed(driver, "Collections"));
		ExtentTestManager.endTest();
	}
	@Test
	public void ISW_004_DisplaySettings_Enable_Products(Method method) {
		ExtentTestManager.startTest(method.getName(), "Check 'ISW - Products displayed ");
		ExtentTestManager.getTest().log(Status.INFO, " ISW_001 - Step 02: Verify that the homepage is opened");
        verifyEquals(frontEndHomePage.getTitleHomePage(), "bc-dev-v2-norah-03");
		
		ExtentTestManager.getTest().log(Status.INFO, "ISW_004 - Step 03: Click and input search keyword in ISW on search page");
		frontEndHomePage.clickAndInputSearchKeywordInISW(driver, "keyboard");
		
		ExtentTestManager.getTest().log(Status.INFO, "ISW_004 - Step 04: Verify ISW is displayed");
		verifyTrue(frontEndHomePage.isInstantSearchWidgetDisplayed(driver));
		
		ExtentTestManager.getTest().log(Status.INFO, "ISW_004 - Step 05: Verify 'Products' session is displayed");
		verifyTrue(frontEndHomePage.isContentTypeHeaderDisplayed(driver, "Products"));
		ExtentTestManager.endTest();
	}
	
	@Test
	public void ISW_005_DisplaySettings_Enable_BlogsAndPages(Method method) {
		ExtentTestManager.startTest(method.getName(), "Check 'ISW - blogs& pages displayed ");
		ExtentTestManager.getTest().log(Status.INFO, " ISW_001 - Step 02: Verify that the homepage is opened");
        verifyEquals(frontEndHomePage.getTitleHomePage(), "bc-dev-v2-norah-03");
		
		ExtentTestManager.getTest().log(Status.INFO, "ISW_005 - Step 01: Click and input search keyword in ISW on search page");
		frontEndHomePage.clickAndInputSearchKeywordInISW(driver, "Preview");
		
		ExtentTestManager.getTest().log(Status.INFO, "ISW_005 - Step 02: Verify ISW is displayed");
		verifyTrue(frontEndHomePage.isInstantSearchWidgetDisplayed(driver));
		
		ExtentTestManager.getTest().log(Status.INFO, "ISW_005 - Step 03: Verify 'Blog & Pages' session is displayed");
		verifyTrue(frontEndHomePage.isContentTypeHeaderDisplayed(driver, "Blog & Pages"));
		ExtentTestManager.endTest();
	}
	
	@Test
	public void ISW_006_DisplaySettings_Enable_OutOfStockProducts(Method method) {
		ExtentTestManager.startTest(method.getName(), "Check out stock products suggestion not display when disable toggle 'Out of stock product'");
		ExtentTestManager.getTest().log(Status.INFO, " ISW_006 - Step 01: Verify that the homepage is opened");
        verifyEquals(frontEndHomePage.getTitleHomePage(), "bc-dev-v2-norah-03");
        
    	ExtentTestManager.getTest().log(Status.INFO, "ISW_006 - Step 02: Click and input search keyword in ISW on search page");
		frontEndHomePage.clickAndInputSearchKeywordInISW(driver, "Alpha Industries MA-1 Reversible Bomber Jacket");
		
		ExtentTestManager.getTest().log(Status.INFO, "ISW_006 - Step 03: Verify ISW is displayed");
		verifyTrue(frontEndHomePage.isInstantSearchWidgetDisplayed(driver));
		
		ExtentTestManager.getTest().log(Status.INFO, "ISW_004 - Step 05: Verify 'Products' session is displayed");
		verifyTrue(frontEndHomePage.isContentTypeHeaderDisplayed(driver, "Products"));
		
		ExtentTestManager.getTest().log(Status.INFO, "ISW_006 - Step 04: Verify that the out of product is displayed in ISW");
		verifyTrue(frontEndHomePage.isOutOfProductsSessionDisplayed("Alpha Industries MA-1 Reversible Bomber Jacket"));
		
		ExtentTestManager.getTest().log(Status.INFO, "ISW_006 - Step 04: Verify that the out of product is displayed in ISW");
		verifyTrue(frontEndHomePage.isOutOfProductItemDisplayed());
		
		ExtentTestManager.endTest();
	}
	
	@Test
	public void ISW_007_DisplaySettings_Enable_ProductVender(Method method) {
		ExtentTestManager.startTest(method.getName(), "Check vendor products suggestion displays when enabled toggle 'Product vendor'");
		ExtentTestManager.getTest().log(Status.INFO, " ISW_007 - Step 01: Verify that the homepage is opened");
        verifyEquals(frontEndHomePage.getTitleHomePage(), "bc-dev-v2-norah-03");
        
        ExtentTestManager.getTest().log(Status.INFO, "ISW_007 - Step 02: Click and input search keyword in ISW on search page");
		frontEndHomePage.clickAndInputSearchKeywordInISW(driver, "Gorgeous Linen Watch");
		
		ExtentTestManager.getTest().log(Status.INFO, "ISW_007 - Step 03: Verify ISW is displayed");
		verifyTrue(frontEndHomePage.isInstantSearchWidgetDisplayed(driver));
		
		ExtentTestManager.getTest().log(Status.INFO, "ISW_007 - Step 04: Verify 'Products' session is displayed");
		verifyTrue(frontEndHomePage.isContentTypeHeaderDisplayed(driver, "Products"));
		
		ExtentTestManager.getTest().log(Status.INFO, "ISW_007 - Step 05: Verify that the product vendor is displayed in ISW");
		verifyTrue(frontEndHomePage.isProductVendorDisplayed("Gorgeous Linen Watch","Terry-Jacobi"));
		
	}
	
	@Test
	public void ISW_008_DisplaySettings_Enable_ProductPrice(Method method) {
		ExtentTestManager.startTest(method.getName(), "Check vendor products suggestion displays when enabled toggle 'Product vendor'");
		ExtentTestManager.getTest().log(Status.INFO, " ISW_008 - Step 01: Verify that the homepage is opened");
        verifyEquals(frontEndHomePage.getTitleHomePage(), "bc-dev-v2-norah-03");
        
        ExtentTestManager.getTest().log(Status.INFO, "ISW_008 - Step 02: Click and input search keyword in ISW on search page");
		frontEndHomePage.clickAndInputSearchKeywordInISW(driver, "Gorgeous Linen Watch");
		
		ExtentTestManager.getTest().log(Status.INFO, "ISW_008 - Step 03: Verify ISW is displayed");
		verifyTrue(frontEndHomePage.isInstantSearchWidgetDisplayed(driver));
		
		ExtentTestManager.getTest().log(Status.INFO, "ISW_008 - Step 04: Verify 'Products' session is displayed");
		verifyTrue(frontEndHomePage.isContentTypeHeaderDisplayed(driver, "Products"));
		
		ExtentTestManager.getTest().log(Status.INFO, "ISW_008 - Step 05: Verify that the 'Product price' is displayed in ISW");
		verifyTrue(frontEndHomePage.isProductPriceDisplayed("Gorgeous Linen Watch","5 VND"));
	}
	
	@Test
	public void ISW_009_DisplaySettings_Enable_ProductImage(Method method) {
		ExtentTestManager.startTest(method.getName(), "Check image products suggestion display when enabled toggle 'Product image'");
		ExtentTestManager.getTest().log(Status.INFO, " ISW_009 - Step 01: Verify that the homepage is opened");
        verifyEquals(frontEndHomePage.getTitleHomePage(), "bc-dev-v2-norah-03");
        
        ExtentTestManager.getTest().log(Status.INFO, "ISW_009 - Step 02: Click and input search keyword in ISW on search page");
		frontEndHomePage.clickAndInputSearchKeywordInISW(driver, "Gorgeous Linen Watch");
		
		ExtentTestManager.getTest().log(Status.INFO, "ISW_009 - Step 03: Verify ISW is displayed");
		verifyTrue(frontEndHomePage.isInstantSearchWidgetDisplayed(driver));
		
		ExtentTestManager.getTest().log(Status.INFO, "ISW_009 - Step 04: Verify 'Products' session is displayed");
		verifyTrue(frontEndHomePage.isContentTypeHeaderDisplayed(driver, "Products"));
		
		ExtentTestManager.getTest().log(Status.INFO, "ISW_009 - Step 05: Verify that the 'Product price' is displayed in ISW");
		verifyTrue(frontEndHomePage.isProductImageDisplayed("Gorgeous Linen Watch","Gorgeous Linen Watch"));
	}
	
	@Test
	public void ISW_010_DisplaySettings_Enable_ProductSalePrice(Method method) {
		ExtentTestManager.startTest(method.getName(), "Check sale price products suggestion not display when disable toggle 'Product sale price'");
		
		ExtentTestManager.getTest().log(Status.INFO, " ISW_010 - Step 01: Verify that the homepage is opened");
        verifyEquals(frontEndHomePage.getTitleHomePage(), "bc-dev-v2-norah-03");
        
        ExtentTestManager.getTest().log(Status.INFO, "ISW_010 - Step 02: Click and input search keyword in ISW on search page");
		frontEndHomePage.clickAndInputSearchKeywordInISW(driver, "Gorgeous Linen Watch");
		
		ExtentTestManager.getTest().log(Status.INFO, "ISW_010 - Step 03: Verify ISW is displayed");
		verifyTrue(frontEndHomePage.isInstantSearchWidgetDisplayed(driver));
		
		ExtentTestManager.getTest().log(Status.INFO, "ISW_010 - Step 04: Verify 'Products' session is displayed");
		verifyTrue(frontEndHomePage.isContentTypeHeaderDisplayed(driver, "Products"));
		
		ExtentTestManager.getTest().log(Status.INFO, "ISW_010 - Step 05: Verify that the 'Product sale price' is displayed in ISW");
		verifyTrue(frontEndHomePage.isProductSalePriceDisplayed("Ergonomic Linen Watch"));
	}
	
	@Test
	public void ISW_011_DisplaySettings_Enable_ProductSKU(Method method) {
	    ExtentTestManager.startTest(method.getName(), "Check sale price products suggestion not display when disable toggle 'Product SKU'");
		
		ExtentTestManager.getTest().log(Status.INFO, " ISW_011 - Step 01: Verify that the homepage is opened");
        verifyEquals(frontEndHomePage.getTitleHomePage(), "bc-dev-v2-norah-03");
        
        ExtentTestManager.getTest().log(Status.INFO, "ISW_011 - Step 02: Click and input search keyword in ISW on search page");
		frontEndHomePage.clickAndInputSearchKeywordInISW(driver, "Gorgeous Linen Watch");
		
		ExtentTestManager.getTest().log(Status.INFO, "ISW_011 - Step 03: Verify ISW is displayed");
		verifyTrue(frontEndHomePage.isInstantSearchWidgetDisplayed(driver));
		
		ExtentTestManager.getTest().log(Status.INFO, "ISW_011 - Step 04: Verify 'Products' session is displayed");
		verifyTrue(frontEndHomePage.isContentTypeHeaderDisplayed(driver, "Products"));
		
		ExtentTestManager.getTest().log(Status.INFO, "ISW_011 - Step 05: Verify that the 'Product SKU' is displayed in ISW");
		verifyTrue(frontEndHomePage.isProductSKUDisplayed("Ergonomic Linen Watch", "ergonomic-linen-watch-small"));
	}
	
	@AfterClass
    public void afterClass() {
        log.info("===== END TESTING ======");
        log.info("Close browser and driver");
        closeBrowserAndDriver(driver);
    }
}

