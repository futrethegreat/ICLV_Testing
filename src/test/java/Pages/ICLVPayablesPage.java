	package Pages;
	
	import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ctc.Utils;
	
	public class ICLVPayablesPage {
		@FindBy(id = "ot80")
		public WebElement tblInvoices;
		@FindBy(xpath = "//*[@id=\"selectedActTypeid_chosen\"]/a/div/b")
		public WebElement lstOption;
		@FindBy(xpath = "//*[@id=\"selectedActTypeid_chosen\"]/div/ul/li[1]")
		public WebElement lstOptionPayInvoice;
		@FindBy(id = "PAYAMT")
		public WebElement txtAmount;
		@FindBy(id = "execute1")
		public WebElement btnExecute;
		@FindBy(id = "confirmok")
		public WebElement btnConfirmOK;
		@FindBy(id = "refreshicon")
		public WebElement btnRefresh;
		@FindBy(xpath = "//*[@id=\"asform\"]/div/div[3]")
		public WebElement lblErrorAmountTooBig;
		@FindBy(id = "confirmmsg")
		public WebElement lblConfirmMsg;
		@FindBy(id = "confirmcode")
		public WebElement txtConfirmCode;
		@FindBy(id = "confirmpassword")
		public WebElement txtConfirmPassword;
		
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
	
		public String getTblInvoices1stRowAmount(WebDriver driver) {
			// Table rows
			List<WebElement> tableRows = tblInvoices.findElements(By.tagName("tr"));
			// Row#1 columns
			List<WebElement> rowCells = tableRows.get(1).findElements(By.tagName("td"));
			// Click on first data row
			Utils.waitUntil_isClickable(driver, rowCells.get(1));
			return rowCells.get(6).getText().replace(",", "");
		}
		
		public void clickLstOption() {
			lstOption.click();
		}
		public void clickLstOptionPayInvoice() {
			lstOptionPayInvoice.click();
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
			Utils.waitUntil_isClickable(driver, lblErrorAmountTooBig);
			String msg = Utils.normalizeString(lblErrorAmountTooBig.getText().trim());
			int i = msg.indexOf("exposure", 0);
//			Utils.consoleMsg("Mensaje error y posicion de texto: " + i + msg);
			msg = msg.substring(0, i+"exposure".length());
			return msg;
		}

		public String getCodeFromConfirmMsg(WebDriver driver) {
			//Returns the code to enter in the fields
			Utils.waitUntil_isClickable(driver, lblConfirmMsg);
			String msg = Utils.normalizeString(lblConfirmMsg.getText().trim());
			int i = msg.indexOf("is", 0);
			msg = msg.substring(i+3, i+11); //i+3 = "is ". i+11 = "is " + 8 digits of code
//			Utils.consoleMsg(7"Code is: " + msg);
			return msg;
		}
	}
