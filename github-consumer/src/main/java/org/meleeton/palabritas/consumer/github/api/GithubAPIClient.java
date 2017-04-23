package org.meleeton.palabritas.consumer.github.api;

public interface GithubAPIClient {

	String getBestRepositories();
	String getCommitByRepository(String fullName);
}
