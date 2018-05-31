package Steps;

import static org.junit.Assert.assertEquals;

import Base.BaseUtil;
import Pages.ICLVPayablesPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * @author DavidSauce
 * 
 *         Implements ICLVApproval.feature steps
 *
 */
public class ApprovalStep extends BaseUtil {

	String documentID;

	private BaseUtil base;

	public ApprovalStep(BaseUtil base) {
		this.base = base;
	}

	/**
	 * Finds first invoice with Approved status set by parameter and clicks on it to
	 * display side menu.
	 * 
	 * @param status
	 */
	@When("^I click first invoice with Approved status \"([^\"]*)\" to approve it$")
	public void whenIClickFirstInvoiceNotApprovedToApproveIt(String status) {
		ICLVPayablesPage ICLVPayablesPage = new ICLVPayablesPage(base.driver);
		documentID = ICLVPayablesPage.getDocumentID1stStatusApproved(base.driver, status, true);
	}

	/**
	 * Clicks on Approve option from drop down menu.
	 */
	@And("^I click the Approve implementation$")
	public void andIClickTheApproveImplementation() {
		ICLVPayablesPage ICLVPayablesPage = new ICLVPayablesPage(base.driver);
		ICLVPayablesPage.clickLstOption();
		ICLVPayablesPage.clickLstOptionApproveInvoice();
	}

	/**
	 * Enters the amount specified in feature step. Makes calculations to get
	 * expected amount in order to use it in final assessment.
	 * 
	 * @param amount
	 */
	@And("^I enter \"([^\"]*)\" as note$")
	public void andIEnterTheAmount(String note) {
		ICLVPayablesPage ICLVPayablesPage = new ICLVPayablesPage(base.driver);
		ICLVPayablesPage.setTxtNoteBox(base.driver, note);
	}

	/**
	 * Checks displayed pending amount is reduced according to amount paid.
	 * 
	 * @param amount
	 * @throws InterruptedException
	 */
	@Then("^Approved status is set to \"([^\"]*)\"$")
	public void thenApprovedStatusIsSetTo(String status) throws InterruptedException {
		ICLVPayablesPage ICLVPayablesPage = new ICLVPayablesPage(base.driver);
		Thread.sleep(1500); // Waits for the invoice table to be reloaded. No way to do it dinamically.
		assertEquals("Approved status not changed.", status,
				ICLVPayablesPage.getApprovedStatusForDocumentID(base.driver, documentID));
	}
	
	/**
	 * Assess the error message returned by the system
	 * 
	 * @throws Exception
	 */
	@Then("^the system should say it is already approved$")
	public void thenTheSystemShouldSayItIsAlreadyApproved() throws Exception {
		ICLVPayablesPage ICLVPayablesPage = new ICLVPayablesPage(base.driver);
		assertEquals("Error message not found.", "Please select an invoice that has not yet been approved",
				ICLVPayablesPage.getLblErrorAmount(base.driver));
		// Utils.consoleMsg("Error message: " +
		// ICLVPayablesPage.getTxtErrorAmountTooBig(base.driver));

		base.driver.quit();
	}

}
