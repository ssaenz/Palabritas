package org.meleeton.palabritas.persistence.impl;

import java.util.List;

import org.meleeton.palabritas.api.model.CommitWord;
import org.meleeton.palabritas.persistence.resources.CommitRepository;
import org.meleeton.palabritas.persistence.resources.CommitRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;

public class CommitRepositoryImpl implements CommitRepositoryCustom{

	@Autowired
	private CommitRepository repository;

	public List<CommitWord> listWords(){
		return (List<CommitWord>) repository.findAll();
	}
    /*
	@Override
	public CommitWord find(String word) {
		CommitWord res = null;
		
		for (CommitWord item : repository.findAll()) {
			if (item.getWord().equals(word)){
				res=item;
			}
		}
		return res;
	}

	@Override
	public void addCommitWord(String commitWord) {
		System.out.println("palabra:" + commitWord);
		if (find(commitWord) == null) {
			System.out.println("No encontrada");
			CommitWord newWord = new CommitWord();
			newWord.setWord(commitWord);
			newWord.setOccurrences(1);
			System.out.println("pre save");
			repository.save(newWord);
			System.out.println("post save");
			
		}else{
			CommitWord foundWord = repository.find(commitWord);
			foundWord.sumOccurrence();
		}
	}
	*/
}
