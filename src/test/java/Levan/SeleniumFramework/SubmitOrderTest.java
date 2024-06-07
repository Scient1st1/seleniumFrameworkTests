package Levan.SeleniumFramework;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Levan.SeleniumFramework.testComponents.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest extends BaseTest {
	String prodName = "ZARA COAT 3";

	@Test(dataProvider="getData", groups= {"Purchase"})
	public void submitOrder(HashMap<String,String> input) throws IOException {

		ProductCatalogue prodCat = loginpage.loginHandler(input.get("email"), input.get("password"));
//		go to product catalog
//		add product to cart 
		List<WebElement> products = prodCat.getProductList();
		prodCat.addProductToCart(input.get("productName"));
//		go to cart page
		CartPage cartPage = prodCat.goToCartPage();

		Boolean match = cartPage.VerifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match);
//		go to checkout
		Checkout checkoutPage = cartPage.goToCheckout();

//		select country from autosuggestive dropdown
		checkoutPage.selectCountry("India");

//		click place order
		ConfirmationPage cp = checkoutPage.submitOrder();

//		go to confirm page
		String confirm = cp.verifyConfirmationMessage();
		System.out.println(confirm);
		Assert.assertTrue(confirm.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

	@Test(dependsOnMethods = { "submitOrder" })
	public void OrderHistoryTest() {
//			check if zara coat 3 is on orders page
		ProductCatalogue prodCat = loginpage.loginHandler("doctorr8@yahoo.com", "Armaxsovs1112");
		Orderpage ordersp = prodCat.goToOrdersPage();
		ordersp.VerifyOrderDisplay(prodName);
		Assert.assertTrue(ordersp.VerifyOrderDisplay(prodName));
	}
	
	
	
	@DataProvider()
	public Object[][] getData() throws IOException{
		
//		HashMap <Object,Object> map = new HashMap<Object,Object>();
//		map.put("email", "doctorr8@yahoo.com");
//		map.put("password", "Armaxsovs1112");
//		map.put("productName", "ZARA COAT 3");
//		
//		HashMap <Object,Object> map1 = new HashMap<Object,Object>();
//		map1.put("email", "tchlevan2@gmail.com");
//		map1.put("password", "Armaxsovs1112");
//		map1.put("productName", "ADIDAS ORIGINAL");
		String dir = System.getProperty("user.dir");
		System.out.println(dir);
		List<HashMap<String,String>> data = getJsonDataToMap(dir + "\\src\\test\\java\\Levan\\SeleniumFramework\\data\\PurchaseOrder.json");
		
		return new Object[][] {{data.get(0)},{data.get(1)}};
	};
	
//	@DataProvider()
//	public Object[][] getData1(){
//		return new Object[][] {{"doctorr8@yahoo.com"},{"Armaxsovs1112"}, {"ZARA COAT 3"}},
//				{{"tchlevan2@gmail.com"}, {"Armaxsovs1112"}, {"ADIDAS ORIGINAL"}}
//	}

}
