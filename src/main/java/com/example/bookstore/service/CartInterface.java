package com.example.bookstore.service;

import com.example.bookstore.dto.CartDTO;
import com.example.bookstore.model.Cart;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CartInterface {

    Cart addCart(CartDTO cartDTO);

    List<Cart> getall();

    Cart FindById(int id);

    void deleteById(int id);

    Cart editById(int id, CartDTO cardDto);

    Cart changeQuantity(int cartId, int quantity);
}
