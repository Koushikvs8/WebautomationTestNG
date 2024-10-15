package com.saucedemo.qa.TestBase;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BaseClass {
	public static WebDriver driver;
	public Properties prop;
	
	@BeforeClass(groups = {"master","smoke","regression"})
	@Parameters({"browser"})
	public void SetUp(String browser) throws IOException
	{    
		
		FileInputStream file;
		try {
			file = new FileInputStream(System.getProperty("user.dir") +"\\src\\test\\resources\\config.properties");
			prop=new Properties();
			prop.load(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String urlLink=prop.getProperty("url");
		String Browser=browser;
		
		if(Browser.equalsIgnoreCase("chrom"))
		{
			driver=new ChromeDriver();
			driver.get(urlLink);
		}
		else if (Browser.equalsIgnoreCase("Edge")) {
			
				driver=new EdgeDriver();
			    driver.get(urlLink);
		}
		else if (Browser.equalsIgnoreCase("firfox")) {
			{   driver=new FirefoxDriver();
				driver.get(urlLink);
			}
			driver.manage().deleteAllCookies();
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		}
	
		
	}

	
	
	@AfterClass
	public void tearDown() throws InterruptedException {
		Thread.sleep(2000);
	 driver.close();
	}
	//screenshot capture
	public String captureScreen(String tname)
	{
		String timeStamp=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot takesScreenshot=(TakesScreenshot) driver;
		File sourceFile=takesScreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\"+tname+"_"+timeStamp;
		File targetFile=new File(targetFilePath);
		sourceFile.renameTo(targetFile);
		return targetFilePath;
		
	}
	
}
