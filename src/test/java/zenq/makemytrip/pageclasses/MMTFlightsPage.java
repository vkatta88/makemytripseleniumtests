package zenq.makemytrip.pageclasses;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import zenq.makemytrip.genericutils.ObjectIdentificationUtils;

public class MMTFlightsPage {
	WebDriver driver;
	Logger logObj;
	ObjectIdentificationUtils identifyObjects = new ObjectIdentificationUtils();
	public MMTFlightsPage(WebDriver driver, Logger logObj) {
		this.driver = driver;
		this.logObj = logObj;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how=How.XPATH, using="//input[contains(@id, 'hp-widget__sfrom')]")
	WebElement fldFromPlace;
	
	@FindBy(how=How.XPATH, using="//input[contains(@id, 'hp-widget__sTo')]")
	WebElement fldToPLace;
	
	@FindBy(how=How.ID, using="hp-widget__depart")
	WebElement fldDepartDate;
	
	@FindBy(how=How.ID, using="hp-widget__paxCounter_pot")
	WebElement fldPassengerSeatType;
	
	@FindBy(how=How.ID, using="economy")
	WebElement btnEconomy;
	
	@FindBy(how=How.ID, using="premiumEconomy")
	WebElement btnPremiumEconomy;
	
	@FindBy(how=How.ID, using="Business")
	WebElement btnBusiness;
	
	@FindBy(how=How.ID, using="searchBtn")
	WebElement btnSearch;
	
	@FindBy(how=How.ID, using="business")
	WebElement btnbusiness;
	
	@FindBy(how=How.LINK_TEXT, using="Done")
	WebElement lnkDone;
	
	@FindBy(how=How.XPATH, using="//ul[@id = 'js-adult_counter']")
	WebElement fldAdultCounter;
	
	public boolean clicknOnFromPlace(){		
		try {
			fldFromPlace.click();
		}catch(NoSuchElementException ex) {
			logObj.error("From Place field element i not available, unable to click, Please check!");
			return false;
		}catch(ElementNotVisibleException ex) {
			logObj.error("From Place Element is not visible or hidden trying to click on hidden elements, unable to click, Please check!");
			return false;
		}		
		return true;
	}
	
	public boolean selectFromPlace(String fromPlace) throws Exception{
		int cnt = 0;
		while(true) {
			
			try {
				driver.findElement(By.xpath("//span[contains(text(), '"+fromPlace+"')]")).click();
				return true;
			}catch(Exception ex) {
				cnt = cnt + 1;
				Thread.sleep(1000);
				if(cnt > 30) {
					logObj.error("Unable to identify field even after 30 sec");
					return false;
				}
			}
		}
		
//		WebElement optionNewDelhi = identifyObjects.selectElementWithTextAndTagName(driver,  "span", fromPlace);
//		if(optionNewDelhi != null) {
//			optionNewDelhi.click();
//			return true;
//		}else {
//			logObj.error("Unable to select from value");
//			return false;
//		}		
	}
	
	public boolean clicknOnToPlace(){		
		try {
			fldToPLace.click();
		}catch(NoSuchElementException ex){
			logObj.error("To Place field element in not available, unable to click, Please check!");
			return false;
		}catch(ElementNotVisibleException ex){
			logObj.error("To Place Element is not visible or hidden trying to click on hidden elements, unable to click, Please check!");
			return false;
		}		
		return true;
	}
	
	public boolean selectToPlace(String toPlace) throws Exception{
		
		int cnt = 0;
		while(true) {
			
			try {
				driver.findElement(By.xpath("//ul[@class='ui-autocomplete ui-front ui-menu ui-widget ui-widget-content hp-widget__sTo']//child::span[contains(text(), '"+toPlace+"')]")).click();
				return true;
			}catch(Exception ex) {
				cnt = cnt + 1;
				Thread.sleep(1000);
				if(cnt > 30) {
					logObj.error("Unable to identify field even after 30 sec");
					return false;
				}
			}
		}
//		WebElement optionNewDelhi = identifyObjects.selectElementWithTextAndTagName(driver,  "span", toPlace);
//		if(optionNewDelhi != null) {
//			optionNewDelhi.click();
//			return true;
//		}else {
//			logObj.error("Unable to select to value");
//			return false;
//		}		
	}
	
	public boolean ClickOnDepartDate(){		
		try {
			fldDepartDate.click();
		}catch(NoSuchElementException ex){
			logObj.error("Depart Date field element in not available, unable to click, Please check!");
			return false;
		}catch(ElementNotVisibleException ex){
			logObj.error("Depart Date Element is not visible or hidden trying to click on hidden elements, unable to click, Please check!");
			return false;
		}		
		return true;
	}
	
	
	public boolean selectDateFromCalendar(String date) throws Exception {		
		if(identifyObjects.selectDateFromCalendar(driver,  date)){
			return true;
		}else {
			return false;
		}
	}
	
	public boolean clickOnSelectPassengerSeatType() {		
		try {
			fldPassengerSeatType.click();
		}catch(NoSuchElementException ex){
			logObj.error("Depart Date field element in not available, unable to click, Please check!");
			return false;
		}catch(ElementNotVisibleException ex){
			logObj.error("Depart Date Element is not visible or hidden trying to click on hidden elements, unable to click, Please check!");
			return false;
		}		
		return true;
	}
	
	public boolean selectNumberOfPassengers(String adults, String children, String infants) throws Exception {
		
		if(identifyObjects.waitForWebElement(fldAdultCounter, 30)){	
		
			if(!adults.isEmpty()) {
				WebElement btnAdultsNumber = driver.findElement(By.xpath("//ul[@id = 'js-adult_counter']//child::li[text()='"+ adults +"']"));
				try {
					btnAdultsNumber.click();
				}catch(NoSuchElementException ex){
					logObj.error("Adult #seat  is not available, unable to click, Please check!");
					return false;
				}catch(ElementNotVisibleException ex){
					logObj.error("Adult #seat  is not available, unable to click, unable to click, Please check!");
					return false;
				}
			}
			
			if(!children.isEmpty()) {
				WebElement btnChildNumber = driver.findElement(By.xpath("//ul[@id = 'js-child_counter']//child::li[text()='"+ children +"']"));
				try {
					btnChildNumber.click();
				}catch(NoSuchElementException ex){
					logObj.error("Children #seat is not available, unable to click, Please check!");
					return false;
				}catch(ElementNotVisibleException ex){
					logObj.error("Children #seat is not available, unable to click, unable to click, Please check!");
					return false;
				}
			}
			
			
			if(!infants.isEmpty()) {
				WebElement btnInfantsNumber = driver.findElement(By.xpath("//ul[@id = 'js-infant_counter']//child::li[text()='"+ infants +"']"));
				try {
					btnInfantsNumber.click();
				}catch(NoSuchElementException ex){
					logObj.error("Infants #seat is not available, unable to click, Please check!");
					return false;
				}catch(ElementNotVisibleException ex){
					logObj.error("Infants #seat is not available, unable to click, unable to click, Please check!");
					return false;
				}
			}
			
			return true;
		}else {
				logObj.error("Unable to find Seat Type list dialog, PLease check");				
				return false;
			}
		
	}
	
	
	public boolean selectBusinessClass(String busClass) {		
		if(!busClass.isEmpty()) {
			if(busClass.equalsIgnoreCase("Economy")) {
				try {
					btnEconomy.click();
				}catch(NoSuchElementException ex){
					logObj.error("Economy button is not available, unable to click, Please check!");
					return false;
				}catch(ElementNotVisibleException ex){
					logObj.error("Economy is not available, unable to click, unable to click, Please check!");
					return false;
				}
			}else if(busClass.equalsIgnoreCase("Premium Economy")) {
				try {
					btnPremiumEconomy.click();
				}catch(NoSuchElementException ex){
					logObj.error("Premium Economy button is not available, unable to click, Please check!");
					return false;
				}catch(ElementNotVisibleException ex){
					logObj.error("Premium Economy  button is not available, unable to click, unable to click, Please check!");
					return false;
				}
			}else if(busClass.equalsIgnoreCase("Business")) {
				try {
					btnBusiness.click();
				}catch(NoSuchElementException ex){
					logObj.error("Business button is not available, unable to click, Please check!");
					return false;
				}catch(ElementNotVisibleException ex){
					logObj.error("Business button is not available, unable to click, unable to click, Please check!");
					return false;
				}
			}
		}else {
			logObj.error("Did not send any classs type, By default Business classs seats will be slected");
			return false;
		}	
		return true;
	}
	
	
	public boolean clickOnDoneButton() {
		try {
			lnkDone.click();
		}catch(NoSuchElementException ex){
			logObj.error("Done link is not available, unable to click, Please check!");
			return false;
		}catch(ElementNotVisibleException ex){
			logObj.error("Done link is not available, unable to click, unable to click, Please check!");
			return false;
		}
		return true;
	}
	
	
	public boolean clickOnSearchButton() {
		try {
			btnSearch.click();
		}catch(NoSuchElementException ex){
			logObj.error("Search button is not available, unable to click, Please check!");
			return false;
		}catch(ElementNotVisibleException ex){
			logObj.error("search button is not available, unable to click, unable to click, Please check!");
			return false;
		}
		return true;
	}
	
	
	
	
	
	
	
	
	

}
