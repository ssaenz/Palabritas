package org.meleeton.palabritas.consumer.github.config;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

@Configuration
@ComponentScan ({"org.meleeton.palabritas.consumer.github"})
@Import({GithubConfig.class})
public class ConsumerConfig {
	
	@Autowired
	GithubConfig config;
	
	@PostConstruct
	public void onInit () {
		System.out.println("*******************" + config.getGithubUrl());
	}
	
	@Bean
	public JacksonJsonProvider getJacksonJsonProvider() {
		return new JacksonJsonProvider();
	}
	
	@Bean 
	public WebClient syncClient() {
		List<Object> providers = new ArrayList<>();
		providers.add(new JacksonJsonProvider());
		return createWebClient(config.getGithubUrl(), providers);
	}
	
	private static WebClient createWebClient (String url, List<Object> providers) {
		WebClient client = WebClient.create(url, providers, true);
		client.accept(MediaType.APPLICATION_JSON);
		return client;
	}

}
