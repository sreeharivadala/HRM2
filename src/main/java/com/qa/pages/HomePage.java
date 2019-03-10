package com.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
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

}
