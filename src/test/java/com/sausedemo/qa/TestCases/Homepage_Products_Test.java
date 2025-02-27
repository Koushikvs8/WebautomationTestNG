package com.sausedemo.qa.TestCases;

import org.testng.annotations.Test;

import org.testng.Assert;
import com.saucedemo.qa.TestBase.BaseClass;
import com.sauceedemo.qa.PageObjects.HomePage;
import com.sauceedemo.qa.PageObjects.LoginPage;

public class Homepage_Products_Test extends BaseClass {
	@Test(priority = 1, groups = {"master","sanity"})
	public void validateMenu() throws InterruptedException {
		////login to page////////
		LoginPage login=new LoginPage(getdriver());
		login.login(prop.getProperty("username"),prop.getProperty("password"));
		////////////////////////////////
		HomePage homepage=new HomePage(getdriver());
		homepage.clickBtnMenu();
		homepage.displayMenulinks();
		Thread.sleep(2000);
		homepage.clickBtnCroseMenu();
	}
	@Test(priority = 2)
	public void ValidateNorOfproducts()
	{      HomePage homepage=new HomePage(getdriver());
	       int products=homepage.norOFproducts();
	      Assert.assertEquals(products, Integer.parseInt(prop.getProperty("Products")) );
		
	}
	@Test(priority = 3)
	public void ValidateAddCart()
	{      HomePage homepage=new HomePage(getdriver());
	homepage.clickAddCartGroup();
	     	
	}	

}
