package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ctc.Utils;

public class ICLVHomePage {
	@FindBy(linkText = "ACCEDER A CUENTA")
	public WebElement lnkSignIn;

	public ICLVHomePage(WebDriver driver) {
		Utils.waitUntil_isPresent(driver, By.linkText("ACCEDER A CUENTA"));
		PageFactory.initElements(driver, this);
	}

	public String getLnkSignInText() {
		return this.lnkSignIn.getText().toString();
	}

	public void clickLnkSignIn() {
		this.lnkSignIn.click();
	}

}
