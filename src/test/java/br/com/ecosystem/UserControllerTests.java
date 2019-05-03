package br.com.ecosystem;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.ecosystem.domain.user.UserEntity;
@ActiveProfiles({"test"})
@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations="classpath:test.properties")
@FixMethodOrder(MethodSorters.JVM)
@AutoConfigureMockMvc
public class UserControllerTests {

	private MockMvc mockMvc;

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
		ResultMatcher ok = MockMvcResultMatchers.status().isOk();
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/ecosystem/api/v1/users")
				.content(asJsonString(request)).contentType(MediaType.APPLICATION_JSON_UTF8);
		this.mockMvc.perform(builder).andExpect(ok);
	}
	@Test
	public void testRequestOfTheListUsers() throws Exception {
		ResultMatcher ok = MockMvcResultMatchers.status().isOk();
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/ecosystem/api/v1/users");
		this.mockMvc.perform(builder).andExpect(ok);
	}
	
	
}
