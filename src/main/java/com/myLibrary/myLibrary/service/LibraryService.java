package com.myLibrary.myLibrary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myLibrary.myLibrary.entity.MyLibrary;
import com.myLibrary.myLibrary.repository.LibraryRepository;

@Service
public class LibraryService {

	private LibraryRepository libraryRepository;
	
	@Autowired
	public LibraryService(LibraryRepository libraryRepository) {
		this.libraryRepository = libraryRepository;
	}

	public void save(MyLibrary library) {
		libraryRepository.save(library);
	}
}
