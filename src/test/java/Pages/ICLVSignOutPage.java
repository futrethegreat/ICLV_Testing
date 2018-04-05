package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ctc.Utils;

public class ICLVSignOutPage {
	@FindBy(xpath = "/html/body/div/section/div/div[1]/h2")
	public WebElement txtSignOut;
	@FindBy(linkText = "Acceso")
	public WebElement lnkAcceso;

	public ICLVSignOutPage(WebDriver driver) {
		Utils.waitUntil_isPresent(driver, By.xpath("/html/body/div/section/div/div[1]/h2"));
		PageFactory.initElements(driver, this);
	}

	public String getTxtSignOutText() {
		return this.txtSignOut.getText().toString();
	}

	public String getLnkAccesoText() {
		return this.lnkAcceso.getText().toString();
	}

	public void clickLnkAcceso() {
		this.lnkAcceso.click();
	}
}
