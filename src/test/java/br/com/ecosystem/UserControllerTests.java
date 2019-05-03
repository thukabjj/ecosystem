package br.com.ecosystem;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.ecosystem.domain.user.UserEntity;
@ActiveProfiles({"test"})
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.JVM)
@AutoConfigureMockMvc
public class UserControllerTests {

	private MockMvc mockMvc;
	
	@Autowired
	private WebApplicationContext wac;

	
	@Before
	public void setup() {
		DefaultMockMvcBuilder builder = MockMvcBuilders.webAppContextSetup(this.wac);
		this.mockMvc = builder.build();
	}


	public static String asJsonString(final Object obj) throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(obj);
	}
	
	@Test
	public void testRegisterUser() throws Exception {
		UserEntity request = new UserEntity();
		request.setName("Teste");
		request.setEmail("teste@teste.com");
		request.setLogin("teste.login");
		request.setPassword("123456");
		ResultMatcher isCreated = MockMvcResultMatchers.status().isCreated();
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/ecosystem/api/v1/users")
				.content(asJsonString(request)).contentType(MediaType.APPLICATION_JSON_UTF8);
		this.mockMvc.perform(builder).andExpect(isCreated);
	}
	@Test
	public void testRequestOfTheListUsers() throws Exception {
		ResultMatcher ok = MockMvcResultMatchers.status().isOk();
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/ecosystem/api/v1/users");
		this.mockMvc.perform(builder).andExpect(ok);
	}
	
	
}
