package pageObjects.boostcommerce.dashboard;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageGeneratorManager;
import pageUIs.dashboard.DashboardPageUI;

public class DashboardPageObject extends BasePage {
	WebDriver driver;
    private final Log log = LogFactory.getLog(getClass());
    
    public DashboardPageObject(WebDriver driver) {
    	this.driver = driver;
    }
    
    public String getTitleDashboardPage() {
        String title = "";
        try {
            sleepInSecond(3);
            title = getCurrentPageTitle(driver);
        } catch (Exception e) {
            log.debug("|DashboardPO| - |getTitleDashboardPage| - Cannot get title of Dash Board page: " + e.getMessage());
        }
        return title;
    }
    
    public void clickLoginButton() {
        try {
            waitForElementClickable(driver, DashboardPageUI.LOGIN_BUTTON_AT_AUTH_PAGE);
            clickToElement(driver, DashboardPageUI.LOGIN_BUTTON_AT_AUTH_PAGE);
        } catch (Exception e) {
            log.debug("|DashboardPO| - |clickLoginButton| - Cannot click on Login button to direct Login Page: " + e.getMessage());
        }
    }
    
    public DashboardPageObject inputToEmailPasswordAndClickLoginSubmit(String email, String password) {
        try {
            waitForElementVisible(driver, DashboardPageUI.EMAIL_TEXT_BOX);
            sendKeyToElement(driver, DashboardPageUI.EMAIL_TEXT_BOX, email);
            waitForElementVisible(driver, DashboardPageUI.PASSWORD_TEXT_BOX);
            sendKeyToElement(driver, DashboardPageUI.PASSWORD_TEXT_BOX, password);
            waitForElementClickable(driver, DashboardPageUI.LOGIN_SUBMIT_BUTTON);
            clickToElement(driver, DashboardPageUI.LOGIN_SUBMIT_BUTTON);
        } catch (Exception e) {
            log.debug("|DashboardPO| - |inputToEmailPasswordAndClickLoginSubmit| - Login failed: " + e.getMessage());
        }
        return PageGeneratorManager.getDashboardPage(driver);
    }
    
    public Object getAuthorization(){
        sleepInSecond(5);
        return getAuthorAccessTokenByJS(driver);
    }

	public void openFrontEndStore(String feUrl) {
		 try {
				openPageUrl(driver, feUrl ); 
	        } catch (Exception e) {
	            log.debug("|DashboardPO| - |Navigate to front end store| - Cannot click on Login button to direct Login Page: " + e.getMessage());
	        }
	}
}
