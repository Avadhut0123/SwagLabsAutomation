package avadhutwakkar.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import avadhutwakkar.AbstractComponents.AbstractComponents;

public class ConfirmationPage extends AbstractComponents {
	
	WebDriver driver;

	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="h2[class='complete-header']")
	WebElement message;
	
	public String verifyConfirmationMessage() {
		return message.getText();
	}
}
