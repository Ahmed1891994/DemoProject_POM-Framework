package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class CheckoutPage extends BasePage {

	// web driver
	final private WebDriver driver;

	public By addresslist = By.id("billing-address-select");
	@SuppressWarnings("unused")
	public By company = By.id("BillingNewAddress_Company");
	@SuppressWarnings("unused")
	public By countryid = By.id("BillingNewAddress_CountryId");
	@SuppressWarnings("unused")
	public By stateprovinceid = By.id("BillingNewAddress_StateProvinceId");
	@SuppressWarnings("unused")
	public By city = By.id("BillingNewAddress_City");

	@SuppressWarnings("unused")
	public By address1 = By.id("BillingNewAddress_Address1");
	@SuppressWarnings("unused")
	public By address2 = By.id("BillingNewAddress_Address2");
	@SuppressWarnings("unused")
	public By zippostalcode = By.id("BillingNewAddress_ZipPostalCode");
	@SuppressWarnings("unused")
	public By phonenumber = By.id("BillingNewAddress_PhoneNumber");
	@SuppressWarnings("unused")
	public By faxnumber = By.id("BillingNewAddress_FaxNumber");
	public By creditcardoption = By.id("paymentmethod_2");
	public By Billingsubmit = By.xpath("//input[@onclick='Billing.save()']");
	public By shippingsubmit = By.xpath("//input[@onclick='Shipping.save()']");
	public By shippingmethodsubmit = By.xpath("//input[@onclick='ShippingMethod.save()']");
	public By paymentmethodsubmit = By.xpath("//input[@onclick='PaymentMethod.save()']");
	public By paymentinfosubmit = By.xpath("//input[@onclick='PaymentInfo.save()']");
	public By confirmordersubmit = By.xpath("//input[@onclick='ConfirmOrder.save()']");
	public By continuetohomepage= By.className("order-completed-continue-button");
	public By buysuccess = By.xpath("(//strong[normalize-space()='Your order has been successfully processed!'])[1]");

	public By creditcardtype = By.id("CreditCardType");
	public By cardholdername = By.id("CardholderName");
	public By cardnumber = By.id("CardNumber");
	public By expiremonth = By.id("ExpireMonth");
	public By expireyear = By.id("ExpireYear");
	public By cardcode = By.id("CardCode");

	public By creditcard = By.id("paymentmethod_2");

	// errors
	public By countryerror = By.cssSelector(".field-validation-error[data-valmsg-for='BillingNewAddress.CountryId']");
	public By cityerror = By.cssSelector(".field-validation-error[data-valmsg-for='BillingNewAddress.City']");
	public By address1error = By.cssSelector(".field-validation-error[data-valmsg-for='BillingNewAddress.Address1']");
	public By zipcodeerror = By.cssSelector(".field-validation-error[data-valmsg-for='BillingNewAddress.ZipPostalCode']");
	public By phoneerror = By.cssSelector(".field-validation-error[data-valmsg-for='BillingNewAddress.PhoneNumber']");

	//page check
	public By page_check = By.className("page-title");
	public By page_title_check = By.className("title");
	// constructor
	public CheckoutPage(WebDriver driver) {
		this.driver = driver;
	}

	public void BillingAddress(int index) {

		ExplicitWaitUntilVisible(addresslist, 5);

		Select countrymenu = new Select(driver.findElement(addresslist));
		countrymenu.selectByIndex(index);
	}

	public void ShippingAddress() {
		ExplicitWaitUntilVisible(shippingsubmit, 5);
		driver.findElement(shippingsubmit).click();
	}

	public void ShippingMethod() {
		ExplicitWaitUntilVisible(shippingmethodsubmit, 5);
		driver.findElement(shippingmethodsubmit).click();
	}

	public void PaymentMethod() {
		ExplicitWaitUntilVisible(creditcardoption, 5);

		driver.findElement(creditcardoption).click();
		driver.findElement(paymentmethodsubmit).click();
	}

	public void PaymentInfo(String creditType, String holderName, String cardNumber, String month, String year,
			String cardCode) {
		ExplicitWaitUntilVisible(creditcardtype, 5);

		driver.findElement(creditcardtype).sendKeys(creditType);

		driver.findElement(cardholdername).sendKeys(holderName);

		driver.findElement(cardnumber).sendKeys(cardNumber);

		driver.findElement(expiremonth).sendKeys(month);

		driver.findElement(expireyear).sendKeys(year);

		driver.findElement(cardcode).sendKeys(cardCode);

		driver.findElement(paymentinfosubmit).click();

	}

	public void ConfirmBuy() {
		ExplicitWaitUntilVisible(confirmordersubmit, 5);
		driver.findElement(confirmordersubmit).click();
	}

	public void BillingSubmit() {
		ExplicitWaitUntilVisible(Billingsubmit, 5);
		driver.findElement(Billingsubmit).click();
	}
}
