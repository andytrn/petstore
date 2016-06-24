package petstore.domain;

import java.io.Serializable;

/**
 * A domain object for Pet.
 * 
 * @author atran
 *
 */
public class Pet implements Serializable {
	
	private static final long serialVersionUID = 5276861403113907831L;
	
	private Integer id;
	private String name;
	private String photo;
	private String status;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPhoto() {
		return photo;
	}
	
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
}
