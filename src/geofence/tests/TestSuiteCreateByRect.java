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

public class TestSuiteCreateByRect {
	// java.util.logging.ConsoleHandler.level ALL;
	static Map<String, String[]> testresultdata = null;

	Gson gson = new Gson();

	@BeforeClass(alwaysRun = true)
	public void suiteSetUp() throws IOException {
		testresultdata = new LinkedHashMap<String, String[]>();

	}

	// @Test
	// (dataProvider="ReadData_Sheet1",dataProviderClass = StaticProvider.class)
	// //It get values from ReadVariant function method

	/*
	 * public void TestScenario1(Test1 t1) throws Exception {
	 * System.out.println(t1.getCol3()+t1.getCol2().replace("\"", ""));
	 * 
	 * io.restassured.response.Response response =
	 * UtilMethods.doGetRequest(t1.getCol3()+t1.getCol2().replace("\"", ""));
	 * Investigation inv2 = gson.fromJson(response.asString(), Investigation.class);
	 * 
	 * Assert.assertEquals(response.getStatusCode() actual value, 200 expected
	 * value, "Correct status code returned");
	 * 
	 * testresultdata.put(t1.getCol1().toString(), new String[] {
	 * response.asString(), inv2.getStartDate(), String.valueOf(inv2.getName())
	 * ,"PASS" });
	 * 
	 * Assert.assertEquals(Float.parseFloat(t1.getCol2().replace("\"", "")) actual
	 * value, inv2.getId() expected value, "Investigation ID  Assertion returned");
	 * 
	 * 
	 * }
	 */

	@Test(dataProvider = "ReadData_Sheet1", dataProviderClass = StaticProvider.class) // It get values from ReadVariant
																						// function method
	public void TestScenarioPost(Test1 t1) {
		// RectangleInvestigation rectangle = new RectangleInvestigation();

		// UtilMethods.setStartDateInThePast( "2019-02-18 10:00 AM");

		// investigation.setStartDate(UtilMethods.tomorrow());

		System.out.println(t1.getCol1());

		System.out.println(t1.getCol2());

		// Response response = UtilMethods.doPostRequest(t1.getCol2().toString(), t1.getCol3());
		 
	
		 
		 String response= HTTPrequestHandler.send_POST_Request(t1.getCol2().toString(), "http://geofence.ci.eng.k8s.iqidev.labs.att.com:30080/geofence/investigations", "application/json", false);
		
		//UtilMethods.doPostRequest(investigation, appURL)
		 
		 System.out.println(response.toString());
		 
		 
		 Investigation inv2 = gson.fromJson(response.toString(), Investigation.class);
		 
		 System.out.println("+++++++++++++"+inv2.getId());
		 
		 
		 testresultdata.put(t1.getCol1().toString(), new String[] { response.toString(), String.valueOf(inv2.getId()), inv2.getStartDate(),inv2.getEndDate(), inv2.getMarket(), inv2.getSubmarket() });
		 
		
		 
		 Assert.assertTrue(t1.getCol2().toString().contains(inv2.getStartDate()));
		 
		 Assert.assertTrue(t1.getCol2().toString().contains(inv2.getEndDate()));
		 
		 Assert.assertTrue(t1.getCol2().toString().contains(inv2.getMarket()));
		 
		 Assert.assertTrue(t1.getCol2().toString().contains(inv2.getSubmarket()));
		 
		// System.out.println(response.asString());
		// assertEquals(400, response.getStatusCode());
		// assertTrue(response.asString().contains("startDate cannot be in the past!"));
	}

	@AfterClass
	public void write1() throws IOException {

		Set<String> keyset = testresultdata.keySet();

		for(String ks :keyset) {

			int r= PoiWriter.findRow(AutomationPropertyHandler.getInstance().getValue("xlsFileName"), AutomationPropertyHandler.getInstance().getValue("xlsSheetName"), ks, 0);

			PoiWriter.appendDataToFile(AutomationPropertyHandler.getInstance().getValue("xlsFileName"), AutomationPropertyHandler.getInstance().getValue("xlsSheetName"), r, 4, (String) testresultdata.get(ks)[0]);
			PoiWriter.appendDataToFile(AutomationPropertyHandler.getInstance().getValue("xlsFileName"), AutomationPropertyHandler.getInstance().getValue("xlsSheetName"), r, 5, (String) testresultdata.get(ks)[1]);
			PoiWriter.appendDataToFile(AutomationPropertyHandler.getInstance().getValue("xlsFileName"), AutomationPropertyHandler.getInstance().getValue("xlsSheetName"), r, 6, (String) testresultdata.get(ks)[2]);
			PoiWriter.appendDataToFile(AutomationPropertyHandler.getInstance().getValue("xlsFileName"), AutomationPropertyHandler.getInstance().getValue("xlsSheetName"), r, 7, (String) testresultdata.get(ks)[3]);

			
			PoiWriter.appendDataToFile(AutomationPropertyHandler.getInstance().getValue("xlsFileName"), AutomationPropertyHandler.getInstance().getValue("xlsSheetName"), r, 8, (String) testresultdata.get(ks)[4]);
			PoiWriter.appendDataToFile(AutomationPropertyHandler.getInstance().getValue("xlsFileName"), AutomationPropertyHandler.getInstance().getValue("xlsSheetName"), r, 9, (String) testresultdata.get(ks)[5]);

		}

	}

}
