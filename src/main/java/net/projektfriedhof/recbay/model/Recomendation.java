package net.projektfriedhof.recbay.model;

import java.util.Date;

public class Recomendation {

	long id;
	String uri;
	int count;
	int views;
	
	Date firstSeen;
	Date lastSeen;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
	
}
