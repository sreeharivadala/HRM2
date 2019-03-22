package com.qa.pages;

import java.sql.Timestamp;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.qa.base.TestBase;
import com.qa.util.TestUtil;

public class AddLocationPage extends TestBase {

	
	@FindBy(xpath="//h2[contains(text(),'Company Info : Locations')]")
	WebElement compInfoText;
	
	@FindBy(xpath="//input[@value='Search']")
	WebElement searchBtn;
	
	@FindBy(xpath="//select[@id='loc_code']")
	WebElement searchMenu;
	
	@FindBy(xpath="//input[@value='Add']")
	WebElement addBtn;
	
	@FindBy(xpath="//input[@value='Delete']")
	WebElement delBtn;
	
	@FindBy(xpath="//input[@id='editBtn']")
	WebElement saveBtn;
	
	@FindBy(xpath="//input[@id='txtLocDescription']")
	WebElement nameBox;
	
	@FindBy(xpath="//select[@id='cmbCountry']") 
	WebElement cntryBox;
	
	@FindBy(xpath="//textarea[@id='txtAddress']")
	WebElement addrBox;
	
	@FindBy(xpath="//input[@id='txtZIP']")
	WebElement zipBox;
	
	String sName;
	
	//Initializing the Page Objects:
			public AddLocationPage(){
				PageFactory.initElements(driver, this);
			}
	

		
	public String validateLocPageTitle() {
		
		return compInfoText.getText();
			
	}

	

public boolean addLocationAndVerify() {
	
		Assert.assertTrue(validateLocElements(),"Location page elements not found");
		
		addBtn.click();
		System.out.println(compInfoText.getText() + "  is the title");

		saveBtn.click();
		Alert alertWdw1 = driver.switchTo().alert();
		if(alertWdw1.getText().contains("- Location Name has to be specified")){
			alertWdw1.accept();
		}
		
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		sName="Test QA "+ts;
		
		
		nameBox.clear();
		nameBox.sendKeys(sName);
		saveBtn.click();
		Alert alertWdw2 = driver.switchTo().alert();
		if(alertWdw2.getText().contains("- Country should be selected!")){
			alertWdw2.accept();
		}
		
		
		String sCNT="India";
		
		Select cntSelect = new Select(cntryBox);
		List<WebElement> cntOptions = cntSelect.getOptions();
		for(int i=1; i<=cntOptions.size(); i++){
			String scntOP = cntOptions.get(i).getText();
			if(scntOP.equals(sCNT)){
				cntSelect.selectByVisibleText(sCNT);
				System.out.println("Country Name "+ scntOP +" is Selected");
				break;
			}
			
		}
	
		saveBtn.click();
		Alert alertWdw3 = driver.switchTo().alert();
		if(alertWdw3.getText().contains("- Address should be specified")){
			alertWdw3.accept();
		}
		addrBox.clear();
		addrBox.sendKeys("Hyderabad");
		
		saveBtn.click();
		Alert alertWdw4 = driver.switchTo().alert();
		if(alertWdw4.getText().contains("- Zip Code should be specified")){
			alertWdw4.accept();
		}
		
		zipBox.clear();
		zipBox.sendKeys("500001");
		
		saveBtn.click();
		
		if(driver.findElement(By.xpath("//h2[contains(text(),'Company Info : Locations')]")).getText().equals("Company Info : Locations")){
			if(TestUtil.verifyLocAdded(sName ))
				return true;
		}
		
		return false;
	}



private boolean validateLocElements() {
	if(compInfoText.getText() .equals("Company Info : Locations") && searchMenu.isDisplayed() && addBtn.isDisplayed() && searchBtn.isDisplayed()
			&& delBtn.isDisplayed())
		return true;
	return false;
}


}
