package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ctc.Utils;

public class LinkedInAuthPage {
	@FindBy(name = "action")
	public WebElement btnAllow;

	public LinkedInAuthPage(WebDriver driver) throws TimeoutException {
		Utils.waitUntil_isPresent(driver, By.name("action"));
		PageFactory.initElements(driver, this);
	}

	public void clickAllowBtn() {
		this.btnAllow.click();
	}
}
