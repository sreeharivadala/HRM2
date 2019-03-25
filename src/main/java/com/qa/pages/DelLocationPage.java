package com.qa.pages;

import java.sql.Timestamp;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.base.TestBase;
import com.qa.util.TestUtil;

public class DelLocationPage extends TestBase{

	String delLocationString;
	String delLocationFullString;
	String newEditString;
	
	WebDriverWait wait = new WebDriverWait(driver,20);
	
	@FindBy(xpath="//input[@value='Delete']")
	WebElement delBtn;
	
	
	
	//Initializing the Page Objects:
	public DelLocationPage(){
		PageFactory.initElements(driver, this);
		delLocationString  =  prop.getProperty("editLocation");
	
	}

	public boolean delLocationAndVerify() {
		
		int rowCnt = driver.findElements(By.xpath("//table[@class='data-table']/tbody/tr")).size();
		for(int i=1; i<=rowCnt; i++){
			delLocationFullString = 	driver.findElement(By.xpath("//table[@class='data-table']/tbody/tr["+i+"]/td[3]")).getText();
			if( delLocationFullString.contains(delLocationString)) {
				System.out.println(delLocationFullString+" displayed at: "+i);
				driver.findElement(By.xpath("//table[@class='data-table']/tbody/tr["+i+"]/td[1]/input[@name='chkLocID[]']")).click();
			//	driver.findElement(By.xpath("//table[@class='data-table']/tbody/tr["+i+"]/td[3]/a")).click();
				System.out.println("Match found at row " + i+ " and Clicked");
				break;
			}
		} 
		
		delBtn = wait.until(ExpectedConditions.visibilityOf(delBtn));
		delBtn.click();
			
		Alert alertWindow = driver.switchTo().alert();
		if(alertWindow.getText().contains("Deletion might affect Company Hierarchy.")){
			alertWindow.accept();
		}
		
		if(driver.findElement(By.xpath("//h2[contains(text(),'Company Info : Locations')]")).getText().equals("Company Info : Locations")){
			if(TestUtil.verifyLocAdded(delLocationFullString ) == false)
				return true;
		}
	return false;
	}

	
}
