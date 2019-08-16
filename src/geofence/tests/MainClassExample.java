package geofence.tests;

import java.io.IOException;
import java.util.Iterator;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import geofence.beans.Investigation;
import geofence.handlers.AutomationPropertyHandler;
import geofence.handlers.HTTPrequestHandler;
import geofence.utils.UtilMethods;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import testng.utils.PoiWriter;

public class MainClassExample {
	
	public static void main(String[] args) throws IOException {
		//String response =HTTPrequestHandler.sendGETRequest("http://geofence.ci.eng.k8s.iqidev.labs.att.com:30080/geofence/investigations/5189178755366082251");
		
		//System.out.println(response.toString());
		
		// ObjectMapper mapper = new ObjectMapper();
		
		String s="{\n" + 
				"   \"name\":\"Sample investigation 1091\",\n" + 
				"   \"creatorAttUid\":\"ms159v\",\n" + 
				"   \"startDate\":\"2019-10-01 03:00 AM\",\n" + 
				"   \"endDate\":\"2019-10-02 03:00 AM\",\n" + 
				"   \"timeZone\":\"CST6CDT\",\n" + 
				"   \"geoFenceType\":\"rectangle\",\n" + 
				"   \"market\":\"North Texas\",\n" + 
				"   \"submarket\":\"Dallas Ft. Worth\",\n" + 
				"   \"geoFence\":{\n" + 
				"      \"startLat\":33.0703402480854,\n" + 
				"      \"endLat\":33.0473208450504,\n" + 
				"      \"startLng\":-117.000961303711,\n" + 
				"      \"endLng\":-116.957702636719\n" + 
				"   }\n" + 
				"}\n" + 
				"";
		
		 RestAssured.baseURI = "http://geofence.ci.eng.k8s.iqidev.labs.att.com:30080/geofence";
	        RequestSpecification httpRequest = RestAssured.given();
	        httpRequest.header("Content-Type", "application/json");
	        
	        httpRequest.body(s.toString());
	        
	        Response response_init = httpRequest.post("/investigations");
		
		//RestAssured.baseURI="http://geofence.ci.eng.k8s.iqidev.labs.att.com:30080/geofence/";
		
//Response  response_init=UtilMethods.doPostRequest(s, "investigations");


//System.out.println(response_init.print());
		
	        System.out.println(response_init.getStatusCode());
	        
	        
	        if(response_init.statusCode()==400) {
	        	System.out.println("YES");
	        }

		 
	//	String response= HTTPrequestHandler.send_POST_Request(s, "http://geofence.ci.eng.k8s.iqidev.labs.att.com:30080/geofence/investigations", "application/json", false);
				System.out.println(response_init.asString());
				//send_POST_Request(s, "http://geofence.ci.eng.k8s.iqidev.labs.att.com:30080/geofence/investigations");
		
		/*
		 * try { mapper.readValue(response.toString(), Investigation.class); } catch
		 * (JsonParseException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } catch (JsonMappingException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } catch (IOException e) { //
		 * TODO Auto-generated catch block e.printStackTrace(); }
		 */
		 
		/*
		 * Gson gson = new Gson(); Investigation inv2 = gson.fromJson(response,
		 * Investigation.class);
		 * 
		 * System.out.println(inv2.getGeoFenceType());
		 */
	}

}
