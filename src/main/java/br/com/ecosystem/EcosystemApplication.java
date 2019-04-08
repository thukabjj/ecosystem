package br.com.ecosystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableAutoConfiguration
@EnableDiscoveryClient
public class EcosystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcosystemApplication.class, args);
	}
	
	@Bean(name = "restTemplate")
	@Autowired
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Bean(name = "restTemplateDiscovery")
	@LoadBalanced
	public RestTemplate restTemplateDiscovery() {
		return new RestTemplate();
	}

}
