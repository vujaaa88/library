package com.myLibrary.myLibrary.service;

import java.util.List;
import java.util.Optional;

import org.hamcrest.Matcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.myLibrary.myLibrary.entity.MyUser;
import com.myLibrary.myLibrary.repository.UserRepository;

@Service
public class UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
   
    @Autowired
    public UserService(UserRepository userRepository,
    		BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public MyUser findUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    public MyUser saveUser(MyUser user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

	public List<MyUser> findAll() {
		return userRepository.findAll();
	}

	public MyUser getOne(long id) {
		return userRepository.getOne(id);
	}
}
