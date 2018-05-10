package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ctc.Utils;

public class ICLVToolMainPage {
	@FindBy(xpath = "//*[@title=\"signout of TREFI\"]")
	public WebElement lnkSignOut;
	/*
	 * FW6 OBJECTS
	@FindBy(linkText = "Invoices")
	public WebElement lnkInvoices;
	@FindBy(linkText = "Payables")
	public WebElement lnkPayables;
	@FindBy(linkText = "Finance")
	public WebElement lnkFinance;
	@FindBy(linkText = "Account")
	public WebElement lnkAccount;
	@FindBy(linkText = "Personal")
	public WebElement lnkPersonal;
	*/
	
	@FindBy(xpath = "//*[@id=\"tooltree\"]/div/div/div/div[3]")
	public WebElement lnkCTC;
	@FindBy(xpath = "//*[@id=\"tooltree\"]/div/div/div/div[7]")
	public WebElement lnkDanper;
	@FindBy(xpath = "//*[@id=\"c208\"]/div/a")
	public WebElement lnkPayables;
	
	public ICLVToolMainPage(WebDriver driver) throws TimeoutException {
		Utils.waitUntil_isPresent(driver, By.xpath("//*[@title=\"signout of TREFI\"]"));
		PageFactory.initElements(driver, this);
	}

	public String getLnkSignOutText() {
		return this.lnkSignOut.getAttribute("title").toString();
	}

	public String getLnkDanperText() {
		return this.lnkDanper.getAttribute("title").toString();
	}

	/* FW6
	public String getLnkInvoicesText() {
		return this.lnkInvoices.getText().toString();
	}
	*/

	public void clickLnkSignOut() {
		this.lnkSignOut.click();
	}

	public void clickLnkDanper(WebDriver driver) {
		
//		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", lnkDanper);
//		
//		Actions actions = new Actions(driver);
//		actions.moveToElement(lnkDanper);
//		actions.perform();
		
		try {
			lnkDanper.click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			lnkCTC.click();
			lnkDanper.click();
		}
	}

	public void clickLnkPayables(WebDriver driver) {
//		//Utils.waitUntil_isClickable(driver, lnkPayables);
//		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", lnkPayables);
//
//		Actions actions = new Actions(driver);
//		actions.moveToElement(lnkPayables);
//		actions.perform();

		try {
			lnkPayables.click();
		} catch (Exception e) {
			clickLnkDanper(driver);
			lnkPayables.click();
		}
	}

}
