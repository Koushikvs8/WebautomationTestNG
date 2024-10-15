package com.saucedemo.qa.Utilities;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.awt.Desktop;
import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.saucedemo.qa.TestBase.BaseClass;

import java.awt.Desktop;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ExtendReportManeger implements ITestListener{
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	public String repName;
	public void onStart(ITestContext context) {
		
		/*SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Date dt=new Date();
		String currentdatetimestamp=df.format(dt);*/
				//above line replaced by single statement
		String timestamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName="Test-Report"+timestamp+".html";
		sparkReporter=new ExtentSparkReporter(System.getProperty("user.dir")+"\\Reports\\"+repName);
	    sparkReporter.config().setDocumentTitle("Automation report");
	    sparkReporter.config().setReportName("Functional Testing");
	    sparkReporter.config().setTheme(Theme.DARK);
	    
	    
	    extent=new ExtentReports();
	    extent.attachReporter(sparkReporter);
	    extent.setSystemInfo("computer name", "lenevo local host");
	    extent.setSystemInfo("Environment", "QA");
	    extent.setSystemInfo("Teaster", "Koushik");
	    
	    String os =context.getCurrentXmlTest().getParameter("os");
	    extent.setSystemInfo("Operating System", os);
	    String browser=context.getCurrentXmlTest().getParameter("browser");
	    extent.setSystemInfo("Browser", browser);
	    
	    List<String> includeGroups=context.getCurrentXmlTest().getIncludedGroups();
	    if(!includeGroups.isEmpty())
	    {
	    	extent.setSystemInfo("Groups", includeGroups.toString());
	    }
	  }
	public void onTestSuccess(ITestResult result) {
	    // not implemented
	 test=extent.createTest(result.getTestClass().getName());
	 test.assignCategory(result.getMethod().getGroups());
	 test.log(Status.PASS, "test case is passed"+result.getName()+"got successfully executed");
	  }
	 public void onTestFailure(ITestResult result) {
		 test=extent.createTest(result.getTestClass().getName());
		 test.assignCategory(result.getMethod().getGroups());
		 test.log(Status.FAIL, "test case is failed"+result.getName());
		 test.log(Status.FAIL, "test case is failed reasone"+result.getThrowable().getMessage());
		 
		 try {
				String imgPath=new BaseClass().captureScreen(result.getName());
				test.addScreenCaptureFromPath(imgPath);
				String logFilePath = imgPath; // Path to your log file
		      
			} catch (Exception e) {
				e.printStackTrace();
			}
		  }
	 public void onTestSkipped(ITestResult result) {
		 test=extent.createTest(result.getTestClass().getName());
		 test.assignCategory(result.getMethod().getGroups());
		 test.log(Status.SKIP, "test case is failed"+result.getName());
		 test.log(Status.SKIP, "test case is failed reasone"+result.getThrowable());
		 
	
		  }
	 public void  onFinish(ITestContext context) {
		 extent.flush();
		 
		 String pathofExtentReport=System.getProperty("user.dir")+"\\Reports\\"+repName;
		 File extentReport=new File(pathofExtentReport);
		 try {
			Desktop.getDesktop().browse(extentReport.toURI());
			// TODO: handle exception
		}
		 catch(Exception e)
		 {
			 e.printStackTrace();
		  }
	
	
}
}