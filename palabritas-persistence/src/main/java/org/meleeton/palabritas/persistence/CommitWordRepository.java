package org.meleeton.palabritas.persistence;

import org.meleeton.palabritas.persistence.model.CommitWord;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CommitWordRepository extends MongoRepository<CommitWord, String> {
	
	public CommitWord findByWord(String word);
	public void deleteByWord(String word);
}
