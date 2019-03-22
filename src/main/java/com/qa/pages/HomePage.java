package com.qa.pages;


import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

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
		
		@FindBy(xpath="//li[@id='admin']/a")
		WebElement AdminBtn;
		
		@FindBy(xpath="//li[@id='admin']/ul/li[1]/a/span")
		WebElement CompInfoBtn;
		
		@FindBy(xpath="//li[@id='admin']/ul/li")
		List<WebElement> AdminList;
		
		@FindBy(xpath="//li[@id='admin']/ul/li[1]/ul/li")
		List<WebElement> CompInfoMenuList;
		
		
		@FindBy(xpath="//li[@id='admin']/ul/li[1]/ul/li[2]/a/span")
		WebElement LocBtn;
		
		Actions action = null;
		
		//Initializing the Page Objects:
		public HomePage(){
			PageFactory.initElements(driver, this);
			action = new Actions(driver);
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
		
		
		action.moveToElement(PIMBtn).build().perform();
				
	//	List<WebElement> PIMList = driver.findElements(By.xpath("////li[@id='pim']/ul/li"));
		
		driver.findElement(By.xpath("//span[contains(text(),'Add Employee')]")).click();
		return new AddEmpPage();
	}


	public EditEmpPage editEmp() {
		
		return new EditEmpPage();
	}


	public DeleteEmpPage delEmp() {
		
		return new DeleteEmpPage();
	}


	public AddLocationPage clickOnAddLocation() {
		
			
		Assert.assertTrue(verifyAdminMenuList(),"Admin Menu List is not correct");
		Assert.assertTrue(verifyCompInfoMenuList(),"Company Info Menu List is not correct");
		LocBtn.click();
		
		return new AddLocationPage();
	}


	private boolean verifyCompInfoMenuList() {
		
		action.moveToElement(CompInfoBtn).build().perform();
		List<String> CompMenuListString = new ArrayList<String>();	
		for(int i=1; i<= CompInfoMenuList.size();i++){
			CompMenuListString.add(driver.findElement(By.xpath("//li[@id='admin']/ul/li[1]/ul/li["+i+"]/a/span")).getText());
			System.out.println("Company Info Menu List "+ i + " " + CompMenuListString.get(i-1));
		}
			
		
		if(CompMenuListString.get(0).equals("General") && CompMenuListString.get(1).equals("Locations") && CompMenuListString.get(2).equals("Company Structure") )
			return true;
				
		return false;
	}


	private boolean verifyAdminMenuList() {
		
		action.moveToElement(AdminBtn).build().perform();
	
		List<String> AdminListString = new ArrayList<String>();	
		for(int i=1; i<= AdminList.size();i++){
			AdminListString.add(driver.findElement(By.xpath("//li[@id='admin']/ul/li["+i+"]/a/span")).getText());
			System.out.println("Admin Menu List "+ i + " " + AdminListString.get(i-1));
		}
			
		
		if(AdminListString.get(0).equals("Company Info") && AdminListString.get(1).equals("Job") && AdminListString.get(2).equals("Qualification") )
			return true;
		
		return false;
	}

}
