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
 * Implements as objects the Selenium objects of "Too failed attempts" error page. 
 * Implements class methods to manage Selenium objects.
 * 
 * @author DavidSauce
 *
 */
public class ICLVToolMainPage {
	@FindBy(xpath = "//*[@title=\"signout of TREFI\"]")
	public WebElement lnkSignOut;
	@FindBy(xpath = "//*[@title='HK1296102']")
	public WebElement lnkCTC;
	@FindBy(xpath = "//*[@title='PE20170040938']")
	public WebElement lnkDANPER;
	@FindBy(xpath = "//*[@title='PE20523625633']")
	public WebElement lnkCTCLATAM;
	
	@FindBys(
        {
	        @FindBy(xpath=".//*[@id=\"tPE20170040938_65\"]"),
	        @FindBy(linkText="To receive")
        }
	)
	public WebElement lnkToReceiveDANPER;

	@FindBys(
		{
			@FindBy(xpath=".//*[@id=\"tPE20170040938_65\"]"),
			@FindBy(linkText="To pay")
		}
	)
	public WebElement lnkToPayDANPER;

	@FindBys(
		{
	        @FindBy(xpath=".//*[@id=\"tPE20170040938_65\"]"),
	        @FindBy(linkText="Finance")
		}
	)
	public WebElement lnkFinanceDANPER;

	@FindBys(
		{
			@FindBy(xpath=".//*[@id=\"tPE20170040938_65\"]"),
			@FindBy(linkText="Account")
		}
	)
	public WebElement lnkAccountDANPER;
	
	@FindBys(
		{
			@FindBy(xpath=".//*[@id=\"tPE20170040938_65\"]"),
			@FindBy(linkText="Invoices")
		}
	)
	public WebElement lnkInvoicesDANPER;
	
	@FindBys(
		{
			@FindBy(xpath=".//*[@id=\"tPE20523625633_65\"]"),
			@FindBy(linkText="To receive")
		}
	)
	public WebElement lnkToReceiveCTCLATAM;
	
	@FindBys(
		{
			@FindBy(xpath=".//*[@id=\"tPE20523625633_65\"]"),
			@FindBy(linkText="To pay")

		}
	)
	public WebElement lnkToPayCTCLATAM;
	
	@FindBys(
		{
			@FindBy(xpath=".//*[@id=\"tPE20523625633_65\"]"),
			@FindBy(linkText="Finance")
		}
	)
	public WebElement lnkFinanceCTCLATAM;

	@FindBys(
		{
			@FindBy(xpath=".//*[@id=\"tPE20523625633_65\"]"),
			@FindBy(linkText="Account")
		}
	)
	public WebElement lnkAccountCTCLATAM;

	@FindBys(
		{
			@FindBy(xpath=".//*[@id=\"tPE20523625633_65\"]"),
			@FindBy(linkText="Invoices")
		}
	)
	public WebElement lnkInvoicesCTCLATAM;
	
	public ICLVToolMainPage(WebDriver driver) throws TimeoutException {
		PageFactory.initElements(driver, this);
		Utils.waitUntil_isPresent(driver, By.xpath("//*[@title=\"signout of TREFI\"]"));
	}

	/**
	 * Returns text typed in lnkSignOut object.
	 * 
	 * @return text typed in lnkSignOut object as string.
	 */
	public String getLnkSignOutText() {
		return this.lnkSignOut.getAttribute("title").toString();
	}

	/**
	 * Returns text typed in lnkDANPER object.
	 * 
	 * @return text typed in lnkDANPER object as string.
	 */
	public String getLnkDanperText() {
		return this.lnkDANPER.getAttribute("title").toString();
	}

	/**
	 * Invokes click method for lnkSignOut object
	 * 
	 */
	public void clickLnkSignOut() {
		this.lnkSignOut.click();
	}

	/**
	 * Invokes click method for lnkCTC object
	 * 
	 */
	public void clickLnkCTC() throws InterruptedException {
		lnkCTC.click();
		Thread.sleep(1000); //waits for the new tab to be opened and all objects loaded.
	}

	/**
	 * Invokes click method for lnkDANPER object
	 * 
	 */
	public void clickLnkDANPER() throws InterruptedException {
		lnkDANPER.click();
		Thread.sleep(1000); //waits for the new tab to be opened and all objects loaded.
	}

	/**
	 * Invokes click method for lnkCTCLATAM object
	 * 
	 */
	public void clickLnkCTCLATAM() throws InterruptedException {
		lnkCTCLATAM.click();
		Thread.sleep(1000); //waits for the new tab to be opened and all objects loaded.
	}

	/**
	 * Invokes click method for lnkToPayDANPER object
	 * 
	 */
	public void clickLnkPayablesDANPER() throws InterruptedException{
		lnkToPayDANPER.click();
		Thread.sleep(1000); //waits for the new tab to be opened and all objects loaded.
	}

	/**
	 * Invokes click method for lnkAccountDANPER object
	 * 
	 */
	public void clickLnkAccountDANPER() throws InterruptedException{
		lnkAccountDANPER.click();
		Thread.sleep(1000); //waits for the new tab to be opened and all objects loaded.
	}

	/**
	 * Invokes click method for lnkToPayCTCLATAM object
	 * 
	 */
	public void clickLnkPayablesCTCLATAM() throws InterruptedException{
		lnkToPayCTCLATAM.click();
		Thread.sleep(1000); //waits for the new tab to be opened and all objects loaded.
	}

	/**
	 * Invokes click method for lnkToReceiveCTCLATAM object
	 * 
	 */
	public void clickLnkToReceiveCTCLATAM() throws InterruptedException{
		lnkToReceiveCTCLATAM.click();
		Thread.sleep(1000); //waits for the new tab to be opened and all objects loaded.
	}

	/**
	 * Invokes click method for lnkAccountCTCLATAM object
	 * 
	 */
	public void clickLnkAccountCTCLATAM() throws InterruptedException{
		lnkAccountCTCLATAM.click();
		Thread.sleep(1000); //waits for the new tab to be opened and all objects loaded.
	}

	/**
	 * Invokes click method for lnkInvoicesCTCLATAM object
	 * 
	 */
	public void clickLnkInvoicesCTCLATAM() throws InterruptedException{
		lnkInvoicesCTCLATAM.click();
		Thread.sleep(1000); //waits for the new tab to be opened and all objects loaded.
	}

}
