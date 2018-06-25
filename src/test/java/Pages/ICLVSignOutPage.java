package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ctc.Utils;

/**
 * Implements as objects the Selenium objects of "Sign out" page https://test.capitool.com/auth/static.php?page=signout 
 * Implements class methods to manage Selenium objects.
 * 
 * @author DavidSauce
 *
 */

public class ICLVSignOutPage {
	@FindBy(xpath = "//*[@id=\"header\"]/p[1]") 
	public WebElement txtSignOut;
	@FindBy(linkText = "Acceso")
	public WebElement lnkAcceso;

	public ICLVSignOutPage(WebDriver driver) throws Exception {
		PageFactory.initElements(driver, this);
		Utils.waitUntil_isPresent(driver, By.xpath("//*[@id=\"header\"]/p[1]"));
	}

	/**
	 * Returns text displayed by txtSignOut object, without non standard characters,
	 * from 0 to 22 which is text: "Gracias por usar Trefi"
	 * 
	 * @return Text displayed in txtSignOut object
	 */
	public String getTxtSignOutText() {
		return Utils.normalizeString(this.txtSignOut.getText().toString().substring(0, 22));
	}

	/**
	 * Returns text typed in lnkAcceso object as String.
	 * 
	 * @return text typed in lnkAcceso object as string.
	 */
	public String getLnkAccesoText() {
		return this.lnkAcceso.getText().toString();
	}

	/**
	 * Invokes click method for lnkAcceso object
	 * 
	 */
	public void clickLnkAcceso() {
		this.lnkAcceso.click();
	}
}
