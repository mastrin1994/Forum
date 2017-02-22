package wyznikiewicz;

import java.sql.Timestamp;

public class UserRelatedMessage
{
	public UserRelatedMessage(Message m, User owner) {
		id = m.getId();
		content = m.getContent();
		date = m.getDate();
		author = owner.getLogin();
	}
	
	private int id;
	
	private String content;
	
	private Timestamp date;	
	
	private String author;
		
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
}