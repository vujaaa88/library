package com.myLibrary.myLibrary;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.myLibrary.myLibrary.controller.HomeController;
import com.myLibrary.myLibrary.entity.MyUser;
import com.myLibrary.myLibrary.service.UserService;


@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class MyLibraryApplicationControllerTests {
	
	@InjectMocks
    HomeController homeController;
	
	@Mock
	UserService userService;

	 @Test
	 public void testAddUser() {
	   
	        MyUser user = new MyUser("firstName", "lastName", "userName","pass");
	        ResponseEntity<Void> responseEntity = homeController.saveUser(user);
	        
	        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
	    }
	
	
	@Test
    public void testFindAll()   {
        	// given
		   MyUser user1 = new MyUser("firstName", "lastName", "userName","pass");
		   MyUser user2 = new MyUser("secondName", "secondName", "secontUserName","secondPass");
		   List<MyUser> users = new ArrayList<>();
        
		  users.add(user1);
		  users.add(user2);
 
		  when(userService.findAll()).thenReturn(users);
 
		  	// when
		  List<MyUser> result = homeController.getUsers();
 
		  	// then
		  assertThat(result.size()).isEqualTo(2);
         
		  assertThat(result.get(0).getFirstName())
                        .isEqualTo(user1.getFirstName());
         
		  assertThat(result.get(1).getFirstName())
                        .isEqualTo(user2.getFirstName());
    }
}
