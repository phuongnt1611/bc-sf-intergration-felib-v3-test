package pageObject.boostcommerce.frontend;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.fontEndStore.FrontEndHomePageUI;
import pageUIs.fontEndStore.FrontEndLoginPageUI;

public class FELoginPageObject extends BasePage {
	WebDriver driver;
    private final Log log = LogFactory.getLog(getClass());
    
    public FELoginPageObject(WebDriver driver) {
    	this.driver = driver;
    }
    
    public void inputToPasswordAndClickEnter(String passwordStore) {
        waitForElementVisible(driver, FrontEndLoginPageUI.PASSWORD_TEXT_BOX);
        sendKeyToElement(driver, FrontEndLoginPageUI.PASSWORD_TEXT_BOX, passwordStore);
        clickToElement(driver, FrontEndLoginPageUI.ENTER_PASSWORD_BUTTON);
    }
}
