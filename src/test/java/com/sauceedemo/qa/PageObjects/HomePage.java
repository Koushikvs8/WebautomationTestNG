package com.sauceedemo.qa.PageObjects;

import java.util.List;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}
	
	
	///////////////////////prodct page elements /////////////////////////
	@FindBy(xpath = "//span[@class='title']")
	WebElement titleproduct;
	
	@FindBy(xpath = "//button[@id='react-burger-menu-btn']")
    WebElement BtnMenu;
	
	@FindBy(xpath = "//a[@id='logout_sidebar_link']")
    WebElement BtnLogout;
	
	@FindBy(xpath = "//a[@id='about_sidebar_link']")
    WebElement aboutlink;
	
	@FindBy(xpath = "//a[@id='inventory_sidebar_link']")
    WebElement allitemlink;
	
	@FindBy(xpath = "//a[@id='reset_sidebar_link']")//button[@id='react-burger-cross-btn']
    WebElement resetLink;
	
	@FindBy(xpath = "//button[@id='react-burger-cross-btn']")
    WebElement BtnCrossMenu;
	///////////////////////// products elements////////////
	@FindBy(xpath = "//div[@class='inventory_list']//div[@class='inventory_item']")
     List<WebElement> productItems;
	
	@FindBy(xpath = "//div[@class='inventory_list']//div[@class='inventory_item_img']")
	WebElement prodImage;
	
	@FindBy(xpath = "//div[@class='inventory_list']//div[@class='inventory_item_img']")
	WebElement prodImge;
	
	@FindBy(xpath = "//div[@class='inventory_item_label']//a[@id='item_"+0+"title_link']")// 0 is element changes with changing value of zero
	WebElement prodName;
	
	@FindBy(xpath = "//div[@class='inventory_list']//div[@class='inventory_item'][1]")// 1 is element changes with changing value of zero
	WebElement price;
	
	@FindBy(xpath = "//button[@class='btn btn_primary btn_small btn_inventory ']")
	WebElement addCart;
	
	@FindBy(xpath = "//div[@class='inventory_list']//div[@class='inventory_item']//button") // [n] generalized xpath for ADD to cart
	List<WebElement> addCarts;
	///////////////////////////// Actions//////////////////
	
	///////Menu Actions///////////////////
	public boolean displaymenu()
	{
		boolean status=titleproduct.isDisplayed();
		return status;
	}
	
	public boolean displayMenulinks()
	{   boolean status = false;
		boolean about=aboutlink.isDisplayed();
		boolean logout=BtnLogout.isDisplayed();
		boolean reset=resetLink.isDisplayed();
		boolean allitem=allitemlink.isDisplayed();
		if(about==true&& logout==true && reset==true && allitem==true)
		{
			 status= true;
		}
		return status;
	}
	
	public void clickBtnCroseMenu()
	{
		BtnCrossMenu.click();
	}
	public void clickBtnMenu()
	{
		BtnMenu.click();
	}
	
	public void clickBtnLogout()
	{
		BtnLogout.click();
	}
	//////////////////////////////////////////
	
	///////////////////////////////Products Actions////////////
	public int norOFproducts()
	{   
		int norProducts = productItems.size();
		
		return norProducts;
	}
	
  public void clickAddCartGroup()
  {
for (int i = 0; i < addCarts.size(); i++) {
	try {
		addCarts.get(i).click();//Click on AddCart
		Thread.sleep(1000);	
		String status =addCarts.get(i).getText();
		if(status=="Remove");
		{
			addCarts.get(i).click();
		}
		}
	 catch (Exception e) {
		e.printStackTrace();
	}
	
}
  }
}

