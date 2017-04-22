package org.meleeton.palabritas.api.model;

import java.util.List;

public class Repository {

	private String name;
	private String fullName;
	private String htmlURL;
	private String apiURL;
	private List<Commit> commits;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getFullName() {
		return fullName;
	}
	
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public String getHtmlURL() {
		return htmlURL;
	}
	
	public void setHtmlURL(String htmlURL) {
		this.htmlURL = htmlURL;
	}
	
	public String getApiURL() {
		return apiURL;
	}
	
	public void setApiURL(String apiURL) {
		this.apiURL = apiURL;
	}
	
	public void addCommit(Commit newCommit){
		commits.add(newCommit);
	}
	
}
