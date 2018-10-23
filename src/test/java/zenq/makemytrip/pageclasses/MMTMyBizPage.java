package zenq.makemytrip.pageclasses;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import zenq.makemytrip.genericutils.ObjectIdentificationUtils;

public class MMTMyBizPage {
	
	WebDriver driver;
	Logger logObj;
	ObjectIdentificationUtils objIdentification = new ObjectIdentificationUtils();
	
	public MMTMyBizPage(WebDriver driver, Logger logObj) {
		this.driver= driver;
		this.logObj = logObj;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(how=How.XPATH, using="//h1[text()='Introducing myBiz by MakeMyTrip']")
	WebElement eleMyBizPageheading;
	
	@FindBy(how=How.ID, using="name")
	WebElement fldMyBizName;
	
	@FindBy(how=How.ID, using="emailId")
	WebElement fldMyBizEmailId;
	
	@FindBy(how=How.ID, using="phoneNo")
	WebElement fldMyBizPhoneNo;
	
	@FindBy(how=How.ID, using="password")
	WebElement fldMyBizPassword;
	
	@FindBy(how=How.ID, using="companyName")
	WebElement fldCompanyName;
	
	@FindBy(how=How.ID, using="pancard")
	WebElement fldPanCard;
	
	@FindBy(how=How.ID, using="gstn")
	WebElement fldGSTN;
	
	@FindBy(how=How.ID, using="signup-buttopn")
	WebElement btnSignUpForFree;
	
	@FindBy(how=How.XPATH, using="//div[@class='modal-content modal-popup']//child::span[text()='Need extra details']")
	WebElement dlgNeedXtraDetails;
	
	@FindBy(how=How.XPATH, using="//div[@class='modal-content modal-popup']//child::p[@id='message']")
	WebElement dlgBodyMessage;
	
	@FindBy(how=How.LINK_TEXT, using="Continue")
	WebElement btnDlgContinue;
	
	@FindBy(how=How.XPATH, using="//h3[@id='post-sign-up-heading-msg']")
	WebElement eleHeadingPostSignUPMsg;
	
	@FindBy(how=How.XPATH, using="//span[@id='user-email']")
	WebElement eleUserEmail;
	
	
	public boolean verifyMyBizPageHeadingAppears() throws Exception {
		try{
			if(objIdentification.waitForWebElement(eleMyBizPageheading, 30)) {
		
				return true;
			}else {
				logObj.error("My Biz heading element is not available, Page not loaded successfully");
				return false;
			}
		}catch(Exception ex) {
			logObj.error("Somethign went wrong whle waiting fpr My BOz heading get loaded, Please check");
			ex.printStackTrace();
			return false;
		}
	}
	
	public boolean enterMyBizName(String name) {
		try{
			if(objIdentification.waitForWebElement(fldMyBizName, 30)) {
				fldMyBizName.sendKeys(name);
				return true;
			}else {
				logObj.error("My Biz Name field is not available, Please check!");
				return false;
			}
		}catch(Exception ex) {
			logObj.error("Somethign went wrong while entering name in My Biz sigup screen, Please check!");
			ex.printStackTrace();
			return false;
		}
	}
	
	public boolean enterMyBizEmailId(String emailId) {
		try{
			if(objIdentification.waitForWebElement(fldMyBizEmailId, 30)) {
				fldMyBizEmailId.sendKeys(emailId);
				return true;
			}else {
				logObj.error("My Biz EmailID field is not available, Please check!");
				return false;
			}
		}catch(Exception ex) {
			logObj.error("Somethign went wrong while entering EmailID in My Biz sigup screen, Please check!");
			ex.printStackTrace();
			return false;
		}
	}
	
	public boolean enterMyBizPhoneNo(String phNo) {
		try{
			if(objIdentification.waitForWebElement(fldMyBizPhoneNo, 30)) {
				fldMyBizPhoneNo.sendKeys(phNo);
				return true;
			}else {
				logObj.error("My Biz Phone Number field is not available, Please check!");
				return false;
			}
		}catch(Exception ex) {
			logObj.error("Somethign went wrong while entering Phone Number in My Biz sigup screen, Please check!");
			ex.printStackTrace();
			return false;
		}
	}
	
	public boolean enterMyBizPassword(String pwd) {
		try{
			if(objIdentification.waitForWebElement(fldMyBizPassword, 30)) {
				fldMyBizPassword.sendKeys(pwd);
				return true;
			}else {
				logObj.error("My Biz Password field is not available, Please check!");
				return false;
			}
		}catch(Exception ex) {
			logObj.error("Somethign went wrong while entering Password in My Biz sigup screen, Please check!");
			ex.printStackTrace();
			return false;
		}
	}
	
	public boolean enterCompanyName(String companyName) {
		try{
			if(objIdentification.waitForWebElement(fldCompanyName, 30)) {
				fldCompanyName.sendKeys(companyName);
				return true;
			}else {
				logObj.error("My Biz Company Name field is not available, Please check!");
				return false;
			}
		}catch(Exception ex) {
			logObj.error("Somethign went wrong while entering Company Name in My Biz sigup screen, Please check!");
			ex.printStackTrace();
			return false;
		}
	}
	
	
	public boolean enterPancard(String panCard) {
		try{
			if(objIdentification.waitForWebElement(fldPanCard, 30)) {
				fldPanCard.sendKeys(panCard);
				return true;
			}else {
				logObj.error("My Biz Pan Card field is not available, Please check!");
				return false;
			}
		}catch(Exception ex) {
			logObj.error("Somethign went wrong while entering Pan Card in My Biz sigup screen, Please check!");
			ex.printStackTrace();
			return false;
		}
	}
	
	public boolean enterGSTN(String myBIZGSTN) {
		try{
			if(objIdentification.waitForWebElement(fldGSTN, 30)) {
				fldGSTN.sendKeys(myBIZGSTN);
				return true;
			}else {
				logObj.error("My Biz GSTN field is not available, Please check!");
				return false;
			}
		}catch(Exception ex) {
			logObj.error("Somethign went wrong while entering GSTN in My Biz sigup screen, Please check!");
			ex.printStackTrace();
			return false;
		}
	}
	
	
	public boolean clickOnSignUpForFree() {
		try{
			if(objIdentification.waitForWebElement(btnSignUpForFree, 30)) {
				btnSignUpForFree.click();
				return true;
			}else {
				logObj.error("My Biz Signup For Free button is not available, Please check!");
				return false;
			}
		}catch(Exception ex) {
			logObj.error("Somethign went wrong while clicking Signup For Free button in My Biz sigup screen, Please check!");
			ex.printStackTrace();
			return false;
		}
	}
	
	public String verifyNextDetailsScreen() {
		try{
			if(objIdentification.waitForWebElement(dlgNeedXtraDetails, 10)) {		
				return "yes";
			}else {
				logObj.info("Need Extra Details dialg not displyed");
				return "no";
			}
		}catch(Exception ex) {
			logObj.error("Somethign went wrong while verifying whether verifying Need extra details screen os dipalyed, Please check");
			ex.printStackTrace();
			return "fail";
		}
	}
	
	public boolean clickOncontinueOnNeedExtraDetailsDialog() {
		try{
			if(objIdentification.waitForWebElement(dlgBodyMessage, 5)) {
				String text = dlgBodyMessage.getText();
				if(text.toLowerCase().equals("Since you are using a public domain email id, we will need your company Name, PAN and GSTN details as well")) {
					btnDlgContinue.click();
					try{
						if(objIdentification.waitForWebElement(fldCompanyName, 10)) {		
							return true;
						}else {
							logObj.error("Comany name Field is not diplayed, PLease check the My BIZ signup screen");
							return true;
						}
					}catch(Exception ex) {
						logObj.error("Somethign went wrong while waiting for Company Name field after click on Continue button in Ned Extra details dialog, Please check");
						ex.printStackTrace();
						return false;
					}
				}else {
					logObj.error("Dialog message validation failed, Please check");
					return false;
				}
			}else {
				logObj.error("My Biz Password field is not available, Please check!");
				return false;
			}
		}catch(Exception ex) {
			logObj.error("Something went wrong while entering Password in My Biz sigup screen, Please check!");
			ex.printStackTrace();
			return false;
		}
	}
	
	
	public boolean verifySignUpSuccessfull(String emailID) throws Exception {
		try{
			MyBizLoginPage myBizLoginPage = new MyBizLoginPage(driver, logObj);
			if(myBizLoginPage.verifyEmailFieldInMyBizLogin()) {
				return true;
			}else {
				return false;
			}
//			if(objIdentification.waitForWebElement(eleHeadingPostSignUPMsg, 20) && objIdentification.waitForWebElement(eleUserEmail, 20)) {	
//				if(eleUserEmail.getText().trim().toLowerCase().equals(emailID)) {
//					return true;
//				}else {
//					logObj.error("Entered User MEail ID is not matched with signup user email");
//					return false;
//				}
//				
//			}else {
//				logObj.error("My Biz Successfull signup email sent message heading element or User email element is not available, Page not loaded successfully");
//				return false;		
//			}
		}catch(Exception ex) {
			logObj.error("Something went wrong while waiting My BIZ Signup successfull, Please check");
			ex.printStackTrace();
			return false;
		}
	}
	
	


}
