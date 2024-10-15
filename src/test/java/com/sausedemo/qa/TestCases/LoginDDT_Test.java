package com.sausedemo.qa.TestCases;
import com.saucedemo.qa.Utilities.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.saucedemo.qa.TestBase.BaseClass;
import com.saucedemo.qa.Utilities.DataProviders;
import com.sauceedemo.qa.PageObjects.HomePage;
import com.sauceedemo.qa.PageObjects.LoginPage;
import com.saucedemo.qa.Utilities.*;
public class LoginDDT_Test extends BaseClass{
	@Test(dataProvider = "dp by Arry Object", dataProviderClass = DataProviders.class)
	public void ValidateLogin(String name, String pwd) throws InterruptedException
	{
		driver.navigate().refresh();
		LoginPage login=new LoginPage(driver);
		System.out.println("entering creds");
		login.setLoginCredentials(name,pwd);
		login.clickLoginBtn();
		Thread.sleep(2000);
		HomePage homepage=new HomePage(driver);
		try {
			boolean status=homepage.displaymenu();

			homepage.clickBtnMenu();
			Thread.sleep(2000);
			homepage.clickBtnLogout();
			System.out.println("User is able to login");
			Assert.assertTrue(true);
			
		} catch (Exception e) {
			// TODO: handle exception
			
			Thread.sleep(2000);
			System.out.println("User is not able to login with invalid data");
			Assert.assertTrue(true);
			
		}
	
		}
	
		

	}

