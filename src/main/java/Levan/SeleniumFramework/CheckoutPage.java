package Levan.SeleniumFramework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends AbstactClass2{
	WebDriver driver;
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css="[placeholder='Select Country']")
	WebElement dropDownSelect;
	@FindBy(xpath="//button[contains(@class, 'ta-item')][2]")
	WebElement selectDesired;
	@FindBy(css=".action__submit")
	WebElement submitBtn;
	
	
	By results = By.cssSelector(".ta-results");
//	waitElementToAppear()
	
	public void selectCountry(String countryName) {
		Actions a = new Actions(driver);
		a.sendKeys(dropDownSelect, countryName).build().perform();	
		waitElementToAppear(results);
		selectDesired.click();
	}
	
	public ConfirmationPage2 submitOrder() {
		submitBtn.click();
		return new ConfirmationPage2(driver);
	}
}
