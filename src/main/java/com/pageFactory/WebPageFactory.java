package com.pageFactory;

import org.openqa.selenium.WebDriver;

import com.MyDemo.Login.*;



public class WebPageFactory {


	
	
	
	private LoginDemoPage LoginDemoPage;
	

	private WebDriver oDriver;



	public WebPageFactory(WebDriver driver) {
		oDriver=driver;
		

		
		LoginDemoPage=new LoginDemoPage(oDriver);
}




	
public LoginDemoPage getLoginDemoPage(){
	return LoginDemoPage;
}


}



