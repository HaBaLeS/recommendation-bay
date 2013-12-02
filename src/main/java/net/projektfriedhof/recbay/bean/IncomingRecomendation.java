package net.projektfriedhof.recbay.bean;

import java.util.List;

public class IncomingRecomendation {

	String uri;
	List<String> taglist;
	
	public String getUri() {
		return uri;
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public List<String> getTaglist() {
		return taglist;
	}
	public void setTaglist(List<String> taglist) {
		this.taglist = taglist;
	}
	
}
