package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class LoginPage extends BasePage {

	// web driver
	@SuppressWarnings("unused")
	private WebDriver driver;

	// Username & pass
	String email;
	String password;


	// buttons
	public By register_page_button = By.className("register-button");
	public By login_submit_button = By.className("login-button");
	public By logout_button = By.className("ico-logout");

	// login fields
	public By email_field = By.id("Email");
	public By password_field = By.id("Password");

	// links
	public By shoppingcart_link = By.className("cart-label");
	public By forgotpasslink = By.className("forgot-password");

	// checkbox
	public By remembermecheckbox = By.id("RememberMe");

	// page check
	public By loginpage_check = By.className("page-title");
	public By account_check = By.className("account");
	public By Invalidpass_check = By.className("validation-summary-errors");
	public By Invalidemail_check = By.className("field-validation-error");
	
	///////////////////////////////////////////////////////////////////////////////////////
	//************************************Constructor************************************//
	///////////////////////////////////////////////////////////////////////////////////////
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	///////////////////////////////////////////////////////////////////////////////////////
	//************************************Methods****************************************//
	///////////////////////////////////////////////////////////////////////////////////////
	public void EnterLoginCredential(String mail, String pass) {
		// to save the mail of current user
		email = mail;
		
		ExplicitWaitUntilVisible(email_field,5);
		
		// send mail and pass
		BasePage.SetTextINField(email_field,mail);
		SetTextINField(password_field,pass);
	}

	public void LoginSubmit() {
		ExplicitWaitUntilVisible(login_submit_button,5);
		clickButton(login_submit_button);
	}

	public void LogoutSubmit()
	{
		ExplicitWaitUntilVisible(logout_button,5);
		clickButton(logout_button);
	}
	




}
