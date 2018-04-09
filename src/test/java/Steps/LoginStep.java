package Steps;

import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.fail;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;

import com.ctc.Utils;

import Base.BaseUtil;
import Pages.ICLVHomePage;
import Pages.ICLVLoginPage;
import Pages.ICLVSignOutPage;
import Pages.ICLVToolMainPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LoginStep extends BaseUtil {

	String parentHandle;

	private BaseUtil base;
	// public final String TrefiMainHomePage = "http://www.trefi.com";
	// public final String SecureTrefiPage = "https://secure.trefi.com/auth/";
	public final String ICLVHomePage = "https://iclvtestweb.capitool.com";

	public LoginStep(BaseUtil base) {
		this.base = base;
	}

	@Given("^I navigate to the ICLV home page$")
	public void givenINavigateToTheICLVHomePage() {
		base.driver.get(ICLVHomePage);
	}

	@And("^I click on Sign In link")
	public void andIClickOnSignInLink() {

		ICLVHomePage ICLVHomePage = new ICLVHomePage(base.driver);
		ICLVHomePage.clickLnkSignIn();
	}

	@Then("^I should see the Login Page")
	public void thenIShouldSeeTheLoginPage() {
		parentHandle = base.driver.getWindowHandle();
		for (String winHandle : base.driver.getWindowHandles()) {
			base.driver.switchTo().window(winHandle);
		}
		ICLVLoginPage ICLVLoginPage = new ICLVLoginPage(base.driver);
		assertEquals("User name field NOT found. Login page NOT reached.", "", ICLVLoginPage.getUserName());
	}

	@When("^I enter ([^\"]*) and ([^\"]*)$")
	public void whenIEnterUsernameAndPassword(String userName, String password) throws Throwable {
		ICLVLoginPage ICLVLoginPage = new ICLVLoginPage(base.driver);
		ICLVLoginPage.typeCredentials(userName, password);
		Utils.waitFor(2500);
	}

	@And("^I click Login button$")
	public void andIClickLoginButton() {
		ICLVLoginPage ICLVLoginPage = new ICLVLoginPage(base.driver);
		ICLVLoginPage.clickLogInBtn();
	}

	@Then("^I should see the Tool main page$")
	public void thenIShouldSeeTheToolMainPage() throws Exception {
		try {
			ICLVToolMainPage ICLVToolMainPage = new ICLVToolMainPage(base.driver);
			assertEquals("Invoices link not found. Tool main page not reached", "Invoices",
					ICLVToolMainPage.getLnkInvoicesText());
		} catch (TimeoutException e) {
			// Si esta aqui es que falla la creacion de la pagina de The Tool
			Utils.consoleMsg("No Tool main page");
			try {
				ICLVLoginPage ICLVLoginPage = new ICLVLoginPage(base.driver);
				assertEquals("Error message not found.", "Ingreso erroneo, por favor trate de nuevo",
						ICLVLoginPage.getErrorText());
				Utils.consoleMsg("Mensaje error: " + ICLVLoginPage.getErrorText());
			} catch (NoSuchElementException e1) {
				Utils.consoleMsg("No error message either. One credentials field must be empty");

			} catch (TimeoutException e2) {
				Utils.consoleMsg("Too many failed attemps");
			}
		}

		Utils.waitFor(2500);
	}

	@When("^I click on Sign Out link$")
	public void whenIClickOnSignOutLink() {
		try {
			ICLVToolMainPage ICLVToolMainPage = new ICLVToolMainPage(base.driver);
			ICLVToolMainPage.clickLnkSignOut();
			Utils.waitFor(2500);
		} catch (Exception e) {
			// Si entra aqui es que no hay pagina The Tool. Seguir hasta el cierre.
			Utils.consoleMsg("No SignOut Link");
		}
	}

	@Then("^I should see the Sign Out page$")
	public void thenIShouldSeeTheSignOutPage() {
		try {
			ICLVSignOutPage ICLVSignOutPage = new ICLVSignOutPage(base.driver);
			assertEquals("SignOut text not found. Sign out not properly made.",
					"USTED HA CERRADO EXITOSAMENTE SU SESION EN TREFI", ICLVSignOutPage.getTxtSignOutText());
			// fail("FALLO AL FINAL");
			Utils.waitFor(2500);
		} catch (Exception e) {
			// Si entra aqui es que no hay página de Sign Out correcta. Seguir hasta el
			// cierre.
			Utils.consoleMsg("No SignOut message");
		}
		base.driver.close();
		base.driver.switchTo().window(parentHandle);
	}
}
