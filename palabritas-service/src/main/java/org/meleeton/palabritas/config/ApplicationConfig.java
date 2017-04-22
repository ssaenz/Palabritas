package org.meleeton.palabritas.config;

import java.util.List;

import javax.annotation.PostConstruct;

import org.meleeton.palabritas.api.model.Repository;
import org.meleeton.palabritas.consumer.github.api.GithubAPIClient;
import org.meleeton.palabritas.consumer.github.config.ConsumerConfig;
import org.meleeton.palabritas.producer.config.ProducerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ServletConfig.class, ProducerConfig.class, ConsumerConfig.class})
public class ApplicationConfig {
	private static final Logger logger = LoggerFactory.getLogger(ApplicationConfig.class);
	
	@Autowired
	GithubAPIClient githubClient;
	
	@PostConstruct
	public void consumer() {
		List<Repository> repositories = githubClient.getBestRepositories();
		logger.info("Founded {} repositories", repositories.size());
	}
	
}
