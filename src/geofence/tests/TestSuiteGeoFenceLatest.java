package geofence.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.google.gson.Gson;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


import geofence.beans.Investigation;
import geofence.beans.RectangleInvestigation;
import geofence.fixtures.Test1;
import geofence.handlers.AutomationPropertyHandler;
import geofence.handlers.HTTPrequestHandler;
import geofence.utils.JsonToMap;
import geofence.utils.UtilMethods;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.internal.path.json.JSONAssertion;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import testng.utils.DataProviderArguments;
import testng.utils.FileDataProvider;
import testng.utils.PoiWriter;
import testng.utils.ProgramCopy;
import testng.utils.StaticProvider;

//############################################################################################
// enter the xls file location and sheetName in automation.properties
// data from and to xls file is read by 
// The testSuite TestSuiteCreateByID  maps to the sheetname GetByID
// response  columns are  - responseJson	responseStartDate	responseName	status
//#############################################################################################

public class TestSuiteGeoFenceLatest {
	// java.util.logging.ConsoleHandler.level ALL;
	static Map<String, String[]> testresultdata = null;

	// Gson gson = new Gson();

	@BeforeClass(alwaysRun = true)
	public void suiteSetUp() throws IOException {
		testresultdata = new LinkedHashMap<String, String[]>();
		
		ProgramCopy.newPth=ProgramCopy.funcCopy();

	}

	@Test(dataProvider = "getDataFromFile2", dataProviderClass = FileDataProvider.class,priority = 1) // It get values from ReadVariant
	@DataProviderArguments(value="get")
	// function method
	public void TestScenarioGet(Test1 t1) throws IOException {
		// RectangleInvestigation rectangle = new RectangleInvestigation();

		// UtilMethods.setStartDateInThePast( "2019-02-18 10:00 AM");

		// investigation.setStartDate(UtilMethods.tomorrow());

		System.out.println("col1 ==== " + t1.getCol1());

		System.out.println("col2 ==== " + t1.getCol2());

		System.out.println("col3 ==== " + t1.getCol3());

		System.out.println("col4 ==== " + t1.getCol4());

		System.out.println("######################");

		// Response response = UtilMethods.doPostRequest(t1.getCol2().toString(),
		// t1.getCol3());

		if (t1.getCol4().equals("get")) {



			//	case "get":
			// else if(t1.getCol4().equals("get")){
			System.out.println("-----This ----is -----GET");

			System.out.println(t1.getCol3() + t1.getCol2());

			io.restassured.response.Response response = UtilMethods
					.doGetRequest(t1.getCol3().toString().trim() + t1.getCol2().toString());

			if (response.statusLine().contains("200")) {

				
				String compareResult = JsonToMap.compareInvestigationMapsJsonStringAllPojo(t1.getCol5().toString(),
						response.asString());

				if (compareResult.contains("PASS") ) {
					// compareResult="PASS Key-val matched for InvestigationMap between Req/Resp
					// succeded";
					testresultdata.put(t1.getCol1().toString(),
							new String[] { response.asString(), "PASS", response.getStatusLine(),
									compareResult
					});
				} else {
					testresultdata.put(t1.getCol1().toString(), new String[] { response.asString(), "FAIL",
							response.getStatusLine(), compareResult });
					
					Assert.assertTrue(!compareResult.contains("FAIL"),"  TEST-ID="+t1.getCol1()+"  "+compareResult);

				}

			} else {
				testresultdata.put(t1.getCol1().toString(),
						new String[] { response.asString(), "FAIL", response.getStatusLine(), "NA" });
				
				Assert.assertTrue(!response.asString().contains("200"),response.asString());
				
			}

			//	break;
			// -------- GET ENDS here -------------//
			// }
		}

	}

	@Test(dataProvider = "getDataFromFile2", dataProviderClass = FileDataProvider.class,priority = 2) // It get values from ReadVariant
	@DataProviderArguments(value="getList")
	// function method
	public void TestScenarioGetList(Test1 t1) throws IOException {
		
		

			String uri = t1.getCol3().toString().trim();

			//System.out.println(" PPP ------ "+uri);

			//String uri="http://geofence.ci.eng.k8s.iqidev.labs.att.com:30080/geofence/investigations?market=North%20Texas&submarket=Dallas%20Ft.%20Worth";

			System.out.println(" PPP ------ "+uri);

			String[] ur=uri.split("\\?");

			Map<String, String> paramMap=JsonToMap.paramToMap(uri);

			RestAssured.baseURI =ur[0];

			

			RequestSpecification httpRequest2 =
					RestAssured.given().queryParams(paramMap); // httpRequest.param("market", "North Texas");
			//param("submarket","ALL"); Response
			Response response2=httpRequest2.request(Method.GET);
			
			String checkPresence="";
			
			if(!t1.getCol5().toString().trim().isEmpty()) {

			 checkPresence= JsonToMap.CheckParamInListPojoParam(response2.asString(), uri,t1.getCol5().toString().trim());
			}
			else {
				 checkPresence= JsonToMap.CheckParamInListPojo(response2.asString(), uri);
			}

			if(checkPresence.contains("FAIL")) {
				testresultdata.put(t1.getCol1().toString(),
						new String[] { response2.statusLine(), "FAIL", response2.getStatusLine(),
								checkPresence});
				Assert.assertTrue(!checkPresence.contains("FAIL"),"  TEST-ID="+t1.getCol1()+"  "+checkPresence);
				
				//Assert.assertT
				
			}else if(!checkPresence.contains("FAIL")) {
				testresultdata.put(t1.getCol1().toString(),
						new String[] { response2.statusLine(), "PASS", response2.getStatusLine(),
								checkPresence});
				
			}

	
	}


	@Test(dataProvider = "getDataFromFile2", dataProviderClass = FileDataProvider.class,priority = 0) // It get values from ReadVariant
	@DataProviderArguments(value="post")
	// function method
	public void TestScenarioPost(Test1 t1) throws IOException {
		// RectangleInvestigation rectangle = new RectangleInvestigation();

		// UtilMethods.setStartDateInThePast( "2019-02-18 10:00 AM");

		// investigation.setStartDate(UtilMethods.tomorrow());

		System.out.println("col1 ==== " + t1.getCol1());

		System.out.println("col2 ==== " + t1.getCol2());

		System.out.println("col3 ==== " + t1.getCol3());

		System.out.println("col4 ==== " + t1.getCol4());

		System.out.println("######################");

		// Response response = UtilMethods.doPostRequest(t1.getCol2().toString(),
		// t1.getCol3());

		//switch (t1.getCol4()) {

		//case "post":

			// if(t1.getCol4().equals("post")) {

			System.out.println("-----This ----is -----POST");

			RestAssured.baseURI = t1.getCol3().toString();
			RequestSpecification httpRequest = RestAssured.given();
			httpRequest.header("Content-Type", "application/json");

			httpRequest.body(t1.getCol2().toString());

			Response response1 = httpRequest.post("/investigations");

			if (response1.statusLine().contains("200")) {
				testresultdata.put(t1.getCol1().toString(),
						new String[] { response1.asString(), "PASS", response1.getStatusLine(), "NA" });
				

			} else {
				testresultdata.put(t1.getCol1().toString(),
						new String[] { response1.asString(), "FAIL", response1.getStatusLine(), "NA"});
				
				Assert.assertTrue(response1.getStatusLine().contains("FAIL"),"  TEST-ID="+t1.getCol1()+"  "+response1.getStatusLine());
			}

			//break;

			// }

			// -------- GET STARTS here -------------//



		//}

	}


	@AfterClass
	public void write1() throws IOException {

		Set<String> keyset = testresultdata.keySet();

		for (String ks : keyset) {

			int r = PoiWriter.findRow(ProgramCopy.newPth,
					AutomationPropertyHandler.getInstance().getValue("xlsSheetName"), ks, 0);

			PoiWriter.appendDataToFile(ProgramCopy.newPth,
					AutomationPropertyHandler.getInstance().getValue("xlsSheetName"), r, 5,
					(String) testresultdata.get(ks)[0]);
			PoiWriter.appendDataToFile(ProgramCopy.newPth,
					AutomationPropertyHandler.getInstance().getValue("xlsSheetName"), r, 6,
					(String) testresultdata.get(ks)[1]);

			PoiWriter.appendDataToFile(ProgramCopy.newPth,
					AutomationPropertyHandler.getInstance().getValue("xlsSheetName"), r, 7,
					(String) testresultdata.get(ks)[2]);

			PoiWriter.appendDataToFile(ProgramCopy.newPth,
					AutomationPropertyHandler.getInstance().getValue("xlsSheetName"), r, 8,
					(String) testresultdata.get(ks)[3]);

			//PoiWriter.appendDataToFile(AutomationPropertyHandler.getInstance().getValue("xlsFileName"),
			//		AutomationPropertyHandler.getInstance().getValue("xlsSheetName"), r, 9,
			//	(String) testresultdata.get(ks)[4]);
			//
			// PoiWriter.appendDataToFile(AutomationPropertyHandler.getInstance().getValue("xlsFileName"),
			// AutomationPropertyHandler.getInstance().getValue("xlsSheetName"), r, 6,
			// (String) testresultdata.get(ks)[2]);
			// PoiWriter.appendDataToFile(AutomationPropertyHandler.getInstance().getValue("xlsFileName"),
			// AutomationPropertyHandler.getInstance().getValue("xlsSheetName"), r, 7,
			// (String) testresultdata.get(ks)[3]);

			// PoiWriter.appendDataToFile(AutomationPropertyHandler.getInstance().getValue("xlsFileName"),
			// AutomationPropertyHandler.getInstance().getValue("xlsSheetName"), r, 8,
			// (String) testresultdata.get(ks)[4]);
			// PoiWriter.appendDataToFile(AutomationPropertyHandler.getInstance().getValue("xlsFileName"),
			// AutomationPropertyHandler.getInstance().getValue("xlsSheetName"), r, 9,
			// (String) testresultdata.get(ks)[5]);

		}

	}

}
