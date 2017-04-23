package org.meleeton.palabritas.consumer.github.impl;

import java.util.List;

import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;
import org.meleeton.palabritas.consumer.github.api.GithubAPIClient;
import org.meleeton.palabritas.persistence.model.Commit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GithubAPIClientImpl implements GithubAPIClient {
	
	private static final String SEARCH_REPO_PATH = "search/repositories";
		
	@Autowired
	private WebClient githubClient;
	
	@Override
	public String getBestRepositories() {
		githubClient.back(Boolean.TRUE);
		githubClient.path(SEARCH_REPO_PATH);
		githubClient.query("q", "stars:%3E1");
		githubClient.query("sort", "stars");
		
		Response response = githubClient.get();
		
//		System.out.println("status: " + response.getStatus());
		String repos = response.readEntity(String.class);
//		System.out.println("repositories" + repos);
		return repos;
	}

	@Override
	public List<Commit> getCommitByRepository(String userName, String repoName) {
		// TODO Auto-generated method stub
		return null;
	}

}
