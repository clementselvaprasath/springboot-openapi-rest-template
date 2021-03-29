package com.baeldung;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import javax.inject.Inject;
import org.springframework.web.client.*;
import org.springframework.http.*;
import com.baeldung.openapi.model.*;
import java.util.*;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootConsoleApplication implements CommandLineRunner {

	private static Logger LOG = LoggerFactory.getLogger(SpringBootConsoleApplication.class);

	@Inject   //getting error 
	private RestTemplate restTemplate;
	
	@Bean
	public RestTemplate restTemplate() {
	 
	    RestTemplate restTemplate = new RestTemplate();
	    return restTemplate;
	}
	
	public static void main(String[] args) {
		LOG.info("STARTING THE APPLICATION");
		SpringApplication.run(SpringBootConsoleApplication.class, args);
		
		LOG.info("APPLICATION FINISHED");
	}

	/**
	 * This method will be executed after the application context is loaded and
	 * right before the Spring Application main method is completed.
	 */
	@Override
	public void run(String... args) throws Exception {
		LOG.info("EXECUTING : command line runner");
    		for (int i = 0; i < args.length; ++i) {
        		LOG.info("args[{}]: {}", i, args[i]);
    		}
    		getRestTemplate();
	}
	
	public void getRestTemplate() {
		String fooResourceUrl = "http://localhost:17070/pets";
		//HttpEntity request = new HttpEntity("");
		
		ResponseEntity<List<Pet>> result = restTemplate.exchange(fooResourceUrl, HttpMethod.GET, null , new ParameterizedTypeReference<List<Pet>>() {});
		
		System.out.println("result : "+result.getBody().get(0).getName());

	}
}
