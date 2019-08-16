package testng.utils;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import geofence.handlers.AutomationPropertyHandler;

public class ProgramCopy {
	/*
	 * public static void main(String[] args) throws IOException {
	 * 
	 * 
	 * 
	 * DateTimeFormatter dtf = DateTimeFormatter.ofPattern("_yyyy_MM_dd_HH_mm_ss");
	 * LocalDateTime now = LocalDateTime.now(); System.out.println(dtf.format(now));
	 * 
	 * funcCopy();
	 * 
	 * 
	 * 
	 * 
	 * }
	 */
	 public static String newPth;
	
    public static String funcCopy() throws IOException {
    	FileSystem system = FileSystems.getDefault();
    	
    	//System.out.println("ppp "+AutomationPropertyHandler.getInstance().getValue("xlsFileName"));
    	
    	 DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss"); 
    	 LocalDateTime now = LocalDateTime.now(); 
        Path original = system.getPath(AutomationPropertyHandler.getInstance().getValue("xlsFileName"));
        
        
      //  AutomationPropertyHandler.getInstance().getValue("xlsFileName").split("\\.");
        
        
        
        String arr[]=AutomationPropertyHandler.getInstance().getValue("xlsFileName").split(".xls");
        
        System.out.println(arr[0]);
       // System.out.println(arr[1]);
        
        Path target = system.getPath(arr[0]+"_"+dtf.format(now)+".xls");
        
        
       // System.out.println(" -------- "+target.toString());
        
     //   System.out.println(original.toString());
        
      //  System.out.println(target.toString());

        try {
            // Throws an exception if the original file is not found.
            Files.copy(original, target, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ex) {
            System.out.println("ERROR");
        }
		return target.toString();
    }
		
	}
	
    
