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

public class StandaloneTest2Unchanged {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String prodName = "ZARA COAT 3";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("doctorr8@yahoo.com");
		driver.findElement(By.id("userPassword")).sendKeys("Armaxsovs1112");
		driver.findElement(By.id("login")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		
		WebElement prod = products.stream().
		filter(product->product.findElement(By.cssSelector("b")).getText().equals(prodName))
		.findFirst().orElse(null);
		
//		.card-body button:last-of-type
		
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));

//		ng-animating
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		
//		catch product titles
		List <WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		
		Boolean match = cartProducts.stream().anyMatch(element-> element.getText().equals(prodName));
		Assert.assertTrue(match);
		
//		go to checkout
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
//		select country from autosuggestive dropdown
		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
		
//		wait untill dropdown select shows 
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
		
		driver.findElement(By.xpath("//button[contains(@class, 'ta-item')][2]")).click();
//		button[contains(@class, 'ta-item')][2]
		
//		click place order
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		String confirm = driver.findElement(By.cssSelector(".hero-primary")).getText();
		System.out.println(confirm);
		Assert.assertTrue(confirm.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
//		Assert.assertEquals(confirm, "THANKYOU FOR THE ORDER.");
	}

}
