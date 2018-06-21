package Pages;
	
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.ctc.Utils;
	
/**
 * Implements as objects the Selenium objects of "To Pay" page when it is used to "Dispute" invoices. 
 * Implements class methods to manage Selenium objects.
 * 
 * @author DavidSauce
 *
 */
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
		
		PageFactory.initElements(driver, this);
		Utils.waitUntil_isClickable(driver, tblInvoices);
	}

	/**
	 * Invokes click method for lstDispute object
	 */
	public void clickLstDispute() {
		lstDispute.click();
	}

	/**
	 * Invokes click method for lstDisputeApproved object
	 */
	public void clickLstDisputeApproved() {
		lstDisputeApproved.click();
	}
	
	/**
	 * Invokes click method for lstDisputeReject object
	 */
	public void clickLstDisputeReject() {
		lstDisputeReject.click();
	}
	
	/**
	 * Invokes click method for btnExecute object
	 */
	public void clickBtnExecute(WebDriver driver) {
		Utils.waitUntil_isClickable(driver, btnExecute);
		btnExecute.click();
	}
	
	/**
	 * Invokes click method for btnConfirmOK object
	 */
	public void clickBtnConfirmOK(WebDriver driver) {
		Utils.waitUntil_isClickable(driver, btnConfirmOK);
		btnConfirmOK.click();
	}

	/**
	 * Invokes click method for btnRefresh object
	 */
	public void clickBtnRefresh(WebDriver driver) {
		btnRefresh = driver.findElement(By.id("refreshicon"));	
		btnRefresh.click();
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
	 * Used when dealing with amount entries.
	 * Returns text displayed by lblError object, without non standard characters.
	 * First waits for object presence in screen.
	 * 
	 * @param driver: Stores current web page. Used to detect object presence in screen.
	 * @return Text displayed in lblError object
	 */
	public String getLblErrorAmount(WebDriver driver) {
		//Returns Please enter a numeric payment amount between zero and the invoice exposure
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
		//Returns Please state the reason for the dispute
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
		Utils.waitUntil_isClickable(driver, lblConfirmMsg);
		String msg = Utils.normalizeString(lblConfirmMsg.getText().trim());
		int i = msg.indexOf("code is", 0);
		msg = msg.substring(i+8, i+16); //i+8 = "is ". i+16 = "code is " + 8 digits of code
		return msg;
	}
}
