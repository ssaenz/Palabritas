package org.meleeton.palabritas.consumer.github.api;

import java.util.List;

import org.meleeton.palabritas.persistence.model.Commit;

public interface GithubAPIClient {

	String getBestRepositories();
	List<Commit> getCommitByRepository(String userName, String repoName);
}
