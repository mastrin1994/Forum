package wyznikiewicz;

import java.sql.Timestamp;

public class UserRelatedTopic  
{
	public UserRelatedTopic(Topic t, User owner) {
		id = t.getId();
		name = t.getName();
		date = t.getDate();
		author = owner.getLogin();
	}
	
	private int id;
	private String name;
	private Timestamp date;	
	private String author;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
}
