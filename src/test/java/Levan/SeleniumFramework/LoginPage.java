package Levan.SeleniumFramework;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponent;
import io.github.bonigarcia.wdm.WebDriverManager;

public class LoginPage extends AbstractComponent {
		
		WebDriver driver;
		
		
		public LoginPage(WebDriver driver) {
			super(driver);
			this.driver = driver;
			PageFactory.initElements(driver, this);
//			during initialisation gives driver as parameter to every findby
		}
		
//		WebElement userEmail = driver.findElement(By.id("userEmail"));
//		PageFactory- reduces the syntax
		@FindBy(id="userEmail")
		WebElement userEmail;
		
		@FindBy(id="userPassword")
		WebElement passwordEl;
		
		@FindBy(id="login")
		WebElement submit;
		
//		.ng-tns-c4-13.ng-star-inserted.ng-trigger.ng-trigger-flyInOut.ngx-toastr.toast-error
		@FindBy(css="[class*='flyInOut']")
		WebElement errorMessage;
		
		public String getErrorMessage() {
			waitForWebElementToAppear(errorMessage);
			String msg = errorMessage.getText();
			return msg;
		}
		
		public ProductCatalogue loginHandler(String email, String password) {
			userEmail.sendKeys(email);
			passwordEl.sendKeys(password);
			submit.click();
			ProductCatalogue prodCat = new ProductCatalogue(driver);
			return prodCat;
		}
		public void goTo() {
			driver.get("https://rahulshettyacademy.com/client");
		}
	

}
