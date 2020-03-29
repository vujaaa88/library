package com.myLibrary.myLibrary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myLibrary.myLibrary.entity.Book;
import com.myLibrary.myLibrary.repository.BookRepository;

@Service
public class BookService {

	private BookRepository bookRepository;
	
	@Autowired
	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	public void save(Book book) {
		bookRepository.save(book);
	}

	public Book findById(long id) {
		return bookRepository.findById(id).get();
	}

	public void delete(Book book) {
		bookRepository.delete(book);
	}
}
