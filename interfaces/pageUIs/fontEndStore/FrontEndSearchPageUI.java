package pageUIs.fontEndStore;

public class FrontEndSearchPageUI {
	public static final String DYNAMIC_SEARCH_RESULT_FOR = "//h1[text()='Search Results for \"%s\"']";

	public static final String SEARCH_BOX = "//main//input[contains(@class,'boost-pfs-search-box')]";
	public static final String HORIZONTAL_FILTER_TREE_LAYOUT = "//div[contains(@class,'boost-pfs-filter-tree boost-pfs-filter-tree-h') and @id='boost-pfs-filter-tree']";
	public static final String VERTICAL_FILTER_TREE_LAYOUT = "//div[contains(@class,'boost-pfs-filter-tree boost-pfs-filter-tree-v') and @id='boost-pfs-filter-tree2']";
	public static final String POPUlAR_SUGGESTION_IN_SEARCH_WIDGET = "//div[@class='boost-pfs-search-suggestion' and not(@style='display: none;')]//div[@aria-label='Popular suggestions']//a";
	public static final String PRODUCTS_IN_SEARCH_WIDGET = "//p[@class='boost-pfs-search-suggestion-product-title']";

	public static final String SEARCH_RESULT_MESSAGE = "//h1[text()='Search Results for \"%s\"']";
	public static final String PRODUCT_COUNT_SHOWING_TEXT = "//span[contains(.,'Showing')]";
	public static final String IFRAME_PREVIEW_BAR = "//iframe[@id='preview-bar-iframe']";
	public static final String INSTANT_SEARCH_WIDGET = "//div[@id='boost-sd__search-widget-init-wrapper-0']";

	public static final String SEARCH_HEADER = "//h1[@class='boost-sd__search-form-title']";

}
