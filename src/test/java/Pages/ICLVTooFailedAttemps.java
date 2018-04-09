package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ctc.Utils;

public class ICLVTooFailedAttemps {
	@FindBy(xpath = "//*[@id=\"header\"]/p")
	public WebElement txtSomethingHappened;

	public ICLVTooFailedAttemps(WebDriver driver) throws Exception {
		Utils.waitUntil_isPresent(driver, By.xpath("//*[@id=\"header\"]/p"));
		PageFactory.initElements(driver, this);
	}

	public String getTxtSomethingHappened() {
		return this.txtSomethingHappened.getText().toString();
	}

}
