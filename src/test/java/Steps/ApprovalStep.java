package Steps;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;

import Base.BaseUtil;
import Pages.ICLVToPayPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Implements ICLVApproval.feature steps
 * 
 * @author DavidSauce
 *
 */
public class ApprovalStep extends BaseUtil {

	// To find invoice in table to check changes.
	String documentID;

	private BaseUtil base;

	public ApprovalStep(BaseUtil base) {
		this.base = base;
	}

	/**
	 * Finds first invoice with Approved status set by parameter "status" and clicks on it to
	 * display side menu.
	 * 
	 * @param status: can be Yes or No.
	 */
	@When("^I click first invoice with Approved status \"([^\"]*)\" to approve it$")
	public void whenIClickFirstInvoiceNotApprovedToApproveIt(String status) {
		ICLVToPayPage ICLVPayablesPage = new ICLVToPayPage(base.driver);
		documentID = ICLVPayablesPage.getDocumentID1stStatusApproved(base.driver, status, true);
		if (documentID.isEmpty()) {
			Assert.fail("No invoice pending to be approved available.");
		}
	}

	/**
	 * Clicks on Approve option from drop down menu.
	 */
	@And("^I click the Approve implementation$")
	public void andIClickTheApproveImplementation() {
		ICLVToPayPage ICLVPayablesPage = new ICLVToPayPage(base.driver);
		ICLVPayablesPage.clickLstOption();
		ICLVPayablesPage.clickLstOptionApproveInvoice();
	}

	/**
	 * Enters the amount specified in feature step. 
	 * 
	 * @param amount
	 */
	@And("^I enter \"([^\"]*)\" as note$")
	public void andIEnterTheAmount(String note) {
		ICLVToPayPage ICLVPayablesPage = new ICLVToPayPage(base.driver);
		ICLVPayablesPage.setTxtNoteBox(base.driver, note);
	}

	/**
	 * Checks if status is changed to specified in "status" param. Usually to Yes.
	 * 
	 * @param status: can be Yes or No
	 * @throws InterruptedException
	 */
	@Then("^Approved status is set to \"([^\"]*)\"$")
	public void thenApprovedStatusIsSetTo(String status) throws InterruptedException {
		ICLVToPayPage ICLVPayablesPage = new ICLVToPayPage(base.driver);
		Thread.sleep(1500); // Waits for the invoice table to be reloaded. No way to do it dinamically.
		assertEquals("Approved status not changed.", status,
				ICLVPayablesPage.getApprovedStatusForDocumentID(base.driver, documentID));
	}
	
	/**
	 * Assess the error message returned by the system.
	 * 
	 * @throws Exception
	 */
	@Then("^the system should say it is already approved$")
	public void thenTheSystemShouldSayItIsAlreadyApproved() throws Exception {
		ICLVToPayPage ICLVPayablesPage = new ICLVToPayPage(base.driver);
		assertEquals("Error message not found.", "Please select an invoice that has not yet been approved",
				ICLVPayablesPage.getLblErrorAmount(base.driver));
		// Utils.consoleMsg("Error message: " +
		// ICLVToPayPage.getTxtErrorAmountTooBig(base.driver));

		base.driver.quit();
	}

}
