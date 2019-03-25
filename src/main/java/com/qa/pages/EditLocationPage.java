package com.qa.pages;

import java.sql.Timestamp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.FindBy;


import com.qa.base.TestBase;
import com.qa.util.TestUtil;

public class EditLocationPage extends TestBase{

	String editLocationString;
	String newEditLocationString;
	String newEditString;
	
	WebDriverWait wait = new WebDriverWait(driver,20);
	
	@FindBy(xpath="//input[@id='editBtn']")
	WebElement editBtn;
	
	@FindBy(xpath="//input[@id='editBtn']")
	WebElement saveBtn;
	
	@FindBy(xpath="//input[@id='txtLocDescription']")
	WebElement nameBox;
	
	@FindBy(xpath="//select[@id='cmbCountry']") 
	WebElement cntryBox;
	
	@FindBy(xpath="//textarea[@id='txtAddress']")
	WebElement addrBox;
	
	@FindBy(xpath="//input[@id='txtZIP']")
	WebElement zipBox;
	
	//Initializing the Page Objects:
	public EditLocationPage(){
		PageFactory.initElements(driver, this);
		editLocationString  =  prop.getProperty("editLocation");
		newEditLocationString = prop.getProperty("newEditLocation");
	}

	public boolean editLocationAndVerify() {
		
		int rowCnt = driver.findElements(By.xpath("//table[@class='data-table']/tbody/tr")).size();
		for(int i=1; i<=rowCnt; i++){
			String tLocName = 	driver.findElement(By.xpath("//table[@class='data-table']/tbody/tr["+i+"]/td[3]")).getText();
			if( tLocName.contains(editLocationString)) {
				System.out.println(tLocName+" displayed at: "+i);
			//	driver.findElement(By.xpath("//table[@class='data-table']/tbody/tr["+i+"]/td[1]/input[@name='chkLocID[]']")).click();
				driver.findElement(By.xpath("//table[@class='data-table']/tbody/tr["+i+"]/td[3]/a")).click();
				System.out.println("Match found at row " + i+ " and Clicked");
				break;
			}
		} 
		
		editBtn = wait.until(ExpectedConditions.visibilityOf(editBtn));
		editBtn.click();
			
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		
		nameBox.clear();
		newEditString = newEditLocationString + ts;
		nameBox.sendKeys(newEditString);
				
		saveBtn.click();
			
		
		if(driver.findElement(By.xpath("//h2[contains(text(),'Company Info : Locations')]")).getText().equals("Company Info : Locations")){
			if(TestUtil.verifyLocAdded(newEditString ))
				return true;
		}
	return false;
	}

	
}
