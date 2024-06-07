package Levan.SeleniumFramework;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstactClass2 {
	WebDriver driver;
	public AbstactClass2(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
	
	
	@FindBy(css="[routerlink*='cart']")
	WebElement cartBtn;
	
	
	public CartPage2 goToCartPage() {
		cartBtn.click();
		CartPage2 cartPage = new CartPage2(driver);
		return cartPage;
	}
	
	public void waitElementToAppear(By ByLocator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	 	wait.until(ExpectedConditions.visibilityOfElementLocated(ByLocator));
	}
	
	public void waitElementTodissapear(By ByLocator) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(ByLocator));
	}
}
