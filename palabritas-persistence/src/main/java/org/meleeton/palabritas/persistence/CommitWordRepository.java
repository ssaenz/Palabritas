package org.meleeton.palabritas.persistence;

import org.meleeton.palabritas.api.model.CommitWord;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommitWordRepository extends MongoRepository<CommitWord, String> {
	
	public CommitWord findByWord(String word);
	
}
