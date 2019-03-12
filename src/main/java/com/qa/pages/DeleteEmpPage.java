package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.qa.base.TestBase;
import com.qa.util.TestUtil;

public class DeleteEmpPage extends TestBase{

	
		
	@FindBy(xpath="//li[@id='pim']/a")
	WebElement PIMBtn;
	WebDriverWait wait = new WebDriverWait(driver,20);
	
	//Initializing the Page Objects:
			public DeleteEmpPage(){
				PageFactory.initElements(driver, this);
			}
	
	
	public boolean deleteEmployeeAndVerify() throws InterruptedException {
	
	Assert.assertEquals(driver.findElement(By.xpath("//h2[contains(text(),'Employee Information')]")).getText(),"Employee Information", " Employee Information Page not found");
	String empToDelete = prop.getProperty("DeleteEmpFirstName") + " " + prop.getProperty("DeleteEmpLastName");
	int rowCnt = driver.findElements(By.xpath("//table[@class='data-table']/tbody/tr")).size();
	String tEmpcode = null;
	String tEmpName;
		for(int i=1; i<=rowCnt; i++){
			
			tEmpcode = driver.findElement(By.xpath("//table[@class='data-table']/tbody/tr["+i+"]/td[2]")).getText();
			tEmpName = 	driver.findElement(By.xpath("//table[@class='data-table']/tbody/tr["+i+"]/td[3]")).getText();
			
			if( tEmpName.equals(empToDelete)) {
				System.out.println(tEmpcode+", "+tEmpName+" displayed at: "+i);
				wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table[@class='data-table']/tbody/tr["+i+"]/td[1]/input"))).click();
				System.out.println("Match found and Clicked");
				break;
			}
					
		} 
		
		
		
		WebElement delBtn = driver.findElement(By.xpath("//input[@value='Delete']"));
		delBtn.click();
			
		WebElement succStringBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='failure']")));
		if(succStringBox.getText().equals("Successfully Deleted") && 
				(TestUtil.verifyEmpAdded(prop.getProperty("DeleteEmpLastName"),prop.getProperty("DeleteEmpFirstName"),tEmpcode )) == false){
			return true;
		}
		return false;
	
	}


	
}
