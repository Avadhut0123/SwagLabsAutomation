package avadhutwakkar.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import avadhutwakkar.AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents {
	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".shopping_cart_link")
	WebElement gotoCart;
	
	@FindBy(css="div[class='inventory_item_name']")
	List<WebElement> getProductListfromCart;
	
	@FindBy(css=".checkout_button")
	WebElement checkout;
	
	public Boolean ConfirmPage(String productName) {
		gotoCart.click();
		Boolean match = getProductListfromCart.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
	public CheckoutPage gotoCheckout() {
		checkout.click();
		return new CheckoutPage(driver);
	}

}
