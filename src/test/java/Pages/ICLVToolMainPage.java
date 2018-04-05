package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ctc.Utils;

public class ICLVToolMainPage {
	@FindBy(xpath = "//*[@title=\"signout of TREFI\"]")
	public WebElement lnkSignOut;
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

	public ICLVToolMainPage(WebDriver driver) {
		Utils.waitUntil_isPresent(driver, By.xpath("//*[@title=\"signout of TREFI\"]"));
		PageFactory.initElements(driver, this);
	}

	public String getLnkSignOutText() {
		return this.lnkSignOut.getAttribute("title").toString();
	}

	public String getLnkInvoicesText() {
		return this.lnkInvoices.getText().toString();
	}

	public void clickLnkSignOut() {
		this.lnkSignOut.click();
	}
}
