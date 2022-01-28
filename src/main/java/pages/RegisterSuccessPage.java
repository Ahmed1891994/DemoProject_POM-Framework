package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterSuccessPage extends BasePage{

	// web driver
	final private WebDriver driver;

	// pagecheck
	public By registerpagedone_check = By.className("result");

	//button
	public By continue_button = By.className("register-continue-button");
	
	public RegisterSuccessPage(WebDriver driver) {
		this.driver = driver;
	}
	public void ContinueSubmitLogin()
	{
		ExplicitWaitUntilVisible(continue_button, 5);
		driver.findElement(continue_button).click();
	}
}
