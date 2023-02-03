package pageObject.boostcommerce.frontend;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.fontEndStore.FrontEndCollectionsUI;
import pageUIs.fontEndStore.FrontEndHomePageUI;

public class FEStoreCollectionsPageObject extends BasePage {
	WebDriver driver;
    private final Log log = LogFactory.getLog(getClass());

	public FEStoreCollectionsPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public String getTitleCollectionsPage() {
	        String title = "";
	        try {
	            sleepInSecond(3);
	            title = getCurrentPageTitle(driver);
	        } catch (Exception e) {
	            log.debug("|FEStoreCollectionsPageObject| - |getTitleCollectionPage| - Cannot get title of Collections page: " + e.getMessage());
	        }
	        return title;
	    }

	public boolean hasCorrectUrl(String expectedUrl) {
		String url = "";
		url = getCurrentPageUrl(driver);
			if(url.contains(expectedUrl)) {
				return true;
			}
			return false;		
	}
	

		public boolean isCollectionsAllPageDisplayed() {
			waitForElementVisible(driver, FrontEndCollectionsUI.PRODUCTS_HEADER);
			return isElementDisplayed(driver, FrontEndCollectionsUI.PRODUCTS_HEADER);
		}



}
