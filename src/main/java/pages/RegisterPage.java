package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends BasePage {
	// web driver
	final private WebDriver driver;

	// Fields
	@SuppressWarnings("unused")
	private String firstname;
	@SuppressWarnings("unused")
	private String lastname;
	@SuppressWarnings("unused")
	private String email;
	@SuppressWarnings("unused")
	private String password;
	@SuppressWarnings("unused")
	private String confirmpassword;

	// login fields
	public By firstname_field = By.id("FirstName");
	public By lastname_field = By.id("LastName");
	public By email_field = By.id("Email");
	public By password_field = By.id("Password");
	public By confirmpassword_field = By.id("ConfirmPassword");

	// radiobutton
	public By gender_male_radiobutton = By.id("gender-male");
	public By gender_female_radiobutton = By.id("gender-female");

	// submit button
	public By register_button = By.id("register-button");

	// pagecheck
	public By registerpage_check = By.className("page-title");

	public By emailexist_check_error = By.className("validation-summary-errors");

	public RegisterPage(WebDriver driver) {
		this.driver = driver;
	}


	public void RegisterSubmit() {
		ExplicitWaitUntilVisible(register_button, 5);
		driver.findElement(register_button).click();
	}

	public void EnterRegisterData(String fn, String ln, String email, String pass, String confpass, String gender) {
		ExplicitWaitUntilVisible(firstname_field, 5);
		driver.findElement(firstname_field).sendKeys(fn);
		driver.findElement(lastname_field).sendKeys(ln);
		driver.findElement(email_field).sendKeys(email);
		driver.findElement(password_field).sendKeys(pass);
		driver.findElement(confirmpassword_field).sendKeys(confpass);
		if (gender.equals("m")) {
			driver.findElement(gender_male_radiobutton).click();
		} else if (gender.equals("f")) {
			driver.findElement(gender_female_radiobutton).click();
		}
	}

}
