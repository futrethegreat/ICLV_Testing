package Steps;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

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
 *         Implements ICLVPayment.feature steps:
 * 
 *         Scenario Outline: Login with correct credentials and sign out
 *         afterwards. Given I navigate to the ICLV home page When I click on
 *         Sign In link Then I should see the Login Page When I enter <UserName>
 *         and <Password> And I click Login button Then I should see the Tool
 *         main page Given I click the Payables tab When I click first invoice
 *         to pay And I click the Pay invoice implementation And I enter the
 *         amount 1.1 And I click Execute Then Open amount of invoice is
 *         decreased by 1.1
 *
 */
public class PaymentStep extends BaseUtil {

	String parentHandle;
	BigDecimal previousAmount;
	BigDecimal expectedAmount;
	BigDecimal nextAmount;

	private BaseUtil base;

	public PaymentStep(BaseUtil base) {
		this.base = base;
	}

	@Given("^I click the Payables tab$")
	public void givenIClickThePayablesTab() {

		ICLVToolMainPage ICLVToolMainPage = new ICLVToolMainPage(base.driver);
		ICLVToolMainPage.clickLnkPayables(base.driver);
		// Utils.waitUntil_isPresent(base.driver, By.linkText("Payables"));
		// WebElement lnkPayables = base.driver.findElement(By.linkText("Payables"));
		// lnkPayables.click();
	}

	@When("^I click first invoice to pay$")
	public void whenIClickFirstInvoiceToPay() {
		// Payables page
		parentHandle = base.driver.getWindowHandle();
		for (String winHandle : base.driver.getWindowHandles()) {
			base.driver.switchTo().window(winHandle);
		}
		ICLVPayablesPage ICLVPayablesPage = new ICLVPayablesPage(base.driver);
		ICLVPayablesPage.clickTblInvoices1stRow(base.driver);

		// // Access table data
		// Utils.waitUntil_isPresent(base.driver, By.id("ot80"));
		// WebElement InvoicesTable = base.driver.findElement(By.id("ot80"));
		// // Table rows
		// List<WebElement> tableRows = InvoicesTable.findElements(By.tagName("tr"));
		// // // Row#1 columns
		// List<WebElement> rowCells = tableRows.get(1).findElements(By.tagName("td"));
		// // // Click on first data row
		// Utils.waitUntil_isClickable(base.driver, rowCells.get(1));
		// rowCells.get(1).click();
	}

	@And("^I click the Pay invoice implementation$")
	public void andIClickThePayInvoiceImplementation() {

		ICLVPayablesPage ICLVPayablesPage = new ICLVPayablesPage(base.driver);
		ICLVPayablesPage.clickLstOption();
		ICLVPayablesPage.clickLstOptionPayInvoice();

		// // Click on list box row to unfold
		// WebElement lstOption =
		// base.driver.findElement(By.xpath("//*[@id=\"selectedActTypeid_chosen\"]/a/div/b"));
		// lstOption.click();
		// // Click on Pay invoice option from the list
		// lstOption =
		// base.driver.findElement(By.xpath("//*[@id=\"selectedActTypeid_chosen\"]/div/ul/li[1]"));
		// lstOption.click();
	}

	@And("^I enter the amount (.+)$")
	public void andIEnterTheAmount(BigDecimal amount) {

		ICLVPayablesPage ICLVPayablesPage = new ICLVPayablesPage(base.driver);
		ICLVPayablesPage.setTxtAmount(base.driver, amount.toString());

		// // Wait until the list is folded and the Amount field is displayed again.
		// Then
		// // type an amount.
		// Utils.waitUntil_isPresent(base.driver, By.id("PAYAMT"));
		// WebElement txtAmount = base.driver.findElement(By.id("PAYAMT"));
		// txtAmount.clear();
		// txtAmount.sendKeys(amount.toString());

		// These lines are just for checking operation and display in console.
		WebElement InvoicesTable = base.driver.findElement(By.id("ot80"));
		List<WebElement> tableRows = InvoicesTable.findElements(By.tagName("tr"));
		List<WebElement> rowCells = tableRows.get(1).findElements(By.tagName("td"));
		BigDecimal amount2Pay = new BigDecimal(amount.toString());
		previousAmount = new BigDecimal(rowCells.get(6).getText().replace(",", ""));
		expectedAmount = previousAmount.subtract(amount2Pay);
	}

	@And("^I click Execute$")
	public void andIClickExecute() {

		ICLVPayablesPage ICLVPayablesPage = new ICLVPayablesPage(base.driver);
		ICLVPayablesPage.clickBtnExecute(base.driver);

		// ICLVPayablesPage.clickBtnConfirmOK(base.driver);

		// // Click on Execute button
		// WebElement btnExecute = base.driver.findElement(By.id("execute1"));
		// btnExecute.click();
		// Utils.waitUntil_isClickable(base.driver, By.id("confirmok"));
		// WebElement btnOK = base.driver.findElement(By.id("confirmok"));
		// btnOK.click();
	}

	@And("^I enter \"([^\"]*)\" as password and the code provided$")
	public void whenIEnterPasswordAndTheCodeProvided(String password) throws Throwable {
		ICLVPayablesPage ICLVPayablesPage = new ICLVPayablesPage(base.driver);
		String code = ICLVPayablesPage.getCodeFromConfirmMsg(base.driver);
		ICLVPayablesPage.setTxtConfirmCode(base.driver, code);
		ICLVPayablesPage.setTxtConfirmPassword(base.driver, password);
	}
	
	@And("^I click OK$")
	public void andIClickOK() {

		ICLVPayablesPage ICLVPayablesPage = new ICLVPayablesPage(base.driver);
		ICLVPayablesPage.clickBtnConfirmOK(base.driver);

		// // Click on OK button
		// Utils.waitUntil_isClickable(base.driver, By.id("confirmok"));
		// WebElement btnOK = base.driver.findElement(By.id("confirmok"));
		// btnOK.click();
	}

	@Then("^Open amount of invoice is decreased by (.+)$")
	public void thenOpenAmountOfInvoiceIsDecreasedBy(BigDecimal amount) throws InterruptedException {
		ICLVPayablesPage ICLVPayablesPage = new ICLVPayablesPage(base.driver);
		Thread.sleep(1500); // ESTA ESPERA HAY QUE HACERLO DINAMICO

		nextAmount = new BigDecimal(ICLVPayablesPage.getTblInvoices1stRowAmount(base.driver));
		assertEquals("Open amount is not correct.", expectedAmount.toString(), nextAmount.toString());

		// // Click on Refresh button
		// Thread.sleep(1500); // ESTA ESPERA HAY QUE HACERLO DINAMICO
		// WebElement btnRefresh = base.driver.findElement(By.id("refreshicon"));
		// Utils.waitUntil_isClickable(base.driver, btnRefresh);
		// btnRefresh.click();
		// Utils.waitUntil_isClickable(base.driver, btnRefresh);
		//
		// WebElement InvoicesTable = base.driver.findElement(By.id("ot80"));
		// List<WebElement> tableRows = InvoicesTable.findElements(By.tagName("tr"));
		// List<WebElement> rowCells = tableRows.get(1).findElements(By.tagName("td"));
		// Utils.waitUntil_isClickable(base.driver, rowCells.get(6));
		// nextAmount = new BigDecimal(rowCells.get(6).getText().replace(",", ""));
		// assertEquals("Open amount is not correct.", expectedAmount.toString(),
		// nextAmount.toString());

		// These lines are just for checking operation and display in console.
		Utils.consoleMsg("Previous: " + previousAmount.toString() + " - Expected: " + expectedAmount.toString()
				+ " - Returned: " + nextAmount.toString());
		if (expectedAmount.compareTo(nextAmount) == 0) {
			System.out.println("WORKING GOOD! :-D");
		} else {
			System.out.println("WORKING BAD! X-(");
		}

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
		assertEquals("Error message not found.",
				"Please enter a numeric payment amount between zero and the invoice exposure",
				ICLVPayablesPage.getLblErrorAmountTooBig(base.driver));
//		Utils.consoleMsg("Error message: " + ICLVPayablesPage.getTxtErrorAmountTooBig(base.driver));
	}
}
