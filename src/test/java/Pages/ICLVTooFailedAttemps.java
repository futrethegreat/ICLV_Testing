package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.ctc.Utils;

/**
 * Implements as objects the Selenium objects of "Too failed attempts" error page. 
 * Implements class methods to manage Selenium objects.
 * 
 * @author DavidSauce
 *
 */
public class ICLVTooFailedAttemps {
	@FindBy(xpath = "//*[@id=\"header\"]/p")
	public WebElement txtSomethingHappened;

	public ICLVTooFailedAttemps(WebDriver driver) throws Exception {
		PageFactory.initElements(driver, this);
		Utils.waitUntil_isPresent(driver, By.xpath("//*[@id=\"header\"]/p"));
	}

	/**
	 * Returns text typed in txtSomethingHappened object.
	 * 
	 * @return text typed in txtSomethingHappened object as string.
	 */
	public String getTxtSomethingHappened() {
		return this.txtSomethingHappened.getText().toString();
	}

}
