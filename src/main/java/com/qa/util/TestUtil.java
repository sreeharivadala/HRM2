package com.qa.util;

import java.io.File;
import java.io.IOException;

import org.apache.maven.surefire.shade.common.org.apache.commons.io.FileUtils;
import org.apache.poi.hslf.model.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import com.qa.base.TestBase;

public class TestUtil extends TestBase{
	
	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 20;

	public static String TESTDATA_SHEET_PATH = "/Users/naveenkhunteta/Documents/workspace"
			+ "/FreeCRMTest/src/main/java/com/crm/qa/testdata/FreeCrmTestData.xlsx";

	static Workbook book;
	static Sheet sheet; 
	static JavascriptExecutor js;
	
	
	public static void takeScreenshotAtEndOfTest() throws IOException {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String currentDir = System.getProperty("user.dir");
		FileUtils.copyFile(scrFile, new File(currentDir + "/screenshots/" + System.currentTimeMillis() + ".png"));
	}
	
	public static boolean elementPresent(WebElement element){
		if( element.isDisplayed())
			return true;
		return false;
		
	}

	public static boolean verifyEmpAdded(String lastName, String firstName, String empCode) {
		System.out.println("Employee to find is  "+ empCode + "  " +firstName  +"  " + lastName);
		
		int rowCnt = driver.findElements(By.xpath("//table[@class='data-table']/tbody/tr")).size();
		for(int i=1; i<=rowCnt; i++){
			
			String tEmpcode = driver.findElement(By.xpath("//table[@class='data-table']/tbody/tr["+i+"]/td[2]")).getText();
			String tEmpName = 	driver.findElement(By.xpath("//table[@class='data-table']/tbody/tr["+i+"]/td[3]")).getText();
			System.out.println("Employee list from table at  "+ tEmpcode + "  " + tEmpName +" at row "+i);
			if( (tEmpcode).equals(empCode) && (tEmpName.equals(firstName+" "+lastName))) {
				System.out.println("Employee with "+ tEmpcode + "  " +tEmpName +"  found at row " + i);
				return true;
			}
					
		}
			
		
		return false;
	}

}
