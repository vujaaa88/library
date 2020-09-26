package com.myLibrary.myLibrary.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myLibrary.myLibrary.entity.Book;
import com.myLibrary.myLibrary.entity.MyLibrary;

public interface LibraryRepository extends JpaRepository<MyLibrary, Long> {
	
	MyLibrary findByUser(MyLibrary myLibrary);
}
