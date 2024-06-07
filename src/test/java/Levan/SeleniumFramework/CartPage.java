package Levan.SeleniumFramework;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponent;
import io.github.bonigarcia.wdm.WebDriverManager;

public class CartPage extends AbstractComponent{

		WebDriver driver;
		
		public CartPage(WebDriver driver) {
			super(driver);
			this.driver = driver;
			PageFactory.initElements(driver, this);
//			during initialisation gives driver as parameter to every findby
		}
		
		
		
		@FindBy(css=".cartSection h3")	
		List <WebElement> cartProducts;
		
		@FindBy(css=".totalRow button")
		WebElement checkoutBtn;
		
		public boolean VerifyProductDisplay(String productName) {
			Boolean match = cartProducts.stream().anyMatch(element-> element.getText().equals(productName));
			return match;
		}
		
		
		public Checkout goToCheckout() {
			checkoutBtn.click();
			Checkout checkout = new Checkout(driver);
			return checkout;
		}
		
	
		
	
	
	

}
