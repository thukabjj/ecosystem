package br.com.ecosystem;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.ecosystem.SpringBootRunApplication;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringBootRunApplication.class)
@TestPropertySource(locations="classpath:test.properties")
public class SpringBootRunApplicationTests {

	@Test
	public void contextLoads() {
	}

}
