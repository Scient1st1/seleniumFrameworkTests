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

public class ProductCatalogue extends AbstractComponent{

		WebDriver driver;
		
		public ProductCatalogue(WebDriver driver) {
			super(driver);
			this.driver = driver;
			PageFactory.initElements(driver, this);
//			during initialisation gives driver as parameter to every findby
		}
		
//		WebElement userEmail = driver.findElement(By.id("userEmail"));
//		PageFactory- reduces the syntax
		
//		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		
		@FindBy(css=".mb-3")	
		List <WebElement> products;
		
		@FindBy(css=".ng-animating")
		WebElement spinner;
		
		By productsBy = By.cssSelector(".mb-3");
		By addToCart = By.cssSelector(".card-body button:last-of-type");
		By toastMessage = By.cssSelector("#toast-container");
		
		public List<WebElement> getProductList() {
			waitForElementToAppear(productsBy);
			return products;
		}
		
		public WebElement getProductByName(String productName) {
			return getProductList().stream().
					filter(product->product.findElement(By.cssSelector("b")).getText().equals(productName))
					.findFirst().orElse(null);
		}
		
		public void addProductToCart(String productName) {
			WebElement prod = getProductByName(productName);
			prod.findElement(addToCart).click();
			waitForElementToAppear(toastMessage);
			waitForElementToDissapear(spinner);
		}
		
	
	

}
