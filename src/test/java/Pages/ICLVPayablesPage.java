	package Pages;
	
	import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.ctc.Utils;
	
	public class ICLVPayablesPage {

		@FindBys(
			{
				@FindBy(xpath=".//*[@id=\"208|65|PE20170040938\"]/div"),
				@FindBy(id="ot80")
			}
		)
//		@FindBy(id = "ot80")
		public WebElement tblInvoices;

		@FindBys(
			{
				@FindBy(xpath=".//*[@id=\"208|65|PE20170040938\"]/div"),
				@FindBy(xpath=".//*[@id=\"selectedActTypeid_chosen\"]")
			}
		)
//		@FindBy(xpath = "//*[@id=\"selectedActTypeid_chosen\"]")
		public WebElement lstOption;

		@FindBys(
			{
				@FindBy(xpath=".//*[@id=\"208|65|PE20170040938\"]/div"),
				@FindBy(xpath=".//*[@id=\"selectedActTypeid_chosen\"]/div/ul/li[1]")
			}
		)
//		@FindBy(xpath = "//*[@id=\"selectedActTypeid_chosen\"]/div/ul/li[1]")
		public WebElement lstOptionPayInvoice;
		
		@FindBys(
			{
				@FindBy(xpath=".//*[@id=\"208|65|PE20170040938\"]/div"),
				@FindBy(xpath=".//*[@id=\"selectedActTypeid_chosen\"]/div/ul/li[2]")
			}
		)
//		@FindBy(xpath = "//*[@id=\"selectedActTypeid_chosen\"]/div/ul/li[2]")
		public WebElement lstOptionApproveInvoice;

		@FindBys(
			{
				@FindBy(xpath=".//*[@id=\"208|65|PE20170040938\"]/div"),
				@FindBy(xpath=".//*[@id=\"selectedActTypeid_chosen\"]/div/ul/li[3]")
			}
		)
//		@FindBy(xpath = "//*[@id=\"selectedActTypeid_chosen\"]/div/ul/li[3]")
		public WebElement lstOptionDisputeInvoice;

		@FindBys(
			{
				@FindBy(xpath=".//*[@id=\"208|65|PE20170040938\"]/div"),
				@FindBy(id = "PAYAMT")
			}
		)
//		@FindBy(id = "PAYAMT")
		public WebElement txtAmount;

		@FindBys(
			{
				@FindBy(xpath=".//*[@id=\"208|65|PE20170040938\"]/div"),
				@FindBy(id = "execute1")
			}
		)
//		@FindBy(id = "execute1")
		public WebElement btnExecute;

		@FindBys(
			{
				@FindBy(xpath=".//*[@id=\"208|65|PE20170040938\"]/div"),
				@FindBy(id = "confirmok")
			}
		)
//		@FindBy(id = "confirmok")
		public WebElement btnConfirmOK;

		@FindBys(
			{
				@FindBy(xpath=".//*[@id=\"208|65|PE20170040938\"]/div"),
				@FindBy(id = "refreshicon")
			}
		)
//		@FindBy(id = "refreshicon")
		public WebElement btnRefresh;

		@FindBys(
			{
				@FindBy(xpath=".//*[@id=\"208|65|PE20170040938\"]/div"),
				@FindBy(xpath = ".//*[@id=\"asform\"]/div/div[3]")
			}
		)
//		@FindBy(xpath = "//*[@id=\"asform\"]/div/div[3]")
		public WebElement lblError;

		@FindBys(
			{
				@FindBy(xpath=".//*[@id=\"208|65|PE20170040938\"]/div"),
				@FindBy(id = "confirmmsg")
			}
		)
//		@FindBy(id = "confirmmsg")
		public WebElement lblConfirmMsg;

		@FindBys(
			{
				@FindBy(xpath=".//*[@id=\"208|65|PE20170040938\"]/div"),
				@FindBy(id = "confirmcode")
			}
		)
//		@FindBy(id = "confirmcode")
		public WebElement txtConfirmCode;

		@FindBys(
			{
				@FindBy(xpath=".//*[@id=\"208|65|PE20170040938\"]/div"),
				@FindBy(id = "confirmpassword")
			}
		)
//		@FindBy(id = "confirmpassword")
		public WebElement txtConfirmPassword;

		@FindBys(
			{
				@FindBy(xpath=".//*[@id=\"208|65|PE20170040938\"]/div"),
				@FindBy(id = "note")
			}
		)
//		@FindBy(id = "note")
		public WebElement txtNoteBox;

		@FindBys(
			{
				@FindBy(xpath=".//*[@id=\"208|65|PE20170040938\"]/div"),
				@FindBy(id = "disputeamount")
			}
		)
//		@FindBy(id = "disputeamount")
		public WebElement txtDisputeAmount;
		
		public ICLVPayablesPage(WebDriver driver) throws TimeoutException {
			
			Utils.waitUntil_isClickable(driver, By.id("ot80"));
	//		Utils.waitUntil_isClickable(driver, tblInvoices);
			PageFactory.initElements(driver, this);
		}
	
		public void clickTblInvoices1stRow(WebDriver driver) {
			// Table rows
			List<WebElement> tableRows = tblInvoices.findElements(By.tagName("tr"));
			// Row#1 columns
			List<WebElement> rowCells = tableRows.get(1).findElements(By.tagName("td"));
			// Click on first data row
			Utils.waitUntil_isClickable(driver, rowCells.get(1));
			rowCells.get(1).click();
		}

		public String clickTblInvoices1stRowNotDisputed(WebDriver driver, String supplier) {
			String supplierInTable = "";
			
			// Table rows
			List<WebElement> tableRows = tblInvoices.findElements(By.tagName("tr"));
			// Row#1 columns
			List<WebElement> rowCells = tableRows.get(1).findElements(By.tagName("td"));
			// Click on first data row
			Utils.waitUntil_isClickable(driver, rowCells.get(1));
			
			for (int i=1;i<tableRows.size();i++) {
				rowCells = tableRows.get(i).findElements(By.tagName("td"));
//				Utils.consoleMsg(rowCells.get(0).getText() + " - " + rowCells.get(11).getText());
				
				supplierInTable=(rowCells.get(2).getText().trim().length()==0) ? supplierInTable : rowCells.get(2).getText().trim();
				
				if ((rowCells.get(11).getText().equals("Dispute") == false) && (rowCells.get(11).getText().equals("Solved") == false)) {
					if (supplier.trim().length() == 0) { 
						rowCells.get(0).click();
						break;
//					} else if (rowCells.get(2).getText().equals(supplier)) {
					} else if (supplierInTable.equals(supplier)) {
//						Utils.consoleMsg("My supplier: " + supplierInTable);
						rowCells.get(0).click();
						break;
					}
				}
			}
			return rowCells.get(0).getText();
		}
	
		public String getTblInvoices1stRowAmount(WebDriver driver) {
			// Table rows
			List<WebElement> tableRows = tblInvoices.findElements(By.tagName("tr"));
			// Row#1 columns
			List<WebElement> rowCells = tableRows.get(1).findElements(By.tagName("td"));
			// Click on first data row
			Utils.waitUntil_isClickable(driver, rowCells.get(1));
			return rowCells.get(6).getText().replace(",", "");
		}

		public String getTblInvoicesRowAmount(WebDriver driver, String documentID) {
			// Table rows
			List<WebElement> tableRows = tblInvoices.findElements(By.tagName("tr"));
			// Row#1 columns
			List<WebElement> rowCells = tableRows.get(1).findElements(By.tagName("td"));
			// Click on first data row
			Utils.waitUntil_isClickable(driver, rowCells.get(1));

			for (int i=1;i<tableRows.size();i++) {
				rowCells = tableRows.get(i).findElements(By.tagName("td"));
//				Utils.consoleMsg(rowCells.get(0).getText() + " - " + rowCells.get(11).getText());
				if ((rowCells.get(11).getText().equals("Dispute") == false) && (rowCells.get(11).getText().equals("Solved") == false)) {
					if (documentID.trim().length() == 0) { 
						rowCells.get(0).click();
						break;
					} else if (rowCells.get(0).getText().equals(documentID)) {
						rowCells.get(0).click();
						break;
					}
				}
			}
			return rowCells.get(6).getText().replace(",", "");
		}

		public String getTblInvoices1stRowDispute(WebDriver driver, String documentID) {
			// Table rows
			List<WebElement> tableRows = tblInvoices.findElements(By.tagName("tr"));
			// Row#1 columns
			List<WebElement> rowCells = tableRows.get(1).findElements(By.tagName("td"));
			// Click on first data row
			Utils.waitUntil_isClickable(driver, rowCells.get(1));
			
			for (int i=1;i<tableRows.size();i++) {
				rowCells = tableRows.get(i).findElements(By.tagName("td"));
				if (rowCells.get(0).getText().equals(documentID)) {
					rowCells.get(0).click();
					break;
				}
			}
			return rowCells.get(11).getText().trim();
		}

		public void clickLstOption() {
			lstOption.click();
		}
		public void clickLstOptionPayInvoice() {
			lstOptionPayInvoice.click();
		}
		public void clickLstOptionApproveInvoice() {
			lstOptionApproveInvoice.click();
		}
		public void clickLstOptionDisputeInvoice() {
			lstOptionDisputeInvoice.click();
		}
		
		public void setTxtAmount(WebDriver driver, String s) {
	//		Utils.waitUntil_isClickable(driver, By.id("PAYAMT"));
			Utils.waitUntil_isClickable(driver, txtAmount);
			txtAmount.clear();
			txtAmount.sendKeys(s);
		}
		public void setTxtConfirmCode(WebDriver driver, String s) {
			Utils.waitUntil_isClickable(driver, txtConfirmCode);
			txtConfirmCode.clear();
			txtConfirmCode.sendKeys(s);
		}
		public void setTxtConfirmPassword(WebDriver driver, String s) {
			Utils.waitUntil_isClickable(driver, txtConfirmPassword);
			txtConfirmPassword.clear();
			txtConfirmPassword.sendKeys(s);
		}
		public void setTxtNoteBox(WebDriver driver, String s) {
			Utils.waitUntil_isClickable(driver, txtNoteBox);
			txtNoteBox.clear();
			txtNoteBox.sendKeys(s);
		}
		public void setTxtDisputeAmount(WebDriver driver, String s) {
			Utils.waitUntil_isClickable(driver, txtDisputeAmount);
			txtDisputeAmount.clear();
			txtDisputeAmount.sendKeys(s);
		}
		
		public void clickBtnExecute(WebDriver driver) {
			Utils.waitUntil_isClickable(driver, btnExecute);
			btnExecute.click();
		}
	
		public void clickBtnConfirmOK(WebDriver driver) {
			Utils.waitUntil_isClickable(driver, btnConfirmOK);
			btnConfirmOK.click();
		}
	
		public void clickBtnRefresh(WebDriver driver) {
			btnRefresh = driver.findElement(By.id("refreshicon"));	
			btnRefresh.click();
		}
		
		public String getLblErrorAmount(WebDriver driver) {
			//Returns Please enter a numeric payment amount between zero and the invoice exposure
			Utils.waitUntil_isClickable(driver, lblError);
			String msg = Utils.normalizeString(lblError.getText().trim());
//			int i = msg.indexOf("exposure", 0);
//			Utils.consoleMsg("Mensaje error y posicion de texto: " + i + msg);
//			msg = msg.substring(0, i+"exposure".length());
			return msg;
		}

		public String getLblErrorNoteTooShort(WebDriver driver) {
			//Returns Please state the reason for the dispute
			Utils.waitUntil_isClickable(driver, lblError);
			String msg = Utils.normalizeString(lblError.getText().trim());
			return msg;
		}

		public String getCodeFromConfirmMsg(WebDriver driver) {
			//Returns the code to enter in the fields
			Utils.waitUntil_isClickable(driver, lblConfirmMsg);
			String msg = Utils.normalizeString(lblConfirmMsg.getText().trim());
			int i = msg.indexOf("code is", 0);
			msg = msg.substring(i+8, i+16); //i+8 = "is ". i+16 = "code is " + 8 digits of code
//			Utils.consoleMsg("Code is: " + msg);
			return msg;
		}
		
		private void moveToElement(WebDriver driver, WebElement element) {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

			Actions actions = new Actions(driver);
			actions.moveToElement(element);
			actions.perform();

		}
	}
