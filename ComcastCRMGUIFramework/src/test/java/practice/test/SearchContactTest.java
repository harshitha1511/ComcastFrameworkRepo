package practice.test;

/**
 * 
 * test class for Contact Module
 * @author Harshitha
 * 
 */
import org.testng.annotations.Test;

import com.contast.crm.objectrepositoryutility.LoginPage;
import com.crm.comcast.basetest.BaseClass;
public class SearchContactTest extends BaseClass
{
	@Test
	public void searchContactTest() 
	{
		LoginPage lp=new LoginPage(driver);
		lp.loginToApp("url", "username", "password");
	}
}
