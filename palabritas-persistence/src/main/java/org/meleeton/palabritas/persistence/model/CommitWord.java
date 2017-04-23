package org.meleeton.palabritas.persistence.model;

import org.springframework.data.annotation.Id;

public class CommitWord {
	
	@Id
    public String id;
	
	private String word;
	private double occurrences;
	
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	
	public double getOccurrences() {
		return occurrences;
	}
	public void setOccurrences(int occurrences) {
		this.occurrences = occurrences;
	}
	public void sumOccurrence(){
		this.occurrences++;
	}
	
}
