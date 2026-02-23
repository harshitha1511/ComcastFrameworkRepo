package practice.test;

import java.io.IOException;
import java.time.Duration;

import org.testng.annotations.*;
import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;

import com.comcast.crm.generic.fileutility.ExcelUtility;
public class GetProducrInfoTest
{
	@Test(dataProvider= "getData")
	public void getProductInfoTest(String brandName,String productName) throws InterruptedException
	{
		WebDriver driver=new ChromeDriver();
		driver.get("https://demowebshop.tricentis.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		//search product
		driver.findElement(By.partialLinkText("Books")).click();
		
		
		//capture product info
		String price=driver.findElement(By.xpath("//a[text()='"+productName+"']/ancestor::div[@class='product-item']/descendant::div[8]/span[2]")).getText();
		System.out.println(price);
		
		driver.quit();
	}
	@DataProvider()
		public Object[][] getData() throws Throwable, IOException
		{
		    ExcelUtility elib=new ExcelUtility();
		    int rowCount=elib.getRowCount("product");
			Object[][] obj=new Object[rowCount][2];
			
			for(int i=0;i<rowCount;i++)
			{
				obj[i][0]=elib.getDataFromExcel("product", i+1, 0);
				obj[i][1]=elib.getDataFromExcel("product", i+1, 1);
			}			
			
			return obj;
			
		}
		
	

		
	}

