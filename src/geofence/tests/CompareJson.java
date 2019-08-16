package geofence.tests;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import geofence.beans.Investigation;

public class CompareJson {
	static Gson gson = new Gson();
	public static void main(String[] args) {
		
		String json="{\n" + 
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
		
		 
	}
	
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

public static String compareGeoFenceMapsJsonStrig(String json1, String json2) {
	
	
Map<String, Object> m1=jsonToInvestigationMap( json1);

Map<String, Object> g1=jsonToGeoFenceMap(m1);
	
	Map<String, Object> m12=jsonToInvestigationMap( json2);
	
	Map<String, Object> g12=jsonToGeoFenceMap(m12);
	
	
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


public static String compareInvestigationMapsJsonString(String json1, String json2) {
	
	Map<String, Object> m1=jsonToInvestigationMap( json1);
	
	Map<String, Object> m12=jsonToInvestigationMap( json2);
	
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

	
	
}
