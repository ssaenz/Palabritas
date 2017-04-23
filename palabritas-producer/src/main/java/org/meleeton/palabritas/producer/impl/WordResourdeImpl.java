package org.meleeton.palabritas.producer.impl;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.meleeton.palabritas.api.model.CommitWord;
import org.meleeton.palabritas.persistence.resources.CommitRepository;
import org.meleeton.palabritas.producer.resources.WordResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Path("/words")
@Service
public class WordResourdeImpl implements WordResource{

	@Autowired
	private CommitRepository repo;

	@Override
	public Response getWords(int offset, int limit) {
		
		//TODO add words
		
		
		
		Gson gson = new GsonBuilder()
				.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
				.create();
		
		
//		List<CommitWord> commitWords = new ArrayList<>();
/*
		CommitWord cw1 = new CommitWord();
		cw1.setWord("bugfix");
		cw1.setOccurrences(2573);

		CommitWord cw2 = new CommitWord();
		cw2.setWord("hotfix");
		cw2.setOccurrences(387);

		CommitWord cw3 = new CommitWord();
		cw3.setWord("typo");
		cw3.setOccurrences(92457);

		CommitWord cw4 = new CommitWord();
		cw4.setWord("connection");
		cw4.setOccurrences(7453);

		CommitWord cw5 = new CommitWord();
		cw5.setWord("performance");
		cw5.setOccurrences(7395);
		
		
		repo.save(cw1);
		repo.save(cw2);
		repo.save(cw3);
		repo.save(cw4);
		repo.save(cw5);
		
		
		System.out.println("Iniciando insert");
		repo.addCommitWord("bugfix");
		repo.addCommitWord("hotfix");
		repo.addCommitWord("typo");
		repo.addCommitWord("performance");
		repo.addCommitWord("connection");
*/
		
		String jsonElement = gson.toJson(repo.findAll());
		return Response.ok(jsonElement).build();
	}
}
