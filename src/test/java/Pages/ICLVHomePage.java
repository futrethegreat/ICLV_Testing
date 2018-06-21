package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ctc.Utils;

/**
 * Implements as objects the Selenium objects of "Home" page https://iclvtestweb.capitool.com. 
 * Implements class methods to manage Selenium objects.
 * 
 * @author DavidSauce
 *
 */

public class ICLVHomePage {
	
//	@FindBy(linkText = "Sign in to your account")
	@FindBy(xpath = "//*[@id=\"post-14\"]/div/div/div/p/a/span")
	public WebElement lnkSignIn;

	public ICLVHomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		Utils.waitUntil_isClickable(driver, lnkSignIn);
	}

	/**
	 * Returns text of lnkSignIn object as String
	 * 
	 * @return text displayed on lnkSignIn object
	 */
	public String getLnkSignInText() {
		return this.lnkSignIn.getText().toString();
	}

	/**
	 * Invokes click method for lnkSignIn object
	 */
	public void clickLnkSignIn() {
		this.lnkSignIn.click();
	}

}
