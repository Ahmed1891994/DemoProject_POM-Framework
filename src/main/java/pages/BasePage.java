package pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BasePage {
	public static WebDriver driver; 
	public ThreadLocal<RemoteWebDriver> Remdriver = null;
	public Select select ; 
	public Actions action ; 
	public Properties prop;
	
	// create constructor 
	public BasePage() 
	{
		
	}

	//******************************************************************************//
	///////////////////////////////////Hooks//////////////////////////////////////////
	//******************************************************************************//
	// Handle configuration to start Browser
		@BeforeMethod(alwaysRun = true, description = "setup browser configuration")
		public void SetUpBrowser() throws MalformedURLException {
			
			// Initialize properties variables
			
			String BRAWSER = null;
			String BaseURL = null;
			String seleniumGrid_Enabled = null;
			
			
			// load properties from properties file and save it in variables
			prop = new Properties();
			Properties prop = LoadProperties();
			BRAWSER = prop.getProperty("browser");
			BaseURL = prop.getProperty("website");
			seleniumGrid_Enabled = prop.getProperty("seleniumGrid_Enabled");

			if(seleniumGrid_Enabled.contains("N"))
			{
				// Setup browser configuration to open
				if (BRAWSER.contains("chrome")) {
					System.setProperty("webdriver.chrome.driver", "BrowserDrivers/chromedriver.exe");
					ChromeOptions options = new ChromeOptions();
					if (BRAWSER.contains("headless"))
						options.addArguments("headless");
					driver = new ChromeDriver(options);
	
				} else if (BRAWSER.contains("edge")) {
					System.setProperty("webdriver.edge.driver", "BrowserDrivers/msedgedriver.exe");
					driver = new EdgeDriver();
				}
				// open specified website
				driver.get(BaseURL);
	
				// maximize window
				driver.manage().window().maximize();
			}
			else if(seleniumGrid_Enabled.contains("Y"))
			{
				Remdriver = new ThreadLocal<>();
				DesiredCapabilities caps = new DesiredCapabilities();
				caps.setCapability("Brawser Name", BRAWSER);
				
				// Selenium Grid Local
				Remdriver.set(new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),caps));
		        getDriver().manage().window().maximize();
		        getDriver().navigate().to(BaseURL);
		    }
		}
	    public WebDriver getDriver()
	    {
	        return Remdriver.get();
	    }
		
		// Close the browser and end session
		@AfterMethod(alwaysRun = true, description = "End session and close browser")
		public void closeBrowser() {
			String seleniumGrid_Enabled = prop.getProperty("seleniumGrid_Enabled");
			if(seleniumGrid_Enabled.contains("N"))
			{
				driver.close();
			}
			else if(seleniumGrid_Enabled.contains("Y"))
			{
				getDriver().quit();
				Remdriver.remove();
			
			}
		}
		
	
	//******************************************************************************//
	////////////////////////////////Elements Handling/////////////////////////////////
	//******************************************************************************//
	public static void clickButton(By button) 
	{
		driver.findElement(button).click();
	}
	
	public static void SetTextINField(By textElement , String value) 
	{
		driver.findElement(textElement).sendKeys(value);
	}
	
	public static String GetText(By textElement) 
	{
		return driver.findElement(textElement).getText();
	}
	
	public static void clearText(By element) 
	{
		driver.findElement(element).clear();
	}
	
	//******************************************************************************//
	////////////////////////////////Generic Functions/////////////////////////////////
	//******************************************************************************//
	public static void TakeScreenShot(String TestMethodName, WebDriver driver) {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String time = java.time.LocalTime.now().toString().replace(":", "-").substring(0, 5);
		String date = java.time.LocalDate.now().toString();
		String destination = System.getProperty("user.dir") + "\\ScreenShots\\" + date + "_" + time + "\\"
				+ TestMethodName + ".png";

		try {
			FileUtils.copyFile(source, new File(destination));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Properties LoadProperties() {
		Properties prop = null;
		try {
			InputStream input = new FileInputStream("Configurations/conf.properties");
			prop = new Properties();
			prop.load(input);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return prop;
	}
	
	
	//******************************************************************************//
	////////////////////////////////Timers methods////////////////////////////////////
	//******************************************************************************//
	public static void ExplicitWaitUntilVisible(By element, int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.visibilityOfElementLocated(element));
	}
	
	public static void ExplicitWaitUntilInVisible(By element, int seconds) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
	}
	
	

	
	//******************************************************************************//
	////////////////////////////////Checking Elements/////////////////////////////////
	//******************************************************************************//
	public static boolean IsDisplayed(By element) {
		boolean flag;
		if (driver.findElement(element).isDisplayed()) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}
	
	public static boolean IsNotExist(By element) {
		boolean flag;
		if (driver.findElements(element).isEmpty()) {
			flag = true;
		} else {
			flag = false;
		}
		return flag;
	}
	
	
	//******************************************************************************//
	////////////////////////////////Debugging/////////////////////////////////////////
	//******************************************************************************//
	public void KnowWhoCalledYou()
	{
		RuntimeException ex = new RuntimeException();
		StackTraceElement[] stackTrace = ex.getStackTrace();
		for (int i = 1 ; i <= 3 ; i++)
		{
			System.out.println("##################################################"); 
			System.out.println(stackTrace[i]); 
		}
		System.out.println("##################################################"); 
	}
}
