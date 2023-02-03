package commons;

import java.io.File;

public class GlobalConstants {
	public static final String ADMIN_PAGE_URL= "https://admin-demo.nopcommerce.com/";
	public static final String USER_PAGE_URL= "https://demo.nopcommerce.com/";
	// get ra đường dẫn thư mục của project
	public static final String PROJECT_PATH = System.getProperty("user.dir");
	public static final String OS_NAME = System.getProperty("os.name");

	// WIN, MAC, LINUX đường dẫn file
	public static final String UPLOAD_FILE = PROJECT_PATH + File.separator + "uploadFiles";
	public static final String DOWLOAD_FILE = PROJECT_PATH + File.separator + "dowloadFiles";
	public static final String BROWSER_LOG = PROJECT_PATH + File.separator + "browserLogs";
	public static final String REPORTNG_SCREENSHOT = PROJECT_PATH + File.separator + "ReportNGScreenShots"+ File.separator ;
	public static final String EXTENT_PATH = PROJECT_PATH + File.separator + "ExtentReportV2" +  File.separator;
	// Database account/ user/ pass/ port
	public static final String DB_DEV_URL = "";
	public static final String DB_DEV_USERNAME = "";
	public static final String DB_DEV_PASSWORD  = "";

	public static final long SHORT_TIMEOUT = 5;
	public static final long LONG_TIMEOUT = 10;
	public static final long RETRY_TEST_FAILD = 3;
	
	// Java version
	public static final String JAVA_VERSION = System.getProperty("java.version");
	
	
	
}
