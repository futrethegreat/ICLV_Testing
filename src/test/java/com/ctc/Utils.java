package com.ctc;

import java.text.Normalizer;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Class to centralize general constants, variables and methods to be used for classes of the project.
 * 
 * @author DavidSauce
 *
 */
public class Utils {

	// Constants for browser management, Chrome and Firefox driver location and driver name.
	private final static String ChromeDriverFileLinux = System.getProperty("user.dir") + "/src/resources/chromedriver";
	private final static String ChromeDriverFileWindows = "src\\resources\\chromedriver.exe";
	private final static String ChromeDriverType = "webdriver.chrome.driver";
	private final static String FirefoxDriverFileLinux = System.getProperty("user.dir") + "/src/resources/geckodriver";
	private final static String FirefoxDriverFileWindows = "src\\resources\\geckodriver.exe";
	private final static String FirefoxDriverType = "webdriver.gecko.driver";

	// Variable to specify the browser to use
	public final static String BROWSER = "FF"; // Options might be CH FF IE SF OP. H means Headless
	
	// Variable to store Operating System type 
	public final static String OperatingSystem = System.getProperty("os.name").toLowerCase();

	// Constants to create a database in memory
	public static final String dbMemoryDriver = org.h2.Driver.class.getName();
	public static final String dbMemoryURL = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
	public static final String dbMemoryUser = "sa";
	public static final String dbMemoryPassword = "";

	public static String dbTestingSchema; //Stores path to SQL file with database spect to create db in memory	
	public static String path2XML; // Directory to store files from database memory

	// Variables to connect database for testing Java features (David Sauce)
	public static String dbIP;
	public static String dbPort;
	public static String dbName = "library";
	public static String dbUser = "jenkins";
	public static String dbPassword = "jenkins";
	public static String dbDriverName = MariaDB.JDBC_DRIVER;
//	public static String dbMemoryDriver = "org.hsqldb.jdbc.JDBCDriver";
	public static String dbUrl;
	
	// Variables to store Driver specs for used environment.
	public static String DriverFile;
	public static String DriverType;

	// Variables to connect database in CTC Testing environment
	public static String dbTestingIP = "192.168.115.135";
	public static String dbTestingPort	= "3306";
	public static String dbTestingName = "ctbadmin";
	public static String dbTestingUser = "promise";
	public static String dbTestingPassword = "hasdlU8sdf3Ajdsafl";
	public static String dbTestingDriverName = MariaDB.JDBC_DRIVER;
	
	private static long secondsToWait = 10; //seconds to wait for the wait selenium methods.

	/**
	 * 
	 * Sets all environment variables depending on the:
	 * - Operating system
	 * - Browser application
	 * - Browser mode (headless or normal)
	 * 
	 */
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
	}

	/**
	 * 
	 * Prints string "msg" in console and a blank line after it.
	 * 
	 * @param msg: string to print to console.
	 */
	public static void consoleMsg(String msg) {
		System.out.println(msg);
		System.out.println();
	}

	/**
	 * 
	 * Waits for the selenium object "locator" is displayed in screen and return true.
	 * After 10 seconds, should return false.
	 * 
	 * @param driver: stores the web page
	 * @param locator: selenium object to find
	 * @return: true if object is found. false if object is not found.
	 * @throws TimeoutException
	 * 
	 */
	public static boolean waitUntil_isPresent(final WebDriver driver, final By locator) throws TimeoutException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, secondsToWait);
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
			return true;
		} catch (TimeoutException te) {
			// te.printStackTrace();
			// return false;
			throw new TimeoutException();
		}

	}

	/**
	 * 
	 * Waits for the selenium object "locator" is hidden in screen and return true.
	 * After 10 seconds, should return false.
	 * 
	 * @param driver: stores the web page
	 * @param locator: selenium object to find
	 * @return: true if object is not found. true if object is found.
	 * @throws TimeoutException
	 * 
	 */
	public static boolean waitUntil_isNotPresent(final WebDriver driver, final By locator) throws TimeoutException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, secondsToWait);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
			return true;
		} catch (TimeoutException te) {
			// te.printStackTrace();
			// return false;
			throw new TimeoutException();
		}
		
	}

	/**
	 * 
	 * Waits for the selenium object "locator" is clickable in screen and return true.
	 * After 10 seconds, should return false.
	 * 
	 * @param driver: stores the web page
	 * @param locator: selenium object to find
	 * @return: true if object is found and is clickable. false if object is not found and/or is not clickable.
	 * @throws TimeoutException
	 * 
	 */
	public static boolean waitUntil_isClickable(final WebDriver driver, final By locator) throws TimeoutException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, secondsToWait);
			wait.until(ExpectedConditions.elementToBeClickable(locator));
			return true;
		} catch (TimeoutException te) {
			// te.printStackTrace();
			// return false;
			throw new TimeoutException();
		}
	}

	/**
	 * 
	 * Waits for the selenium object "WE" is clickable in screen and return true.
	 * After 10 seconds, should return false.
	 * 
	 * @param driver: stores the web page
	 * @param WE: selenium object to find
	 * @return: true if object is found and is clickable. false if object is not found and/or is not clickable.
	 * @throws TimeoutException
	 * 
	 */
	public static boolean waitUntil_isClickable(final WebDriver driver, final WebElement WE) throws TimeoutException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, secondsToWait);
			wait.until(ExpectedConditions.elementToBeClickable(WE));
			return true;
		} catch (TimeoutException te) {
			// te.printStackTrace();
			// return false;
			throw new TimeoutException();
		}
	}

	/**
	 * Removes non standard characters (accents, spanish, etc.) from string received as param.
	 * 
	 * @param s: string to clean
	 * @return: string without non-standar characters.
	 */
	public static String normalizeString(String s) {
		String normalizedString = Normalizer.normalize(s, Normalizer.Form.NFD);
		normalizedString = normalizedString.replaceAll("[^\\p{ASCII}]", "");
		return normalizedString;
	}

	/**
	 * Sleeps system for amount of seconds specified (m*1000)
	 * 
	 * @param m: miliseconds to wait.
	 * @throws InterruptedException
	 */
	public static void waitFor(long m) throws InterruptedException {
		Thread.sleep(0);
	}
	
	/**
	 * Shows or hides right menu, depending on "show" param and menu status.
	 * 
	 * @param driver: stores the web page.
	 * @param show: true to show menu. false to hide menu.
	 * 
	 */
	public static void rightMenuAction(WebDriver driver, Boolean show) {
		WebElement rightMenu = driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/div/div[3]"));
		WebElement beaconField = driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/div/div[2]/div[1]/form/h3/table/tbody/tr/td[2]/div"));
		
		Actions action = new Actions(driver);

		//if need to show and is not displayed -> send keys to show
		if ((show) && !beaconField.isDisplayed()) {
			action.click(rightMenu).perform();            
		}

		//if need to hide and is displayed -> send keys to hide
		if ((!show) && beaconField.isDisplayed()) {
			action.doubleClick(rightMenu).perform();            
		}
		
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

	