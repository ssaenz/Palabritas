package org.meleeton.palabritas.persistence.resources;

import org.meleeton.palabritas.api.model.CommitWord;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommitRepository extends MongoRepository<CommitWord, String>, CommitRepositoryCustom {
	//TODO 
	
}
