package com.myLibrary.myLibrary.repository;

import org.springframework.data.repository.CrudRepository;

import com.myLibrary.myLibrary.entity.MyLibrary;

public interface LibraryRepository extends CrudRepository<MyLibrary, Long> {

}
