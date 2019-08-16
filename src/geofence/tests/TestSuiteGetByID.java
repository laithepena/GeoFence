package geofence.tests;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.google.gson.Gson;
import geofence.beans.Investigation;
import geofence.fixtures.Test1;
import geofence.handlers.AutomationPropertyHandler;
import geofence.handlers.HTTPrequestHandler;
import geofence.utils.UtilMethods;
import testng.utils.PoiWriter;
import testng.utils.StaticProvider;


//############################################################################################
// enter the xls file location and sheetName in automation.properties
// data from and to xls file is read by 
// The testSuite TestSuiteGetByID  maps to the sheetname GetByID
// response  columns are  - responseJson	responseStartDate	responseName	status
//#############################################################################################


public class TestSuiteGetByID {
	//java.util.logging.ConsoleHandler.level  ALL;
	static Map<String, String[]> testresultdata=null;

	Gson gson = new Gson();

	@BeforeClass(alwaysRun = true)
	public void suiteSetUp() throws IOException {
		testresultdata = new LinkedHashMap<String, String[]>();

	}

	@Test 
	(dataProvider="ReadData_Sheet1",dataProviderClass = StaticProvider.class) //It get values from ReadVariant function method

	public void TestScenario1(Test1 t1) throws Exception
	{ 
		System.out.println(t1.getCol3()+t1.getCol2().replace("\"", ""));
		
		io.restassured.response.Response response = UtilMethods.doGetRequest(t1.getCol3()+t1.getCol2().replace("\"", ""));
		
		//StringHTTPrequestHandler.send_GET_Request(URL, isCredentialRequired)
		
		Investigation inv2 = gson.fromJson(response.asString(), Investigation.class);

		Assert.assertEquals(response.getStatusCode() /*actual value*/, 200 /*expected value*/, "Correct status code returned");
	//int s=	response.getStatusCode();
		testresultdata.put(t1.getCol1().toString(), new String[] { response.asString(),  inv2.getStartDate(), String.valueOf(inv2.getName()) ,String.valueOf(response.getStatusCode()) });

		Assert.assertEquals(t1.getCol2().replace("\"", "") /*actual value*/, inv2.getId() /*expected value*/, "Investigation ID  Assertion returned");


	}

	@AfterClass
	public  void write1() throws IOException{

		Set<String> keyset = testresultdata.keySet();

		for(String ks :keyset) {

			int r= PoiWriter.findRow(AutomationPropertyHandler.getInstance().getValue("xlsFileName"), AutomationPropertyHandler.getInstance().getValue("xlsSheetName"), ks, 0);

			PoiWriter.appendDataToFile(AutomationPropertyHandler.getInstance().getValue("xlsFileName"), AutomationPropertyHandler.getInstance().getValue("xlsSheetName"), r, 4, (String) testresultdata.get(ks)[0]);
			PoiWriter.appendDataToFile(AutomationPropertyHandler.getInstance().getValue("xlsFileName"), AutomationPropertyHandler.getInstance().getValue("xlsSheetName"), r, 5, (String) testresultdata.get(ks)[1]);
			PoiWriter.appendDataToFile(AutomationPropertyHandler.getInstance().getValue("xlsFileName"), AutomationPropertyHandler.getInstance().getValue("xlsSheetName"), r, 6, (String) testresultdata.get(ks)[2]);
			PoiWriter.appendDataToFile(AutomationPropertyHandler.getInstance().getValue("xlsFileName"), AutomationPropertyHandler.getInstance().getValue("xlsSheetName"), r, 7, (String) testresultdata.get(ks)[3]);

		}

	}






}




