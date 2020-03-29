package com.myLibrary.myLibrary.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myLibrary.myLibrary.entity.MyUser;

public interface UserRepository extends JpaRepository<MyUser, Long> {
   MyUser findByUserName(String userName);
}