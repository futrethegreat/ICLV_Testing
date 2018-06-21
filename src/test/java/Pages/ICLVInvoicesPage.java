package Pages;
	
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.ctc.Utils;
	
/**
 * Implements as objects the Selenium objects of "Invoices" page. 
 * Implements class methods to manage Selenium objects.
 * 
 * @author DavidSauce
 *
 */
public class ICLVInvoicesPage {
		
	@FindBys(
        {
        	@FindBy(xpath=".//*[@id=\"165|65|PE20523625633\"]/div"),
	        @FindBy(id="ot55")
	    }
	)
	public WebElement tblDebtors;

	@FindBys(
		{
			@FindBy(xpath=".//*[@id=\"165|65|PE20523625633\"]/div"),
			@FindBy(id="ot36")
		}
	)
	public WebElement tblContacts;

	@FindBys(
		{
			@FindBy(xpath=".//*[@id=\"165|65|PE20523625633\"]"),
			@FindBy(id="ot80")
		}
	)
	public WebElement tblInvoices;

	@FindBys(
		{
			@FindBy(xpath=".//*[@id=\"165|65|PE20523625633\"]/div"),
			@FindBy(xpath=".//*[@id=\"triggertask\"]/div[3]"),
			@FindBy(xpath=".//*[@id=\"selectedActTypeid_chosen\"]")
		}
	)
	public WebElement lstDocument;

	@FindBys(
		{
			@FindBy(xpath=".//*[@id=\"165|65|PE20523625633\"]/div"),
			@FindBy(xpath=".//*[@id=\"triggertask\"]/div[3]"),
			@FindBy(xpath=".//*[@id=\"selectedActTypeid_chosen\"]/div/ul/li[1]")
		}
	)
	public WebElement lstDocumentInvoice;
	
	@FindBys(
		{
			@FindBy(xpath=".//*[@id=\"165|65|PE20523625633\"]/div"),
			@FindBy(xpath=".//*[@id=\"triggertask\"]/div[3]"),
			@FindBy(xpath=".//*[@id=\"selectedActTypeid_chosen\"]/div/ul/li[5]")
		}
	)
	public WebElement lstDocumentSubmitDocument;
	
	@FindBys(
		{
			@FindBy(xpath=".//*[@id=\"165|65|PE20523625633\"]/div"),
			@FindBy(xpath=".//*[@id=\"triggertask\"]/div[3]"),
			@FindBy(xpath = ".//*[@id=\"execute1\"]")
		}
	)
	public WebElement btnExecute;		
	
	@FindBys(
		{
			@FindBy(xpath=".//*[@id=\"165|65|PE20523625633\"]"),
			@FindBy(xpath = ".//*[@id=\"asform\"]/div/div[4]/table/tbody/tr[2]/td[2]")
		}
	)
	public WebElement lblDocumentID;		

	@FindBys(
		{
			@FindBy(xpath="//*[@id=\"165|65|PE20523625633\"]"),
			@FindBy(xpath = "//*[@id=\"asform\"]/div/div[4]/table/tbody/tr[16]/td/textarea")
		}
	)
	public WebElement txtNoteBox;		

	@FindBys(
		{
			@FindBy(xpath="//*[@id=\"165|65|PE20523625633\"]"),
			@FindBy(xpath = "//*[@id=\"autocomplete\"]")
		}
	)
	public WebElement txtCodigo;		

	@FindBys(
		{
			@FindBy(xpath="//*[@id=\"165|65|PE20523625633\"]"),
			@FindBy(xpath = "//*[@id=\"newinvoiceline1\"]/td[5]/input")
		}
	)
	public WebElement txtValor;		

	@FindBys(
		{
			@FindBy(xpath="//*[@id=\"165|65|PE20523625633\"]"),
			@FindBy(xpath = "//*[@id=\"newinvoiceline1\"]/td[2]/textarea")
		}
	)
	public WebElement txtDescription;		
	
	public ICLVInvoicesPage(WebDriver driver) throws TimeoutException {
		PageFactory.initElements(driver, this);
		Utils.waitUntil_isClickable(driver, tblDebtors);
	}

	/**
	 * Clicks in table Debtors on row of debtor specified in param "debtor".
	 * 
	 * @param driver: Stores current web page. Used to detect object presence in screen.
	 * @param debtor: Debtor to locate in debtors table.
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
		Thread.sleep(500); //waits for the objects to be loaded in the selected row.
	}
	
	/**
	 * Clicks in table Contacts on row of contact specified in param "contact".
	 * 
	 * @param driver: Stores current web page. Used to detect object presence in screen.
	 * @param contact: Contact to locate in debtors table.
	 * 
	 * @throws InterruptedException
	 */
	public void clickTblContactsRow(WebDriver driver, String contact) throws InterruptedException {
		List<WebElement> tableRows = tblContacts.findElements(By.tagName("tr"));
		for (int i = 0; i < tableRows.size(); i++) {
			List<WebElement> rowCells = tableRows.get(i).findElements(By.tagName("td"));
			for (int j=0; j<rowCells.size(); j++) {
				if (rowCells.get(j).getText().contains(contact)) {
					rowCells.get(j).click();
					break;
				}
			}
		}
		Thread.sleep(500); //waits for the objects to be loaded in the selected row.
	}
	
	/**
	 *  Checks in table Invoices if exits invoice specified by param "invoiceID".
	 * 
	 * @param driver: Stores current web page. Used to detect object presence in screen.
	 * @param invoiceID: Invoice to locate in debtors table.
	 * @return True if invoice exits in table. False otherwise.
	 * 
	 * @throws InterruptedException
	 */
	public Boolean checkTblInvoices(WebDriver driver, String invoiceID) {
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
				r = true;
			}
		}
		return r;
	}

	/**
	 * Clicks in table Invoices on first row with an invoice in "Draft" state.
	 * 
	 * @param driver: Stores current web page. Used to detect object presence in screen.
	 * @return True an invoice in "Draft" state exits in table. False otherwise.
	 * 
	 * @throws InterruptedException
	 */
	public Boolean clickTblInvoicesDraft(WebDriver driver) {
		Boolean r = false;
		
		// Table rows
		List<WebElement> tableRows = tblInvoices.findElements(By.tagName("tr"));
		// Row#1 columns
		List<WebElement> rowCells = tableRows.get(1).findElements(By.tagName("td"));
		// Click on first data row
		Utils.waitUntil_isClickable(driver, rowCells.get(1));
		
		for (int i=1;i<tableRows.size();i++) {
			rowCells = tableRows.get(i).findElements(By.tagName("td"));
			if (rowCells.get(2).getText().equals("Draft")) {
				rowCells.get(2).click();
				r = true;
				break;
			}
		}
		return r;
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
	 * Types in txtCodigo object the text received in param "s".
	 * First waits for object presence in screen.
	 * 
	 * @param driver: Stores current web page. Used to detect object presence in screen.
	 * @param s: Text to write in object.
	 */
	public void setTxtCodigo(WebDriver driver, String s) {
		Utils.waitUntil_isClickable(driver, txtCodigo);
		txtCodigo.clear();
		txtCodigo.sendKeys(s);
	}
	
	/**
	 * Types in txtDescription object the text received in param "s".
	 * First waits for object presence in screen.
	 * Finally, scrolls page down to make button Execute visible.
	 * 
	 * @param driver: Stores current web page. Used to detect object presence in screen.
	 * @param s: Text to write in object.
	 */
	public void setTxtDescription(WebDriver driver, String s) {
		Utils.waitUntil_isClickable(driver, txtDescription);
		txtDescription.clear();
		txtDescription.sendKeys(s);
		txtNoteBox.sendKeys("Note"); //Also writes something in Note box.
		
		//Scrolls page down to make button Execute visible
		Actions action = new Actions(driver);
		action.doubleClick(lblDocumentID).perform(); 
		action.sendKeys(Keys.PAGE_DOWN).perform();
	}
	
	/**
	 * Types in txtValor object the text received in param "s".
	 * First waits for object presence in screen.
	 * 
	 * @param driver: Stores current web page. Used to detect object presence in screen.
	 * @param s: Text to write in object.
	 */
	public void setTxtValor(WebDriver driver, String s) {
		Utils.waitUntil_isClickable(driver, txtValor);
		txtValor.clear();
		txtValor.sendKeys(s);
	}
	
	/**
	 * Returns text displayed by lblDocumentID object.
	 * First waits for object presence in screen.
	 * 
	 * @param driver: Stores current web page. Used to detect object presence in screen.
	 * @return Text displayed in lblDocumentID object as String
	 */
	public String getDocumentID(WebDriver driver) {
		return lblDocumentID.getText();
	}
	
	/**
	 * Invokes click method for btnExecute object
	 * 
	 * @param driver: Stores current web page. Used to detect object presence in screen.
	 */
	public void clickBtnExecute(WebDriver driver) throws InterruptedException {
		Utils.waitUntil_isClickable(driver, btnExecute);
		btnExecute.click();
		Thread.sleep(1500); //waits for the table invoices to be refreshed with new invoice created.
	}

	/**
	 * Invokes click method for lstDocument object
	 * 
	 * @param driver: Stores current web page. Used to detect object presence in screen.
	 */
	public void clickLstDocument(WebDriver driver) throws InterruptedException {
		Utils.waitUntil_isClickable(driver, lstDocument);
		lstDocument.click();
		Thread.sleep(750); //waits for objects in page to be refreshed.
	}

	/**
	 * Invokes click method for lstDocumentInvoice object
	 * 
	 * @param driver: Stores current web page. Used to detect object presence in screen.
	 */
	public void clickLstDocumentInvoice(WebDriver driver) throws InterruptedException {
		Utils.waitUntil_isClickable(driver, lstDocument);
		lstDocument.click();
		Thread.sleep(250); //waits for objects in page to be refreshed.
		lstDocumentInvoice.click();
		Thread.sleep(750); //waits for objects in page to be refreshed.
	}

	/**
	 * Invokes click method for lstDocumentSubmitDocument object
	 * 
	 * @param driver: Stores current web page. Used to detect object presence in screen.
	 */
	public void clickLstDocumentSubmitDocument(WebDriver driver) throws InterruptedException {
		Utils.waitUntil_isClickable(driver, lstDocument);
		lstDocument.click();
		Thread.sleep(250); //waits for objects in page to be refreshed.
		lstDocumentSubmitDocument.click();
		Thread.sleep(750); //waits for objects in page to be refreshed.
	}
}
