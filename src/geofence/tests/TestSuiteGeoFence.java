package geofence.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.google.gson.Gson;
import geofence.beans.Investigation;
import geofence.beans.RectangleInvestigation;
import geofence.fixtures.Test1;
import geofence.handlers.AutomationPropertyHandler;
import geofence.handlers.HTTPrequestHandler;
import geofence.utils.UtilMethods;
import io.restassured.response.Response;
import testng.utils.PoiWriter;
import testng.utils.StaticProvider;

//############################################################################################
// enter the xls file location and sheetName in automation.properties
// data from and to xls file is read by 
// The testSuite TestSuiteCreateByID  maps to the sheetname GetByID
// response  columns are  - responseJson	responseStartDate	responseName	status
//#############################################################################################

public class TestSuiteGeoFence {
	// java.util.logging.ConsoleHandler.level ALL;
	static Map<String, String[]> testresultdata = null;

	Gson gson = new Gson();

	@BeforeClass(alwaysRun = true)
	public void suiteSetUp() throws IOException {
		testresultdata = new LinkedHashMap<String, String[]>();

	}

	

	@Test(dataProvider = "ReadData_Sheet1", dataProviderClass = StaticProvider.class) // It get values from ReadVariant
																						// function method
	public void TestScenarioPost(Test1 t1) {
		// RectangleInvestigation rectangle = new RectangleInvestigation();

		// UtilMethods.setStartDateInThePast( "2019-02-18 10:00 AM");

		// investigation.setStartDate(UtilMethods.tomorrow());

		System.out.println("col1 ==== "+t1.getCol1());

		System.out.println("col2 ==== "+t1.getCol2());
		
		System.out.println("col3 ==== "+t1.getCol3());
		
		System.out.println("col4 ==== "+t1.getCol4());
		
		System.out.println("######################");

		// Response response = UtilMethods.doPostRequest(t1.getCol2().toString(), t1.getCol3());
		
		  if(t1.getCol4().equals("post")) {
		  
		  System.out.println("-----This ----is -----POST");
		  
		  
		//  String response=HTTPrequestHandler.send_POST_Request(t1.getCol2().toString(),t1.getCol3().toString(), "application/json", false);
		  
		  //
		//  String investigation = "";
		Response  response_init=UtilMethods.doPostRequest(t1.getCol2().toString(), t1.getCol3().toString());
		
		String response=response_init.toString();
		
		System.out.println();
				//doPostRequest(investigation, t1.getCol3());
		  
		  System.out.println(response.toString());
		  
		  
		  Investigation inv2 = gson.fromJson(response.toString(), Investigation.class);
		  
		  System.out.println("+++++++++++++"+inv2.getId());
		  
		  
		  testresultdata.put(t1.getCol1().toString(), new String[] {response.toString() });
	
		  Assert.assertTrue(t1.getCol2().toString().contains(inv2.getStartDate()));
		  
		  Assert.assertTrue(t1.getCol2().toString().contains(inv2.getEndDate()));
		  
		  Assert.assertTrue(t1.getCol2().toString().contains(inv2.getMarket()));
		  
		  Assert.assertTrue(t1.getCol2().toString().contains(inv2.getSubmarket()));
		  
		  }else
		 if(t1.getCol4().equals("get")){
			System.out.println("-----This ----is -----GET");
			
			System.out.println(t1.getCol3()+t1.getCol2());
			
			io.restassured.response.Response response = UtilMethods.doGetRequest(t1.getCol3().toString()+t1.getCol2().toString());
			
			//StringHTTPrequestHandler.send_GET_Request(URL, isCredentialRequired)
			
			Investigation inv2 = gson.fromJson(response.asString(), Investigation.class);

			Assert.assertEquals(response.getStatusCode() /*actual value*/, 200 /*expected value*/, "Correct status code returned");
		//int s=	response.getStatusCode();
			testresultdata.put(t1.getCol1().toString(), new String[] { response.asString() });

			Assert.assertEquals(t1.getCol2().replace("\"", "") /*actual value*/, inv2.getId() /*expected value*/, "Investigation ID  Assertion returned");

		}
		
	}

	@AfterClass
	public void write1() throws IOException {

		Set<String> keyset = testresultdata.keySet();

		for(String ks :keyset) {

			int r= PoiWriter.findRow(AutomationPropertyHandler.getInstance().getValue("xlsFileName"), AutomationPropertyHandler.getInstance().getValue("xlsSheetName"), ks, 0);

			PoiWriter.appendDataToFile(AutomationPropertyHandler.getInstance().getValue("xlsFileName"), AutomationPropertyHandler.getInstance().getValue("xlsSheetName"), r, 5, (String) testresultdata.get(ks)[0]);
			//PoiWriter.appendDataToFile(AutomationPropertyHandler.getInstance().getValue("xlsFileName"), AutomationPropertyHandler.getInstance().getValue("xlsSheetName"), r, 5, (String) testresultdata.get(ks)[1]);
			//PoiWriter.appendDataToFile(AutomationPropertyHandler.getInstance().getValue("xlsFileName"), AutomationPropertyHandler.getInstance().getValue("xlsSheetName"), r, 6, (String) testresultdata.get(ks)[2]);
			//PoiWriter.appendDataToFile(AutomationPropertyHandler.getInstance().getValue("xlsFileName"), AutomationPropertyHandler.getInstance().getValue("xlsSheetName"), r, 7, (String) testresultdata.get(ks)[3]);

			
			//PoiWriter.appendDataToFile(AutomationPropertyHandler.getInstance().getValue("xlsFileName"), AutomationPropertyHandler.getInstance().getValue("xlsSheetName"), r, 8, (String) testresultdata.get(ks)[4]);
			//PoiWriter.appendDataToFile(AutomationPropertyHandler.getInstance().getValue("xlsFileName"), AutomationPropertyHandler.getInstance().getValue("xlsSheetName"), r, 9, (String) testresultdata.get(ks)[5]);

		}

	}

}
