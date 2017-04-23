package org.meleeton.palabritas.config;

import java.util.List;

import javax.annotation.PostConstruct;

import org.meleeton.palabritas.api.model.CommitWord;
import org.meleeton.palabritas.consumer.github.api.GithubAPIClient;
import org.meleeton.palabritas.consumer.github.config.ConsumerConfig;
import org.meleeton.palabritas.persistence.CommitWordRepository;
import org.meleeton.palabritas.producer.config.ProducerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Sort;

@Configuration
@Import({ServletConfig.class, ProducerConfig.class, ConsumerConfig.class})
public class ApplicationConfig {
	private static final Logger logger = LoggerFactory.getLogger(ApplicationConfig.class);
	
	@Autowired
	GithubAPIClient githubClient;
	
	@Autowired
	CommitWordRepository repo;
	
	@PostConstruct
	public void consumer() {
		String repositories = githubClient.getBestRepositories();
		logger.info(repositories);
//		CommitWord word = new CommitWord();
//		word.setOccurrences(2);
//		word.setWord("Hola2");
		System.out.println("Repo.findAll");
//		repo.save(word);
		List<CommitWord> words = repo.findAll(new Sort(new Sort.Order(Sort.Direction.ASC, "occurrences")));
		words.forEach(w -> {
			System.out.println(w.getOccurrences() + " " + w.getWord());
		});
		
		CommitWord savedWord = repo.findByWord("Hola");
		System.out.println(savedWord.getWord());
//		System.out.println(repositories);
	}
	
}
