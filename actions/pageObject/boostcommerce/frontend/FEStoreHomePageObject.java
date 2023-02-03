package pageObject.boostcommerce.frontend;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.base.BasePageUI;
import pageUIs.fontEndStore.FrontEndHomePageUI;

public class FEStoreHomePageObject extends BasePage {
	WebDriver driver;
    private final Log log = LogFactory.getLog(getClass());

	public FEStoreHomePageObject(WebDriver driver) {
		this.driver = driver;
	}
	
    public String getTitleHomePage() {
        String title = "";
        try {
            sleepInSecond(3);
            title = getCurrentPageTitle(driver);
        } catch (Exception e) {
            log.debug("Cannot get title of Home page: " + e.getMessage());
        }
        return title;
    }

	public boolean isOutOfProductsSessionDisplayed(String outOfProduct) {
		waitForElementVisible(driver, BasePageUI.INSTANT_SEARCH_PRODUCT_LIST_ITEMS, outOfProduct);
		return isElementDisplayed(driver, BasePageUI.INSTANT_SEARCH_PRODUCT_LIST_ITEMS, outOfProduct);
	}

	public boolean isOutOfProductItemDisplayed() {
		waitForElementVisible(driver, BasePageUI.SUGGESTION_QUERIES_ITEM_OFSP);
		return isElementDisplayed(driver, BasePageUI.SUGGESTION_QUERIES_ITEM_OFSP);
	}
	
	public boolean isTitleOfProductDisplayed(String productTitle) {
		waitForElementVisible(driver, BasePageUI.TITLE_OF_PRODUCT_DYNAMIC, productTitle);
		return isElementDisplayed(driver, BasePageUI.TITLE_OF_PRODUCT_DYNAMIC, productTitle);
	}

	public boolean isProductVendorDisplayed(String productTitle, String productVender) {
		waitForElementVisible(driver, BasePageUI.TITLE_OF_PRODUCT_DYNAMIC, productTitle);
		waitForElementVisible(driver, BasePageUI.VENDOR_PRODUCT_DYNAMIC, productVender);
		return isElementDisplayed(driver, BasePageUI.TITLE_OF_PRODUCT_DYNAMIC, productTitle) && isElementDisplayed(driver, BasePageUI.VENDOR_PRODUCT_DYNAMIC, productVender) ;
	}

	public boolean isProductPriceDisplayed(String productTitle, String productPrice) {
		waitForElementVisible(driver, BasePageUI.PRODUCT_PRICE_FOLLOW_TITLE_DYNAMIC, productTitle, productPrice);
		return  isElementDisplayed(driver, BasePageUI.PRODUCT_PRICE_FOLLOW_TITLE_DYNAMIC, productTitle, productPrice) ;
	}

	public boolean isProductImageDisplayed(String productTitle, String productImage) {
		waitForElementVisible(driver, BasePageUI.TITLE_OF_PRODUCT_DYNAMIC, productTitle);
		waitForElementVisible(driver, BasePageUI.PRODUCT_IMAGE_ISW_DYNAMIC, productImage);
		return isElementDisplayed(driver, BasePageUI.TITLE_OF_PRODUCT_DYNAMIC, productTitle) && isElementDisplayed(driver, BasePageUI.PRODUCT_IMAGE_ISW_DYNAMIC, productImage) ;
	}

	public boolean isProductSalePriceDisplayed(String productTitle) {
		waitForElementVisible(driver, BasePageUI.PRODUCT_SALE_PRICE_ISW_DYNAMIC, productTitle);
		return  isElementDisplayed(driver, BasePageUI.PRODUCT_SALE_PRICE_ISW_DYNAMIC, productTitle);
	}

	public boolean isProductSKUDisplayed(String productTitle, String productSKU) {
		waitForElementVisible(driver, BasePageUI.PRODUCT_SKU_ISW_DYNAMIC, productTitle, productSKU);
		return  isElementDisplayed(driver, BasePageUI.PRODUCT_SKU_ISW_DYNAMIC, productTitle, productSKU);
	}
	
	

}
