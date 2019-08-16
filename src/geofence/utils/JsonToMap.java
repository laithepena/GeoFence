package geofence.utils;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import geofence.beans.Investigation;




public class JsonToMap {

	static Gson gson = new Gson();

	public static Map<String, Object> jsonToGeoFenceMap(Map<String, Object> m1) {

		Map<String,Object> geoFence_map = new HashMap<String,Object>();

		//Object gson;
		geoFence_map = (Map<String,Object>) gson.fromJson(m1.get("geoFence").toString(), geoFence_map.getClass());
		return geoFence_map;
	}



	public static Map<String, Object> jsonToInvestigationMap(String json) {
		Map<String,Object> investigation_map = new HashMap<String,Object>();
		investigation_map = (Map<String,Object>) gson.fromJson(json, investigation_map.getClass());
		return investigation_map;
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

	public static String CheckParamInListPojo(String response_string, String uri) throws IOException {
		
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
	JsonArray jsonArray1 = (JsonArray) jsonParser.parse(response_string.toString());

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
        	res1="\n  FAIL  - param-KEY="+pair.getKey()+" param-VALUE="+pair.getValue()+"  id ="+inv1.getId();
        	Count++;
        	break;
        }
        
        
        
       // it.remove(); // avoids a ConcurrentModificationException
    }
		

	}

	return res1=res1+"\n Total Mismatches ="+Count+" Total="+jsonArray1.size();
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

					res1=" \n ### FAIL "; //+ param.values()+" "+" doesnot exists in " +response_map.values() ;

					Count++; total++;
				}else {
					total++;
				}

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

		res1=res1+"\n Total Mismatches ="+Count+" Total="+total;

		System.out.println(res1);

		// System.out.println("POMPU ------"+Count);





		return res1;

	}


	public static String compareInvestigationMapsJsonStringAll(String json1, String json2) {

		Map<String, Object> m1=jsonToInvestigationMap( json1);

		Map<String, Object> g1=jsonToGeoFenceMap(m1);
		//-------------------------------------------------------------------
		Map<String, Object> m12=jsonToInvestigationMap( json2);

		Map<String, Object> g12=jsonToGeoFenceMap(m12);
		String result0="";
		if(m1.keySet().equals(m12.keySet())||g1.keySet().equals(g12.keySet())) {



			String result1=""; String result2=""; String result3=""; String result4="";String result5="";

			String result6="";String result7="";

			String result11=""; String result12=""; String result13=""; String result14="";

			if(m1.containsValue(m12.values())) {

			}else {
				result1=" FAIL id did not matched "+m1.values().toString();
			}

			/*
			 * if(m1.get("creatorAttUid").equals(m12.get("creatorAttUid"))) { //
			 * result2="creatorAttUid matched"; } else {
			 * result2=" FAIL creatorAttUid did not matched " +m1.get("creatorAttUid"); }
			 * if(m1.get("id").equals(m12.get("id"))) { // result1="id matched"; } else {
			 * result1=" FAIL id did not matched "+ m1.get("id"); }
			 * 
			 * if(m1.get("startDate").equals(m12.get("startDate"))) { //
			 * result3="startDate matched"; } else {
			 * result3="FAIL startDate did not matched "+m1.get("startDate"); }
			 * 
			 * if(m1.get("endDate").equals(m12.get("endDate"))) { //
			 * result4=" endDate matched"; } else {
			 * result4="FAIL endDate did not matched "+m1.get("endDate"); }
			 * 
			 * 
			 * if(m1.get("timeZone").equals(m12.get("timeZone"))) {
			 * //result5="FAIL timeZone matched"; } else {
			 * result5="FAIL timeZone did not matched "+m1.get("timeZone"); }
			 * 
			 * if(m1.get("market").equals(m12.get("market"))) { //
			 * result6="FAIL market matched"; } else {
			 * result6="FAIL market did not matched "+m1.get("market"); }
			 * 
			 * if(m1.get("submarket").equals(m12.get("submarket"))) { //
			 * result6="submarket matched"; } else {
			 * result6=" FAIL submarket did not matched "+m1.get("submarket"); }
			 * 
			 * if(m1.get("geoFenceType").equals(m12.get("geoFenceType"))) { //
			 * result7="geoFenceType matched"; } else {
			 * result7=" FAIL geoFenceType did not matched "+m1.get("geoFenceType"); }
			 * 
			 * 
			 * if(g1.get("startLat").equals(g12.get("startLat"))) { // result1="id matched";
			 * } else { result11="FAIL startLat did not matched "+g1.get("startLat"); }
			 * 
			 * if(g1.get("endLat").equals(g12.get("endLat"))) { // result1="id matched"; }
			 * else { result12="FAIL endLat did not matched "+g1.get("endLat"); }
			 * if(g1.get("startLng").equals(g12.get("startLng"))) { // result1="id matched";
			 * } else { result13="FAIL startLng did not matched "+g1.get("startLng"); }
			 * if(g1.get("endLng").equals(g12.get("endLng"))) { // result1="id matched"; }
			 * else { result14="FAIL endLng did not matched "+g1.get("endLng"); }
			 */

			return result0+result1+result2+result3+result4+result5+result6+result7+result11+result12+result13+result14;
		}
		else {
			return result0="keySet doesnot match for investigationCompare ";
		}


	}

	public static String compareInvestigationMapsJsonStringAllPojo(String json1, String json2) {



		Investigation inv1=	gson.fromJson(json1.trim(), Investigation.class);


		//Gson gson2 = new Gson(); 
		Investigation inv2=	gson.fromJson(json2.trim(), Investigation.class);



		String result0="";
		if(!inv1.equals(inv2)) {



			String result1=""; String result2=""; String result3=""; String result4="";String result5="";

			String result6="";String result7="";

			String result8=""; String result9=""; String result10=""; 


			if(inv1.getId().equals(inv2.getId())) {

			}else {
				result1=" , \n FAIL id did not matched "+inv1.getId()+" "+inv2.getId();
			}

			if(inv1.getCreatorAttUid().equals(inv2.getCreatorAttUid())) {

			}else {
				result2=" , \n FAIL CreatorAttUid did not matched "+inv1.getCreatorAttUid()+" "+inv2.getCreatorAttUid();
			}
  
			if(inv1.getMarket().equals(inv2.getMarket())) {

			}else {
				result3=" , \n FAIL Market did not matched "+inv1.getMarket()+" "+inv2.getMarket();
			}


			if(inv1.getSubmarket().equals(inv2.getSubmarket())) {

			}else {
				result4=" , \n FAIL Submarket did not matched "+inv1.getSubmarket()+" "+inv2.getSubmarket();
			}
			
			if(inv1.getTimeZone().equals(inv2.getTimeZone())) {

			}else {
				result5=" , \n FAIL TimeZone did not matched "+inv1.getTimeZone()+" "+inv1.getTimeZone();
			}
			
			if(inv1.getStartDate().equals(inv2.getStartDate())) {

			}else {
				result6=" , \n FAIL StartDate did not matched "+inv1.getStartDate()+" "+inv1.getStartDate();
			}
			
			if(inv1.getEndDate().equals(inv2.getEndDate())) {

			}else {
				result6=" , \n FAIL EndDate did not matched "+inv1.getEndDate()+" "+inv1.getEndDate();
			}
			
			if(inv1.getGeoFenceType().equals(inv2.getGeoFenceType())) {

			}else {
				result8=" , \n FAIL GeoFenceType did not matched "+inv1.getGeoFenceType()+" "+inv1.getGeoFenceType();
			}

			
			if(inv1.getGeoFence().equals(inv2.getGeoFence())) {

			}else {
				result9=" , \n FAIL GeoFence did not matched "+inv1.getGeoFence()+" "+inv1.getGeoFence();
			}
			
			if(inv1.getStatus().equals(inv2.getStatus())) {

			}else {
				result10=" , \nFAIL Status did not matched "+inv1.getStatus()+" "+inv1.getStatus();
			}

			return result0+result1+result2+result3+result4+result5+result6+result7+result8+result9+result10;
		}
		else {
			return result0+" PASS - all Key Val Matched between Req and Resp";
		}


	}

	public static String compareGeoFenceMapsJsonString(String json1, String json2) {


		Map<String, Object> m1=jsonToInvestigationMap( json1);

		Map<String, Object> g1=jsonToGeoFenceMap(m1);

		Map<String, Object> m12=jsonToInvestigationMap( json2);

		Map<String, Object> g12=jsonToGeoFenceMap(m12);

		String result0="";
		if(g1.keySet().equals(g12.keySet())) {

			String result1=""; String result2=""; String result3=""; String result4="";

			if(g1.get("startLat").equals(g12.get("startLat"))) {
				// result1="id matched";
			}
			else {
				result1="FAIL startLat did not matched";
			}

			if(g1.get("endLat").equals(g12.get("endLat"))) {
				// result1="id matched";
			}
			else {
				result2="FAIL endLat did not matched";
			}
			if(g1.get("startLng").equals(g12.get("startLng"))) {
				// result1="id matched";
			}
			else {
				result3="FAIL startLng did not matched";
			}
			if(g1.get("endLng").equals(g12.get("endLng"))) {
				// result1="id matched";
			}
			else {
				result4="FAIL endLng did not matched";
			}

			return result0+result1+result2+result3+result4;
		}
		else {
			return result0="keySet doesnot match for GeoFenceCompare ";
		}
	}


	public static String compareInvestigationMapsJsonString(String json1, String json2) {

		Map<String, Object> m1=jsonToInvestigationMap( json1);

		Map<String, Object> m12=jsonToInvestigationMap( json2);

		String result0="";
		if(m1.keySet().equals(m12.keySet())) {



			String result1=""; String result2=""; String result3=""; String result4="";String result5="";

			String result6="";String result7="";

			if(m1.get("id").equals(m12.get("id"))) {
				// result1="id matched";
			}
			else {
				result1="FAIL id did not matched";
			}

			if(m1.get("creatorAttUid").equals(m12.get("creatorAttUid"))) {
				// result2="creatorAttUid matched";
			}
			else {
				result2="FAIL creatorAttUid did not matched";
			}

			if(m1.get("startDate").equals(m12.get("startDate"))) {
				// result3="startDate matched";
			}
			else {
				result3="FAIL startDate did not matched";
			}

			if(m1.get("endDate").equals(m12.get("endDate"))) {
				// result4=" endDate matched";
			}
			else {
				result4="FAIL endDate did not matched";
			}


			if(m1.get("timeZone").equals(m12.get("timeZone"))) {
				//result5="FAIL timeZone matched";
			}
			else {
				result5="FAIL timeZone did not matched";
			}

			if(m1.get("market").equals(m12.get("market"))) {
				// result6="FAIL market matched";
			}
			else {
				result6="FAIL market did not matched";
			}

			if(m1.get("submarket").equals(m12.get("submarket"))) {
				// result6="submarket matched";
			}
			else {
				result6=" FAIL submarket did not matched";
			}

			if(m1.get("geoFenceType").equals(m12.get("geoFenceType"))) {
				// result7="geoFenceType matched";
			}
			else {
				result7=" FAIL geoFenceType did not matched";
			}

			return result0+result1+result2+result3+result4+result5+result6+result7;
		}
		else {
			return result0="keySet doesnot match for investigationCompare ";
		}


	}



	public static String compareGeoFenceMaps(Map<String, Object> g1, Map<String, Object> g12) {

		String result1=""; String result2=""; String result3=""; String result4="";

		if(g1.get("startLat").equals(g12.get("startLat"))) {
			// result1="id matched";
		}
		else {
			result1="FAIL startLat did not matched";
		}

		if(g1.get("endLat").equals(g12.get("endLat"))) {
			// result1="id matched";
		}
		else {
			result2="FAIL endLat did not matched";
		}
		if(g1.get("startLng").equals(g12.get("startLng"))) {
			// result1="id matched";
		}
		else {
			result3="FAIL startLng did not matched";
		}
		if(g1.get("endLng").equals(g12.get("endLng"))) {
			// result1="id matched";
		}
		else {
			result4="FAIL endLng did not matched";
		}

		return result1+result2+result3+result4;
	}



	public static String compareInvestigationMaps(Map<String, Object> m1, Map<String, Object> m12) {

		String result1=""; String result2=""; String result3=""; String result4="";String result5="";

		String result6="";String result7="";

		if(m1.get("id").equals(m12.get("id"))) {
			// result1="id matched";
		}
		else {
			result1="FAIL id did not matched";
		}

		if(m1.get("creatorAttUid").equals(m12.get("creatorAttUid"))) {
			// result2="creatorAttUid matched";
		}
		else {
			result2="FAIL creatorAttUid did not matched";
		}

		if(m1.get("startDate").equals(m12.get("startDate"))) {
			// result3="startDate matched";
		}
		else {
			result3="FAIL startDate did not matched";
		}

		if(m1.get("endDate").equals(m12.get("endDate"))) {
			// result4=" endDate matched";
		}
		else {
			result4="FAIL endDate did not matched";
		}


		if(m1.get("timeZone").equals(m12.get("timeZone"))) {
			//result5="FAIL timeZone matched";
		}
		else {
			result5="FAIL timeZone did not matched";
		}

		if(m1.get("market").equals(m12.get("market"))) {
			// result6="FAIL market matched";
		}
		else {
			result6="FAIL market did not matched";
		}

		if(m1.get("submarket").equals(m12.get("submarket"))) {
			// result6="submarket matched";
		}
		else {
			result6=" FAIL submarket did not matched";
		}

		if(m1.get("geoFenceType").equals(m12.get("geoFenceType"))) {
			// result7="geoFenceType matched";
		}
		else {
			result7=" FAIL geoFenceType did not matched";
		}

		return result1+result2+result3+result4+result5+result6+result7;
	}


	public static Map<String, String> paramToMap(String uri) throws IOException {
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
