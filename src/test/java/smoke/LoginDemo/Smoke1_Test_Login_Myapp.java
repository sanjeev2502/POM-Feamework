package smoke.LoginDemo;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import java.util.Properties;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

//import com.MyADT.Pages.MyADTDashBoardPage;
import com.common.function.Common;
import com.common.function.ConfigFunction;
import com.common.function.ExtentReportClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import commonFunctions.ExcelConnector;
//import com.chudly.Web.base.WebTestCase;
//import com.myADT.Web.base.WebTestCase;
import com.Web.base.WebTestCase;

@Listeners({  ExtentReportClass.class })
public class Smoke1_Test_Login_Myapp extends WebTestCase {
	private Common common ;

	static Properties prop=new Properties();
	InputStream input = null;


	@BeforeClass
	public void initialize(ITestContext testContext)
			throws IOException {
		input = new FileInputStream("config.properties");

		// load a properties file
		prop.load(input);
		WebDriver driver=ConfigFunction.selectBrowserDriver(prop.getProperty("browser"),prop.getProperty("url"));


		super.initilizeWebfactory(driver);
		super.setDriver(driver);
		testContext.setAttribute("driver", driver);
		

	
	
	
	
	common = new Common(driver);



	}


	/*
	@DataProvider
	public static Object[][] login() throws IOException{

		return ExcelConnector.ExcelRead(prop.getProperty("mydemoLogin"),"login");



			}


	 */

	//@Test(dataProvider="login")
	//	public void smoke1_Test_Login_Myapp(Hashtable<String,String> data)  throws InterruptedException ,IOException{

	@Test	
	public void smoke1_Test_Login_Myapp()  throws InterruptedException ,IOException{

		common.impicitWait(20);


		Assert.assertTrue(getPageFactory().getLoginDemoPage().LoginMyDemo("sanjeev", "pwd1"));

		
	}
	@AfterTest
	public void closeBrowsr(){
		driver.quit();

	}


}
