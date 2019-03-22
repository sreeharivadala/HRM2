package com.qa.testscripts;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;

import com.qa.pages.AddLocationPage;
import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;

public class AddLocationPageTest extends TestBase{
	
	LoginPage loginPage;
	HomePage homePage;
	AddLocationPage addLocationPage;
	
	public AddLocationPageTest(){
		super();
	}
	
	@BeforeMethod
	public void setUp() throws InterruptedException{
		initialization();
		loginPage = new LoginPage();	
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		addLocationPage = homePage.clickOnAddLocation();
		driver.switchTo().frame("rightMenu");
	}
	
/*	@Test(priority=1)
	public void addLocPageTitleTest(){
		String title = addLocationPage.validateLocPageTitle();
		Assert.assertEquals(title, "Company Info : Locations","Company Info : Locations Title is not displayed");
	}
	
	*/		@Test(priority=2)
	public void addLocationAndVerifyTest(){
		
		Assert.assertTrue(addLocationPage.addLocationAndVerify(),"Add Location Page Test Failed");
		driver.switchTo().parentFrame();
	} 
/*	@Test(priority=3)
	public void logoutTest(){
		driver.switchTo().parentFrame();
		homePage.logout();
		Assert.assertEquals(loginPage.validateLoginPageTitle(), "OrangeHRM - New Level of HR Management", "Login Page Title is not displayed");
	}
	
	*/
	
	@AfterMethod
	public void tearDown(){
		
		loginPage = null;
		homePage = null;
	//	driver.close();
	//	driver.quit();
	}

}
