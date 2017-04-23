package org.meleeton.palabritas.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.annotation.PostConstruct;

import org.meleeton.palabritas.consumer.github.api.GithubAPIClient;
import org.meleeton.palabritas.consumer.github.config.ConsumerConfig;
import org.meleeton.palabritas.persistence.CommitWordRepository;
import org.meleeton.palabritas.persistence.model.CommitWord;
import org.meleeton.palabritas.producer.config.ProducerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Sort;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Configuration
@Import({ServletConfig.class, ProducerConfig.class, ConsumerConfig.class})
public class ApplicationConfig {
	

	@Autowired
	GithubAPIClient githubClient;
	
	@Autowired
	CommitWordRepository repo;
	
	@PostConstruct
	public void init() {
		List<CommitWord> words = repo.findAll(new Sort(new Sort.Order(Sort.Direction.DESC, "occurrences")));
		if (words == null || words.isEmpty()) {
			populateDB();
		}
	}
	
	public void populateDB() {
		
		String repositories = githubClient.getBestRepositories();
		System.out.println(repositories);
		JsonElement jsonElement = new JsonParser().parse(repositories);
		
		JsonObject jsonObject = jsonElement.getAsJsonObject();
		
		JsonArray repos = jsonObject.get("items").getAsJsonArray();
		
		repos.forEach(repo -> {
			String fullName = repo.getAsJsonObject().get("full_name").getAsString();
			String commits = githubClient.getCommitByRepository(fullName);
			
			JsonElement commitsElement = new JsonParser().parse(commits);
			System.out.println(commits);
			JsonArray commitsArray = commitsElement.getAsJsonArray();
			
			commitsArray.forEach(commit -> {
				String message = commit.getAsJsonObject().get("commit").getAsJsonObject().get("message").getAsString();
				process(message);
			});
		});
	}
	
	private void process (String message) {
		String[] stringArray = {"some", "which", "the", "was", "when", "this", "that", "a", "an", "and", "aboard", "about", "abobe", "abreast", "abroad", "absent", "across", "adjacent", "after", "against", "along", "alonside", "amid", "among", "apropos", "apud", "around", "as", "astride", "at", "atop", "ontop", "bar", "before", "behind", "below", "beneath", "beside", "besides", "between", "beyond", "but", "by", "chez", "circa", "come", "despite", "down", "during", "except", "for", "from", "in", "inside", "into", "less", "like", "minus", "near", "notwithstanding", "of", "off", "on", "onto", "opposite", "out", "outside", "over", "pace", "past", "per", "post", "pre", "pro", "qua", "re", "sana", "save", "short", "since", "than", "the", "through", "throughout", "to", "toward", "towards", "under", "underneath", "unlike", "until", "up", "upon", "upside", "versus", "via", "vice", "with", "within", "without", "worth"};
		List<String> connectors = new ArrayList<String>(Arrays.asList(stringArray));
		
		Scanner s= new Scanner(message);
		while(s.hasNext()){
		    String word = s.next().toLowerCase();
		    if (word.matches("[a-zA-z]{3,}") && !connectors.contains(word))
		    	saveWord(word);
		}
		s.close();
	}
	
	private void saveWord (String word) {
		CommitWord commitWord = repo.findByWord(word);
		if (commitWord != null) {
			commitWord.setOccurrences(commitWord.getOccurrences() + 1);
		} else {
			commitWord = new CommitWord();
			commitWord.setWord(word);
			commitWord.setOccurrences(1);
		}
		repo.save(commitWord);
	}
}
