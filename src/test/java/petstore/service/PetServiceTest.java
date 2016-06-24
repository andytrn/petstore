package petstore.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import petstore.Application;
import petstore.domain.Pet;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class, locations="classpath:spring-beans.xml")
public class PetServiceTest {
	
	@Autowired
	private PetService service;
	
	@Test
	public void testAll() throws Exception {
		
		Pet pet1 = new Pet();
		
		pet1.setName("Max");
		pet1.setPhoto("http://petstore.com/photo/" + pet1.getName().toLowerCase() + ".jpg");
		pet1.setStatus("available");
		
		//Insert
		service.insert(pet1);
		
		//Select
		Pet newpet = service.getByName(pet1.getName());
		Assert.assertNotNull(newpet);
		Assert.assertEquals(pet1.getName(), newpet.getName());
		
		Pet pet2 = new Pet();
		
		pet2.setName("Charlie");
		pet2.setPhoto("http://petstore.com/photo/" + pet2.getName().toLowerCase() + ".jpg");
		pet2.setStatus("available");
		
		//Insert
		service.insert(pet2);
		
		//Select
		newpet = service.getByName(pet2.getName());
		Assert.assertNotNull(newpet);
		Assert.assertEquals(pet2.getName(), newpet.getName());
		
		//Select all
		List<Pet> all = service.getAll();
		Assert.assertEquals(2, all.size());
		
		//Delete
		service.deleteByName(pet1.getName());
		
		//Check
		newpet = service.getByName(pet1.getName());
		Assert.assertNull(newpet);
		
		//Select all
		all = service.getAll();
		Assert.assertEquals(1, all.size());
		
	}
	
}
