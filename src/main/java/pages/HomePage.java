package pages;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class HomePage extends BasePage {

	// web driver
	private WebDriver driver;

	// instances of utilities
	BasePage util;
	Properties prop;

	// title
	By hometitle = By.xpath("//img[@alt='Tricentis Demo Web Shop']");

	// buttons
	public By register_link = By.className("ico-register");
	public By login_link = By.className("ico-login");
	public By logout_link = By.cssSelector(".ico-logout");

	// Menu
	public By Menu_Choice = By.className("top-menu");
	public By Computers_Choice = By.linkText("Computers");
	public By Desktops_Choice = By.linkText("Desktops");

	// page check
	public By homepage_check = By.className("topic-html-content-header");

	// links
	private By address = By.xpath("//div[@class='column my-account']//a[normalize-space()='Addresses']");

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	// login link
	public void EnterLoginPage() {
		ExplicitWaitUntilVisible(register_link,5);
		driver.findElement(login_link).click();
	}

	// register link
	public void EnterRegisterPage() {
		ExplicitWaitUntilVisible(register_link,5);
		driver.findElement(register_link).click();
	}

	// logout link
	public void PressLogoutLink() {
		ExplicitWaitUntilVisible(logout_link,5);
		driver.findElement(logout_link).click();
	}

	public void ChooseTypeProducts(int menuindex, int submenuindex) {

		Actions a = new Actions(driver);
		WebElement menuindexelement = driver.findElement(By.xpath("//ul[@class='top-menu']//li[" + menuindex + "]"));
		if (submenuindex != 0) {
			a.moveToElement(menuindexelement).perform();
			WebElement submenuindexelement = driver.findElement(
					By.xpath("//ul[@class='top-menu']//li[" + menuindex + "]//ul[1]//li[" + submenuindex + "]"));
			a.moveToElement(submenuindexelement).click().perform();
		} else {
			a.moveToElement(menuindexelement).click().perform();
		}
	}

	public void AddressLink() {
		ExplicitWaitUntilVisible(address, 5);
		driver.findElement(address).click();
	}

	public void ReturnHomePage() {
		ExplicitWaitUntilVisible(hometitle, 5);
		driver.findElement(hometitle).click();
	}

}
