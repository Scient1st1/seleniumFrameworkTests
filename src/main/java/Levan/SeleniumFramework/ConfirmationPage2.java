package Levan.SeleniumFramework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage2 extends AbstactClass2{
	WebDriver driver;
	public ConfirmationPage2(WebDriver driver) {
			super(driver);
			this.driver = driver;
			PageFactory.initElements(driver, this);
	}

	@FindBy(css=".hero-primary")
	WebElement confirm;
	
	public String verifyConfirmationMsg() {
		return confirm.getText();
	}
}
