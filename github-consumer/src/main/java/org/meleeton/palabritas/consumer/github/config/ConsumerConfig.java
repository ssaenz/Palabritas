package org.meleeton.palabritas.consumer.github.config;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.apache.cxf.jaxrs.client.WebClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

@Configuration
@ComponentScan ({"org.meleeton.palabritas.consumer.github"})
@EnableConfigurationProperties
@ConfigurationProperties(prefix="consumer.configuration")
public class ConsumerConfig {
	
	private String githubUrl;

	public String getGithubUrl() {
		return githubUrl;
	}

	public void setGithubUrl(String githubUrl) {
		this.githubUrl = githubUrl;
	}
	
	@Bean
	public JacksonJsonProvider getJacksonJsonProvider() {
		return new JacksonJsonProvider();
	}
	
	@Bean 
	public WebClient syncClient() {
		List<Object> providers = new ArrayList<>();
		providers.add(new JacksonJsonProvider());
		return createWebClient(githubUrl, providers);
	}
	
	private static WebClient createWebClient (String url, List<Object> providers) {
		WebClient client = WebClient.create(url, providers, true);
		client.accept(MediaType.APPLICATION_JSON);
		return client;
	}

}
