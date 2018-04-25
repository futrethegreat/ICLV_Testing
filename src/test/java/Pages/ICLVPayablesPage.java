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
	
	public void clickBtnExecute(WebDriver driver) {
		Utils.waitUntil_isClickable(driver, btnExecute);
		btnExecute.click();
	}

	public void clickBtnConfirmOK(WebDriver driver) {
		Utils.waitUntil_isClickable(driver, btnConfirmOK);
		btnConfirmOK.click();
	}

	public void clickBtnRefresh(WebDriver driver) {
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		btnRefresh.click();
	}
}
