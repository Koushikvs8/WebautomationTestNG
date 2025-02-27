package com.saucedemo.qa.TestBase;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
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
	public Properties prop;
	 static ThreadLocal<WebDriver> driver =new ThreadLocal<WebDriver>();
    @Parameters("browser")
	@BeforeClass
	public void SetUp(@Optional String browsers) throws IOException
	{    
		String chosenBrowser=null;
		
		try {
			FileInputStream file;
			file = new FileInputStream("C:\\Usereclipsonly\\Koushikworkspace1\\JavaSEliniumAutomation\\saucedemo\\src\\test\\resources\\config.properties");
			prop=new Properties();
			prop.load(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String urlLink=prop.getProperty("url");
		String browserFromProperties=prop.getProperty("Brw");
		String browserFromMaven=System.getProperty(browsers);
		System.out.println(browsers);
		 chosenBrowser = (browsers !=null && !browsers.isEmpty()) ? browsers : 
             (browserFromMaven != null && !browserFromMaven.isEmpty()) ? browserFromMaven : 
             browserFromProperties;
		
			
		
		
		   System.out.println("Executing tests on: " + chosenBrowser);

	        if (driver.get() == null) {
	            switch (chosenBrowser.toLowerCase()) {
	                case "chrome":
	                    driver.set(new ChromeDriver());
	                    break;
	                case "edge":
	                    driver.set(new EdgeDriver());
	                    break;
	                case "firefox":
	                    driver.set(new FirefoxDriver());
	                    break;
	                default:
	                    throw new IllegalArgumentException("Invalid browser specified: " + chosenBrowser);
	            }
	        }
	    
			driver.get().manage().deleteAllCookies();
			driver.get().manage().window().maximize();
			driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.get().get(urlLink);
		}
	
		
	
public static WebDriver getdriver()
{
	return driver.get();
}
	
	
	@AfterClass
	public void tearDown() throws InterruptedException {
		Thread.sleep(2000);
		if(driver.get()!=null)
		{
	 driver.get().close();
		}
		driver.remove();
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
