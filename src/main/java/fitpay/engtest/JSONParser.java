package fitpay.engtest;

import java.io.*;
import org.json.*;

public class JSONParser {
	
	JSONObject jsonResponse = new JSONObject();
	String creditCardState = "";
	String deviceState = "";
	
	public JSONParser(String userId, String deviceState, String creditCardState) {
		jsonResponse.put("userId", userId);
		this.deviceState = deviceState;
		this.creditCardState = creditCardState;
	}
	
	public void parseDeviceInfo(String message) {
		JSONObject obj = new JSONObject(message);
		JSONArray deviceArray = obj.getJSONArray("results");
		JSONArray responseArray = new JSONArray();
		JSONObject tempObject = new JSONObject();
		for (int i = 0; i < deviceArray.length(); i++) {
			tempObject = new JSONObject();
			tempObject.put("deviceId", deviceArray.getJSONObject(i).getString("deviceIdentifier"));
			tempObject.put("state", deviceArray.getJSONObject(i).getString("state"));
			if (deviceState.equals("") || deviceArray.getJSONObject(i).getString("state").equals(deviceState)) {
				responseArray.put(tempObject);
			}
		}
		jsonResponse.put("devices", responseArray);
	}
	
	public void parseCreditCardInfo(String message) {
		JSONObject obj = new JSONObject(message);
		JSONArray deviceArray = obj.getJSONArray("results");
		JSONArray responseArray = new JSONArray();
		JSONObject tempObject = new JSONObject();
		for (int i = 0; i < deviceArray.length(); i++) {
			tempObject = new JSONObject();
			tempObject.put("creditCardId", deviceArray.getJSONObject(i).getString("creditCardId"));
			tempObject.put("state", deviceArray.getJSONObject(i).getString("state"));
			if (creditCardState.equals("") || deviceArray.getJSONObject(i).getString("state").equals(creditCardState)) {
				responseArray.put(tempObject);
			}
		}
		jsonResponse.put("creditCards", responseArray);
	}

}