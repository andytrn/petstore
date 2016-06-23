package petstore.model;

/**
 * A model object for Pet.
 * 
 * @author atran
 *
 */
public class Pet {
	
	private Long id;
	private String name;
	private String photo;
	private String status;
	
	public Pet(String name, String photo, String status) {
		
		this.name = name;
		this.photo = photo;
		this.status = status;
		
	}
	
	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getPhoto() {
		return photo;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
}
