package geofence.tests;

//package geofence.tests;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonToken;

import geofence.beans.GeoFence;
import geofence.beans.Investigation;
import geofence.handlers.AutomationPropertyHandler;
import geofence.handlers.HTTPrequestHandler;
import geofence.utils.JsonToMap;
import geofence.utils.UtilMethods;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import testng.utils.PoiWriter;

public class CompareJsonMain {

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
		/*
		String uri1="http://geofence.ci.eng.k8s.iqidev.labs.att.com:30080/geofence/investigations/5251630235470774999";
		
		RestAssured.baseURI =uri1;
        RequestSpecification httpRequest1 =
				RestAssured.given(); // httpRequest.param("market", "North Texas");
		//param("submarket","ALL"); Response
		Response response1=httpRequest1.request(Method.GET);
		
		String uri2="http://geofence.ci.eng.k8s.iqidev.labs.att.com:30080/geofence/investigations/6647804776088610687";
		// 7773579756205837582

		RestAssured.baseURI =uri2;
        RequestSpecification httpRequest2 =
				RestAssured.given(); // httpRequest.param("market", "North Texas");
		//param("submarket","ALL"); Response
		Response response2=httpRequest2.request(Method.GET);
	        
		
		 * System.out.println(response2.asString());
		 * 
		 * JsonParser parser = new JsonParser(); JsonElement jsonTree =
		 * parser.parse(response2.asString());
		 
		
		Gson gson1 = new Gson(); 
		
		 * if(jsonTree.isJsonObject()){ JsonObject jsonObject =
		 * jsonTree.getAsJsonObject(); JsonElement f1 = jsonObject.get("geoFence");
		 * 
		 * System.out.println(f1.toString()); gson.fromJson(f1.toString(),
		 * GeoFence.class); }
		 
		
		Investigation inv1=	gson1.fromJson(response1.asString(), Investigation.class);
		
		
		//Gson gson2 = new Gson(); 
		Investigation inv2=	gson1.fromJson(response2.asString(), Investigation.class);
		
		System.out.println(inv1.getGeoFence());
		
		System.out.println(inv2.getGeoFence());
		
		//System.out.println(inv.getGeoFence().toString());
		
		if(inv1.getGeoFence().getStartLat().equals(inv2.getGeoFence().getStartLat())) {
			System.out.println("equal");
		}else {
			
		}
		
		*/
		// ################################
		
		
		String uri = "http://geofence.ci.eng.k8s.iqidev.labs.att.com:30080/geofence/investigations?market=North%20Texas&submarket=Dallas%20Ft.%20Worth&status=expired";
				//"http://geofence.ci.eng.k8s.iqidev.labs.att.com:30080/geofence/investigations?market=North%20Texas&submarket=Dallas%20Ft.%20Worth";

		//System.out.println(" PPP ------ "+uri);

		//String uri="http://geofence.ci.eng.k8s.iqidev.labs.att.com:30080/geofence/investigations?market=North%20Texas&submarket=Dallas%20Ft.%20Worth";

		System.out.println(" PPP ------ "+uri);

		String[] ur=uri.split("\\?");

		Map<String, String> paramMap=JsonToMap.paramToMap(uri);

		RestAssured.baseURI =ur[0];

		/*
		 * String[] ur=uri.split("\\?");
		 * 
		 * //Map<String, String> response_map=JsonToMap.paramToMap(uri); //
		 * param("market", "North Texas")
		 * 
		 * RestAssured.baseURI =ur[0] ;//+URLEncoder.encode(ur[1]);
		 * 
		 * RequestSpecification httpRequest = RestAssured.given().queryParams(param);
		 * //param("submarket","ALL"); Response Response
		 * response=httpRequest.request(Method.GET);
		 */

		RequestSpecification httpRequest22 =
				RestAssured.given().queryParams(paramMap); // httpRequest.param("market", "North Texas");
		//param("submarket","ALL"); Response
		Response response_string=httpRequest22.request(Method.GET);
		
		
		//////////////////////----------------------------
		
		// String response_string, String uri;
		uri = URLDecoder.decode(uri, "UTF-8");

		//System.out.println(uri);

		String[] arr1=   uri.split("\\?");
		// System.out.println(arr[0]);

		String[] arr=arr1[1].split("\\&");


		Map <String,String> param=new HashMap<>();

		for(String s1:arr) {
			//	 System.out.println("------ "+s);
			String[] arr_param =s1.split("\\=");
			// System.out.println("  +++++++ "+arr_param[0]+" "+arr_param[1]);

			// arr_param[1]=arr_param[1].replace("\\%20", " ");
			param.put( arr_param[0], arr_param[1]);

			//System.out.println(URLEncoder.encode(arr_param[1]));
		}

	//	Investigation inv1=	gson1.fromJson(response1.asString(), Investigation.class);

		JsonParser jsonParser = new JsonParser();
		JsonArray jsonArray1 = (JsonArray) jsonParser.parse(response_string.asString());

		int jsonarray_length1 = jsonArray1.size();

		String res1="";boolean flag=true;
		int Count=0; int total=0;
		
		Gson gson = new Gson();
		
		System.out.println(jsonArray1.size());
		
		for (int i = 0; i < jsonarray_length1; i++) {
			//JsonElement jelm = ;
			
			Investigation inv1=	gson.fromJson(jsonArray1.get(i).toString(), Investigation.class);
			
			System.out.println(inv1.getId());
		System.out.println(param.toString());
		
		System.out.println(param.values());
		
		
		Iterator it = param.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        System.out.println("   ------------- "+pair.getKey() + " = " + pair.getValue());
	        
	        if(!inv1.toString().contains(pair.getKey().toString())||!inv1.toString().contains(pair.getValue().toString())) {
	        	System.out.println("POMPU  YESYES ...........KEY...");
	        	res1="\n  FAIL param didnot matched with Response - param-KEY="+pair.getKey()+" param-VALUE="+pair.getValue();
	        	Count++;
	        	break;
	        }
	        
	        
	        
	       // it.remove(); // avoids a ConcurrentModificationException
	    }
			

		}

		res1=res1+"\n Total Mismatches ="+Count+" Total="+jsonArray1.size();

		System.out.println(res1);





	//	return res1;

		
		/*
		 * Investigation inv =
		 * 
		 * 
		 * 
		  
		 */
	
	
	
	}
}

// RestAssured.baseURI="http://geofence.ci.eng.k8s.iqidev.labs.att.com:30080/geofence/";

//Response  response_init=UtilMethods.doPostRequest(s, "investigations");

//System.out.println(response_init.print());

// String response= HTTPrequestHandler.send_POST_Request(s,
// "http://geofence.ci.eng.k8s.iqidev.labs.att.com:30080/geofence/investigations",
// "application/json", false);

// send_POST_Request(s,
// "http://geofence.ci.eng.k8s.iqidev.labs.att.com:30080/geofence/investigations");

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
