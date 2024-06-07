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

public class Orderpage extends AbstractComponent{

		WebDriver driver;
		
		public Orderpage(WebDriver driver) {
			super(driver);
			this.driver = driver;
			PageFactory.initElements(driver, this);
//			during initialisation gives driver as parameter to every findby
		}
		
		
		
//		tr td:nth-child(3)
		@FindBy(css="tr td:nth-child(3)")
		List<WebElement> productNames;
		
		public boolean VerifyOrderDisplay(String productName) {
			Boolean match = productNames.stream().anyMatch(element-> element.getText().equals(productName));
			return match;
		}
		
	
		
	
		
	
	
	

}
