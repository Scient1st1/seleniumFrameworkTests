package Levan.SeleniumFramework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponent;

public class Checkout extends AbstractComponent{
	WebDriver driver;
	public Checkout(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}
	
	@FindBy(css="[placeholder='Select Country']")
	WebElement country;
	@FindBy(xpath="//button[contains(@class, 'ta-item')][2]")
	WebElement countrySelection;
	@FindBy(css=".action__submit")
	WebElement subtmitBtn;
	
	By result = By.cssSelector(".ta-results");
	
	public void selectCountry(String countryName) {
		Actions a = new Actions(driver);
		a.sendKeys(country, countryName).build().perform();
		waitForElementToAppear(result);
		countrySelection.click();

	}
	
	public ConfirmationPage submitOrder() {
		subtmitBtn.click();	
		ConfirmationPage cp = new ConfirmationPage(driver);
		return cp;
	}

}
