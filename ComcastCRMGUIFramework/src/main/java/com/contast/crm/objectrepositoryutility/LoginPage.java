package com.contast.crm.objectrepositoryutility;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.comcast.crm.generic.webdrieverutility.WebDriverUtility;
/**
 * 
 * @author Harshitha
 * 
 * Contains Login Page elemnts and business library like Login
 * 
 */

public class LoginPage extends WebDriverUtility
{
	WebDriver driver;
	public LoginPage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	//Rule-1 Create a seperate java class
	
	//Rule-2 Object Creation
	
	@FindBy(name="user_name")
	private WebElement userNameEdit;
	
	@FindBy(name="user_password")
	private WebElement userPasswordEdit;
	
	@FindBy(id="submitButton")
	private WebElement loginButton;  //Rule-3 Object intiatization

	
	 //Rule-4 Object Encapsulation
	public WebElement getUserNameEdit() 
	{
		return userNameEdit;
	}

	public WebElement getUserPasswordEdit() 
	{
		return userPasswordEdit;
	}

	public WebElement getLoginButton() 
	{
		return loginButton;
	}	
	/**
	 * login to application based on uesrname,password,url arguments
	 * @param url
	 * @param username
	 * @param password
	 */
	//Rule-5 Provide Action
	public void loginToApp(String url,String username,String password)
	{
		waitForPageLoad(driver);
		driver.get(url);
		driver.manage().window().maximize();
		userNameEdit.sendKeys(username);
		userPasswordEdit.sendKeys(password);
		loginButton.click();
		
	}
	
}

	
