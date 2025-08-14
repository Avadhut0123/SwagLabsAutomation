package avadhutwakkar.TestCases;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import avadhutwakkar.pageObjects.CheckoutPage;
import avadhutwakkar.pageObjects.ConfirmationPage;
import avadhutwakkar.TestComponents.BaseTests;
import avadhutwakkar.pageObjects.CartPage;
import avadhutwakkar.pageObjects.OrderDetails;
import avadhutwakkar.pageObjects.ProductCatalogue;
import avadhutwakkar.pageObjects.ProductPage;

public class SubmitOrderTest extends BaseTests {

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void SubmitOrder(HashMap<String, String> input) throws IOException {

		ProductCatalogue productcatalogue = landingpage.loginApplication(input.get("email"), input.get("password"));
		productcatalogue.getProductList();
		productcatalogue.addProductToCart(input.get("productName"));

		CartPage cartpage = new CartPage(driver);
		Boolean match = cartpage.ConfirmPage(input.get("productName"));
		Assert.assertTrue(match);

		CheckoutPage checkoutpage = cartpage.gotoCheckout();
		checkoutpage.checkoutPage("Legend", "Tester", "125478");
		OrderDetails orderdetails = checkoutpage.submitOrder();

		String[] details = orderdetails.getOrderdetails();
		System.out.println("Payment Information: " + details[0]);
		System.out.println("Shipping Information: " + details[1]);
		System.out.println("Total Order Amount: " + details[2]);

		ConfirmationPage confirmationpage = orderdetails.goToConfirmationPage();

		String successMessage = confirmationpage.verifyConfirmationMessage();
		Assert.assertTrue(successMessage.equalsIgnoreCase("Thank you for your order!"));

		performLogoutWithBack();
	}

	@Test(dependsOnMethods = { "SubmitOrder" })
	public void productPage() {
		String productName = "Sauce Labs Bike Light";
		landingpage.loginApplication("standard_user", "secret_sauce");
		ProductPage productPage = new ProductPage(driver);
		Assert.assertTrue(productPage.openAndReturnFromProduct(productName));
		performLogout();
	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonDataToHashMap(System.getProperty("user.dir") + "\\src\\test\\java\\avadhutwakkar\\data\\PurchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}
}
