	package Pages;
	
	import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.ctc.Utils;
	
	public class ICLVDisputesPage {

		@FindBys(
			{
				@FindBy(xpath=".//*[@id=\"208|65|PE20170040938\"]/div"),
				@FindBy(id="ot80")
			}
		)
		public WebElement tblInvoices;

		@FindBys(
			{
				@FindBy(xpath=".//*[@id=\"208|65|PE20170040938\"]/div"),
				@FindBy(xpath=".//*[@id=\"triggertask\"]/div[1]"),
				@FindBy(xpath=".//*[@id=\"selectedActTypeid_chosen\"]")
			}
		)
		public WebElement lstDispute;

		@FindBys(
			{
				@FindBy(xpath=".//*[@id=\"208|65|PE20170040938\"]/div"),
				@FindBy(xpath=".//*[@id=\"triggertask\"]/div[1]"),
				@FindBy(xpath=".//*[@id=\"selectedActTypeid_chosen\"]/div/ul/li[1]")
			}
		)
		public WebElement lstDisputeApproved;
		
		@FindBys(
			{
				@FindBy(xpath=".//*[@id=\"208|65|PE20170040938\"]/div"),
				@FindBy(xpath=".//*[@id=\"triggertask\"]/div[1]"),
				@FindBy(xpath=".//*[@id=\"selectedActTypeid_chosen\"]/div/ul/li[2]")
			}
		)
		public WebElement lstDisputeReject;

		@FindBys(
			{
				@FindBy(xpath=".//*[@id=\"208|65|PE20170040938\"]/div"),
				@FindBy(id = "execute1")
			}
		)
		public WebElement btnExecute;

		@FindBys(
			{
				@FindBy(xpath=".//*[@id=\"208|65|PE20170040938\"]/div"),
				@FindBy(id = "confirmok")
			}
		)
		public WebElement btnConfirmOK;

		@FindBys(
			{
				@FindBy(xpath=".//*[@id=\"208|65|PE20170040938\"]/div"),
				@FindBy(id = "refreshicon")
			}
		)
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
		public WebElement lblConfirmMsg;

		@FindBys(
			{
				@FindBy(xpath=".//*[@id=\"208|65|PE20170040938\"]/div"),
				@FindBy(id = "confirmcode")
			}
		)
		public WebElement txtConfirmCode;

		@FindBys(
			{
				@FindBy(xpath=".//*[@id=\"208|65|PE20170040938\"]/div"),
				@FindBy(id = "confirmpassword")
			}
		)
		public WebElement txtConfirmPassword;

		@FindBys(
			{
				@FindBy(xpath=".//*[@id=\"208|65|PE20170040938\"]/div"),
				@FindBy(id = "note")
			}
		)
		public WebElement txtNoteBox;

		public ICLVDisputesPage(WebDriver driver) throws TimeoutException {
			
			Utils.waitUntil_isClickable(driver, By.id("ot80"));
			PageFactory.initElements(driver, this);
		}
	
		public void clickLstDispute() {
			lstDispute.click();
		}
		public void clickLstDisputeApproved() {
			lstDisputeApproved.click();
		}
		public void clickLstDisputeReject() {
			lstDisputeReject.click();
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
	}
