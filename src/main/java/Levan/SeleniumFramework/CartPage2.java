package Levan.SeleniumFramework;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage2 extends AbstactClass2{
	WebDriver driver;

	public CartPage2(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
		
	}
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartProducts;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutBtn;
	
	public Boolean verifyProductDisplay(String prodName) {
		Boolean match = cartProducts.stream().anyMatch(element-> element.getText().equals(prodName));
		return match;
	}
	
	public CheckoutPage goToCheckout() {
		checkoutBtn.click();
		return new CheckoutPage(driver);
	}

}
