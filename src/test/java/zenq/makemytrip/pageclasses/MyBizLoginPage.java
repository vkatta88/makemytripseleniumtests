package zenq.makemytrip.pageclasses;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import zenq.makemytrip.genericutils.ObjectIdentificationUtils;

public class MyBizLoginPage {
	WebDriver driver;
	Logger logObj;
	ObjectIdentificationUtils objIdentification = new ObjectIdentificationUtils();
	
	public MyBizLoginPage(WebDriver driver, Logger logObj) {
		this.driver= driver;
		this.logObj = logObj;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(how=How.ID, using="name")
	WebElement fldEmailID;
	
	
	public boolean verifyEmailFieldInMyBizLogin() {
		
		try{
			if(objIdentification.waitForWebElement(fldEmailID, 30)) {
				return true;
			}else {
				logObj.error("My Biz Email Id/User Name field not exists");
				return false;
			}
		}catch(Exception ex) {
			logObj.error("Something went wrong while waiting for email id field after click on Signup for Free,  Please check");
			ex.printStackTrace();
			return false;
		}

	}
	

}
