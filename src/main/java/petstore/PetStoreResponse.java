package petstore;

/**
 * A model for the response.
 * 
 * @author atran
 *
 */
public class PetStoreResponse {
	
	private String status;
	private String message;
	private Object value;
	
	/**
	 * Constructor.
	 * 
	 * @param message
	 */
	public PetStoreResponse(String status, String message, Object value) {
		
		this.status = status;
		this.message = message;
		this.value = value;
		
	}
	
	/**
	 * Returns the status of the request.
	 * 
	 * @return
	 */
	public String getStatus() {
		
		return status;
		
	}
	
	/**
	 * Returns the error message if set, or null.
	 * 
	 * @return
	 */
	public String getMessage() {
		
		return message;
		
	}
	
	/**
	 * Returns the value of the card.
	 * 
	 * @return
	 */
	public Object getValue() {
		
		return value;
		
	}
	
}
