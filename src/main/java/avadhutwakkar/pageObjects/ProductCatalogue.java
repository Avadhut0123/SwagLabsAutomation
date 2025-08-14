package avadhutwakkar.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import avadhutwakkar.AbstractComponents.AbstractComponents;

public class ProductCatalogue extends AbstractComponents {

	WebDriver driver;

	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".inventory_item")
	List<WebElement> products;

	By waitproducts = By.cssSelector(".inventory_item");
	By addtoCart = By.cssSelector(".btn_inventory ");

	public List<WebElement> getProductList() {
		waitForElementToAppear(waitproducts);
		return products;
	}
	
	public WebElement getProductByName(String productName) {
		WebElement prod = getProductList().stream().filter(
				product -> product.findElement(By.cssSelector(".inventory_item_name ")).getText().equals(productName))
				.findFirst().orElse(null);
		return prod;
	}
	
	public void addProductToCart(String productName) {
		WebElement prod = getProductByName(productName);
		prod.findElement(addtoCart).click();
	}

}