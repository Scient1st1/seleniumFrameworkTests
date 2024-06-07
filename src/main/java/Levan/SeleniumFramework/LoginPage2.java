package Levan.SeleniumFramework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage2 extends AbstactClass2{
	WebDriver driver;

	public LoginPage2(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
		
	}

	@FindBy(id="userEmail")
	WebElement userEmail;
	@FindBy(id="userPassword")
	WebElement userPassword;
	@FindBy(id="login")
	WebElement submitData;
	
	public ProductCatalogue2 handleLogin(String email, String password) {
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		submitData.click();
		ProductCatalogue2 prodCat2 = new ProductCatalogue2(driver);
		return prodCat2;
	}
	public void goToLoginPage() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	

}
