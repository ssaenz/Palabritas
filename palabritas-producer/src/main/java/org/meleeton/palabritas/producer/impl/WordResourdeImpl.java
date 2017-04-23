package org.meleeton.palabritas.producer.impl;

import java.util.List;

import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.meleeton.palabritas.api.model.CommitWord;
import org.meleeton.palabritas.persistence.CommitWordRepository;
import org.meleeton.palabritas.producer.resources.WordResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Path("/words")
@Service
public class WordResourdeImpl implements WordResource{
	
	@Autowired
	CommitWordRepository repo;

	@Override
	public Response getWords(int offset, int limit) {
		Gson gson = new GsonBuilder()
				.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
				.create();
		List<CommitWord> words = repo.findAll(new Sort(new Sort.Order(Sort.Direction.DESC, "occurrences")));
		String jsonElement = gson.toJson(words);
		return Response.ok(jsonElement).build();
	}
}
