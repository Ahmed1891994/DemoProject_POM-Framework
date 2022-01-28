package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.Select;

public class AddressesAccountPage extends BasePage {

	// web driver
	final private WebDriver driver;

	// button
	public By newaddress_button = By.className("add-address-button");

	public By firstname_Ad = By.id("Address_FirstName");
	public By lastname_Ad = By.id("Address_LastName");
	public By email_Ad = By.id("Address_Email");
	public By company_Ad = By.id("Address_Company");
	public By countryid_Ad = By.id("Address_CountryId");
	public By stateprovinceid_Ad = By.id("Address_StateProvinceId");
	public By city_Ad = By.id("Address_City");
	public By address1_Ad = By.id("Address_Address1");
	public By address2_Ad = By.id("Address_Address2");
	public By zippostalcode_Ad = By.id("Address_ZipPostalCode");
	public By phonenumber_Ad = By.id("Address_PhoneNumber");
	public By faxnumber_Ad = By.id("Address_FaxNumber");

	public By saveaddressbutton = By.className("save-address-button");

	private By addresslist_check = By.xpath("//div[@class='address-list']//div[@class='section address-item']");
	public By accountaddress_check = By.className("page-title");

	public int oldnumberofaddresses;

	// constructor
	public AddressesAccountPage(WebDriver driver) {
		this.driver = driver;
	}

	public void ClickAddNewAddress()
	{
		ExplicitWaitUntilVisible(newaddress_button, 5);
		try {
			oldnumberofaddresses = driver.findElements(addresslist_check).size();
		} catch (NoSuchElementException e) {
			oldnumberofaddresses = 0;
		}
		driver.findElement(newaddress_button).click();
		
	}
	public void AddnewAddress(String firstname, String lastname, String email, String IN_company, String country,
			String state, String cityname, String IN_address1, String IN_address2, String zipcode, String phonenum,
			String faxnum) {
		ExplicitWaitUntilVisible(firstname_Ad, 5);

		driver.findElement(firstname_Ad).sendKeys(firstname);
		driver.findElement(lastname_Ad).sendKeys(lastname);
		driver.findElement(email_Ad).sendKeys(email);
		driver.findElement(company_Ad).sendKeys(IN_company);

		Select countrymenu = new Select(driver.findElement(countryid_Ad));
		countrymenu.selectByVisibleText(country);

		Select statemenu = new Select(driver.findElement(stateprovinceid_Ad));
		statemenu.selectByVisibleText(state);

		driver.findElement(city_Ad).sendKeys(cityname);
		driver.findElement(address1_Ad).sendKeys(IN_address1);

		driver.findElement(address2_Ad).sendKeys(IN_address2);
		driver.findElement(zippostalcode_Ad).sendKeys(zipcode);
		driver.findElement(phonenumber_Ad).sendKeys(phonenum);
		driver.findElement(faxnumber_Ad).sendKeys(faxnum);
	}

	public void SaveNewAddress()
	{
		ExplicitWaitUntilVisible(saveaddressbutton, 5);
		driver.findElement(saveaddressbutton).click();
	}
	public void DeleteAllAddresses() {
		int num;
		try {
			List<WebElement> elements = driver.findElements(addresslist_check);
			num = elements.size();
			int i;
			By deletebutton;
			for (i = num; i > 0; --i) {
				deletebutton = By.xpath("(//input[@value='Delete'])[" + i + "]");
				ExplicitWaitUntilVisible(deletebutton, 5);
				driver.findElement(deletebutton).click();
				driver.switchTo().alert().accept();
				driver.navigate().refresh();
			}
		} catch (NoSuchElementException e) {

		}
	}

	public int CheckNewNumberAddressSaved() {
		return driver.findElements(addresslist_check).size();
		
	}

}
