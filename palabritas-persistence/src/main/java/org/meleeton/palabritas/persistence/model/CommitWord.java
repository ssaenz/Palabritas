package org.meleeton.palabritas.persistence.model;

import org.springframework.data.annotation.Id;

public class CommitWord {
	
	@Id
    public String id;
	
	private String word;
	private long occurrences;
	
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	
	public long getOccurrences() {
		return occurrences;
	}
	public void setOccurrences(long occurrences) {
		this.occurrences = occurrences;
	}
	public void sumOccurrence(){
		this.occurrences++;
	}
	
}
