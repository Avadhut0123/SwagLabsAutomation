package avadhutwakkar.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import avadhutwakkar.AbstractComponents.AbstractComponents;

public class ProductPage extends AbstractComponents {
	WebDriver driver;

	public ProductPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".inventory_item")
	List<WebElement> products;

	@FindBy(css = ".inventory_details_back_button")
	private WebElement backButton;

	By waitproducts = By.cssSelector(".inventory_item");

	private List<WebElement> getProductList() {
		waitForElementToAppear(waitproducts);
		return products;
	}

	public WebElement findProductByName(String productName) {
		return getProductList().stream().filter(
				product -> product.findElement(By.cssSelector(".inventory_item_name ")).getText().equals(productName))
				.findFirst().orElseThrow(() -> new RuntimeException("Product not found: " + productName));
	}

	public void viewProductDetails(String productName) {
		WebElement product = findProductByName(productName);
		product.findElement(By.cssSelector(".inventory_item_name")).click();
	}

	public void goBackToProductList() {
		backButton.click();
	}

	public boolean openAndReturnFromProduct(String productName) {
		viewProductDetails(productName);
		goBackToProductList();
		return true;
	}
}
