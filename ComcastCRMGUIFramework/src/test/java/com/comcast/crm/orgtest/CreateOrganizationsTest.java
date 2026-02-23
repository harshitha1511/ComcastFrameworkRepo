package com.comcast.crm.orgtest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.contast.crm.objectrepositoryutility.CreateNewOrganizationsPage;
import com.contast.crm.objectrepositoryutility.HomePage;
import com.contast.crm.objectrepositoryutility.OrganizationsInfoPage;
import com.contast.crm.objectrepositoryutility.OrganizationsPage;
import com.crm.comcast.basetest.BaseClass;

@Listeners(com.comcast.crm.listenerutility.ListImpClass.class)
public class CreateOrganizationsTest extends BaseClass {

    @Test(groups = "smokeTest")
    public void createOrganizationTest() throws Throwable {

        String orgName = elib.getDataFromExcel("org", 1, 2) + jlib.getRandomNumber();

        HomePage hp = new HomePage(driver);
        hp.getOrgLink().click();

        OrganizationsPage op = new OrganizationsPage(driver);
        op.getCreateNewOrgButton().click();

        driver.findElement(By.xpath("//textarea[@name='ship_street']")).sendKeys("Test Address");

        CreateNewOrganizationsPage cnop = new CreateNewOrganizationsPage(driver);
        cnop.createOrg(orgName);

        OrganizationsInfoPage oip = new OrganizationsInfoPage(driver);
        String actOrgName = oip.getHeaderMsg().getText();

        if (actOrgName.contains(orgName)) {
            System.out.println(orgName + " name is verified == PASS");
        } else {
            System.out.println(orgName + " name is not verified == FAIL");
        }
    }

    @Test(groups = "regressionTest")
    public void createOrganizationsWithPhoneNumber() throws Throwable {

        String orgName = elib.getDataFromExcel("or", 4, 2) + jlib.getRandomNumber();
        String phoneNumber = "9876543210";

        driver.findElement(By.linkText("Organizations")).click();
        driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();

        driver.findElement(By.name("accountname")).sendKeys(orgName);
        driver.findElement(By.id("phone")).sendKeys(phoneNumber);
        driver.findElement(By.xpath("//textarea[@name='ship_street']")).sendKeys("Address");
        driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

        String actualPhNo = driver.findElement(By.id("dtlview_Phone")).getText();

        if (actualPhNo.equals(phoneNumber)) {
            System.out.println(phoneNumber + " is verified == PASS");
        } else {
            System.out.println(phoneNumber + " is not verified == FAIL");
        }
    }

    @Test(groups = "regressionTest")
    public void createOrganizationWithIndustries() throws Throwable {

        String orgName = elib.getDataFromExcel("org", 7, 2) + jlib.getRandomNumber();
        String industry = "Banking";
        String type = "Customer";

        driver.findElement(By.linkText("Organizations")).click();
        driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();

        driver.findElement(By.name("accountname")).sendKeys(orgName);

        WebElement industryDropdown = driver.findElement(By.name("industry"));
        Select selectIndustry = new Select(industryDropdown);
        selectIndustry.selectByVisibleText(industry);

        WebElement typeDropdown = driver.findElement(By.name("accounttype"));
        Select selectType = new Select(typeDropdown);
        selectType.selectByVisibleText(type);

        driver.findElement(By.xpath("//textarea[@name='ship_street']")).sendKeys("Address");
        driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

        String actualIndustry = driver.findElement(By.id("dtlview_Industry")).getText();
        if (actualIndustry.equals(industry)) {
            System.out.println(industry + " is verified == PASS");
        } else {
            System.out.println(industry + " is not verified == FAIL");
        }

        String actualType = driver.findElement(By.id("dtlview_Type")).getText();
        if (actualType.equals(type)) {
            System.out.println(type + " is verified == PASS");
        } else {
            System.out.println(type + " is not verified == FAIL");
        }
    }
}
