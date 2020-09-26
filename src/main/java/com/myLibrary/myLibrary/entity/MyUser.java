package com.myLibrary.myLibrary.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="users")
public class MyUser {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="first_name")
	@NotEmpty(message = "*Please provide your first name")
	private String firstName;
	
	@Column(name="last_name")
	@NotEmpty(message = "*Please provide your last name")
	private String lastName;
	
	@Column(unique = true,name="user_name")
    @NotEmpty(message = "*Please provide a user name")
	private String userName;
	
	@Column(name="email")
	@Email(message = "*Please provide a email")
	private String email;
	
	@Column(name="password")
    @NotEmpty(message = "*Please provide your password")
	private String password;

	@OneToOne
	@JoinColumn(name="library")
	@JsonManagedReference
	private MyLibrary library;
	
	public MyUser() {
		
	}

	public MyUser(String firstName, String lastName, String userName, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public MyLibrary getLibrary() {
		return library;
	}

	public void setLibrary(MyLibrary library) {
		this.library = library;
	}
}

	