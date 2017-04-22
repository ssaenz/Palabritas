package org.meleeton.palabritas.consumer.github.api;

import java.util.List;

import org.meleeton.palabritas.api.model.Commit;
import org.meleeton.palabritas.api.model.Repository;

public interface GithubAPIClient {

	List<Repository> getBestRepositories();
	List<Commit> getCommitByRepository(String userName, String repoName);
}
