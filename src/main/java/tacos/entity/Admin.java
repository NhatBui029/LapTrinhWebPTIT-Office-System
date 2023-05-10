package tacos.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="admins")
public class Admin {
	@Id
	private String user;
	
	private String password;

	public Admin(String user, String password) {
		super();
		this.user = user;
		this.password = password;
	}
	
	public Admin() {
		
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Admin [user=" + user + ", password=" + password + "]";
	}
	
	
}
