package pageUIs.admin;

public class AdminAppPageUI {
	public static final String IFRAME_APP = "//iframe[@name='app-iframe']";

	public static final String PLAN_STATUS_NAME = "//div[@class='subscribeBox']//span[text()='%s']";
	public static final String BUTTON_OF_PLAN_STATUS = "//div[@class='subscribeBox']//button/span/span";

	public static final String SUBSCRIBE_NOW_BUTTON = "//button[@type='button']//span[text()='Subscribe now']";
	public static final String SUBSCRIPTION_EXCEEDED_TITLE = "//h2[text()='Subscription exceeded']";
	public static final String SUBSCRIPTION_EXCEEDED_CONTENT = "//h2[text()='Subscription exceeded']//parent::div//parent::div/following-sibling::div//span[contains(.,'plan')]";
	public static final String SUBSCRIPTION_SELECT_PLAN = "//span[text()='Select plan']";

	public static final String FREE_TRIAL_EXPIRED_TITLE = "//h2[text()='Your free trial has expired']";
	public static final String WELCOME_MESSAGE = "//p[contains(.,'Welcome, %s!')]";
}
