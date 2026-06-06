package testCase;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegisterPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegisterationPage extends BaseClass {

    @Test(groups={"sanity","master",})
    public void verify_AccoutRegsterationPage() {
        logger.info("********TC001 accountRegsteration started*******");
        try {
            logger.info("its in HomePage");
            HomePage hp = new HomePage(driver);
            hp.clickMyaccount();
            hp.clickregister();

            logger.info("its in accountregpagePage");
            AccountRegisterPage arp = new AccountRegisterPage(driver);

            logger.info("entering all fields");
            arp.setFname(randomeString().toUpperCase());
            arp.setLname(randomeString().toUpperCase());
            arp.setEmail(randomeString() + "@gmail.com");
            arp.settelephnno(randomeNum());
            String pwd = randomeAlphaNum();
            arp.setPwd(pwd);
            arp.setCnmpwd(pwd);
            arp.clkchkbox();
            arp.clkContinue();

            logger.info("its validating msg");
            String msgdisplyed = arp.msgConfirmation();
            Assert.assertTrue(msgdisplyed.contains("Your Account Has Been Created"), "Confirmation message mismatch");

            logger.info("test passed");
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }
}
