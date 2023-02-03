package commons;

import org.openqa.selenium.WebDriver;

import pageObject.boostcommerce.frontend.FELoginPageObject;
import pageObject.boostcommerce.frontend.FEStoreCollectionsPageObject;
import pageObject.boostcommerce.frontend.FEStoreHomePageObject;
import pageObject.boostcommerce.frontend.FEStoreSearchResultPageObject;
import pageObjects.boostcommerce.dashboard.DashboardPageObject;

public class PageGeneratorManager {
	public static FEStoreHomePageObject getFEStoreHomePage(WebDriver driver) {
		return new FEStoreHomePageObject(driver);
	}

	public static FEStoreCollectionsPageObject getFEStoreCollectionsPage(WebDriver driver) {
		return new FEStoreCollectionsPageObject(driver);
	}

	public static DashboardPageObject getDashboardPage(WebDriver driver) {
		return new DashboardPageObject(driver);
	}

	public static FELoginPageObject getFELoginPage(WebDriver driver) {
		return new FELoginPageObject(driver);
	}
	
	public static FEStoreSearchResultPageObject getSearchPage(WebDriver driver) {
		return new FEStoreSearchResultPageObject(driver);
	}

}
