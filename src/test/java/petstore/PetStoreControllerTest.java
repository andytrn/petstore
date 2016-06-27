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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithSecurityContextTestExecutionListener;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@TestExecutionListeners(listeners={DependencyInjectionTestExecutionListener.class, WithSecurityContextTestExecutionListener.class})
@WithMockUser(username="admin",password="admin",roles={"USER","ADMIN"})
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
		
		this.mockMvc.perform(post("/pet"))
				.andDo(print())
				.andExpect(status().isBadRequest());
		
	}
	
	@Test
	public void addWithPparamShouldReturnSuccess() throws Exception {
		
		this.mockMvc.perform(post("/pet").param("name", "Max").param("photo", "http://petstore.com/photos/max.jpg").param("status", "available"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.status").value("success"))
				.andExpect(jsonPath("$.value").exists());
		
	}
	
	@Test
	public void getNoParamShouldReturnSuccess() throws Exception {
		
		this.mockMvc.perform(get("/pet"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.status").value("success"));
		
	}
	
	@Test
	public void getWithPparamShouldReturnError() throws Exception {
		
		this.mockMvc.perform(get("/pet/0"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.status").value("failure"))
				.andExpect(jsonPath("$.value").doesNotExist());
		
	}
	
	@Test
	public void getWithPparamShouldReturnSuccess() throws Exception {
		
		this.mockMvc.perform(get("/pet/1"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.status").value("success"))
				.andExpect(jsonPath("$.value").exists());
		
	}
	
	@Test
	public void deleteNoParamShouldReturnError() throws Exception {
		
		this.mockMvc.perform(delete("/pet"))
				.andDo(print())
				.andExpect(status().isMethodNotAllowed());
		
	}
	
	@Test
	public void deleteWithPparamShouldReturnSuccess() throws Exception {
		
		this.mockMvc.perform(delete("/pet/0"))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.status").value("success"))
				.andExpect(jsonPath("$.value").doesNotExist());
		
	}
	
}
