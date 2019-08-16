package geofence.tests;

//import static org.testng.AssertJUnit.assertThat;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.core.Response;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.testng.log4testng.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


import geofence.beans.Investigation;
import geofence.beans.RectangleInvestigation;

//import com.sun.rowset.internal.Row;

import geofence.fixtures.Test1;
import geofence.handlers.AutomationPropertyHandler;
import geofence.handlers.HTTPrequestHandler;
import geofence.utils.UtilMethods;
import testng.utils.PoiWriter;
import testng.utils.StaticProvider;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;
//import io.restassured.response.Response;
import java.util.HashMap;
import java.util.Map;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;



public class TestSuiteDemo {
	//java.util.logging.ConsoleHandler.level  ALL;
		 static Map<String, String[]> testresultdata=null;
	
		 Gson gson = new Gson();
		 
		 private static Logger logger=Logger.getLogger(TestSuiteDemo.class);
		 
		 
	 @BeforeClass(alwaysRun = true)
		public void suiteSetUp() throws IOException {

		// StaticProvider
		  // workbook = new HSSFWorkbook();
		      //create a new work sheet
		   //    sheet = workbook.createSheet("Test Result");
	    	testresultdata = new LinkedHashMap<String, String[]>();
	
		}
	    
	    
	   @Test //Test method
	    (dataProvider="ReadData_Sheet1",dataProviderClass = StaticProvider.class) //It get values from ReadVariant function method
	
	    public void TestScenario1(Test1 t1) throws Exception
	    { // 5189178755366082251   string.Replace("\"", "");
	 
		 // String response =HTTPrequestHandler.sendGETRequest("http://geofence.ci.eng.k8s.iqidev.labs.att.com:30080/geofence/investigations/"+t1.getCol2().replace("\"", ""));
		
			 
	   // -----------------------------------------------------------------------------------------------------------------//
	    // the first column is the key of teh map - here only building the Map - writing to xls is done at AfterClass
		// -----------------------------------------------------------------------------------------------------------------//
	   // testresultdata.put(t1.getCol1(), new String[] { response, "NA", "NA", "NA" });
	   // testresultdata.put(t1.getCol1(), new String[] { "Test Step No31.", "Action909", "Expected Output", "Actual Output2" });
	    
		   io.restassured.response.Response response = UtilMethods.doGetRequest("http://geofence.ci.eng.k8s.iqidev.labs.att.com:30080/geofence/investigations/"+t1.getCol2().replace("\"", ""));
		   
		   
		   // Status Assertion
		   
		   Assert.assertEquals(response.getStatusCode() /*actual value*/, 200 /*expected value*/, "Correct status code returned");
		   
		  // System.out.println(t1.getCol2().replace("\"", ""));
		   
		  // System.out.println(response.getStatusCode());
		
		   
		  String id= t1.getCol2().replace("\"", "").toString();
		  
		 // System.out.println(Integer.parseInt(t1.getCol2().replace("\"", "")));
		   
		   Investigation inv2 = gson.fromJson(response.asString(), Investigation.class);
		   
		   // Assert.assertEquals(Float.parseFloat(t1.getCol2().replace("\"", "")) /*actual value*/, inv2.getId() /*expected value*/, "Investigation ID  Assertion returned");
		  logger.info(t1.getCol2().replace("\"", "")+" Logging.........");
		  System.out.println("OUTSIDE ----- "+response.asString());
		  if(response.toString().length()>0){
			  
			 
			  System.out.println(response.toString());
			    
			}
		  else {
			  System.out.println("NO RESPONSE");
		  }
		  
		 
		  // Investigation ID  Assertion  - comparing as float
		  
//Assert.assertTrue(t1.getCol2().replace("\"", "").equals(Float.toString(inv2.getId())));
		  
		//System.out.println(response.statusLine());
		  
		  testresultdata.put(t1.getCol1().toString(), new String[] { response.asString(), String.valueOf(inv2.getId() ), String.valueOf(inv2.getStatus()) ,"PASS" });
		  
		   
			   
		 Assert.assertEquals(Float.parseFloat(t1.getCol2().replace("\"", "")) /*actual value*/, inv2.getId() /*expected value*/, "Investigation ID  Assertion returned");
			   
			  
			   //############
		 
		 Gson gson = new Gson();
		 Investigation inv3 = gson.fromJson(response.toString(), Investigation.class);
		 
		// inv3.
			//Asserting that result of Norway is Oslo
			Assert.assertEquals(inv3.getId(), "10");
		  
	    }
	   
	 
	  

	  
	   	   
	   @AfterClass
	   public  void write1() throws IOException{
		   
		   Set<String> keyset = testresultdata.keySet();
		  
		   int r;
		  r=1; // PoiWriter.findRow("./input/TestResult.xls", "Data","TC", 0)+1;
		   
		   for(String ks :keyset) {
			   
			  // System.out.println("---------"+PoiWriter.getCellValue(AutomationPropertyHandler.getInstance().getValue("xlsFileName"), AutomationPropertyHandler.getInstance().getValue("xlsSheetName"), r, 0));
			   
			  
			   
			 // if(ks==PoiWriter.getCellValue(AutomationPropertyHandler.getInstance().getValue("xlsFileName"), AutomationPropertyHandler.getInstance().getValue("xlsSheetName"), r, 0)) {
			   
			   PoiWriter.appendDataToFile(AutomationPropertyHandler.getInstance().getValue("xlsFileName"), AutomationPropertyHandler.getInstance().getValue("xlsSheetName"), r, 4, (String) testresultdata.get(ks)[0]);
			   PoiWriter.appendDataToFile(AutomationPropertyHandler.getInstance().getValue("xlsFileName"), AutomationPropertyHandler.getInstance().getValue("xlsSheetName"), r, 5, (String) testresultdata.get(ks)[1]);
			   PoiWriter.appendDataToFile(AutomationPropertyHandler.getInstance().getValue("xlsFileName"), AutomationPropertyHandler.getInstance().getValue("xlsSheetName"), r, 6, (String) testresultdata.get(ks)[2]);
			   PoiWriter.appendDataToFile(AutomationPropertyHandler.getInstance().getValue("xlsFileName"), AutomationPropertyHandler.getInstance().getValue("xlsSheetName"), r, 7, (String) testresultdata.get(ks)[3]);
			   
			   r++;
			//  }
		   }
		   
		   
		   //PoiWriter.findRow(AutomationPropertyHandler.getInstance().getValue("xlsFileName"), AutomationPropertyHandler.getInstance().getValue("xlsSheetName"), "", 0);
		   
		 
	   }
	   
	   
	  
		    
		 
		  
}

  
  

