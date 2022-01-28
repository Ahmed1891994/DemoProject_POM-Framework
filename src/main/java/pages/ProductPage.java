package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage extends BasePage {
	// web driver
	private WebDriver driver;

	//////////////////////////////// start Generic product
	//////////////////////////////// variables//////////////////////////////////////
	// quantity field
	public By productquantity = By.className("qty-input");
	public By addtocart = By.id("add-to-cart-button-75");
	public By cart = By.id("topcartlink");
	public By productadded_check = By.id("bar-notification");
	public By page_check= By.className("product-name");
	//////////////////////////////// end Generic product
	//////////////////////////////// variables////////////////////////////////////////

	///////////////////////////// start Desktops product
	///////////////////////////// variables////////////////////////////////////////
	// processor
	private By processor_radiobutton = By.xpath("//div[@class='attributes']//dd[1]//ul//li//input");

	// software
	private By image_viewer = By.xpath("//div[@class='attributes']//dd[4]//ul//li[1]//input");
	private By office_suite = By.xpath("//div[@class='attributes']//dd[4]//ul//li[2]//input");
	///////////////////////////// end Desktops product
	///////////////////////////// variables//////////////////////////////////////////

	// constructor
	public ProductPage(WebDriver driver) {
		this.driver = driver;
	}

	//////////////////////////////// start Generic product
	//////////////////////////////// methods////////////////////////////////////////
	public void AddQuantity(int quantity) {
		// converting from INTEGER to int
		Integer quantityINTEGER = Integer.valueOf(quantity);

		// send number of products to buy
		driver.findElement(productquantity).clear();
		driver.findElement(productquantity).sendKeys(quantityINTEGER.toString());
	}

	public void AddToCart() {
		driver.findElement(addtocart).click();
		WebDriverWait wait = new WebDriverWait(driver,  Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(productadded_check));
	}

	public void WaitBar()
	{
		ExplicitWaitUntilVisible(productadded_check,5);
		ExplicitWaitUntilInVisible(productadded_check,5);
	}
	
	public void GoToCart() {
		WaitBar();
		driver.findElement(cart).click();
	}

	//////////////////////////////// end Generic product
	//////////////////////////////// methods/////////////////////////////////////////

	///////////////////////////// start Desktops product
	///////////////////////////// methods////////////////////////////////////////

	public void DesktopsSPecification(boolean processor, int ram, int hdd, boolean image, boolean office) {
		if (processor) {
			if (driver.findElement(processor_radiobutton).isSelected() == false)
				driver.findElement(processor_radiobutton).click();
		} else
			;

		if (image) {
			if (driver.findElement(image_viewer).isSelected() == false)
				driver.findElement(image_viewer).click();
		} else
			;
		if (office) {
			if (driver.findElement(office_suite).isSelected() == false)
				driver.findElement(office_suite).click();
		} else
			;
		driver.findElement(By.xpath("//div[@class='attributes']//dd[2]//ul//li[" + ram + "]//input")).click();
		driver.findElement(By.xpath("//div[@class='attributes']//dd[3]//ul//li[" + hdd + "]//input")).click();
	}


}
