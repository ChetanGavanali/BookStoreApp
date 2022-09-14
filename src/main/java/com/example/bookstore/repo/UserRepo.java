package com.example.bookstore.repo;

import com.example.bookstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UserRepo extends JpaRepository<User, Integer> {


    @Query(value = "SELECT * FROM user WHERE email = :email", nativeQuery = true)
    User findByEmail(String email);

}
