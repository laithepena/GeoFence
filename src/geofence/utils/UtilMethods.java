package geofence.utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import geofence.beans.Investigation;


public class UtilMethods  {


    public static Response doPostRequest(String investigation, String appURL) {
        RestAssured.defaultParser = Parser.JSON;
        return
                given().headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON)
                        .contentType(ContentType.JSON)
                        .body(investigation)
                        .post(appURL)
                        .then()
                        .extract()
                        .response();
    }

    public static Response doGetRequest(String endpoint) {
        RestAssured.defaultParser = Parser.JSON;
        return
                given().headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON)
                        .contentType(ContentType.JSON)
                        .when().get(endpoint)
                        .then()
                        .extract()
                        .response();
    }

    public static Response doDeleteRequest(String endpoint) {
        RestAssured.defaultParser = Parser.JSON;
        return
                given().headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON)
                        .contentType(ContentType.JSON)
                        .when().delete(endpoint)
                        .then()
                        .extract()
                        .response();
    }


    public static String today() {
        SimpleDateFormat formattedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm aaa");
        Calendar c = Calendar.getInstance();
        String today = (String)(formattedDate.format(c.getTime()));

        return today;
    }

    public static String tomorrow() {
        SimpleDateFormat formattedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm aaa");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 1); // number of days to add
        String tomorrow = (String)(formattedDate.format(c.getTime()));

        return tomorrow;
    }

    public static String dayAfterTomorrow() {
        SimpleDateFormat formattedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm aaa");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 2); // number of days to add
        String dayAfterTomorrow = (String)(formattedDate.format(c.getTime()));

        return dayAfterTomorrow;
    }

    public static String yesterday() {
        SimpleDateFormat formattedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm aaa");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, -1); // number of days to add
        String yesterday = (String)(formattedDate.format(c.getTime()));

        return yesterday;
    }

    public static String inTenDays() {
        SimpleDateFormat formattedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm aaa");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 10); // number of days to add
        String inTenDays = (String)(formattedDate.format(c.getTime()));

        return inTenDays;
    }


	/*
	 * public static void setStartDateInThePast(Investigation investigation){
	 * investigation.setInvestigationName("startDateInThePast");
	 * investigation.setCreatorAttUid("test");
	 * investigation.setStartDate(UtilMethods.yesterday());
	 * investigation.setEndDate(UtilMethods.tomorrow());
	 * investigation.setTimeZone("CST6CDT");
	 * investigation.setGeoFenceType("rectangle");
	 * investigation.setMarket("North Texas");
	 * investigation.setSubmarket("Dallas Ft. Worth"); }
	 */
	/*
	 * public static void setValidValues(Investigation investigation){
	 * investigation.setInvestigationName("startDateInThePast");
	 * investigation.setCreatorAttUid("test");
	 * investigation.setStartDate(UtilMethods.tomorrow());
	 * investigation.setEndDate(UtilMethods.dayAfterTomorrow());
	 * investigation.setTimeZone("CST6CDT");
	 * investigation.setGeoFenceType("rectangle");
	 * investigation.setMarket("North Texas");
	 * investigation.setSubmarket("Dallas Ft. Worth"); }
	 * 
	 * public static void setEndDateInTenDays(Investigation investigation){
	 * investigation.setInvestigationName("startDateInThePast");
	 * investigation.setCreatorAttUid("test");
	 * investigation.setStartDate(UtilMethods.tomorrow());
	 * investigation.setEndDate(UtilMethods.inTenDays());
	 * investigation.setTimeZone("CST6CDT");
	 * investigation.setGeoFenceType("rectangle");
	 * investigation.setMarket("North Texas");
	 * investigation.setSubmarket("Dallas Ft. Worth"); }
	 * 
	 * public static void deleteInvestigation(String id){ String endPoint = appURL +
	 * "/" + id; Response response = doDeleteRequest(endPoint); assertEquals(200,
	 * response.getStatusCode()); System.out.println("Investigation with ID: " + id
	 * + " deleted with success"); }
	 */

}
