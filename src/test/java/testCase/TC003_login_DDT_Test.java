package testCase;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

public class TC003_login_DDT_Test extends BaseClass{
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class)

	public void verify_loginDDT(String email, String password, String exp ) {
		logger.info("*****TC003LoginDDtTest Started*****");
		try {
			logger.info("click on homepage");
		HomePage hp = new HomePage(driver);
		hp.clickMyaccount();
		hp.clickLogin();
		logger.info("clicked login button");
		
		LoginPage lp = new LoginPage(driver);
		logger.info("entering credentials");
		lp.setEmailforLogin(email);
		lp.setpwdforLogin(password);
		lp.clickloginbthn();
		
		logger.info("its in myaccountpage validation");
		MyAccountPage macc= new MyAccountPage(driver);
		boolean targetpage = macc.myAccountpageExist();
		
		if(exp.equalsIgnoreCase("valid"))
		{
			if(targetpage==true)
			{
				macc.clicklogout();
				Assert.assertTrue(true);
			}
			else 
			{
				Assert.assertTrue(false);
			}
		}
		if(exp.equalsIgnoreCase("Invalid"))
		{
			if(targetpage==true)
			{
				macc.clicklogout();
				Assert.assertTrue(false);
			}
			else
			{
				Assert.assertTrue(true);
			}
		}
		
	}
		
		catch(Exception e)
		{
			Assert.fail(e.getMessage());
		}
		logger.info("completed");
		

}
}
