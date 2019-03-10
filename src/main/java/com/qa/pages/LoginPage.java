package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.base.TestBase;
import com.qa.util.TestUtil;

public class LoginPage extends TestBase{
	
	//Page Factory - OR:
	@FindBy(xpath="//input[@name='txtUserName']")
	WebElement username;
	
	@FindBy(name="txtPassword")
	WebElement password;
	
	@FindBy(xpath="//input[@value='Login']")
	WebElement loginBtn;
	
	@FindBy(xpath="//input[@value='Clear']")
	WebElement clearBtn;

	
	//Initializing the Page Objects:
	public LoginPage(){
		PageFactory.initElements(driver, this);
	}
	
	//Actions:
	public String validateLoginPageTitle(){
		return driver.getTitle();
	}
	

	public HomePage login(String un, String pwd) throws InterruptedException{
		username.sendKeys( prop.getProperty("username"));
		password.sendKeys( prop.getProperty("password"));
		loginBtn.click();
		
		return new HomePage();
	}

	public boolean verifyElementsPresent() {
		
		if(TestUtil.elementPresent(username) &&  TestUtil.elementPresent(password) && TestUtil.elementPresent(loginBtn)
				&& TestUtil.elementPresent(clearBtn)){
			return true;
		}
		return false;
		
	}

}
