package zenq.makemytrip.pageclasses;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import zenq.makemytrip.genericutils.ObjectIdentificationUtils;

public class MMTHomePage {
	WebDriver driver;
	Logger logObj;
	ObjectIdentificationUtils objIdentification = new ObjectIdentificationUtils();
	
	public MMTHomePage(WebDriver driver, Logger logObj) {
		this.driver= driver;
		this.logObj = logObj;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how=How.ID, using="ch_funnel_more")
	WebElement lnkMorePlus;	
	@FindBy(how=How.LINK_TEXT, using="MyBiz")
	WebElement lnkMyBiz;
	@FindBy(how=How.LINK_TEXT, using="International Holidays")
	WebElement lnkHolidays;
	
	public boolean hovermOuseOnMorePlus() throws Exception {
		try {
			if(objIdentification.waitForWebElement(lnkMorePlus, 30)) {
				Actions actObj = new Actions(driver);
				actObj.moveToElement(lnkMorePlus).build().perform();
				if(objIdentification.waitForWebElement(lnkMyBiz, 30)) {
					return true;
				}else {
					
					return false;
				}
	
			}else {
				logObj.error("More + link is not availablel to do mousehover, Please check!");
				return false;
			}
		}catch(Exception ex) {
			logObj.error("Unable to hover mouse om More + link, Please check!");
			return false;
		}
	}
	
	public boolean selectMyBIZLinkFromMorePluslist() {
		try {
			lnkMyBiz.click();
			MMTMyBizPage myBizPage = new MMTMyBizPage(driver, logObj);
			if(myBizPage.verifyMyBizPageHeadingAppears()) {
				return true;
			}else {
				logObj.error("Unable to load My Biz Home page after clicking on MyBiz link");
				return false;
			}
		}catch(Exception ex) {
			logObj.error("Unble to click on My Biz link in More + options");
			return false;
		}
	}
	
	
	public boolean selectHolidaysLinkFromMoreplusList() {
		try {
			lnkHolidays.click();
			MMTHolidaysPage holidaysPage = new MMTHolidaysPage(driver, logObj);
			if(holidaysPage.verifyBuildYourOwnPkgBtn()) {
				return true;
			}else {
				logObj.error("Unable to load nternational Holidays page after clicking on MyBiz link");
				return false;
			}
		}catch(Exception ex) {
			logObj.error("Unble to click on International Holidays link in More + options");
			return false;
		}
	}
	
	
	
	

}
