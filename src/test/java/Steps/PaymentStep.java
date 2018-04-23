package Steps;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.ctc.Utils;

import Base.BaseUtil;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

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
		Utils.waitUntil_isPresent(base.driver, By.linkText("Payables"));
		WebElement lnkPayables = base.driver.findElement(By.linkText("Payables"));
		lnkPayables.click();
	}

	@When("^I click first invoice to pay$")
	public void whenIClickFirstInvoiceToPay() {
		// Payables page
		parentHandle = base.driver.getWindowHandle();
		for (String winHandle : base.driver.getWindowHandles()) {
			base.driver.switchTo().window(winHandle);
		}

		// Access table data
		Utils.waitUntil_isPresent(base.driver, By.id("ot80"));
		WebElement InvoicesTable = base.driver.findElement(By.id("ot80"));
		// Table rows
		List<WebElement> tableRows = InvoicesTable.findElements(By.tagName("tr"));
		// Row#1 columns
		List<WebElement> rowCells = tableRows.get(1).findElements(By.tagName("td"));
		// Click on first data row
		Utils.waitUntil_isClickable(base.driver, rowCells.get(1));
		rowCells.get(1).click();
	}

	@And("^I click the Pay invoice implementation$")
	public void andIClickThePayInvoiceImplementation() {
		// Click on list box row to unfold
		WebElement lstOption = base.driver.findElement(By.xpath("//*[@id=\"selectedActTypeid_chosen\"]/a/div/b"));
		lstOption.click();
		// Click on Pay invoice option from the list
		lstOption = base.driver.findElement(By.xpath("//*[@id=\"selectedActTypeid_chosen\"]/div/ul/li[1]"));
		lstOption.click();
	}

	@And("^I enter the amount (.+)$")
	public void andIEnterTheAmount(BigDecimal amount) {
		// Wait until the list is folded and the Amount field is displayed again. Then
		// type an amount.
		Utils.waitUntil_isPresent(base.driver, By.id("PAYAMT"));
		WebElement txtAmount = base.driver.findElement(By.id("PAYAMT"));
		txtAmount.clear();
		txtAmount.sendKeys(amount.toString());

		WebElement InvoicesTable = base.driver.findElement(By.id("ot80"));
		List<WebElement> tableRows = InvoicesTable.findElements(By.tagName("tr"));
		List<WebElement> rowCells = tableRows.get(1).findElements(By.tagName("td"));
		BigDecimal amount2Pay = new BigDecimal(amount.toString());
		previousAmount = new BigDecimal(rowCells.get(6).getText().replace(",", ""));
		expectedAmount = previousAmount.subtract(amount2Pay);
	}

	@And("^I click Execute$")
	public void andIClickExecute() {
		// Click on Execute button
		WebElement btnExecute = base.driver.findElement(By.id("execute1"));
		btnExecute.click();
		Utils.waitUntil_isClickable(base.driver, By.id("confirmok"));
		WebElement btnOK = base.driver.findElement(By.id("confirmok"));
		btnOK.click();

	}

	@Then("^Open amount of invoice is decreased by (.+)$")
	public void thenOpenAmountOfInvoiceIsDecreasedBy(BigDecimal amount) throws InterruptedException {
		// Click on Refresh button
		Utils.waitUntil_isClickable(base.driver, By.id("refreshicon"));
		Utils.waitUntil_isClickable(base.driver, By.id("refreshicon"));
		WebElement btnRefresh = base.driver.findElement(By.id("refreshicon"));
		btnRefresh.click();
		// Thread.sleep(1500); // ESTA ESPERA HAY QUE HACERLO DINAMICO
		Utils.waitUntil_isClickable(base.driver, By.id("refreshicon"));
		Utils.waitUntil_isClickable(base.driver, By.id("refreshicon"));
		WebElement InvoicesTable = base.driver.findElement(By.id("ot80"));
		List<WebElement> tableRows = InvoicesTable.findElements(By.tagName("tr"));
		List<WebElement> rowCells = tableRows.get(1).findElements(By.tagName("td"));
		nextAmount = new BigDecimal(rowCells.get(6).getText().replace(",", ""));

		assertEquals("Open amount is not correct.", expectedAmount.toString(), nextAmount.toString());

		Utils.consoleMsg("Previous: " + previousAmount.toString() + " - Expected: " + expectedAmount.toString()
				+ " - Returned: " + nextAmount.toString());
		if (expectedAmount.compareTo(nextAmount) == 0) {
			System.out.println("WORKING GOOD! :-D");
		} else {
			System.out.println("WORKING BAD! X-(");
		}

		base.driver.quit();
	}
}
