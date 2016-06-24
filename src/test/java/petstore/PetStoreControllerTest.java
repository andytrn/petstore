package petstore;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class, locations="classpath:spring-beans.xml")
@WebAppConfiguration
public class PetStoreControllerTest {
	
	@Autowired
	private WebApplicationContext ctx;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() {
		
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
		
	}
	
	@Test
	public void addNoParamShouldReturnError() throws Exception {
		
		this.mockMvc.perform(post("/add"))
				.andDo(print())
				.andExpect(status().isBadRequest());
		
	}
	
	@Test
	public void addWitPparamShouldReturnSuccess() throws Exception {
		
		this.mockMvc.perform(post("/add").param("name", "Max").param("photo", "http://petstore.com/photos/max.jpg").param("status", "available"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.status").value("success"))
				.andExpect(jsonPath("$.value").exists());
		
	}
	
	@Test
	public void getNoParamShouldReturnSuccess() throws Exception {
		
		this.mockMvc.perform(get("/get"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.status").value("success"));
		
	}
	
	@Test
	public void getWitPparamShouldReturnSuccess() throws Exception {
		
		this.mockMvc.perform(get("/get/0"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.status").value("success"))
				.andExpect(jsonPath("$.value").exists());
		
	}
	
	@Test
	public void deleteNoParamShouldReturnError() throws Exception {
		
		this.mockMvc.perform(delete("/delete"))
				.andDo(print())
				.andExpect(status().isMethodNotAllowed());
		
	}
	
	@Test
	public void deleteWitPparamShouldReturnSuccess() throws Exception {
		
		this.mockMvc.perform(delete("/delete/0"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.status").value("success"))
				.andExpect(jsonPath("$.value").doesNotExist());
		
	}
	
}
