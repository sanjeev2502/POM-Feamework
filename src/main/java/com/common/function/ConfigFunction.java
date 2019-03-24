package com.common.function;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

//import io.github.bonigarcia.wdm.WebDriverManager;

public class ConfigFunction {
	//Select Webdriver browser
	@SuppressWarnings("deprecation")
	public  static WebDriver selectBrowserDriver(String browser,String url){
		WebDriver driver =null;
	//	System.out.println(System.getProperty("os.name"));
		if(System.getProperty("os.name").contains("Windows")){
			if (browser.equalsIgnoreCase("IE")){
				//System.setProperty("webdriver.ie.driver",  System.getProperty("user.dir")+"\\ExeFile\\Driver\\IEdriver\\IEDriverServer.exe");
			//	WebDriverManager.iedriver().version("3.14.0").arch32().setup();
				driver=new InternetExplorerDriver();
				driver.manage().window().maximize();
				driver.get(url);
			}
			else if(browser.equalsIgnoreCase("Chrome")){
				//WebDriverManager.chromedriver().version("2.45").setup();
				System.setProperty("webdriver.chrome.driver",  System.getProperty("user.dir")+"\\ExeFile\\Driver\\chromeDriver\\chromedriver.exe");
				
			//	System.setProperty("webdriver.chrome.driver","C:\\Sanjeev_Software\\chromedriver_win32\\chromedriver.exe");
			//	System.setProperty("webdriver.chrome.driver",  System.getProperty("C:\\Sanjeev_Software\\chromedriver_win32\\chromedriver.exe"));
				
				driver=new ChromeDriver();
				driver.manage().window().maximize();
				driver.get(url);
			}
			else if(browser.equalsIgnoreCase("firefox")){
				//System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"\\ExeFile\\Driver\\gecckoDriver\\geckodriver.exe");
				//File pathBinary = new File("C:\\Users\\Rgupta\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
				//FirefoxBinary firefoxBinary = new FirefoxBinary(pathBinary);
		//		WebDriverManager.firefoxdriver().arch32().setup();
				//FirefoxProfile firefoxProfile = new FirefoxProfile();   
				DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
				desiredCapabilities.setAcceptInsecureCerts(true);
				driver=new FirefoxDriver(desiredCapabilities);
				driver.manage().window().maximize();
				driver.get(url);
				driver.navigate().refresh();
			}
		}
		else{
			System.out.println("Browser not handled");
		}
		//driver.manage().deleteAllCookies();
		driver.manage().window().maximize();

		return driver;
	}

}
