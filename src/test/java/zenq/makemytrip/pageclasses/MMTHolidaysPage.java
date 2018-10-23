package zenq.makemytrip.pageclasses;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import zenq.makemytrip.genericutils.ObjectIdentificationUtils;

public class MMTHolidaysPage {
	
	WebDriver driver;
	Logger logObj;
	ObjectIdentificationUtils objIdentification = new ObjectIdentificationUtils();
	
	public MMTHolidaysPage(WebDriver driver, Logger logObj) {
		this.driver= driver;
		this.logObj = logObj;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how=How.XPATH, using="//a[text()='Build your own package']")
	WebElement btnBuildYourOwnPkg;
	
	
	public boolean verifyBuildYourOwnPkgBtn() {
		try{
			if(objIdentification.waitForWebElement(btnBuildYourOwnPkg, 10)) {
				return true;
			}else {
				logObj.error("Build Your Own Package button is not available");
				return false;
			}
		}catch(Exception ex) {
			logObj.error("Unable to wait for Build Your Own Package button, Please check");
			ex.printStackTrace();
			return false;
		}
	}
	
	
	public boolean clickOnBuildYourOwnPkgBtn() {
		try{
			if(objIdentification.waitForWebElement(btnBuildYourOwnPkg, 10)) {
				btnBuildYourOwnPkg.click();
				return true;
			}else {
				logObj.error("Build Your Own Package button is not available");
				return false;
			}
		}catch(Exception ex) {
			logObj.error("Unable to click on Build Your Own Package button, Please check");
			ex.printStackTrace();
			return false;
		}
	}
	

}
