package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ctc.Utils;

public class ICLVLoginPage {
	@FindBy(name = "signinid")
	public WebElement txtUserName;
	@FindBy(name = "pass")
	public WebElement txtPassword;
	@FindBy(name = "submitTrefi")
	public WebElement btnLogIn;
	@FindBy(xpath = "//*[@id=\"enrollform\"]/div/span")
	public WebElement txtErrorLogIn;

	public ICLVLoginPage(WebDriver driver) {
		Utils.waitUntil_isPresent(driver, By.name("signinid"));
		PageFactory.initElements(driver, this);
	}

	public void typeCredentials(String userName, String password) {
		this.txtUserName.clear();
		this.txtPassword.clear();
		this.txtUserName.sendKeys(new CharSequence[] { userName });
		this.txtPassword.sendKeys(new CharSequence[] { password });
	}

	public void setUserName(String userName) {
		this.txtUserName.sendKeys(new CharSequence[] { userName });
	}

	public String getUserName() {
		return this.txtUserName.getText().toString();
	}

	public void setPassword(String password) {
		this.txtPassword.sendKeys(new CharSequence[] { password });
	}

	public void clickLogInBtn() {
		this.btnLogIn.click();
	}

	public String getErrorText() {
		return this.txtErrorLogIn.getText().trim();
	}
}
