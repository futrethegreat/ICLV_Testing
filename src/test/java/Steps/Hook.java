package Steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.ctc.Utils;

import Base.BaseUtil;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

/**
 * Class with preconditions and postconditions for all tests.
 * 
 * @author DavidSauce
 *
 */
public class Hook extends BaseUtil {

	private BaseUtil base;

	public Hook(BaseUtil base) {
		this.base = base;
	}

	/**
	 * Precondition for all tests.
	 * Sets environment and opens browser afterwards.
	 * 
	 * @throws InterruptedException
	 */
	@Before
	public void InitializeTest() throws InterruptedException {
		Utils.setEnvironment();
		base.driver = openBrowser(); // Opens browser following constants and variables set in Utils class.
	}

	/**
	 * Closes browser.
	 * 
	 * @param scenario
	 */
	@After
	public void TearDownTest(Scenario scenario) {
		if (scenario.isFailed()) {
			// Scenario fallido
		}
//		// Get Browser name and version.
//		Capabilities caps = ((RemoteWebDriver) base.driver).getCapabilities();
//		String browserName = caps.getBrowserName();
//		String browserVersion = caps.getVersion();
//
		base.driver.quit(); //closes browser
	}

	/**
	 * Opens browser following variables and constants set on Utils class.
	 * Returns browser opened as a WebDriver object.
	 * 
	 * @return browser session opened.
	 */
	@SuppressWarnings("unused")
	private WebDriver openBrowser() {
		// Passing the real webdriver for the browser selected

		System.setProperty(Utils.DriverType, Utils.DriverFile);
		// driver se puede usar en LoginStep por dependency injection usando
		// picocontainer
		// (hook y loginstep extienden base)
		if (Utils.BROWSER == "CHH") {
			ChromeOptions chromeOptions = new ChromeOptions();
			chromeOptions.addArguments("headless");
			chromeOptions.addArguments("window-size=1200x800");
			return new ChromeDriver(chromeOptions);
		} else if (Utils.BROWSER == "FFH") {
			FirefoxBinary firefoxBinary = new FirefoxBinary();
			firefoxBinary.addCommandLineOptions("--headless");
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.setBinary(firefoxBinary);
//			firefoxOptions.setLogLevel(FirefoxDriverLogLevel.WARN);
			return new FirefoxDriver(firefoxOptions);

			// FirefoxOptions options = new FirefoxOptions();
			// options.setHeadless(true);
			// return new FirefoxDriver(options);
		} else if (Utils.BROWSER == "CH") {
			return new ChromeDriver();
		} else if (Utils.BROWSER == "FF") {
			return new FirefoxDriver();
		} else {
			return new FirefoxDriver();
		}
	}

}