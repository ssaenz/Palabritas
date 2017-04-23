package org.meleeton.palabritas.consumer.github.impl;

import java.util.List;

import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;
import org.meleeton.palabritas.consumer.github.api.GithubAPIClient;
import org.meleeton.palabritas.persistence.model.Commit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Service
public class GithubAPIClientImpl implements GithubAPIClient {
	
	@Autowired
	private WebClient githubClient;
	
	@Override
	public String getBestRepositories() {
		githubClient.back(Boolean.TRUE);
		githubClient.path("search/repositories");
		githubClient.query("client_id", "f7500fb860f332efa263");
		githubClient.query("client_secret", "0cb61c275ae8c31ad594e09f66f485f82f7511d8");
		githubClient.query("q", "stars:%3E1");
		githubClient.query("sort", "stars");
		Response response = githubClient.get();
		String repos = response.readEntity(String.class);
		return repos;
	}

	@Override
	public String getCommitByRepository(String fullName) {
		githubClient.back(Boolean.TRUE);
		githubClient.path("/repos/" + fullName + "/commits");
		githubClient.query("client_id", "f7500fb860f332efa263");
		githubClient.query("client_secret", "0cb61c275ae8c31ad594e09f66f485f82f7511d8");
		Response response = githubClient.get();
		String repos = response.readEntity(String.class);
		
		return repos;
	}

}
