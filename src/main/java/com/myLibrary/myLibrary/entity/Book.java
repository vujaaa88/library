package com.myLibrary.myLibrary.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Book {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	private String title;
	
	private String description;
	
	@Column(name="created_on")
	@JsonFormat(pattern = "MMM,dd,yyyy HH:mm:ss")
	private LocalDateTime createdOn = LocalDateTime.now();
	
	@ManyToOne
	@JsonBackReference
	private MyLibrary library;
	
	@Column(name="published_on")
	private String publishedOn;
	
	private String author;
	
	@Column(name="authors_birthday")
	private LocalDate authorsBirthday;

	public Book() {
		
	}

	public Book(String title, String description,String publishedOn) {
		this.title = title;
		this.description = description;
		this.publishedOn = publishedOn;
	}
	public Book(long id,String title, String description,String publishedOn) {
		this.id = id;
		this.title = title;
		this.description = description;
		this.publishedOn = publishedOn;
	}
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public MyLibrary getLibrary() {
		return library;
	}

	public void setLibrary(MyLibrary library) {
		this.library = library;
	}

	public String getPublishedOn() {
		return publishedOn;
	}

	public void setPublishedOn(String publishedOn) {
		this.publishedOn = publishedOn;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public LocalDate getAuthorsBirthday() {
		return authorsBirthday;
	}

	public void setAuthorsBirthday(LocalDate authorsBirthday) {
		this.authorsBirthday = authorsBirthday;
	}
	
	public LocalDateTime getCreatedOn() {
		return createdOn;
	}
}