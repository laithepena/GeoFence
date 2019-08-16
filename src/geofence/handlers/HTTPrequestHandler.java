package geofence.handlers;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.DeleteMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.http.HttpStatus;


public class HTTPrequestHandler {

	public static String send_POST_Request(String payload,String URL) {

		return send_POST_Request(payload,URL,"application/json");
	}

	public static String send_POST_Request(String payload,String URL, String contentType) {
		return send_POST_Request(payload, URL, contentType, false);
	}

	public static String send_POST_Request(String payload,String URL, String contentType, boolean isCredentialRequired) {

		HttpClientParams cparams = new HttpClientParams();
		HttpClient httpclient;

		PostMethod post = null;
		String responsejsonString="";
		try {	

			post = new PostMethod(URL);
			post.setRequestHeader("Content-type",contentType); 

			post.setRequestEntity(new InputStreamRequestEntity(
					new ByteArrayInputStream(payload.getBytes("UTF-8")),
					payload.length()));

			httpclient = new HttpClient(cparams);
			if (isCredentialRequired) {
				Credentials credentials = new UsernamePasswordCredentials("Administrator", "password");
				httpclient.getState().setCredentials(AuthScope.ANY, credentials);
			}
			int st_code = httpclient.executeMethod(post);
			System.out.println("st_code"+st_code);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ie) {
				System.out.println(ie.getMessage());
			}
			if (st_code == HttpStatus.SC_OK) {  
				responsejsonString = post.getResponseBodyAsString();
				System.out.println("jsonString"+responsejsonString);
			} else {
				System.out.println("Response code: " + post.getStatusCode() + 
						"\n Request:\n" + payload + 
						"\n Response:\n" + responsejsonString);

			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
			responsejsonString=null;

		} finally {
			post.releaseConnection();	
		}
		return responsejsonString;
	}



	public static String send_Delete_Request(String URL) {

		HttpClientParams cparams = new HttpClientParams();
		HttpClient httpclient;

		DeleteMethod delete=null;
		String responsejsonString="";
		try {	
			delete = new  DeleteMethod(URL);
			delete.setRequestHeader("Content-type","application/json"); 
			httpclient = new HttpClient(cparams);
			int st_code = httpclient.executeMethod(delete);
			System.out.println("st_code"+st_code);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ie) {
				System.out.println(ie.getMessage());
			}
			if (st_code == HttpStatus.SC_OK) {  
				responsejsonString = delete.getResponseBodyAsString();
				System.out.println("jsonString"+responsejsonString);
			} else {
				System.out.println("Response code: " + delete.getStatusCode() + 
						"\n Request URL:\n" + URL + 
						"\n Response:\n" + responsejsonString);

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());

		} finally {
			delete.releaseConnection();
		}
		return responsejsonString;
	}

	public static String send_GET_Request(String URL, boolean isCredentialRequired) {

		HttpClientParams cparams = new HttpClientParams();
		HttpClient httpclient;

		GetMethod getmethod=null;
		String responsejsonString="";
		try {	
			getmethod = new  GetMethod(URL);
			getmethod.setRequestHeader("Content-type","application/json"); 
			httpclient = new HttpClient(cparams);
			if (isCredentialRequired) {
				Credentials credentials = new UsernamePasswordCredentials("Administrator", "password");
				httpclient.getState().setCredentials(AuthScope.ANY, credentials);
			}

			int st_code = httpclient.executeMethod(getmethod);	
			/*try {
Thread.sleep(5000);
} catch (InterruptedException ie) {
System.out.println(ie.getMessage());
}*/
			responsejsonString = getmethod.getResponseBodyAsString();

			/*if (st_code == HttpStatus.SC_OK) {  
responsejsonString = getmethod.getResponseBodyAsString();
//System.out.println("jsonString"+responsejsonString);
} else {
System.out.println("Response code: " + getmethod.getStatusCode() + 
"\n Request URL:\n" + URL + 
"\n Response:\n" + responsejsonString);

}*/
		} catch (Exception e) {
			System.out.println(e.getMessage());
			responsejsonString=e.getMessage();

		} finally {
			getmethod.releaseConnection();
		}
		return responsejsonString;
	}

	public static String sendGETRequest(String url) {

		StringBuffer response = new StringBuffer();

		URL obj ;
		HttpURLConnection con = null;
		try{
			obj = new URL(url);
			con = (HttpURLConnection) obj.openConnection();

			// optional default is GET
			con.setRequestMethod("GET");

			//add request header
			//con.setRequestProperty("User-Agent", USER_AGENT);

			int responseCode = con.getResponseCode();
			System.out.println("Response Code : " + responseCode);

			BufferedReader in = new BufferedReader(
					new InputStreamReader(con.getInputStream()));
			String inputLine;


			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			response= response.append(e.getMessage());

		} finally {
			con.disconnect();
		}
		return response.toString();
	}
}
