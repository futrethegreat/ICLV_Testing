package Steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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
		Thread.sleep(2500);
	}

	@And("^I click Login button$")
	public void andIClickLoginButton() {
		ICLVLoginPage ICLVLoginPage = new ICLVLoginPage(base.driver);
		ICLVLoginPage.clickLogInBtn();
	}

	@Then("^I should see the Tool main page$")
	public void thenIShouldSeeTheToolMainPage() throws Throwable {
		ICLVToolMainPage ICLVToolMainPage = new ICLVToolMainPage(base.driver);
		assertEquals("Invoices link not found. Tool main page not reached", "Invoices",
				ICLVToolMainPage.getLnkInvoicesText());
		Thread.sleep(2500);
	}

	@When("^I click on Sign Out link$")
	public void whenIClickOnSignOutLink() throws Throwable {
		ICLVToolMainPage ICLVToolMainPage = new ICLVToolMainPage(base.driver);
		ICLVToolMainPage.clickLnkSignOut();
		Thread.sleep(2500);
	}

	@Then("^I should see the Sign Out page$")
	public void thenIShouldSeeTheSignOutPage() throws Throwable {
		ICLVSignOutPage ICLVSignOutPage = new ICLVSignOutPage(base.driver);
		assertEquals("SignOut text not found. Sign out not properly made.",
				"USTED HA CERRADO EXITOSAMENTE SU SESIÓN EN TREFI", ICLVSignOutPage.getTxtSignOutText());
		fail("FALLO AL FINAL");
		Thread.sleep(2500);
		base.driver.close();
		base.driver.switchTo().window(parentHandle);
	}

	@Then("^I should receive error message$")
	public void thenIShouldReceiveErrorMessage() throws Throwable {

		Thread.sleep(5000);
		base.driver.close();
		base.driver.switchTo().window(parentHandle);
	}

	/*
	 * @And("^On Secure Trefi Page I enter ([^\"]*) and ([^\\\"]*)$") public void
	 * andIEnterCredentials(String UserName, String Password) throws Throwable {
	 * SecureTrefiLoginPage PageSecureTrefi = new SecureTrefiLoginPage(base.driver);
	 * PageSecureTrefi.Login(UserName, Password);
	 * 
	 * Utils.consoleMsg("Username: " + UserName); Utils.consoleMsg("Password: " +
	 * Password); Thread.sleep(Wait2secs); }
	 * 
	 * @And("^I click Login button$") public void andIClickLoginButton() throws
	 * Throwable { SecureTrefiLoginPage PageSecureTrefi = new
	 * SecureTrefiLoginPage(base.driver); PageSecureTrefi.clickLogInBtn(); }
	 * 
	 * @Then("^I should see the Tool main page$") public void
	 * thenIShouldSeeTheToolMainPage() throws Throwable { SecureTrefiLoginPage
	 * PageSecureTrefi = new SecureTrefiLoginPage(base.driver); try { //
	 * Functions.consoleMsg(PageSecureTrefi.getLblLoginFailed()); // Si llega aqui
	 * es que el mensaje de error existia -> Login incorrecto
	 * PageSecureTrefi.clickLogInBtn(); assertEquals(PageSecureTrefi.getErrorText(),
	 * "Sign in failed, please try again", "No error login text. Login succesful");
	 * sleep(Wait2secs); } catch (Exception e) { // Si entra aqui es que no hay
	 * mensaje de error -> Login correcto // e.printStackTrace();
	 * Utils.consoleMsg("Login correcto"); Thread.sleep(Wait2secs); //
	 * PageSecureTrefi.clickLogoutLnk(); // Thread.sleep(Wait2secs); } }
	 */

}
