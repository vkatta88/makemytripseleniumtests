package zenq.makemytrip.genericutils;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.google.common.io.Files;

public class GenericMethods {
	
public int getMonthNumber(String month) {
		
		if(month.equalsIgnoreCase("JANUARY") || month.equalsIgnoreCase("JAN")) {
			return 1;
		}else if(month.equalsIgnoreCase("FEBRUARY") || month.equalsIgnoreCase("FEB")) {
			return 2;
		}else if(month.equalsIgnoreCase("MARCH") || month.equalsIgnoreCase("MAR")) {
			return 3;
		}else if(month.equalsIgnoreCase("APRIL") || month.equalsIgnoreCase("APR")) {
			return 4;
		}else if(month.equalsIgnoreCase("MAY")) {
			return 5;
		}else if(month.equalsIgnoreCase("JUNE") || month.equalsIgnoreCase("JUN")) {
			return 6;
		}else if(month.equalsIgnoreCase("JULY") || month.equalsIgnoreCase("JUL")) {
			return 7;
		}else if(month.equalsIgnoreCase("AUGUST") || month.equalsIgnoreCase("AUG")) {
			return 8;
		}else if(month.equalsIgnoreCase("SEPTEMBER") || month.equalsIgnoreCase("SEP")) {
			return 9;
		}else if(month.equalsIgnoreCase("OCTOBER") || month.equalsIgnoreCase("OCT")) {
			return 10;
		}else if(month.equalsIgnoreCase("NOVEMBER") || month.equalsIgnoreCase("NOV")) {
			return 11;
		}else if(month.equalsIgnoreCase("DECEMBER") || month.equalsIgnoreCase("DEC")) {
			return 12;
		}else {
			return -1;
		}	
		
	}
	
	//this method return Month name in string in specific format which is require by the user like in MMM format or entire month name
	public String getMonthString(int num, String reqFormat) {
		
		switch(num) {
		case 0:
			if (reqFormat.equalsIgnoreCase("MMM")) {
				return "JAN";
			}else {
				return "January";
			}
		case 1:
			if (reqFormat.equalsIgnoreCase("MMM")) {
				return "FEB";
			}else {
				return "February";
			}
		case 2:
			if (reqFormat.equalsIgnoreCase("MMM")) {
				return "MAR";
			}else {
				return "March";
			}
			
		case 3:
			if (reqFormat.equalsIgnoreCase("MMM")) {
				return "APR";
			}else {
				return "April";
			}
			
		case 4:
			if (reqFormat.equalsIgnoreCase("MMM")) {
				return "MAY";
			}else {
				return "May";
			}
		case 5:
			if (reqFormat.equalsIgnoreCase("MMM")) {
				return "JUN";
			}else {
				return "June";
			}
		case 6:
			if (reqFormat.equalsIgnoreCase("MMM")) {
				return "JUL";
			}else {
				return "July";
			}
		case 7:
			if (reqFormat.equalsIgnoreCase("MMM")) {
				return "AUG";
			}else {
				return "August";
			}
		case 8:
			if (reqFormat.equalsIgnoreCase("MMM")) {
				return "SEP";
			}else {
				return "September";
			}
		case 9:
			if (reqFormat.equalsIgnoreCase("MMM")) {
				return "OCT";
			}else {
				return "October";
			}
		case 10:
			if (reqFormat.equalsIgnoreCase("MMM")) {
				return "NOV";
			}else {
				return "November";
			}
		case 11:
			if (reqFormat.equalsIgnoreCase("MMM")) {
				return "DEC";
			}else {
				return "December";
			}
		default :
			return null;
		}
		
	}
	
	public Date getFormattedDate(String strDate) {
		try {
			if(strDate.contains("/")) {
				strDate = strDate.replace("/", "-");
			}
			DateFormat sdf;
			String[] array = strDate.split("-");
			String day = array[0];
			String month = array[1];
			String year = array[2];
			if(month.length() == 2 || month.length() == 1 ) {
				sdf = new SimpleDateFormat("dd-MM-yyyy");
			}else if(month.length() == 3) {
				sdf = new SimpleDateFormat("dd-MMM-yyyy");
			}else {
				int monthIndex = getMonthNumber(month);
				strDate = day + "-" + "0"+ Integer.toString(monthIndex) + "-"+ year;
				sdf = new SimpleDateFormat("dd-MM-yyyy");
			}
	//		String number = "10";
	//		int result = Integer.parseInt(number);	
			// Convert String to Date in java
			Date today = sdf.parse(strDate);
			return today;
		}catch(Exception ex) {
			return null;
		}
//		System.out.println("Date is : " + today.toString());
//		System.out.println(today.getMonth());
	}
	
	public void takeSnapshot(WebDriver driver, String testName) throws Exception {
		try{
			String workingDir = System.getProperty("user.dir");
			TakesScreenshot ts = (TakesScreenshot)driver;
			Date curDate = new Date();                                            
			SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMYYYYhhmmss"); 
			String actdate = dateFormat.format(curDate); 
			String fileName = workingDir + "\\screenshots\\" + actdate + "_" + testName + ".png";
			File snap = ts.getScreenshotAs(OutputType.FILE);
			File actscreenshot = new File(fileName);
			Files.copy(snap, actscreenshot);
		}catch(Exception ex) {
			System.out.println("Unable to take snpshot for test script '" + testName + "', please check");
		}
	}
	
	public boolean deleteFilesInDirectory(String path) {
//		 String path="D:\test"; 
	    try{
	    	File file = new File(path);	 
	    	if(file.exists()) {
	    		boolean flag = false;
			    File[] files = file.listFiles(); 
			    if(files.length == 0) {
			    	flag = true;
			    	System.out.println("No FIles exists in the folder '" + path);
			    }
			    for (File f:files) 
			    {
			    	if (f.isFile() && f.exists()) 
			        { 
			    		f.delete();
			    		System.out.println("successfully deleted");
			    		flag = true;
			        }else{
			        	System.out.println("cant delete a file due to open or error");
			        } 
			    }
						
				if(flag) {
					System.out.println("Files deleted successfully from folder " + path);
					return true;
				}else {
					System.out.println("Files deleted successfully from folder " + path);
					return false;
				}
	    	}
	    	else {
	    		return true;
	    	}
		    
	    }catch(Exception ex) {
	    	System.out.println("Unable to deleted files fom Folder '" + path + "'" );
	    	return false;
	    }
			
	}
	

}
