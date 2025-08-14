package avadhutwakkar.TestCases;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		String productName = "Sauce Labs Bike Light";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get("https://www.saucedemo.com/");
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".inventory_item")));

		List<WebElement> products = driver.findElements(By.cssSelector(".inventory_item"));

		WebElement prod = products.stream().filter(
				product -> product.findElement(By.cssSelector(".inventory_item_name ")).getText().equals(productName))
				.findFirst().orElse(null);
		prod.findElement(By.cssSelector(".btn_inventory ")).click();

		driver.findElement(By.cssSelector(".shopping_cart_link")).click();

		List<WebElement> cartProducts = driver.findElements(By.cssSelector("div[class='inventory_item_name']"));
		Boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		
		driver.findElement(By.cssSelector(".checkout_button")).click();

		driver.findElement(By.cssSelector("input[placeholder='First Name']")).sendKeys("Legend");
		driver.findElement(By.cssSelector("input[placeholder='Last Name']")).sendKeys("Tester");
		driver.findElement(By.cssSelector("input[placeholder='Zip/Postal Code']")).sendKeys("125478");
		driver.findElement(By.xpath("//input[@id='continue']")).click();

		String paymentInfo = driver.findElement(By.xpath("//div[@class='summary_value_label'][1]")).getText();
		System.out.println("Payment Information: " + paymentInfo);

		String shippingInfo = driver.findElement(By.xpath("//div[@class='summary_value_label'][2]")).getText();
		System.out.println("Shipping Information: " + shippingInfo);

		String totalAmount = driver.findElement(By.cssSelector("div[data-test='total-label']")).getText();
		String[] amount = totalAmount.split(" ");
		System.out.println("Total Order Amount: " + amount[1]);

		driver.findElement(By.id("finish")).click();

		String successMessage = driver.findElement(By.cssSelector("h2[class='complete-header']")).getText();
		Assert.assertTrue(successMessage.equalsIgnoreCase("Thank you for your order!"));

		driver.findElement(By.cssSelector("button[id='back-to-products']")).click();
		
		driver.findElement(By.id("react-burger-menu-btn")).click();
		WebElement logout = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout_sidebar_link")));
		
		logout.click();
		driver.quit();
	}

}
