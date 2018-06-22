package Steps;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;

import com.ctc.Utils;

import Base.BaseUtil;
import Pages.ICLVInvoicesPage;
import Pages.ICLVToolMainPage;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Implements ICLVApproval.feature steps
 * 
 * @author DavidSauce
 *
 */
public class CreateInvoiceStep extends BaseUtil {

	String documentID; // To find invoice in table to check changes.
	String debtorID; // To find invoice in table to check changes.

	private BaseUtil base;

	public CreateInvoiceStep(BaseUtil base) {
		this.base = base;
	}

	/**
	 * Clicks on "company" Invoices menu link. 
	 * 
	 * @param company
	 * @throws InterruptedException
	 */
	@Given("^I click the Invoices link for \"([^\"]*)\"$")
	public void givenIClickThePayablesLinkFor(String company) throws InterruptedException {
		ICLVToolMainPage ICLVToolMainPage = new ICLVToolMainPage(base.driver);
		if (company.contains("LATIN AMERICA")) {
			ICLVToolMainPage.clickLnkCTCLATAM();
			ICLVToolMainPage.clickLnkInvoicesCTCLATAM();
		}
	}

	/**
	 * Clicks on "debtor" row in Debtors table. 
	 * 
	 * @param debtor
	 * @throws InterruptedException
	 */
	@When("^I click debtor \"([^\"]*)\"$")
	public void whenIClickOnDebtor(String debtor) throws InterruptedException {
		ICLVInvoicesPage ICLVInvoicesPage = new ICLVInvoicesPage(base.driver);
		debtorID = debtor;
		ICLVInvoicesPage.clickTblDebtorsRow(base.driver, debtor);
	}

	/**
	 * Clicks on "contact" row in Debtors table. 
	 * 
	 * @param contact
	 * @throws InterruptedException
	 */
	@And("^I click contact \"([^\"]*)\"$")
	public void andIClickContact(String contact) throws InterruptedException {
		ICLVInvoicesPage ICLVInvoicesPage = new ICLVInvoicesPage(base.driver);
		ICLVInvoicesPage.clickTblContactsRow(base.driver, contact);
	}

	/**
	 * Clicks on New Invoice option from drop down menu.
	 */
	@And("^I click new Invoice implementation$")
	public void andIClickNewInvoiceImplementation() throws InterruptedException {
		ICLVInvoicesPage ICLVInvoicesPage = new ICLVInvoicesPage(base.driver);
		ICLVInvoicesPage.clickLstDocumentInvoice(base.driver);
		documentID = ICLVInvoicesPage.getDocumentID(base.driver);
	}

	/**
	 * Enters "codigo" in invoice codigo field.
	 * 
	 * @param codigo
	 * @throws InterruptedException
	 */
	@And("^I enter \"([^\"]*)\" as Codigo$")
	public void andIEnterCodigo(String codigo) throws InterruptedException {
		ICLVInvoicesPage ICLVInvoicesPage = new ICLVInvoicesPage(base.driver);
		ICLVInvoicesPage.setTxtCodigo(base.driver, codigo);
	}

	/**
	 * Enters a random value in invoice Value field.
	 * 
	 * @throws InterruptedException
	 */
	@And("^I enter a random value as Valor$")
	public void andIEnterARandomValueAsValor() throws InterruptedException {
		ICLVInvoicesPage ICLVInvoicesPage = new ICLVInvoicesPage(base.driver);
		// Random value between 5 and 1000
		Integer randomValue = 5 + (int)(Math.random() * ((1000 - 5) + 1));
		ICLVInvoicesPage.setTxtValor(base.driver, randomValue.toString());
	}

	/**
	 * Enters "description" in invoice Description field.
	 * 
	 * @param description
	 * @throws InterruptedException
	 */
	@And("^I enter \"([^\"]*)\" as Description$")
	public void andIEnterDescription(String description) throws InterruptedException {
		ICLVInvoicesPage ICLVInvoicesPage = new ICLVInvoicesPage(base.driver);
		ICLVInvoicesPage.setTxtDescription(base.driver, description);
	}

	/**
	 * Clicks on Execute button.
	 * 
	 * @throws InterruptedException 
	 */
	@And("^I click Execute to create invoice$")
	public void andIClickExecuteToCreateInvoice() throws InterruptedException {
		ICLVInvoicesPage ICLVInvoicesPage = new ICLVInvoicesPage(base.driver);
		ICLVInvoicesPage.clickBtnExecute(base.driver);
		
		// After clicking on Execute button, scroll up page to make visible needed objects.
		Actions action = new Actions(base.driver);
		action.doubleClick(ICLVInvoicesPage.lblDocumentID).perform(); 
		action.sendKeys(Keys.PAGE_UP).perform();
	}
	
	/**
	 * Checks the new invoice created is displayed in Documents table.
	 */
	@Then("^the new Invoice is displayed in Documents table$")
	public void thenTheNewInvoiceIsDisplayedInDocumentsTable() {
		Utils.rightMenuAction(base.driver, false);
		ICLVInvoicesPage ICLVInvoicesPage = new ICLVInvoicesPage(base.driver);
		assertTrue("Invoice not created.", ICLVInvoicesPage.checkTblInvoices(base.driver, documentID));
		Utils.rightMenuAction(base.driver, true); //show right menu to make options visible.
	}
	
	/**
	 * Loops invoices table and submits all in "Draft" status.
	 * 
	 * @throws InterruptedException
	 */
	@And("^I submit all invoices in Draft status$")
	public void andISubmitAllInvoicesInDraftStatus() throws InterruptedException {
		ICLVInvoicesPage ICLVInvoicesPage = new ICLVInvoicesPage(base.driver);
		Utils.rightMenuAction(base.driver, false); //hides right menu to make invoices table full visible

		while (ICLVInvoicesPage.clickTblInvoicesDraft(base.driver)) {
			Utils.rightMenuAction(base.driver, true); // shows right menu to make dropdown options visible
			ICLVInvoicesPage.clickLstDocumentSubmitDocument(base.driver);
			ICLVInvoicesPage.clickBtnExecute(base.driver);
			Utils.rightMenuAction(base.driver, false); //hides right menu to make invoices table full visible
		}
	}
	
	/**
	 * Checks all invoices are in Open status.
	 * 
	 * @throws InterruptedException
	 */
	@And("^all the invoices in Draft are changed to Open$")
	public void thenAllTheInvoicesInDraftAreChangedToOpen() throws InterruptedException {
		ICLVInvoicesPage ICLVInvoicesPage = new ICLVInvoicesPage(base.driver);
		assertTrue("Still invoices in Draft.", ICLVInvoicesPage.clickTblInvoicesDraft(base.driver)==false);
	}
	
}
