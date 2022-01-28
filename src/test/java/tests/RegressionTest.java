package tests;

import java.util.Properties;

import org.junit.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pages.AddressesAccountPage;
import pages.BasePage;
import pages.CheckoutPage;
import pages.DesktopsPage;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductPage;
import pages.RegisterPage;
import pages.ShoppingCartPage;

@Listeners(utility.Listeners.class)

public class RegressionTest extends BasePage {

	// save data of the properties file in variables
	Properties prop = LoadProperties();

	// Pages
	HomePage homepage;
	LoginPage loginPage;
	RegisterPage registerpage;
	DesktopsPage desktoppage;
	ProductPage productpage;
	ShoppingCartPage shoppingcartPage;
	CheckoutPage checkoutoage;
	AddressesAccountPage addressesaccountpage;

	@Test(enabled = true, priority = 0, description = "register with preregistered Email")
	public void RegisterationWithExistemail() {

		homepage = new HomePage(driver);
		// check page
		Assert.assertEquals("Welcome to our store", GetText(homepage.homepage_check));
		// check Homepage elements
		Assert.assertTrue(IsDisplayed(homepage.register_link));
		Assert.assertTrue(IsNotExist(homepage.logout_link));

		homepage.EnterRegisterPage();

		registerpage = new RegisterPage(driver);
		Assert.assertTrue(BasePage.IsDisplayed((registerpage.firstname_field)));
		Assert.assertTrue(BasePage.IsDisplayed((registerpage.lastname_field)));
		Assert.assertTrue(BasePage.IsDisplayed((registerpage.email_field)));
		Assert.assertTrue(BasePage.IsDisplayed((registerpage.password_field)));
		Assert.assertTrue(BasePage.IsDisplayed((registerpage.confirmpassword_field)));
		Assert.assertTrue(BasePage.IsDisplayed((registerpage.gender_male_radiobutton)));
		Assert.assertTrue(BasePage.IsDisplayed((registerpage.gender_female_radiobutton)));
		Assert.assertTrue(BasePage.IsDisplayed((registerpage.register_button)));
		Assert.assertTrue(BasePage.IsDisplayed((registerpage.registerpage_check)));
		registerpage.EnterRegisterData(prop.getProperty("firstname"), prop.getProperty("lastname"),
				prop.getProperty("email"), prop.getProperty("password"), prop.getProperty("confirmpassword"),
				prop.getProperty("gender"));
		registerpage.RegisterSubmit();
		Assert.assertTrue(IsDisplayed((registerpage.emailexist_check_error)));
	}

	@Test(enabled = true, priority = 1, description = "Login with invalid email")
	public void InvalidEmailLogin() {
		homepage = new HomePage(driver);
		// check page
		Assert.assertEquals("Welcome to our store", GetText(homepage.homepage_check));
		// check Homepage elements
		Assert.assertTrue(IsDisplayed(homepage.login_link));
		Assert.assertTrue(IsNotExist(homepage.logout_link));

		homepage.EnterLoginPage();
		Assert.assertEquals("About login / registration", GetText(homepage.homepage_check));

		loginPage = new LoginPage(driver);
		Assert.assertTrue(BasePage.IsDisplayed((loginPage.email_field)));
		Assert.assertTrue(BasePage.IsDisplayed((loginPage.password_field)));
		Assert.assertTrue(BasePage.IsDisplayed((loginPage.remembermecheckbox)));
		Assert.assertTrue(BasePage.IsDisplayed((loginPage.login_submit_button)));
		Assert.assertTrue(BasePage.IsDisplayed((loginPage.loginpage_check)));
		Assert.assertTrue(IsNotExist(loginPage.logout_button));

		loginPage.EnterLoginCredential(prop.getProperty("email") + "0", prop.getProperty("password"));
		loginPage.LoginSubmit();
		Assert.assertTrue(BasePage.IsDisplayed((loginPage.Invalidemail_check)));
		Assert.assertTrue(IsNotExist(loginPage.Invalidpass_check));

	}

	@Test(enabled = true, priority = 1, description = "login with invalid password")
	public void InvalidPassLogin() {
		homepage = new HomePage(driver);
		// check page
		Assert.assertEquals("Welcome to our store", GetText(homepage.homepage_check));
		// check Homepage elements
		Assert.assertTrue(IsDisplayed(homepage.login_link));
		Assert.assertTrue(IsNotExist(homepage.logout_link));

		homepage.EnterLoginPage();
		Assert.assertEquals("About login / registration", GetText(homepage.homepage_check));

		loginPage = new LoginPage(driver);
		Assert.assertTrue(BasePage.IsDisplayed((loginPage.email_field)));
		Assert.assertTrue(BasePage.IsDisplayed((loginPage.password_field)));
		Assert.assertTrue(BasePage.IsDisplayed((loginPage.remembermecheckbox)));
		Assert.assertTrue(BasePage.IsDisplayed((loginPage.login_submit_button)));
		Assert.assertTrue(BasePage.IsDisplayed((loginPage.loginpage_check)));
		Assert.assertTrue(IsNotExist(loginPage.logout_button));

		loginPage.EnterLoginCredential(prop.getProperty("email"), prop.getProperty("password") + "0");
		loginPage.LoginSubmit();
		Assert.assertTrue(BasePage.IsDisplayed(loginPage.Invalidpass_check));
		Assert.assertTrue(IsNotExist(loginPage.Invalidemail_check));
	}

	@Test(enabled = true, priority = 1, description = "Apply Discount and check discount is done perfectly")
	public void CheckDiscountDone() {
		homepage = new HomePage(driver);
		// check page
		Assert.assertEquals("Welcome to our store", GetText(homepage.homepage_check));
		// check Homepage elements
		Assert.assertTrue(IsDisplayed(homepage.login_link));
		Assert.assertTrue(IsNotExist(homepage.logout_link));

		homepage.EnterLoginPage();
		Assert.assertEquals("About login / registration", GetText(homepage.homepage_check));

		loginPage = new LoginPage(driver);
		Assert.assertTrue(BasePage.IsDisplayed(loginPage.email_field));
		Assert.assertTrue(BasePage.IsDisplayed(loginPage.password_field));
		Assert.assertTrue(BasePage.IsDisplayed(loginPage.remembermecheckbox));
		Assert.assertTrue(BasePage.IsDisplayed(loginPage.login_submit_button));
		Assert.assertTrue(BasePage.IsDisplayed(loginPage.loginpage_check));
		Assert.assertTrue(IsNotExist(loginPage.logout_button));

		loginPage.EnterLoginCredential(prop.getProperty("email"), prop.getProperty("password"));
		loginPage.LoginSubmit();
		Assert.assertTrue(IsDisplayed(loginPage.logout_button));
		Assert.assertEquals(prop.getProperty("email"), GetText(loginPage.account_check));

		homepage.ChooseTypeProducts(2, 1);

		desktoppage = new DesktopsPage(driver);
		Assert.assertEquals("Desktops", GetText(desktoppage.page_check));
		Assert.assertTrue(IsDisplayed(desktoppage.simplecomputer_product));
		desktoppage.SelectProductByPic(6);

		productpage = new ProductPage(driver);
		Assert.assertEquals("Simple Computer", GetText(productpage.page_check));
		productpage.DesktopsSPecification(true, 2, 1, false, false);
		Assert.assertTrue(IsDisplayed(productpage.productquantity));
		Assert.assertTrue(IsDisplayed(productpage.addtocart));
		Assert.assertTrue(IsDisplayed(productpage.cart));
		productpage.AddQuantity(1);
		productpage.AddToCart();
		Assert.assertTrue(IsDisplayed(productpage.productadded_check));
		productpage.GoToCart();

		shoppingcartPage = new ShoppingCartPage(driver);
		Assert.assertEquals("Shopping cart", GetText(shoppingcartPage.page_check));

		Assert.assertTrue(IsDisplayed(shoppingcartPage.discountfield));
		Assert.assertTrue(IsDisplayed(shoppingcartPage.discountapply));
		shoppingcartPage.EnterDiscountCode(prop.getProperty("discountcode"));
		Assert.assertTrue(shoppingcartPage.CheckDiscountDone());

		loginPage.LogoutSubmit();
		ExplicitWaitUntilVisible(homepage.login_link, 5);
		Assert.assertTrue(IsDisplayed(homepage.login_link));
	}

	@Test(enabled = true, priority = 1, description = "Check that checkoutPage can't be opened without approving Terms")
	public void CheckoutWithoutTerms() {
		homepage = new HomePage(driver);
		// check page
		Assert.assertEquals("Welcome to our store", GetText(homepage.homepage_check));
		// check Homepage elements
		Assert.assertTrue(IsDisplayed(homepage.login_link));
		Assert.assertTrue(IsNotExist(homepage.logout_link));

		homepage.EnterLoginPage();
		Assert.assertEquals("About login / registration", GetText(homepage.homepage_check));

		loginPage = new LoginPage(driver);
		Assert.assertTrue(BasePage.IsDisplayed(loginPage.email_field));
		Assert.assertTrue(BasePage.IsDisplayed(loginPage.password_field));
		Assert.assertTrue(BasePage.IsDisplayed(loginPage.remembermecheckbox));
		Assert.assertTrue(BasePage.IsDisplayed(loginPage.login_submit_button));
		Assert.assertTrue(BasePage.IsDisplayed(loginPage.loginpage_check));
		Assert.assertTrue(IsNotExist(loginPage.logout_button));

		loginPage.EnterLoginCredential(prop.getProperty("email"), prop.getProperty("password"));
		loginPage.LoginSubmit();
		Assert.assertTrue(IsDisplayed(loginPage.logout_button));
		Assert.assertEquals(prop.getProperty("email"), GetText(loginPage.account_check));

		homepage.ChooseTypeProducts(2, 1);

		desktoppage = new DesktopsPage(driver);
		Assert.assertEquals("Desktops", GetText(desktoppage.page_check));
		Assert.assertTrue(IsDisplayed(desktoppage.simplecomputer_product));
		desktoppage.SelectProductByPic(6);

		productpage = new ProductPage(driver);
		Assert.assertEquals("Simple Computer", GetText(productpage.page_check));
		productpage.DesktopsSPecification(true, 2, 1, false, false);
		Assert.assertTrue(IsDisplayed(productpage.productquantity));
		Assert.assertTrue(IsDisplayed(productpage.addtocart));
		Assert.assertTrue(IsDisplayed(productpage.cart));
		productpage.AddQuantity(1);
		productpage.AddToCart();
		Assert.assertTrue(IsDisplayed(productpage.productadded_check));
		productpage.GoToCart();

		shoppingcartPage = new ShoppingCartPage(driver);
		Assert.assertEquals("Shopping cart", GetText(shoppingcartPage.page_check));

		Assert.assertTrue(IsDisplayed(shoppingcartPage.discountfield));
		Assert.assertTrue(IsDisplayed(shoppingcartPage.discountapply));
		shoppingcartPage.EnterDiscountCode(prop.getProperty("discountcode"));
		Assert.assertTrue(shoppingcartPage.CheckDiscountDone());

		Assert.assertTrue(IsDisplayed(shoppingcartPage.checkout_button));
		shoppingcartPage.Click_Checkout();

		Assert.assertTrue(BasePage.IsDisplayed(shoppingcartPage.termofservicealert));
		shoppingcartPage.CloseTermOfServiceALert();

		Assert.assertTrue(IsDisplayed(shoppingcartPage.checkout_button));
		shoppingcartPage.Click_Checkout();

		Assert.assertTrue(BasePage.IsDisplayed(shoppingcartPage.termofservicealert));
		shoppingcartPage.CloseTermOfServiceALert();

		shoppingcartPage.EnableCheckout();
		checkoutoage = new CheckoutPage(driver);
		Assert.assertEquals("Checkout", GetText(checkoutoage.page_check));
	}

	@Test(enabled = true, priority = 1, description = "Checkout that Shipping address can't be done without Filling Address")
	public void CheckBillingAddressRequired() {
		homepage = new HomePage(driver);
		// check page
		Assert.assertEquals("Welcome to our store", GetText(homepage.homepage_check));
		// check Homepage elements
		Assert.assertTrue(IsDisplayed(homepage.login_link));
		Assert.assertTrue(IsNotExist(homepage.logout_link));

		homepage.EnterLoginPage();
		Assert.assertEquals("About login / registration", GetText(homepage.homepage_check));

		loginPage = new LoginPage(driver);
		Assert.assertTrue(IsDisplayed(loginPage.email_field));
		Assert.assertTrue(IsDisplayed(loginPage.password_field));
		Assert.assertTrue(IsDisplayed(loginPage.remembermecheckbox));
		Assert.assertTrue(IsDisplayed(loginPage.login_submit_button));
		Assert.assertTrue(IsDisplayed(loginPage.loginpage_check));
		Assert.assertTrue(IsNotExist(loginPage.logout_button));

		loginPage.EnterLoginCredential(prop.getProperty("email"), prop.getProperty("password"));
		loginPage.LoginSubmit();
		Assert.assertTrue(IsDisplayed(loginPage.logout_button));
		Assert.assertEquals(prop.getProperty("email"), GetText(loginPage.account_check));

		homepage.AddressLink();

		addressesaccountpage = new AddressesAccountPage(driver);
		addressesaccountpage.DeleteAllAddresses();

		homepage.ReturnHomePage();
		// check page
		Assert.assertEquals("Welcome to our store", GetText(homepage.homepage_check));

		homepage.ChooseTypeProducts(2, 1);

		desktoppage = new DesktopsPage(driver);
		Assert.assertEquals("Desktops", GetText(desktoppage.page_check));
		Assert.assertTrue(IsDisplayed(desktoppage.simplecomputer_product));
		desktoppage.SelectProductByPic(6);

		productpage = new ProductPage(driver);
		Assert.assertEquals("Simple Computer", GetText(productpage.page_check));
		productpage.DesktopsSPecification(true, 2, 1, false, false);
		Assert.assertTrue(IsDisplayed(productpage.productquantity));
		Assert.assertTrue(IsDisplayed(productpage.addtocart));
		Assert.assertTrue(IsDisplayed(productpage.cart));
		productpage.AddQuantity(1);
		productpage.AddToCart();
		Assert.assertTrue(IsDisplayed(productpage.productadded_check));
		productpage.GoToCart();

		shoppingcartPage = new ShoppingCartPage(driver);
		Assert.assertEquals("Shopping cart", GetText(shoppingcartPage.page_check));

		Assert.assertTrue(IsDisplayed(shoppingcartPage.discountfield));
		Assert.assertTrue(IsDisplayed(shoppingcartPage.discountapply));
		shoppingcartPage.EnterDiscountCode(prop.getProperty("discountcode"));
		Assert.assertTrue(shoppingcartPage.CheckDiscountDone());

		Assert.assertTrue(IsDisplayed(shoppingcartPage.termofservice_checkbox));
		Assert.assertTrue(IsDisplayed(shoppingcartPage.checkout_button));
		shoppingcartPage.EnableCheckout();

		checkoutoage = new CheckoutPage(driver);
		Assert.assertEquals("Checkout", GetText(checkoutoage.page_check));

		checkoutoage.BillingSubmit();

		ExplicitWaitUntilVisible(checkoutoage.countryerror, 5);
		driver.findElement(checkoutoage.countryerror).isDisplayed();
		driver.findElement(checkoutoage.cityerror).isDisplayed();
		driver.findElement(checkoutoage.address1error).isDisplayed();
		driver.findElement(checkoutoage.zipcodeerror).isDisplayed();
		driver.findElement(checkoutoage.phoneerror).isDisplayed();

		loginPage.LogoutSubmit();
		Assert.assertEquals("Welcome to our store", GetText(homepage.homepage_check));
		Assert.assertTrue(IsDisplayed(homepage.login_link));
		Assert.assertTrue(IsNotExist(loginPage.logout_button));
	}
}
