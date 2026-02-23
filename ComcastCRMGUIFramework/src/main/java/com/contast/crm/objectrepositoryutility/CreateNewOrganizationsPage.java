package com.contast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreateNewOrganizationsPage 
{
	WebDriver driver;
	public CreateNewOrganizationsPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="accountname")
	private WebElement orgNameEdit;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement saveButton;
	
	@FindBy(name="industry")
	private WebElement industryDropdown;

	public WebElement getOrgNameEdit() {
		return orgNameEdit;
	}

	public WebElement getSaveButton() {
		return saveButton;
	}
	
	public void createOrg(String orgName)
	{
		orgNameEdit.sendKeys(orgName);
		saveButton.click();
	}
	
	public void createOrg(String orgName,String industry)
	{
		orgNameEdit.sendKeys(orgName);
		Select sel=new Select(industryDropdown);
		sel.selectByVisibleText(industry);
		saveButton.click();
	}
	
	
}
