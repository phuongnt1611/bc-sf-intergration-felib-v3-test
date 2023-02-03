package utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class APIHelper {
	public static String request(String httpMethod, String urlString, String authToken, String requestJSONString) {
		try {
			URL url = new URL(urlString);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod(httpMethod);
			connection.setRequestProperty("x-bc-sf-filter-auth", authToken);
			
			if (httpMethod != "GET") {
				connection.setRequestProperty("Content-Type", "application/json");
				connection.setDoOutput(true);
				try(OutputStream os = connection.getOutputStream()) {
				    byte[] input = requestJSONString.getBytes("utf-8");
				    os.write(input, 0, input.length);			
				}
			}
			int responseCode = connection.getResponseCode();		
			System.out.println(httpMethod + " Response Code :: " + responseCode);

			if (responseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader in = new BufferedReader(
					new InputStreamReader(connection.getInputStream())
				);
				String inputLine;
				StringBuffer response = new StringBuffer();

				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();

				return response.toString();
			} else {
				System.out.println(httpMethod + " request did not work.");
				return null;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String sendGET(String urlString, String authToken) {
		return request("GET", urlString, authToken, "");
	}
	
	public static String sendPOST(String urlString, String authToken, String requestJSONString) {
		return request("POST", urlString, authToken, requestJSONString);
	}
	
	public static String sendPUT(String urlString, String authToken, String requestJSONString) {
		return request("PUT", urlString, authToken, requestJSONString);
	}
}
