package com.myLibrary.myLibrary.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class MyLibrary {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="library_name")
	private String libraryName;

	@OneToOne
	@JsonBackReference
	private MyUser user;
	
	@OneToMany(mappedBy = "library")
	@JsonManagedReference
	private List<Book> books = new ArrayList<>();
	
	
	public MyLibrary() {
		
	}
	
	public MyLibrary(String libraryName) {
		this.libraryName = libraryName;
	}
	
	public MyLibrary(String libraryName,MyUser user) {
		this.libraryName = libraryName;
		this.user = user;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getLibraryName() {
		return libraryName;
	}
	public void setLibraryName(String libraryName) {
		this.libraryName = libraryName;
	}
	public MyUser getUser() {
		return user;
	}
	public void setUser(MyUser user) {
		this.user = user;
	}
	public List<Book> getBooks() {
		return books;
	}
	public void addBook(Book book) {
		this.books.add(book);
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	public void addAll(List<Book> list) {
		books.addAll(list);
	}
	public int size() {
		return books.size();
	}
}
