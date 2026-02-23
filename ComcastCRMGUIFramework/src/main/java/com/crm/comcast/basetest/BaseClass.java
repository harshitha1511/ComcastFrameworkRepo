package com.crm.comcast.basetest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdrieverutility.JavaUtility;
import com.comcast.crm.generic.webdrieverutility.WebDriverUtility;
import com.contast.crm.objectrepositoryutility.HomePage;
import com.contast.crm.objectrepositoryutility.LoginPage;

public class BaseClass 
{
	
	public FileUtility flib=new FileUtility();
	public ExcelUtility elib=new ExcelUtility();
    public JavaUtility jlib=new JavaUtility();
    public WebDriverUtility wlib=new WebDriverUtility();

	
	public WebDriver driver=null;
	public static WebDriver sdriver=null;
	
	@BeforeSuite
	public void configBS()
	{
		System.out.println("==connect to db, Report config==");
	}
	
	@BeforeClass
	public void configBC() throws Throwable
	{
		System.out.println("==launch the browser==");
		String BROWSER=flib.getDataFromPropertiesFiles("browser");
		   
		    //polymorphism
		    if(BROWSER.equals("chrome"))
		    {
		    	driver=new ChromeDriver();
		    }
		    else if(BROWSER.equals("fireFox"))
		    {
		    	driver=new FirefoxDriver();
		    }
		    
		    else if(BROWSER.equals("edge"))
		    {
		    	driver=new EdgeDriver();
		    }
		    
		    else
		    {
		    	driver=new ChromeDriver();
		    }
		    sdriver=driver;
	}
	
	@BeforeMethod
	public void configBM() throws Throwable
	{
		System.out.println("login");
		String URL=flib.getDataFromPropertiesFiles("url");
		String USERNAME=flib.getDataFromPropertiesFiles("username");
		String PASSWORD=flib.getDataFromPropertiesFiles("password");
		LoginPage lp=new LoginPage(driver);
		lp.loginToApp(URL, USERNAME, PASSWORD);
	}
	
	@AfterMethod
	public void configAM()
	{
		System.out.println("==logout==");
		HomePage hp=new HomePage(driver);
		hp.logOut();
	}
	
	@AfterClass
	public void configAC()
	{
		System.out.println("==close the browser==");
		driver.quit();
	}
	
	@AfterSuite
	public void configAS()
	{
		System.out.println("==close db, Report backup==");
	}
	
	
}