package Levan.SeleniumFramework;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductCatalogue2 extends AbstactClass2 {
	WebDriver driver;

	public ProductCatalogue2(WebDriver driver) {
		super(driver);
		this.driver=driver;
		
		PageFactory.initElements(driver, this);
		
		
	}

	@FindBy(css=".mb-3")
	List<WebElement> products;
	
	By find = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastContainer = By.cssSelector("#toast-container");
	By spinner = By.cssSelector(".ng-animating");
	
	public List<WebElement> getProductList() {
		waitElementToAppear(find);
		return products;
	}
	
	public WebElement getProductByName(String productName) {
		WebElement prod = getProductList().stream().
				filter(product->product.findElement(By.cssSelector("b")).getText().equals(productName))
				.findFirst().orElse(null);
		return prod;
	}
	
	public void addProductToCart(String productName) {
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitElementToAppear(toastContainer);
		waitElementTodissapear(spinner);
	}
	

}
