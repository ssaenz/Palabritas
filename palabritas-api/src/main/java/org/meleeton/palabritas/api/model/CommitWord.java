package org.meleeton.palabritas.api.model;

public class CommitWord {
	
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
