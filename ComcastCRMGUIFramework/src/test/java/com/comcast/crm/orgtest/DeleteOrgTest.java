package com.comcast.crm.orgtest;

import java.io.IOException;
import java.time.Duration;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.comcast.crm.generic.fileutility.ExcelUtility;
import com.comcast.crm.generic.fileutility.FileUtility;
import com.comcast.crm.generic.webdrieverutility.JavaUtility;
import com.comcast.crm.generic.webdrieverutility.WebDriverUtility;
import com.contast.crm.objectrepositoryutility.CreateNewOrganizationsPage;
import com.contast.crm.objectrepositoryutility.HomePage;
import com.contast.crm.objectrepositoryutility.LoginPage;
import com.contast.crm.objectrepositoryutility.OrganizationsInfoPage;
import com.contast.crm.objectrepositoryutility.OrganizationsPage;

public class DeleteOrgTest {

	public static void main(String[] args) throws Throwable, IOException, ParseException 
	{
		    FileUtility flib=new FileUtility();
	        ExcelUtility elib=new ExcelUtility();
	        JavaUtility jlib=new JavaUtility();
	        WebDriverUtility wlib=new WebDriverUtility();
	  
	        //read the data from properties file
	        
			
			String BROWSER=flib.getDataFromPropertiesFiles("browser");
			String URL=flib.getDataFromPropertiesFiles("url");
			String USERNAME=flib.getDataFromPropertiesFiles("username");
			String PASSWORD=flib.getDataFromPropertiesFiles("password");

			    
			//Read testScript data from Excel File
			String orgName=elib.getDataFromExcel("org",10,2) + jlib.getRandomNumber();
		   
			    
//			    System.out.println(BROWSER);
//			    System.out.println(URL);
				
//			    Scanner s=new Scanner(System.in);
//			    System.out.println("enter the Browser");
//			    
//			    String browser = s.next();
			    
			    // read testScript data from Excel file
			    
//			    
			    WebDriver driver=null;
			   
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
			    //step 1 : login to application
			    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				driver.get(URL);
				
				LoginPage lp=new LoginPage(driver);
				lp.loginToApp("url","admin", "admin");
				
				// step 2 : navigate to Organization module
				HomePage hp=new HomePage(driver);
				hp.getOrgLink().click();
		
				// step 3 : click on "create Organization" Button
				OrganizationsPage op=new OrganizationsPage(driver);
				op.getCreateNewOrgButton().click();
				
				driver.findElement(By.xpath("//textarea[@name='ship_street']")).sendKeys("a");
				
				// step 4 : enter all the details and create new Organization
				CreateNewOrganizationsPage cnop=new CreateNewOrganizationsPage(driver);
				cnop.createOrg(orgName);
				
				//verify Header msg Expected Result
				OrganizationsInfoPage oip=new OrganizationsInfoPage(driver);
				String actOrgName= oip.getHeaderMsg().getText();
				if(actOrgName.contains(orgName))
				{
					System.out.println(orgName + " name is verified ==PASS");
				}
				
				else
				{
					System.out.println(orgName + " name is not verified ==FAIL");
				}
				
				//go back to Organiations Page
				hp.getOrgLink().click();
	
				//search for Organization
				op.getSearchEdit().sendKeys(orgName);
				//wlib.select(op.getSearchDropDown(), "Organization Name");
				wlib.selectByVisibleText(op.getSearchDropDown(), "Organization Name");


				op.getSearchButton().click();
				
				
				//In dynamic web table select and delete org
				driver.findElement(By.xpath("//a[text()='"+orgName+"']/../../td[8]/a[text()='del']")).click();
				
				driver.switchTo().alert().accept();
				
				// step 5 : logout
				hp.logOut();
				driver.quit();
	}

}
