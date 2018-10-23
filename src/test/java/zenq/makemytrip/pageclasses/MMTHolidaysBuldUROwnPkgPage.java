package zenq.makemytrip.pageclasses;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import zenq.makemytrip.genericutils.ObjectIdentificationUtils;

public class MMTHolidaysBuldUROwnPkgPage {
	WebDriver driver;
	ObjectIdentificationUtils objIdentification = new ObjectIdentificationUtils();
	Logger logObj;
	public MMTHolidaysBuldUROwnPkgPage(WebDriver driver,Logger logObj) {
		this.driver= driver;
		this.logObj = logObj;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how=How.XPATH, using="//p[text()='Starting City']//following-sibling::span")
	WebElement fldStartingCity;
	
	@FindBy(how=How.NAME, using="oneMonth")
	WebElement fldStartDate;
	
	@FindBy(how=How.ID, using="ui-datepicker-div")
	WebElement eleStartDateCalendar;
	
	@FindBy(how=How.XPATH, using="//input[contains(@class,'destinationCity')]")
	WebElement fldDestinationCity;
	
	@FindBy(how=How.LINK_TEXT, using="+ Add More Room")
	WebElement lnkAddMoreRooms;
	
	@FindBy(how=How.LINK_TEXT, using="Get Details")
	WebElement lnkgetDetails;
	
	@FindBy(how=How.XPATH, using="//input[contains(@class, 'destinationCity')]")
	WebElement flddestinationcity;
	
	@FindBy(how=How.XPATH, using="//li[@class='ui-menu-item']")
	WebElement menuDestinationCities;	
		
	public boolean selectStartCity(String city) {		
		try{
			if(objIdentification.waitForWebElement(fldStartingCity, 30)) {
				fldStartingCity.click();
				WebElement lnkEle = objIdentification.getWebElementWithLnkText(driver,  city);
				
				if(lnkEle != null) {
					lnkEle.click();
					return true;
				}else {
					logObj.error("Unable to find Desired city  in the dropdown list of Start City");
					return false;
				}
			}else {
				logObj.error("Start City Element is not available , Please check");
				return false;
			}
		}catch(Exception ex) {
			logObj.error("Unable to click on Build Your Own Package button, Please check");
			ex.printStackTrace();
			return false;
		}
	}
	
	public boolean clickOnStartDateField() {
		try {
			if(objIdentification.waitForWebElement(fldStartDate, 10)) {
				fldStartDate.click();
				if(objIdentification.waitForWebElement(eleStartDateCalendar, 5)) {
					return true;
				}else {
					logObj.error("Date Calendar not opened after clicking on Start Date field, Please check");
					return false;
				}
				
			}else {
				logObj.error("Start Date field not available, PLease check");
				return false;
			}
			
		}catch(Exception ex) {
			logObj.error("Unable to click on Start Date in Build you own page, PLease check");
			ex.printStackTrace();
			return false;
		}
	}
	
	
	public boolean selectStartDate(String date) {
		try {
			if(objIdentification.selectDateFromSingleCalendar(driver, date, eleStartDateCalendar)) {
				return true;
			}else {
				logObj.error("SelectDateFromSingleCalendar method failed, PLease check");
				return false;
			}
			
		}catch(Exception ex) {
			logObj.error("Unable to select Date  from Calendar in Build you own page, PLease check");
			ex.printStackTrace();
			return false;
		}
	}
	
	public boolean selectDestinationCity(String destinationCity) {
		try {
			if(objIdentification.waitForWebElement(flddestinationcity, 30)) {
				flddestinationcity.sendKeys("Goa, Goa");
				flddestinationcity.click();
				Robot ron = new Robot();
				ron.keyPress(KeyEvent.VK_DOWN);
				ron.keyPress(KeyEvent.VK_ENTER);
				int cnt = 0;
				while(true) {
					try {
						if(driver.findElement(By.xpath("//div[contains(@class, 'city_row')]")).isDisplayed()){
							return true;
						}
					}catch(Exception ex) {
						Thread.sleep(1000);
						cnt = cnt +1;
						if(cnt > 10) {
							logObj.error("Unable to Add Destination city, PLease check!");
							return false;
						}
					}
				}			
								
			}else {
				logObj.error("Destination City Element is not available , Please check");
				return false;
			}
		}catch(Exception ex) {
			logObj.error("Unable to select Destination city in Build you own page, PLease check");
			ex.printStackTrace();
			return false;
		}
		
	}
	
	public boolean enterRoomsAndTravellersDetails(WebDriver driver, String adults, String children, String infants){
		try{
			int iAdults = Integer.parseInt(adults);		
			int iChildren = Integer.parseInt(children);		
			int iInfants = Integer.parseInt(infants);		
			if(objIdentification.enterRoomDetails(driver, iAdults, iChildren, iInfants)) {
				return true;
			}else {
				logObj.error("enterRoomDetails  method failed, unable to select traveller details, please check");
				return false;
			}
			
		}catch(Exception ex){
			logObj.error("Unable to select traveller details, Please check");
			ex.printStackTrace();
			return false;
		}
		
	}
	
	public boolean clickOnGetDetailsButton() {
		try {
			if(objIdentification.waitForWebElement(lnkgetDetails, 10)) {
				lnkgetDetails.click();
				return true;
			}else {
				logObj.error("Element is not avaialble on the page, Please check");
				return false;
			}
			
		}catch(Exception ex) {
			logObj.error("Unable to click on Getdetails button in Build you own page, PLease check");
			ex.printStackTrace();
			return false;
		}
	}
	
	public boolean verifyAndValidateAlerts(String AlertMessage, String AcceptOrDismiss) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		try{
		    wait.until(ExpectedConditions.alertIsPresent());
		    Alert alert = driver.switchTo().alert();
		    logObj.info("Alert Message is " + alert.getText());
		    if(!AlertMessage.isEmpty()) {
		    	
		    	if(alert.getText().toUpperCase().trim().contains(AlertMessage.toUpperCase().trim())) {
			    	return false;	
			    }else {
			    	logObj.error("Alert message validation failed");
			    	alert.dismiss();
			    	return false;
			    }
			    
		    }
		    
		    if(AcceptOrDismiss.toUpperCase().equals("ACCEPT")) {
	    		alert.accept();
	    		return false;
	    	}else if(AcceptOrDismiss.toUpperCase().equals("DISMISS")) {
	    		alert.dismiss();
	    		return false;
	    	}
	    	else {
	    		logObj.error("Invalid Option seleccted to close the Alert, Only Accept or dismiss are valid");
	    		return false;
	    	}
		    
		}
		catch (Exception e){
		    logObj.error("No alert");
		    return true;
		}
		
	}
	

}
