package com.qa.testscripts;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import com.qa.base.TestBase;
import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;

public class LoginPageTest extends TestBase {

	LoginPage loginPage;
	HomePage homePage;
	
	public LoginPageTest(){
		super();
	}
	
	@BeforeMethod
	public void setUp(){
		initialization();
		loginPage = new LoginPage();	
	}
	
	@Test(priority=1)
	public void loginPageTitleTest(){
		String title = loginPage.validateLoginPageTitle();
		Assert.assertEquals(title, "OrangeHRM - New Level of HR Management");
	}
	
	@Test(priority=2)
	public void loginPageElementsPresentTest(){
		
		Assert.assertTrue(loginPage.verifyElementsPresent(), "Elements missing in LoginPage");
	}
	@Test(priority=3)
	public void loginTest() throws InterruptedException{
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(homePage.validateHomePageTitle(), "OrangeHRM","Home Page Title is not displayed");
	}
	
	
	
	@AfterMethod
	public void tearDown(){
		
		loginPage = null;
		homePage = null;
		driver.close();
		driver.quit();
	}
	
	
}
