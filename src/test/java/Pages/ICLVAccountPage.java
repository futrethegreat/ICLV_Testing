package Pages;

import java.math.BigDecimal;
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
 * Implements as objects the Selenium objects of "Account" page. 
 * Implements class methods to manage Selenium objects.
 * 
 * @author DavidSauce
 *
 */
public class ICLVAccountPage {

	@FindBys(
		{ 
			@FindBy(xpath = ".//*[@id=\"212|65|PE20170040938\"]/div"), 
			@FindBy(id = "ot30") 
		}
	)
	public WebElement tblBankAccounts;

	@FindBys(
		{ 
			@FindBy(xpath = ".//*[@id=\"212|65|PE20170040938\"]/div"), 
			@FindBy(id = "ot446") 
		}
	)
	public WebElement tblTRFIPayments;

	@FindBys(
		{ 
			@FindBy(xpath = ".//*[@id=\"212|65|PE20170040938\"]/div"),
			@FindBy(xpath = ".//*[@id=\"triggertask\"]/div"),
			@FindBy(xpath = ".//*[@id=\"selectedActTypeid_chosen\"]") 
		}
	)
	public WebElement lstOptions;

	@FindBys(
		{ 
			@FindBy(xpath = ".//*[@id=\"212|65|PE20170040938\"]/div"),
			@FindBy(xpath = ".//*[@id=\"triggertask\"]/div"),
			@FindBy(xpath = ".//*[@id=\"selectedActTypeid_chosen\"]/div/ul/li[1]") 
		}
	)
	public WebElement lstOptionsWithdraw;

	@FindBys(
		{ 
			@FindBy(xpath = ".//*[@id=\"212|65|PE20170040938\"]/div"),
			@FindBy(xpath = ".//*[@id=\"triggertask\"]/div"),
			@FindBy(xpath = ".//*[@id=\"selectedActTypeid_chosen\"]/div/ul/li[2]") 
		}
	)
	public WebElement lstOptionsProgram;

	@FindBys(
		{ 
			@FindBy(xpath = ".//*[@id=\"212|65|PE20170040938\"]/div"),
			@FindBy(xpath = ".//*[@id=\"triggertask\"]/div"), 
			@FindBy(id = "execute1") 
		}
	)
	public WebElement btnExecute;

	@FindBys(
		{ 
			@FindBy(xpath = ".//*[@id=\"212|65|PE20170040938\"]/div"),
			@FindBy(xpath = ".//*[@id=\"triggertask\"]/div"), 
			@FindBy(id = "confirmok") 
		}
	)
	public WebElement btnConfirmOK;

	@FindBys(
		{ 
			@FindBy(xpath = ".//*[@id=\"212|65|PE20170040938\"]/div"),
			@FindBy(xpath = ".//*[@id=\"triggertask\"]/div"), 
			@FindBy(xpath = ".//*[@id=\"asform\"]/div/div[3]") 
		}
	)
	public WebElement lblError;

	@FindBys(
		{ 
			@FindBy(xpath = ".//*[@id=\"212|65|PE20170040938\"]/div"),
			@FindBy(xpath = ".//*[@id=\"triggertask\"]/div"), 
			@FindBy(id = "confirmmsg") 
		}
	)
	public WebElement lblConfirmMsg;

	@FindBys(
		{ 
			@FindBy(xpath = ".//*[@id=\"212|65|PE20170040938\"]/div"),
			@FindBy(xpath = ".//*[@id=\"triggertask\"]/div"), 
			@FindBy(id = "confirmcode") 
		}
	)
	public WebElement txtConfirmCode;

	@FindBys(
		{ 
			@FindBy(xpath = ".//*[@id=\"212|65|PE20170040938\"]/div"), 
			@FindBy(xpath = ".//*[@id=\"triggertask\"]/div"), 
			@FindBy(id = "confirmpassword") 
		}
	)
	public WebElement txtConfirmPassword;

	@FindBys(
		{ 
			@FindBy(xpath = ".//*[@id=\"212|65|PE20170040938\"]/div"),
			@FindBy(xpath = ".//*[@id=\"triggertask\"]/div"), 
			@FindBy(xpath = ".//*[@id=\"AMT\"]")
		}
	)
	public WebElement txtAmount;

	public ICLVAccountPage(WebDriver driver) throws TimeoutException {
		PageFactory.initElements(driver, this);
		Utils.waitUntil_isClickable(driver, tblBankAccounts);
	}

	/**
	 * Invokes click method for lstOption object
	 */
	public void clickLstOptions() {
		lstOptions.click();
	}

	/**
	 * Invokes click method for lstOptionsWithdraw object
	 */
	public void clickLstOptionsWithdraw() {
		lstOptionsWithdraw.click();
	}

	/**
	 * Invokes click method for lstOptionsProgram object
	 */
	public void clickLstOptionsProgram() {
		lstOptionsProgram.click();
	}

	/**
	 * Invokes click method for btnExecute object
	 * 
	 * @param driver
	 */
	public void clickBtnExecute(WebDriver driver) {
		Utils.waitUntil_isClickable(driver, btnExecute);
		btnExecute.click();
	}

	/**
	 * Invokes click method for btnConfirmOK object
	 * 
	 * @param driver
	 */
	public void clickBtnConfirmOK(WebDriver driver) {
		Utils.waitUntil_isClickable(driver, btnConfirmOK);
		btnConfirmOK.click();
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
	 * Returns text displayed by lblError object, without non standard characters.
	 * First waits for object presence in screen.
	 * 
	 * @param driver: Stores current web page. Used to detect object presence in screen.
	 * @return Text displayed in lblError object
	 */
	public String getLblError(WebDriver driver) {
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
		Utils.waitUntil_isPresent(driver, lblConfirmMsg);
		String msg = Utils.normalizeString(lblConfirmMsg.getText().trim());
		int i = msg.indexOf("code is", 0);
		msg = msg.substring(i + 8, i + 16); // i+8 = "is ". i+16 = "code is " + 8 digits of code
		return msg;
	}

	/**
	 * Returns Account number for first account with saldo of "currencyID" and clicks
	 * in the row
	 * 
	 * @param driver: Stores current web page. Used to detect object presence in screen.
	 * @param currencyID: Currency to locate in accounts table.
	 *
	 * @return Account number as string 
	 */
	public String getAccountNumberForCurrencyID(WebDriver driver, String currencyID) {
		String auxCurrencyID;
		BigDecimal auxSaldo;

		// Table rows
		List<WebElement> tableRows = tblBankAccounts.findElements(By.tagName("tr"));
		// Row#1 columns
		List<WebElement> rowCells = tableRows.get(1).findElements(By.tagName("td"));
		// Click in first data row
		Utils.waitUntil_isClickable(driver, rowCells.get(1));

		for (int i = 1; i < tableRows.size(); i++) {
			rowCells = tableRows.get(i).findElements(By.tagName("td"));
			auxCurrencyID = rowCells.get(0).getText();
			auxSaldo = new BigDecimal(rowCells.get(2).getText().replace(",", "")); //removes , before to convert to BigDecimal

			if (auxCurrencyID.equals(currencyID) && (auxSaldo.compareTo(BigDecimal.ZERO) > 0)) {
				// if currency is equal and account has saldo, it is chosen.
				rowCells.get(0).click();
				break;
			}
		}
		return rowCells.get(1).getText();
	}

	/**
	 * Returns Saldo for account accountID and clicks in the row
	 * 
	 * @param driver: Stores current web page. Used to detect object presence in screen.
	 * @param accountID: Account to locate in accounts table
	 *
	 * @return Saldo of account as string
	 */
	public String getSaldoForAccountID(WebDriver driver, String accountID) {
		String auxAccountID;

		// Table rows
		List<WebElement> tableRows = tblBankAccounts.findElements(By.tagName("tr"));
		// Row#1 columns
		List<WebElement> rowCells = tableRows.get(1).findElements(By.tagName("td"));
		// Click in first data row
		Utils.waitUntil_isClickable(driver, rowCells.get(1));

		for (int i = 1; i < tableRows.size(); i++) {
			rowCells = tableRows.get(i).findElements(By.tagName("td"));
			auxAccountID = rowCells.get(1).getText();

			if (auxAccountID.equals(accountID)) {
				rowCells.get(0).click();
				break;
			}
		}
		//removes , to allow convert to BigDecimal
		return rowCells.get(2).getText().replace(",", ""); 
	}
}
