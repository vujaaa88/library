package com.myLibrary.myLibrary.repository;

import org.springframework.data.repository.CrudRepository;

import com.myLibrary.myLibrary.entity.Book;

public interface BookRepository extends CrudRepository<Book, Long> {

}
