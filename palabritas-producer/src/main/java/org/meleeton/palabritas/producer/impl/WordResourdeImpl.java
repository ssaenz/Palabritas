package org.meleeton.palabritas.producer.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.meleeton.palabritas.api.model.CommitWord;
import org.meleeton.palabritas.producer.resources.WordResource;
import org.springframework.stereotype.Service;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Path("/words")
@Service
public class WordResourdeImpl implements WordResource{

	@Override
	public Response getWords(String offset) {
		List<CommitWord> commitWords = new ArrayList<>();
		
		//TODO add words
		Gson gson = new GsonBuilder()
				.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
				.create();
		
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

		commitWords.add(cw1);
		commitWords.add(cw2);
		commitWords.add(cw3);
		commitWords.add(cw4);
		commitWords.add(cw5);
		
		String jsonElement = gson.toJson(commitWords);
		return Response.ok(jsonElement).build();
	}
}
