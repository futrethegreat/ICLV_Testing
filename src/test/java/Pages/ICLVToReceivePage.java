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
 * Implements as objects the Selenium objects of "To Receive" page. 
 * Implements class methods to manage Selenium objects.
 * 
 * @author DavidSauce
 *
 */
public class ICLVToReceivePage {
		
	@FindBys(
        {
        	@FindBy(xpath=".//*[@id=\"206|65|PE20523625633\"]/div"),
	        @FindBy(id="ot55")
	    }
	)
	public WebElement tblDebtors;

	@FindBys(
		{
			@FindBy(xpath=".//*[@id=\"206|65|PE20523625633\"]"),
			@FindBy(id="ot80")
		}
	)
	public WebElement tblInvoices;

	@FindBys(
		{
			@FindBy(xpath=".//*[@id=\"206|65|PE20523625633\"]"),
			@FindBy(xpath=".//*[@id=\"asform\"]/h3/table/tbody/tr[1]/td[2]/div")
		}
	)
	public WebElement btnResolveQuery;
	
	@FindBys(
		{
			@FindBy(xpath=".//*[@id=\"206|65|PE20523625633\"]"),
			@FindBy(id = "execute1")
		}
	)
	public WebElement btnExecute;		
	
	@FindBys(
		{
			@FindBy(xpath="//*[@id=\"206|65|PE20523625633\"]"),
			@FindBy(id = "note")
		}
	)
	public WebElement txtNoteBox;		

	public ICLVToReceivePage(WebDriver driver) throws TimeoutException {
		PageFactory.initElements(driver, this);
		Utils.waitUntil_isClickable(driver, tblDebtors);
	}

	/**
	 * Clicks in table Debtors on row of debtor specified in param "debtor",
	 * 
	 * @param driver: Stores current web page. Used to detect object presence in screen.
	 * @param debtor: Debtor to locate in invoices table.
	 * 
	 * @throws InterruptedException
	 */
	public void clickTblDebtorsRow(WebDriver driver, String debtor) throws InterruptedException {
		List<WebElement> tableRows = tblDebtors.findElements(By.tagName("tr"));
		for (int i = 0; i < tableRows.size(); i++) {
			List<WebElement> rowCells = tableRows.get(i).findElements(By.tagName("td"));
			for (int j=0; j<rowCells.size(); j++) {
				if (rowCells.get(j).getText().contains(debtor)) {
					rowCells.get(j).click();
					break;
				}
			}
		}
		Thread.sleep(500); // waits for refreshing objects in table. 
	}
	
	/**
	 * Checks if invoice "invoiceID" is disputed or not.
	 * 
	 * @param driver: Stores current web page. Used to detect object presence in screen.
	 * @param invoiceID: Invoice to locate in debtors table. 
	 * @return True if invoiceID is disputed. False if it is not.
	 * 
	 * @throws InterruptedException
	 */
	public Boolean checkTblInvoicesDisputed(WebDriver driver, String invoiceID) {
		Boolean r = false;
		
		// Table rows
		List<WebElement> tableRows = tblInvoices.findElements(By.tagName("tr"));
		// Row#1 columns
		List<WebElement> rowCells = tableRows.get(1).findElements(By.tagName("td"));
		// Click on first data row
		Utils.waitUntil_isClickable(driver, rowCells.get(1));
		
		for (int i=1;i<tableRows.size();i++) {
			rowCells = tableRows.get(i).findElements(By.tagName("td"));
			if (rowCells.get(1).getText().equals(invoiceID)) {
				r = rowCells.get(9).getText().length()!=0; // 9 is Disputed column
			}
		}
		return r;
	}

	/**
	 * Clicks in table Invoices the first invoice "Disputed"
	 * Finally returns the document ID of that document.
	 * 
	 * @param driver: Stores current web page. Used to detect object presence in screen.
	 * @return document ID of clicked row.
	 */
	public String clickInvoiceDisputed(WebDriver driver) {
		String documentID = "";
		
		// Table rows
		List<WebElement> tableRows = tblInvoices.findElements(By.tagName("tr"));
		// Row#1 columns
		List<WebElement> rowCells = tableRows.get(1).findElements(By.tagName("td"));
		// Click on first data row
		try {
			Utils.waitUntil_isClickable(driver, rowCells.get(0));
		} catch (TimeoutException e) {
			//If no invoices table, return empty string. 
			return documentID;
		}
		
		for (int i=1;i<tableRows.size();i++) {
			rowCells = tableRows.get(i).findElements(By.tagName("td"));
			if (rowCells.get(9).getText().trim().length()!=0) {
				rowCells.get(1).click();
				documentID = rowCells.get(1).getText();
				break;
			}
		}
		return documentID;
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
	 * Invokes click method for btnResolveQuery object
	 * 
	 * @param driver: Stores current web page. Used to detect object presence in screen.
	 */
	public void clickBtnResolveQuery(WebDriver driver) throws InterruptedException  {
		Utils.waitUntil_isClickable(driver, btnResolveQuery);
		btnResolveQuery.click();
	}
	
	/**
	 * Invokes click method for btnExecute object
	 * 
	 * @param driver: Stores current web page. Used to detect object presence in screen.
	 */
	public void clickBtnExecute(WebDriver driver) throws InterruptedException {
		Utils.waitUntil_isClickable(driver, btnExecute);
		btnExecute.click();
		Thread.sleep(750); // waits for the objects in screen be refreshed.
	}
}
