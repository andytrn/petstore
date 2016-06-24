package petstore.mapper;

import java.util.List;

import petstore.domain.Pet;

/**
 * MyBatis access mapper for the Pet table.
 * 
 * @author atran
 *
 */
public interface PetMapper {
	
	/**
	 * Inserts a Pet record.
	 * 
	 * @param pet
	 */
	public void insert(Pet pet);
	
	/**
	 * Returns all Pet records.
	 * 
	 * @return
	 */
	public List<Pet> getAll();
	
	/**
	 * Returns a specific Pet record by Id.
	 * 
	 * @param id
	 * @return
	 */
	public Pet getById(int id);
	
	/**
	 * Returns a specific Pet record by Name.
	 * 
	 * @param name
	 * @return
	 */
	public Pet getByName(String name);
	
	/**
	 * Deletes a specific Pet record by Id.
	 * 
	 * @param id
	 */
	public void deleteById(int id);
	
	/**
	 * Deletes a specific Pet record by Name.
	 * 
	 * @param name
	 */
	public void deleteByName(String name);
	
}
