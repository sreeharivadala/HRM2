package com.qa.testscripts;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;

public class HomePageTest extends TestBase {
	
	LoginPage loginPage;
	HomePage homePage;
	
	public HomePageTest(){
		super();
	}
	
	@BeforeMethod
	public void setUp() throws InterruptedException{
		initialization();
		loginPage = new LoginPage();	
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
/*	@Test(priority=1)
	public void homePageTitleTest(){
		String title = homePage.validateHomePageTitle();
		Assert.assertEquals(title, "OrangeHRM","Home Page Title is not displayed");
	}
	*/
	@Test(priority=2)
	public void homePageElementsPresentTest(){
		
		Assert.assertTrue(homePage.verifyElementsPresent(),"Home Page Elements missing");
	} 
	@Test(priority=3)
	public void logoutTest(){
		homePage.logout();
		Assert.assertEquals(loginPage.validateLoginPageTitle(), "OrangeHRM - New Level of HR Management", "Login Page Title is not displayed");
	}
	
	
	
	@AfterMethod
	public void tearDown(){
		
		loginPage = null;
		homePage = null;
		driver.close();
		driver.quit();
	}
}
