package com.ctc;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utils {

	private final static String ChromeDriverFileLinux = System.getProperty("user.dir") + "/src/resources/chromedriver";
	private final static String ChromeDriverFileWindows = "src\\resources\\chromedriver.exe";
	private final static String ChromeDriverType = "webdriver.chrome.driver";
	private final static String FirefoxDriverFileLinux = System.getProperty("user.dir") + "/src/resources/geckodriver";
	private final static String FirefoxDriverFileWindows = "src\\resources\\geckodriver.exe";
	private final static String FirefoxDriverType = "webdriver.gecko.driver";

	public static final String dbTestingDriver = org.h2.Driver.class.getName();
	public static final String dbTestingURL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
	public static final String dbTestingUser = "sa";
	public static final String dbTestingPassword = "";
	public final static String BROWSER = "FF"; // Options can be CH FF IE SF OP
	public final static String OperatingSystem = System.getProperty("os.name").toLowerCase();

	public static String dbIP;
	public static String dbPort;
	public static String dbName = "library";
	public static String dbUser = "jenkins";
	public static String dbPassword = "jenkins";
	public static String dbDriverName = MariaDB.JDBC_DRIVER;
	public static String dbMemoryDriver = "org.hsqldb.jdbc.JDBCDriver";
	public static String dbUrl;
	public static String DriverFile;
	public static String DriverType;
	public static String path2XML;
	public static String dbTestingSchema;

	@SuppressWarnings("unused")
	public static void setEnvironment() {

		if (OperatingSystem.contains("win")) {
			// SETTINGS FOR WINDOWS

			dbIP = "localhost";
			dbPort = "3306";
			dbUrl = "jdbc:mariadb://" + dbIP + ":" + dbPort + "/" + dbName;
			path2XML = "src\\resources\\";
			dbTestingSchema = "src\\resources\\library_struct.sql";

			if (BROWSER == "FF") {
				DriverFile = FirefoxDriverFileWindows;
				DriverType = FirefoxDriverType;
			} else if (BROWSER == "CH") {
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

			if (BROWSER == "FF") {
				DriverFile = FirefoxDriverFileLinux;
				DriverType = FirefoxDriverType;
			} else if (BROWSER == "CH") {
				DriverFile = ChromeDriverFileLinux;
				DriverType = ChromeDriverType;
			}
		}
	}

	public static void consoleMsg(String msg) {
		System.out.println(msg);
		System.out.println();
	}

	public static boolean waitUntil_isPresent(final WebDriver driver, final By locator) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 10);
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		} catch (TimeoutException te) {
			te.printStackTrace();
			return false;
		}
		return true;

	}
}
