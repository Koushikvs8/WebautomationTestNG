package com.sauceedemo.qa.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.saucedemo.qa.TestBase.BaseClass;

public class BasePage extends BaseClass {
WebDriver driver;

public BasePage(WebDriver driver) {
	// TODO Auto-generated constructor stub
	this.driver=driver;
	PageFactory.initElements(driver, this);
}
}
