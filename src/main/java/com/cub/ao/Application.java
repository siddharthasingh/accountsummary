package com.cub.ao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.web.client.RestTemplate;
// @ConfigurationProperties(locations="")
@SpringBootApplication
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@ComponentScan("com.cub.ao")
//@Configuration

@PropertySources({
    @PropertySource(value = "classpath:application.properties")
})
public class Application {
	 
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	} 

    public static void main(String[] args) {

    	SpringApplication.run(Application.class, args);    		
    }
}