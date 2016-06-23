package petstore;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import petstore.model.Pet;
import petstore.util.JsonUtil;

/**
 * A controller for the Pet Store operations.
 * 
 * @author atran
 *
 */
@RestController
public class PetStoreController {
	
	/**
	 * Add a pet to the store.
	 * 
	 * @param name The name of the pet.
	 * @param photo The URL to the photo of the pet.
	 * @param status The availability status of the pet.
	 * @return A response object.
	 */
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public PetStoreResponse addPet(@RequestParam String name, @RequestParam String photo, @RequestParam String status) {
		
		PetStoreResponse response = null;
		
		try {
			
			Pet pet = new Pet(name, photo, status);
			
			response = new PetStoreResponse("success", null, JsonUtil.toJson(pet));
			
		} catch (JsonProcessingException e) {
			
			response = new PetStoreResponse("failure", e.getMessage(), null);
			
		} //try
		
		return response;
		
	} //addPet
	
	@RequestMapping(value="/delete/{id}", method = RequestMethod.DELETE)
	public PetStoreResponse deletePet(@PathVariable Long id) {
		
		PetStoreResponse response = null;
		
		try {
			
			response = new PetStoreResponse("success", null, null);
			
		} catch (Exception e) { //TODO
			
			response = new PetStoreResponse("failure", e.getMessage(), null);
			
		} //try
		
		return response;
		
	} //deletePet
	
	@RequestMapping(value="/get/{id}", method = RequestMethod.GET)
	public PetStoreResponse getPet(@PathVariable Long id) {
		
		PetStoreResponse response = null;
		
		try {
			
			response = new PetStoreResponse("success", null, null);
			
		} catch (Exception e) { //TODO
			
			response = new PetStoreResponse("failure", e.getMessage(), null);
			
		} //try
		
		return response;
		
	} //getPet
		
}
