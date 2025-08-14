package avadhutwakkar.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import avadhutwakkar.AbstractComponents.AbstractComponents;

public class OrderDetails extends AbstractComponents {

	WebDriver driver;

	public OrderDetails(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='summary_value_label'][1]")
	WebElement paymentInfo;

	@FindBy(xpath = "//div[@class='summary_value_label'][2]")
	WebElement shippingInfo;

	@FindBy(css = "div[data-test='total-label']")
	WebElement totalAmount;

	@FindBy(id = "finish")
	WebElement confirmOrder;

	public String[] getOrderdetails() {
		String payinfo = paymentInfo.getText();
		String shipinfo = shippingInfo.getText();
		String Amount = totalAmount.getText();
		String[] amountParts = Amount.split(" ");
		String amount = (amountParts.length > 1) ? amountParts[1] : "N/A";
		return new String[] { payinfo, shipinfo, amount };
	}

	public ConfirmationPage goToConfirmationPage() {
		confirmOrder.click();
		return new ConfirmationPage(driver);
	}
}
