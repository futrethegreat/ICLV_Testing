package com.ctc;

import java.text.Normalizer;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utils {

	private final static String ChromeDriverFileLinux = System.getProperty("user.dir") + "/src/resources/chromedriver";
	private final static String ChromeDriverFileWindows = "src\\resources\\chromedriver.exe";
	private final static String ChromeDriverType = "webdriver.chrome.driver";
	private final static String FirefoxDriverFileLinux = System.getProperty("user.dir") + "/src/resources/geckodriver";
	private final static String FirefoxDriverFileWindows = "src\\resources\\geckodriver.exe";
	private final static String FirefoxDriverType = "webdriver.gecko.driver";

	public static final String dbMemoryDriver = org.h2.Driver.class.getName();
	public static final String dbMemoryURL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
	public static final String dbMemoryUser = "sa";
	public static final String dbMemoryPassword = "";
	public final static String BROWSER = "FFH"; // Options might be CH FF IE SF OP. H means Headless
	public final static String OperatingSystem = System.getProperty("os.name").toLowerCase();

	public static String dbIP;
	public static String dbPort;
	public static String dbName = "library";
	public static String dbUser = "jenkins";
	public static String dbPassword = "jenkins";
	public static String dbDriverName = MariaDB.JDBC_DRIVER;
//	public static String dbMemoryDriver = "org.hsqldb.jdbc.JDBCDriver";
	public static String dbUrl;
	public static String DriverFile;
	public static String DriverType;
	public static String path2XML;
	public static String dbTestingSchema;
	
	public static String dbTestingIP = "192.168.115.135";
	public static String dbTestingPort	= "3306";
	public static String dbTestingName = "ctbadmin";
	public static String dbTestingUser = "promise";
	public static String dbTestingPassword = "hasdlU8sdf3Ajdsafl";
	public static String dbTestingDriverName = MariaDB.JDBC_DRIVER;
	

	@SuppressWarnings("unused")
	public static void setEnvironment() {

		if (OperatingSystem.contains("win")) {
			// SETTINGS FOR WINDOWS

			dbIP = "localhost";
			dbPort = "3306";
			dbUrl = "jdbc:mariadb://" + dbIP + ":" + dbPort + "/" + dbName;
			path2XML = "src\\resources\\";
			dbTestingSchema = "src\\resources\\library_struct.sql";

			if ((BROWSER == "FF") || (BROWSER == "FFH")) {
				DriverFile = FirefoxDriverFileWindows;
				DriverType = FirefoxDriverType;
			} else if ((BROWSER == "CH") || (BROWSER == "CHH")) {
				DriverFile = ChromeDriverFileWindows;
				DriverType = ChromeDriverType;
			}

		} else if (OperatingSystem.contains("nux")) {
			// SETTINGS FOR LINUX

			dbIP = "192.168.116.163";
			dbPort = "3307";
			dbUrl = "jdbc:mariadb://" + dbIP + ":" + dbPort + "/" + dbName;
			path2XML = "src/resources/";
			dbTestingSchema = "src/resources/library_struct.sql";

			if ((BROWSER == "FF") || (BROWSER == "FFH")) {
				DriverFile = FirefoxDriverFileLinux;
				DriverType = FirefoxDriverType;
			} else if ((BROWSER == "CH") || (BROWSER == "CHH")) {
				DriverFile = ChromeDriverFileLinux;
				DriverType = ChromeDriverType;
			}
		}

		consoleMsg("User.dir: " + System.getProperty("user.dir"));
		consoleMsg("DriverFile: " + DriverFile);
		consoleMsg("DriverType: " + DriverType);
	}

	public static void consoleMsg(String msg) {
		System.out.println(msg);
		System.out.println();
	}

	public static boolean waitUntil_isPresent(final WebDriver driver, final By locator) throws TimeoutException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			return true;
		} catch (TimeoutException te) {
			// te.printStackTrace();
			// return false;
			throw new TimeoutException();
		}

	}

	public static boolean waitUntil_isNotPresent(final WebDriver driver, final By locator) throws TimeoutException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
			return true;
		} catch (TimeoutException te) {
			// te.printStackTrace();
			// return false;
			throw new TimeoutException();
		}
		
	}

	public static boolean waitUntil_isClickable(final WebDriver driver, final By locator) throws TimeoutException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(locator));
			return true;
		} catch (TimeoutException te) {
			// te.printStackTrace();
			// return false;
			throw new TimeoutException();
		}
	}

	public static boolean waitUntil_isClickable(final WebDriver driver, final WebElement WE) throws TimeoutException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.elementToBeClickable(WE));
			return true;
		} catch (TimeoutException te) {
			// te.printStackTrace();
			// return false;
			throw new TimeoutException();
		}
	}

	public static String normalizeString(String s) {
		String normalizedString = Normalizer.normalize(s, Normalizer.Form.NFD);
		normalizedString = normalizedString.replaceAll("[^\\p{ASCII}]", "");
		return normalizedString;
	}

	public static void waitFor(long m) throws InterruptedException {
		Thread.sleep(0);
	}

}
