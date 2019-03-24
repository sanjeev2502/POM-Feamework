package com.MyDemo.Login;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.common.function.Common;
import com.common.function.ReportLog;

public class LoginDemoPage {

	
	private WebDriver driver;
	//ReportLog ReportLog = new ReportLog();

	private Common Common;
	ReportLog ReportLog = new ReportLog();

	@FindBy(id = "email")
	private WebElement LoginUserID;
	
	
	public LoginDemoPage(WebDriver driver) {
		this.driver=driver;
		Common = new Common(driver);
		PageFactory.initElements(driver, this);
	}
	
	public boolean LoginMyDemo(String userName,String password) throws IOException{

		if (Common.setObjectValue(LoginUserID, " User Name",userName) == false) {
			ReportLog.writeLogs(driver, LoginUserID," User Name should be   entered",
					" User Name is not entered", "Fail");
			return false;

		}
		ReportLog.writeLogs(driver, LoginUserID, " User Name should be entered",
				" User Name is entered", "Pass");
		return true;

		}
}
