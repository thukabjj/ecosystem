package br.com.ecosystem.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
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

import br.com.ecosystem.SpringBootRunApplication;
import br.com.ecosystem.domain.model.UserRequestDto;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootRunApplication.class)
@TestPropertySource(locations="classpath:test.properties")
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
	public void testListUsers() throws Exception {
		ResultMatcher ok = MockMvcResultMatchers.status().isOk();
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/ingresse/api/v1/users");
		this.mockMvc.perform(builder).andExpect(ok);
	}
	@Test
	public void testRegisterUser() throws Exception {
		UserRequestDto request = new UserRequestDto();
		request.setName("Teste");
		request.setEmail("teste@teste.com");
		request.setLogin("teste.login");
		request.setPassword("123456");
		ResultMatcher ok = MockMvcResultMatchers.status().isOk();
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/ingresse/api/v1/users")
				.content(asJsonString(request)).contentType(MediaType.APPLICATION_JSON_UTF8);
		this.mockMvc.perform(builder).andExpect(ok);
	}
	
	
}
