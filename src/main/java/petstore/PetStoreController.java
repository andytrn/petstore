package petstore;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import petstore.domain.Pet;
import petstore.service.PetService;

/**
 * A controller for the Pet Store operations.
 * 
 * @author atran
 *
 */
@RestController
public class PetStoreController {
	
	@Autowired
	private PetService service;
	
	/**
	 * Creates a pet record and store in the database.
	 * 
	 * @param name The name of the pet.
	 * @param photo The URL to the photo of the pet.
	 * @param status The availability status of the pet.
	 * @return A response object.
	 */
	@RequestMapping(value="/pet", method = RequestMethod.POST)
	public PetStoreResponse create(@RequestParam String name, @RequestParam String photo, @RequestParam String status) {
		
		PetStoreResponse response = null;
		
		try {
			
			Pet pet = new Pet();
			
			pet.setName(name);
			pet.setPhoto(photo);
			pet.setStatus(status);
			
			service.insert(pet);
			
			response = new PetStoreResponse("success", null, pet);
			
		} catch (Exception e) { //TODO
			
			response = new PetStoreResponse("failure", e.getMessage(), null);
			
		} //try
		
		return response;
		
	} //addPet
	
	/**
	 * Removes a pet from the store.
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/pet/{id}", method = RequestMethod.DELETE)
	public PetStoreResponse delete(@PathVariable Integer id) {
		
		PetStoreResponse response = null;
		
		try {
			
			service.deleteById(id);
			
			response = new PetStoreResponse("success", null, null);
			
		} catch (Exception e) { //TODO
			
			response = new PetStoreResponse("failure", e.getMessage(), null);
			
		} //try
		
		return response;
		
	} //deletePet
	
	/**
	 * Returns all pets in the store.
	 * 
	 * @return
	 */
	@RequestMapping(value="/pet", method = RequestMethod.GET)
	public PetStoreResponse get() {
		
		PetStoreResponse response = null;
		
		try {
			
			List<Pet> pets = service.getAll();
			
			response = new PetStoreResponse("success", null, pets);
			
		} catch (Exception e) { //TODO
			
			response = new PetStoreResponse("failure", e.getMessage(), null);
			
		} //try
		
		return response;
		
	} //getPet
	
	/**
	 * Returns a pet from the store.
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/pet/{id}", method = RequestMethod.GET)
	public PetStoreResponse get(@PathVariable Integer id) {
		
		PetStoreResponse response = null;
		
		try {
			
			Pet pet = service.getById(id);
			
			if (pet == null) {
				
				throw new Exception("Not found");
				
			} //if
			
			response = new PetStoreResponse("success", null, pet);
			
		} catch (Exception e) { //TODO
			
			response = new PetStoreResponse("failure", e.getMessage(), null);
			
		} //try
		
		return response;
		
	} //getPet
	
	/**
	 * Returns the current logged in user.
	 * 
	 * @return
	 */
	@RequestMapping(value="/user", method = RequestMethod.GET)
	public PetStoreResponse user() {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		return new PetStoreResponse("success", null, auth.getName());
		
	}
		
}
