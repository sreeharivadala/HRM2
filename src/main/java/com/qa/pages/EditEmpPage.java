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

public class EditEmpPage extends TestBase {
	
	@FindBy(xpath="//h2[contains(text(),'PIM : Add Employee')]")
	WebElement addEmpText;
	
	@FindBy(id="btnEdit")
	WebElement saveBtn;
	
	@FindBy(xpath="//input[@id='txtEmpLastName']")
	WebElement lastNameEditBox;
	
	@FindBy(xpath="//input[@id='txtEmpFirstName']")
	WebElement firstNameEditBox;
	
	@FindBy(xpath="//li[@id='pim']/a")
	WebElement PIMBtn;
	WebDriverWait wait = new WebDriverWait(driver,20);
	
	//Initializing the Page Objects:
			public EditEmpPage(){
				PageFactory.initElements(driver, this);
			}
	
	
	public boolean editEmployeeAndVerify() throws InterruptedException {
		
		
		
		
	Assert.assertEquals(driver.findElement(By.xpath("//h2[contains(text(),'Employee Information')]")).getText(),"Employee Information", " Employee Information Page not found");
		
	String empToEdit = prop.getProperty("EmpFirstName") + " " + prop.getProperty("EmpLastName");
		

		int rowCnt = driver.findElements(By.xpath("//table[@class='data-table']/tbody/tr")).size();
		for(int i=1; i<=rowCnt; i++){
			
			String tEmpcode = driver.findElement(By.xpath("//table[@class='data-table']/tbody/tr["+i+"]/td[2]")).getText();
			String tEmpName = 	driver.findElement(By.xpath("//table[@class='data-table']/tbody/tr["+i+"]/td[3]")).getText();
			
			if( tEmpName.equals(empToEdit)) {
				System.out.println(tEmpcode+", "+tEmpName+" displayed at: "+i);
				driver.findElement(By.xpath("//table[@class='data-table']/tbody/tr["+i+"]/td[3]/a")).click();
				System.out.println("Match found and Clicked");
				break;
			}
					
		} 
		
		
		
		WebElement editBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='btnEditPers']")));
		editBtn.click();
			
				
		lastNameEditBox.clear();
		lastNameEditBox.sendKeys(prop.getProperty("NewEmpLastNameEdit"));
				
		firstNameEditBox.clear();
		firstNameEditBox.sendKeys(prop.getProperty("NewEmpFirstNameEdit"));
		WebElement saveBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='btnEditPers'][@value='Save']")));
		saveBtn.click();
			
		
		if(driver.findElement(By.xpath("//h2[contains(text(),'Personal Details')]")).getText().equals("Personal Details")){
			String empCode = driver.findElement(By.xpath("//input[@id='txtEmployeeId']")).getAttribute("value");
			System.out.println("Employee with code "+empCode+ " edited");
			driver.switchTo().parentFrame();
			
			Actions action = new Actions(driver);
			action.moveToElement(PIMBtn).build().perform();
			driver.findElement(By.xpath("//span[contains(text(),'Employee List')]")).click();
			driver.switchTo().frame("rightMenu");
			if(TestUtil.verifyEmpAdded(prop.getProperty("NewEmpLastNameEdit"),prop.getProperty("NewEmpFirstNameEdit"),empCode ))
				return true;
		}
		
		return false;
	}


}
