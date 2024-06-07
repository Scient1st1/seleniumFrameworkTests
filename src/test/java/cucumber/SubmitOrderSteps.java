package cucumber;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Levan.SeleniumFramework.CartPage;
import Levan.SeleniumFramework.Checkout;
import Levan.SeleniumFramework.ConfirmationPage;
import Levan.SeleniumFramework.LoginPage;
import Levan.SeleniumFramework.ProductCatalogue;
import Levan.SeleniumFramework.testComponents.BaseTest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SubmitOrderSteps extends BaseTest {
	public LoginPage loginpage;
	public ProductCatalogue prodCat;
	public ConfirmationPage cp;
	@Given("I landed on Ecommerce Page")
	public void i_landed_on_ecommerce_page() throws IOException {
		loginpage = launchApp();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_with_username_and_password(String username, String password) {
		prodCat = loginpage.loginHandler(username,password);
	}
	@When("^I add the product (.+) from cart$")
	public void I_add_the_product_from_cart(String productName) {
		List<WebElement> products = prodCat.getProductList();
		prodCat.addProductToCart(productName);
	}
	@When("^Checkout (.+) and submit the order$")
	public void checkout_and_submit_the_order(String productName) {
		CartPage cartPage = prodCat.goToCartPage();

		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		Checkout checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("India");
		cp = checkoutPage.submitOrder();
	}
	@Then("the message {string} is displayed on the confirmation page")
	public void message_is_displayed_on_confirmationPage(String string) {
		String confirm = cp.verifyConfirmationMessage();
		Assert.assertTrue(confirm.equalsIgnoreCase(string));
		driver.close();
	}
	
}
