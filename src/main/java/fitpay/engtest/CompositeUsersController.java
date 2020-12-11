package fitpay.engtest;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;

import org.json.*;
import java.io.*;

@RestController
public class CompositeUsersController {
	
	@Autowired
	UserDataService userDataService;

	@RequestMapping(value = "/compositeUsers/{var}", method = RequestMethod.GET)
	public ResponseEntity<String> index(@PathVariable("var") String id, @RequestParam(required=false, name="creditCardState", defaultValue="") String creditCardState, 
		@RequestParam(required=false, name="deviceState", defaultValue="") String deviceState, @RequestHeader("Authorization") String token) {
		
		// RETRIEVE TOKEN FROM INPUT
		String[] splitString = token.split(" ");
		
		userDataService.setToken(splitString[1]);
		userDataService.setUserId(id);
		HTTPResults response = null;
		
		JSONParser jsonParser = new JSONParser(id, deviceState, creditCardState);
		
		// SET HEADERS
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set("Content-Type", "application/json");
		
		// RETRIEVE USER DEVICES
		try {
			response = userDataService.getUserDevices();
			if (response.responseCode != 200) {
				return ResponseEntity.status(response.responseCode)
					.headers(responseHeaders)
					.body(response.message);
			}
			jsonParser.parseDeviceInfo(response.message);
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		// RETRIEVE USER CREDIT CARDS
		try {
			response = userDataService.getUserCreditCards();
			if (response.responseCode != 200) {
				return ResponseEntity.status(response.responseCode)
					.headers(responseHeaders)
					.body(response.message);
			}
			jsonParser.parseCreditCardInfo(response.message);
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		// RETURN RESPONSE
		return ResponseEntity.ok()
			.headers(responseHeaders)
			.body(jsonParser.jsonResponse.toString());
	}

}