package Steps;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.ctc.MariaDB;
import com.ctc.Utils;

import Base.BaseUtil;
import Pages.ICLVPayablesPage;
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
public class PaymentStep extends BaseUtil {

	BigDecimal previousAmount;
	BigDecimal expectedAmount;
	BigDecimal nextAmount;
	BigDecimal amount2Pay;
	String documentID;

	private BaseUtil base;

	public PaymentStep(BaseUtil base) {
		this.base = base;
	}

	/**
	 * Clicks on debtor To Pay menu link. Parameter is not used at this moment. It
	 * is just to give clarity to feature step to know which debtor is used.
	 * 
	 * @param debtor
	 * @throws InterruptedException
	 */
	@Given("^I click the Payables link for \"([^\"]*)\"$")
	public void givenIClickThePayablesLinkFor(String debtor) throws InterruptedException {
		ICLVToolMainPage ICLVToolMainPage = new ICLVToolMainPage(base.driver);
		// ICLVToolMainPage.clickLnkCTC();
		if (debtor.contains("DANPER")) {
			ICLVToolMainPage.clickLnkDANPER();
			ICLVToolMainPage.clickLnkPayablesDANPER();
		} else if (debtor.contains("LATIN AMERICA")) {
			ICLVToolMainPage.clickLnkCTCLATAM();
			ICLVToolMainPage.clickLnkPayablesCTCLATAM();
		}
	}

	/**
	 * Finds first invoice not disputed pending to pay, gets pending amount and
	 * clicks on it to display side menu.
	 */
	@When("^I click first invoice not disputed to pay it$")
	public void whenIClickFirstInvoiceNotDisputedToPayIt() {
		ICLVPayablesPage ICLVPayablesPage = new ICLVPayablesPage(base.driver);
		previousAmount = new BigDecimal(ICLVPayablesPage.getPendingAmount1stNotDisputed(base.driver, true));
		documentID = ICLVPayablesPage.getDocumentID1stNotDisputed(base.driver);
	}

	/**
	 * Finds first invoice not disputed pending to pay, gets pending amount and
	 * clicks on it to display side menu.
	 */
	@When("^I click first invoice not disputed of \"([^\"]*)\" to pay it$")
	public void whenIClickFirstInvoiceNotDisputedOfSupplierToPayIt(String supplier) {
		ICLVPayablesPage ICLVPayablesPage = new ICLVPayablesPage(base.driver);
		previousAmount = new BigDecimal(ICLVPayablesPage.getPendingAmount1stNotDisputed(base.driver, supplier, true));
		documentID = ICLVPayablesPage.getDocumentID1stNotDisputed(base.driver, supplier);
	}

	/**	 * Clicks on Pay now option from drop down menu.
	 */
	@And("^I click the Pay invoice implementation$")
	public void andIClickThePayInvoiceImplementation() {
		ICLVPayablesPage ICLVPayablesPage = new ICLVPayablesPage(base.driver);
		ICLVPayablesPage.clickLstOption();
		ICLVPayablesPage.clickLstOptionPayInvoice();
	}

	/**
	 * Enters the amount specified in feature step. Makes calculations to get
	 * expected amount in order to use it in final assessment.
	 * 
	 * @param amount
	 */
	@And("^I enter the amount \"([^\"]*)\"$")
	public void andIEnterTheAmount(String amount) {
		ICLVPayablesPage ICLVPayablesPage = new ICLVPayablesPage(base.driver);
		if (amount.equals("Full")) {
			amount2Pay = previousAmount;
		} else {
			amount2Pay = new BigDecimal(amount);
		}
		ICLVPayablesPage.setTxtAmount(base.driver, amount2Pay.toString());
		expectedAmount = previousAmount.subtract(amount2Pay);
	}

	/**
	 * Clicks on Execute button.
	 */
	@And("^I click Execute$")
	public void andIClickExecute() {
		ICLVPayablesPage ICLVPayablesPage = new ICLVPayablesPage(base.driver);
		ICLVPayablesPage.clickBtnExecute(base.driver);
	}

	/**
	 * Enters password and code provided in the confirmation popup.
	 * 
	 * @param password
	 */
	@And("^I enter \"([^\"]*)\" as password and the code provided$")
	public void whenIEnterPasswordAndTheCodeProvided(String password) {
		ICLVPayablesPage ICLVPayablesPage = new ICLVPayablesPage(base.driver);
		String code = ICLVPayablesPage.getCodeFromConfirmMsg(base.driver);
		ICLVPayablesPage.setTxtConfirmCode(base.driver, code);
		ICLVPayablesPage.setTxtConfirmPassword(base.driver, password);
	}

	/**
	 * Clicks OK button of confirmation popup in order to confirm payment.
	 * @throws InterruptedException 
	 */
	@And("^I click OK$")
	public void andIClickOK() throws InterruptedException {
		ICLVPayablesPage ICLVPayablesPage = new ICLVPayablesPage(base.driver);
		ICLVPayablesPage.clickBtnConfirmOK(base.driver);
	}

	/**
	 * Checks displayed pending amount is reduced according to amount paid.
	 * 
	 * @param amount
	 * @throws InterruptedException
	 */
	@Then("^Open amount of invoice is decreased by \"([^\"]*)\"$")
	public void thenOpenAmountOfInvoiceIsDecreasedBy(String amount) throws InterruptedException {
		ICLVPayablesPage ICLVPayablesPage = new ICLVPayablesPage(base.driver);
		Thread.sleep(1500); // Waits for the invoice table to be reloaded. No way to do it dinamically.
		nextAmount = new BigDecimal(ICLVPayablesPage.getPendingAmountForDocumentID(base.driver, documentID));
		assertEquals("Open amount is not correct.", expectedAmount.toString(), nextAmount.toString());
	}

	@And("^Amount in database is also updated$")
	public void AmountInDatabaseIsAlsoUpdated() {
		assertEquals("Open amount in DB is not correct.", expectedAmount.toString(), getOpenAmountFromDB());
		base.driver.quit();
	}

	@And("^I enter an amount larger than the remaining$")
	public void andIEnterAnAmountLargerThanTheRemaining() {

		WebElement InvoicesTable = base.driver.findElement(By.id("ot80"));
		List<WebElement> tableRows = InvoicesTable.findElements(By.tagName("tr"));
		List<WebElement> rowCells = tableRows.get(1).findElements(By.tagName("td"));
		previousAmount = new BigDecimal(rowCells.get(6).getText().replace(",", ""));
		BigDecimal amount2Pay = previousAmount.add(new BigDecimal(1));

		ICLVPayablesPage ICLVPayablesPage = new ICLVPayablesPage(base.driver);
		ICLVPayablesPage.setTxtAmount(base.driver, amount2Pay.toString());
	}

	@Then("^the system should say it is not possible$")
	public void thenTheSystemShouldSayItIsNotPossible() throws Exception {
		ICLVPayablesPage ICLVPayablesPage = new ICLVPayablesPage(base.driver);
		assertEquals("Error message not found.", "Please enter an amount between 0 and the exposure on the invoice",
				ICLVPayablesPage.getLblErrorAmount(base.driver));
		// Utils.consoleMsg("Error message: " +
		// ICLVPayablesPage.getTxtErrorAmountTooBig(base.driver));

		base.driver.quit();
	}

	@Then("^the system should say there is no sufficient amount$")
	public void thenTheSystemShouldSayThereIsNoSufficientAmount() throws Exception {
		ICLVPayablesPage ICLVPayablesPage = new ICLVPayablesPage(base.driver);
		assertEquals("Error message not found.", "The amount on your account is not sufficient to make this payment",
				ICLVPayablesPage.getLblErrorAmount(base.driver));
		base.driver.quit();
	}

	public String getOpenAmountFromDB() {
		MariaDB javaMySQLBasic = new MariaDB();
		Connection c;
		String openAMT = "";

		try {
			c = javaMySQLBasic.connectDatabase(Utils.dbTestingIP, Utils.dbTestingPort, Utils.dbTestingName,
					Utils.dbTestingUser, Utils.dbTestingPassword);

			PreparedStatement s = c.prepareStatement(
					"select r.INSID as DOCUMENTID, d.FKMAPLEGALENTITY as DEBTOR,d.NAME as DEBTORNAME, l.FKMAPLEGALENTITY as LENDER,  l.NAME as LENDERNAME,r.ORGAMT as TOTALAMOUNT,r.ORGAMT-r.PAYAMT as OPEN \n"
							+ "from ctbadmin.receivable r "
							+ "inner join ctbadmin.legalentity d on d.LEGALENTITYID=r.FKDEBTOR "
							+ "inner join ctbadmin.lender l on l.LEGALENTITYID=r.FKLENDER "
							+ "where d.FKMAPLEGALENTITY=? and r.INSID=?;");
			s.setString(1, getLEID());
			s.setString(2, documentID);
			ResultSet rs = s.executeQuery();

			if (rs.next()) {
				openAMT = rs.getString("OPEN");
			}

			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ERROR: Closing application. Check database connection data and credentials.");

		} catch (Exception e) {
			System.out.println("ERROR: Exception not handled: ");
			e.printStackTrace();
		}
		return openAMT;
	}

	public String getLEID() {
		String LEID = "";
		try {
			String sURL = base.driver.getCurrentUrl();
			URL aURL = new URL(sURL);
			Map<String, String> map = getQueryMap(aURL.getQuery());
			LEID = map.get("leid");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return LEID;
	}

	public static Map<String, String> getQueryMap(String query) {
		String[] params = query.split("&");
		Map<String, String> map = new HashMap<String, String>();
		for (String param : params) {
			String name = param.split("=")[0];
			String value = param.split("=")[1];
			map.put(name, value);
		}
		return map;
	}

}
