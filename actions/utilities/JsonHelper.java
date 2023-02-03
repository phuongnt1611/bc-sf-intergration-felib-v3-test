package utilities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import com.google.gson.*;

public class JsonHelper {
	public static JsonObject readJsonFile(String jsonFile) throws IOException {
		String jsonString = new String(Files.readAllBytes(Paths.get(jsonFile)));
		JsonObject jsonObj = JsonParser.parseString(jsonString).getAsJsonObject();
		
		return jsonObj;
	}
	
	public static JsonObject readJsonFile(String jsonFile, String keyPath) throws IOException {
		String jsonString = new String(Files.readAllBytes(Paths.get(jsonFile)));
		JsonObject jsonObj = JsonParser.parseString(jsonString).getAsJsonObject();
		String[] keys = keyPath.split("\\.");
		System.out.println(keys[0]);
		System.out.println(keys[1]);
		System.out.println(jsonObj);
		
		for (int i = 0; i < keys.length; i++) {
			jsonObj = jsonObj.getAsJsonObject(keys[i]);
			System.out.println(keys[i]);
			System.out.println(jsonObj);
		}
		
		return jsonObj;
	}
	
    public static void extendJsonObject(JsonObject leftObj, JsonObject rightObj)  {
        for (Map.Entry<String, JsonElement> rightEntry : rightObj.entrySet()) {
            String rightKey = rightEntry.getKey();
            JsonElement rightVal = rightEntry.getValue();
            
            if (leftObj.has(rightKey)) {
                JsonElement leftVal = leftObj.get(rightKey);
                
                if (leftVal.isJsonObject() && rightVal.isJsonObject()) {
                    extendJsonObject(leftVal.getAsJsonObject(), rightVal.getAsJsonObject());
                } else {
                	leftObj.add(rightKey, rightVal);
                }
            }
        }
    }
}