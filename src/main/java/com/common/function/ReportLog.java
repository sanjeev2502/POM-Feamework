package com.common.function;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
//import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.ITestResult;
import org.testng.Reporter;

public class ReportLog {

	File dest;
	String log4jlogs="Y";
	String flagForScreenShotEnable;
	static String testMethodName;
	//private Logger logger = Logger.getLogger(ReportLog.class.getName());

	public  static void setTestMethodName(String testMethodName1) {
		testMethodName=testMethodName1;
	}
	public ReportLog() {

	}

	public void writeLogs(WebDriver d, WebElement element, String expectedtext,
			String actualtext,String teststatus) throws IOException {
		getScreenShot(d, element, expectedtext, actualtext, teststatus);
	}

	public void writeLogsInfo(WebDriver d, 
			String info, String teststatus) throws IOException {

		Reporter.log(info);

	}


	public void writeSQLLogs(String expectedtext,
			String actualtext, String teststatus) throws IOException {
		reportSQL( expectedtext, actualtext, teststatus);
	}
	private void reportSQL(String expectedtext,String actualtext,  String teststatus)
			throws IOException {
		try

		{
			Reporter.log(" Expected: " + expectedtext + "\n Actual: "
					+ actualtext);
		}

		catch(Exception e){

			Reporter.log(ExceptionUtils.getStackTrace(e));
			Reporter.log(" Expected: "
					+ expectedtext
					+ " \n Actual: "
					+ actualtext);
		}
	}


	private void getScreenShot(WebDriver driver, WebElement element,
			String expectedtext,String actualtext,  String teststatus)
					throws IOException {
		flagForScreenShotEnable = "Y";
		StringBuilder href = null;
		try{
			if (flagForScreenShotEnable.equalsIgnoreCase("Y")) {
				//File file = new File("");
				Calendar lCDateTime = Calendar.getInstance();
				//String a = lCDateTime.getTimeInMillis() + ".jpg";
				if (driver != null) {
					if (element != null) {
						JavascriptExecutor js = (JavascriptExecutor) driver;
						js.executeScript(
								"arguments[0].style.border='3px solid red'",
								element);
					}

					File scrFile = ((TakesScreenshot) driver)
							.getScreenshotAs(OutputType.FILE);
					String f = "C:\\Testing\\Automation\\ScreenshotReport"
							+ File.separator
							+"ScreenShot/13-02"
							+ File.separator
							+ lCDateTime.getTimeInMillis() + ".jpg";
			
					System.out.println("path"+ f);
					dest = new File(f);
					FileUtils.copyFile(scrFile, dest);

					href = new StringBuilder();


					Reporter.log(" Expected:::"
							+ expectedtext
							+ "  Actual::: "
							+ actualtext
							+ href.append("<div><a HREF='"
									+ dest.getAbsolutePath()
									+ "'><img src='"
									+ dest.getAbsolutePath()
									+ "' alt='Screen Shot' style='width:250px;height:200px'></a></div>"));
				} else {
					Reporter.log(" Expected:::" + expectedtext + "Actual::: "
							+ actualtext);

				}



			}
		}catch(Exception e){

			Reporter.log(ExceptionUtils.getStackTrace(e));
			Reporter.log(" Expected:::"
					+ expectedtext
					+ "  Actual::: "
					+ actualtext);
		}
	}

}
