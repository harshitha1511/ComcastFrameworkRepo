package com.comcast.crm.contacttest;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.contast.crm.objectrepositoryutility.HomePage;
import com.crm.comcast.basetest.BaseClass;

/**
 * 
 * @author Harshitha 
 * 
 */
public class CreateContactTest extends BaseClass 
{
	
	@Test(groups = "smokeTest")
	public void createContactTest() throws Throwable {
		// Generate random last name
		String lastName = elib.getDataFromExcel("contact", 1, 2) + jlib.getRandomNumber();

		// Step 1: Navigate to Contacts
		driver.findElement(By.linkText("Contacts")).click();

		// Step 2: Click on Create Contact
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

		// Step 3: Enter Last Name
		driver.findElement(By.name("lastname")).sendKeys(lastName);

		// Step 4: Click Save
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// Step 5: Verification
		String actualLastName = driver.findElement(By.id("dtlview_Last Name")).getText();

		
	}

	@Test(groups = "regressionTest")
	public void createContactWithSupportDateTest() throws IOException, Throwable {
		// Read testScript data from Excel File
		String lastName = elib.getDataFromExcel("contact", 4, 2) + jlib.getRandomNumber();

		// step 2 : navigate to Contact module
		HomePage hp = new HomePage(driver);
		hp.getContactLink().click();

		// step 3 : click on "create Contact" Button
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

		// step 4 : enter all the details and create new Organization

		String startDate = jlib.getSystemDateYYYYDDMM();
		String endDate = jlib.getRequiredDataYYYYDDMM(30);

		driver.findElement(By.name("lastname")).sendKeys(lastName);

		driver.findElement(By.name("support_start_date")).clear();
		driver.findElement(By.name("support_start_date")).sendKeys(startDate);

		driver.findElement(By.name("support_end_date")).clear();
		driver.findElement(By.name("support_end_date")).sendKeys(endDate);

		// driver.findElement(By.xpath("//textarea[@name='ship_street']")).sendKeys("a");
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// verify Header phone number into Expected Result
		String actualLastName = driver.findElement(By.id("dtlview_Last Name")).getText();
		if (actualLastName.equals(lastName)) {
			System.out.println(lastName + " is created==PASS");
		} else {
			System.out.println(lastName + " is not created==FAIL");
		}
	}

	@Test(groups = "regressionTest")
	public void createContactWithOrgTest() throws IOException, Throwable {
		// Read testScript data from Excel File
		String orgName = elib.getDataFromExcel("contact", 7, 2) + jlib.getRandomNumber();
		String contactLastName = elib.getDataFromExcel("contact", 7, 2) + jlib.getRandomNumber();

		// step 2 : navigate to Organization module
		driver.findElement(By.linkText("Organizations")).click();

		// step 3 : click on "create Organization" Button
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();

		// step 4 : enter all the details and create new Contact
		driver.findElement(By.name("accountname")).sendKeys(orgName);
		driver.findElement(By.xpath("//textarea[@name='ship_street']")).sendKeys("a");
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// verify Header msg Expected Result
		String headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (headerInfo.contains(orgName)) {
			System.out.println(orgName + "is created==PASS");
		} else {
			System.out.println(orgName + "is not created==FAIL");
		}

		// step 5 : navigate to contact module
		driver.findElement(By.linkText("Contacts")).click();

		// step 6 : click on "create Organization" Button
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

		// step 7 : enter all the details and create new Organization
		driver.findElement(By.name("lastname")).sendKeys(contactLastName);
		driver.findElement(By.xpath("//input[@name='account_name']/following-sibling::img")).click();

		// switch to child window
		wlib.toSwitchToTabOnUrl(driver, "module=Accounts");

		// search org in child window
		driver.findElement(By.name("search_text")).sendKeys(orgName);
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[text()='" + orgName + "']")).click();

		// switch back to parent window
		wlib.toSwitchToTabOnUrl(driver, "Contacts&action");

		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// verify Header msg Expected Result
		headerInfo = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (headerInfo.contains(contactLastName)) {
			System.out.println(contactLastName + "is created==PASS");
		} else {
			System.out.println(contactLastName + "is not created==FAIL");
		}

		// verify Header orgName into Expected Result
		String actualOrgName = driver.findElement(By.id("mouseArea_Organization Name")).getText();
		System.out.println(actualOrgName);
		if (actualOrgName.trim().equals(orgName)) {
			System.out.println(orgName + "is created==PASS");
		} else {
			System.out.println(orgName + "is not created==FAIL");
		}

	}
}
