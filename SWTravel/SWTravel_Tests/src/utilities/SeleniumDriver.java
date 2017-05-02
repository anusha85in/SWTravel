package utilities;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.WebDriver;

public class SeleniumDriver {
	private static String browserTypeChrome = "Chrome";
	private static String chromeDriverPath = "C:/SWTravel/SWTravel_Tests/drivers/chromedriver.exe";
	private static String IEDriverPath = "C:/SWTravel/SWTravel_Tests/drivers/IEDriver.exe";	
	private static WebDriver browser;
	private static String baseUrl;
	public static String ContentId1 = "content-1";
	public static String ContentId2 = "content-2";
	
	protected SeleniumDriver() {
	}
	
	//Instantiates Chrome browser.
	public static void initializeDriverChrome(String url) {
		baseUrl = url;
		browser = getBrowser(browserTypeChrome);
		browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		browser.get(baseUrl);
	}
	
	//Closes driver
	public static void closeDriver() {
		browser.quit();
	}
	
	//Refreshes Page
	public static void refreshPage() {
		browser.get(browser.getCurrentUrl());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	//Verifies text matches text in element. 
	public static void verifyTextEquals(String elementLabel, String text) {	
		assertEquals(text, getWebElement(elementLabel).getText());
	}

	//Verify text passed in parameter equals text in Element. 
	public static void verifyTextContains(String elementLabel, String text) {
		String elementText = getWebElement(elementLabel).getText();
		int counter = 0;
		while (! elementText.contains(text) && counter < 180)
		{
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			counter++;
			elementText = getWebElement(elementLabel).getText();
		}
		if(! elementText.contains(text) )
		{
			fail("Expected text: \"" + text + "\" not contained in \"" + elementText + "\"");
		}
	}
	
	//Find and click Element on a page.
	public static void findAndClick(String elementLabel) {
		WebElement element = getWebElement(elementLabel);
		int counter = 0;
		while(element == null && counter < 120)
		{
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			counter++;
			element = getWebElement(elementLabel);
		}
		if(element == null)
		{
			throw new Error("Cannot find: " + elementLabel);
		}
		try {
			element.click();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Error("Clicking on " + elementLabel + " failed");
		}
	}
	
	//Verify Radio button is selected. 
	public static boolean isRadioButtonSelected(String elementLabel) {
		WebElement element = getWebElement(elementLabel);
		int counter = 0;
		while(element == null && counter < 120)
		{
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			counter++;
			element = getWebElement(elementLabel);
		}
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		boolean isButtonSelected = element.isSelected();
		return isButtonSelected;
	}
	
	//Enter text in a field.
	public static void setTextField(String elementLabel, String text) {
		WebElement element = getWebElement(elementLabel);
		getWebElement(elementLabel).clear();
		element.sendKeys(text);
	}
	
	//Select value from a drop down 
	public static void clickDropDown(String elementLabel, String text) {
		WebElement element = getWebElement(elementLabel);
		Select dropdown= new Select(element);
		dropdown.selectByVisibleText(text);
	}
	
	//Verify text field is displayed and is not grayed out.
	public static boolean isTextFieldDisplayed(String elementLabel) {
		boolean isFieldDisplayed = getWebElement(elementLabel).isDisplayed();
		browser.switchTo().defaultContent();
		return isFieldDisplayed;
	}
	
	//Verify element exists.
	public static void verifyExists(String elementLabel, String contentId) {
		if (getWebElement(elementLabel) == null)
		{
			fail(elementLabel + " not found");
		}
	}
	
	//Get Browser
	public static WebDriver getBrowser(String browserType) {
		browserType = browserType.toLowerCase();
		WebDriver driver = null;
			if (browserType.equals("ie")) {
				System.setProperty("webdriver.ie.driver", IEDriverPath);
				driver = new InternetExplorerDriver();
			} else if (browserType.equals("chrome")) {
				System.setProperty("webdriver.chrome.driver", chromeDriverPath);
				driver = new ChromeDriver();
			}
		return driver;
	}


	//=================================================================================================================
	// PRIVATE METHODS
	//=================================================================================================================
	private static WebElement getWebElement(String elementLabel) {
		WebElement element = (elementLabel.contains("/")) ? 
				browser.findElement(By.xpath(elementLabel)) : browser.findElement(By.id(elementLabel));
		return element;
	}
	
}
