package avadhutwakkar.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import avadhutwakkar.AbstractComponents.AbstractComponents;

public class CheckoutPage extends AbstractComponents{
	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="input[placeholder='First Name']")
	WebElement firstName;
	
	@FindBy(css="input[placeholder='Last Name']")
	WebElement lastName;
	
	@FindBy(css="input[placeholder='Zip/Postal Code']")
	WebElement pincode;
	
	@FindBy(xpath="//input[@id='continue']")
	WebElement Continue;
	
	@FindBy(tagName="h3")
	WebElement errorMessage;
	
	public void checkoutPage(String Name,String Surname,String Zipcode) {
		firstName.sendKeys(Name);
		lastName.sendKeys(Surname);
		pincode.sendKeys(Zipcode);
	}
	
	public OrderDetails submitOrder() {
		Continue.click();
		return new OrderDetails(driver);
	}
	
	public String getErrorMessage() {
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
}
