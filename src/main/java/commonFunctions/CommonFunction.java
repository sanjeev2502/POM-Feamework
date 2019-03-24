package commonFunctions;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;


import junit.framework.Assert;

public class CommonFunction  {
	private WebDriver driver;

	public CommonFunction(WebDriver driver2) {

		this.driver = driver2;
	}

	//Select Webdriver browser
	public  WebDriver selectBrowserDriver(String browser){
		//WebDriver driver =null;
		if (browser.equalsIgnoreCase("IE")){
			System.setProperty("webdriver.ie.driver",  System.getProperty("user.dir")+"\\ExeFile\\Driver\\IEdriver\\IEDriverServer.exe");
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
			driver = new InternetExplorerDriver(capabilities);
			driver.manage().window().maximize();
		}
		else if(browser.equalsIgnoreCase("Chrome")){
			System.setProperty("webdriver.chrome.driver",  System.getProperty("user.dir")+"\\ExeFile\\Driver\\chromeDriver\\chromedriver.exe");
			driver=new ChromeDriver();
			driver.manage().window().maximize();
		}
		else if(browser.equalsIgnoreCase("firefox")){
			System.setProperty("webdriver.geckodriver.driver",System.getProperty("user.dir")+"\\ExeFile\\Driver\\gecckoDriver\\geckodriver.exe");
			driver=new FirefoxDriver();
			driver.manage().window().maximize();
		}
		else if(browser.equalsIgnoreCase("safari")){
			
			//System.setProperty("webdriver.geckodriver.driver",System.getProperty("user.dir")+"\\ExeFile\\Driver\\gecckoDriver\\geckodriver.exe");
			driver=new SafariDriver();
			driver.manage().window().maximize();
		}
		else
			System.out.println("Browser not handled");

		return driver;
	}

	//entering text in a textbox
	public  void enterText(WebElement elem,String text){
		elem.clear();
		elem.sendKeys(text);
	}

	//Retrieve text from label or textbox
	public  String getText(WebElement elem){
		return elem.getText();
	}

	//clicking on button
	public  void buttonClick(WebElement elem){
		elem.click();
	}
	//Selecting Dropdown by Index
	public  void selectDropdownByID(WebElement elem,int index){
		Select selectDD=new Select(elem);
		selectDD.selectByIndex(index);
	}

	//Selecting Dropdown by Value
	public  void selectDropdownByValue(WebElement elem,String value){
		Select selectDD=new Select(elem);

		selectDD.selectByValue(value);
	}

	//Selecting Dropdown by text
	public  void selectDropdownByVisibleText(WebDriver driver,WebElement elem,String visibleText){
		Select selectDD=new Select(elem);
		selectDD.selectByVisibleText(visibleText);
	}

	//Wait for Element to be visible
	public  void waitForElementVisibility(WebDriver driver,int time,WebElement elem){

		WebDriverWait wait=new WebDriverWait (driver,time);
		wait.until(ExpectedConditions.visibilityOf(elem));

	}

	//

	//Label Validation

	public void verifyText(WebDriver driver,String expectedValue,WebElement elem){
		if(getText(elem).equals(expectedValue))
		{
			Assert.assertTrue("String matched", true);
			Reporter.log("String Matched");

		}
		else
		{Assert.assertFalse("Not matching", false);
		Reporter.log("Not Matching");
		}
	}

	//Screenshot

	public static void getScreenShot(WebDriver driver,String path,String screenShotName) throws IOException{

		Date date = new Date();
		String destinationPath=System.getProperty("user.dir")+path+date.getTime()+screenShotName;

		File src= ((TakesScreenshot)driver). getScreenshotAs(OutputType. FILE);

		FileUtils.copyFile(src, new File(destinationPath));


	}

}
