package pages;



import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DesktopsPage extends BasePage{

	// web driver
	final private WebDriver driver;

	//product simple computer
	public By simplecomputer_product = By.linkText("Simple Computer");
	public By All_products = By.className("product-grid");
	public By Each_product = By.className("item-box");
	//page check
	public By page_check = By.className("page-title");
	
	
	public DesktopsPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void SelectProductByPic(int index)
	{
		WebElement productelement = driver.findElement(By.xpath("//div[@class='product-grid']//div["+index+"]//div//div[@class='picture']"));		
		productelement.click();
	}
	

}
