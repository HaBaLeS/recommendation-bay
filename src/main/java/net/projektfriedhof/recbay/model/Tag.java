package net.projektfriedhof.recbay.model;

public class Tag implements DBObject{

	long id;
	String name;
	int count;
	int tagtype;
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}
	/**
	 * @return the tagtype
	 */
	public int getTagtype() {
		return tagtype;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}
	/**
	 * @param tagtype the tagtype to set
	 */
	public void setTagtype(int tagtype) {
		this.tagtype = tagtype;
	}

	
}
