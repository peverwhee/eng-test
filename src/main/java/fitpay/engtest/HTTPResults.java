package fitpay.engtest;

public class HTTPResults {
	public Integer responseCode = null;
	public String message = null;
	
	public HTTPResults(Integer responseCode, String message) {
		this.responseCode = responseCode;
		this.message = message;
	}

}