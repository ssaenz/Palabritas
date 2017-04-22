package org.meleeton.palabritas.api.model;

public class Commit {

	private String shaURL;
	private String message;
	private String date;
	
	public String getShaURL() {
		return shaURL;
	}
	public void setShaURL(String shaURL) {
		this.shaURL = shaURL;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	public String toString(){
		return this.shaURL;
	}
}
