package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegisterPage extends BasePage{

	public AccountRegisterPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//input[@id='input-firstname']") WebElement txt_fname;
	@FindBy(xpath="//input[@id='input-lastname']") WebElement txt_lname;
	@FindBy(xpath="//input[@id='input-email']") WebElement txt_email;
	@FindBy(xpath="//input[@id='input-telephone']") WebElement txt_telphn;
	@FindBy(xpath="//input[@id='input-password']") WebElement txt_pwd;
	@FindBy(xpath="//input[@id='input-confirm']") WebElement txt_cnfrmpwd;
	@FindBy(xpath="//input[@name='agree']") WebElement chkbox;
	@FindBy(xpath="//input[@value='Continue']") WebElement btn_continue;
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']") WebElement msgdisplay;
	
	
	public void setFname(String fname)
	{
		txt_fname.sendKeys(fname);
	}
	public void setLname(String lname)
	{
		txt_lname.sendKeys(lname);
	}
	public void setEmail(String email)
	{
		txt_email.sendKeys(email);
	}
	public void settelephnno(String telphnno)
	{
		txt_telphn.sendKeys(telphnno);
	}
	public void setPwd(String pwd)
	{
		txt_pwd.sendKeys(pwd);
	}
	public void setCnmpwd(String pwd)
	{
		txt_cnfrmpwd.sendKeys(pwd);
	}
	public void clkchkbox()
	{
		chkbox.click();
	}
	public void clkContinue()
	{
		btn_continue.click();
	}
	public  String msgConfirmation()
	{
		try {
			return(msgdisplay.getText());
		}
		catch(Exception e)
		{
			return(e.getMessage());
		}
		
	}

}
