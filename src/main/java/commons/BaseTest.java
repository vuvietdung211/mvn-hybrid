package commons;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	private WebDriver driver;
	protected final Log log;

// Constructor
	protected BaseTest() {
		log = LogFactory.getLog(getClass());
	}

	protected WebDriver getBrowserDriver(String browserName) {
		BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());

		switch (browserList) {
		case FIREFOX:
			FirefoxOptions ffOptions = new FirefoxOptions();
			ffOptions.addArguments("-private");
			driver = WebDriverManager.firefoxdriver().capabilities(ffOptions).create();
			break;

		case FIREFOX_HEADLESS:
			FirefoxOptions ffHeadOptions = new FirefoxOptions();
			ffHeadOptions.addArguments("-headless");
			ffHeadOptions.addArguments("-window-size=1920x1080");
			driver = WebDriverManager.firefoxdriver().capabilities(ffHeadOptions).create();
			break;

		case OPERA:
			driver = WebDriverManager.operadriver().create();
			break;

		case CHROME:
			WebDriverManager.chromedriver().create();
			driver = new ChromeDriver();
			break;

		case CHROME_HEADLESS:
			ChromeOptions chromeHeadOptions = new ChromeOptions();
			chromeHeadOptions.addArguments("--headless");
			chromeHeadOptions.addArguments("-window-size=1920x1080");
			driver = WebDriverManager.chromedriver().capabilities(chromeHeadOptions).create();
			break;

		case EDGE:
			driver = WebDriverManager.edgedriver().create();
			break;

		case SAFARI:
			driver = WebDriverManager.safaridriver().create();
			// driver = new SafariDriver();
			break;

		case IE:
			driver = WebDriverManager.iedriver().arch32().create();
			break;
		case COC_COC:
			WebDriverManager.chromedriver().driverVersion("107.0.5304.62").setup();
			ChromeOptions options = new ChromeOptions();
			options.setBinary("String Path");
			driver = new ChromeDriver(options);
			break;
		default:
			throw new RuntimeException("Browser name is invalid");
		}
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(GlobalConstants.DEV_USER_URL);
		return driver;
	}

	protected WebDriver getBrowserDriver(String browserName, String appURL) {
		BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());

		switch (browserList) {
		case FIREFOX:
			FirefoxOptions ffOptions = new FirefoxOptions();
			ffOptions.addArguments("-private");
			driver = WebDriverManager.firefoxdriver().capabilities(ffOptions).create();
			break;

		case FIREFOX_HEADLESS:
			FirefoxOptions ffHeadOptions = new FirefoxOptions();
			ffHeadOptions.addArguments("-headless");
			ffHeadOptions.addArguments("-window-size=1920x1080");
			driver = WebDriverManager.firefoxdriver().capabilities(ffHeadOptions).create();
			break;

		case OPERA:
			driver = WebDriverManager.operadriver().create();
			break;

		case CHROME:
			WebDriverManager.chromedriver().create();
			driver = new ChromeDriver();
			break;

		case CHROME_HEADLESS:
			ChromeOptions chromeHeadOptions = new ChromeOptions();
			chromeHeadOptions.addArguments("--headless");
			chromeHeadOptions.addArguments("-window-size=1920x1080");
			driver = WebDriverManager.chromedriver().capabilities(chromeHeadOptions).create();
			break;

		case EDGE:
			driver = WebDriverManager.edgedriver().create();
			break;

		case SAFARI:
			driver = WebDriverManager.safaridriver().create();
			// driver = new SafariDriver();
			break;

		case IE:
			driver = WebDriverManager.iedriver().arch32().create();
			break;
		case COC_COC:
			WebDriverManager.chromedriver().driverVersion("107.0.5304.62").setup();
			ChromeOptions options = new ChromeOptions();
			options.setBinary("String Path");
			driver = new ChromeDriver(options);
			break;
		default:
			throw new RuntimeException("Browser name is invalid");
		}
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(getAppURLByServerName(browserName));
		return driver;
	}
	
	protected WebDriver getBrowserDriver(String browserName, String serverName, String roleName) {
		BrowserList browserList = BrowserList.valueOf(browserName.toUpperCase());

		switch (browserList) {
		case FIREFOX:
			FirefoxOptions ffOptions = new FirefoxOptions();
			ffOptions.addArguments("-private");
			driver = WebDriverManager.firefoxdriver().capabilities(ffOptions).create();
			break;

		case FIREFOX_HEADLESS:
			FirefoxOptions ffHeadOptions = new FirefoxOptions();
			ffHeadOptions.addArguments("-headless");
			ffHeadOptions.addArguments("-window-size=1920x1080");
			driver = WebDriverManager.firefoxdriver().capabilities(ffHeadOptions).create();
			break;

		case OPERA:
			driver = WebDriverManager.operadriver().create();
			break;

		case CHROME:
			WebDriverManager.chromedriver().create();
			driver = new ChromeDriver();
			break;

		case CHROME_HEADLESS:
			ChromeOptions chromeHeadOptions = new ChromeOptions();
			chromeHeadOptions.addArguments("--headless");
			chromeHeadOptions.addArguments("-window-size=1920x1080");
			driver = WebDriverManager.chromedriver().capabilities(chromeHeadOptions).create();
			break;

		case EDGE:
			driver = WebDriverManager.edgedriver().create();
			break;

		case SAFARI:
			driver = WebDriverManager.safaridriver().create();
			// driver = new SafariDriver();
			break;

		case IE:
			driver = WebDriverManager.iedriver().arch32().create();
			break;
		case COC_COC:
			WebDriverManager.chromedriver().driverVersion("107.0.5304.62").setup();
			ChromeOptions options = new ChromeOptions();
			options.setBinary("String Path");
			driver = new ChromeDriver(options);
			break;
		default:
			throw new RuntimeException("Browser name is invalid");
		}

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(getAppURLByRoleName(serverName, roleName));
		return driver;
	}

	private String getUserAppURLByServerName(String ServerName) {
		ServerList serverList = ServerList.valueOf(ServerName.toUpperCase());
		
		switch (serverList) {
		case DEV:
			return GlobalConstants.DEV_USER_URL;
		case STAGING:
			return GlobalConstants.STAGING_USER_URL;
		case TESTING:
			return GlobalConstants.TESTING_USER_URL;
		case LIVE:
			return GlobalConstants.LIVE_USER_URL;
		default:
			throw new RuntimeException("Server name is not valid");
		}
	}

	private String getAdminAppURLByServerName(String ServerName) {
		ServerList serverList = ServerList.valueOf(ServerName.toUpperCase());

		switch (serverList) {
		case DEV:
			return GlobalConstants.DEV_ADMIN_URL;
		case STAGING:
			return GlobalConstants.STAGING_ADMIN_URL;

		case TESTING:
			return GlobalConstants.TESTING_ADMIN_URL;

		case LIVE:
			return GlobalConstants.LIVE_ADMIN_URL;

		default:
			throw new RuntimeException("Server name is not valid");
		}
	}

	private String getAppURLByRoleName(String serverName, String roleName) {
		if (roleName.toLowerCase().equals("user")) {
			return getUserAppURLByServerName(serverName);
		} else {
			return getAdminAppURLByServerName(serverName);
		}
	}

	private String getAppURLByServerName(String ServerName) {
		ServerList serverList = ServerList.valueOf(ServerName.toUpperCase());

		switch (serverList) {
		case DEV:
			return GlobalConstants.DEV_USER_URL;
		case STAGING:
			return GlobalConstants.STAGING_USER_URL;

		case TESTING:
			return GlobalConstants.TESTING_USER_URL;

		case LIVE:
			return GlobalConstants.LIVE_USER_URL;

		default:
			throw new RuntimeException("Server name is not valid");
		}
	}
	
	private boolean checkTrue(boolean condition) {
		boolean pass = true;
		try {
			if (condition == true) {
				log.info(" -------------------------- PASSED -------------------------- ");
			} else {
				log.info(" -------------------------- FAILED -------------------------- ");
			}
			Assert.assertTrue(condition);
		} catch (Throwable e) {
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
			if (condition == false) {
				log.info(" -------------------------- PASSED -------------------------- ");
			} else {
				log.info(" -------------------------- FAILED -------------------------- ");
			}
			Assert.assertFalse(condition);
		} catch (Throwable e) {
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
			log.info(" -------------------------- PASSED -------------------------- ");
		} catch (Throwable e) {
			pass = false;
			log.info(" -------------------------- FAILED -------------------------- ");
			VerificationFailures.getFailures().addFailureForTest(Reporter.getCurrentTestResult(), e);
			Reporter.getCurrentTestResult().setThrowable(e);
		}
		return pass;
	}

	protected boolean verifyEquals(Object actual, Object expected) {
		return checkEquals(actual, expected);
	}

	public int getRamdomNumber() {
		Random rand = new Random();
		return rand.nextInt(9999);
	}

	public WebDriver getDriver() {
		return this.driver;
	}

	@BeforeTest
	public void deleteAllFilesInReportNGScreenshot() {
		System.out.println("------ START delete file in folder ----");
		try {
			String workingDir = System.getProperty("user.dir");

			String pathFolderDownload = workingDir + "\\allure-json";
			File file = new File(pathFolderDownload);
			File[] listOfFiles = file.listFiles();
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					System.out.println(listOfFiles[i].getName());
					new File(listOfFiles[i].toString()).delete();
				}
			}
		} catch (Exception e) {
			System.out.print(e.getMessage());
		}
		System.out.println("---------- END delete file in folder ----");
	}

	protected void closeBrowserAndDriver() {
		String cmd = "";
		try {
			String osName = System.getProperty("os.name").toLowerCase();
			log.info("OS name = " + osName);

			String driverInstanceName = driver.toString().toLowerCase();
			log.info("Driver instance name = " + osName);

			if (driverInstanceName.contains("chrome")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq chromedriver*\"";
				} else {
					cmd = "pkill chromedriver";
				}
			} else if (driverInstanceName.contains("internetexplorer")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq IEDriverServer*\"";
				}
			} else if (driverInstanceName.contains("firefox")) {
				if (osName.contains("windows")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq geckodriver*\"";
				} else {
					cmd = "pkill geckodriver";
				}
			} else if (driverInstanceName.contains("edge")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq msedgedriver*\"";
				} else {
					cmd = "pkill msedgedriver";
				}
			} else if (driverInstanceName.contains("opera")) {
				if (osName.contains("window")) {
					cmd = "taskkill /F /FI \"IMAGENAME eq operadriver*\"";
				} else {
					cmd = "pkill operadriver";
				}
			} else if (driverInstanceName.contains("safari")) {
				if (osName.contains("mac")) {
					cmd = "pkill safaridriver";
				}
			}

			if (driver != null) {
				driver.manage().deleteAllCookies();
				driver.quit();
			}
		} catch (Exception e) {
			log.info(e.getMessage());
		} finally {
			try {
				Process process = Runtime.getRuntime().exec(cmd);
				process.waitFor();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
