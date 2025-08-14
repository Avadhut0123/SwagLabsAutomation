package avadhutwakkar.TestCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import avadhutwakkar.TestComponents.BaseTests;
import avadhutwakkar.TestComponents.Retry;
import avadhutwakkar.pageObjects.CartPage;
import avadhutwakkar.pageObjects.CheckoutPage;
import avadhutwakkar.pageObjects.LogoutAction;
import avadhutwakkar.pageObjects.ProductCatalogue;

public class LoginErrorValidationTest extends BaseTests {

	@Test(groups = {"ErrorHandling"},retryAnalyzer=Retry.class)
	public void LoginWithLockedUser() throws IOException {
		landingpage.loginApplication("locked_out_user", "secret_sauce");
		Assert.assertEquals("Epic sadface: Sorry, this user has been locked out.", landingpage.getErrorMessage());
	}
	
	@Test
	public void LoginWithProblemUser() throws IOException {
		String productName = "Sauce Labs Bike Light";
		ProductCatalogue productcatalogue = landingpage.loginApplication("problem_user", "secret_sauce");
		
		productcatalogue.getProductList();
		productcatalogue.addProductToCart(productName);
		
		CartPage cartpage = new CartPage(driver);
		Boolean match = cartpage.ConfirmPage(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutpage = cartpage.gotoCheckout();
		checkoutpage.checkoutPage("Legend", "Tester", "125478");
		checkoutpage.submitOrder();
		Assert.assertEquals("Error: Last Name is required",checkoutpage.getErrorMessage());
		
		LogoutAction logoutpage = new LogoutAction(driver);
		logoutpage.logoutThePage();
	}
	
}
