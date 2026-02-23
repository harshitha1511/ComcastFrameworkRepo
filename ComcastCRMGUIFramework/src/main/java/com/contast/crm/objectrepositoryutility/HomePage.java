package com.contast.crm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage 
{
	WebDriver driver;
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText ="Organizations")
	private WebElement orgLink;
	
	@FindBy(linkText ="Contacts")
	private WebElement contactLink;
	
	@FindBy(linkText ="Campaigns")
	private WebElement campaignLink;
	
	@FindBy(linkText ="More")
	private WebElement moreLink;
	
	@FindBy(xpath ="//img[contains(@src,'user')]")
	private WebElement adminImg;
	
	@FindBy(linkText ="Sign Out")
	private WebElement signOutLink;
	
	public WebElement getOrgLink() 
	{
		return orgLink;
	}

	public WebElement getContactLink() 
    {
		return contactLink;
	}
	
	public WebElement getMoreLink() 
	{
		return moreLink;
	}

	public WebElement getCampaignLink() 
	{
		return campaignLink;
	}

	public void navigateToCampaign()
	{
		Actions action = new Actions(driver);
		action.moveToElement(moreLink);
		campaignLink.click();
	}
	
	public void logOut()
	{
		Actions action=new Actions(driver);
		action.moveToElement(adminImg).perform();
		signOutLink.click();
	}
}
