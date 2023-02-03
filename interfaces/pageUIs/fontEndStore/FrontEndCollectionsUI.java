package pageUIs.fontEndStore;

public class FrontEndCollectionsUI {
	public static final String VERTICAL_FILTER_TREE_LAYOUT = "//div[contains(@class,'boost-pfs-filter-tree boost-pfs-filter-tree-v') and @id='boost-pfs-filter-tree2']";

	public static final String DYNAMIC_FILTER_OPTION_VALUE = "//div[contains(@class,'boost-pfs-filter-option-%s')]//span[text()='%s']";
	public static final String DYNAMIC_FILTER_OPTION_VALUE_COLOR = "//div[contains(@class,'boost-pfs-filter-option-color')]//span[@title='%s']";
	public static final String DYNAMIC_FILTER_OPTION = "//div[@class='boost-pfs-filter-tree boost-pfs-filter-tree-h']//div[contains(@class,'boost-pfs-filter-option-%s')]";
	public static final String REFINE_BY_CLEAR_ALL = "//div[@class='boost-pfs-filter-refine-by-wrapper']//button[text()='Clear All']";
	public static final String REFINE_BY_LABEL = "//span[text()='Refine By']";
	public static final String INSTANT_SEARCH_WIDGET = "//div[@id='boost-sd__search-widget-init-wrapper-0']";
	public static final String POPUlAR_SUGGESTION_TEXT = "//div[@class='boost-pfs-search-suggestion' and not(@style='display: none;')]//div[@aria-label='Popular suggestions']//a";
	public static final String VIEW_ALL_PRODUCTS_ON_INSTANT_SEARCH = "//div[@class='boost-pfs-search-suggestion' and not(@style='display: none;')]//span[contains(.,'View all')]";

	public static final String PASSWORD_TEXT_BOX = "//input[@id='password']";
	public static final String ENTER_PASSWORD_BUTTON = "//button[text()='Enter']";

	public static final String HORIZONTAL_FILTER_TREE_LAYOUT = "//div[contains(@class,'boost-pfs-filter-tree boost-pfs-filter-tree-h') and @id='boost-pfs-filter-tree']";
	public static final String DYNAMIC_APPLY_BUTTON = "//div[contains(@class,'boost-pfs-filter-option-%s')]//button[text()='Apply']";
	public static final String POPUP_EMAIL_SUBSCRIBE = "//button[@class='cc-popup-close']";

	public static final String FILTER_TITLE = "//span[@class='boost-pfs-filter-option-title-text' and text()='%s']/parent::button";
	public static final String SEARCH_BOX_ON_FILTER = "//span[text()='%s']/parent::button/parent::div/following-sibling::div//input";
	public static final String REFINE_BY_SINGLE_VALUE = "//span[@class='refine-by-type']";
	public static final String MULTIPLE_FILTER_OPTION_SELECT = "//span[text()='%s']/parent::button/parent::div/following-sibling::div/div/ul[contains(@class,'boost-pfs-filter-option-item-list-multiple-list')]";
	public static final String SINGLE_FILTER_OPTION_SELECT = "//span[text()='%s']/parent::button/parent::div/following-sibling::div/div/ul[contains(@class,'boost-pfs-filter-option-item-list-single-list')]";
	public static final String LIST_VALUES_OF_FILTER_OPTION = "//span[text()='%s']/parent::button/parent::div/following-sibling::div/div//span[@class='boost-pfs-filter-option-value']";
	public static final String LIST_VALUES_OF_FILTER_OPTION_COLOR = "//span[text()='Color']/parent::button/parent::div/following-sibling::div/div/ul/li/span";
	public static final String LAST_VALUES_OF_FILTER_OPTION = "(//span[text()='%s']/parent::button/parent::div/following-sibling::div/div//span[@class='boost-pfs-filter-option-value'])[last()]";
	public static final String TOOLTIP_ICON = "//span[@class='boost-pfs-filter-option-title-text' and text()='%s']/following-sibling::div[@class='boost-pfs-filter-option-tooltip']";
	public static final String TOOLTIP_CONTENT = "//span[@class='boost-pfs-filter-option-title-text' and text()='%s']/following-sibling::div//div[@class='boost-pfs-filter-qtip-content']";
	public static final String CLEAR_FILTER_OPTION = "//span[@class='refine-by-type']/parent::button[contains(@aria-label, '%s %s')]";
	public static final String CLEAR_FILTER_OPTION_H = "//span[@class='refine-by-type']/parent::button[contains(@aria-label, '%s')]";
	public static final String FILTER_OPTION_VALUE_REFINE_BY = "//span[@class='refine-by-option' and text()='%s']/following-sibling::span[text()='%s']";
	public static final String FILTER_OPTION_VALUE_REFINE_BY_H = "//span[@class='refine-by-option']/following-sibling::span[text()='%s']";

	public static final String MIN_PRICE_TEXT_BOX = "//input[@class='boost-pfs-filter-option-range-amount-min']";
	public static final String MAX_PRICE_TEXT_BOX = "//input[@class='boost-pfs-filter-option-range-amount-max']";
	public static final String MIN_PRICE_ON_RANGE_SLIDER = "//div[contains(@class,'boost-pfs-filter-option-range-slider')]//div[@aria-label='Min value']";
	public static final String MAX_PRICE_ON_RANGE_SLIDER = "//div[contains(@class,'boost-pfs-filter-option-range-slider')]//div[@aria-label='Max value']";
	public static final String DYNAMIC_MIN_PRICE_ON_RANGE_SLIDER = "//div[contains(@class,'boost-pfs-filter-option-range-slider')]//div[@aria-label='Min value' and @aria-valuetext=";
	public static final String DYNAMIC_MAX_PRICE_ON_RANGE_SLIDER = "//div[contains(@class,'boost-pfs-filter-option-range-slider')]//div[@aria-label='Max value' and @aria-valuetext=";

	public static final String LIST_VALUES_PRODUCT_NUMBER_OF_FILTER_OPTION = "//span[text()='%s']/parent::button/parent::div/following-sibling::div/div//span[@class='boost-pfs-filter-option-amount']";
	public static final String VIEW_MORE_BUTTON = "//span[text()='%s']/parent::button/parent::div/following-sibling::div/div/button[text()='View More']";
	public static final String VIEW_MORE_BUTTON_HORIZONTAL = "//span[text()='%s']/parent::button/parent::div/following-sibling::div/div//button[@aria-label='View More']";

	public static final String ARROW_ICON_MENU_FILTER_COLLECTION = "//span[text()='%s']/parent::button/parent::div/following-sibling::div/div//span[@class='boost-pfs-filter-option-value' and text()='%s']/parent::a/following-sibling::button/span";
	public static final String MENU_FILTER_COLLECTION = "//span[text()='%s']/parent::button/parent::div/following-sibling::div/div//span[@class='boost-pfs-filter-option-value' and text()='%s']";

	public static final String ALL_FILTER_COLLECTION = "//span[text()='%s']/parent::button/parent::div/following-sibling::div/div//span[@class='boost-pfs-filter-option-value' and text()='All']";
	public static final String LOADING_ICON_WHEN_FILTERING = "//span[@class='boost-pfs-filter-loading-icon']";

	public static final String DYNAMIC_LABEL_CONTENT_TYPE_INSTANT_SEARCH = "//li[@aria-label='%s']";
	public static final String DYNAMIC_ITEMS_CONTENT_TYPE_INSTANT_SEARCH = "//li[@aria-label='%s']//following-sibling::li/a";
	public static final String ITEMS_PRODUCT_INSTANT_SEARCH = "//li[@aria-label='Products']//following-sibling::li//p[@class='boost-pfs-search-suggestion-product-title']";
	public static final String ITEMS_TRENDING_PRODUCT_INSTANT_SEARCH = "//li[@aria-label='Trending products']//following-sibling::li/a//p[@class='boost-pfs-search-suggestion-product-title']";
	public static final String NO_RESULT_INSTANT_SEARCH = "//div[@class='boost-pfs-search-suggestion-no-result']/p";
	public static final String PRODUCTS_INSTANT_SEARCH = "//li[@aria-label='Products']//following-sibling::li/a//p[@class='boost-pfs-search-suggestion-product-title']";
	public static final String IN_COLLECTION_SEARCH_BOX = "//input[@class='boost-pfs-in-collection-search-input']";

	public static final String LOAD_MORE_BUTTON = "//button[@class='boost-pfs-filter-load-more-button']";
	public static final String SCROLL_TO_TOP_BUTTON = "//button[contains(@class,'boost-pfs-filter-scroll-to-top')]";
	public static final String DYNAMIC_REVIEW_RATINGS_FILTER_VALUE = "//div[contains(@class,'boost-pfs-filter-option-review-ratings')]//button[contains(@aria-label,'%s Star')]";
	public static final String DYNAMIC_FILTER_OPTION_VALUE_REVIEW_RATINGS = "//div[@id='boost-pfs-filter-tree-pf-r-review-ratings']//button[contains(@aria-label,'%s')]";

	public static final String PRODUCTS_HEADER = "//h1[@class='boost-sd__header-title' and text()='Products']";
}
