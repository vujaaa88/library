package com.myLibrary.myLibrary;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.myLibrary.myLibrary.entity.Book;
import com.myLibrary.myLibrary.entity.MyLibrary;
import com.myLibrary.myLibrary.entity.MyUser;
import com.myLibrary.myLibrary.repository.UserRepository;
import com.myLibrary.myLibrary.service.UserService;



@RunWith(MockitoJUnitRunner.class)
public class MyLibraryApplicationServiceTest {
	
	
	@InjectMocks
	UserService userService;
	
	@Mock
	UserRepository userRepository;
	
	@Before
    public void setUp(){
        
        MyUser user = new MyUser("firstName","lastName","userName","pass");
        user.setId(1);
        
        MyLibrary library = new MyLibrary();
        library.setId(1);
        library.setLibraryName("libraryName");
        
        library.addBook(new Book("firstBook","firstDescription","firstPublishedOn"));
        library.addBook(new Book("secondBook","secondDescription","secondPublishedOn"));
        library.addBook(new Book("thirdBook","thirdDescription","thirdPublishedOn"));
        
        user.setLibrary(library);
        
        when(userService.getOne(1)).thenReturn(user);
    }

	@Test
	public void testUser() {
		
		MyUser user = userService.getOne(1L);
		assertThat("firstName",is(user.getFirstName()));
	}
	
	@Test
	public void testUserLibrary() {
		 
		MyUser user = userService.getOne(1L);
		assertThat("libraryName",is(user.getLibrary().getLibraryName()));
	}
	
	@Test
	public void testLibrarySize() {
		 
		MyUser user = userService.getOne(1L);
		MyLibrary firstLibrary = user.getLibrary();
		assertThat(3, is(firstLibrary.size()));
	}
	
	@Test
	public void testOneBook() {
		
		MyUser user = userService.getOne(1L);
		List<Book> list = user.getLibrary().getBooks();
		Book book = new Book("secondBook","secondDescription","secondPublishedOn");
		assertThat(true, is(book.getTitle().equals(list.get(1).getTitle())));
	}
}
