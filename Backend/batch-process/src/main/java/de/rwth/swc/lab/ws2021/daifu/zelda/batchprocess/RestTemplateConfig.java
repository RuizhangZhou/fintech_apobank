package de.rwth.swc.lab.ws2021.daifu.zelda.batchprocess;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate(@Autowired RestTemplateBuilder builder) {
        return builder.build();
    }

}
