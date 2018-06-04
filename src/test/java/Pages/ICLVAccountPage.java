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

public class ICLVAccountPage {

	@FindBys({ @FindBy(xpath = ".//*[@id=\"212|65|PE20170040938\"]/div"), @FindBy(id = "ot30") })
	public WebElement tblBankAccounts;

	@FindBys({ @FindBy(xpath = ".//*[@id=\"212|65|PE20170040938\"]/div"), @FindBy(id = "ot446") })
	public WebElement tblTRFIPayments;

	@FindBys({ @FindBy(xpath = ".//*[@id=\"212|65|PE20170040938\"]/div"), // *[@id="212|65|PE20170040938"]/div
			@FindBy(xpath = ".//*[@id=\"triggertask\"]/div"),
			@FindBy(xpath = ".//*[@id=\"selectedActTypeid_chosen\"]") })
	public WebElement lstOptions;

	@FindBys({ @FindBy(xpath = ".//*[@id=\"212|65|PE20170040938\"]/div"),
			@FindBy(xpath = ".//*[@id=\"triggertask\"]/div"),
			@FindBy(xpath = ".//*[@id=\"selectedActTypeid_chosen\"]/div/ul/li[1]") })
	public WebElement lstOptionsWithdraw;

	@FindBys({ @FindBy(xpath = ".//*[@id=\"212|65|PE20170040938\"]/div"),
			@FindBy(xpath = ".//*[@id=\"triggertask\"]/div"),
			@FindBy(xpath = ".//*[@id=\"selectedActTypeid_chosen\"]/div/ul/li[2]") })
	public WebElement lstOptionsProgram;

	@FindBys(
		{ 
			@FindBy(xpath = ".//*[@id=\"212|65|PE20170040938\"]/div"),
			@FindBy(xpath = ".//*[@id=\"triggertask\"]/div"), 
			@FindBy(id = "execute1") 
		})
	public WebElement btnExecute;

	@FindBys(
		{ 
			@FindBy(xpath = ".//*[@id=\"212|65|PE20170040938\"]/div"),
			@FindBy(xpath = ".//*[@id=\"triggertask\"]/div"), 
			@FindBy(id = "confirmok") 
		})
	public WebElement btnConfirmOK;

	@FindBys(
		{ 
			@FindBy(xpath = ".//*[@id=\"212|65|PE20170040938\"]/div"),
			@FindBy(xpath = ".//*[@id=\"triggertask\"]/div"), 
			@FindBy(xpath = ".//*[@id=\"asform\"]/div/div[3]") 
		})
	public WebElement lblError;

	@FindBys(
		{ 
			@FindBy(xpath = ".//*[@id=\"212|65|PE20170040938\"]/div"),
			@FindBy(xpath = ".//*[@id=\"triggertask\"]/div"), 
			@FindBy(id = "confirmmsg") 
		})
	public WebElement lblConfirmMsg;

	@FindBys(
		{ 
			@FindBy(xpath = ".//*[@id=\"212|65|PE20170040938\"]/div"),
			@FindBy(xpath = ".//*[@id=\"triggertask\"]/div"), 
			@FindBy(id = "confirmcode") 
		})
	public WebElement txtConfirmCode;

	@FindBys(
		{ 
			@FindBy(xpath = ".//*[@id=\"212|65|PE20170040938\"]/div"), 
			@FindBy(xpath = ".//*[@id=\"triggertask\"]/div"), 
			@FindBy(id = "confirmpassword") 
		})
	public WebElement txtConfirmPassword;

	@FindBys(
		{ 
			@FindBy(xpath = ".//*[@id=\"212|65|PE20170040938\"]/div"),
			@FindBy(xpath = ".//*[@id=\"triggertask\"]/div"), 
			@FindBy(xpath = ".//*[@id=\"AMT\"]")
		})
	public WebElement txtAmount;

	public ICLVAccountPage(WebDriver driver) throws TimeoutException {
		Utils.waitUntil_isClickable(driver, By.id("ot30"));
		PageFactory.initElements(driver, this);
	}

	public void clickLstOptions() {
		lstOptions.click();
	}

	public void clickLstOptionsWithdraw() {
		lstOptionsWithdraw.click();
	}

	public void clickLstOptionsProgram() {
		lstOptionsProgram.click();
	}

	public void clickBtnExecute(WebDriver driver) {
		Utils.waitUntil_isClickable(driver, btnExecute);
		btnExecute.click();
	}

	public void clickBtnConfirmOK(WebDriver driver) {
		Utils.waitUntil_isClickable(driver, btnConfirmOK);
		btnConfirmOK.click();
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


	public void setTxtAmount(WebDriver driver, String s) {
		Utils.waitUntil_isClickable(driver, txtAmount);
		txtAmount.clear();
		txtAmount.sendKeys(s);
	}

	public String getLblError(WebDriver driver) {
		// Returns Please state the reason for the dispute
		Utils.waitUntil_isClickable(driver, lblError);
		String msg = Utils.normalizeString(lblError.getText().trim());
		return msg;
	}

	public String getCodeFromConfirmMsg(WebDriver driver) {
		// Returns the code to enter in the fields
		Utils.waitUntil_isClickable(driver, lblConfirmMsg);
		String msg = Utils.normalizeString(lblConfirmMsg.getText().trim());
		int i = msg.indexOf("code is", 0);
		msg = msg.substring(i + 8, i + 16); // i+8 = "is ". i+16 = "code is " + 8 digits of code
		return msg;
	}

	/**
	 * Returns Account number for first account with saldo of currencyID and clicks
	 * on the row
	 * 
	 * @param driver
	 * @param currencyID
	 *
	 * @return
	 */
	public String getAccountNumberForCurrencyID(WebDriver driver, String currencyID) {
		String auxCurrencyID;
		BigDecimal auxSaldo;

		// Table rows
		List<WebElement> tableRows = tblBankAccounts.findElements(By.tagName("tr"));
		// Row#1 columns
		List<WebElement> rowCells = tableRows.get(1).findElements(By.tagName("td"));
		// Click on first data row
		Utils.waitUntil_isClickable(driver, rowCells.get(1));

		for (int i = 1; i < tableRows.size(); i++) {
			rowCells = tableRows.get(i).findElements(By.tagName("td"));
			auxCurrencyID = rowCells.get(0).getText();
			auxSaldo = new BigDecimal(rowCells.get(2).getText().replace(",", ""));

			if (auxCurrencyID.equals(currencyID) && (auxSaldo.compareTo(BigDecimal.ZERO) > 0)) {
				rowCells.get(0).click();
				break;
			}
		}
		return rowCells.get(1).getText();
	}

	/**
	 * Returns Saldo for account accountID and clicks on the row
	 * 
	 * @param driver
	 * @param accountID
	 *
	 * @return
	 */
	public String getSaldoForAccountID(WebDriver driver, String accountID) {
		String auxAccountID;

		// Table rows
		List<WebElement> tableRows = tblBankAccounts.findElements(By.tagName("tr"));
		// Row#1 columns
		List<WebElement> rowCells = tableRows.get(1).findElements(By.tagName("td"));
		// Click on first data row
		Utils.waitUntil_isClickable(driver, rowCells.get(1));

		for (int i = 1; i < tableRows.size(); i++) {
			rowCells = tableRows.get(i).findElements(By.tagName("td"));
			auxAccountID = rowCells.get(1).getText();

			if (auxAccountID.equals(accountID)) {
				rowCells.get(0).click();
				break;
			}
		}
		return rowCells.get(2).getText().replace(",", "");
	}
}
