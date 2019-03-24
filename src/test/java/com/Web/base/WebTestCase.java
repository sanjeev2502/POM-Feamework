package com.Web.base;


import java.util.Random;

//import jxl.write.WritableWorkbook;

//import org.apache.commons.lang3.time.StopWatch;
import org.openqa.selenium.WebDriver;

import org.testng.Reporter;
import org.testng.annotations.*;

import com.pageFactory.WebPageFactory;

//import org.apache.log4j.xml.DOMConfigurator;

//
public class WebTestCase {
/*	//protected SeleniumDriverUtil selenium = new SeleniumDriverUtil();
	protected WebDriver driver;
	protected String browserValue=null;
	protected String enviornment=null;
	protected ITestContext testContextValue;*/
	//private WritableWorkbook workbook;
//	private TestSession session;
	private WebPageFactory pageFactory;
	protected WebDriver driver;
	
	
	//private ExcelManager xls = new ExcelManager(System.getProperty("user.dir")
		//	+ "\\data\\" + this.getClass().getSimpleName() + ".xlsx");

	/*public WebTestCase( ) {
		DOMConfigurator.configure("log4j.xml");
	}*/
	
	/*@DataProvider(name = "inputdata")
	public Object[][] readExcelData(Method m) {
		Object[][] sheetData = null;

		sheetData = xls.getData1(m.getName(), "Test Data");
		return sheetData;
	}*/

	
	public void initilizeWebfactory(WebDriver driver)
	{
		pageFactory = new WebPageFactory(driver);
		
	}

	public WebDriver getDriver() {
		return driver;
	}
	
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	
/*
	public TestSession getSession() {
		return session;
	}

	public void setSession(TestSession session) {
		this.session = session;
	}
*/
	public WebPageFactory getPageFactory() {

		return pageFactory;
	}
	
	

	public String getCookieValue(String cookieName)
	{
		String cookie = getDriver().manage().getCookieNamed(cookieName).getValue();
		if(cookie!= null) 
			Reporter.log("Cookie exist " + cookie, true);
		else
			Reporter.log("Cookie doese not exist ", true);

		return cookie;
	}
/*
	@Parameters({ "browser","env" })
	@BeforeClass(alwaysRun = true)
	public void initialize(ITestContext testContext,
			@Optional("") String browser, @Optional("") String env) throws IOException {
		
		if(selenium == null)
			selenium = new SeleniumDriverUtil();
		
		envvalues.createPropertyFile(env);
		getenvvalues = new GetEnvValues();
		enviornment=env;	
		browserValue=browser;
		getbrowser(browser);
		
		initilizeWebfactory(driver);
		testContext.setAttribute("driver", driver);	
		this.testContextValue=testContext;
		getDriver().manage().window().maximize();
	}*/
	
	@AfterMethod(alwaysRun = true)
	public void closeMenu() {
		getDriver().manage().deleteAllCookies();
		getDriver().navigate().refresh();
		getDriver().manage().window().maximize();
		}

	
	@AfterClass(alwaysRun = true)
	public void closeBrowser() {	
		getDriver().quit();
	}
	@AfterTest(alwaysRun = true)
	public void closeBrowser1() {	
		getDriver().quit();
	}

	/*public StopWatch getStopWatch() {
		return stopWatch;
	}

	public void setStopWatch(StopWatch stopWatch) {
		this.stopWatch = stopWatch;
	}*/
	
	public String getEmailAdress(String emailAddress){
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(100000);
		String[] emailAddress1=emailAddress.split("@");
		return emailAddress1[0]+randomInt+"@"+emailAddress1[1];
	}
}