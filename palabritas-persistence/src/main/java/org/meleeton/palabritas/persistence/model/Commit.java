package org.meleeton.palabritas.persistence.model;

import java.util.Date;

public class Commit {

	private String Url;
	private String message;
	private Date date;
	
	public String getShaURL() {
		return Url;
	}
	public void setShaURL(String Url) {
		this.Url = Url;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
