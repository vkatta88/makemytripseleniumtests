package zenq.makemytrip.pageclasses;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import zenq.makemytrip.genericutils.ObjectIdentificationUtils;

public class SearchFlightsPage {
	Logger logObj;
	WebDriver driver;
	ObjectIdentificationUtils objIdentification = new ObjectIdentificationUtils();
	public SearchFlightsPage(WebDriver driver, Logger logObj) {
		this.driver = driver;
		this.logObj = logObj;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how=How.XPATH, using="//a[contains(@class, 'modify_search_toggle')]")
	WebElement btnModifySearch;
	
	
	
	@FindBy(how=How.XPATH, using="//p[contains(text(), 'Aargh! No flights found')]")
	WebElement eleNoFLightsErrMsg;
	
//	@FindBy(how=How.XPATH, using="//div[contains(@class, 'listing_top clearfix')]")
//	WebElement divSearchedFlights;
	

	@FindAll({
	    @FindBy(how = How.XPATH, using = "//div[contains(@class, 'listing_top clearfix')]")	    
	})
	private List<WebElement> divSearchedFlights;
	
	public boolean verifyModifysrchBtnAvailability() throws Exception {
		try{
			if(objIdentification.waitForWebElement(btnModifySearch, 30)) {
		
				return true;
			}else{
				return false;
			}
		}catch(Exception ex) {
			logObj.error("Some thing went wrong whilw waiting until Modify Search Button, Please check!");
			return false;
		}
	}
	
	public boolean noFlightsMsg() throws Exception {
		try{
			if(objIdentification.waitForWebElement(eleNoFLightsErrMsg, 2)) {
		
				return true;
			}else{
				return false;
			}
		}catch(Exception ex) {
			logObj.error("Some thing went wrong while checking NoFLight error messsage, Please check");
			ex.printStackTrace();
			return false;
		}
	}
	
	public int searchFlightSuccessfull() throws Exception {
		try{
			return divSearchedFlights.size();
		}catch(Exception ex) {
			logObj.error("Some thing went wrong while checking searched flights dispalyed or not, Please check!");
			ex.printStackTrace();
			return -1;
		}
	}
	
	
	
	
	
	

}
