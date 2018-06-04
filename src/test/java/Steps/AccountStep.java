package Steps;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import Base.BaseUtil;
import Pages.ICLVAccountPage;
import Pages.ICLVToolMainPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * @author DavidSauce
 * 
 *         Implements ICLVPayment.feature steps
 *
 */
public class AccountStep extends BaseUtil {

	BigDecimal previousAmount;
	BigDecimal expectedAmount;
	BigDecimal nextAmount;
	BigDecimal amount2Pay;
	String accountID;

	private BaseUtil base;

	public AccountStep(BaseUtil base) {
		this.base = base;
	}

	@Given("^I click the Account link for \"([^\"]*)\"$")
	public void givenIClickTheAccountLinkFor(String debtor) throws InterruptedException {
		ICLVToolMainPage ICLVToolMainPage = new ICLVToolMainPage(base.driver);
		// ICLVToolMainPage.clickLnkCTC();
		if (debtor.contains("DANPER")) {
			ICLVToolMainPage.clickLnkDANPER();
			ICLVToolMainPage.clickLnkAccountDANPER();
		} else if (debtor.contains("LATIN AMERICA")) {
			ICLVToolMainPage.clickLnkCTCLATAM();
			ICLVToolMainPage.clickLnkAccountCTCLATAM();
		}		
	}

	@When("^I click first account in \"([^\"]*)\" currency and with Saldo$")
	public void whenIClickFirstAccountInCurrencyAndWithSaldo(String currencyID) {
		ICLVAccountPage ICLVAccountPage = new ICLVAccountPage(base.driver);
		accountID = ICLVAccountPage.getAccountNumberForCurrencyID(base.driver, currencyID);
		previousAmount = new BigDecimal(ICLVAccountPage.getSaldoForAccountID(base.driver, accountID));
	}

	@And("^I click the Withdraw implementation$")
	public void andIClickTheWithdrawImplementation() {
		ICLVAccountPage ICLVAccountPage= new ICLVAccountPage(base.driver);
		ICLVAccountPage.clickLstOptions();
		ICLVAccountPage.clickLstOptionsWithdraw();
	}

	/**
	 * Enters the amount specified in feature step. Makes calculations to get
	 * expected amount in order to use it in final assessment.
	 * 
	 * @param amount
	 */
	@And("^I enter \"([^\"]*)\" as the amount to withdraw$")
	public void andIEnterTheAmount(String amount) {
		ICLVAccountPage ICLVAccountPage = new ICLVAccountPage(base.driver);
		if (amount.equals("Full")) {
			amount2Pay = previousAmount;
		} else {
			amount2Pay = new BigDecimal(amount);
		}
		ICLVAccountPage.setTxtAmount(base.driver, amount2Pay.toString());
		expectedAmount = previousAmount.subtract(amount2Pay);
	}

	/**
	 * Clicks on Execute button.
	 */
	@And("^I click Execute withdraw$")
	public void andIClickExecuteWithDraw() {
		ICLVAccountPage ICLVAccountPage = new ICLVAccountPage(base.driver);
		ICLVAccountPage.clickBtnExecute(base.driver);
	}

	/**
	 * Enters password and code provided in the confirmation popup.
	 * 
	 * @param password
	 */
	@And("^I enter \"([^\"]*)\" as password and the code provided for the withdraw$")
	public void whenIEnterPasswordAndTheCodeProvidedForTheWithdraw(String password) {
		ICLVAccountPage ICLVAccountPage = new ICLVAccountPage(base.driver);
		String code = ICLVAccountPage.getCodeFromConfirmMsg(base.driver);
		ICLVAccountPage.setTxtConfirmCode(base.driver, code);
		ICLVAccountPage.setTxtConfirmPassword(base.driver, password);
	}

	/**
	 * Clicks OK button of confirmation popup in order to confirm payment.
	 * @throws InterruptedException 
	 */
	@And("^I click OK for the withdraw$")
	public void andIClickOK() throws InterruptedException {
		ICLVAccountPage ICLVAccountPage = new ICLVAccountPage(base.driver);
		ICLVAccountPage.clickBtnConfirmOK(base.driver);
	}

	/**
	 * Checks displayed Saldo is reduced according to amount withdrawn.
	 * 
	 * @param amount
	 * @throws InterruptedException
	 */
	@Then("^Saldo of account is decreased by \"([^\"]*)\"$")
	public void thenSaldoOfAccountIsDecreasedBy(String amount) throws InterruptedException {
		ICLVAccountPage ICLVAccountPage = new ICLVAccountPage(base.driver);
		Thread.sleep(1500); // Waits for the accounts table to be reloaded. No way to do it dinamically.
		nextAmount = new BigDecimal(ICLVAccountPage.getSaldoForAccountID(base.driver, accountID));

		assertEquals("Pending Saldo is not correct.", expectedAmount.toString(), nextAmount.toString());
	}

	/**
	 * Checks "payment already programmed" displayed error message.
	 * 
	 * @throws InterruptedException
	 */
	@Then("^an error message is displayed$")
	public void thenAnErrorMessageIsDisplayed() throws InterruptedException {
		ICLVAccountPage ICLVAccountPage = new ICLVAccountPage(base.driver);
		Thread.sleep(1500); // Waits for the accounts table to be reloaded. No way to do it dinamically.
		assertEquals("Error message is not displayed.", "Payment already programmed to today", ICLVAccountPage.getLblError(base.driver));
	}
	
}
