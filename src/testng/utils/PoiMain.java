package testng.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import geofence.handlers.AutomationPropertyHandler;

public class PoiMain {
	
	
	
	
	static Map<String, Object[]> testresultdata;
	
	public static void main(String[] args) throws IOException {
		
		System.out.println("---------"+PoiWriter.getCellValue(AutomationPropertyHandler.getInstance().getValue("xlsFileName"), AutomationPropertyHandler.getInstance().getValue("xlsSheetName"), 1, 3));
		  
		System.out.println("---------"+PoiWriter.getCellValue(AutomationPropertyHandler.getInstance().getValue("xlsFileName"), AutomationPropertyHandler.getInstance().getValue("xlsSheetName"), 2, 3));
		
		
		
		
		
	}

}
