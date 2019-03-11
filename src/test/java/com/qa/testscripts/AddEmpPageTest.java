package com.qa.testscripts;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.pages.AddEmpPage;
import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;

public class AddEmpPageTest extends TestBase{
	
	LoginPage loginPage;
	HomePage homePage;
	AddEmpPage addEmpPage;
	
	public AddEmpPageTest(){
		super();
	}
	
	@BeforeMethod
	public void setUp() throws InterruptedException{
		initialization();
		loginPage = new LoginPage();	
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		addEmpPage = homePage.clickOnAddEmp();
		driver.switchTo().frame("rightMenu");
	}
	
/*	@Test(priority=1)
	public void addEmpPageTitleTest(){
		String title = addEmpPage.validateEmpPageTitle();
		Assert.assertEquals(title, "PIM : Add Employee","Add Emp Page Title is not displayed");
	}
*/	
		@Test(priority=2)
	public void addEmployeeAndVerifyTest(){
		
		Assert.assertTrue(addEmpPage.addEmployeeAndVerify(),"Add Emp Page Test Failed");
		driver.switchTo().parentFrame();
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
	//	driver.close();
	//	driver.quit();
	}

}
