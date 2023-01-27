package commons;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaDriver;
import org.testng.Assert;
import org.testng.Reporter;

import io.github.bonigarcia.wdm.WebDriverManager;

//Chứa các hàm dùng chung cho test cases
public class BaseTest {
	private WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	protected WebDriver getBrowserDriver(String browserName, String appUrl) {
		if (browserName.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browserName.equals("h_firefox")) {
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions firefoxOpt = new FirefoxOptions();
			firefoxOpt.addArguments("-headless");
			firefoxOpt.addArguments("window-size=1920x1080");
			driver = new FirefoxDriver(firefoxOpt);
		} else if (browserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browserName.equals("h_chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions chromefoxOpt = new ChromeOptions();
			chromefoxOpt.addArguments("-headless");
			chromefoxOpt.addArguments("window-size=1920x1080");
			driver = new ChromeDriver(chromefoxOpt);
		} else if (browserName.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else if (browserName.equals("opera")) {
			WebDriverManager.operadriver().setup();
			driver = new OperaDriver();
		} else if (browserName.equals("cococ")) {
			// Xem lại config ở đây:
			// https://www.youtube.com/watch?v=pc7HwqmnhHg&list=PLo1QA-RK2zyqDT_-l5anWh-hiIX69hDo-&index=53&ab_channel=AutomationFC
		} else if (browserName.equals("brave")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else {
			throw new RuntimeException("Browser name is invalid");
		}
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(appUrl);
		return driver;

	}

	protected WebDriver getBrowserDriver(String browserName) {
		BrowserList browser = BrowserList.valueOf(browserName.toUpperCase());
		if (browser == BrowserList.FIREFOX) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browser == BrowserList.H_FIREFOX) {
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions firefoxOpt = new FirefoxOptions();
			firefoxOpt.addArguments("-headless");
			firefoxOpt.addArguments("window-size=1920x1080");
			driver = new FirefoxDriver(firefoxOpt);
		} else if (browser == BrowserList.CHROME) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browser == BrowserList.H_CHROME) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions chromefoxOpt = new ChromeOptions();
			chromefoxOpt.addArguments("-headless");
			chromefoxOpt.addArguments("window-size=1920x1080");
			driver = new ChromeDriver(chromefoxOpt);
		} else if (browser == BrowserList.EDGE) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else if (browserName.equals("opera")) {
			WebDriverManager.operadriver().setup();
			driver = new OperaDriver();
		} else if (browserName.equals("cococ")) {
			// Xem lại config ở đây:
			// https://www.youtube.com/watch?v=pc7HwqmnhHg&list=PLo1QA-RK2zyqDT_-l5anWh-hiIX69hDo-&index=53&ab_channel=AutomationFC
		} else if (browserName.equals("brave")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		} else {
			throw new RuntimeException("Browser name is invalid");
		}
		driver.manage().timeouts().implicitlyWait(GlobalConstants.LONG_TIMEOUT, TimeUnit.SECONDS);
		driver.get("");
		return driver;

	}

	private String getEnvironmentUrl(String environmentName) {
		String url = null;
		switch (environmentName) {
		case "STAGING":
			url = GlobalConstants.USER_PAGE_URL;
			break;
		case "PRODUCTION":
			url = GlobalConstants.USER_PAGE_URL;
			break;
		}
		return url;
	}

	private String getEnvironmentValue(String serverName) {
		String environmentUrl = null;
		EnviromentList environment = EnviromentList.valueOf(serverName.toUpperCase());
		if (environment == EnviromentList.STAGING) {
			environmentUrl = "";
		} else {
			environmentUrl = "";
		}
		return environmentUrl;

	}

	protected int generateFakeNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}

	private boolean checkTrue(boolean condition) {
		boolean pass = true;
		try {
			Assert.assertTrue(condition);
			System.out.println(" -------------------------- PASSED -------------------------- ");

		} catch (Throwable e) {
			System.out.println(" -------------------------- FAILED -------------------------- ");
			pass = false;

			// Add lỗi vào ReportNG
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyTrue(boolean condition) {
		return checkTrue(condition);
	}

	private boolean checkFailed(boolean condition) {
		boolean pass = true;
		try {

			Assert.assertFalse(condition);
			System.out.println(" -------------------------- PASSED -------------------------- ");

		} catch (Throwable e) {
			System.out.println(" -------------------------- FAILED -------------------------- ");

			pass = false;
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyFalse(boolean condition) {
		return checkFailed(condition);
	}

	private boolean checkEquals(Object actual, Object expected) {
		boolean pass = true;
		try {
			Assert.assertEquals(actual, expected);
			System.out.println(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			System.out.println(" -------------------------- FAILED -------------------------- ");
			pass = false;	
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyEquals(Object actual, Object expected) {
		return checkEquals(actual, expected);
	}
}
