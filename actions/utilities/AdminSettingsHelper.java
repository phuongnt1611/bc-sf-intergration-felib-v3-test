package utilities;

import java.io.IOException;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class AdminSettingsHelper {	 
	 public static void updateAdminSettings(String urlString, String authToken, String updateSettingsJsonFile, String keyPath) {
		 // Get current settings JSON
		 String responseString = APIHelper.sendGET(urlString, authToken);
		 JsonObject responseObj = JsonParser.parseString(responseString).getAsJsonObject();
		 JsonObject settingsObj = responseObj.getAsJsonObject("data");

		try {
			// Update settings JSON
			JsonObject updateSettingsObj = JsonHelper.readJsonFile(updateSettingsJsonFile, keyPath);
			JsonHelper.extendJsonObject(settingsObj, updateSettingsObj);
			
			 // Send PUT request to update settings
			 JsonObject putDataObj = new JsonObject();
			 putDataObj.add("data", settingsObj);
			 JsonObject putBodyObj = new JsonObject();
			 putBodyObj.add("requestParams", putDataObj);
			 String putBodyString = putBodyObj.toString();
			 APIHelper.sendPUT(urlString, authToken, putBodyString);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
