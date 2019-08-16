package geofence.handlers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class AutomationPropertyHandler{

	   private static AutomationPropertyHandler instance = null;

	   private Properties props = null;

	   private AutomationPropertyHandler() throws IOException{
	         // Here you could read the file into props object
	     
		   
		   
	        		 
	        		 
	        this.props =  new Properties();
             FileInputStream in = new FileInputStream("./input/automation.properties");
             this.props.load(in);
	   }

	   public static synchronized AutomationPropertyHandler getInstance() throws IOException{
	       if (instance == null)
	           instance = new AutomationPropertyHandler();
	       return instance;
	   }

	   public String getValue(String propKey){
	       return this.props.getProperty(propKey);
	   }
	}
