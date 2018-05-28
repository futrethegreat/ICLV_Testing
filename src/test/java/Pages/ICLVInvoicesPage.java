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
	
	public class ICLVInvoicesPage {
		
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
//			@FindBy(id = "execute1")
		public WebElement btnExecute;		
		
		@FindBys(
			{
				@FindBy(xpath="//*[@id=\"206|65|PE20523625633\"]"),
				@FindBy(id = "note")
			}
		)
//			@FindBy(id = "note")
		public WebElement txtNoteBox;		

//		@FindBy(id = "ot55")
//		public WebElement tblDebtors;
//		@FindBy(id = "ot80")
//		public WebElement tblInvoices;
		
		public ICLVInvoicesPage(WebDriver driver) throws TimeoutException {
			Utils.waitUntil_isClickable(driver, By.id("ot55"));
			PageFactory.initElements(driver, this);
		}

		public void clickTblDebtorsRow(WebDriver driver, String debtor) throws InterruptedException {
			List<WebElement> tableRows = tblDebtors.findElements(By.tagName("tr"));
//			System.out.println("No of rows are : " + tableRows.size());
			for (int i = 0; i < tableRows.size(); i++) {
//				System.out.println(tableRows.get(i).getText());
				List<WebElement> rowCells = tableRows.get(i).findElements(By.tagName("td"));
				for (int j=0; j<rowCells.size(); j++) {
					if (rowCells.get(j).getText().contains(debtor)) {
						rowCells.get(j).click();
						break;
					}
//					System.out.println(rowCells.get(j).getText());
				}
			}
			Thread.sleep(500);
		}
		
		public Boolean checkTblInvoicesDisputed(WebDriver driver, String invoiceID) {
			Boolean r = false;
			
			// Table rows
			List<WebElement> tableRows = tblInvoices.findElements(By.tagName("tr"));
			// Row#1 columns
			List<WebElement> rowCells = tableRows.get(1).findElements(By.tagName("td"));
			// Click on first data row
			Utils.waitUntil_isClickable(driver, rowCells.get(1));
			
//			Utils.consoleMsg("size: " + tableRows.size());
//			Utils.consoleMsg("invoice ID: " + invoiceID);
			for (int i=1;i<tableRows.size();i++) {
				rowCells = tableRows.get(i).findElements(By.tagName("td"));
//				Utils.consoleMsg("0: " + rowCells.get(0).getText());
//				Utils.consoleMsg("1: " + rowCells.get(1).getText());
//				Utils.consoleMsg("2: " + rowCells.get(2).getText());
//				Utils.consoleMsg("3: " + rowCells.get(3).getText());
//				Utils.consoleMsg("4: " + rowCells.get(4).getText());
				if (rowCells.get(1).getText().equals(invoiceID)) {
//					Utils.consoleMsg("Task: " + rowCells.get(1).getText());
					r = rowCells.get(9).getText().length()!=0;
				}
			}
			return r;
		}

		public String clickInvoiceDisputed(WebDriver driver) {
			String documentID = "";
			
			// Table rows
			List<WebElement> tableRows = tblInvoices.findElements(By.tagName("tr"));
			// Row#1 columns
			List<WebElement> rowCells = tableRows.get(1).findElements(By.tagName("td"));
			// Click on first data row
			Utils.waitUntil_isClickable(driver, rowCells.get(1));
			
//			Utils.consoleMsg("size: " + tableRows.size());
//			Utils.consoleMsg("invoice ID: " + invoiceID);
			for (int i=1;i<tableRows.size();i++) {
				rowCells = tableRows.get(i).findElements(By.tagName("td"));
//				Utils.consoleMsg("0: " + rowCells.get(0).getText());
//				Utils.consoleMsg("1: " + rowCells.get(1).getText());
//				Utils.consoleMsg("2: " + rowCells.get(2).getText());
//				Utils.consoleMsg("3: " + rowCells.get(3).getText());
//				Utils.consoleMsg("Task: [" + rowCells.get(9).getText() + "]");
				if (rowCells.get(9).getText().trim().length()!=0) {
					rowCells.get(1).click();
					documentID = rowCells.get(1).getText();
					break;
				}
			}
			return documentID;
		}

		public void setTxtNoteBox(WebDriver driver, String s) {
			Utils.waitUntil_isClickable(driver, txtNoteBox);
			txtNoteBox.clear();
			txtNoteBox.sendKeys(s);
		}
		
		public void clickBtnResolveQuery(WebDriver driver) throws InterruptedException  {
			Utils.waitUntil_isClickable(driver, btnResolveQuery);
			btnResolveQuery.click();
		}
		
		public void clickBtnExecute(WebDriver driver) throws InterruptedException {
			Utils.waitUntil_isClickable(driver, btnExecute);
			btnExecute.click();
			Thread.sleep(750);
		}
	}
