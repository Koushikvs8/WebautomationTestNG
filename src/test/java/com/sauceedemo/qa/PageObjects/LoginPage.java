package com.sauceedemo.qa.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage  {

	public LoginPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	//////////////////////////login page web elements web elements//////////////////////////
	
@FindBy(xpath = "//input[@id='user-name']")
WebElement txtLoginUserName;

@FindBy(xpath = "//input[@id='password']")
WebElement txtLoginPassword;

@FindBy(xpath = "//input[@id='login-button']")
WebElement btnLogin;



//////////////////////////login page Actions//////////////////////////

public void setLoginCredentials(String username, String password )
{
	txtLoginUserName.sendKeys(username);
	txtLoginPassword.sendKeys(password);
}

public void clickLoginBtn()
{
	btnLogin.click();
}

public void clearLoginCred()
{
	txtLoginUserName.clear();
	txtLoginPassword.clear();
}
///////
public void login(String username, String password) throws InterruptedException
{
	System.out.println("entering creds");
	driver.manage().window().maximize();
	setLoginCredentials(username,password);
	clickLoginBtn();
	Thread.sleep(2000);

}
}
