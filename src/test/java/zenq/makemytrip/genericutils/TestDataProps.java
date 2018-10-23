package zenq.makemytrip.genericutils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class TestDataProps {
	Properties propObj;
	String workingDir = System.getProperty("user.dir");
	Logger logObj = Logger.getLogger("TestDataProps");
	public Properties getProps(){		
		try{
			File file = new File(workingDir + "\\testdata\\configs\\testdata.properties");
			FileInputStream fis = new FileInputStream(file);		
			propObj = new Properties();
			propObj.load(fis);
			return propObj;
		}catch(Exception ex) {
			logObj.error("Unable to get test data from props file");
			ex.printStackTrace();
			return null;
		}
	}
}
