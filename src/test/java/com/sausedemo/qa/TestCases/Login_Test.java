package com.sausedemo.qa.TestCases;


import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



import org.testng.Assert;



import com.saucedemo.qa.TestBase.BaseClass;
import com.saucedemo.qa.Utilities.DataProviders;
import com.sauceedemo.qa.PageObjects.HomePage;
import com.sauceedemo.qa.PageObjects.LoginPage;



public class Login_Test extends BaseClass{
	@Test(groups = {"master","smoke","regression"})
	public void ValidateLogin() throws InterruptedException
	{   
		getdriver().navigate().refresh();
		LoginPage login=new LoginPage(getdriver());
		login.setLoginCredentials(prop.getProperty("username"),prop.getProperty("password"));
		login.clickLoginBtn();
		Thread.sleep(2000);
		HomePage homepage=new HomePage(getdriver());
		try {
			boolean status=homepage.displaymenu();

			homepage.clickBtnMenu();
			Thread.sleep(2000);
			homepage.clickBtnLogout();
			System.out.println("User is able to login");
			Assert.assertTrue(false);
		} catch (Exception e) {
			// TODO: handle exception
			
			Thread.sleep(2000);
			System.out.println("User is not able to login with invalid data");
			Assert.assertTrue(true);
			
		}
	
		}
	
		

	}


