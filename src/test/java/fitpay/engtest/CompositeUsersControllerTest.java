package fitpay.engtest;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import org.junit.jupiter.api.Test;
import org.junit.Assert;

import org.mockito.Mockito;

public class CompositeUsersControllerTest {
	
	@Test
	public void getCompositeUsersUnfiltered() throws Exception {
		CompositeUsersController controller = new CompositeUsersController();
		
		String expectedDeviceResultsTruncated = "{\"results\":[{\"deviceIdentifier\":\"7e987d27-6bbd-4b9b-a8dd-6e21634d47fc\",\"state\":\"INITIALIZED\"},{\"deviceIdentifier\":\"8f822640-4ad3-407d-9162-bc10a75ef7af\",\"state\":\"INITIALIZED\"}]}";
		String expectedCreditCardResultsTruncated = "{\"results\":[{\"creditCardId\":\"ac9e9721-e2fd-47dc-a9e7-594a94106ef1\",\"state\":\"ERROR\"}]}";;
		
		controller.userDataService = Mockito.mock(UserDataService.class);
		Mockito.when(controller.userDataService.getUserDevices()).thenReturn(new HTTPResults(200, expectedDeviceResultsTruncated));
		Mockito.when(controller.userDataService.getUserCreditCards()).thenReturn(new HTTPResults(200, expectedCreditCardResultsTruncated));
				
		String testUser = "f7a4d560-5309-44b2-94ab-02e1d831085f";
		String testToken = "test token";
		String deviceState = "";
		String creditCardState = "";
		
		ResponseEntity<String> response = controller.index(testUser, creditCardState, deviceState, testToken);
		String resultString = response.getBody();
		
		Mockito.verify(controller.userDataService).setUserId(testUser);
		Mockito.verify(controller.userDataService).setToken(testToken.split(" ")[1]);

		String nonFilteredResult = "{\"creditCards\":[{\"creditCardId\":\"ac9e9721-e2fd-47dc-a9e7-594a94106ef1\",\"state\":\"ERROR\"}],\"devices\":[{\"state\":\"INITIALIZED\",\"deviceId\":\"7e987d27-6bbd-4b9b-a8dd-6e21634d47fc\"},{\"state\":\"INITIALIZED\",\"deviceId\":\"8f822640-4ad3-407d-9162-bc10a75ef7af\"}],\"userId\":\"f7a4d560-5309-44b2-94ab-02e1d831085f\"}";
		Assert.assertEquals(resultString, nonFilteredResult);
				
	}
	
	@Test
	public void getCompositeUsersDeviceFiltered() throws Exception {
		CompositeUsersController controller = new CompositeUsersController();
		
		String expectedDeviceResultsTruncated = "{\"results\":[{\"deviceIdentifier\":\"7e987d27-6bbd-4b9b-a8dd-6e21634d47fc\",\"state\":\"INITIALIZED\"},{\"deviceIdentifier\":\"8f822640-4ad3-407d-9162-bc10a75ef7af\",\"state\":\"INITIALIZED\"}]}";
		String expectedCreditCardResultsTruncated = "{\"results\":[{\"creditCardId\":\"ac9e9721-e2fd-47dc-a9e7-594a94106ef1\",\"state\":\"ERROR\"}]}";;
		
		controller.userDataService = Mockito.mock(UserDataService.class);
		Mockito.when(controller.userDataService.getUserDevices()).thenReturn(new HTTPResults(200, expectedDeviceResultsTruncated));
		Mockito.when(controller.userDataService.getUserCreditCards()).thenReturn(new HTTPResults(200, expectedCreditCardResultsTruncated));
				
		String testUser = "f7a4d560-5309-44b2-94ab-02e1d831085f";
		String testToken = "test token";
		String deviceState = "ERROR";
		String creditCardState = "";
		
		ResponseEntity<String> response = controller.index(testUser, creditCardState, deviceState, testToken);
		String resultString = response.getBody();
		
		String deviceFilteredResults = "{\"creditCards\":[{\"creditCardId\":\"ac9e9721-e2fd-47dc-a9e7-594a94106ef1\",\"state\":\"ERROR\"}],\"devices\":[],\"userId\":\"f7a4d560-5309-44b2-94ab-02e1d831085f\"}";
		Assert.assertEquals(resultString, deviceFilteredResults);
				
	}
	
	@Test
	public void getCompositeUsersCreditCardFiltered() throws Exception {
		CompositeUsersController controller = new CompositeUsersController();
		
		String expectedDeviceResultsTruncated = "{\"results\":[{\"deviceIdentifier\":\"7e987d27-6bbd-4b9b-a8dd-6e21634d47fc\",\"state\":\"INITIALIZED\"},{\"deviceIdentifier\":\"8f822640-4ad3-407d-9162-bc10a75ef7af\",\"state\":\"INITIALIZED\"}]}";
		String expectedCreditCardResultsTruncated = "{\"results\":[{\"creditCardId\":\"ac9e9721-e2fd-47dc-a9e7-594a94106ef1\",\"state\":\"ERROR\"}]}";;
		
		controller.userDataService = Mockito.mock(UserDataService.class);
		Mockito.when(controller.userDataService.getUserDevices()).thenReturn(new HTTPResults(200, expectedDeviceResultsTruncated));
		Mockito.when(controller.userDataService.getUserCreditCards()).thenReturn(new HTTPResults(200, expectedCreditCardResultsTruncated));
				
		String testUser = "f7a4d560-5309-44b2-94ab-02e1d831085f";
		String testToken = "test token";
		String deviceState = "";
		String creditCardState = "INITIALIZED";
		
		ResponseEntity<String> response = controller.index(testUser, creditCardState, deviceState, testToken);
		String resultString = response.getBody();
		
		String creditCardFilteredResult = "{\"creditCards\":[],\"devices\":[{\"state\":\"INITIALIZED\",\"deviceId\":\"7e987d27-6bbd-4b9b-a8dd-6e21634d47fc\"},{\"state\":\"INITIALIZED\",\"deviceId\":\"8f822640-4ad3-407d-9162-bc10a75ef7af\"}],\"userId\":\"f7a4d560-5309-44b2-94ab-02e1d831085f\"}";
		Assert.assertEquals(resultString, creditCardFilteredResult);
				
	}
	
	@Test
	public void getCompositeUsersBothFiltered() throws Exception {
		CompositeUsersController controller = new CompositeUsersController();
		
		String expectedDeviceResultsTruncated = "{\"results\":[{\"deviceIdentifier\":\"7e987d27-6bbd-4b9b-a8dd-6e21634d47fc\",\"state\":\"INITIALIZED\"},{\"deviceIdentifier\":\"8f822640-4ad3-407d-9162-bc10a75ef7af\",\"state\":\"INITIALIZED\"}]}";
		String expectedCreditCardResultsTruncated = "{\"results\":[{\"creditCardId\":\"ac9e9721-e2fd-47dc-a9e7-594a94106ef1\",\"state\":\"ERROR\"}]}";;
		
		controller.userDataService = Mockito.mock(UserDataService.class);
		Mockito.when(controller.userDataService.getUserDevices()).thenReturn(new HTTPResults(200, expectedDeviceResultsTruncated));
		Mockito.when(controller.userDataService.getUserCreditCards()).thenReturn(new HTTPResults(200, expectedCreditCardResultsTruncated));
				
		String testUser = "f7a4d560-5309-44b2-94ab-02e1d831085f";
		String testToken = "test token";
		String deviceState = "ERROR";
		String creditCardState = "INITIALIZED";
		
		ResponseEntity<String> response = controller.index(testUser, creditCardState, deviceState, testToken);
		String resultString = response.getBody();
		
		String bothFilteredResult = "{\"creditCards\":[],\"devices\":[],\"userId\":\"f7a4d560-5309-44b2-94ab-02e1d831085f\"}";
		Assert.assertEquals(resultString, bothFilteredResult);
				
	}
	
	@Test
	public void invalidUser() throws Exception {
		CompositeUsersController controller = new CompositeUsersController();
		
		String expectedDeviceResultsTruncated = "{\"status\":400,\"summary\":\"Bad request\"}";
		String expectedCreditCardResultsTruncated = "{\"status\":400,\"summary\":\"Bad request\"}";
		
		controller.userDataService = Mockito.mock(UserDataService.class);
		Mockito.when(controller.userDataService.getUserDevices()).thenReturn(new HTTPResults(400, expectedDeviceResultsTruncated));
		Mockito.when(controller.userDataService.getUserCreditCards()).thenReturn(new HTTPResults(400, expectedCreditCardResultsTruncated));
				
		String testUser = "BAD_USER_ID_FOR_THE_TEST_HERE";
		String testToken = "test token";
		String deviceState = "ERROR";
		String creditCardState = "INITIALIZED";
		
		ResponseEntity<String> response = controller.index(testUser, creditCardState, deviceState, testToken);
		String resultString = response.getBody();
		
		Mockito.verify(controller.userDataService).setUserId(testUser);
		Mockito.verify(controller.userDataService).setToken(testToken.split(" ")[1]);
		
		System.out.println(response.getStatusCode());
		Assert.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
		Assert.assertEquals(resultString, expectedDeviceResultsTruncated);
	}
	
}