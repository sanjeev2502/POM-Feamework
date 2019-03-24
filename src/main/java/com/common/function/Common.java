package com.common.function;


import java.io.IOException;
import java.util.*;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.exception.ExceptionUtils;
/*import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.util.PDFTextStripper;*/
import org.openqa.selenium.*;
/*import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;*/
import org.openqa.selenium.interactions.*;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.Reporter;



/**
 * This class contains all the common methods for common selenium events which
 * can be reused through out the project.
 * 
 * 
 * 
 */

public class Common {

	private WebDriver driver;
	private String testName;
	ReportLog ReportLog= new ReportLog();
	public Common(WebDriver driver2) {

		driver = driver2;

	}

	/**
	 * This method is used for switch windows
	 */
	public void getWindowHandle(String title) {
		Set<String> handles = driver.getWindowHandles();
		if (handles.size() >= 1) {
			System.out
			.println("Number of browsers opened are" + handles.size());
			for (String handle : handles) {
				driver.switchTo().window(handle);
				if (driver.getTitle().contains(title)) {
					driver.getWindowHandle();
					break;
				}

			}
		}

	}


	/**
	 * method to handle scrolling to a particular webelement
	 * 
	 * @param e
	 *            Webelement e
	 * 
	 */
	public void scrollIntoViewPage(WebElement e) {

		((JavascriptExecutor) driver).executeScript(
				"arguments[0].scrollIntoView();", e);
	}

	/**
	 * method to modify the css value of Top attribute
	 * 
	 * @param e
	 *            , px Webelement e Pixel position px
	 * 
	 */
	public void top(WebElement e, int px) {
		((JavascriptExecutor) driver).executeScript(
				"arguments[0].setAttribute('style', 'top: 70px;');", e);
	}

	/**
	 * method is used as handle Explicit wait
	 * 
	 */
	/*public WebElement explicitWait(final By by) {
		final Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(60, TimeUnit.SECONDS)
				.pollingEvery(2, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class)
				.ignoring(TimeoutException.class)
				.ignoring(ElementNotVisibleException.class)
				.ignoring(Exception.class);
		WebElement element = wait.until(new Function<WebDriver, WebElement>() {
			@Override
			public WebElement apply(WebDriver driver) {
				return wait.until(ExpectedConditions
						.presenceOfElementLocated(by));
			}
		});
		return element;
	}*/

	/**
	 * @param mouseClick
	 *            method is to perform click operation using mouse interactions
	 *            This method was written to handle chrome related issues on
	 *            clicking on an element
	 */
	public void mouseClick(WebElement e) {
		Actions action = new Actions(driver);
		action.moveToElement(e).click().perform();
	}

	/**
	 * @param slidermovement
	 *            method is to perform drag and drop the slider from one
	 *            position to other position *
	 */
	public void slidermovement(WebElement e, int pixelsLeft, int pixelright) {
		Actions dragger = new Actions(driver);
		dragger.moveToElement(e).clickAndHold()
		.moveByOffset(pixelsLeft, pixelright).release().perform();
	}

	/**
	 * @param moveto
	 *            (WebElement e) method is to perform mouse over on particular
	 *            webelement *
	 */
	public void moveto(WebElement e) {
		try {
			Actions actn = new Actions(driver);
			actn.moveToElement(e).perform();
		} catch (MoveTargetOutOfBoundsException mtobe) {
			// reporter.log(
			// "-please wait some more time and perform move to operation to avoid this exception-because when you are trying to move the element is not visible-",
			// true);
			mtobe.printStackTrace();
		}

	}

	/**
	 * @param scrolling
	 *            () scrolling howard stern NPL page to see all the available
	 *            episodes *
	 */
	public void scrolling() {

		List<WebElement> image = driver
				.findElements(By
						.xpath("/html/body/div[3]/div/div/div[2]/div[1]/div[1]/div/div[2]/div/div[2]/div/div/div[1]/div/div[2]/div[2]/div//span[2]"));

		for (WebElement clickimg : image) {
			((JavascriptExecutor) driver).executeScript(
					"arguments[0].scrollIntoView(true);", clickimg);
			// reporter.log(clickimg.getText(), true);
		}
	}

	/**
	 * @param contextclick
	 *            (WebElement e) to perform right click on a particular
	 *            webelement
	 */
	/**
	 * Method to right click on the element
	 * 
	 * @param e: Element on which right click to perform
	 */
	public void contextclick(WebElement e) {
		Actions a = new Actions(driver);
		a.contextClick(e).perform();
	}

	/**
	 * @param dragAndDrop
	 *            (WebElement drag, WebElement drop) to perform drag and drop of
	 *            a particular element on to the target element
	 */
	public void dragAndDrop(WebElement drag, WebElement drop) {
		Actions a = new Actions(driver);
		a.dragAndDrop(drag, drop).perform();
	}

	public boolean clickHoldDragDrop(WebElement e, WebElement e1) {
		try{
			Actions action = new Actions(driver);
			action.moveToElement(e).build().perform();
			action.clickAndHold(e).release(e1).build().perform();
			//action.dragAndDrop(e, e1).build().perform();
			return true;
			// action.release(e).perform();
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
			return false;
		}
	}

	/**
	 * Page scroll down through java script.For down increase y-axis
	 */
	public void scrollDown() {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,200)", "");

	}

	/**
	 * Page scroll down through java script.For Up increase x-axis
	 */
	public void scrollUp() {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,-350)", "");

	}

	/**
	 * verifyText(String s, String s1) Method to validate the text comparision
	 */
	public boolean verifyText(String s, String s1) {

		if (s1.contains(s)) {
			return true;
		} 

		return false;
	}

	/**
	 * containsAll() returns true if the collection contains all the elements in
	 * the parameter collection, and false if not.
	 * 
	 * @param <T>
	 * @param a
	 * @param b
	 */
	public <T> void verifyListData(List<T> a, List<T> b) {
		boolean flag = false;
		String mismatchVins = null;
		if (a.containsAll(b)) {
			flag = a.removeAll(b);
			if (flag) {
				mismatchVins = getMismatchVins(a);
			}
		} else {
			flag = b.removeAll(a);
			if (flag) {
				mismatchVins = getMismatchVins(b);
			}
		}
		if (mismatchVins != null) {
			// reporter.log("not equals data:" + mismatchVins, true);
			Assert.assertTrue(false);
		} else {
			// reporter.log("equals data:", true);
			Assert.assertTrue(true);
		}

	}

	/**
	 * 
	 * @param b
	 * @return
	 */
	public <T> String getMismatchVins(List<T> b) {
		StringBuilder mismatchVins = new StringBuilder();
		int i = 0;
		for (T mismatch : b) {
			i++;
			if (i < b.size() && i != b.size()) {
				mismatchVins.append(mismatch).append(",");
			} else {
				mismatchVins.append(mismatch);
			}
			if (i == b.size()) {
				break;
			}
		}
		String mismatch1;
		if (mismatchVins.length() > b.size()) {
			mismatch1 = mismatchVins.substring(0, 99);
		} else {
			mismatch1 = mismatchVins.toString();
		}
		return mismatch1;
	}

	/**
	 * mainWindowHandle() To handle multiple windows
	 */
	public String mainWindowHandle() {
		String mainWindow = driver.getWindowHandle();
		System.out.println(mainWindow + ":main window");
		return mainWindow;
	}

	/**
	 * mainWindowTitle() To handle multiple windows
	 */
	public String mainWindowTitle() {
		String mainWindowTitle = driver.getTitle();
		System.out.println(mainWindowTitle + ":main window title");
		return mainWindowTitle;
	}

	/**
	 * childWindowHandles(String mainWindowHandle) To handle multiple windows
	 */
	public void childWindowHandles(String mainWindowHandle) {
		// maximizeWindow();
		Set<String> s = driver.getWindowHandles();
		Iterator<String> ite = s.iterator();
		while (ite.hasNext()) {
			String childWindow = ite.next().toString();
			if (!childWindow.contains(mainWindowHandle)) {
				driver.switchTo().window(childWindow);
				String childWindow_title = driver.getTitle();
				System.out.println(childWindow_title
						+ ":after switching child window");
				implicitWait(10);
			}
		}
	}

	
	/**
	 * Close ALL childWindowHandles(String mainWindowHandle) To handle multiple windows
	 */
	public void CloseAllchildWindowHandles(String mainWindowHandle) {
		// maximizeWindow();
		Set<String> s = driver.getWindowHandles();
		Iterator<String> ite = s.iterator();
		while (ite.hasNext()) {
			String childWindow = ite.next().toString();
			if (!childWindow.contains(mainWindowHandle)) {
				driver.switchTo().window(childWindow);
				
				driver.close();
		
			}
		}
		
		driver.switchTo().window(mainWindowHandle);
	}

	
	
	
	/**
	 * switchToMainWidnow(String mainWindowTitle) To handle multiple windows
	 */
	public boolean switchToMainWidnow(String mainWindowTitle) {
		boolean flag = false;
		int dSize = driver.getWindowHandles().size();
		System.out.println("windows size before closing child window:" + dSize);
		if (dSize > 1) {
			driver.close();
		}
		int dSize1 = driver.getWindowHandles().size();
		System.out.println("windows size after closing child window:" + dSize1);
		Set<String> availableWindows = driver.getWindowHandles();
		if (!availableWindows.isEmpty()) {
			for (String windowId : availableWindows) {
				if (driver.switchTo().window(windowId).getTitle()
						.equals(mainWindowTitle)) {
					flag = true;
				}
			}
		}
		return flag;
	}

	/**
	 * switchToDefault() To switch back to default page after perfomring actions
	 * in frame tag
	 */
	public void switchToDefault() {
		System.out.println("Switching to default Content");
		driver.switchTo().defaultContent(); // you are now outside both frames
	}



	/**
	 * verifyPDFText() To validate the pdf text on Siriusxm Login page
	 *//*
	public String verifyPDFText() {
		String output = null;
		try {
			URL url = new URL(driver.getCurrentUrl());
			URLConnection urlConn = url.openConnection();
			Reporter.log("current url:" + driver.getCurrentUrl(), true);
			FileInputStream fileToParse = new FileInputStream(urlConn.getURL()
					.getFile());
			PDFParser parser = new PDFParser(fileToParse);
			parser.parse();
			output = new PDFTextStripper().getText(parser.getPDDocument());
			Reporter.log("PDF text is:" + output, true);
			parser.getPDDocument().close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return output;
	}*/

	/**
	 * pageLoad(int seconds) wait for page to load before timeout exception is
	 * thrown
	 */
	public void pageLoad(int seconds) {
		driver.manage().timeouts()
		.pageLoadTimeout(seconds * 10, TimeUnit.SECONDS);
	}

	public boolean clickElement(WebElement e,String objectname) throws Exception
	{
		try{

			e.click();
			ReportLog.writeLogs(driver, e,objectname+" should be clicked "
					,objectname+" is clicked", "True");


			return true;
		}


		catch(Exception exp)
		{
			ReportLog.writeLogs(driver, e,objectname+" should be clicked "
					,objectname+" is not clicked. Exception -: "+exp, "False");
			return false;
		}
	}
	public boolean clickElementJSByCSS(String ed,String objectname) throws Exception
	{	//WebElement e=driver.findElement(By.cssSelector(ed));
		try{
			JavascriptExecutor js=(JavascriptExecutor)driver;
			String js1="window.document.querySelector(\""+ed+"\").click()";
			System.out.println("Here "+js1);
			js.executeScript(js1);
			System.out.println("Executed");
			return true;
		}


		catch(Exception exp)
		{
			Reporter.log("Some Exception happened ");
			Reporter.log(ExceptionUtils.getStackTrace(exp));
			return false;
		}
	}

	// Function :-clickOnObject
	//This function will work for all click operations except on RadioButton and ChechBox Click for which clickOnDynamicObject can be used .
	public boolean clickOnObject(WebElement e, String objectname)  {

		boolean isClickDone = false;

		if (e == null) {
			System.out.println(objectname + "does not exist on webpage");
			Reporter.log(objectname + "does not exist on webpage");
			return false;
		}

		//handleUnexpectedDialog(3);

		for(int i=1;i<=3;i++){
			try {
				//if (isElementPresent(e)) {
				if (waitForElementToBeDisplayed(e,60)) {
					Thread.sleep(8000);
					e.click();
					// This code is used for PhantomJS.. 
					/*Actions builder = new Actions(driver);
					builder.moveToElement(e).click().build().perform();*/
					
					System.out.println("clicked the " + objectname);
					isClickDone = true;
					break;
				}
			} catch (Exception exception3) {
				if(i==3){
					Reporter.log("Some Exception happened ");
					Reporter.log(ExceptionUtils.getStackTrace(exception3));
				}
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}

		/*if (isClickDone == false) {

			return false;
		}*/

		// Reporter.log("Clicked the " + objectname);
		return isClickDone;

	}

	// Function :-clickOnDynamicObject
	//This function will work for RadioButton and ChechBox Click.
	public boolean clickOnDynamicObject(WebElement e, String objectname) {

		boolean isClickDone = false;

		if (e == null) {
			System.out.println(objectname + "does not exist on webpage");
			Reporter.log(objectname + "does not exist on webpage");
			return false;
		}

		//handleUnexpectedDialog(1);

		for(int i=1;i<=3;i++){
			try {
				//e=waitForElementToBeDisplayed(e,60);// Commented By rk423c otherwise will not work for radiobutton and checkBox.
				//if (isElementPresent(e)) {
				if (e!=null) {
					e.click();
/*
						Actions builder = new Actions(driver);
						builder.moveToElement(e).click().build().perform();
					/*String javaScript = "var evObj = document.createEvent('MouseEvents');"
							+ "evObj.initMouseEvent(\"click\",true, true, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
							+ "arguments[0].dispatchEvent(evObj);";

					((JavascriptExecutor) driver).executeScript(javaScript, e);*/

					System.out.println("clicked the " + objectname);
					isClickDone = true;
					break;
				}
			} catch (Exception exception3) {
				if(i==3){
					Reporter.log("Some Exception happened ");
					Reporter.log(ExceptionUtils.getStackTrace(exception3));
				}
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}

		return isClickDone;
	}

	public boolean selectByVisibleText(WebElement e, String objectname,String objValue) {
		boolean isSelectDone = false;


		try {

			if (e!=null) {

				Select select = new Select(e);
				select.selectByVisibleText(objValue);
				isSelectDone=true;
				Reporter.log(objectname + " Value is Selected to :-" + objValue);
			}
		} catch (Exception exception3) {

			Reporter.log("Some Exception happened ");
			System.out.println(exception3);
			Reporter.log(ExceptionUtils.getStackTrace(exception3));
		}
		return isSelectDone;

	}

	public boolean selectByValue(WebElement e, String objectname,
			String objValue) {

		boolean isSelectDone = false;

		if (e == null) {
			System.out.println(objectname + "does not exist on webpage");
			Reporter.log(objectname + "does not exist on webpage");
			return false;
		}

		//handleUnexpectedDialog(1);

		try {

			//if (isElementPresent(e)) {
			if (e!=null) {
				//	e=waitForElementToBeDisplayed(e,40);// Commented By rk423c otherwise will not work for select operation.
				Select select = new Select(e);
				//select.deselectByValue(objValue);
				select.selectByValue(objValue);

				isSelectDone = true;
				Reporter.log(objectname + " Value is Selected to :-" + objValue);
			}
		} catch (Exception exception3) {
			Reporter.log("Some Exception happened ");
			System.out.println(exception3);
			Reporter.log(ExceptionUtils.getStackTrace(exception3));
		}

		return isSelectDone;
	}
	public boolean selectByValueREG(WebElement e, String objectname,
			String objValue) {

		boolean isSelectDone = false;

		if (e == null) {
			System.out.println(objectname + "does not exist on webpage");
			Reporter.log(objectname + "does not exist on webpage");
			return false;
		}

		//handleUnexpectedDialog(1);

		try {

			//if (isElementPresent(e)) {
			if (e!=null) {
				//	e=waitForElementToBeDisplayed(e,40);// Commented By rk423c otherwise will not work for select operation.
				Select select = new Select(e);
				select.selectByValue(objValue);


				isSelectDone = true;
				Reporter.log(objectname + " Value is Selected to :-" + objValue);
			}
		} catch (Exception exception3) {
			Reporter.log("Some Exception happened ");
			System.out.println(exception3);
			Reporter.log(ExceptionUtils.getStackTrace(exception3));
		}

		return isSelectDone;
	}

	public boolean selectByIndex(WebElement e, String objectname, int objValue) {

		boolean isSelectDone = false;

		if (e == null) {
			System.out.println(objectname + "does not exist on webpage");
			Reporter.log(objectname + "does not exist on webpage");
			return false;
		}

		//handleUnexpectedDialog(1);

		try {

			//if (isElementPresent(e) == true) {

			//e=waitForElementToBeDisplayed(e,60);  // Commented By rk423c otherwise will not work for select operation.
			if (e!=null) {
				Select select = new Select(e);
				select.selectByIndex(objValue);
			}

			isSelectDone = true;
			Reporter.log(objectname + " Value is Selected to :-" + objValue);
		} catch (Exception exception3) {
			Reporter.log("Some Exception happened ");
			Reporter.log(ExceptionUtils.getStackTrace(exception3));
		}

		return isSelectDone;
	}

	public boolean setObjectValue(WebElement e, String objectname,
			String objValue) {

		boolean isSetDone = false;

		if (e == null) {
			System.out.println(objectname + "does not exist on webpage");
			Reporter.log(objectname + "does not exist on webpage");
			return false;
		}

		//handleUnexpectedDialog(3);
		for(int i=1;i<=3;i++){
			try {
				if (waitForElementToBeDisplayed(e,60)) {
					//if (isElementPresent(e)) {
					
					e.clear();
					e.sendKeys(objValue);
					System.out.println("Enter in text box:::" + objValue);
					isSetDone = true;
					Reporter.log(objectname + " Value is set to :-" + objValue);
					break;
				}
			} catch (Exception exception3) {
				Reporter.log("Some Exception happened ");
				Reporter.log(ExceptionUtils.getStackTrace(exception3));
			}
		}
		return isSetDone;
	}
	
	public boolean setObjectValueBackSpace(WebElement e, String objectname,
			String objValue) {

		boolean isSetDone = false;

		if (e == null) {
			System.out.println(objectname + "does not exist on webpage");
			Reporter.log(objectname + "does not exist on webpage");
			return false;
		}

		//handleUnexpectedDialog(3);
		
			try {
				if (waitForElementToBeDisplayed(e,60)) {
					//if (isElementPresent(e)) {
					System.out.println("text "+e.getText()+"size is "+e.getText().length());
					for(int j=0;j<30;j++){
						e.sendKeys(Keys.BACK_SPACE);
						}
					
					e.sendKeys(objValue);
					System.out.println("Enter in text box:::" + objValue);
					isSetDone = true;
					Reporter.log(objectname + " Value is set to :-" + objValue);
					
				}
			} catch (Exception exception3) {
				Reporter.log("Some Exception happened ");
				Reporter.log(ExceptionUtils.getStackTrace(exception3));
			}
	
		return isSetDone;
	}

	public boolean setObjectValueJS(String id, String objectname,
			String objValue) {

		boolean isSetDone = false;

		if (id == "") {
			System.out.println(objectname + "does not exist on webpage");
			Reporter.log(objectname + "does not exist on webpage");
			return false;
		}

		try {
			JavascriptExecutor js=(JavascriptExecutor)driver;
			js.executeScript("document.getElementById('"+id+"').value='"+objValue+"'");
			System.out.println("Enter in text box:::" + objValue);
			isSetDone = true;
			Reporter.log(objectname + " Value is set to :-" + objValue);


		} catch (Exception exception3) {
			Reporter.log("Some Exception happened ");
			Reporter.log(ExceptionUtils.getStackTrace(exception3));
		}

		return isSetDone;
	}

	public boolean setObjectValueComplex(WebElement e, String objectname,
			String objValue) {

		boolean isSetDone = false;

		if (e == null) {
			System.out.println(objectname + "does not exist on webpage");
			Reporter.log(objectname + "does not exist on webpage");
			return false;
		}

		//handleUnexpectedDialog(3);

		try {
			if (waitForElementToBeDisplayed(e,60)) {

				//if (isElementPresent(e)) {
				e.clear();
				e.sendKeys(objValue);
				e.sendKeys(Keys.ENTER);
				Thread.sleep(5000);

				System.out.println("Enter in text box:::" + objValue);
				isSetDone = true;
				Reporter.log(objectname + " Value is set to :-" + objValue);
			}
		} catch (Exception exception3) {
			Reporter.log("Some Exception happened ");
			Reporter.log(ExceptionUtils.getStackTrace(exception3));
		}

		return isSetDone;
	}

	public boolean verifyObjectContainsValue(WebElement e, String objectname,
			String objValue) {

		boolean isSetDone = false;

		if (e == null) {
			System.out.println(objectname + "does not exist on webpage");
			Reporter.log(objectname + "does not exist on webpage");
			return false;
		}



		try {
			if (waitForElementToBeDisplayed(e,60)) {
				//if (isElementPresent(e)) {
				if (e.getText().contains(objValue)) {
					//Reporter.log(objectname + " Contains Value as :-"
					//	+ objValue);
					isSetDone = true;
					Reporter.log(objectname + " Contains Value as :-" + objValue);
				}
			}

		} catch (Exception exception3) {
			Reporter.log("Some Exception happened ");
			Reporter.log(ExceptionUtils.getStackTrace(exception3));
		}

		return isSetDone;
	}

	public boolean verifyObjectEqualValue(WebElement e, String objectname,
			String objValue) {

		boolean isSetDone = false;

		if (e == null) {
			System.out.println(objectname + "does not exist on webpage");
			Reporter.log(objectname + "does not exist on webpage");
			return false;
		}

		//handleUnexpectedDialog(3);

		try {
			if (waitForElementToBeDisplayed(e,60)) {
				//if (isElementPresent(e)) {
				if (e.getText().equalsIgnoreCase(objValue)) {
					isSetDone = true;
					Reporter.log(objectname + " equal Value to :-" + objValue);
				}
			}

		} catch (Exception exception3) {
			Reporter.log("Some Exception happened ");
			Reporter.log(ExceptionUtils.getStackTrace(exception3));
		}

		return isSetDone;
	}
	// Function :-getObjectText
	//This function is to take text of an element and will be stored in StringBuilder parameter of objectText function. 
	public boolean getObjectText(WebElement e, String objectName,StringBuilder wireLessCountValue) {
		boolean isSetDone = false;
		if (e == null) {
			System.out.println(objectName + "does not exist on webpage");
			Reporter.log(objectName + "does not exist on webpage");
			return false;
		}

		//	handleUnexpectedDialog(3);

		//	handleUnexpectedDialog(3);



		//	handleUnexpectedDialog(3);


		try {

			wireLessCountValue.append(e.getText());
			isSetDone = true;
			Reporter.log(objectName+ " getText is :-" + wireLessCountValue);

		} catch (Exception exception3) {
			Reporter.log("Some Exception happened ");
			Reporter.log(ExceptionUtils.getStackTrace(exception3));
		}

		return isSetDone;
	}



	public boolean verifyObjectEqualValueWithAttribute(WebElement e,String attributeName, String objectname,
			String objValue) {

		boolean isSetDone = false;

		if (e == null) {
			System.out.println(objectname + "does not exist on webpage");
			Reporter.log(objectname + "does not exist on webpage");
			return false;
		}

		//handleUnexpectedDialog(3);

		try {
			if (waitForElementToBeDisplayed(e,60)) {
				//if (isElementPresent(e)) {
				if (e.getAttribute(attributeName).equalsIgnoreCase(objValue)) {
					isSetDone = true;
					Reporter.log(objectname + " not equal Value to :-" + objValue);
				}
			}

		} catch (Exception exception3) {
			Reporter.log("Some Exception happened ");
			Reporter.log(ExceptionUtils.getStackTrace(exception3));
		}

		return isSetDone;
	}

	public void handleUnexpectedDialog(int timeout) {

		WebDriverWait Wait = new WebDriverWait(driver, timeout);

		WebElement element = null;
		try {

			element = Wait
					.until(ExpectedConditions.presenceOfElementLocated(By
							.xpath("//*[@class='fsrCloseBtn' or @id='tcChat_btnCloseChat_img']")));
			if (element != null) {
				if (element.isEnabled()) {
					element.click();
					Reporter.log("Feedback  or chat dialog poped up. Clicked to get rid off");
				}
			}
		} catch (Exception e) {
			// System.out.println("some exception occured dialog  "
			// +e.toString());

		}

	}

	/**
	 * isElementPresent(WebElement e) Method to validate a particular webelement
	 * is available on webpage or not
	 */
	public boolean isElementPresent(WebElement e) {

		try {

			if (e == null) {
				Reporter.log("Control does not exist on page");
				return false;
			}
			//handleUnexpectedDialog(1);

			if (e.isEnabled() == false) {
				System.out.println("Element not displayed");
				return false;
			}

		} catch (NoSuchElementException nsee) {
			Reporter.log(e.toString() + " is not available");
			return false;

		} catch(Exception Ex){
			//Catch the exception if it is not caught in the above catches
			//handleUnexpectedDialog(3);
			Reporter.log("FAIL"+"--Exception found--", true);
			Reporter.log(ExceptionUtils.getStackTrace(Ex));
			return false;
		}

		System.out.println("Information ::::Element is available for further actions");
		return true;
	}

	/**
	 * findDuplicates(List<String> input Method to check duplicates in a list
	 * 
	 */
	public static HashSet<String> findDuplicates(List<String> input) {
		List<String> copy = new ArrayList<String>(input);
		for (String value : new HashSet<String>(input)) {
			copy.remove(value);
		}
		return new HashSet<String>(copy);
	}

	/*public boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}*/

	//Waiting Method: Starts

	public boolean waitForPageLoadwithDynamicControl(WebElement ee,
			String validationstring) {
		boolean isPageReadyForValidation = false;
		long startTime = System.currentTimeMillis();
		long endTime = startTime + 60 * 1000;

		if (waitForPageIsLoadingProgress(driver, startTime, endTime) == false) {
			Reporter.log("spinner is appearing");
			isPageReadyForValidation = false;
		}
		if (waitForPageLoad(driver) == false) {
			Reporter.log("not loaded in given time");
			isPageReadyForValidation = false;
		}
		//handleUnexpectedDialog(3);

		if (waitForElementToBeDisplayed(ee,40)) {
			isPageReadyForValidation=true;
		}
		return isPageReadyForValidation;

	}

	public boolean waitForPageIsLoadingProgress(WebDriver driver,
			long startTime, long endTime) {

		boolean flag = true;

		try {
			String spinnerAppearing = "<div class=\"spinner\" ng-show=\"isLoading\">";

			//handleUnexpectedDialog(2);

			while ((driver.getPageSource().contains(spinnerAppearing))) {

				if (System.currentTimeMillis() > endTime) {
					Reporter.log("Wait..... Spinner is appearing");
					flag = false;
					System.out.println("Spinner is appearing");

				}
				Thread.sleep(30);
			}

		} catch (Exception e) {
		}

		return flag;
	}

	public boolean waitForPageLoad(WebDriver driver) {

		long startTime = System.currentTimeMillis();
		long waitTime = 120000;
		String pageLoadStatus = null;
		try {
			do {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				pageLoadStatus = (String) js
						.executeScript("return document.readyState");
				System.out.print((System.currentTimeMillis() - startTime));

				if ((System.currentTimeMillis() - startTime) > waitTime) {

					Reporter.log("timeout completed " + waitTime + "ms");

					return false;
				}
				System.out.println("waiting for page load");
				Thread.sleep(100);
			} while (!pageLoadStatus.equals("complete"));
			System.out.println("Page Loaded correctly");

		} catch (Exception e) {
			System.out.println("some Exeption happened during page load:::::"
					+ e.toString());
		}

		return true;

	}

	public void MouseHoverFunction(WebElement e) {

		try {
			if(waitForElementToBeDisplayed(e,40)){
				String javaScript = "var evObj = document.createEvent('MouseEvents');"
						+ "evObj.initMouseEvent(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
						+ "arguments[0].dispatchEvent(evObj);";

				((JavascriptExecutor) driver).executeScript(javaScript, e);

				System.out.println("MouseHoverFunction");
			}
		} catch (Exception ee) {
		}

	}

	public boolean waitForElementToBeDisplayed(WebElement element, int timeInSeconds) {

		try {

			if(element == null)
				return false;

			WebDriverWait wait=new WebDriverWait(driver, timeInSeconds);
			wait.until(ExpectedConditions.visibilityOf(element));

			if (element.isDisplayed()) 
				return true;

			return false;

		} catch(NoSuchElementException e){
			//handleUnexpectedDialog(3);
			Reporter.log("FAIL"+"--No Such Element Exception--", true);
			Reporter.log(ExceptionUtils.getStackTrace(e));
			return false;

		} catch (StaleElementReferenceException e) {

			//handleUnexpectedDialog(3);
			Reporter.log("--Stale Time  Exception ignored--", true);
			return false;

		} catch (TimeoutException toe) {

			//handleUnexpectedDialog(3);
			Reporter.log("FAIL"+"--Time Out Exception--", true);
			Reporter.log(ExceptionUtils.getStackTrace(toe));
			return false;

		} catch(Exception e){
			//Catch the exception if it is not caught in the above catches
			//handleUnexpectedDialog(3);
			Reporter.log("FAIL"+"--Exception found--", true);
			Reporter.log(ExceptionUtils.getStackTrace(e));
			return false;
		}
	}

	/**
	 * elementToBeClickable(final By by, int timeInSeconds) Method to perform
	 * click operation on any element once it is visible to perform the action
	 */
	/*public WebElement elementToBeClickable(final By by, int timeInSeconds) {
		return new FluentWait<WebDriver>(driver)
				// Wait for the condition
				.withTimeout(timeInSeconds * 10, TimeUnit.SECONDS)
				// which to check for the condition with interval of 5 seconds.
				.pollingEvery(2, TimeUnit.SECONDS)
				// Which will ignore the NoSuchElementException
				.ignoring(NoSuchElementException.class)
				.ignoring(ElementNotVisibleException.class)
				.ignoring(WebDriverException.class)
				.until(new ExpectedCondition<WebElement>() {
					public ExpectedCondition<WebElement> FluentWaitMethodNameObj = ExpectedConditions
							.elementToBeClickable(by);

					@Override
					public WebElement apply(WebDriver driver) {
						WebElement element = FluentWaitMethodNameObj
								.apply(driver);
						try {
							if (element != null && element.isDisplayed()) {
								return element;
							} else {
								return null;
							}
						} catch (StaleElementReferenceException e) {
							// reporter.log("--Stale Element Exception--",
							// true);
							e.printStackTrace();
							return null;
						} catch (TimeoutException toe) {
							// reporter.log("--Time Out Exception--", true);
							toe.printStackTrace();
							return null;
						}
					}

				});
	}
	 */
	/**
	 * waitForElementPresent(final By by, int timeOutInSeconds) Method to check
	 * element is visible or not .If not available wait for the amount of the
	 * time given
	 */
	public void waitForElementPresent(final By by, int timeOutInSeconds) {
		try {
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS); // nullify
			// implicitlyWait()

			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			wait.until(ExpectedConditions
					.presenceOfElementLocated(by));

			//driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS); // reset

			// implicitlyWait
			// return the element
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * waitForElementPresent(final By by, int timeOutInSeconds) Method to check
	 * element is visible or not .If not available wait for the amount of the
	 * time given
	 */
	public void waitForElementToClickable(WebElement elem, int timeOutInSeconds) {
		try {
			//driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS); // nullify
			// implicitlyWait()

			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			wait.until(ExpectedConditions
					.elementToBeClickable(elem));

			//driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS); // reset
			// implicitlyWait
			// return the element
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * waitForDriver(WebElement e, int sleeptTime, int rounds) wait for
	 * particular element and loop for every given number of seconds
	 */
	public boolean waitForDriver(WebElement e, int sleeptTime, int rounds) {
		boolean flag = false;

		L1: for (int i = 0; i < rounds; i++) {
			driver.manage().timeouts()
			.implicitlyWait(sleeptTime * 10, TimeUnit.SECONDS);
			try {
				//if (isElementPresent(e)) { Changed by Ankur: 30th Nov 16
				if (e != null) {
					flag = true;
					Assert.assertTrue(flag);
					break;
				} else {
					throw new Exception("element not displayed");
				}
			} catch (Exception ee) {
				// reporter.log("-wait for element-" + e, true);
				continue L1;
			}
		}

		return flag;
	}

	/**
	 * waitForElement(final WebElement e, int timeOut,WebDriver driver) Selenium
	 * explicit wait command to wait for particular webelement
	 */
	/*public boolean waitForElement(final WebElement e, int timeOut,
			WebDriver driver) {
		try {
			new WebDriverWait(driver, timeOut) {
			}.until(new ExpectedCondition<Boolean>() {

				@Override
				public Boolean apply(WebDriver driverObject) {
					//return isElementPresent(e); Changed by Ankur: 30th Nov 16
					return (e != null) ? true : false;
				}
			});
		} catch (NoSuchElementException noSuchElementException) {
			noSuchElementException.printStackTrace();
			System.out.println("NoSuchElementException exception for : "
					+ noSuchElementException.toString());
			Reporter.log("NoSuchElementException exception for "
					+ noSuchElementException.toString());
			return false;
		} catch (TimeoutException timeoutException) {
			timeoutException.printStackTrace();
			System.out.println("TimeoutException exception for : "
					+ timeoutException.toString());
			Reporter.log("TimeoutException exception for "
					+ timeoutException.toString());
			return false;
		} catch (Exception exception) {
			exception.printStackTrace();
			System.out.println("Some other exception for : "
					+ exception.toString());
			Reporter.log("Some other exception for " + exception.toString());
			return false;
		} finally {
			driver.manage().timeouts()
					.implicitlyWait(timeOut, TimeUnit.MILLISECONDS);
		}

		System.out.println(e.toString() + " is enabled: ");
		Reporter.log(e.toString() + " is enabled: ");
		return true;
	}*/

	/**
	 * @param dragAndDrop
	 *            (WebElement drag, WebElement drop) to perform drag and drop of
	 *            a particular element on to the target element
	 */
	public void impicitWait(int seconds) {

		try {
			driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * implicitWait(int seconds) wait command for implicitly waiting for the
	 * page to load
	 */
	public void implicitWait(int seconds) {
		try {
			driver.manage().timeouts()
			.implicitlyWait(seconds * 10, TimeUnit.SECONDS);
		} catch (TimeoutException toe) {
			toe.printStackTrace();
		}
	}

	//Waiting Method: Ends

	/**
	 * switchToBack() To switch back to previous page after perfomring actions
	 * in frame tag
	 */
	public boolean switchToBack() {
		driver.navigate().back();
		return true;
	}

	public void switchToFrameById(String Id) throws InterruptedException {
		System.out.println("Frame is ");
		Thread.sleep(5000);
		String frame=driver.findElement(By.tagName("Iframe")).getAttribute("id");
		System.out.println("Frame is "+frame);
		Thread.sleep(5000);
		driver.switchTo().frame(frame); // you are now outside both frames
	}
	public void switchToFrameByName(String Name) {
		driver.switchTo().frame(Name); // you are now outside both frames
	}






	public String getText(WebElement e, String objectname)  {
		String s=null;
		if (e == null) {
			System.out.println(objectname + "does not exist on webpage");
			Reporter.log(objectname + "does not exist on webpage");
		}
		else{
			s= e.getText();
			Reporter.log(objectname + "="+s);


		}
		return s;
	}

	/**
	 * @param refreshPage
	 *            method is to perform refresh the page
	 */
	public void refreshPage() {

		driver.navigate().refresh();
	}

	public boolean verifyText(WebElement e, String text) throws InterruptedException,IOException
	{
		System.out.println("Outside verification");
		Thread.sleep(10000);
		String st=getText(e,text);
		if (st.contains(text) == false) {
			ReportLog.writeLogs(driver, e,text+" message should be Visible: "+st
					,text+" message is not visible", "Fail");
			System.out.println("Inside verification Fail "+st);
			return false;

		}
		ReportLog.writeLogs(driver, e,text+" message should be Visible: "+st
				,text+" message is  Visible", "Pass");
		System.out.println("Inside verification Pass "+st);
		return true;

	}

	//private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	public static String randomAlphaNumeric(int count) {
		Random r = new Random(); // perhaps make it a class variable so you don't make a new one every time

		StringBuilder sb = new StringBuilder();
		sb.append("TestRegistration");
		for(int i = 0; i < count; i++) {
			sb.append(r.nextInt(9));

		}

		return sb.toString();
	}

	//Public static final String NUMERIC_STRING = "0123456789";
	public static String randomNumeric(int count) {
		Random r = new Random(); // perhaps make it a class variable so you don't make a new one every time

		StringBuilder sb = new StringBuilder();
		//sb.append("TestRegistration");
		for(int i = 0; i < count; i++) {
			sb.append(r.nextInt(9));

		}

		return sb.toString();
	}
	//Close Modal Window ID
	public boolean closeModal(String id){
		try{
			JavascriptExecutor js=(JavascriptExecutor)driver;
			js.executeScript("document.getElementById('"+id+"').style.display = 'none'");
			Reporter.log("Modal with id ("+id+") is closed Succesfully" );
			return true;
		}
		catch(Exception ex){
			Reporter.log("Modal is not closed Succesfully" );
			System.out.println(ex.getMessage());
			return false;

		}

	}	

	public boolean verifyPageTitle(String etitle)
	{
		if(etitle=="")
		{
			return false;
		}else{
			System.out.println("Verifying Title");
			driver.getTitle().contains(etitle);
			System.out.println("Title is verified");
			return true;
		}

	}
	public boolean verifyCurrentUrlContains(String eUrl)
	{
	
		if(eUrl=="")
		{
			return false;
		}else{
			System.out.println("Verifying URL");
		Boolean	flag=driver.getCurrentUrl().contains(eUrl);
			if(flag==true)
			System.out.println("URL is verified");
			else
			 System.out.println("URL Mismatch");
			return flag;
		}

	}






}


