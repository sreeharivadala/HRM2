package com.qa.pages;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.TestBase;
import com.qa.util.TestUtil;

public class HomePage extends TestBase{

	//Page Factory - OR:
	
		
		@FindBy(xpath="//a[contains(text(),'Change Password')]")
		WebElement changepasswordBtn;
		
		@FindBy(xpath="//ul[@id='option-menu']/li[1]")
		WebElement welcomeStringBox;
		
		@FindBy(linkText="Logout")
		WebElement logoutBtn;
		
		
		@FindBy(xpath="//li[@id='pim']/a")
		WebElement PIMBtn;
		
		//Initializing the Page Objects:
		public HomePage(){
			PageFactory.initElements(driver, this);
		}
		
	
	public boolean verifyElementsPresent() {
		if(TestUtil.elementPresent(logoutBtn) &&  TestUtil.elementPresent(changepasswordBtn) && TestUtil.elementPresent(welcomeStringBox)
				){
			return true;
		}
		return false;
	}

	public String validateHomePageTitle() {
		return driver.getTitle();
	}


	public void logout() {
		
		logoutBtn.click();
	}


	public AddEmpPage clickOnAddEmp() {
		
		Actions action = new Actions(driver);
		action.moveToElement(PIMBtn).build().perform();
				
	//	List<WebElement> PIMList = driver.findElements(By.xpath("////li[@id='pim']/ul/li"));
		
		driver.findElement(By.xpath("//span[contains(text(),'Add Employee')]")).click();
		return new AddEmpPage();
	}

}
