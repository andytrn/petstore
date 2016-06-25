package petstore.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import petstore.domain.Pet;
import petstore.mapper.PetMapper;

/**
 * Serivce class to access Pet data.
 * 
 * @author atran
 *
 */
@Service
public class PetService {
	
	@Autowired
	private PetMapper petMapper;
	
	/**
	 * Inserts a Pet record.
	 * 
	 * @param pet
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void insert(Pet pet) {
		
		petMapper.insert(pet);
		
	} //insertPet
	
	/**
	 * Returns all Pet records.
	 * 
	 * @return
	 */
	public List<Pet> getAll() {
		
		return petMapper.getAll();
		
	} //getAll
	
	/**
	 * Returns a specific Pet record by Id.
	 * 
	 * @param id
	 * @return
	 */
	public Pet getById(Integer id) {
		
		return petMapper.getById(id);
		
	} //getById
	
	/**
	 * Returns a specific Pet record by Name.
	 * 
	 * @param name
	 * @return
	 */
	public Pet getByName(String name) {
		
		return petMapper.getByName(name);
		
	} //getByName
	
	/**
	 * Deletes a specific Pet record by Id.
	 * 
	 * @param id
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void deleteById(Integer id) {
		
		petMapper.deleteById(id);
		
	} //deleteById
	
	/**
	 * Deletes a specific Pet record by Name.
	 * 
	 * @param name
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void deleteByName(String name) {
		
		petMapper.deleteByName(name);
		
	} //deleteByName
	
}
