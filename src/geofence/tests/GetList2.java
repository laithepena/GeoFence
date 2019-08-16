package geofence.tests;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import static org.hamcrest.core.IsEqual.*;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import geofence.beans.Investigation;
import geofence.utils.JsonToMap;
import geofence.utils.UtilMethods;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetList2 {


	public static void main(String[] args) throws IOException {
		//io.restassured.response.Response response = UtilMethods.doGetRequest("http://geofence.ci.eng.k8s.iqidev.labs.att.com:30080/geofence/investigations/52516302354707749997861");

		//	System.out.println(response.asString());

		//"http://geofence.ci.eng.k8s.iqidev.labs.att.com:30080/geofence/investigations?market=North%20Texas&submarket=Dallas%20Ft.%20Worth"

		String string = "North Texas";

		String encodedString = URLEncoder.encode(string, "UTF-8");

		// System.out.println("Encoded String: " + encodedString);

		   String uri="http://geofence.ci.eng.k8s.iqidev.labs.att.com:30080/geofence/investigations?attId=ms159v";

		// String uri="http://geofence.ci.eng.k8s.iqidev.labs.att.com:30080/geofence/investigations?market=North%20Texas&submarket=Dallas%20Ft.%20Worth";

		//String uri="http://geofence.ci.eng.k8s.iqidev.labs.att.com:30080/geofence/investigations?market=North%20Texas&submarket=Dallas%20Ft.%20Worth&status=expired";


		//  RestAssured.baseURI = uri; 

		/*
		 * RequestSpecification httpRequest = RestAssured.given(); //
		 * httpRequest.param("market", "North Texas"); //param("submarket","ALL");
		 * Response Response response=httpRequest.request(Method.GET);
		 */

		Map<String, String> param=JsonToMap.paramToMap(uri);
		//String uri = "";

		String[] ur=uri.split("\\?");

		//Map<String, String> response_map=JsonToMap.paramToMap(uri);  // param("market", "North Texas")

		RestAssured.baseURI =ur[0] ;//+URLEncoder.encode(ur[1]);

		RequestSpecification httpRequest =
				RestAssured.given().queryParams(param);
		//param("submarket","ALL"); Response
		Response response=httpRequest.request(Method.GET);

		//System.out.println(response.asString());

		System.out.println(httpRequest.get().asString());



		//Investigation Inv =response.jsonPath().getObject("inv", Investigation.class);

		// List<Investigation> allInvestigation = jsonPathEvaluator.getList("Investigations", Investigation.class);


		//String r=CheckParamInListPojoParam(response.asString(),uri,"market=North,status=Expoired");
		
		String r=CheckParamInListPojoParam(response.asString(),uri,"creatorAttUid=ms159v");

		System.out.println(r);



	}


	public static String CheckParamInListPojoParam(String response_string, String uri, String paramExpectedList) throws IOException {


		// // Expected Param Parsing Starts--------------

		String[] arrParamExpected=paramExpectedList.split(",");

		//System.out.println(arrParamExpected[0]);
		//System.out.println(arrParamExpected[1]);


		Map <String,String> Expectedparam=new HashMap<>();

		for(String s1:arrParamExpected) {
			//	 System.out.println("------ "+s);
			String[] arr_param =s1.split("\\=");
			// System.out.println("  +++++++ "+arr_param[0]+" "+arr_param[1]);

			// arr_param[1]=arr_param[1].replace("\\%20", " ");
			Expectedparam.put( arr_param[0], arr_param[1]);

			//System.out.println(URLEncoder.encode(arr_param[1]));
		}

		// classic way, loop a Map
		for (Map.Entry<String, String> entry : Expectedparam.entrySet()) {
			System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
		}

		//	System.out.println(Expectedparam.get("status"));


		// Expected Param END-----------------------------------------------

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
			if(Expectedparam.containsKey(arr_param[0])) {
				System.out.println(" arr_param[0] =   "+arr_param[0]+"     Expectedparam.get(arr_param[0])    "+Expectedparam.get(arr_param[0]));
				param.put( arr_param[0], Expectedparam.get(arr_param[0]));

			}
			else { param.put( arr_param[0], arr_param[1]);}

			//System.out.println(URLEncoder.encode(arr_param[1]));
		}


		for (Map.Entry<String, String> entry : param.entrySet()) {
			//System.out.println("param  Key : " + entry.getKey() + " param  Value : " + entry.getValue());
		}

		Investigation inv0=new Investigation();

		for (Map.Entry<String, String> entry : param.entrySet()) {
			System.out.println("param  Key : " + entry.getKey() + " param  Value : " + entry.getValue());

			if(entry.getKey().toLowerCase().matches("creatorAttUid")) {

				inv0.setCreatorAttUid(entry.getValue());
			}
			if(entry.getKey().toLowerCase().matches("market")) {

				inv0.setMarket(entry.getValue());
			}
			
			if(entry.getKey().toLowerCase().matches("submarket")) {

				inv0.setSubmarket(entry.getValue());
			}

			if(entry.getKey().toLowerCase().matches("status")) {

				inv0.setStatus(entry.getValue());
			}
 

		}

		System.out.println("inv0 =========== "+inv0.getMarket());
		
		System.out.println("inv0 =========== "+inv0.getStatus());

		//	Investigation inv1=	gson1.fromJson(response1.asString(), Investigation.class);

		JsonParser jsonParser = new JsonParser();
		JsonArray jsonArray1 = (JsonArray) jsonParser.parse(response_string.toString());

		int jsonarray_length1 = jsonArray1.size();

		String res1="";
		String res2="";
		String res3="";
		String res4="";
		boolean flag=true;
		int Count=0; int total=0;

		Gson gson = new Gson();

		System.out.println(jsonArray1.size());

		for (int i = 0; i < jsonarray_length1; i++) {
			//JsonElement jelm = ;

			Investigation inv1=	gson.fromJson(jsonArray1.get(i).toString(), Investigation.class);


			//Iterator it = param.entrySet().iterator();
			//while (it.hasNext()) {
			//	Map.Entry pair = (Map.Entry)it.next();
				//System.out.println("   -------------param key "+pair.getKey() + " = param val  =" + pair.getValue());

				/*
				 * if(!inv1.toString().contains(pair.getKey().toString())||!inv1.toString().
				 * contains(pair.getValue().toString())) { //
				 * System.out.println("POMPU  YESYES ...........KEY...");
				 * res1="\n  FAIL  - param-KEY="+pair.getKey()+" param-VALUE="+pair.getValue()
				 * +"  id ="+inv1.getId(); Count++; break; }
				 */

				//inv1.get

				System.out.println("START inv1 ------------ "+inv1.getCreatorAttUid()+ "  "+ inv1.getStatus()+"  "+inv1.getSubmarket());

				//System.out.println("START pair ------------"+pair.getValue().toString());

				if(  !inv1.getCreatorAttUid().toString().equalsIgnoreCase(inv0.getCreatorAttUid()) && inv0.getCreatorAttUid()!=null) {
					res1=" \n  FAIL  - attuid param-Value= "+inv0.getCreatorAttUid()+"  attuid param-Value="+inv0.getCreatorAttUid()+"  id ="+inv1.getId();
					Count++;
					//break;
				}

				if(!inv1.getMarket().toString().equalsIgnoreCase(inv0.getMarket()) && inv0.getMarket()!=null ) {
					res2=" \n  FAIL  - market -Value="+ inv1.getMarket() +"  market param-Value= "+inv0.getMarket()+"  id ="+inv1.getId();
					Count++;
					//break;
				}

				if(!inv1.getSubmarket().toString().equalsIgnoreCase(inv0.getSubmarket())  &&  inv0.getSubmarket()!=null) {
					res3=" \n  FAIL  - SubMarket param-Value="+inv1.getSubmarket()+"  SubMarket param-Value="+inv0.getSubmarket()+"  id ="+inv1.getId();
					Count++;
					//break;
				}
				
				if(!inv1.getStatus().toString().equalsIgnoreCase(inv0.getStatus()) && inv0.getStatus()!=null ) {
					res4=" \n  FAIL  - Status param-Value="+inv0.getStatus()+"   Status param-Value="+inv0.getStatus()+"  id ="+inv1.getId();
					Count++;
					//break;
				}



				// it.remove(); // avoids a ConcurrentModificationException
			//}


		}

		return res1+res2+res3+res4+"\n Total Mismatches ="+Count+" Total="+jsonArray1.size();
	}



	public static String CheckParamInList(String response_string, String uri) throws IOException {

		// Map<String, String> param  =JsonToMap.paramToMap(uri);

		uri = URLDecoder.decode(uri, "UTF-8");

		//System.out.println(uri);

		String[] arr1=   uri.split("\\?");
		// System.out.println(arr[0]);

		String[] arr=arr1[1].split("\\&");


		Map <String,String> param=new HashMap<>();

		for(String s:arr) {
			//	 System.out.println("------ "+s);
			String[] arr_param =s.split("\\=");
			// System.out.println("  +++++++ "+arr_param[0]+" "+arr_param[1]);

			// arr_param[1]=arr_param[1].replace("\\%20", " ");
			param.put( arr_param[0], arr_param[1]);

			//System.out.println(URLEncoder.encode(arr_param[1]));
		}



		JsonParser jsonParser = new JsonParser();
		JsonArray jsonArray1 = (JsonArray) jsonParser.parse(response_string);

		int jsonarray_length1 = jsonArray1.size();

		String res1="";boolean flag=true;
		int Count=0; int total=0;
		for (int i = 0; i < jsonarray_length1; i++) {
			JsonElement jelm = jsonArray1.get(i);
			Map<String, Object> response_map=JsonToMap.jsonToInvestigationMap( jelm.toString());
			//System.out.println(jelm.toString());
			//  for (Entry<String, String> ent : param.entrySet()) {

			//  System.out.println("Value = " + value);

			//  System.out.println(param.values().toString().trim());
			for(String s:param.values()) {
				if(!response_map.containsValue(s)) {
					// System.out.println(response_map.values());
					// System.out.println("======"+param.values());

					res1=res1+" \n ### FAIL "+ param.values()+" "+" doesnot exists in " +response_map.values() ;

					Count++;
				}
				total++;
			}

			/*
			 * for(String v:param.values()) { System.out.println("V = " + v);
			 * if(v.contains(value.toString())) { System.out.println("---- "+value);
			 * Count++; System.out.println(v);
			 * 
			 * } }
			 */



			// }

		}

		System.out.println(res1+"\n Total Mismatches ="+Count+" Total="+total);

		// System.out.println("POMPU ------"+Count);





		return res1;

	}

	private static Map<String, String> paramToMap(String uri) throws IOException {
		uri = URLDecoder.decode(uri, "UTF-8");

		//System.out.println(uri);

		String[] arr1=   uri.split("\\?");
		// System.out.println(arr[0]);

		String[] arr=arr1[1].split("\\&");



		Map <String,String> param=new HashMap<>();

		for(String s:arr) {
			//	 System.out.println("------ "+s);
			String[] arr_param =s.split("\\=");
			// System.out.println("  +++++++ "+arr_param[0]+" "+arr_param[1]);

			// arr_param[1]=arr_param[1].replace("\\%20", " ");
			param.put( arr_param[0], arr_param[1]);

			//System.out.println(URLEncoder.encode(arr_param[1]));
		}



		return param;
	}

}
