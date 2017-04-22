package org.meleeton.palabritas.api.model;

import java.util.List;

public class CommitWord {
	
	private String word;
	private double occurrences;
	private List<String> commits;
	
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
	
	public void addCommit(String commitStr){
		this.commits.add(commitStr);
		incrementOccurrence();
	}
	private void incrementOccurrence(){
		occurrences++;
	}
	
}
