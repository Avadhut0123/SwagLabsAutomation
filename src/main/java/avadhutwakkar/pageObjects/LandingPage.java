package avadhutwakkar.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import avadhutwakkar.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents{

	WebDriver driver;

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Page Factory
	@FindBy(id = "user-name")
	WebElement userEmail;

	@FindBy(id = "password")
	WebElement userpass;

	@FindBy(id = "login-button")
	WebElement login;
	
	@FindBy(tagName="h3")
	WebElement errorMessage;

	public ProductCatalogue loginApplication(String email, String password) {
		userEmail.sendKeys(email);
		userpass.sendKeys(password);
		login.click();
		return new ProductCatalogue(driver);
	}
	
	public String getErrorMessage() {
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
	public void goTo() {
		driver.get("https://www.saucedemo.com/");
	}
}
