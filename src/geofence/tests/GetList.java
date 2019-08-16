package geofence.tests;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import static org.hamcrest.core.IsEqual.*;

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

public class GetList {
	
	
	public static void main(String[] args) throws IOException {
		//io.restassured.response.Response response = UtilMethods.doGetRequest("http://geofence.ci.eng.k8s.iqidev.labs.att.com:30080/geofence/investigations/52516302354707749997861");
		
	//	System.out.println(response.asString());
		
		//"http://geofence.ci.eng.k8s.iqidev.labs.att.com:30080/geofence/investigations?market=North%20Texas&submarket=Dallas%20Ft.%20Worth"
		
		 String string = "North Texas";
		 
         String encodedString = URLEncoder.encode(string, "UTF-8");

        // System.out.println("Encoded String: " + encodedString);
         
      //   String uri="http://geofence.ci.eng.k8s.iqidev.labs.att.com:30080/geofence/investigations?attId=ms159v";
         
        String uri="http://geofence.ci.eng.k8s.iqidev.labs.att.com:30080/geofence/investigations?market=North%20Texas&submarket=Dallas%20Ft.%20Worth";
         
        // String uri="http://geofence.ci.eng.k8s.iqidev.labs.att.com:30080/geofence/investigations?market=North";
         
		
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
		  
		 
		  String r=CheckParamInList(response.asString(),uri);
		  
		  System.out.println(r);
         
		

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
