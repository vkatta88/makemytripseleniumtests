package zenq.makemytrip.tests;

import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import zenq.makemytrip.genericutils.GenericMethods;
import zenq.makemytrip.genericutils.TestDataProps;
import zenq.makemytrip.pageclasses.MMTHomePage;
import zenq.makemytrip.pageclasses.MMTMyBizPage;

public class MyBizTests extends Driver {
	
	String workingDir = System.getProperty("user.dir");
	WebDriver driver;
//	TestDataProps testProps = new TestDataProps();
	
	TestDataProps testProps = new TestDataProps();
	Properties prop; 
	Logger logObj = Logger.getLogger("MyBizTests");
	
	@BeforeClass
	public void beforeClass() {
		prop = testProps.getProps();
	}
	
	@BeforeMethod
	public void beforeMethod() {
		PropertyConfigurator.configure(workingDir + "\\configs\\log4j.properties");
		System.setProperty("webdriver.chrome.driver", workingDir + "\\browserdrivers\\chromedriver.exe");
		driver = new ChromeDriver();  
		driver.get(prop.getProperty("app_URL"));
		
		driver.manage().window().maximize();
	}
	
	@Test
	
	public void verifyMYBiZSignUpFuctionlity() throws Exception {
		PropertyConfigurator.configure(workingDir + "\\configs\\log4j.properties");
		MMTHomePage mmtHomePage = new MMTHomePage(driver, logObj); 
		MMTMyBizPage mmtMyBizPage = new MMTMyBizPage(driver, logObj);
		
		if(!mmtHomePage.hovermOuseOnMorePlus()) {
			logObj.error("Unable to hover on More+ link, Test Failed");
			Assert.assertTrue(false);
		}
		
		if(!mmtHomePage.selectMyBIZLinkFromMorePluslist()) {
			logObj.error("Unable to navigate to MyBz page, Test Failed");
			Assert.assertTrue(false);
		}
		
		if(!mmtMyBizPage.enterMyBizName(prop.getProperty("BIZ_NAME"))) {
			logObj.error("Unable to Enter Name in Name field, Test Failed");
			Assert.assertTrue(false);
		}
		
		if(!mmtMyBizPage.enterMyBizEmailId(prop.getProperty("BIZ_EMAILID"))) {
			logObj.error("Unable to Enter EmaiId in Email Id field, Test Failed");
			Assert.assertTrue(false);
		}
		
		if(!mmtMyBizPage.enterMyBizPhoneNo(prop.getProperty("BIZ_MOBILE_NUMBER"))) {
			logObj.error("Unable to Enter Phone Number in Phone Number field, Test Failed");
			Assert.assertTrue(false);
		}
		
		if(!mmtMyBizPage.enterMyBizPassword(prop.getProperty("BIZ_PASSWORD"))) {
			logObj.error("Unable to Enter Pasword in Password field, Test Failed");
			Assert.assertTrue(false);
		}
		
		if(!mmtMyBizPage.clickOnSignUpForFree()) {
			logObj.error("Unable to Click on SignUp for free, Test Failed");
			Assert.assertTrue(false);
		}
		String result = mmtMyBizPage.verifyNextDetailsScreen();
		if(result.equals("yes")) {
			if(!mmtMyBizPage.clickOncontinueOnNeedExtraDetailsDialog()) {
				logObj.error("Clicking on Continue button failed, Test Failed");
				Assert.assertTrue(false);
			}
			
			if(!mmtMyBizPage.enterCompanyName(prop.getProperty("BIZ_COMPANY_NAME"))) {
				logObj.error("Unable to Company name in Company Name field, Test Failed");
				Assert.assertTrue(false);
			}
			
			if(!mmtMyBizPage.enterPancard(prop.getProperty("BIZ_PAN"))) {
				logObj.error("Unable to enter PAN Card in Compny Pan Card field, Test Failed");
				Assert.assertTrue(false);
			}
			
			if(!mmtMyBizPage.clickOnSignUpForFree()) {
				logObj.error("Unable to Click on SignUp for free after enteirng the extra details needed, Test Failed");
				Assert.assertTrue(false);
			}
			
		}else if(result.equals("fail")) {
			logObj.error("Clicking on Continue button failed, Test Failed");
			Assert.assertTrue(false);
		}
		
		if(!mmtMyBizPage.verifySignUpSuccessfull(prop.getProperty("BIZ_EMAILID"))) {
			logObj.error("Signup not ucessfull, Test Failed");
			Assert.assertTrue(false);
		}		
		
	}
	
	
	@AfterMethod
	public void afterMethod(ITestResult result) throws Exception {
		GenericMethods genMethods = new GenericMethods();
		if(result.isSuccess()) {
			logObj.info(result.getName().toUpperCase() + " has success.");
			
		}else {
			logObj.error(result.getName().toUpperCase() + " has failure.");			
		}
		
		genMethods.takeSnapshot(driver,  result.getName().trim().toUpperCase());
		driver.quit();
		
	}

}
