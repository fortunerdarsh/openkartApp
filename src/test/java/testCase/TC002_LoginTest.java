package testCase;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass{
	@Test(groups={"regression","master",})
	public void verify_login() {
		logger.info("********loginTest started*****");
		try {
			
		
	HomePage hp = new HomePage(driver);
	hp.clickMyaccount();
	logger.info("click on myaccountpage");
	hp.clickLogin();
	logger.info("click on loginpage");
	
	LoginPage lp = new LoginPage(driver);
	logger.info("entering credentilas");
	lp.setEmailforLogin(p.getProperty("email"));
	lp.setpwdforLogin(p.getProperty("password"));
	logger.info("enterd");
	lp.clickloginbthn();
	logger.info("validateing");
	
	
	}
		catch(Exception e)
		{
			e.getMessage();
		}
}
}
