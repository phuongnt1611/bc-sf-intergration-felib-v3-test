package pageUIs.base;

public class BasePageUI {
	public static final String NAVIGATION_DYNAMIC_LINK_AT_SYSTEM_DASHBOARD = "//nav//li/a[contains(.,'%s')]";
	public static final String  NAVIGATION_DYNAMIC_LINK_AT_HOME_PAGE_FE= "//nav[@class='header__inline-menu']//span[text()='%s']";
	public static final String INSTANT_SEARCH_WIDGET = "//div[@id='boost-sd__search-widget-init-wrapper-0']";
	public static final String SEARCH_ICON = "//details-modal[@class='header__search']";
	public static final String POPULAR_SUGGESTION_HEADER = "//h3[text()='Popular suggestions']";
	public static final String COLLECTIONS_HEADER = "//h3[text()='Collections']";
	public static final String DYNAMIC_CONTENT_TYPE_HEADER = "//h3[text()='%s']";
	public static final String INSTANT_SEARCH_PRODUCT_LIST_ITEMS="//p[@class='boost-sd__suggestion-queries-item-title' and text()='%s']";
	public static final String SUGGESTION_QUERIES_ITEM_OFSP = "//p[text()='Alpha Industries MA-1 Reversible Bomber Jacket']";
	public static final String TITLE_OF_PRODUCT_DYNAMIC = "//p[@class='boost-sd__suggestion-queries-item-title' and text()='%s']";
	public static final String VENDOR_PRODUCT_DYNAMIC = "//div[@class='boost-sd__suggestion-queries-item-vendor' and text()='%s']";
	public static final String PRODUCT_PRICE_FOLLOW_TITLE_DYNAMIC = "//p[@class='boost-sd__suggestion-queries-item-title' and text()='%s']/parent::div//span[text()='%s']";
	public static final String PRODUCT_IMAGE_ISW_DYNAMIC = "//img[contains(@alt, '%s')]";
	public static final String PRODUCT_SALE_PRICE_ISW_DYNAMIC = "//p[@class='boost-sd__suggestion-queries-item-title' and text()='%s']/parent::div/preceding-sibling::div//div[text()='Sale']";
	public static final String PRODUCT_SKU_ISW_DYNAMIC = "//p[@class='boost-sd__suggestion-queries-item-title' and text()='%s']/following-sibling::div[text()='%s']";
	
	public static final String SEARCH_BOX_BOOST = "//input[@class='search__input field__input boost-sd__search-widget-init-input']";
	public static final String DYNAMIC_MENU_AT_ADMIN_SIMULATOR = "//li[contains(@class,'menuItem menu-%s dropdown')]";
	public static final String DYNAMIC_MENU_ITEM_AT_ADMIN_SIMULATOR = "//span[text()='%s']";
	public static final String IFRAME_APP = "//iframe[@name='app-iframe']";
	public static final String BODY = "//body";

	
}
