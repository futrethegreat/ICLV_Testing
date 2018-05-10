	package Pages;
	
	import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ctc.Utils;
	
	public class ICLVPayablesPage {
		@FindBy(id = "ot80")
		public WebElement tblInvoices;
// FW6		@FindBy(xpath = "//*[@id=\"selectedActTypeid_chosen\"]/a/div/b")
		@FindBy(xpath = "//*[@id=\"selectedActTypeid_chosen\"]")
		public WebElement lstOption;
		@FindBy(xpath = "//*[@id=\"selectedActTypeid_chosen\"]/div/ul/li[1]")
		public WebElement lstOptionPayInvoice;
		@FindBy(xpath = "//*[@id=\"selectedActTypeid_chosen\"]/div/ul/li[2]")
		public WebElement lstOptionApproveInvoice;
		@FindBy(xpath = "//*[@id=\"selectedActTypeid_chosen\"]/div/ul/li[3]")
		public WebElement lstOptionDisputeInvoice;
		@FindBy(id = "PAYAMT")
		public WebElement txtAmount;
		@FindBy(id = "execute1")
		public WebElement btnExecute;
		@FindBy(id = "confirmok")
		public WebElement btnConfirmOK;
		@FindBy(id = "refreshicon")
		public WebElement btnRefresh;
		@FindBy(xpath = "//*[@id=\"asform\"]/div/div[3]")
		public WebElement lblError;
		@FindBy(id = "confirmmsg")
		public WebElement lblConfirmMsg;
		@FindBy(id = "confirmcode")
		public WebElement txtConfirmCode;
		@FindBy(id = "confirmpassword")
		public WebElement txtConfirmPassword;
		@FindBy(id = "note")
		public WebElement txtNoteBox;
		
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

		public String clickTblInvoices1stRowNotDisputed(WebDriver driver) {
			// Table rows
			List<WebElement> tableRows = tblInvoices.findElements(By.tagName("tr"));
			// Row#1 columns
			List<WebElement> rowCells = tableRows.get(1).findElements(By.tagName("td"));
			// Click on first data row
			Utils.waitUntil_isClickable(driver, rowCells.get(1));
			
			for (int i=1;i<tableRows.size();i++) {
				rowCells = tableRows.get(i).findElements(By.tagName("td"));
//				Utils.consoleMsg(rowCells.get(0).getText() + " - " + rowCells.get(11).getText());
				if (rowCells.get(11).getText().equals("Dispute") == false) {
					rowCells.get(0).click();
					break;
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
			
			return rowCells.get(11).getText();
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
		
		public String getLblErrorAmountTooBig(WebDriver driver) {
			//Returns Please enter a numeric payment amount between zero and the invoice exposure
			Utils.waitUntil_isClickable(driver, lblError);
			String msg = Utils.normalizeString(lblError.getText().trim());
			int i = msg.indexOf("exposure", 0);
//			Utils.consoleMsg("Mensaje error y posicion de texto: " + i + msg);
			msg = msg.substring(0, i+"exposure".length());
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
//			Utils.consoleMsg(7"Code is: " + msg);
			return msg;
		}
		
		private void moveToElement(WebDriver driver, WebElement element) {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

			Actions actions = new Actions(driver);
			actions.moveToElement(element);
			actions.perform();

		}
	}
