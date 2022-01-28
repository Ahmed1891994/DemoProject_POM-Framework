package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ShoppingCartPage extends BasePage {

	// web driver
	final private WebDriver driver;

	public By page_check = By.className("page-title");


	public By totalprice = By.className("product-price");

	public By discountfield = By.xpath("//input[@name='discountcouponcode']");
	public By discountapply = By.xpath("//input[@name='applydiscountcouponcode']");
	public By discountcheck = By.className("message");

	public By checkout_button = By.id("checkout");

	public By termofservice_checkbox = By.id("termsofservice");
	public By termofservicealert = By
			.xpath("//p[contains(text(),'Please accept the terms of service before the next')]");
	public By termofserviceclosemsg = By.className("ui-icon-closethick");

	// constructor
	public ShoppingCartPage(WebDriver driver) {
		this.driver = driver;
	}


	public void EnterDiscountCode(String code) {
		if (code.equals("AutomationDiscount2")) {
			driver.findElement(discountfield).sendKeys(code);
		}
		driver.findElement(discountapply).click();
	}

	public boolean CheckDiscountDone() {
		// String discount =
		// driver.findElement(By.xpath("//table[@class='cart-total']//tbody//tr[4]//td[2]")).getText();
		String pricewithoutdiscount = driver.findElement(By.xpath("//table[@class='cart-total']//tbody//tr[1]//td[2]"))
				.getText();
		String pricewithdiscount = driver.findElement(By.xpath("//table[@class='cart-total']//tbody//tr[5]//td[2]"))
				.getText();
		double result = Double.parseDouble(pricewithoutdiscount)
				- (Double.parseDouble(pricewithoutdiscount) * 20 / 100);
		return Double.valueOf(result).intValue() ==  Double.valueOf(pricewithdiscount).intValue();
	}

	public void Click_Checkout() {
		ExplicitWaitUntilVisible(checkout_button, 5);
		driver.findElement(checkout_button).click();
	}
	
	public void CloseTermOfServiceALert()
	{
		ExplicitWaitUntilVisible(termofserviceclosemsg, 5);
		driver.findElement(termofserviceclosemsg).click();
	}

	public void EnableCheckout() {
		ExplicitWaitUntilVisible(termofservice_checkbox, 5);
		if (driver.findElement(termofservice_checkbox).isSelected() == false) {
			driver.findElement(termofservice_checkbox).click();
		}
		ExplicitWaitUntilVisible(checkout_button, 5);
		driver.findElement(checkout_button).click();
	}

}
