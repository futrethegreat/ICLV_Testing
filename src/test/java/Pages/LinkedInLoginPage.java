package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ctc.Utils;

public class LinkedInLoginPage {
	@FindBy(name = "session_key")
	public WebElement txtEmail;
	@FindBy(name = "session_password")
	public WebElement txtPassword;
	@FindBy(name = "signin")
	public WebElement btnLogIn;

	public LinkedInLoginPage(WebDriver driver) throws TimeoutException {
		Utils.waitUntil_isPresent(driver, By.name("signin"));
		PageFactory.initElements(driver, this);
	}

	public void typeCredentials(String userName, String password) {
		this.txtEmail.clear();
		this.txtPassword.clear();
		this.txtEmail.sendKeys(new CharSequence[] { userName });
		this.txtPassword.sendKeys(new CharSequence[] { password });
	}

	public void setEmail(String email) {
		this.txtEmail.sendKeys(new CharSequence[] { email });
	}

	public String getEmail() {
		return this.txtEmail.getText().toString();
	}

	public void setPassword(String password) {
		this.txtPassword.sendKeys(new CharSequence[] { password });
	}

	public void clickLogInBtn() {
		this.btnLogIn.click();
	}
}
