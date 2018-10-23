package zenq.makemytrip.tests;

import java.net.URL;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import zenq.makemytrip.genericutils.GenericMethods;
import zenq.makemytrip.genericutils.TestDataProps;
import zenq.makemytrip.pageclasses.MMTFlightsPage;
import zenq.makemytrip.pageclasses.MMTHolidaysBuldUROwnPkgPage;
import zenq.makemytrip.pageclasses.MMTHolidaysPage;
import zenq.makemytrip.pageclasses.MMTHomePage;
import zenq.makemytrip.pageclasses.SearchFlightsPage;

public class MakeMyTripTests extends Driver {
	String workingDir = System.getProperty("user.dir");
	WebDriver driver;
	TestDataProps testProps = new TestDataProps();
	Properties prop; 
	Logger logObj = Logger.getLogger("MakeMyTripTests");
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
	public void verifySearchFLights() throws Exception {
		MMTFlightsPage flightsPage = new MMTFlightsPage(driver, logObj);
		SearchFlightsPage searchFightsPage = new SearchFlightsPage(driver, logObj);
		if(!flightsPage.clicknOnFromPlace()) {
			logObj.error("Click on From Place Failed");
			Assert.assertTrue(false);
		}
		
		if(!flightsPage.selectFromPlace(prop.getProperty("FROM_PLACE"))) {
			logObj.error("Unable tp select value from From Place dropdown");
			Assert.assertTrue(false);
		}
		
		if(!flightsPage.clicknOnToPlace()) {
			logObj.error("Unable tp select value from To Place dropdown");
			Assert.assertTrue(false);
		}
		
		if(!flightsPage.selectToPlace(prop.getProperty("TO_PLACE"))) {
			logObj.error("Unable tp select value from To Place dropdown");
			Assert.assertTrue(false);
		}
		
		if(!flightsPage.ClickOnDepartDate()) {
			logObj.error("Unable Click on Dparture Date");
			Assert.assertTrue(false);
		}
		
		if(!flightsPage.selectDateFromCalendar(prop.getProperty("START_DATE"))) {
			logObj.error("Unable to selct date");
			Assert.assertTrue(false);
		}
				
		if(!flightsPage.clickOnSelectPassengerSeatType()) {
			logObj.error("Unable to click on Select type of Class and number o seats ");
			Assert.assertTrue(false);
		}
		
		if(!flightsPage.selectNumberOfPassengers(prop.getProperty("ADULTS_SEATS"), prop.getProperty("CHILDREN_SEATS"), prop.getProperty("INFANT_SEATS"))) {
			logObj.error("Unable to select eas and class");
			Assert.assertTrue(false);
		}
		
		if(!flightsPage.selectBusinessClass(prop.getProperty("SEAT_CLASSTYPE"))) {
			logObj.error("Unable to select class");
			Assert.assertTrue(false);
		}
		
		if(!flightsPage.clickOnDoneButton()) {
			logObj.error("Unable to select class");
			Assert.assertTrue(false);
		}
		
		if(!flightsPage.clickOnSearchButton()) {
			logObj.error("Unable to click search button");
			Assert.assertTrue(false);
		}
		
		if(!searchFightsPage.verifyModifysrchBtnAvailability()) {
			logObj.error("Unable to get the search flights page after entering journey details, PLease check.");
			Assert.assertTrue(false);
		}
		
		if(searchFightsPage.noFlightsMsg()) {
			logObj.info("No filghts are available in the serached route on the given depart date");
		}else if(searchFightsPage.searchFlightSuccessfull() >=1){
			logObj.info("flights available and please go and buk desired flight");
		}
		
		Assert.assertTrue(true);		
		
	}
	
	@Test
	public void verifyMaxOccupancyPerRoomBuildYourOwnPkgFunc() throws Exception {
		
		MMTHomePage mmtHomePage = new MMTHomePage(driver, logObj);
		MMTHolidaysPage internHolidaysPage = new MMTHolidaysPage(driver, logObj);
		String parentWindow = driver.getWindowHandle();
		
		if(!mmtHomePage.hovermOuseOnMorePlus()) {
			logObj.error("Unable to hover on More+ link, Test Failed");
			Assert.assertTrue(false);
		}
		
		if(!mmtHomePage.selectHolidaysLinkFromMoreplusList()) {
			logObj.error("Unable to navigate to International Holidays page, Test Failed");
			Assert.assertTrue(false);
		}
		
		if(!internHolidaysPage.clickOnBuildYourOwnPkgBtn()) {
			logObj.error("Unable to navigate to International Holidays page, Test Failed");
			Assert.assertTrue(false);
		}
//		
		
		Set<String> childWindows = driver.getWindowHandles();
		
		for(String childWindow : childWindows) {
			if(!parentWindow.equals(childWindow)) {
				driver.switchTo().window(childWindow);
				MMTHolidaysBuldUROwnPkgPage buildUrOwnPkgPage = new MMTHolidaysBuldUROwnPkgPage(driver, logObj);
				if(!buildUrOwnPkgPage.selectStartCity(prop.getProperty("START_CITY"))) {
					logObj.error("Test Failed at selecting Start City in Build Your Own Packag page");
					Assert.assertTrue(false);
				}
				
				if(!buildUrOwnPkgPage.clickOnStartDateField()) {
					logObj.error("Test Failed at clicking on Start Date in Build Your Own Packag page");
					Assert.assertTrue(false);
				}
				
				if(!buildUrOwnPkgPage.selectStartDate(prop.getProperty("START_DATE"))) {
					logObj.error("Test Failed at selecting Start Date in Build Your Own Packag page");
					Assert.assertTrue(false);
				}
				
				if(!buildUrOwnPkgPage.selectDestinationCity(prop.getProperty("DESTINATION_CITY"))) {
					logObj.error("Test Failed at selecting Destination City in Build Your Own Packag page");
					Assert.assertTrue(false);
				}
				
				if(!buildUrOwnPkgPage.enterRoomsAndTravellersDetails(driver, prop.getProperty("NO_OF_ADULTS"), prop.getProperty("NO_OF_CHILDREN"), prop.getProperty("NO_OF_INFANTS"))) {
					logObj.error("Test Failed at selecting traveller details and per room details in Build Your Own Packag page");
					Assert.assertTrue(false);
				}
				
				if(!buildUrOwnPkgPage.clickOnGetDetailsButton()) {
					logObj.error("Test Failed at clicking on Get Details button in Build Your Own Packag page");
					Assert.assertTrue(false);
				}
				if(buildUrOwnPkgPage.verifyAndValidateAlerts("A room can have at max occupancy of 5.", "Accept")) {
					logObj.error("Alert is not shown for Maximum occupancy of 5 members per room");
					Assert.assertTrue(false);
				}else {
					Assert.assertTrue(true);
				}
				driver.close();
			}		
			driver.switchTo().window(parentWindow);
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
