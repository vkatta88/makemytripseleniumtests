package zenq.makemytrip.genericutils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class ObjectIdentificationUtils {
	GenericMethods genericMethods = new GenericMethods();
	Logger logObj = Logger.getLogger("ObjectIdentificationUtils");
	public WebElement selectElementWithTextAndTagName(WebDriver driver, String tagName, String text) {
		try
		{
			WebElement ele = driver.findElement(By.xpath("//" + tagName + "[contains(text(),'" + text + "')]"));
			logObj.info("helo00000000000000 " + waitForWebElement(ele, 30));
			if(waitForWebElement(ele, 30)) {
				return ele;
			}else {
				return null;
			}
		}catch(Exception ex) {
			logObj.error("Unable to identify the required element with tagname '" + tagName +"' and text value '"+ text +"' using xpath, Please check");
			return null;
		}
		
	}
	
	public WebElement getWebElementWithLnkText(WebDriver driver, String linkText) {
		try {
			WebElement ele = driver.findElement(By.linkText(linkText));
			return ele;
		}catch(Exception ex) {
			logObj.error("Unable to find required element with link text, Please check!");
			ex.printStackTrace();
			return null;
		}
	}
	
	public boolean selectDateFromCalendar(WebDriver driver, String date) throws Exception {
		
		Date formattedDate = genericMethods.getFormattedDate(date);
		int dayOfMonth = formattedDate.getDate();
		int month = formattedDate.getMonth();
		int year = formattedDate.getYear();
		int actyear = year + 1900;
		String reqyear = Integer.toString(actyear);
		String reqDatw = Integer.toString(dayOfMonth);
		String monthName = genericMethods.getMonthString(month, "");
		WebElement calendarEle = driver.findElement(By.xpath("//div[@class='ui-datepicker-group ui-datepicker-group-first']"));
		if(waitForWebElement(calendarEle, 30)) {
			
		}else {
			logObj.error("Calender dialog not opened, PLease check");
			return false;
		}
		
		while(true) {
			WebElement calFrstMonth = driver.findElement(By.xpath("//div[@class='ui-datepicker-group ui-datepicker-group-first']//child::span[@class='ui-datepicker-month']"));
			WebElement calFirstYear = driver.findElement(By.xpath("//div[@class='ui-datepicker-group ui-datepicker-group-first']//child::span[@class='ui-datepicker-year']"));
			WebElement calLastMonth = driver.findElement(By.xpath("//div[@class='ui-datepicker-group ui-datepicker-group-last']//child::span[@class='ui-datepicker-month']"));
			WebElement calLastYear = driver.findElement(By.xpath("//div[@class='ui-datepicker-group ui-datepicker-group-last']//child::span[@class='ui-datepicker-year']"));
			logObj.info(calFrstMonth.getText());
			logObj.info(calLastYear.getText());
			if(calFrstMonth.getText().toUpperCase().trim().equals(monthName.toUpperCase()) && calFirstYear.getText().trim().equals(reqyear)) {
				calFrstMonth.click();
				try{
					driver.findElement(By.xpath("//div[@class='ui-datepicker-group ui-datepicker-group-first']//child::a[text()='"+reqDatw+"']")).click();
				}catch(NoSuchElementException ex) {
					logObj.error("Unalble to locate element, Please check the date given is greater than or equal to todays date");
					return false;
				}
				break;
			}else if (calLastMonth.getText().toUpperCase().trim().equals(monthName.toUpperCase()) && calLastYear.getText().trim().equals(reqyear)){
				calLastMonth.click();
				try{
					driver.findElement(By.xpath("//div[@class='ui-datepicker-group ui-datepicker-group-last']//child::a[text()='"+reqDatw+"']")).click();
				}catch(NoSuchElementException ex) {
					logObj.error("Unalble to locate element, Please check the date given is greater than or equal to todays date");
					return false;
				}
				break;
			}else{
				try{
					logObj.info(driver.findElement(By.xpath("//span[text()='Next']")).isEnabled() + "   trtrtuegfgirt");
					if (driver.findElement(By.xpath("//span[text()='Next']")).isEnabled()){				
						driver.findElement(By.xpath("//span[text()='Next']")).click();
						int cnt = 10;
						boolean isExists = false;;
						try{
							while(cnt<=10) {						
								isExists = driver.findElement(By.xpath("//div[@class='ui-datepicker-group ui-datepicker-group-first']")).isDisplayed();
								break;
							}
						}catch(Exception ex) {
							Thread.sleep(1000);
							cnt = cnt +1;
						}
						
						if(isExists) {
							
						}else {
							logObj.error("Unable to load calendar after clicking on Next button");
							return false;
						}
											
					}else {
						logObj.error("Reached last month in the calender to book in adavnce, PLease Depart date properly, you can book max a year advance");
						return false;
					}
				}catch(Exception ex) {
					logObj.error("Reached last month or unable to find Next arrow button on calendar, PLease check");
					return false;
					
				}
			}
		}
		
		return true;
					
		
		
		
	}
	
	
	
	public boolean waitForWebElement(WebElement ele, int seconds) throws Exception {
		
		int cnt = 0;
		boolean isExists= false;
		while(cnt <= seconds) {
			try {
				isExists = ele.isDisplayed();
				break;
			}catch(Exception ex) {
				Thread.sleep(1000);
				cnt = cnt + 1;
			}
		}		
		if(isExists) {
			return true;
		}else {
			logObj.info("Waitedf for '" + seconds + "' and Unable to find element on the Page" );
			return false;
		}
	}
	
	public boolean selectDateFromSingleCalendar(WebDriver driver, String date, WebElement calendar) throws Exception {
		Date formattedDate = genericMethods.getFormattedDate(date);
		int dayOfMonth = formattedDate.getDate();
		int month = formattedDate.getMonth();
		int year = formattedDate.getYear();
		int actyear = year + 1900;
		String reqyear = Integer.toString(actyear);
		String reqDatw = Integer.toString(dayOfMonth);
		String monthName = genericMethods.getMonthString(month, "");
//		logObj.error(monthName + " ooooooo " + reqyear);
//		WebElement calendarEle = driver.findElement(By.className("ui-datepicker-div"));
		if(waitForWebElement(calendar, 30)) {
			
//			return true;
		}else {
			logObj.error("Calender dialog not opened, PLease check");
			return false;
		}
		
		while(true) {
//			try {
				WebElement caltMonth = calendar.findElement(By.xpath("//span[@class='ui-datepicker-month']"));
				WebElement calyear = calendar.findElement(By.xpath("//span[@class='ui-datepicker-year']"));
				String actMonth = caltMonth.getText();
				String actYear = calyear.getText();
//				logObj.info(actMonth);
//				logObj.info(actYear);
				if(actMonth.trim().toUpperCase().equals(monthName.toUpperCase().trim()) && actYear.trim().toUpperCase().equals(reqyear.toUpperCase().trim())) {
					calendar.findElement(By.xpath("//a[text()='"+reqDatw+"']")).click();
					return true;
				}else{
					try{
						logObj.info(driver.findElement(By.xpath("//span[text()='Next']")).isEnabled());
						if (driver.findElement(By.xpath("//span[text()='Next']")).isEnabled()){				
							driver.findElement(By.xpath("//span[text()='Next']")).click();
							int cnt = 10;
							boolean isExists = false;;
							try{
								while(cnt<=10) {						
									isExists = calendar.isDisplayed();
									break;
								}
							}catch(Exception ex) {
								Thread.sleep(1000);
								cnt = cnt +1;
							}							
							if(isExists) {								
							}else {
								logObj.error("Unable to load calendar after clicking on Next button");
								return false;
							}												
						}else {
							logObj.error("Reached last month in the calender to book in adavnce, Please Depart date properly, you can book max a year advance");
							return false;
						}
					}catch(Exception ex) {
						logObj.error("Reached last month or unable to find Next arrow button on calendar, PLease check");
						return false;						
					}
				}				
//				return true;
//			}catch(Exception ex) {
//				logObj.error("Something went wrong while selecting the Date from Calendar, Please check!");
//				return false;
//			}
		}		
		
	}
	
	
	
	public boolean enterRoomDetails(WebDriver driver, int adults, int children, int infants) {
		
		
		if(adults > 20) {			
			logObj.error("You cannot give more than 20 adults");
			return false;
		}
		if(!(children > adults)) {
			if(children > 15 ) {				
				logObj.error("You cannot give more than 15 children");
				return false;
			}
		}else {
			logObj.error("no of children should not be greater than Adults");
			return false;
		}
		
		if(!(infants > children) && !(infants > adults)) {
		
			if(infants > 10) {
				logObj.error("You cannot give more than 15 infants");
				return false;
			}
		}else {
			logObj.error("no of infants should not be greater than Adults and children");
			return false;
		}
		int nofAdultRooms = 0;
		if(adults > 4){
			
			int adultquotient = adults /4;
			int adultReminder = adults % 4;
//			logObj.info(adultquotient + "====="+ adultReminder);
			if(adultReminder > 0) {
				nofAdultRooms = adultquotient;
			}else {
				nofAdultRooms =  adultquotient - 1;
			}
		}
		
		if(children > 3) {
			int childquotient = children /3;
			int childReminder = children %3;
			
		}
		
		if(infants > 2) {
			int infantquotient = children /3;
			int infantReminder = children %3;
		}
		
		try {
			for(int i=1; i<=nofAdultRooms;i++) {
				driver.findElement(By.xpath("//a[text()='+ Add More Room']")).click();

			}		
			List<WebElement> adultDropDowns = driver.findElements(By.xpath("//p[contains(text(), 'ADULT')]//following-sibling::div"));
			List<WebElement> childDropDowns = driver.findElements(By.xpath("//p[contains(text(), 'CHILD')]//following-sibling::div"));
			List<WebElement> infantDropdowns = driver.findElements(By.xpath("//p[contains(text(), 'INFANT')]//following-sibling::div"));
//			logObj.info(adultDropDowns.size());
//			logObj.info(childDropDowns.size());
//			logObj.info(infantDropdowns.size());
			if(adultDropDowns.size() > 0) {
				for(int i=0; i<=adultDropDowns.size() - 1;i++) {
					try{
						if(adults > 0) {
							adultDropDowns.get(i).click();
							Thread.sleep(2000);
						}else {
							break;
						}
						try {
							if(adults >= 4) {
								List<WebElement> childs = driver.findElements(By.xpath("//ul[@class='holD_drop_menu make_absolute']"));
								for(WebElement child:childs) {
									try {
										Actions actObj = new Actions(driver);
										actObj.moveToElement(child).build().perform();
										child.findElement(By.linkText("4")).click();
										
										break;
									}catch(Exception ex) {
										
									}
								}
								adults = adults - 4;
							}
							else if(adults!=0 && adults <3){
								List<WebElement> childs = driver.findElements(By.xpath("//ul[@class='holD_drop_menu make_absolute']"));
								for(WebElement child:childs) {
									try {
										Actions actObj = new Actions(driver);
										actObj.moveToElement(child).build().perform();
										child.findElement(By.linkText(Integer.toString(adults))).click();
										adults = 0;
										break;
									}catch(Exception ex) {
										
									}
								}
							}
						}catch(Exception ex) {
							logObj.error("Unable to select value from Adults Dropdown list" + i);
							ex.printStackTrace();
							return false;
						}
							
						
					}catch(Exception ex) {
						logObj.error("Unable to click on Adults '"+i+"th' drop down at index ");
						return false;
					}									
				}
				if(children > 0) {
					for(int i=0; i<=childDropDowns.size() - 1;i++) {
						try{
							if(children > 0) {
								childDropDowns.get(i).click();
								Thread.sleep(2000);
							}else {
								break;
							}
							try {
								if(children >= 3) {
									List<WebElement> childs = driver.findElements(By.xpath("//ul[@class='holD_drop_menu make_absolute']"));
									
									for(WebElement child:childs) {
										try {
											Actions actObj = new Actions(driver);
											actObj.moveToElement(child).build().perform();
											child.findElement(By.linkText("3")).click();
											
											break;
										}catch(Exception ex) {
											
										}
									}
									
									children = children - 3;
								}
								else if(children!=0 && children <3){
									List<WebElement> childs = driver.findElements(By.xpath("//ul[@class='holD_drop_menu make_absolute']"));
									for(WebElement child:childs) {
										try {
											Actions actObj = new Actions(driver);
											actObj.moveToElement(child).build().perform();
											child.findElement(By.linkText(Integer.toString(children))).click();
											children = 0;
											break;
										}catch(Exception ex) {
											
										}
									}
								}
							}catch(Exception ex) {
								logObj.error("Unable to select value from Children Dropdown list");
								ex.printStackTrace();
								return false;
							}
								
//							return true;
						}catch(Exception ex) {
							logObj.error("Unable to click on Children '"+i+"th' drop down at index ");
						}									
					}
				}
				
				if(infants > 0) {
					for(int i=0; i<=infantDropdowns.size() - 1;i++) {
						try{
							if(infants > 0) {
								infantDropdowns.get(i).click();
								Thread.sleep(2000);
							}else {
								break;
							}
							
							try {
								if(infants >= 0) {
									List<WebElement> childs = driver.findElements(By.xpath("//ul[@class='holD_drop_menu make_absolute']"));
									for(WebElement child:childs) {
										try {
											Actions actObj = new Actions(driver);
											actObj.moveToElement(child).build().perform();
											child.findElement(By.linkText("2")).click();
											break;
											
										}catch(Exception ex) {
											
										}
									}
									infants = infants - 2;
								}
								else if(infants!=0 && infants <=2){
									List<WebElement> childs = driver.findElements(By.xpath("//ul[@class='holD_drop_menu make_absolute']"));
									for(WebElement child:childs) {
										try {
											Actions actObj = new Actions(driver);
											actObj.moveToElement(child).build().perform();
											child.findElement(By.linkText(Integer.toString(infants))).click();
											infants = 0;
											break;
										}catch(Exception ex) {
											
										}
									}
								}
							}catch(Exception ex) {
								logObj.error("Unable to select value from Infants Dropdown list");
								ex.printStackTrace();
								return false;
							}							
						}catch(Exception ex) {
							logObj.error("Unable to click on Infants '"+i+"th' drop down at index ");
							ex.printStackTrace();
							return false;
						}									
					}
				}
			}else {
				logObj.error("There are no rooms added on the UI to selected no of Travellers");
				return false;
				
			}
		}catch(Exception ex) {
			logObj.error("Failed while Adding rooms and selecting travellers");
			ex.printStackTrace();
			return false;
		}
			
			
			return true;
	}

}
