package Levan.SeleniumFramework;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandaloneTest2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String prodName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		LoginPage2 loginPage = new LoginPage2(driver);
		loginPage.goToLoginPage();
		
		
		ProductCatalogue2 prodCat = loginPage.handleLogin("doctorr8@yahoo.com", "Armaxsovs1112");
		
		List<WebElement> products = prodCat.getProductList();
		prodCat.addProductToCart(prodName);
		CartPage2 cartPage = prodCat.goToCartPage();
		
		Boolean match = cartPage.verifyProductDisplay(prodName);
		Assert.assertTrue(match);
		
//		go to checkout
		CheckoutPage checkPage = cartPage.goToCheckout();
		checkPage.selectCountry("india");
		ConfirmationPage2 confirmPage = checkPage.submitOrder();

		
		String confirm = confirmPage.verifyConfirmationMsg();
		
		Assert.assertTrue(confirm.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
//		Assert.assertEquals(confirm, "THANKYOU FOR THE ORDER.");
	}

}
