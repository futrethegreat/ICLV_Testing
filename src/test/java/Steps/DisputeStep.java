package Steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import Base.BaseUtil;
import Pages.ICLVInvoicesPage;
import Pages.ICLVPayablesPage;
import Pages.ICLVToolMainPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class DisputeStep extends BaseUtil {

	String parentHandle;
	String documentID;

	private BaseUtil base;

	public DisputeStep(BaseUtil base) {
		this.base = base;
	}

	@When("^I click first invoice to dispute of \"([^\"]*)\"$")
	public void whenIClickFirstInvoiceToDispute(String supplier) {
		// Payables page
		parentHandle = base.driver.getWindowHandle();
		for (String winHandle : base.driver.getWindowHandles()) {
			base.driver.switchTo().window(winHandle);
		}
		ICLVPayablesPage ICLVPayablesPage = new ICLVPayablesPage(base.driver);
		documentID = ICLVPayablesPage.clickTblInvoices1stRowNotDisputed(base.driver, supplier);
//		Utils.consoleMsg("document ID. " + documentID);
	}
	
	@And("^I click the Dispute invoice implementation$")
	public void andIClickTheDisputeInvoiceImplementation() {
		ICLVPayablesPage ICLVPayablesPage = new ICLVPayablesPage(base.driver);
		ICLVPayablesPage.clickLstOption();
		ICLVPayablesPage.clickLstOptionDisputeInvoice();
	}
	
	@And("^I enter full pending amount$")
	public void andIEnterFullPendingAmount() {
		String disputeAmount;
		ICLVPayablesPage ICLVPayablesPage = new ICLVPayablesPage(base.driver);
		disputeAmount = ICLVPayablesPage.getTblInvoicesRowAmount(base.driver, documentID);
//		Utils.consoleMsg("dispute amount. " + disputeAmount);
		ICLVPayablesPage.setTxtDisputeAmount(base.driver, disputeAmount);
	}

	@And("^I enter a description \"([^\"]*)\"$")
	public void andIEnterADescription(String s) {
		ICLVPayablesPage ICLVPayablesPage = new ICLVPayablesPage(base.driver);
		ICLVPayablesPage.setTxtNoteBox(base.driver, s);
	}

	@Then("^Status dispute of invoice is set to Yes$")
	public void thenStatusDisputeOfInvoiceIsSetToYes() throws InterruptedException {
		ICLVPayablesPage ICLVPayablesPage = new ICLVPayablesPage(base.driver);
		Thread.sleep(1500); // ESTA ESPERA HAY QUE HACERLO DINAMICO

		assertEquals("Invoice is not set as disputed.", "Dispute", ICLVPayablesPage.getTblInvoices1stRowDispute(base.driver, documentID));
	}

	@Given("^I click supplier Invoices tab$")
	public void givenIClickSupplierInvoicesTab() throws InterruptedException {
		ICLVToolMainPage ICLVToolMainPage = new ICLVToolMainPage(base.driver);
		ICLVToolMainPage.clickLnkDANPER();
		ICLVToolMainPage.clickLnkCTCLATAM();
		ICLVToolMainPage.clickLnkInvoicesCTCLATAM();
	}
	
	@When("^I click on debtor \"([^\"]*)\"$")
	public void whenIClickOnDebtor(String debtor) throws InterruptedException {
		ICLVInvoicesPage ICLVInvoicesPage = new ICLVInvoicesPage(base.driver);
		ICLVInvoicesPage.clickTblDebtorsRow(base.driver, debtor);
	}

	@Then("^a task is created to resolve the invoice disputed$")
	public void thenATaskIsCreatedToResolveTheInvoiceDisputed() throws Exception {
		ICLVInvoicesPage ICLVInvoicesPage = new ICLVInvoicesPage(base.driver);
		assertTrue("Invoice not disputed.",
				ICLVInvoicesPage.checkTblInvoicesDisputed(base.driver,documentID));
	
		base.driver.quit();
	}

	@Then("^the system should say it is too short$")
	public void thenTheSystemShouldSayItIsTooShort() throws Exception {
		ICLVPayablesPage ICLVPayablesPage = new ICLVPayablesPage(base.driver);
		assertEquals("Error message not found.",
				"Please state the reason for the dispute. Use at least 25 characters.",
				ICLVPayablesPage.getLblErrorNoteTooShort(base.driver));

		base.driver.quit();
	}

}