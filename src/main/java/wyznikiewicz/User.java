package wyznikiewicz;

import java.sql.Timestamp;
import javax.validation.constraints.NotNull;
import javax.persistence.*;

@Entity
@Table(name="users")
public class User  
{
	@Id
	@GeneratedValue
	@NotNull
	@Column(name="Id")
	private int id;
	
	@NotNull
	@Column(name="Login")
	private String login;
	
	@NotNull
	@Column(name="Password")
	private String password;
	
	@NotNull
	@Column(name="RegisterDate")
	private Timestamp date;	
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
