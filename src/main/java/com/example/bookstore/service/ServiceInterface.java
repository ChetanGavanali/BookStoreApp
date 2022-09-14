package com.example.bookstore.service;

import com.example.bookstore.dto.LoginDTO;
import com.example.bookstore.dto.UserDTO;
import com.example.bookstore.model.User;

public interface ServiceInterface {

    String insertRecord(UserDTO userDTO);


    User FindById(int id);

    User editByEmail(UserDTO userDTO, String email_address);

    User getDataByToken(String token);

    User findAll();

    User loginUser(LoginDTO loginDTO);

    String forgotPassword(String email);

    String resetPassword(LoginDTO loginDto);

    User findByEmail(String email);
}
