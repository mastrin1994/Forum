package wyznikiewicz;

import java.sql.Timestamp;
import javax.validation.constraints.NotNull;
import javax.persistence.*;

@Entity
@Table(name="topic")
public class Topic  
{
	@Id
	@GeneratedValue
	@NotNull
	@Column(name="Id")
	private int id;
	
	@NotNull
	@Column(name="Name")
	private String name;
	
	@NotNull
	@Column(name="CreationDate")
	private Timestamp date;	
	
	@NotNull
	@Column(name="Author")
	private int userId;
	
	@NotNull
	@Column(name="Content")
	private String content;
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
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
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
}
