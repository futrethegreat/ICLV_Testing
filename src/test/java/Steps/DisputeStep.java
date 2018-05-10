package Steps;

import static org.junit.Assert.assertEquals;

import Base.BaseUtil;
import Pages.ICLVPayablesPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class DisputeStep extends BaseUtil {

	String parentHandle;
	String documentID;

	private BaseUtil base;

	public DisputeStep(BaseUtil base) {
		this.base = base;
	}


	@When("^I click first invoice to dispute$")
	public void whenIClickFirstInvoiceToDispute() {
		// Payables page
		parentHandle = base.driver.getWindowHandle();
		for (String winHandle : base.driver.getWindowHandles()) {
			base.driver.switchTo().window(winHandle);
		}
		ICLVPayablesPage ICLVPayablesPage = new ICLVPayablesPage(base.driver);
		documentID = ICLVPayablesPage.clickTblInvoices1stRowNotDisputed(base.driver);
	}

	@And("^I click the Dispute invoice implementation$")
	public void andIClickTheDisputeInvoiceImplementation() {
		ICLVPayablesPage ICLVPayablesPage = new ICLVPayablesPage(base.driver);
		ICLVPayablesPage.clickLstOption();
		ICLVPayablesPage.clickLstOptionDisputeInvoice();
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
		base.driver.close();
	}
	
	@Then("^the system should say it is too short$")
	public void thenTheSystemShouldSayItIsTooShort() throws Exception {
		ICLVPayablesPage ICLVPayablesPage = new ICLVPayablesPage(base.driver);
		assertEquals("Error message not found.",
				"Please state the reason for the dispute",
				ICLVPayablesPage.getLblErrorNoteTooShort(base.driver));

		base.driver.quit();
	}

}
