package com.common.function;


import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.testng.IReporter;
import org.testng.IResultMap;
import org.testng.ISuite;
import org.testng.ISuiteResult;
import org.testng.ITestContext;

import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.xml.XmlSuite;

public class ExtentReportClass implements IReporter
{
	private ExtentReports extent;

	public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory)
	{ Calendar lCDateTime = Calendar.getInstance();
	DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	Date date = new Date();
	String strDate=dateFormat.format(date);
	ISuiteResult r;
	String f = System.getProperty("user.dir") + File.separator+ "ExtentReport/test/19-01"
			+ File.separator
			+ "TestReport_"+strDate+".html";

	System.out.println("path"+ f);
	this.extent = new ExtentReports(f, Boolean.valueOf(true));
	Iterator<ISuiteResult> localIterator2;
	for (Iterator<ISuite> localIterator1 = suites.iterator(); localIterator1.hasNext(); 
			localIterator2.hasNext())
	{
		ISuite suite = (ISuite)localIterator1.next();
		Map<String, ISuiteResult> result = suite.getResults();

		localIterator2 = result.values().iterator(); 
		//continue;
		 r = (ISuiteResult)localIterator2.next();
		ITestContext context = r.getTestContext();

		buildTestNodes(context.getPassedTests(), LogStatus.PASS);
		buildTestNodes(context.getFailedTests(), LogStatus.FAIL);
		buildTestNodes(context.getSkippedTests(), LogStatus.SKIP);
	}
	this.extent.flush();
	this.extent.close();
	}

	private void buildTestNodes(IResultMap tests, LogStatus status)
	{
		if (tests.size() > 0) {
			for (ITestResult result : tests.getAllResults())
			{
				ExtentTest test = this.extent.startTest(result.getMethod().getMethodName());

				test.setStartedTime(getTime(result.getStartMillis()));
				test.setEndedTime(getTime(result.getEndMillis()));

				List<String> testngReporterLogs = Reporter.getOutput(result);
				for (String testngLog : testngReporterLogs) {
					test.log(LogStatus.PASS, testngLog);
				}
				if (result.getThrowable() != null) {
					test.log(status, result.getThrowable().getMessage());
				}
				String[] arrayOfString;
				int j = (arrayOfString = result.getMethod().getGroups()).length;
				for (int i = 0; i < j; i++)
				{
					String group = arrayOfString[i];
					test.assignCategory(new String[] { group });
				}
				if (result.getThrowable() != null) {
					test.log(status, result.getThrowable());
				}
				this.extent.endTest(test);
			}
		}
	}

	private Date getTime(long millis)
	{
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();
	}
}



