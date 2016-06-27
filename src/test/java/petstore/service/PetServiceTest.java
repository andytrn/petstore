package petstore.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithSecurityContextTestExecutionListener;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import net.minidev.json.JSONValue;
import petstore.Application;
import petstore.domain.Pet;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@TestExecutionListeners(listeners={DependencyInjectionTestExecutionListener.class, WithSecurityContextTestExecutionListener.class})
@WithMockUser(username="admin",password="admin",roles={"USER","ADMIN"})
public class PetServiceTest {
	
	@Autowired
	private PetService service;
	
	@Test
	public void testAll() throws Exception {
		
		//Select all
		List<Pet> all = service.getAll();
		Assert.assertEquals(3, all.size());
		
		Pet pet = new Pet();
		
		pet.setName("Mimi");
		pet.setPhoto("http://petstore.com/photo/" + pet.getName().toLowerCase() + ".jpg");
		pet.setStatus("available");
		
		//Insert
		service.insert(pet);
		
		//Select
		Pet newpet = service.getByName(pet.getName());
		Assert.assertNotNull(newpet);
		Assert.assertEquals(pet.getName(), newpet.getName());
		
		//Select all
		all = service.getAll();
		Assert.assertEquals(4, all.size());
		System.out.println(JSONValue.toJSONString(all));
		
		//Delete
		service.deleteByName(pet.getName());
		
		//Check
		newpet = service.getByName(pet.getName());
		Assert.assertNull(newpet);
		
		//Select all
		all = service.getAll();
		Assert.assertEquals(3, all.size());
		
	}
	
}
