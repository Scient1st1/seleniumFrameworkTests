package Levan.SeleniumFramework;

import java.io.IOException;
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
import org.testng.annotations.Test;

import Levan.SeleniumFramework.testComponents.BaseTest;
import Levan.SeleniumFramework.testComponents.RetryDemo;
import io.github.bonigarcia.wdm.WebDriverManager;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups={"ErrorHandling"}, retryAnalyzer = RetryDemo.class)
	public void loginErrorValidation() throws IOException {
		
		ProductCatalogue prodCat = loginpage.loginHandler("tchlevan2@gmail.com", "Arma123xsovs1112");
//		.ng-tns-c4-13.ng-star-inserted.ng-trigger.ng-trigger-flyInOut.ngx-toastr.toast-error
		Assert.assertEquals("Incorrect email or password.", loginpage.getErrorMessage());
		
	}
	
	@Test
	public void productErrorValidation() {
		String prodName = "ZARA COAT 3";
		ProductCatalogue prodCat = loginpage.loginHandler("doctorr8@yahoo.com", "Armaxsovs1112");
		List <WebElement> products = prodCat.getProductList();
		prodCat.addProductToCart(prodName);
		CartPage cartPage = prodCat.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay(prodName);
		Assert.assertTrue(match);
	}

}
