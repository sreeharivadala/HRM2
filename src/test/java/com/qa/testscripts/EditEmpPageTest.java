package com.qa.testscripts;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.pages.AddEmpPage;
import com.qa.pages.EditEmpPage;
import com.qa.pages.HomePage;
import com.qa.pages.LoginPage;

public class EditEmpPageTest extends TestBase {
	LoginPage loginPage;
	HomePage homePage;
	EditEmpPage editEmpPage;
	
	public EditEmpPageTest(){
		super();
	}
	
	@BeforeMethod
	public void setUp() throws InterruptedException{
		initialization();
		loginPage = new LoginPage();	
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
		driver.switchTo().frame("rightMenu");
		editEmpPage = homePage.editEmp();
		
	}
	
/*	@Test(priority=1)
	public void addEmpPageTitleTest(){
		String title = addEmpPage.validateEmpPageTitle();
		Assert.assertEquals(title, "PIM : Add Employee","Add Emp Page Title is not displayed");
	}
*/	
		@Test(priority=2)
	public void editEmployeeAndVerifyTest() throws InterruptedException{
		
		Assert.assertTrue(editEmpPage.editEmployeeAndVerify(),"Edit Emp Page Test Failed");
		
	} 
	@Test(priority=3)
	public void logoutTest(){
		driver.switchTo().parentFrame();
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
