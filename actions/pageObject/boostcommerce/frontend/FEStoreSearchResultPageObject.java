package pageObject.boostcommerce.frontend;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.fontEndStore.FrontEndHomePageUI;
import pageUIs.fontEndStore.FrontEndSearchPageUI;

public class FEStoreSearchResultPageObject extends BasePage {
	WebDriver driver;
    private final Log log = LogFactory.getLog(getClass());

	public FEStoreSearchResultPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public boolean isSearchPageDisplayed() {
		waitForElementVisible(driver, FrontEndSearchPageUI.SEARCH_HEADER);
		return isElementDisplayed(driver, FrontEndSearchPageUI.SEARCH_HEADER);
	}

	public void closeSearchBox() {
        sendESCKeys(driver);		
	}
}
