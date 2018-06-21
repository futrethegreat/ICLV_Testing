package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ctc.Utils;

/**
 * Implements as objects the Selenium objects of "Login" page https://test.capitool.com/auth. 
 * Implements class methods to manage Selenium objects.
 * 
 * @author DavidSauce
 *
 */

public class ICLVLoginPage {
	@FindBy(name = "signinid")
	public WebElement txtUserName;
	@FindBy(name = "pass")
	public WebElement txtPassword;
	@FindBy(name = "submitTrefi")
	public WebElement btnLogIn;
	@FindBy(xpath = "//*[@id=\"enrollform\"]/div/span")
	public WebElement txtErrorLogIn;
	@FindBy(name = "submit") // LinkedIn button
	public WebElement btnLinkedIn;

	public ICLVLoginPage(WebDriver driver) throws TimeoutException {
		PageFactory.initElements(driver, this);
		Utils.waitUntil_isPresent(driver, By.name("signinid"));
	}

	/**
	 * Types user and password received as params.
	 * 
	 * @param userName
	 * @param password
	 */
	public void typeCredentials(String userName, String password) {
		this.txtUserName.clear();
		this.txtPassword.clear();
		this.txtUserName.sendKeys(new CharSequence[] { userName });
		this.txtPassword.sendKeys(new CharSequence[] { password });
	}

	/**
	 * Types user received as param.
	 * 
	 * @param userName
	 */
	public void setUserName(String userName) {
		this.txtUserName.sendKeys(new CharSequence[] { userName });
	}

	
	/**
	 * Returns text typed in txtUserName object.
	 * 
	 * @return text typed in txtUserName object as string.
	 */
	public String getUserName() {
		return this.txtUserName.getText().toString();
	}

	/**
	 * Types password received as param.
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.txtPassword.sendKeys(new CharSequence[] { password });
	}

	/**
	 * Invokes click method for btnLogIn object
	 * 
	 */
	public void clickLogInBtn() {
		this.btnLogIn.click();
	}

	/**
	 * Invokes click method for btnLinkedIn object
	 * 
	 */
	public void clickLinkedInBtn() {
		this.btnLinkedIn.click();
	}

	/**
	 * Returns text displayed by txtErrorLogIn object, without non standard characters.
	 * 
	 * @return Text displayed in txtErrorLogIn object
	 */
	public String getErrorText() throws NoSuchElementException {
		return Utils.normalizeString(this.txtErrorLogIn.getText().trim());
	}
}
