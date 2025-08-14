package avadhutwakkar.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import avadhutwakkar.AbstractComponents.AbstractComponents;

public class LogoutAction extends AbstractComponents {
	WebDriver driver;

	public LogoutAction(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="button[id='back-to-products']")
	WebElement backToProducts;
	
	@FindBy(id="react-burger-menu-btn")
	WebElement menu;
	
	By waitForlogout = By.id("logout_sidebar_link");
	
	public void backToProducts() {
		backToProducts.click();
	}
	
	public void logoutThePage() {
		menu.click();
		WebElement logout = waitForElementToAppear(waitForlogout);
		logout.click();
	}
}
