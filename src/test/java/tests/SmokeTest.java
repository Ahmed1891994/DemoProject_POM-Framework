package tests;

import java.util.Properties;

import org.junit.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pages.BasePage;
import pages.AddressesAccountPage;
import pages.CheckoutPage;
import pages.DesktopsPage;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductPage;
import pages.RegisterPage;
import pages.RegisterSuccessPage;
import pages.ShoppingCartPage;

@Listeners(utility.Listeners.class)

public class SmokeTest extends BasePage {
	// save data of the properties file in variables
	Properties prop = LoadProperties();
	String confirmpassword = prop.getProperty("confirmpassword");
	String gender = prop.getProperty("gender");
	String firstname = prop.getProperty("firstname");
	String lastname = prop.getProperty("lastname");
	String email = prop.getProperty("email");
	String password = prop.getProperty("password");
	String discountcode = prop.getProperty("discountcode");
	String company = prop.getProperty("company");
	String country = prop.getProperty("country");
	String state = prop.getProperty("state");
	String cityname = prop.getProperty("cityname");
	String address1 = prop.getProperty("address1");
	String address2 = prop.getProperty("address2");
	String zipcode = prop.getProperty("zipcode");

	String phonenumber = prop.getProperty("phonenumber");
	String faxnumber = prop.getProperty("faxnumber");
	String creditcardtype = prop.getProperty("creditcardtype");
	String creditcardholdername = prop.getProperty("creditcardholdername");
	String cardnumber = prop.getProperty("cardnumber");

	String expiremonth = prop.getProperty("expiremonth");
	String expireyear = prop.getProperty("expireyear");
	String cardcode = prop.getProperty("cardcode");

	// pages
	HomePage homepage;
	LoginPage loginPage;
	RegisterPage registerpage;
	RegisterSuccessPage registersuccess;
	DesktopsPage desktoppage;
	ProductPage productpage;
	ShoppingCartPage shoppingcartPage;
	CheckoutPage checkoutoage;
	AddressesAccountPage addressesaccountpage;

	@Test(enabled = true, priority = 0, description = "New Registration with new email")
	public void newRegisteration() {
		homepage = new HomePage(driver);
		// check page
		Assert.assertEquals("Welcome to our store", GetText(homepage.homepage_check));
		// check Homepage elements
		Assert.assertTrue(IsDisplayed(homepage.register_link));
		Assert.assertTrue(IsNotExist(homepage.logout_link));

		homepage.EnterRegisterPage();
		registerpage = new RegisterPage(driver);
		// check page
		Assert.assertEquals("Register", GetText(registerpage.registerpage_check));

		// check registration elements
		Assert.assertTrue(IsDisplayed(registerpage.firstname_field));
		Assert.assertTrue(IsDisplayed(registerpage.lastname_field));
		Assert.assertTrue(IsDisplayed(registerpage.email_field));
		Assert.assertTrue(IsDisplayed(registerpage.password_field));
		Assert.assertTrue(IsDisplayed(registerpage.confirmpassword_field));
		Assert.assertTrue(IsDisplayed(registerpage.gender_male_radiobutton));
		Assert.assertTrue(IsDisplayed(registerpage.gender_female_radiobutton));
		Assert.assertTrue(IsDisplayed(registerpage.register_button));
		registerpage.EnterRegisterData(firstname, lastname, email, password, confirmpassword, gender);
		registerpage.RegisterSubmit();
		registersuccess = new RegisterSuccessPage(driver);

		// check page
		Assert.assertEquals("Your registration completed", GetText(registersuccess.registerpagedone_check));

		// check registration success elements
		Assert.assertTrue(IsDisplayed(registersuccess.continue_button));

		registersuccess.ContinueSubmitLogin();
		loginPage = new LoginPage(driver);
		// check page
		Assert.assertTrue(IsDisplayed(loginPage.logout_button));
		Assert.assertEquals(email, GetText(loginPage.account_check));

		homepage.PressLogoutLink();
		Assert.assertEquals("Welcome to our store", GetText(homepage.homepage_check));
		Assert.assertTrue(IsDisplayed(homepage.login_link));
		Assert.assertTrue(IsNotExist(loginPage.logout_button));
	}

	@Test(enabled = true, priority = 1, description = "Login with valid email and valid password")
	public void ValidLogin() {
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

		loginPage.EnterLoginCredential(email, password);
		loginPage.LoginSubmit();
		Assert.assertTrue(IsDisplayed(loginPage.logout_button));
		Assert.assertEquals(email, GetText(loginPage.account_check));

		homepage.PressLogoutLink();
		Assert.assertEquals("Welcome to our store", GetText(homepage.homepage_check));
		Assert.assertTrue(IsDisplayed(homepage.login_link));
		Assert.assertTrue(IsNotExist(loginPage.logout_button));
	}

	@Test(enabled = true, priority = 2, description = "buy simple computer and apply discount and pay buy visa")
	public void BuyProduct() {
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

		loginPage.EnterLoginCredential(email, password);
		loginPage.LoginSubmit();
		Assert.assertTrue(IsDisplayed(loginPage.logout_button));
		Assert.assertEquals(email, GetText(loginPage.account_check));

		homepage.AddressLink();
		
		addressesaccountpage = new AddressesAccountPage(driver);
		Assert.assertEquals("My account - Addresses", GetText(addressesaccountpage.accountaddress_check));
		Assert.assertTrue(IsDisplayed(addressesaccountpage.newaddress_button));
		
		addressesaccountpage.ClickAddNewAddress();
		Assert.assertTrue(IsDisplayed(addressesaccountpage.firstname_Ad));
		Assert.assertTrue(IsDisplayed(addressesaccountpage.lastname_Ad));
		Assert.assertTrue(IsDisplayed(addressesaccountpage.email_Ad));
		Assert.assertTrue(IsDisplayed(addressesaccountpage.company_Ad));
		Assert.assertTrue(IsDisplayed(addressesaccountpage.countryid_Ad));
		Assert.assertTrue(IsDisplayed(addressesaccountpage.stateprovinceid_Ad));
		Assert.assertTrue(IsDisplayed(addressesaccountpage.city_Ad));
		Assert.assertTrue(IsDisplayed(addressesaccountpage.address1_Ad));
		Assert.assertTrue(IsDisplayed(addressesaccountpage.address2_Ad));
		Assert.assertTrue(IsDisplayed(addressesaccountpage.zippostalcode_Ad));
		Assert.assertTrue(IsDisplayed(addressesaccountpage.phonenumber_Ad));
		Assert.assertTrue(IsDisplayed(addressesaccountpage.faxnumber_Ad));
		
		addressesaccountpage.AddnewAddress(firstname, lastname, email, company, country, state, cityname, address1,
				address2, zipcode, phonenumber, faxnumber);
		Assert.assertTrue(IsDisplayed(addressesaccountpage.saveaddressbutton));
		addressesaccountpage.SaveNewAddress();
		Assert.assertTrue(addressesaccountpage.oldnumberofaddresses < addressesaccountpage.CheckNewNumberAddressSaved());

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
		shoppingcartPage.EnterDiscountCode(discountcode);
		Assert.assertTrue(shoppingcartPage.CheckDiscountDone());
		
		Assert.assertTrue(IsDisplayed(shoppingcartPage.termofservice_checkbox));
		Assert.assertTrue(IsDisplayed(shoppingcartPage.checkout_button));
		shoppingcartPage.EnableCheckout();

		checkoutoage = new CheckoutPage(driver);
		Assert.assertEquals("Checkout", GetText(checkoutoage.page_check));
		
		Assert.assertTrue(IsDisplayed(checkoutoage.addresslist));
		Assert.assertTrue(IsDisplayed(checkoutoage.Billingsubmit));
		checkoutoage.BillingAddress(0);
		checkoutoage.BillingSubmit();
		
		ExplicitWaitUntilVisible(checkoutoage.shippingsubmit,5);
		Assert.assertTrue(IsDisplayed(checkoutoage.shippingsubmit));
		checkoutoage.ShippingAddress();
		
		ExplicitWaitUntilVisible(checkoutoage.shippingmethodsubmit,5);
		Assert.assertTrue(IsDisplayed(checkoutoage.shippingmethodsubmit));
		checkoutoage.ShippingMethod();
		
		ExplicitWaitUntilVisible(checkoutoage.creditcardoption,5);
		Assert.assertTrue(IsDisplayed(checkoutoage.creditcardoption));
		Assert.assertTrue(IsDisplayed(checkoutoage.paymentmethodsubmit));
		checkoutoage.PaymentMethod();
		
		ExplicitWaitUntilVisible(checkoutoage.creditcardtype,5);
		Assert.assertTrue(IsDisplayed(checkoutoage.creditcardtype));
		Assert.assertTrue(IsDisplayed(checkoutoage.cardholdername));
		Assert.assertTrue(IsDisplayed(checkoutoage.cardnumber));
		Assert.assertTrue(IsDisplayed(checkoutoage.expiremonth));
		Assert.assertTrue(IsDisplayed(checkoutoage.expireyear));
		Assert.assertTrue(IsDisplayed(checkoutoage.cardcode));
		Assert.assertTrue(IsDisplayed(checkoutoage.paymentinfosubmit));
		checkoutoage.PaymentInfo(creditcardtype, creditcardholdername, cardnumber, expiremonth, expireyear, cardcode);
		
		ExplicitWaitUntilVisible(checkoutoage.confirmordersubmit,5);
		Assert.assertTrue(IsDisplayed(checkoutoage.confirmordersubmit));
		checkoutoage.ConfirmBuy();
		
		ExplicitWaitUntilVisible(checkoutoage.continuetohomepage,5);
		Assert.assertEquals("Thank you", GetText(checkoutoage.page_check));
		Assert.assertEquals("Your order has been successfully processed!", GetText(checkoutoage.page_title_check));

		homepage.PressLogoutLink();
		ExplicitWaitUntilVisible(homepage.login_link,5);
		Assert.assertTrue(IsDisplayed(homepage.login_link));
	}

	@Test(enabled = true, priority = 1, description = "Check product is added successfully in the cart")
	public void AddProductToCartSuccessfully() {
		homepage = new HomePage(driver);
		// check page
		Assert.assertEquals("Welcome to our store", GetText(homepage.homepage_check));
		// check Homepage elements
		Assert.assertTrue(IsDisplayed(homepage.login_link));
		Assert.assertTrue(IsNotExist(homepage.logout_link));

		loginPage = new LoginPage(driver);
		Assert.assertTrue(IsDisplayed((loginPage.email_field)));
		Assert.assertTrue(IsDisplayed((loginPage.password_field)));
		Assert.assertTrue(IsDisplayed((loginPage.remembermecheckbox)));
		Assert.assertTrue(IsDisplayed((loginPage.login_submit_button)));
		Assert.assertTrue(IsDisplayed((loginPage.loginpage_check)));
		Assert.assertTrue(IsNotExist((loginPage.logout_button)));

		loginPage.EnterLoginCredential(email, password);
		loginPage.LoginSubmit();
		Assert.assertTrue(IsDisplayed((loginPage.logout_button)));
		Assert.assertTrue(IsDisplayed((loginPage.account_check)));
		Assert.assertEquals(email, driver.findElement(loginPage.account_check).getText());
		Assert.assertTrue(IsNotExist((loginPage.loginpage_check)));

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
		productpage.WaitBar();
		
		loginPage.LogoutSubmit();
	}

}
