package Pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.ctc.Utils;

/**
 * Implements as objects the Selenium objects of "To Pay" page. 
 * Implements class methods to manage Selenium objects.
 * 
 * @author DavidSauce
 *
 */
public class ICLVToPayPage {

	@FindBys(
		{ 
			@FindBy(xpath = ".//*[@id=\"208|65|PE20170040938\"]/div"), 
			@FindBy(id = "ot80") 
		}
	)
	public WebElement tblInvoices;

	@FindBys(
		{ 
			@FindBy(xpath = ".//*[@id=\"208|65|PE20170040938\"]/div"),
			@FindBy(xpath = ".//*[@id=\"triggertask\"]/div[1]"),
			@FindBy(xpath = ".//*[@id=\"selectedActTypeid_chosen\"]") 
		}
	)
	public WebElement lstOption;

	@FindBys(
		{ 
			@FindBy(xpath = ".//*[@id=\"208|65|PE20170040938\"]/div"),
			@FindBy(xpath = ".//*[@id=\"triggertask\"]/div[1]"),
			@FindBy(xpath = ".//*[@id=\"selectedActTypeid_chosen\"]/div/ul/li[1]") 
		}
	)
	public WebElement lstOptionPayInvoice;

	@FindBys(
		{ 
			@FindBy(xpath = ".//*[@id=\"208|65|PE20170040938\"]/div"),
			@FindBy(xpath = ".//*[@id=\"triggertask\"]/div[1]"),
			@FindBy(xpath = ".//*[@id=\"selectedActTypeid_chosen\"]/div/ul/li[2]") 
		}
	)
	public WebElement lstOptionApproveInvoice;

	@FindBys(
		{ 
			@FindBy(xpath = ".//*[@id=\"208|65|PE20170040938\"]/div"),
			@FindBy(xpath = ".//*[@id=\"triggertask\"]/div[1]"),
			@FindBy(xpath = ".//*[@id=\"selectedActTypeid_chosen\"]/div/ul/li[3]") 
		}
	)
	public WebElement lstOptionDisputeInvoice;

	@FindBys(
		{ 
			@FindBy(xpath = ".//*[@id=\"208|65|PE20170040938\"]/div"), 
			@FindBy(id = "PAYAMT") 
		}
	)
	public WebElement txtAmount;

	@FindBys(
		{ 
			@FindBy(xpath = ".//*[@id=\"208|65|PE20170040938\"]/div"), 
			@FindBy(id = "execute1") 
		}
	)
	public WebElement btnExecute;

	@FindBys(
		{ 
			@FindBy(xpath = ".//*[@id=\"208|65|PE20170040938\"]/div"), 
			@FindBy(id = "confirmok") 
		}
	)
	public WebElement btnConfirmOK;

	@FindBys(
		{ 
			@FindBy(xpath = ".//*[@id=\"208|65|PE20170040938\"]/div"), 
			@FindBy(id = "refreshicon") 
		}
	)
	public WebElement btnRefresh;

	@FindBys(
		{ 
			@FindBy(xpath = ".//*[@id=\"208|65|PE20170040938\"]/div"),
			@FindBy(xpath = ".//*[@id=\"asform\"]/div/div[3]") 
		}
	)
	public WebElement lblError;

	@FindBys(
		{ 
			@FindBy(xpath = ".//*[@id=\"208|65|PE20170040938\"]/div"), 
			@FindBy(id = "confirmmsg") 
		}
	)
	public WebElement lblConfirmMsg;

	@FindBys(
		{ 
			@FindBy(xpath = ".//*[@id=\"208|65|PE20170040938\"]/div"), 
			@FindBy(id = "confirmcode") 
		}
	)
	public WebElement txtConfirmCode;

	@FindBys(
		{ 
			@FindBy(xpath = ".//*[@id=\"208|65|PE20170040938\"]/div"), 
			@FindBy(id = "confirmpassword") 
		}
	)
	public WebElement txtConfirmPassword;

	@FindBys(
		{ 
			@FindBy(xpath = ".//*[@id=\"208|65|PE20170040938\"]/div"), 
			@FindBy(id = "note") 
		}
	)
	public WebElement txtNoteBox;

	@FindBys(
		{ 
			@FindBy(xpath = ".//*[@id=\"208|65|PE20170040938\"]/div"), 
			@FindBy(id = "disputeamount") 
		}
	)
	public WebElement txtDisputeAmount;

	public ICLVToPayPage(WebDriver driver) throws TimeoutException {
		PageFactory.initElements(driver, this);
		Utils.waitUntil_isClickable(driver, tblInvoices);
	}

	/**
	 * Clicks in table Invoices on row of supplier specified in param "supplier",
	 * where status is not "Dispute" nor "Solved".
	 * Finally returns the document ID of that document.
	 * 
	 * @param driver: Stores current web page. Used to detect object presence in screen.
	 * @param supplier: Supplier to locate in invoices table.
	 * @return document ID of clicked row.
	 */
	public String clickTblInvoices1stRowNotDisputed(WebDriver driver, String supplier) {
		String supplierInTable = "";
		String documentID = "";

		// Table rows
		List<WebElement> tableRows = tblInvoices.findElements(By.tagName("tr"));
		// Row#1 columns
		List<WebElement> rowCells = tableRows.get(1).findElements(By.tagName("td"));
		// Click on first data row
		try {
			Utils.waitUntil_isClickable(driver, rowCells.get(1));
		} catch (TimeoutException e) {
			//If no invoices table, return empty string. 
			return documentID;
		}

		for (int i = 1; i < tableRows.size(); i++) {
			rowCells = tableRows.get(i).findElements(By.tagName("td"));
			supplierInTable = (rowCells.get(2).getText().trim().length() == 0) ? supplierInTable
					: rowCells.get(2).getText().trim();

			if ((rowCells.get(11).getText().equals("Dispute") == false)
					&& (rowCells.get(11).getText().equals("Solved") == false)) {
				if (supplier.trim().length() == 0) {
					rowCells.get(0).click();
					documentID = rowCells.get(0).getText();
					break;
				} else if (supplierInTable.equals(supplier)) {
					rowCells.get(0).click();
					documentID = rowCells.get(0).getText();
					break;
				}
			}
		}
		return documentID;
	}

	/**
	 * Clicks on first invoice in the status received on "status" param for "supplier"
	 * param, if this is specified. Returns document ID of clicked row.
	 * 
	 * @param driver: Stores current web page. Used to detect object presence in screen.
	 * @param status: Status to locate in invoices table.
	 * @param supplier: Supplier to locate in invoices table.
	 * @return document ID of clicked row.
	 */
	public String clickTblInvoices1stRowInStatus(WebDriver driver, String status, String supplier) {
		String supplierInTable = "";
		String documentID = "";

		// Table rows
		List<WebElement> tableRows = tblInvoices.findElements(By.tagName("tr"));
		// Row#1 columns
		List<WebElement> rowCells = tableRows.get(1).findElements(By.tagName("td"));
		// Click on first data row
		try {
			Utils.waitUntil_isClickable(driver, rowCells.get(1));
		} catch (TimeoutException e) {
			//If no invoices table, return empty string. 
			return documentID;
		}
		
		for (int i = 1; i < tableRows.size(); i++) {
			rowCells = tableRows.get(i).findElements(By.tagName("td"));
			supplierInTable = (rowCells.get(2).getText().trim().length() == 0) ? supplierInTable
					: rowCells.get(2).getText().trim();

			if (rowCells.get(11).getText().equals(status)) {
				if (supplier.trim().length() == 0) {
					rowCells.get(0).click();
					documentID = rowCells.get(0).getText();
					break;
				} else if (supplierInTable.equals(supplier)) {
					rowCells.get(0).click();
					documentID = rowCells.get(0).getText();
					break;
				}
			}
		}
		return documentID; 
	}

	/**
	 * Returns pending amount to pay for first OPEN invoice not in Disputed neither Solved
	 * 
	 * @param driver: Stores current web page. Used to detect object presence in screen.
	 * @param click: if true, also line found is clicked
	 * @return
	 */
	public String getPendingAmount1stNotDisputed(WebDriver driver, boolean click) {
		String pendingAmount = "";
		
		// Table rows
		List<WebElement> tableRows = tblInvoices.findElements(By.tagName("tr"));
		// Row#1 columns
		List<WebElement> rowCells = tableRows.get(1).findElements(By.tagName("td"));
		// Click on first data row
		try {
			Utils.waitUntil_isClickable(driver, rowCells.get(1));
		} catch (TimeoutException e) {
			//If no invoices table, return empty string. 
			return pendingAmount;
		}

		for (int i = 1; i < tableRows.size(); i++) {
			rowCells = tableRows.get(i).findElements(By.tagName("td"));

			if ((rowCells.get(11).getText().equals("Dispute") == false)
					&& (rowCells.get(11).getText().equals("Solved") == false)
					&& (rowCells.get(13).getText().equals("Open"))) {
				if (click) {
					rowCells.get(0).click();
				}
				pendingAmount = rowCells.get(6).getText().replace(",", "");
				break;
			}
		}
		return pendingAmount;
	}

	/**
	 * Returns pending amount to pay for first OPEN invoice not in Disputed neither
	 * Solved for the supllier specified. Clicks on the row if click = true.
	 * 
	 * @param driver: Stores current web page. Used to detect object presence in screen.
	 * @param supplier: Supplier to locate in invoices table
	 * @param click: if true, also line found is clicked
	 * @return: Pending amount for the invoice found.
	 */
	public String getPendingAmount1stNotDisputed(WebDriver driver, String supplier, boolean click) {
		String supplierInTable = "";
		String pendingAmount = "";

		// Table rows
		List<WebElement> tableRows = tblInvoices.findElements(By.tagName("tr"));
		// Row#1 columns
		List<WebElement> rowCells = tableRows.get(1).findElements(By.tagName("td"));
		// Click on first data row
		try {
			Utils.waitUntil_isClickable(driver, rowCells.get(1));
		} catch (TimeoutException e) {
			//If no invoices table, return empty string. 
			return pendingAmount;
		}

		for (int i = 1; i < tableRows.size(); i++) {
			rowCells = tableRows.get(i).findElements(By.tagName("td"));
			supplierInTable = (rowCells.get(2).getText().trim().length() == 0) ? supplierInTable
					: rowCells.get(2).getText().trim();

			if ((rowCells.get(11).getText().equals("Dispute") == false)
					&& (rowCells.get(11).getText().equals("Solved") == false)
					&& (rowCells.get(13).getText().equals("Open"))) {

				if (supplier.trim().length() == 0) {
					if (click) {
						rowCells.get(0).click();
					}
					pendingAmount = rowCells.get(6).getText().replace(",", "");
					break;
				} else if (supplierInTable.contains(supplier)) {
					if (click) {
						rowCells.get(0).click();
					}
					pendingAmount = rowCells.get(6).getText().replace(",", "");
					break;
				}
			}
		}
		return pendingAmount;
	}

	/**
	 * Returns documentID for first OPEN invoice not in Disputed neither Solved
	 * 
	 * @param driver: Stores current web page. Used to detect object presence in screen.
	 * @return document ID for invoice found. As String.
	 */
	public String getDocumentID1stNotDisputed(WebDriver driver) {
		String documentID = "";
		// Table rows
		List<WebElement> tableRows = tblInvoices.findElements(By.tagName("tr"));
		// Row#1 columns
		List<WebElement> rowCells = tableRows.get(1).findElements(By.tagName("td"));
		// Click on first data row
		try {
			Utils.waitUntil_isClickable(driver, rowCells.get(1));
		} catch (TimeoutException e) {
			//If no invoices table, return empty string. 
			return documentID;
		}

		for (int i = 1; i < tableRows.size(); i++) {
			rowCells = tableRows.get(i).findElements(By.tagName("td"));

			if ((rowCells.get(11).getText().equals("Dispute") == false)
					&& (rowCells.get(11).getText().equals("Solved") == false)
					&& (rowCells.get(13).getText().equals("Open"))) {
				documentID = rowCells.get(0).getText();
				break;
			}
		}
		return documentID;
	}

	/**
	 * Returns documentID for first OPEN invoice not in Disputed neither Solved for
	 * supplier received by param
	 * 
	 * @param driver: Stores current web page. Used to detect object presence in screen.
	 * @param supplier: Supplier to locate in invoices table
	 * @return document ID for invoice found. As String.
	 */
	public String getDocumentID1stNotDisputed(WebDriver driver, String supplier) {
		String supplierInTable = "";
		String documentID = "";

		// Table rows
		List<WebElement> tableRows = tblInvoices.findElements(By.tagName("tr"));
		// Row#1 columns
		List<WebElement> rowCells = tableRows.get(1).findElements(By.tagName("td"));
		// Click on first data row
		try {
			Utils.waitUntil_isClickable(driver, rowCells.get(1));
		} catch (TimeoutException e) {
			//If no invoices table, return empty string. 
			return documentID;
		}

		for (int i = 1; i < tableRows.size(); i++) {
			rowCells = tableRows.get(i).findElements(By.tagName("td"));
			supplierInTable = (rowCells.get(2).getText().trim().length() == 0) ? supplierInTable
					: rowCells.get(2).getText().trim();
			
			if ((rowCells.get(11).getText().equals("Dispute") == false)
					&& (rowCells.get(11).getText().equals("Solved") == false)
					&& (rowCells.get(13).getText().equals("Open"))) {

				if (supplier.trim().length() == 0) {
					documentID = rowCells.get(0).getText();
					break;
				} else if (supplierInTable.contains(supplier)) {
					documentID = rowCells.get(0).getText();
					break;
				}
			}
		}
		return documentID;
	}

	/**
	 * Returns documentID for first invoice with APPROVED status equals to status
	 * param
	 * 
	 * @param driver: Stores current web page. Used to detect object presence in screen.
	 * @param status: Status to locate in invoices table.
	 * @param click: if true, also line found is clicked
	 * @return documentID for first document which fits condition.
	 */
	public String getDocumentID1stStatusApproved(WebDriver driver, String status, boolean click) {
		String documentID = "";
		// Table rows
		List<WebElement> tableRows = tblInvoices.findElements(By.tagName("tr"));
		// Row#1 columns
		List<WebElement> rowCells = tableRows.get(1).findElements(By.tagName("td"));
		// Click on first data row
		try {
			Utils.waitUntil_isClickable(driver, rowCells.get(1));
		} catch (TimeoutException e) {
			//If no invoices table, return empty string. 
			return documentID;
		}

		for (int i = 1; i < tableRows.size(); i++) {
			rowCells = tableRows.get(i).findElements(By.tagName("td"));

			if (rowCells.get(12).getText().equals(status)) {
				if (click) {
					rowCells.get(0).click();
					documentID = rowCells.get(0).getText();
				}
				break;
			}
		}
		return documentID;
	}

	/**
	 * Returns pending amount to pay for specified documentID
	 * 
	 * @param driver: Stores current web page. Used to detect object presence in screen.
	 * @param documentID: Document ID to locate in invoices table.
	 *
	 * @return Pending amount for invoice found.
	 */
	public String getPendingAmountForDocumentID(WebDriver driver, String documentID) {
		String pendingAmount = "";
		// Table rows
		List<WebElement> tableRows = tblInvoices.findElements(By.tagName("tr"));
		// Row#1 columns
		List<WebElement> rowCells = tableRows.get(1).findElements(By.tagName("td"));
		// Click on first data row
		try {
			Utils.waitUntil_isClickable(driver, rowCells.get(1));
		} catch (TimeoutException e) {
			//If no invoices table, return empty string. 
			return pendingAmount;
		}

		for (int i = 1; i < tableRows.size(); i++) {
			rowCells = tableRows.get(i).findElements(By.tagName("td"));

			if (rowCells.get(0).getText().equals(documentID)) {
				pendingAmount = rowCells.get(6).getText().replace(",", "");
				break;
			}
		}
		return pendingAmount;
	}

	/**
	 * Returns Approved status for specified documentID
	 * 
	 * @param driver: Stores current web page. Used to detect object presence in screen.
	 * @param documentID: Document ID to locate in invoices table.
	 *
	 * @return Approved status for invoice found.
	 */
	public String getApprovedStatusForDocumentID(WebDriver driver, String documentID) {
		String approvedStatus = "";
		
		// Table rows
		List<WebElement> tableRows = tblInvoices.findElements(By.tagName("tr"));
		// Row#1 columns
		List<WebElement> rowCells = tableRows.get(1).findElements(By.tagName("td"));
		// Click on first data row
		try {
			Utils.waitUntil_isClickable(driver, rowCells.get(1));
		} catch (TimeoutException e) {
			//If no invoices table, return empty string. 
			return approvedStatus;
		}
		
		for (int i = 1; i < tableRows.size(); i++) {
			rowCells = tableRows.get(i).findElements(By.tagName("td"));

			if (rowCells.get(0).getText().equals(documentID)) {
				approvedStatus=rowCells.get(12).getText();
				break;
			}
		}
		return approvedStatus;
	}

	/**
	 * Returns invoice pending amount for specified documentID for invoices not in "Dispute" or "Solved" status.
	 * 
	 * @param driver: Stores current web page. Used to detect object presence in screen.
	 * @param documentID: Document ID to locate in invoices table.
	 *
	 * @return Pending amount for invoice found.
	 */
	public String getTblInvoicesRowAmount(WebDriver driver, String documentID) {
		String pendingAmount = "";
		// Table rows
		List<WebElement> tableRows = tblInvoices.findElements(By.tagName("tr"));
		// Row#1 columns
		List<WebElement> rowCells = tableRows.get(1).findElements(By.tagName("td"));
		// Click on first data row
		try {
			Utils.waitUntil_isClickable(driver, rowCells.get(1));
		} catch (TimeoutException e) {
			//If no invoices table, return empty string. 
			return pendingAmount;
		}		

		for (int i = 1; i < tableRows.size(); i++) {
			rowCells = tableRows.get(i).findElements(By.tagName("td"));
			if ((rowCells.get(11).getText().equals("Dispute") == false)
					&& (rowCells.get(11).getText().equals("Solved") == false)) {
				if (documentID.trim().length() == 0) {
					rowCells.get(0).click();
					pendingAmount = rowCells.get(6).getText().replace(",", "");
					break;
				} else if (rowCells.get(0).getText().equals(documentID)) {
					rowCells.get(0).click();
					pendingAmount = rowCells.get(6).getText().replace(",", "");
					break;
				}
			}
		}
		return pendingAmount;
	}

	/**
	 * Returns invoice disputed columnn for specified documentID.
	 * 
	 * @param driver: Stores current web page. Used to detect object presence in screen.
	 * @param documentID: Document ID to locate in invoices table.
	 *
	 * @return Disputed column for invoice found.
	 */
	public String getTblInvoices1stRowDispute(WebDriver driver, String documentID) {
		String disputed = "";
		
		// Table rows
		List<WebElement> tableRows = tblInvoices.findElements(By.tagName("tr"));
		// Row#1 columns
		List<WebElement> rowCells = tableRows.get(1).findElements(By.tagName("td"));
		// Click on first data row
		try {
			Utils.waitUntil_isClickable(driver, rowCells.get(1));
		} catch (TimeoutException e) {
			//If no invoices table, return empty string. 
			return disputed;
		}		

		for (int i = 1; i < tableRows.size(); i++) {
			rowCells = tableRows.get(i).findElements(By.tagName("td"));
			if (rowCells.get(0).getText().equals(documentID)) {
				rowCells.get(0).click();
				disputed = rowCells.get(11).getText().trim();
				break;
			}
		}
		return disputed;
	}

	/**
	 * Invokes click method for lstOption object
	 * 
	 */
	public void clickLstOption() {
		lstOption.click();
	}

	/**
	 * Invokes click method for lstOptionPayInvoice object
	 * 
	 */
	public void clickLstOptionPayInvoice() {
		lstOptionPayInvoice.click();
	}

	/**
	 * Invokes click method for lstOptionApproveInvoice object
	 * 
	 */
	public void clickLstOptionApproveInvoice() {
		lstOptionApproveInvoice.click();
	}

	/**
	 * Invokes click method for lstOptionDisputeInvoice object
	 * 
	 */
	public void clickLstOptionDisputeInvoice() {
		lstOptionDisputeInvoice.click();
	}

	/**
	 * Invokes click method for btnExecute object
	 * 
	 * @param driver: Stores current web page. Used to detect object presence in screen.
	 */
	public void clickBtnExecute(WebDriver driver) {
		Utils.waitUntil_isClickable(driver, btnExecute);
		btnExecute.click();
	}

	/**
	 * Invokes click method for btnConfirmOK object
	 * 
	 * @param driver: Stores current web page. Used to detect object presence in screen.
	 */
	public void clickBtnConfirmOK(WebDriver driver) throws InterruptedException {
		Utils.waitUntil_isClickable(driver, btnConfirmOK);
		btnConfirmOK.click();
		Thread.sleep(1000);
	}

	/**
	 * Invokes click method for btnRefresh object
	 * 
	 * @param driver: Stores current web page. Used to detect object presence in screen.
	 */
	public void clickBtnRefresh(WebDriver driver) {
		btnRefresh = driver.findElement(By.id("refreshicon"));
		btnRefresh.click();
	}

	/**
	 * Types in txtAmount object the text received in param "s".
	 * First waits for object presence in screen.
	 * 
	 * @param driver: Stores current web page. Used to detect object presence in screen.
	 * @param s: Text to write in object.
	 */
	public void setTxtAmount(WebDriver driver, String s) {
		Utils.waitUntil_isClickable(driver, txtAmount);
		txtAmount.clear();
		txtAmount.sendKeys(s);
	}

	/**
	 * Types in txtConfirmCode object the text received in param "s".
	 * First waits for object presence in screen.
	 * 
	 * @param driver: Stores current web page. Used to detect object presence in screen.
	 * @param s: Text to write in object.
	 */
	public void setTxtConfirmCode(WebDriver driver, String s) {
		Utils.waitUntil_isClickable(driver, txtConfirmCode);
		txtConfirmCode.clear();
		txtConfirmCode.sendKeys(s);
	}

	/**
	 * Types in txtConfirmPassword object the text received in param "s".
	 * First waits for object presence in screen.
	 * 
	 * @param driver: Stores current web page. Used to detect object presence in screen.
	 * @param s: Text to write in object.
	 */
	public void setTxtConfirmPassword(WebDriver driver, String s) {
		Utils.waitUntil_isClickable(driver, txtConfirmPassword);
		txtConfirmPassword.clear();
		txtConfirmPassword.sendKeys(s);
	}

	/**
	 * Types in txtNoteBox object the text received in param "s".
	 * First waits for object presence in screen.
	 * 
	 * @param driver: Stores current web page. Used to detect object presence in screen.
	 * @param s: Text to write in object.
	 */
	public void setTxtNoteBox(WebDriver driver, String s) {
		Utils.waitUntil_isClickable(driver, txtNoteBox);
		txtNoteBox.clear();
		txtNoteBox.sendKeys(s);
	}

	/**
	 * Types in txtDisputeAmount object the text received in param "s".
	 * First waits for object presence in screen.
	 * 
	 * @param driver: Stores current web page. Used to detect object presence in screen.
	 * @param s: Text to write in object.
	 */
	public void setTxtDisputeAmount(WebDriver driver, String s) {
		Utils.waitUntil_isClickable(driver, txtDisputeAmount);
		txtDisputeAmount.clear();
		txtDisputeAmount.sendKeys(s);
	}

	/**
	 * Used when dealing with amount entries.
	 * Returns text displayed by lblError object, without non standard characters.
	 * First waits for object presence in screen.
	 * 
	 * @param driver: Stores current web page. Used to detect object presence in screen.
	 * @return Text displayed in lblError object
	 */
	public String getLblErrorAmount(WebDriver driver) {
		Utils.waitUntil_isClickable(driver, lblError);
		String msg = Utils.normalizeString(lblError.getText().trim());
		return msg;
	}

	/**
	 * Used when dealing with Note entries.
	 * Returns text displayed by lblError object, without non standard characters.
	 * First waits for object presence in screen.
	 * 
	 * @param driver: Stores current web page. Used to detect object presence in screen.
	 * @return Text displayed in lblError object
	 */
	public String getLblErrorNoteTooShort(WebDriver driver) {
		Utils.waitUntil_isClickable(driver, lblError);
		String msg = Utils.normalizeString(lblError.getText().trim());
		return msg;
	}

	/**
	 * Returns the confirmation code from lblConfirmMsg object.
	 * First waits for object presence in screen.
	 * 
	 * @param driver: Stores current web page. Used to detect object presence in screen.
	 * @return Confirmation code as string displayed in lblConfirmMsg object
	 */
	public String getCodeFromConfirmMsg(WebDriver driver) {
		// Returns the code to enter in the fields
		Utils.waitUntil_isClickable(driver, lblConfirmMsg);
		String msg = Utils.normalizeString(lblConfirmMsg.getText().trim());
		int i = msg.indexOf("code is", 0);
		msg = msg.substring(i + 8, i + 16); // i+8 = "is ". i+16 = "code is " + 8 digits of code
		return msg;
	}

/*	private void moveToElement(WebDriver driver, WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

		Actions actions = new Actions(driver);
		actions.moveToElement(element);
		actions.perform();

	}
*/}
