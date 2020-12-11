package fitpay.engtest;

import java.net.*;
import java.io.*;

import org.springframework.stereotype.Service;

@Service
public class UserDataService {

	public String baseURL = "https://api.qa.fitpay.ninja/users/";
	public String token = "";
	public String userId = "";
	
	public void setToken(String token) {
		this.token = token;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	private String retrieveResults(InputStream content) throws IOException {
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(content));
		
		StringBuilder response = new StringBuilder();
		String inputLine;
		while ((inputLine = reader.readLine()) != null) {
			response.append(inputLine);
		}
		reader.close();
		return response.toString();
	}
	
	public HTTPResults getUserDevices() throws IOException {
		URL url = new URL(baseURL + userId + "/devices");

		HttpURLConnection connection = (HttpURLConnection) url
				.openConnection();
		connection.setRequestMethod("GET");
		connection.setDoOutput(true);
		connection.setRequestProperty("Authorization", "Bearer " + token);
		int responseCode = connection.getResponseCode();
		if (responseCode != 200) {
			InputStream errorStream = (InputStream) connection.getErrorStream();
			HTTPResults returnObject = new HTTPResults(responseCode, retrieveResults(errorStream));
			return returnObject;
		}
		InputStream content = (InputStream) connection.getInputStream();
		HTTPResults returnObject = new HTTPResults(responseCode, retrieveResults(content));
		return returnObject;
	}
	
	public HTTPResults getUserCreditCards() throws IOException {
		URL url = new URL(baseURL + userId + "/creditCards");

		HttpURLConnection connection = (HttpURLConnection) url
				.openConnection();
		connection.setRequestMethod("GET");
		connection.setDoOutput(true);
		connection.setRequestProperty("Authorization", "Bearer " + token);
		int responseCode = connection.getResponseCode();
		if (responseCode != 200) {
			InputStream errorStream = (InputStream) connection.getErrorStream();
			HTTPResults returnObject = new HTTPResults(responseCode, retrieveResults(errorStream));
			return returnObject;
		}
		InputStream content = (InputStream) connection.getInputStream();
		HTTPResults returnObject = new HTTPResults(responseCode, retrieveResults(content));
		return returnObject;
	}

}