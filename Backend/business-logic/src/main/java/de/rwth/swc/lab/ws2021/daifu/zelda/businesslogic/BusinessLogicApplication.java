package de.rwth.swc.lab.ws2021.daifu.zelda.businesslogic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class BusinessLogicApplication {

	//private static final Logger log = LoggerFactory.getLogger(BusinessLogicApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BusinessLogicApplication.class, args);
	}

/*
	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			Customer customer = restTemplate.getForObject(
				"http://localhost:8080/api/v1/customers/1?getBy=id", Customer.class);
			log.info(customer.toString());
		};
	}
*/

}
