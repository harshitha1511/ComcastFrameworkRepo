package com.contast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationsPage 
{
	WebDriver driver;
	public OrganizationsPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="search_text")
	private WebElement searchEdit;
	
	@FindBy(name="search_field")
	private WebElement searchDropDown;
	
	@FindBy(name="submit")
	private WebElement searchButton;

	public WebElement getSearchEdit() 
	{
		return searchEdit;
	}

	public WebElement getSearchDropDown() 
	{
		return searchDropDown;
	}
	
	public WebElement getSearchButton() {
		return searchButton;
	}

	@FindBy(xpath="//img[@title='Create Organization...']")
	private WebElement CreateNewOrgButton;
	
	public WebElement getCreateNewOrgButton()
	{
		return CreateNewOrgButton;
	}
	
	
}
