package com.saucedemo.qa.Utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	
//////Dataprovider by Excelpage///////////
		@DataProvider(name = "dp by Execelutility")
		public static String[][] getData() throws IOException
		{    String path="C:\\Usereclipsonly\\KoushikWorkspace2\\SeleniumSaucedemoApplication\\src\\test\\resources\\LoginDDTdata.xlsx";
		  ExcelUtility xutil=new ExcelUtility(path);
		  int totalrows=xutil.getRowCount("Sheet1");
				  int totalcol=xutil.getCellCount("Sheet1", 1);
		  String logindata[][]=new String[totalrows][totalcol];
		  for (int i = 1; i <=totalrows; i++) {
			for (int j = 0; j <=totalcol-1; j++) {
				logindata[i-1][j]=xutil.getCellData("Sheet1", i, j);
			
				
			}
			
		}
			return logindata;
			
		}
	
	
	
	//////Dataprovider by Object Arry///////////
	@DataProvider(name = "dp by Arry Object")
	public Object[][] logindata()
	{
		Object data[][]= {
				{"standard_user","secret_sauce"},
				{"",""},
				{"saadbvaebfb@rghh",""},
				{"","secret_sauce"},
				{"performance_glitch_user","secret_sauce"},
				
				};
		return data;
	

}
}
