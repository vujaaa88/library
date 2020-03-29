package com.myLibrary.myLibrary.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.myLibrary.myLibrary.entity.Book;
//import com.myLibrary.myLibrary.entity.Book;
import com.myLibrary.myLibrary.entity.MyLibrary;
import com.myLibrary.myLibrary.entity.MyUser;
import com.myLibrary.myLibrary.service.BookService;
//import com.myLibrary.myLibrary.service.BookService;
import com.myLibrary.myLibrary.service.LibraryService;
import com.myLibrary.myLibrary.service.UserService;


@RestController
public class HomeController {

	private UserService userService;
	private LibraryService libraryService;
	private BookService bookService;
	
	@Autowired
	public HomeController(UserService userService,LibraryService libraryService,
			BookService bookService) {
		this.userService = userService;
		this.libraryService = libraryService;
		this.bookService = bookService;
	}
	@PostMapping("/new")
	public ResponseEntity<Void> saveUser(@RequestBody MyUser user){
		if(userService.findUserByUserName(user.getUserName()) == null) {
			userService.saveUser(user);
		}
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	@GetMapping("/users")
	public ResponseEntity<List<MyUser>> getUsers(){
		return new ResponseEntity<List<MyUser>>(userService.findAll(), HttpStatus.OK);
	}
	@GetMapping("/user")
	public ResponseEntity<MyUser> getUser(Authentication auth) {
		return new ResponseEntity<MyUser>(userService.findUserByUserName(auth.getName()), HttpStatus.OK);
	}
	
	@PostMapping("/create")
	public ResponseEntity<Void> createLibrary(@RequestBody MyLibrary library,Authentication authentication) {
		MyUser user = userService.findUserByUserName(authentication.getName());
		user.setLibrary(library);
		library.setUser(user);
		libraryService.save(library);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	@GetMapping("/books")
	public ResponseEntity<List<Book>> getBooks(Authentication authentication){
		MyUser user = userService.findUserByUserName(authentication.getName());
		MyLibrary library = user.getLibrary();
		return new ResponseEntity<List<Book>>(library.getBooks(), HttpStatus.OK);
	}
	@PostMapping("/add")
	public ResponseEntity<Void> addBook(@RequestBody Book book,Authentication authentication) {
		MyUser user = userService.findUserByUserName(authentication.getName());
		MyLibrary library = user.getLibrary();
		library.addBook(book);
		book.setLibrary(library);
		bookService.save(book);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	@GetMapping("/book{id}")
	public ResponseEntity<Book> getBook(@PathVariable long id) {
		return new ResponseEntity<Book>(bookService.findById(id), HttpStatus.OK);
	}
	@DeleteMapping("/delete{id}")
	public ResponseEntity<Void> deleteBook(@PathVariable long id) {
		Book book = bookService.findById(id);
		bookService.delete(book);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	@DeleteMapping("/deleteAll")
	public ResponseEntity<Void> deleteAll(Authentication authentication) {
		MyUser user = userService.findUserByUserName(authentication.getName());
		MyLibrary library = user.getLibrary();
		List<Book> theBooks = library.getBooks();
		for(int i = 0; i < theBooks.size(); i++) {
			bookService.delete(theBooks.get(i));
		}
		library.setBooks(null);
		libraryService.save(library);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
