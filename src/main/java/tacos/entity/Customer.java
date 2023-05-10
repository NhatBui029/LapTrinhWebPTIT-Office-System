package tacos.entity;

import jakarta.persistence.*;

@Entity
@Table(name="customers")
public class Customer {
	@Id
	private String user;
	
	private String password;
	
	private String name;
	
	private String age;
	
	private String address;
	
	private String phone;
	
	private String deleted;

	public Customer(String user, String password, String name, String age, String address, String phone) {
		super();
		this.user = user;
		this.password = password;
		this.name = name;
		this.age = age;
		this.address = address;
		this.phone = phone;
		this.deleted = "false";
	}
	
	public Customer() {
		this.deleted = "false";
	}



	public String getDeleted() {
		return deleted;
	}

	public void setDeleted(String deleted) {
		this.deleted = deleted;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Customer [user=" + user + ", password=" + password + ", name=" + name + ", age=" + age + ", address="
				+ address + ", phone=" + phone + "]";
	}
	
	
}
