package commons;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;

import pageUIs.base.BasePageUI;
import pageUIs.fontEndStore.FrontEndHomePageUI;


public class BasePage {
	protected WebDriverWait explicitWait;
	protected WebElement element;
	protected JavascriptExecutor jsExecutor;
	protected Actions action;
	protected Select select;
	private final Log log = LogFactory.getLog(getClass());

	/**
	 * To open your website after launching a browser
	 *
	 * @param driver
	 * @param url:   input url to open page
	 */
	protected void openPageUrl(WebDriver driver, String url) {
		try {
			driver.get(url);
		} catch (Exception e) {
			log.error("|BasePage| - |openPageUrl| - URL cannot opened: " + e.getMessage());
		}
	}

	/**
	 * To read the current URL from the browser’s address bar
	 *
	 * @param driver
	 */
	protected String getCurrentPageUrl(WebDriver driver) {
		String url = "";
		try {
			url = driver.getCurrentUrl();
		} catch (Exception e) {
			log.error("|BasePage| - |getCurrentPageUrl| - Cannot get current URL: " + e.getMessage());
		}
		return url;
	}

	/**
	 * To press the browser’s back button
	 *
	 * @param driver
	 */
	protected void backToPage(WebDriver driver) {
		try {
			driver.navigate().back();
		} catch (Exception e) {
			log.error("|BasePage| - |backToPage| - Cannot back to page: " + e.getMessage());
		}
	}

	/**
	 * To press the browser’s forward button
	 *
	 * @param driver
	 */
	protected void forwardToPage(WebDriver driver) {
		try {
			driver.navigate().forward();
		} catch (Exception e) {
			log.error("|BasePage| - |forwardToPage| - Cannot forward to page: " + e.getMessage());
		}
	}

	/**
	 * To refresh the current page
	 *
	 * @param driver
	 */
	 public void refreshCurrentPage(WebDriver driver) {
		try {
			driver.navigate().refresh();
		} catch (Exception e) {
			log.error("|BasePage| - |refreshCurrentPage| - Cannot refresh current page: " + e.getMessage());
		}
	}

	/**
	 * To read the current page title from the browser
	 *
	 * @param driver
	 */
	protected String getCurrentPageTitle(WebDriver driver) {
		String title = "";
		try {
			title = driver.getTitle();
		} catch (Exception e) {
			log.error("|BasePage| - |getCurrentPageTitle| - Cannot get current Title: " + e.getMessage());
		}
		return title;
	}

	/**
	 * To read the current page source from the browser
	 *
	 * @param driver
	 */
	protected String getCurrentPageSource(WebDriver driver) {
		String pageSource = "";
		try {
			pageSource = driver.getPageSource();
		} catch (Exception e) {
			log.error("|BasePage| - |getCurrentPageSource| - Cannot get current Page Source: " + e.getMessage());
		}
		return pageSource;
	}

	/**
	 * To get the window handle of the current window
	 *
	 * @param driver
	 * @return the ID of window at the current window
	 */
	protected String getWindowID(WebDriver driver) {
		String windowID = "";
		try {
			windowID = driver.getWindowHandle();
		} catch (Exception e) {
			log.error("|BasePage| - |getWindowID| - Cannot get window ID: " + e.getMessage());
		}
		return windowID;
	}

	/**
	 * To get list of window handles at the current window
	 *
	 * @param driver
	 * @return the set ID of windows
	 */
	protected Set<String> getListWindowsID(WebDriver driver) {
		Set<String> listWindowID = new HashSet<String>();
		try {
			listWindowID = driver.getWindowHandles();
		} catch (Exception e) {
			log.error("|BasePage| - |getListWindowsID| - Cannot get list window ID: " + e.getMessage());
		}
		return listWindowID;
	}

	/**
	 * To switch to child window (only 2 windows)
	 *
	 * @param driver
	 * @param parentID the id of window that will switch to
	 */
	protected void switchWindowByID(WebDriver driver, String parentID) {
		try {
			Set<String> IdWinDow = driver.getWindowHandles();
			for (String a : IdWinDow) {
				if (!a.equals(parentID)) {
					driver.switchTo().window(a);
				}
			}
		} catch (Exception e) {
			log.error("|BasePage| - |switchWindowByID| - Cannot switch window by ID: " + e.getMessage());
		}
	}

	/**
	 * To switch to the new window/tab by Title
	 *
	 * @param driver
	 * @param title: the title of window that will switch to
	 */
	protected void switchWindowByTitle(WebDriver driver, String title) {
		try {
			Set<String> windows = driver.getWindowHandles();
			for (String a : windows) {
				driver.switchTo().window(a);
				if (driver.getTitle().equals(title)) {
					break;
				}
			}
		} catch (Exception e) {
			log.error("|BasePage| - |switchWindowByTitle| - Cannot switch window by title: " + e.getMessage());
		}
	}

	/**
	 * To close all windows and switch back to the original window
	 *
	 * @param driver
	 * @param ID:    the ID of parent window
	 */
	protected void closeAllWindowsWithoutParentWindow(WebDriver driver, String ID) {
		try {
			Set<String> IdWindows = driver.getWindowHandles();
			for (String a : IdWindows) {
				if (!a.equals(ID)) {
					driver.switchTo().window(a);
					sleepInSecond(2);
					driver.close();
				}
			}
			driver.switchTo().window(ID);
		} catch (Exception e) {
			log.error(
					"|BasePage| - |closeAllWindowsWithoutParent| - Cannot close all window without parent window: "
							+ e.getMessage());
		}
	}

	/**
	 * To find the frame using your xpath expression and switch to it
	 *
	 * @param driver
	 * @param locator: the locator of frame that will switch to
	 */
	protected void switchToFrame(WebDriver driver, String locator) {
		WebElement element = null;
		try {
			element = getElement(driver, locator);
			driver.switchTo().frame(element);
		} catch (Exception e) {
			log.error("|BasePage| - |switchToFrame| - Cannot switch to frame: " + e.getMessage());
		}
	}

	/**
	 * To leave an iframe or frameset, switch back to the default content
	 *
	 * @param driver
	 */
	protected void switchToDefaultContent(WebDriver driver) {
		try {
			driver.switchTo().defaultContent();
		} catch (Exception e) {
			log.error(
					"|BasePage| - |switchToDefaultContent| - Cannot switch to default content: " + e.getMessage());
		}
	}

	/**
	 * To cast rest parameter
	 *
	 * @param locator: the xpath expression of an element
	 * @param values:  at least a value that will cast to locator
	 * @return full xpath expression with dynamic values
	 */
	protected String castToParameter(String locator, String... values) {
		try {
			locator = String.format(locator, (Object[]) values);
			return locator;
		} catch (Exception e) {
			log.error("|BasePage| - |castToParameter| - Cannot switch to default content: " + e.getMessage());
		}
		return locator;
	}

	/**
	 * To find an element by matching an XPath expression
	 *
	 * @param locator: the xpath expression of an element
	 * @return way to find an element by xpath
	 */
	protected By getByXpath(String locator) {
		return By.xpath(locator);
	}

	/**
	 * To find an element by matching an xpath/id/class/name/css/ expression
	 *
	 * @param locatorType: the xpath expression of an element
	 * @return way to find an element by xpath/id/class/name/css
	 */
	private By getByLocator(String locatorType) {
		By by = null;
		if (locatorType.startsWith("id=") || locatorType.startsWith("ID=") || locatorType.startsWith("Id=")) {
			by = By.id(locatorType.substring(3));
		} else if (locatorType.startsWith("class=") || locatorType.startsWith("CLASS=")
				|| locatorType.startsWith("Class=")) {
			by = By.className(locatorType.substring(6));
		} else if (locatorType.startsWith("name=") || locatorType.startsWith("NAME=")
				|| locatorType.startsWith("Name=")) {
			by = By.name(locatorType.substring(4));
		} else if (locatorType.startsWith("css=") || locatorType.startsWith("CSS=") || locatorType.startsWith("Css=")) {
			by = By.cssSelector(locatorType.substring(4));
		} else if (locatorType.startsWith("xpath=") || locatorType.startsWith("XPATH=")
				|| locatorType.startsWith("Xpath=")) {
			by = By.xpath(locatorType.substring(6));
		} else {
			throw new RuntimeException("Locator type is not supported");
		}
		return by;
	}

	/**
	 * To find an element by matching an dynamic XPath expression
	 *
	 * @param locatorType: the xpath expression of an element
	 * @param values
	 * @return way to find an element by dynamic xpath
	 */
	private String getDynamicXpath(String locatorType, String... values) {
		System.out.println("Locator type before = " + locatorType);
		if (locatorType.startsWith("xpath=") || locatorType.startsWith("XPATH=") || locatorType.startsWith("Xpath=")) {
			locatorType = String.format(locatorType, (Object[]) values);
		}
		System.out.println("Locator type after = " + locatorType);
		return locatorType;
	}

	public void switchToWindowByPageTitle(WebDriver driver, String expectedPageTitle) {
		Set<String> allWindowIDs = driver.getWindowHandles();

		for (String id : allWindowIDs) {
			driver.switchTo().window(id);
			String actualPageTitle = driver.getTitle();
			if (actualPageTitle.equals(expectedPageTitle)) {
				break;
			} else {

			}
		}

	}

	/**
	 * To find an element and returns a first matching on the page
	 *
	 * @param driver
	 * @param locator: the xpath expression of an element that will get
	 * @return element matching xpath locator
	 */
	protected WebElement getElement(WebDriver driver, String locator) {
		WebElement element = null;
		try {
			element = driver.findElement(getByXpath(locator));
		} catch (Exception e) {
			log.error("|BasePage| - |getElement| - Cannot get element: " + e.getMessage());
		}
		return element;
	}

	/**
	 * To find an element and returns a first matching with rest parameter in xpath
	 * locator
	 *
	 * @param driver
	 * @param locator: the xpath expression of an element that will get
	 * @param values:  at least a value that will cast to locator
	 * @return element matching dynamic xpath locator
	 */
	protected WebElement getElement(WebDriver driver, String locator, String... values) {
		WebElement element = null;
		try {
			element = driver.findElement(getByXpath(castToParameter(locator, values)));
		} catch (Exception e) {
			log.error("|BasePage| - |getElement| - Cannot get element: " + e.getMessage());
		}
		return element;
	}

	/**
	 * To find a list of web elements. If only one element is found, it will still
	 * return a collection (of one element). If no element matches the locator, an
	 * empty list will be returned.
	 *
	 * @param driver
	 * @param locator: the xpath expression of elements that will get
	 * @return list of elements matching xpath locator
	 */
	protected List<WebElement> getElements(WebDriver driver, String locator) {
		List<WebElement> listElements = new ArrayList<WebElement>();
		try {
			listElements = driver.findElements(getByXpath(locator));
		} catch (Exception e) {
			log.error("|BasePage| - |getElements| - Cannot get list elements: " + e.getMessage());
		}
		return listElements;
	}

	/**
	 * To find a collection of web elements with rest parameter in xpath locator. If
	 * only one element is found, it will still return a collection (of one
	 * element). If no element matches the locator, an empty list will be returned.
	 *
	 * @param driver
	 * @param locator: the xpath expression of elements that will get
	 * @param values:  at least a value that will cast to locator
	 * @return list of elements matching dynamic xpath locator
	 */
	protected List<WebElement> getElements(WebDriver driver, String locator, String... values) {
		List<WebElement> listElements = new ArrayList<WebElement>();
		try {
			listElements = driver.findElements(getByXpath(castToParameter(locator, values)));
		} catch (Exception e) {
			log.error("|BasePage| - |getElements| - Cannot get list elements: " + e.getMessage());
		}
		return listElements;
	}

	/**
	 * To return the size of element matching an xpath expression
	 *
	 * @param driver
	 * @param locator: the xpath expression of elements that will get
	 * @return the size of list elements matching xpath locator
	 */
	protected int getSizeElements(WebDriver driver, String locator) {
		int sizeElement = 0;
		try {
			sizeElement = getElements(driver, locator).size();
		} catch (Exception e) {
			log.error("|BasePage| - |getSizeElements| - Cannot get size elements: " + e.getMessage());
		}
		return sizeElement;
	}

	/**
	 * To return the size of elements matching an xpath expression with rest
	 * parameter
	 *
	 * @param driver
	 * @param locator: the xpath expression of elements that will get
	 * @param values:  at least a value that will cast to locator
	 * @return the size of list elements matching dynamic xpath locator
	 */
	protected int getSizeElements(WebDriver driver, String locator, String... values) {
		int sizeElement = 0;
		try {
			sizeElement = getElements(driver, locator, values).size();
		} catch (Exception e) {
			log.error("|BasePage| - |getSizeElements| - Cannot get size elements: " + e.getMessage());
		}
		return sizeElement;
	}

	/**
	 * To click an element matching an xpath expression
	 *
	 * @param driver
	 * @param locator: the xpath expression of an element that will be clicked on
	 */
	protected void clickToElement(WebDriver driver, String locator) {
		WebElement element = null;
		try {
			element = getElement(driver, locator);
			element.click();
			sleepInSecond(3);
		} catch (Exception e) {
			log.error("|BasePage| - |clickToElement| - Cannot click to element: " + e.getMessage());
		}
	}

	/**
	 * To click an element matching an xpath expression with rest parameter
	 *
	 * @param driver
	 * @param locator: the xpath expression of an element that will be clicked on
	 * @param values:  at least a value that will cast to locator
	 */
	protected void clickToElement(WebDriver driver, String locator, String... values) {
		WebElement element = null;
		try {
			element = getElement(driver, castToParameter(locator, values));
			element.click();
		} catch (Exception e) {
			log.error("|BasePage| - |clickToElement| - Cannot click to element: " + e.getMessage());
		}
	}

	/**
	 * To type a key sequence in DOM element
	 *
	 * @param driver
	 * @param locator:   the xpath expression of an element that will be sent keys
	 * @param textValue: the content that will be sent to text box
	 */
	protected void sendKeyToElement(WebDriver driver, String locator, String textValue) {
		WebElement element = null;
		try {
			element = getElement(driver, locator);
			clearTextBox(driver, locator);
			element.sendKeys(textValue);
		} catch (Exception e) {
			log.error("|BasePage| - |sendKeyToElement| - Cannot send key to element: " + e.getMessage());
		}
	}

	protected void clearField(WebDriver driver, String locator) {
		WebElement element = null;
		try {
			element = getElement(driver, locator);
			element.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.BACK_SPACE));
		} catch (Exception e) {
			log.error("|BasePage| - |sendKeyToElement| - Cannot send key to element: " + e.getMessage());
		}
	}
	
	protected void sendESCKeys(WebDriver driver) {
		WebElement element = null;
		try {
			element = getElement(driver, BasePageUI.BODY);
			element.sendKeys(Keys.ESCAPE);
		} catch (Exception e) {
			log.error("|BasePage| - |sendKeyToElement| - Cannot send key to element: " + e.getMessage());
		}
	}
	
	public void clearTextBoxByKeyboardBackSpace(WebDriver driver, String locator) {
		String inputText = getElementAttribute(driver, locator, "value");
		if (inputText != null) {
			for (int i = 0; i < inputText.length(); i++) {
				element.sendKeys(Keys.BACK_SPACE);
			}
		}

	}

	/**
	 * To type a key sequence in DOM element matching an xpath expression with rest
	 * parameter
	 *
	 * @param driver
	 * @param textValue: the content that will be sent to text box
	 * @param locator:   the xpath expression of a text box that will be sent keys
	 * @param values:    at least a value that will cast to locator
	 */
	protected void sendKeyToElement(WebDriver driver, String textValue, String locator, String... values) {
		WebElement element = null;
		try {
			element = getElement(driver, castToParameter(locator, values));
			element.clear();
			element.sendKeys(textValue);
		} catch (Exception e) {
			log.error("|BasePage| - |sendKeyToElement| - Cannot send key to element: " + e.getMessage());
		}
	}

	/**
	 * To click to a text box has type number
	 *
	 * @param driver
	 * @param locator: the xpath expression of a text box that will be clicked on
	 * @param value:   the number of times that will click on the text box
	 */
	protected void clickToTextBoxTypeNumber(WebDriver driver, String locator, int value) {
		WebElement element = null;
		try {
			element = getElement(driver, locator);
			for (int i = 0; i < value; i++) {
				clickToElement(driver, locator);
			}
		} catch (Exception e) {
			log.error("|BasePage| - |clickToTextBoxTypeNumber| - Cannot click to textbox by type number: "
					+ e.getMessage());
		}
	}

	/**
	 * To clear the content of an editable element with rest parameter in xpath
	 * locator
	 *
	 * @param driver
	 * @param locator: the xpath expression of a text box that will be cleared
	 * @param values:  at least a value that will cast to locator
	 */
	protected void clearTextBox(WebDriver driver, String locator, String... values) {
		WebElement element = null;
		try {
			element = getElement(driver, castToParameter(locator, values));
			element.clear();
		} catch (Exception e) {
			log.error("|BasePage| - |clearTextBox| - Cannot clear text box: " + e.getMessage());
		}
	}

	/**
	 * To clear the content of an editable element
	 *
	 * @param driver
	 * @param locator: the xpath expression of a text box that will be cleared
	 */
	protected void clearTextBox(WebDriver driver, String locator) {
		WebElement element = null;
		try {
			element = getElement(driver, locator);
			element.clear();
		} catch (Exception e) {
			log.error("|BasePage| - |clearTextBox| - Cannot clear text box: " + e.getMessage());
		}
	}

	/**
	 * To switch to the alert and press the OK button
	 *
	 * @param driver
	 */
	protected void acceptAlert(WebDriver driver) {
		try {
			driver.switchTo().alert().accept();
		} catch (Exception e) {
			log.error("|BasePage| - |acceptAlert| - Cannot accept Alert: " + e.getMessage());
		}
	}

	/**
	 * To switch to the alert and press the Cancel button
	 *
	 * @param driver
	 */
	protected void cancelAlert(WebDriver driver) {
		try {
			driver.switchTo().alert().dismiss();
		} catch (Exception e) {
			log.error("|BasePage| - |cancelAlert| - Cannot cancel Alert: " + e.getMessage());
		}
	}

	/**
	 * To switch to the alert and store the alert in a variable for reuse
	 *
	 * @param driver
	 * @return the text is stored at alert
	 */
	protected String getTextAlert(WebDriver driver) {
		String textAlert = "";
		try {
			textAlert = driver.switchTo().alert().getText();
		} catch (Exception e) {
			log.error("|BasePage| - |getTextAlert| - Cannot get text alert: " + e.getMessage());
		}
		return textAlert;
	}

	/**
	 * To switch to the alert and type a message to text box
	 *
	 * @param driver
	 * @param value  the content that will be sent to alert
	 */
	protected void setTextAlert(WebDriver driver, String value) {
		try {
			driver.switchTo().alert().sendKeys(value);
		} catch (Exception e) {
			log.error("|BasePage| - |setTextAlert| - Cannot set to text alert: " + e.getMessage());
		}
	}

	/**
	 * To select an option based upon its text
	 *
	 * @param driver
	 * @param textValue: the option's text that will be selected
	 * @param locator:   the xpath expression of a dropdown
	 */
	protected void selectItemByVisible(WebDriver driver, String textValue, String locator) {
		WebElement element = null;
		try {
			element = getElement(driver, locator);
			select = new Select(element);
			select.selectByVisibleText(textValue);
		} catch (Exception e) {
			log.error("|BasePage| - |selectItemByVisible| - Cannot select item by visible: " + e.getMessage());
		}
	}

	/**
	 * To select an option based upon its text with rest parameter in xpath locator
	 *
	 * @param driver
	 * @param textValue: the option's text that will be selected
	 * @param locator:   the xpath expression of a dropdown
	 * @param values:    at least a value that will cast to locator
	 */
	protected void selectItemByVisible(WebDriver driver, String textValue, String locator, String... values) {
		WebElement element = null;
		try {
			element = getElement(driver, locator, values);
			select = new Select(element);
			select.selectByVisibleText(textValue);
		} catch (Exception e) {
			log.error("|BasePage| - |selectItemByVisible| - Cannot select item by visible: " + e.getMessage());
		}
	}

	/**
	 * Return a text of webElement referencing the first selection option found
	 *
	 * @param driver
	 * @param locator: the xpath expression of a dropdown
	 * @return the option's text was selected
	 */
	protected String getFirstSelectedTextInDropdown(WebDriver driver, String locator) {
		WebElement element = null;
		String text = "";
		try {
			element = getElement(driver, locator);
			select = new Select(element);
			text = select.getFirstSelectedOption().getText();
		} catch (Exception e) {
			log.error("|BasePage| - |getFirstSelectedTextInDropdown| - Cannot get selected in dropdown: "
					+ e.getMessage());
		}
		return text;
	}

	/**
	 * Return a text of webElement referencing the first selection option with rest
	 * parameter in xpath locator
	 *
	 * @param driver
	 * @param locator: the xpath expression of a dropdown
	 * @param values:  at least a value that will cast to locator
	 * @return the option's text was selected
	 */
	protected String getFirstSelectedTextInDropdown(WebDriver driver, String locator, String... values) {
		String text = "";
		WebElement element = null;
		try {
			element = getElement(driver, castToParameter(locator, values));
			select = new Select(element);
			text = select.getFirstSelectedOption().getText();
		} catch (Exception e) {
			log.error("|BasePage| - |getFirstSelectedTextInDropdown| - Cannot get selected in dropdown: "
					+ e.getMessage());
		}
		return text;
	}

	/**
	 * To check elements allow you to select more than one option
	 *
	 * @param driver
	 * @param locator: the xpath expression of a dropdown
	 * @return the status of dropdown is multiple or single
	 */
	protected boolean isDropdownMultiple(WebDriver driver, String locator) {
		boolean checkMultiple = false;
		WebElement element = null;
		try {
			element = getElement(driver, locator);
			select = new Select(element);
			checkMultiple = select.isMultiple();
		} catch (Exception e) {
			log.error("|BasePage| - |isDropdownMultiple| - Dropdown multiple error: " + e.getMessage());
		}
		return checkMultiple;
	}

	/**
	 * To deselect an <option> based upon its text
	 *
	 * @param driver
	 * @param textValue: the option's text that will be deselected
	 * @param locator:   the xpath expression of a checkbox/dropdown
	 */
	protected void deSelectItemByVisible(WebDriver driver, String textValue, String locator) {
		WebElement element = null;
		try {
			element = getElement(driver, locator);
			select = new Select(element);
			select.deselectByVisibleText(textValue);
		} catch (Exception e) {
			log.error("|BasePage| - |deSelectItemByVisible| - Cannot deselect item by visible: " + e.getMessage());
		}
	}

	/**
	 * To deselect an <option> based upon its text with rest parameter in xpath
	 * locator
	 *
	 * @param driver
	 * @param textValue: the option's text that will be deselected
	 * @param locator:   the xpath expression of a checkbox/dropdown
	 * @param values:    at least a value that will cast to locator
	 */
	protected void deSelectItemByVisible(WebDriver driver, String textValue, String locator, String... values) {
		WebElement element = null;
		try {
			element = getElement(driver, castToParameter(locator, values));
			select = new Select(element);
			select.deselectByVisibleText(textValue);
		} catch (Exception e) {
			log.error("|BasePage| - |deSelectItemByVisible| - Cannot deselect item by visible: " + e.getMessage());
		}
	}

	/**
	 * To deselect all selected <option> elements
	 *
	 * @param driver
	 * @param locator: the xpath expression of a checkbox/dropdown
	 */
	protected void deSelectAllOptions(WebDriver driver, String locator) {
		WebElement element = null;
		try {
			element = getElement(driver, locator);
			select = new Select(element);
			select.deselectAll();
		} catch (Exception e) {
			log.error("|BasePage| - |deSelectAllOptions| - Cannot deselect all items: " + e.getMessage());
		}
	}

	/**
	 * To return the rendered attribute of the specified element
	 *
	 * @param driver
	 * @param locator:       the xpath expression of an element
	 * @param attributeName: the name of attribute that will be got
	 * @return the attribute value of an element
	 */
	protected String getElementAttribute(WebDriver driver, String locator, String attributeName) {
		WebElement element = null;
		String elementAttribute = "";
		try {
			element = getElement(driver, locator);
			elementAttribute = element.getAttribute(attributeName);
		} catch (Exception e) {
			log.error("|BasePage| - |getElementAttribute| - Cannot get element attribute: " + e.getMessage());
		}
		return elementAttribute;
	}

	/**
	 * To return the rendered attribute of the specified element with rest parameter
	 * in xpath locator
	 *
	 * @param driver
	 * @param locator:       the xpath expression of an element
	 * @param attributeName: the name of attribute that will be got
	 * @param values:        at least a value that will cast to locator
	 * @return the attribute value of an element matching a dynamic locator
	 */
	protected String getElementAttribute(WebDriver driver, String locator, String attributeName, String... values) {
		WebElement element = null;
		String elementAttribute = "";
		try {
			element = getElement(driver, castToParameter(locator, values));
			elementAttribute = element.getAttribute(attributeName);
		} catch (Exception e) {
			log.error("|BasePage| - |getElementAttribute| - Cannot get element attribute: " + e.getMessage());
		}
		return elementAttribute;
	}

	protected List<String> getElementAttributes(WebDriver driver, String locator, String attributeName) {
		List<WebElement> items = getElements(driver, locator);
		ArrayList<String> listItems = new ArrayList<String>();

		try {
			for (WebElement webElement : items) {
				listItems.add(webElement.getAttribute(attributeName).trim());
			}
		} catch (Exception e) {
			log.error("|BasePage| - |getElementAttribute| - Cannot get element attribute: " + e.getMessage());
		}
		return listItems;
	}

	/**
	 * To return the rendered text of the specified element
	 *
	 * @param driver
	 * @param locator: the xpath expression of an element
	 * @return the element's text matching locator
	 */
	protected String getElementText(WebDriver driver, String locator) {
		WebElement element = null;
		String elementText = "";
		try {
			element = getElement(driver, locator);
			elementText = element.getText().trim();
		} catch (Exception e) {
			log.error("|BasePage| - |getElementText| - Cannot get element text: " + e.getMessage());
		}
		return elementText;
	}

	/**
	 * To return the rendered text of the specified element with rest parameter in
	 * xpath locator
	 *
	 * @param driver
	 * @param locator: the xpath expression of an element
	 * @param values:  at least a value that will cast to locator
	 * @return the element's text matching dynamic locator
	 */
	protected String getElementText(WebDriver driver, String locator, String... values) {
		WebElement element = null;
		String elementText = "";
		try {
			element = getElement(driver, castToParameter(locator, values));
			elementText = element.getText();
		} catch (Exception e) {
			log.error("|BasePage| - |getElementText| - Cannot get element text: " + e.getMessage());
		}
		return elementText;
	}

	/**
	 * To return the rendered text of a list of elements
	 *
	 * @param driver
	 * @param locator: the xpath expression of an element
	 * @return the list text of elements matching locator
	 */
	protected List<String> getElementsText(WebDriver driver, String locator) {
		List<WebElement> items = getElements(driver, locator);
		ArrayList<String> listItems = new ArrayList<String>();
		try {
			for (WebElement webElement : items) {
				listItems.add(webElement.getText().trim());
			}
		} catch (Exception e) {
			log.error("|BasePage| - |getElementsText| - Cannot get elements text: " + e.getMessage());
		}
		return listItems;
	}

	/**
	 * To return the rendered text of a list of elements with rest parameter in
	 * xpath locator
	 *
	 * @param driver
	 * @param locator: the xpath expression of an element
	 * @param values:  at least a value that will cast to locator
	 * @return the list text of elements matching dynamic locator
	 */
	protected List<String> getElementsText(WebDriver driver, String locator, String... values) {
		List<WebElement> items = getElements(driver, locator, values);
		ArrayList<String> listItems = new ArrayList<>();
		try {
			for (WebElement webElement : items) {
				listItems.add(webElement.getText().trim());
			}
		} catch (Exception e) {
			log.error("|BasePage| - |getElementsText| - Cannot get element text: " + e.getMessage());
		}
		return listItems;

	}

	/**
	 * To return the size of elements found
	 *
	 * @param driver
	 * @param locator: the xpath expression of an element
	 * @return the size of list elements matching locator
	 */
	protected int countElementSize(WebDriver driver, String locator) {
		int elementSize = 0;
		try {
			elementSize = getElements(driver, locator).size();
		} catch (Exception e) {
			log.error("|BasePage| - |countElementSize| - Cannot get elements size: " + e.getMessage());
		}
		return elementSize;
	}

	/**
	 * To return the size of elements found with rest parameter in xpath locator
	 *
	 * @param driver
	 * @param locator: the xpath expression of an element
	 * @param values:  at least a value that will cast to locator
	 * @return the size of list elements matching dynamic locator
	 */
	protected int countElementSize(WebDriver driver, String locator, String... values) {
		int elementSize = 0;
		try {
			elementSize = getElements(driver, castToParameter(locator, values)).size();
		} catch (Exception e) {
			log.error("|BasePage| - |countElementSize| - Cannot get elements size: " + e.getMessage());
		}
		return elementSize;
	}

	/**
	 * To click on the checkbox of the specific element
	 *
	 * @param driver
	 * @param locator: the xpath expression of a check box
	 * @param values:  at least a value that will cast to locator
	 */
	protected void checkToCheckBox(WebDriver driver, String locator, String... values) {
		WebElement element = null;
		try {
			element = getElement(driver, castToParameter(locator, values));
			if (!element.isSelected()) {
				element.click();
			}
		} catch (Exception e) {
			log.error("|BasePage| - |checkToCheckBox| - Cannot check to checkbox: " + e.getMessage());
		}
	}

	/**
	 * To click on the checkbox of the specific element with rest parameter in xpath
	 * locator
	 *
	 * @param driver
	 * @param locator: the xpath expression of a check box
	 */
	protected void checkToCheckBox(WebDriver driver, String locator) {
		WebElement element = null;
		try {
			element = getElement(driver, locator);
			if (!element.isSelected()) {
				element.click();
			}
		} catch (Exception e) {
			log.error("|BasePage| - |checkToCheckBox| - Cannot check to checkbox: " + e.getMessage());
		}
	}

	/**
	 * To uncheck the checkbox of specific element
	 *
	 * @param driver
	 * @param locator: the xpath expression of a check box
	 */
	protected void unCheckToCheckBox(WebDriver driver, String locator) {
		WebElement element = null;
		try {
			element = getElement(driver, locator);
			if (element.isSelected()) {
				element.click();
			}
		} catch (Exception e) {
			log.error("|BasePage| - |unCheckToCheckBox| - Cannot uncheck to checkbox: " + e.getMessage());
		}
	}

	protected void unCheckToCheckBox(WebDriver driver, String locator, String... values) {
		WebElement element = null;
		try {
			element = getElement(driver, castToParameter(locator, values));
			if (element.isSelected()) {
				element.click();
			}
		} catch (Exception e) {
			log.error("|BasePage| - |unCheckToCheckBox| - Cannot uncheck to checkbox: " + e.getMessage());
		}
	}

	/**
	 * To check if the element is displayed or undisplayed.
	 *
	 * @param driver
	 * @param locator: the xpath expression of an element
	 * @return the boolean value, true if the element is undisplayed on a web page,
	 *         else returns false.
	 */
	protected boolean isElementUnDisplayed(WebDriver driver, String locator) {
		boolean status = true;
		try {
			overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
			List<WebElement> elements = getElements(driver, locator);
			overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
			if (elements.size() == 0) {
				return status;
			} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
				return status;
			} else {
				status = false;
				return status;
			}
		} catch (Exception e) {
			status = false;
			log.error("|BasePage| - |isElementUnDisplayed| - Element is undisplayed error : " + e.getMessage());
		}
		return status;
	}

	/**
	 * To check if the element is displayed or undisplayed with rest parameter in
	 * xpath locator
	 *
	 * @param driver
	 * @param locator: the xpath expression of an element
	 * @param values:  at least a value that will cast to locator
	 * @return the boolean value, true if the element is undisplayed on a web page,
	 *         else returns false.
	 */
	protected boolean isElementUnDisplayed(WebDriver driver, String locator, String... values) {
		boolean status = true;
		try {
			overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
			List<WebElement> elements = getElements(driver, castToParameter(locator, values));
			overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
			if (elements.size() == 0) {
				return status;
			} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
				return status;
			} else {
				status = false;
				return status;
			}
		} catch (Exception e) {
			status = false;
			log.error("|BasePage| - |isElementUnDisplayed| - Element is undisplayed error : " + e.getMessage());
		}
		return status;
	}

	/**
	 * To check if the element is displayed or undisplayed.
	 *
	 * @param driver
	 * @param locator: the xpath expression of an element
	 * @return the boolean value, true if the element is displayed on a web page,
	 *         else returns false.
	 */
	protected boolean isElementDisplayed(WebDriver driver, String locator) {
		boolean check= true;
		try {
			return getElement(driver, locator).isDisplayed();
		} catch (Exception e) {
			check = false;
			log.error("|BasePage| - |isElementDisplayed| - Element is displayed error: " + e.getMessage());
		}
		return check;
	}

	/**
	 * To check if the element is displayed or undisplayed with rest parameter in
	 * xpath locator. Returns a boolean value, true if the element is displayed on a
	 * web page, else returns false.
	 *
	 * @param driver
	 * @param locator: the xpath expression of an element
	 * @param values:  at least a value that will cast to locator
	 * @return the boolean value, true if the element is displayed on a web page,
	 *         else returns false.
	 */
	protected boolean isElementDisplayed(WebDriver driver, String locator, String... values) {
		boolean check;
		try {
			return getElement(driver, locator, values).isDisplayed();
		} catch (Exception e) {
			check = false;
			log.error("|BasePage| - |isElementDisplayed| - Element is displayed error: " + e.getMessage());
		}
		return check;
	}

	protected boolean areElementsDisplayed(WebDriver driver, String locator, String... values) {
		boolean check = false;
		int count = 0;
		try {
			List<WebElement> elements = getElements(driver, locator, values);
			for (WebElement e : elements) {
				if (e.isDisplayed()) {
					count++;
				}
			}
			if (count == elements.size()) {
				check = true;
			}
		} catch (Exception e) {
			check = false;
			log.error("|BasePage| - |isElementDisplayed| - Element is displayed error: " + e.getMessage());
		}
		return check;
	}

	/**
	 * to check if the element is enabled or disabled.
	 *
	 * @param driver
	 * @param locator: the xpath expression of an element
	 * @return the boolean value, True if the element is enabled in the current
	 *         browsing context, else returns false.
	 */
	protected boolean isElementEnabled(WebDriver driver, String locator) {
		boolean check;
		try {
			return getElement(driver, locator).isEnabled();
		} catch (Exception e) {
			check = false;
			log.error("|BasePage| - |isElementEnabled| - Element is enabled error: " + e.getMessage());
		}
		return check;
	}

	/**
	 * To determines if the referenced Element is selected or not.
	 *
	 * @param driver
	 * @param locator: the xpath expression of an element
	 * @return the boolean value, True if referenced element is selected in the
	 *         current browsing context, else returns false.
	 */
	protected boolean isElementSelected(WebDriver driver, String locator) {
		boolean check = true;
		try {
			if (getElement(driver, locator).isSelected()) {
				return check;
			}
			;
		} catch (Exception e) {
			check = false;
			log.error("|BasePage| - |isElementSelected| - Element is selected error: " + e.getMessage());
		}
		return check;
	}

	/**
	 * To determines if the referenced Element is selected or not with rest
	 * parameter in xpath locator.
	 *
	 * @param driver
	 * @param locator: the xpath expression of an element
	 * @param values:  at least a value that will cast to locator
	 * @return the boolean value, True if referenced element is selected in the
	 *         current browsing context, else returns false.
	 */
	protected boolean isElementSelected(WebDriver driver, String locator, String... values) {
		boolean check = true;
		try {
			if (getElement(driver, castToParameter(locator, values)).isSelected()) {
				return check;
			}
		} catch (Exception e) {
			check = false;
			log.error("|BasePage| - |isElementSelected| - Element is selected error: " + e.getMessage());
		}
		return check;
	}

	/**
	 * To perform double-click action on the element
	 *
	 * @param driver
	 * @param locator: the xpath expression of an element
	 */
	protected void doubleClickToElement(WebDriver driver, String locator) {
		try {
			action = new Actions(driver);
			action.doubleClick(getElement(driver, locator)).perform();
		} catch (Exception e) {
			log.error("|BasePage| - |doubleClickToElement| - Cannot double click to element: " + e.getMessage());
		}
	}

	/**
	 * To perform context-click action on the element
	 *
	 * @param driver
	 * @param locator: the xpath expression of an element
	 */
	protected void rightClickToElement(WebDriver driver, String locator) {
		try {
			action = new Actions(driver);
			action.contextClick(getElement(driver, locator)).perform();
		} catch (Exception e) {
			log.error("|BasePage| - |rightClickToElement| - Cannot right click to element: " + e.getMessage());
		}
	}

	/**
	 * To moves the mouse to the middle of the element
	 *
	 * @param driver
	 * @param locator: the xpath expression of an element
	 */
	protected void hoverMouseToElement(WebDriver driver, String locator) {
		try {
			action = new Actions(driver);
			action.moveToElement(getElement(driver, locator)).perform();
		} catch (Exception e) {
			log.error("|BasePage| - |hoverMouseToElement| - Cannot hover mouse to element: " + e.getMessage());
		}
	}

	/**
	 * To moves the mouse to the middle of the element with rest parameter in xpath
	 * locator
	 *
	 * @param driver
	 * @param locator: the xpath expression of an element
	 * @param values:  at least a value that will cast to locator
	 */
	protected void hoverMouseToElement(WebDriver driver, String locator, String... values) {
		try {
			action = new Actions(driver);
			action.moveToElement(getElement(driver, castToParameter(locator, values))).perform();
		} catch (Exception e) {
			log.error("|BasePage| - |hoverMouseToElement| - Cannot hover mouse to element: " + e.getMessage());
		}
	}

	/**
	 * To perform move to the element and clicks (without releasing) in the middle
	 * of the given element
	 *
	 * @param driver
	 * @param locator: the xpath expression of an element
	 */
	protected void clickAndHoldToElement(WebDriver driver, String locator) {
		try {
			action = new Actions(driver);
			action.clickAndHold(getElement(driver, locator)).perform();
		} catch (Exception e) {
			log.error("|BasePage| - |clickAndHoldToElement| - Cannot click and hold to element: " + e.getMessage());
		}
	}

	/**
	 * This method firstly performs a click-and-hold on the source element, moves to
	 * the location of the target element and then releases the mouse.
	 *
	 * @param driver
	 * @param sourceLocator: the xpath of source locator of an element
	 * @param targetLocator: the xpath of target locator of an element
	 */
	protected void dragAnDropElement(WebDriver driver, String sourceLocator, String targetLocator) {
		try {
			action = new Actions(driver);
			action.dragAndDrop(getElement(driver, sourceLocator), getElement(driver, targetLocator)).perform();
		} catch (Exception e) {
			log.error("|BasePage| - |dragAnDropElement| - Cannot drag and drop element: " + e.getMessage());
		}
	}

	/**
	 * To perform keyboard action
	 *
	 * @param driver
	 * @param locator: the xpath expression of an element
	 * @param key:     keyboard that will be pressed
	 */
	protected void sendKeyBoardToElement(WebDriver driver, String locator, Keys key) {
		try {
			action = new Actions(driver);
			action.sendKeys(getElement(driver, locator), key).build().perform();
		} catch (Exception e) {
			log.error("|BasePage| - |sendKeyBoardToElement| - Cannot send key board to element: " + e.getMessage());
		}
	}

	protected void keyDownToElement(WebDriver driver, Keys key) {
		try {
			action = new Actions(driver);
			action.keyDown(key).build().perform();
		} catch (Exception e) {
			log.error("|BasePage| - |sendKeyDownControlToElement| - Cannot send key down control to element: "
					+ e.getMessage());
		}
	}

	protected void keyUpToElement(WebDriver driver, Keys key) {
		try {
			action = new Actions(driver);
			action.keyUp(key).build().perform();
		} catch (Exception e) {
			log.error("|BasePage| - |sendKeyDownControlToElement| - Cannot send key up control to element: "
					+ e.getMessage());
		}
	}

	/**
	 * To send key to element by executing Java script
	 *
	 * @param driver
	 * @param locator: the xpath expression of an element
	 * @param text:    the content that will be sent to text box
	 */
	protected void sendKeyToElementByJS(WebDriver driver, String locator, String text) {
		try {
			jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("arguments[0].setAttribute('value','" + text + "')", getElement(driver, locator));
		} catch (Exception e) {
			log.error("|BasePage| - |sendKeyToElementByJS| - Cannot send key to element by JS: " + e.getMessage());
		}
	}

	/**
	 * To send key to element with rest parameter in xpath locator by executing
	 * JavaScript
	 *
	 * @param driver
	 * @param text:    the content that will be sent to element
	 * @param locator: the xpath expression of an element
	 * @param values:  at least a value that will cast to locator
	 */
	protected void sendKeyToElementByJS(WebDriver driver, String text, String locator, String... values) {
		try {
			jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("arguments[0].setAttribute('value','" + text + "')",
					getElement(driver, castToParameter(locator, values)));
		} catch (Exception e) {
			log.error("|BasePage| - |sendKeyToElementByJS| - Cannot send key to element by JS: " + e.getMessage());
		}
	}

	/**
	 * To click on element by executing JavaScript
	 *
	 * @param driver
	 * @param locator: the xpath expression of an element
	 */
	protected void clickElementByJS(WebDriver driver, String locator) {
		try {
			jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("arguments[0].click()", getElement(driver, locator));
		} catch (java.lang.Exception e) {
			log.error("|BasePage| - |clickElementByJS| - Cannot click on element by JS: " + e.getMessage());
		}
	}

	/**
	 * To open new tab and switch to it
	 *
	 * @param driver
	 */
	protected void openNewTabByJS(WebDriver driver) {
		try {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("window.open()");
			ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tabs.get(1));
		} catch (Exception e) {
			log.error("|BasePage| - |openNewTabByJS| - Cannot open new tab by JS: " + e.getMessage());
		}
	}

	/**
	 * To navigate to url by executing JavaScript
	 *
	 * @param driver
	 * @param url:   The address that will navigate to
	 */
	protected void navigateToUrlByJS(WebDriver driver, String url) {
		try {
			jsExecutor = (JavascriptExecutor) driver;
			jsExecutor.executeScript("window.location='" + url + "'");
		} catch (Exception e) {
			log.error("|BasePage| - |navigateToUrlByJS| - Cannot navigate to url by JS: " + e.getMessage());
		}
	}

	/**
	 * To get return domain from script
	 *
	 * @param driver
	 * @return the domain at the current page
	 */
	protected Object getDomainByJS(WebDriver driver) {
		try {
			jsExecutor = (JavascriptExecutor) driver;
		} catch (Exception e) {
			log.error("|BasePage| - |getDomainByJS| - Cannot get domain at the current page by JS: "
					+ e.getMessage());
		}
		return jsExecutor.executeScript("return document.domain");
	}

	/**
	 * To get return title from script
	 *
	 * @param driver
	 * @return the title at the current page
	 */
	protected Object getTitleByJS(WebDriver driver) {
		try {
			jsExecutor = (JavascriptExecutor) driver;
		} catch (Exception e) {
			log.error(
					"|BasePage| - |getTitleByJS| - Cannot get title at the current page by JS: " + e.getMessage());
		}
		return jsExecutor.executeScript("return document.title");
	}

	/**
	 * To get return url from script
	 *
	 * @param driver
	 * @return the url address at the current page
	 */
	protected Object getUrlByJS(WebDriver driver) {
		try {
			jsExecutor = (JavascriptExecutor) driver;
		} catch (Exception e) {
			log.error("|BasePage| - |getUrlByJS| - Cannot get url by JS: " + e.getMessage());
		}
		return jsExecutor.executeScript("return document.URL");
	}

	protected Object getAuthorAccessTokenByJS(WebDriver driver) {
		Object token = "";
		try {
			jsExecutor = (JavascriptExecutor) driver;
			token = jsExecutor.executeScript("return window.localStorage.getItem('auth0AccessToken')");
		} catch (Exception e) {
			log.error("|BasePage| - |getAuthorAccessToken| - Cannot get token by JS: " + e.getMessage());
		}
		return token;
	}

	/**
	 * To get return text from script
	 *
	 * @param driver
	 * @return the text content of the specified node, and all its descendants
	 */
	protected Object getInnerTextByJS(WebDriver driver) {
		try {
			jsExecutor = (JavascriptExecutor) driver;
		} catch (Exception e) {
			log.error("|BasePage| - |getInnerTextByJS| - Cannot get inner text by JS: " + e.getMessage());
		}
		return jsExecutor.executeScript("return document.documentElement.innerText");
	}

	/**
	 * To scroll to element with rest parameter in xpath locator by executing
	 * JavaScript
	 *
	 * @param driver
	 * @param locator: the xpath expression of an element
	 * @param values:  at least a value that will cast to locator
	 * @return
	 */
	protected Object scrollToElementByJS(WebDriver driver, String locator, String... values) {
		try {
			jsExecutor = (JavascriptExecutor) driver;
		} catch (Exception e) {
			log.error("|BasePage| - |scrollToElementByJS| - Cannot scroll to element by JS: " + e.getMessage());
		}
		return jsExecutor.executeScript("arguments[0].scrollIntoView(true)",
				getElement(driver, castToParameter(locator, values)));
	}

	/**
	 * To scroll to element by executing JavaScript
	 *
	 * @param driver
	 * @param locator: the xpath expression of an element
	 * @return
	 */
	protected Object scrollToElementByJS(WebDriver driver, String locator) {
		jsExecutor = (JavascriptExecutor) driver;
		return jsExecutor.executeScript("arguments[0].scrollIntoView(true)", getElement(driver, locator));
	}

	protected Object scrollVerticallyByJS(WebDriver driver) {
		jsExecutor = (JavascriptExecutor) driver;
		return jsExecutor.executeScript("window.scrollBy(0,-400)");
	}

	/**
	 * To scroll to the bottom page by executing JavaScript
	 *
	 * @param driver
	 * @return
	 */
	protected Object scrollToBottomPageByJS(WebDriver driver) {
		try {
			jsExecutor = (JavascriptExecutor) driver;
		} catch (Exception e) {
			log.error("|BasePage| - |scrollToBottomPageByJS| - Cannot scroll to bottom of the page by JS: "
					+ e.getMessage());
		}
		return jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	/**
	 * To scroll to the top page by executing JavaScript
	 *
	 * @param driver
	 * @return
	 */
	protected Object scrollToTopPageByJS(WebDriver driver) {
		try {
			jsExecutor = (JavascriptExecutor) driver;
		} catch (Exception e) {
			log.error("|BasePage| - |scrollToTopPageByJS| - Cannot scroll to top of the page by JS: "
					+ e.getMessage());
		}
		return jsExecutor.executeScript("window.scrollTo(0, 0)");

	}

	/**
	 * To remove the specific attribute of element by executing JavaScript
	 *
	 * @param driver
	 * @param locator:   the xpath expression of an element
	 * @param attribute: the name of attribute that will be removed
	 * @return
	 */
	protected Object removeAttributeByJS(WebDriver driver, String locator, String attribute) {
		try {
			jsExecutor = (JavascriptExecutor) driver;
		} catch (Exception e) {
			log.error("|BasePage| - |scrollToTopPageByJS| - Cannot remove attribute by JS: " + e.getMessage());
		}
		return jsExecutor.executeScript("arguments[0].removeAttribute('" + attribute + "')",
				getElement(driver, locator));

	}

	/**
	 * To highlight an element by executing JavaScript
	 *
	 * @param driver
	 * @param locator: the xpath expression of an element
	 */
	protected void highlightElement(WebDriver driver, String locator) {
		try {
			element = getElement(driver, locator);
			String originalStyle = element.getAttribute("style");
			jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
					"border: 2px solid red; border-style: dashed;");
			sleepInSecond(1);
			jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
					originalStyle);
		} catch (Exception e) {
			log.error("|BasePage| - |highlightElement| - Cannot highlight an element by JS: " + e.getMessage());
		}
	}

	/**
	 * To check image is loaded or not by executing JavaScript
	 *
	 * @param driver
	 * @param locator: the xpath expression of an element
	 * @return a boolean value, true if image is load, else false
	 */
	protected boolean isImageLoaded(WebDriver driver, String locator) {
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth !=\"underfined\"&& arguments[0].naturalWidth > 0",
				getElement(driver, locator));
		try {
			if (status == true) {
				return status;
			}
		} catch (Exception e) {
			log.error("|BasePage| - |isImageLoaded| - Image cannot be loaded error: " + e.getMessage());
		}
		return status;
	}

	/**
	 * To sleep without any conditions
	 *
	 * @param time
	 */
	protected void sleepInSecond(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			log.error("|BasePage| - |sleepInSecond| - Sleep in second error: " + e.getMessage());
		}
	}

	/**
	 * To wait till all elements present on the web page that match the locator are
	 * visible.
	 *
	 * @param driver
	 * @param locator: the xpath expression of all elements
	 */
	protected void waitForAllElementsVisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		try {
			explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(locator)));
		} catch (Exception e) {
			log.error("|BasePage| - |waitForAllElementsVisible| - Error wait for all elements are visible : "
					+ e.getMessage());
		}
	}

	/**
	 * To wait till all elements present and visible on the web page that match the
	 * dynamic locator.
	 *
	 * @param driver
	 * @param locator: the xpath expression of all elements
	 * @param values:  at least a value that will cast to locator
	 */
	protected void waitForAllElementsVisible(WebDriver driver, String locator, String... values) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		try {
			explicitWait.until(
					ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(castToParameter(locator, values))));
		} catch (Exception e) {
			log.error("|BasePage| - |waitForAllElementsVisible| - Error wait for all elements are visible : "
					+ e.getMessage());
		}
	}

	/**
	 * To wait till an element is present on the DOM of a page and visible
	 *
	 * @param driver
	 * @param locator:the xpath expression of an element
	 */
	protected void waitForElementVisible(WebDriver driver, String locator) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(locator)));
		} catch (Exception e) {
			log.error("|BasePage| - |waitForElementVisible| - Error wait for an element is visible : "
					+ e.getMessage());
		}
	}

	/**
	 * To wait till an element is present on the DOM of a page and visible with rest
	 * parameter in xpath locator
	 *
	 * @param driver
	 * @param locator: the xpath expression of an element
	 * @param values:  at least a value that will cast to locator
	 */
	protected void waitForElementVisible(WebDriver driver, String locator, String... values) {
		explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
		try {
			explicitWait
					.until(ExpectedConditions.visibilityOfElementLocated(getByXpath(castToParameter(locator, values))));
		} catch (Exception e) {
			log.error("|BasePage| - |waitForElementVisible| - Error wait for an element is visible : "
					+ e.getMessage());
		}
	}

	/**
	 * To wait till an element is visible and enabled such that you can click it.
	 *
	 * @param driver
	 * @param locator: the xpath expression of an element
	 */
	protected void waitForElementClickable(WebDriver driver, String locator) {
		try {
			explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
			explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(locator)));
		} catch (Exception e) {
			log.error("|BasePage| - |waitForElementClickable| - Error wait for an element is clickable : "
					+ e.getMessage());
		}
	}

	/**
	 * To wait till an element is visible and enabled such that you can click it
	 * with rest parameter in xpath locator
	 *
	 * @param driver
	 * @param locator: the xpath expression of an element
	 * @param values:  at least a value that will cast to locator
	 */
	protected void waitForElementClickable(WebDriver driver, String locator, String... values) {
		try {
			explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
			explicitWait.until(ExpectedConditions.elementToBeClickable(getByXpath(castToParameter(locator, values))));
		} catch (Exception e) {
			log.error("|BasePage| - |waitForElementClickable| - Error wait for an element is clickable : "
					+ e.getMessage());
		}
	}

	/**
	 * To wait till an element is either invisible or not present on the DOM.
	 *
	 * @param driver
	 * @param locator: the xpath expression of an element
	 */
	protected void waitForElementInvisible(WebDriver driver, String locator) {
		try {
			explicitWait = new WebDriverWait(driver, GlobalConstants.SHORT_TIMEOUT);
			overrideGlobalTimeout(driver, GlobalConstants.SHORT_TIMEOUT);
			explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(locator)));
			overrideGlobalTimeout(driver, GlobalConstants.LONG_TIMEOUT);
		} catch (Exception e) {
			log.error("|BasePage| - |waitForElementInvisible| - Error wait for an element is invisible : "
					+ e.getMessage());
		}
	}

	/**
	 * To wait till an alert is presence on a web page
	 *
	 * @param driver
	 */
	protected void waitAlertPresence(WebDriver driver) {
		try {
			explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
			explicitWait.until(ExpectedConditions.alertIsPresent());
		} catch (Exception e) {
			log.error("|BasePage| - |waitAlertPresence| - Error wait for an alert is presence : " + e.getMessage());
		}
	}

	/**
	 * To wait till an element is present on the DOM of a page.
	 *
	 * @param driver
	 * @param locator: the xpath expression of an element
	 */
	protected void waitElementPresence(WebDriver driver, String locator) {
		try {
			explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
			explicitWait.until(ExpectedConditions.presenceOfElementLocated(getByXpath(locator)));
		} catch (Exception e) {
			log.error("|BasePage| - |waitElementPresence| - Error wait for an element is presence : "
					+ e.getMessage());
		}
	}

	/**
	 * To wait till there is at least one element present on a web page.
	 *
	 * @param driver
	 * @param locator: the xpath expression of all elements
	 */
	protected void waitAllElementsPresence(WebDriver driver, String locator) {
		try {
			explicitWait = new WebDriverWait(driver, GlobalConstants.LONG_TIMEOUT);
			explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(locator)));
		} catch (Exception e) {
			log.error("|BasePage| - |waitAllElementsPresence| - Error wait for all elements are presence : "
					+ e.getMessage());
		}
	}

	/**
	 * To override global time out. The implicit wait is set for the life of the
	 * session since override
	 *
	 * @param driver
	 * @param timeOut
	 */
	protected void overrideGlobalTimeout(WebDriver driver, long timeOut) {
		driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
	}

	/**
	 * To check data type is string sorted ascending
	 *
	 * @param driver
	 * @param locator: the xpath expression of all elements which will be sorted
	 * @return a boolean value, true if data is sorted ascending, else false
	 */
	protected boolean isDataStringSortedAscending(WebDriver driver, String locator) {
		ArrayList<String> arrayList = new ArrayList<String>();
		List<WebElement> elementList = driver.findElements(By.xpath(locator));
		for (WebElement element : elementList) {
			arrayList.add(element.getText().trim());
		}
		ArrayList<String> sortedList = new ArrayList<>();
		for (String child : arrayList) {
			sortedList.add(child);
		}
		Collections.sort(sortedList);
		return sortedList.equals(arrayList);

	}

	protected boolean isDataStringSortedAscending(WebDriver driver, String locator, String... values) {
		ArrayList<String> arrayList = new ArrayList<String>();
		List<WebElement> elementList = driver.findElements(By.xpath(castToParameter(locator, values)));
		for (WebElement element : elementList) {
			arrayList.add(element.getText());
		}
		ArrayList<String> sortedList = new ArrayList<>();
		for (String child : arrayList) {
			sortedList.add(child);
		}
		Collections.sort(sortedList);

		return sortedList.equals(arrayList);

	}

	/**
	 * To check data type is string sorted descending
	 *
	 * @param driver
	 * @param locator: the xpath expression of all elements which will be sorted
	 * @return a boolean value, true if data is sorted descending, else false
	 */
	protected boolean isDataStringSortedDescending(WebDriver driver, String locator) {
		ArrayList<String> arrayList = new ArrayList<String>();
		List<WebElement> elementList = driver.findElements(By.xpath(locator));
		for (WebElement element : elementList) {
			arrayList.add(element.getText().trim());
		}
		ArrayList<String> sortedList = new ArrayList<>();
		for (String child : arrayList) {
			sortedList.add(child);
		}
		Collections.sort(sortedList);
		Collections.reverse(sortedList);
		return sortedList.equals(arrayList);
	}

	protected boolean isDataStringSortedDescending(WebDriver driver, String locator, String... values) {
		ArrayList<String> arrayList = new ArrayList<String>();
		List<WebElement> elementList = driver.findElements(By.xpath(castToParameter(locator, values)));
		for (WebElement element : elementList) {
			arrayList.add(element.getText());
		}
		ArrayList<String> sortedList = new ArrayList<>();
		for (String child : arrayList) {
			sortedList.add(child);
		}
		Collections.sort(sortedList);
		Collections.reverse(sortedList);
		return sortedList.equals(arrayList);
	}

	/**
	 * To check data type is float sorted ascending
	 *
	 * @param driver
	 * @param locator: the xpath expression of all elements which will be sorted
	 * @return a boolean value, true if data is sorted ascending, else false
	 */
	protected boolean isDataFloatSortedAscending(WebDriver driver, String locator) {
		ArrayList<Float> arrayList = new ArrayList<Float>();
		List<WebElement> elementList = driver.findElements(By.xpath(locator));
		for (WebElement element : elementList) {
			arrayList.add(Float.parseFloat(element.getText().replace("$", "").replace(",", "").trim()));
		}
		ArrayList<Float> sortedList = new ArrayList<Float>();
		for (Float child : arrayList) {
			sortedList.add(child);
		}
		Collections.sort(sortedList);

		return sortedList.equals(arrayList);

	}

	/**
	 * To check data type is float sorted descending
	 *
	 * @param driver
	 * @param locator: the xpath expression of all elements which will be sorted
	 * @return a boolean value, true if data is sorted descending, else false
	 */
	protected boolean isDataFloatSortedDescending(WebDriver driver, String locator) {
		boolean status = true;
		try {
			ArrayList<Float> arrayList = new ArrayList<Float>();
			List<WebElement> elementList = driver.findElements(By.xpath(locator));
			for (WebElement element : elementList) {
				arrayList.add(Float.parseFloat(element.getText().replace("$", "").replace(",", "").trim()));
			}
			ArrayList<Float> sortedList = new ArrayList<Float>();
			for (Float child : arrayList) {
				sortedList.add(child);
			}
			Collections.sort(sortedList);
			Collections.reverse(sortedList);
			if (sortedList.equals(arrayList)) {
				return status;
			}
		} catch (NumberFormatException e) {
			status = false;
			log.error("|BasePage| - |isDataFloatSortedDescending| - Error data float is sorted descending : "
					+ e.getMessage());
		}
		return status;
	}

	/**
	 * To check data type is date sorted ascending
	 *
	 * @param driver
	 * @param locator: the xpath expression of all elements which will be sorted
	 * @return a boolean value, true if data is sorted ascending, else false
	 */
	protected boolean isDataDateSortedAscending(WebDriver driver, String locator) {
		boolean status = true;
		try {
			ArrayList<Date> arrayList = new ArrayList<Date>();
			List<WebElement> elementList = driver.findElements(By.xpath(locator));
			for (WebElement element : elementList) {
				arrayList.add(convertStringToDate(element.getText().trim()));
			}
			ArrayList<Date> sortedList = new ArrayList<Date>();
			for (Date child : arrayList) {
				sortedList.add(child);
			}
			Collections.sort(sortedList);
			if (sortedList.equals(arrayList)) {
				return status;
			}
		} catch (Exception e) {
			status = false;
			log.error("|BasePage| - |isDataDateSortedAscending| - Error data date is sorted ascending : "
					+ e.getMessage());
		}
		return status;
	}

	/**
	 * To check data type is date sorted descending
	 *
	 * @param driver
	 * @param locator: the xpath expression of all elements which will be sorted
	 * @return a boolean value, true if data is sorted descending, else false
	 */
	protected boolean isDataDateSortedDescending(WebDriver driver, String locator) {
		boolean status = true;
		try {
			ArrayList<Date> arrayList = new ArrayList<Date>();
			List<WebElement> elementList = driver.findElements(By.xpath(locator));
			for (WebElement element : elementList) {
				arrayList.add(convertStringToDate(element.getText().trim()));
			}
			ArrayList<Date> sortedList = new ArrayList<Date>();
			for (Date child : arrayList) {
				sortedList.add(child);
			}
			Collections.sort(sortedList);
			Collections.reverse(sortedList);
			if (sortedList.equals(arrayList)) {
				return status;
			}
		} catch (Exception e) {
			status = false;
			log.error("|BasePage| - |isDataDateSortedDescending| - Error data date is sorted descending : "
					+ e.getMessage());
		}
		return status;
	}

	/**
	 * To convert data type from String to Date
	 *
	 * @param dateInString
	 * @return
	 */
	protected Date convertStringToDate(String dateInString) {
		dateInString = dateInString.replace(".", "");
		SimpleDateFormat formatter = new SimpleDateFormat("MMM dd yyyy");
		Date date = null;
		try {
			date = formatter.parse(dateInString);
		} catch (ParseException e) {
			log.error("|BasePage| - |convertStringToDate| - Cannot convert from String to Data: " + e.getMessage());
		}
		return date;
	}

	/**
	 * To check if data at table is displayed or not
	 *
	 * @param driver
	 * @param locatorColumnName: locator of column's name
	 * @param locatorColumn:     locator of column contains text value
	 * @param columnName:        column's name on table
	 * @param textValue:         expected text value
	 * @return a boolean value, true if data is displayed correctly, else false
	 */
	protected boolean isDataDisplayedAtTable(WebDriver driver, String locatorColumnName, String locatorColumn,
			String columnName, String textValue) {
		boolean check = true;
		try {
			int indexOfColumnName = getSizeElements(driver, locatorColumnName, columnName) + 1;
			locatorColumn = castToParameter(locatorColumn, String.valueOf(indexOfColumnName));
			List<String> items = getElementsText(driver, locatorColumn);
			for (String s : items) {
				if (s.contains(textValue)) {
					return check;
				}
			}
		} catch (Exception e) {
			check = false;
			log.error("|BasePage| - |isDataDisplayedAtTable| - Data is not displayed at table: " + e.getMessage());
		}
		return check;
	}

	/**
	 * To check if text result equals keyword or not
	 *
	 * @param driver
	 * @param keyword:       text value that you want to search for
	 * @param resultLocator: locator of text result
	 * @return return a boolean value, true if text result equals keyword, else
	 *         false
	 */
	protected boolean isResultEqualsKeyword(WebDriver driver, String keyword, String resultLocator) {
		boolean status = true;
		try {
			int checkCount = 0;
			List<String> listItems = getElementsText(driver, resultLocator);
			for (String p : listItems) {
				if (p.trim().equals(keyword)) {
					checkCount++;
				}
			}
			if (listItems.size() == checkCount) {
				return status;
			}
		} catch (Exception e) {
			status = false;
			log.error("|BasePage| - |isResultEqualsKeyword| - Error search result doesn't equals keyword: "
					+ e.getMessage());
		}
		return status;
	}

	/**
	 * To check if text result contains keyword or not
	 *
	 * @param driver
	 * @param keyword:       text value that you want to search for
	 * @param resultLocator: locator of text result
	 * @return a boolean value, true if text result contains keyword, else false
	 */
	protected boolean isResultContainsKeyword(WebDriver driver, String keyword, String resultLocator) {
		boolean status = true;
		try {
			int checkCount = 0;
			List<String> listItems = getElementsText(driver, resultLocator);
			for (String p : listItems) {
				if (p.trim().contains(keyword)) {
					checkCount++;
				}
			}
			if (listItems.size() == checkCount) {
				return status;
			}
		} catch (Exception e) {
			status = false;
			log.error("|BasePage| - |isResultContainsKeyword| - Error search result doesn't contains keyword: "
					+ e.getMessage());
		}
		return status;
	}

	/**
	 * To check if text result contains keyword values
	 *
	 * @param driver
	 * @param resultLocator: locator of text result
	 * @param valueExpected
	 * @return a boolean value, true if text result contains keywords, else false
	 */
	public boolean isResultContainsKeyword(WebDriver driver, String resultLocator, String... valueExpected) {
		int check = 0;
		List<String> listItems = getElementsText(driver, resultLocator);
		String[] arrayItems = new String[listItems.size()];
		arrayItems = listItems.toArray(arrayItems);

		for (int i = 0; i < arrayItems.length; i++) {
			for (int j = 0; j < valueExpected.length; j++) {
				if (arrayItems[i].equals(valueExpected[j])) {
					check++;
				}
			}
		}
		if (listItems.size() == check) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * To select multiple options in customer dropdown
	 *
	 * @param driver
	 * @param xpathLocatorParent:   the xpath expression contains all items in
	 *                              dropdown
	 * @param xpathLocatorAllItems: the xpath expression of all items
	 * @param expectedValue:        the value that you want to select
	 */
	protected void multipleSelect(WebDriver driver, String xpathLocatorParent, String xpathLocatorAllItems,
			String... expectedValue) {
		try {
			driver.findElement(By.xpath(xpathLocatorParent)).click();
			explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpathLocatorAllItems)));
			List<WebElement> allItems = driver.findElements(By.xpath(xpathLocatorAllItems));
			for (WebElement items : allItems) {
				for (String item : expectedValue) {
					if (items.getText().equals(item)) {
						jsExecutor.executeScript("arguments[0].scrollIntoView(true)", items);
						sleepInSecond(1);
						items.click();
						break;
					}
				}
			}
		} catch (Exception e) {
			log.error("|BasePage| - |multipleSelect| - Cannot select multiple options: " + e.getMessage());
		}
	}

	/**
	 * To select an option in custom dropdown
	 *
	 * @param driver
	 * @param xpathLocatorParent:   the xpath expression contains all items in
	 *                              dropdown
	 * @param xpathLocatorAllItems: the xpath expression of all items
	 * @param textExpected
	 */
	protected void selectItemInCustomDropdown(WebDriver driver, String xpathLocatorParent, String xpathLocatorAllItems,
			String textExpected) {
		try {

			driver.findElement(By.xpath(xpathLocatorParent)).click();
			explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpathLocatorAllItems)));
			List<WebElement> listAllElement = driver.findElements(By.xpath(xpathLocatorAllItems));
			for (WebElement itemElement : listAllElement) {
				if (itemElement.getText().equals(textExpected)) {
//                    jsExecutor.executeScript("arguments[0].scrollIntoView(true)", itemElement);
					explicitWait.until(ExpectedConditions.elementToBeClickable(itemElement));
					sleepInSecond(2);
					itemElement.click();
					sleepInSecond(2);
					break;
				}
			}
		} catch (Exception e) {
			log.error("|BasePage| - |selectItemInCustomDropdown| - Cannot select option in custom dropdowm: "
					+ e.getMessage());
		}

	}

//    /**
//     * To get response code
//     *
//     * @param url
//     * @return
//     * @throws IOException
//     */
//    public int getResponseCode(String url, String parameterKey, String parameterValue) throws Exception {
//        HttpPost request = new HttpPost(url);
//        List<NameValuePair> urlParameters = new ArrayList<>();
//        request.setHeader("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6Ik1VTTNOekZHTXpCRE1EaEZPREkwTnpoRVJUZzFSa0pGUkVSRk1UTXhOME5DTmpZM09UYzFOUSJ9.eyJpc3MiOiJodHRwczovL2JjLXN0YWdpbmcuYXV0aDAuY29tLyIsInN1YiI6ImF1dGgwfDYxMzg0MjU0ZjJlNTA4MDA2OTcyODYyNSIsImF1ZCI6WyJhcGk6bWFuYWdlbWVudCIsImh0dHBzOi8vYmMtc3RhZ2luZy5hdXRoMC5jb20vdXNlcmluZm8iXSwiaWF0IjoxNjMzMzI5MjIwLCJleHAiOjE2MzM0MTU2MjAsImF6cCI6Im00NDVQVm5yeWxBTGtUcFB3NnNnSzJRdGZrM2Y1ekdkIiwic2NvcGUiOiJvcGVuaWQgcHJvZmlsZSIsInBlcm1pc3Npb25zIjpbXX0.lftfMqDAXpN8ExmbOCtmRMb_yonkM51MWMUIm583Ml8bIAmfLZ_oFSsP5ugIVW-B6VlGG7QA97_xcvQdLZXQXkZWP_YFcgpZuHlOWSKIpw5ILUX9kcSzMwjSkbUiUvmBEeC6rLuNZtql-up67inRqJnmjGYZCIXBfNcxJGIDeQk-aoY9lbwNh5sns1ZUXsaMkDkk20nrl7IINIjtWOXlCOVd5mMPeJWNTXVw4qbhT869UQwDi0-21fon-q-xjM9X6aeEa86aNA1riSdHiVHBK40k-zPGf5NnphtpgJw2PjJInhWqmchuwRCgjjaOJw0tdyTkrYTKUq_tWTo6zpVtIw");
//        urlParameters.add(new BasicNameValuePair(parameterKey, parameterValue));
//        request.setEntity(new UrlEncodedFormEntity(urlParameters));
//
//        CloseableHttpClient client = HttpClients.createDefault();
//        CloseableHttpResponse response = client.execute(request);
//        //HttpEntity entity = response.getEntity();
//        int code = response.getStatusLine().getStatusCode();
//        // String result = EntityUtils.toString(entity);
//        //  System.out.println("Result \n"+result);
//        return code;
//    }

	/**
	 * Get response message
	 *
	 * @param url
	 * @return
	 * @throws IOException
	 */
	protected String getResponseMessage(String url) throws IOException {
		HttpURLConnection cont = (HttpURLConnection) new URL(url).openConnection();
		cont.setRequestMethod("POST");
		cont.connect();
		String message = cont.getResponseMessage();
		return message;
	}

	public boolean checkParameterFilterOptionValue(WebDriver driver, String filterOption,
			String... filterOptionValues) {
		String currentUrl = getCurrentPageUrl(driver);
		String parameterFilter = currentUrl.substring(currentUrl.lastIndexOf("?") + 1);
		String formatFilterOption = filterOption.trim().toLowerCase().replace(" ", "_");
		String prefixFilterOption = "";

		String[] text = filterOption.toLowerCase().split(" ");
		for (int i = 0; i < text.length; i++) {
			prefixFilterOption += text[i].charAt(0);
		}

		return parameterFilter.equals("pf_" + prefixFilterOption + "_" + formatFilterOption + "=" + filterOptionValues)
				? true
				: false;

	}

	public boolean checkParameterSortBy(WebDriver driver, String sortOption) {
		String currentUrl = getCurrentPageUrl(driver);
		boolean check;
		if (!sortOption.equalsIgnoreCase("title ascending")) {
			String parameterSorting = currentUrl.substring(currentUrl.lastIndexOf("?") + 1);
			String formatSorting = sortOption.trim().toLowerCase().replace(" ", "-");
			check = parameterSorting.equals("sort=" + formatSorting) ? true : false;
		} else {
			check = !currentUrl.contains("?");
		}
		return check;

	}

	public boolean checkParameterSearch(WebDriver driver, String searchKeyword) {
		String currentUrl = getCurrentPageUrl(driver);
		String parameterSearch = currentUrl.substring(currentUrl.lastIndexOf("?") + 1);
		String formatSearch = searchKeyword.trim();

		return parameterSearch.equals("q=" + formatSearch) ? true : false;
	}
	
	public void openRelativeUrl(WebDriver driver, String baseUrl, String relativeUrl ) {
	    openPageUrl(driver, baseUrl + relativeUrl);
	}
	
	public BasePage openDynamicUrl(WebDriver driver, String baseUrl, String relativeUrl) {
		openPageUrl(driver, baseUrl + relativeUrl);
		switch (relativeUrl) {
		case "/collections/all":
			return PageGeneratorManager.getFEStoreCollectionsPage(driver);
		case "/collections/shirt":
			return PageGeneratorManager.getFEStoreCollectionsPage(driver);
		case "/search":
			return PageGeneratorManager.getSearchPage(driver);
		case "":
			return PageGeneratorManager.getFEStoreHomePage(driver);
		default:
			throw new RuntimeException("Invalid page name at Homepage.");
		}
	}
	
	public boolean isInstantSearchWidgetDisplayed(WebDriver driver) {
		waitForElementVisible(driver,  BasePageUI.INSTANT_SEARCH_WIDGET);
	     return isElementDisplayed(driver, BasePageUI.INSTANT_SEARCH_WIDGET);
	}
	
	public boolean isInstantSearchWidgetUndisplayed(WebDriver driver) {
	boolean status = false;
	try {
		status =  isElementUnDisplayed(driver, BasePageUI.INSTANT_SEARCH_WIDGET);
		System.out.println("isElementUnDisplayed status: "+ status);
	} catch (Exception e) {
        log.debug("Cannot check instant search widget is undisplayed: " + e.getMessage());
	}
	return status;
}
	
	public void clickAndInputSearchKeywordInISW(WebDriver driver, String keyword) {
		waitForElementClickable(driver,BasePageUI.SEARCH_ICON);
		clickToElement(driver, BasePageUI.SEARCH_ICON);
		sendKeyToElement(driver, BasePageUI.SEARCH_BOX_BOOST , keyword);
	}
	
	public BasePage openDynamicPage(WebDriver driver, String pageName) {
		waitForElementClickable(driver, BasePageUI.NAVIGATION_DYNAMIC_LINK_AT_HOME_PAGE_FE, pageName);
		clickToElement(driver, BasePageUI.NAVIGATION_DYNAMIC_LINK_AT_HOME_PAGE_FE, pageName);
		switch (pageName) {
		case "Home":
			return PageGeneratorManager.getFEStoreHomePage(driver);
		case "All Products":
			return PageGeneratorManager.getFEStoreCollectionsPage(driver);
		case "Dresses":
			return PageGeneratorManager.getFEStoreCollectionsPage(driver);
		default:
			throw new RuntimeException("Invalid page name at Homepage.");
		}
	}
	
	public boolean isContentTypeHeaderDisplayed(WebDriver driver, String contentType) {
		waitForElementVisible(driver, BasePageUI.DYNAMIC_CONTENT_TYPE_HEADER, contentType);
		return isElementDisplayed(driver, BasePageUI.DYNAMIC_CONTENT_TYPE_HEADER, contentType);
	}

}