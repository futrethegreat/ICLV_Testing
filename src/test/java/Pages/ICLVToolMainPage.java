package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import com.ctc.Utils;

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
	        @FindBy(id="tPE20170040938_65"),
	        @FindBy(xpath="//*[@id=\"c208\"]/div/a")
        }
	)
	public WebElement lnkToPayDANPER;

	@FindBys(
			{
				@FindBy(id="tPE20170040938_65"),
				@FindBy(xpath="//*[@id=\"c212\"]/div/a")
			}
			)
	public WebElement lnkAccountDANPER;

	@FindBys(
		{
			@FindBy(id="tPE20523625633_65"),
			@FindBy(xpath="//*[@id=\"tPE20523625633_65\"]/tbody/tr[3]/td[3]/div/a")
		}
		)
	public WebElement lnkToPayCTCLATAM;

	@FindBys(
        {
            @FindBy(xpath="//*[@id=\"tPE20523625633_65\"]"),
            @FindBy(xpath="//*[@id=\"tPE20523625633_65\"]/tbody/tr[2]/td[3]/div/a")
        }
	)
	public WebElement lnkToReceiveCTCLATAM;

	@FindBys(
			{
				@FindBy(xpath="//*[@id=\"tPE20523625633_65\"]"),
				@FindBy(xpath="//*[@id=\"tPE20523625633_65\"]/tbody/tr[2]/td[5]/div/a")
			}
			)
	public WebElement lnkAccountCTCLATAM;
	
	public ICLVToolMainPage(WebDriver driver) throws TimeoutException {
		Utils.waitUntil_isPresent(driver, By.xpath("//*[@title=\"signout of TREFI\"]"));
		PageFactory.initElements(driver, this);
	}

	public String getLnkSignOutText() {
		return this.lnkSignOut.getAttribute("title").toString();
	}

	public String getLnkDanperText() {
		return this.lnkDANPER.getAttribute("title").toString();
	}

	public void clickLnkSignOut() {
		this.lnkSignOut.click();
	}

	public void clickLnkCTC() throws InterruptedException {
		lnkCTC.click();
		Thread.sleep(1000);
	}

	public void clickLnkDANPER() throws InterruptedException {
		lnkDANPER.click();
		Thread.sleep(1000);
	}

	public void clickLnkCTCLATAM() throws InterruptedException {
		lnkCTCLATAM.click();
		Thread.sleep(1000);
	}

	public void clickLnkPayablesDANPER() throws InterruptedException{
		lnkToPayDANPER.click();
		Thread.sleep(1000);
	}

	public void clickLnkAccountDANPER() throws InterruptedException{
		lnkAccountDANPER.click();
		Thread.sleep(1000);
	}

	public void clickLnkPayablesCTCLATAM() throws InterruptedException{
		lnkToPayCTCLATAM.click();
		Thread.sleep(1000);
	}

	public void clickLnkInvoicesCTCLATAM() throws InterruptedException{
		lnkToReceiveCTCLATAM.click();
		Thread.sleep(1000);
	}

	public void clickLnkAccountCTCLATAM() throws InterruptedException{
		lnkAccountCTCLATAM.click();
		Thread.sleep(1000);
	}

}
