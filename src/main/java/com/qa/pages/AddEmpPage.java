//author sreehari dated 3rd march
package com.qa.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.qa.base.TestBase;
import com.qa.util.TestUtil;

public class AddEmpPage extends TestBase {
	
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
	
	//Initializing the Page Objects:
			public AddEmpPage(){
				PageFactory.initElements(driver, this);
			}
	
	public String validateEmpPageTitle() {
		
		return addEmpText.getText();
		
	}

	public boolean addEmployeeAndVerify() {
		
		System.out.println(addEmpText.getText() + "  is the title");
		saveBtn.click();
		Alert alertWdw = driver.switchTo().alert();
		if(alertWdw.getText().equals("Last Name Empty!")){
			alertWdw.accept();
		}
		else return false;
		
		lastNameEditBox.clear();
		lastNameEditBox.sendKeys(prop.getProperty("EmpLastName"));
		saveBtn.click();
		
		if(alertWdw.getText().equals("First Name Empty!")){
			alertWdw.accept();
		}
		else return false;
		
		firstNameEditBox.clear();
		firstNameEditBox.sendKeys(prop.getProperty("EmpFirstName"));
		saveBtn.click();
		
		if(driver.findElement(By.xpath("//h2[contains(text(),'Personal Details')]")).getText().equals("Personal Details")){
			String empCode = driver.findElement(By.xpath("//input[@id='txtEmployeeId']")).getAttribute("value");
			System.out.println("Employee with code "+empCode+ " added");
			driver.switchTo().parentFrame();
			
			Actions action = new Actions(driver);
			action.moveToElement(PIMBtn).build().perform();
			driver.findElement(By.xpath("//span[contains(text(),'Employee List')]")).click();
			driver.switchTo().frame("rightMenu");
			if(TestUtil.verifyEmpAdded(prop.getProperty("EmpLastName"),prop.getProperty("EmpFirstName"),empCode ))
				return true;
		}
		
		return false;
	}

}
