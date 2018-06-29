package Steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;

import Base.BaseUtil;
import Pages.ICLVDisputesPage;
import Pages.ICLVToPayPage;
import Pages.ICLVToReceivePage;
import Pages.ICLVToolMainPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Implements ICLVDispute.feature steps
 * 
 * @author DavidSauce
 *
 */
public class DisputeStep extends BaseUtil {

	String parentHandle; //used in FW6 to control new windows
	String documentID; // To find invoice in table to check changes.
	String debtorID; // To find invoice in table to check changes.
	boolean resolved; 

	private BaseUtil base;

	public DisputeStep(BaseUtil base) {
		this.base = base;
	}

	/**
	 * Clicks on first not disputed invoice of supplier "supplier".
	 * 
	 * @param supplier
	 */
	@When("^I click first invoice to dispute of \"([^\"]*)\"$")
	public void whenIClickFirstInvoiceToDispute(String supplier) {
		// Payables page
		parentHandle = base.driver.getWindowHandle();
		for (String winHandle : base.driver.getWindowHandles()) {
			base.driver.switchTo().window(winHandle);
		}
		ICLVToPayPage ICLVPayablesPage = new ICLVToPayPage(base.driver);
		documentID = ICLVPayablesPage.clickTblInvoices1stRowNotDisputed(base.driver, supplier);
		if (documentID.isEmpty()) {
			Assert.fail("No invoice pending to be approved available.");
		}
		
	}

	/**
	 * Clicks on Dispute invoice drop down menu option.
	 */
	@And("^I click the Dispute invoice implementation$")
	public void andIClickTheDisputeInvoiceImplementation() {
		ICLVToPayPage ICLVPayablesPage = new ICLVToPayPage(base.driver);
		ICLVPayablesPage.clickLstOption();
		ICLVPayablesPage.clickLstOptionDisputeInvoice();
	}

	/**
	 * Enters full pending amount in Amount field to make dispute.
	 */
	@And("^I enter full pending amount$")
	public void andIEnterFullPendingAmount() {
		String disputeAmount;
		ICLVToPayPage ICLVPayablesPage = new ICLVToPayPage(base.driver);
		disputeAmount = ICLVPayablesPage.getTblInvoicesRowAmount(base.driver, documentID);
		ICLVPayablesPage.setTxtDisputeAmount(base.driver, disputeAmount);
	}

	/**
	 * Enters in Note field the text received in "s".
	 * 
	 * @param s
	 */
	@And("^I enter a description \"([^\"]*)\"$")
	public void andIEnterADescription(String s) {
		ICLVToPayPage ICLVPayablesPage = new ICLVToPayPage(base.driver);
		ICLVPayablesPage.setTxtNoteBox(base.driver, s);
	}

	/**
	 * Checks invoice disputed status is changed to Disputed.
	 * 
	 * @throws InterruptedException
	 */
	@Then("^Status dispute of invoice is set to Yes$")
	public void thenStatusDisputeOfInvoiceIsSetToYes() throws InterruptedException {
		ICLVToPayPage ICLVPayablesPage = new ICLVToPayPage(base.driver);
		Thread.sleep(1500); // Waits for the invoices table to be refreshed.

		assertEquals("Invoice is not set as disputed.", "Dispute",
				ICLVPayablesPage.getTblInvoices1stRowDispute(base.driver, documentID));
	}

	/**
	 * Clicks on supplier To Receive main menu option.
	 * 
	 * @throws InterruptedException
	 */
	@Given("^I click supplier To Receive tab$")
	public void givenIClickSupplierToReceiveTab() throws InterruptedException {
		ICLVToolMainPage ICLVToolMainPage = new ICLVToolMainPage(base.driver);
		ICLVToolMainPage.clickLnkCTCLATAM();
		ICLVToolMainPage.clickLnkToReceiveCTCLATAM();
	}

	/**
	 * Clicks on debtor "debtor" in table Debtors.
	 * 
	 * @param debtor
	 * @throws InterruptedException
	 */
	@When("^I click on debtor \"([^\"]*)\"$")
	public void whenIClickOnDebtor(String debtor) throws InterruptedException {
		ICLVToReceivePage ICLVToReceivePage = new ICLVToReceivePage(base.driver);
		debtorID = debtor;
		ICLVToReceivePage.clickTblDebtorsRow(base.driver, debtor);
	}

	/**
	 * Checks in invoices table that a task is created for the invoice to be resolved.
	 * 
	 * @throws Exception
	 */
	@Then("^a task is created to resolve the invoice disputed$")
	public void thenATaskIsCreatedToResolveTheInvoiceDisputed() throws Exception {
		ICLVToReceivePage ICLVToReceivePage = new ICLVToReceivePage(base.driver);
		assertTrue("Invoice not disputed.", ICLVToReceivePage.checkTblInvoicesDisputed(base.driver, documentID));
	}

	/**
	 * Checks error message when note for the dispute is too short.
	 * 
	 * @throws Exception
	 */
	@Then("^the system should say it is too short$")
	public void thenTheSystemShouldSayItIsTooShort() throws Exception {
		ICLVToPayPage ICLVPayablesPage = new ICLVToPayPage(base.driver);
		assertEquals("Error message not found.", "Please state the reason for the dispute. Use at least 25 characters.",
				ICLVPayablesPage.getLblErrorNoteTooShort(base.driver));
	}

	/**
	 * Select a Disputed invoice in invoices table.
	 * 
	 * @throws Exception
	 */
	@And("^select a disputed invoice$")
	public void andSelectADisputedInvoice() throws Exception {
		ICLVToReceivePage ICLVToReceivePage = new ICLVToReceivePage(base.driver);
		documentID = ICLVToReceivePage.clickInvoiceDisputed(base.driver);
		if (documentID.isEmpty()) {
			Assert.fail("No invoice disputed is available.");
		} else {
//			System.out.println("x Note: " + ICLVToReceivePage.txtNoteBox.getLocation().x);
//			System.out.println("y Note: " + ICLVToReceivePage.txtNoteBox.getLocation().y);
////			System.out.println("isDisplayed utils Note: " + Utils.isDisplayed(base.driver, ICLVToReceivePage.txtNoteBox));
//			System.out.println("isEnabled Note: " + ICLVToReceivePage.txtNoteBox.isEnabled());
//			System.out.println("isDisplayed Note: " + ICLVToReceivePage.txtNoteBox.isDisplayed());
//			System.out.println("size: " + ICLVToReceivePage.txtNoteBox.getSize());
//			ICLVToReceivePage.clickBtnResolveQuery(base.driver);
//			
//			System.out.println("x Note: " + ICLVToReceivePage.txtNoteBox.getLocation().x);
//			System.out.println("y Note: " + ICLVToReceivePage.txtNoteBox.getLocation().y);
////			System.out.println("isDisplayed utils Note: " + Utils.isDisplayed(base.driver, ICLVToReceivePage.txtNoteBox));
//			System.out.println("isEnabled Note: " + ICLVToReceivePage.txtNoteBox.isEnabled());
//			System.out.println("isDisplayed Note: " + ICLVToReceivePage.txtNoteBox.isDisplayed());
//			System.out.println("size: " + ICLVToReceivePage.txtNoteBox.getSize());
////			ICLVToReceivePage.clickBtnResolveQuery(base.driver);
			
			Thread.sleep(500); //To give time all objects be loaded.
			if (!ICLVToReceivePage.txtNoteBox.isDisplayed()) {
				System.out.println("Entra en if not isDisplayed: " + ICLVToReceivePage.txtNoteBox.isDisplayed());
				ICLVToReceivePage.clickBtnResolveQuery(base.driver);
			}
		}
	}

	/**
	 * Types a Note in Note box as description for the dispute.
	 * Clicks on Execute button to dispute the invoice.
	 * Checks on debtors table in the row for the working debtor and finally,
	 * assess the working invoice status is changed to Disputed.
	 * 
	 * @throws Exception
	 */
	@And("^resolve the dispute with a note$")
	public void andResolveTheDisputeWithANote() throws Exception {
		ICLVToReceivePage ICLVToReceivePage = new ICLVToReceivePage(base.driver);
		//documentID = ICLVToReceivePage.clickInvoiceDisputed(base.driver);
		System.out.println("DocumentID> " + documentID);
		ICLVToReceivePage.setTxtNoteBox(base.driver, "This invoice is correct");
		ICLVToReceivePage.clickBtnExecute(base.driver);

		ICLVToReceivePage.clickTblDebtorsRow(base.driver, debtorID);

		assertFalse("Invoice disputed.", ICLVToReceivePage.checkTblInvoicesDisputed(base.driver, documentID));
	}

	/**
	 * Checks the working invoice for the working debtor is no longer disputed.
	 * 
	 * @throws Exception
	 */
	@Then("^the invoice is no longer disputed$")
	public void thenTheInvoiceIsNoLongerDisputed() throws Exception {
		ICLVToReceivePage ICLVToReceivePage = new ICLVToReceivePage(base.driver);
		ICLVToReceivePage.clickTblDebtorsRow(base.driver, debtorID);
		assertFalse("Invoice disputed.", ICLVToReceivePage.checkTblInvoicesDisputed(base.driver, documentID));
	}

	/**
	 * Clicks on To Pay main menu option for supplier.
	 * 
	 * @throws InterruptedException
	 */
	@Given("^I click To Pay for supplier$")
	public void givenIClickToPayForSupplier() throws InterruptedException {
		ICLVToolMainPage ICLVToolMainPage = new ICLVToolMainPage(base.driver);
		ICLVToolMainPage.clickLnkCTCLATAM();
		ICLVToolMainPage.clickLnkDANPER();
		ICLVToolMainPage.clickLnkPayablesDANPER();
	}

	/**
	 * Checks if working invoice status is changed to "Solved".
	 * 
	 */
	@When("^I click invoice resolved$")
	public void whenIClickInvoiceResolved() {
		ICLVToPayPage ICLVPayablesPage = new ICLVToPayPage(base.driver);
		resolved = ICLVPayablesPage.getTblInvoices1stRowDispute(base.driver, documentID).equals("Solved");
	}

	/**
	 * Assess if working invoice status is changed to "Solved".
	 * 
	 * @throws Exception
	 */
	@Then("^it is pending to be approved$")
	public void thenItIsPendingToBeApproved() throws Exception {
		assertTrue("Invoice not Solved.", resolved);
	}

	/**
	 * Clicks in first invoice with status "status" in table Invoices.
	 * 
	 * @param status
	 */
	@When("^I click first invoice in \"([^\"]*)\" status$")
	public void whenIClickFirstInvoiceInStatus(String status) {
		ICLVToPayPage ICLVPayablesPage = new ICLVToPayPage(base.driver);
		documentID = ICLVPayablesPage.clickTblInvoices1stRowInStatus(base.driver, status, "");
		if (documentID.isEmpty()) {
			Assert.fail("No invoice in status " + status + " available.");
		} 
	}

	/**
	 * Clicks on Approve dispute option on drop down list.
	 * 
	 */
	@And("^I click the Approved dispute implementation$")
	public void andIClickTheApprovedDisputeImplementation() {
		ICLVDisputesPage ICLVDisputesPage = new ICLVDisputesPage(base.driver);
		ICLVDisputesPage.clickLstDispute();
		ICLVDisputesPage.clickLstDisputeApproved();
	}

	/**
	 * Types in Note field the text received in "note" param.
	 * 
	 * @param note
	 */
	@And("^I enter as Note \"([^\"]*)\"")
	public void andIEnterAsNote(String note) {
		ICLVDisputesPage ICLVDisputesPage = new ICLVDisputesPage(base.driver);
		ICLVDisputesPage.setTxtNoteBox(base.driver, note);
	}

	/**
	 * Assess if working invoice Status is changed to status received in param "status"
	 * 
	 * @param status
	 */
	@Then("^the invoice status changes to \"([^\"]*)\"$")
	public void thenTheInvoiceStatusChangesTo(String status) {
		ICLVToPayPage ICLVPayablesPage = new ICLVToPayPage(base.driver);

		assertEquals("Invoice is not set as " + status, status,
				ICLVPayablesPage.getTblInvoices1stRowDispute(base.driver, documentID));
	}

	/**
	 * Clicks on Rejected dispute option on drop down list.
	 * 
	 */
	@And("^I click the Rejected dispute implementation$")
	public void andIClickTheRejectedDisputeImplementation() {
		ICLVDisputesPage ICLVDisputesPage = new ICLVDisputesPage(base.driver);
		ICLVDisputesPage.clickLstDispute();
		ICLVDisputesPage.clickLstDisputeReject();
	}
}
