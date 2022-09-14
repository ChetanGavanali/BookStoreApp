package com.example.bookstore.service;


import com.example.bookstore.dto.CartDTO;
import com.example.bookstore.exception.CartException;
import com.example.bookstore.model.Book;
import com.example.bookstore.model.Cart;
import com.example.bookstore.model.User;
import com.example.bookstore.repo.BookRepo;
import com.example.bookstore.repo.CartRepo;
import com.example.bookstore.repo.UserRepo;
import com.example.bookstore.util.EmailSenderService;
import com.example.bookstore.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService implements CartInterface {

    @Autowired
    CartRepo cartRepo;
    @Autowired
    TokenUtil tokenUtil;
    @Autowired
    EmailSenderService emailSender;

    @Autowired
    UserRepo userRepo;
    @Autowired
    BookRepo bookRepo;

    @Override
    public Cart addCart(CartDTO cartDTO) {
        Optional<User> user = userRepo.findById(cartDTO.getUserid());
        Optional<Book> book = bookRepo.findById(cartDTO.getBookId());
        if (user.isPresent() && book.isPresent()) {
            Cart cartDetails = new Cart(user.get(), book.get(), cartDTO.getQuantity());
            cartRepo.save(cartDetails);
            return cartDetails;

        } else
            throw new CartException(" userid and bookid is invalid");
    }

    @Override
    public List<Cart> getall() {
        List<Cart> cart = cartRepo.findAll();
        return cart;
    }

    @Override
    public Cart FindById(int id) {
        Optional<Cart> cart = cartRepo.findById(id);
        return cart.get();
    }

    @Override
    public void deleteById(int id) {
        Optional<Cart> findById = cartRepo.findById(id);
        if (findById.isPresent()){
            cartRepo.deleteById(id);
        } else throw new CartException("Id:"+id+" not present");

    }

    @Override
    public Cart editById(int id, CartDTO cartDTO) {
        Optional<Book> book =bookRepo.findById(cartDTO.getBookId());
        Cart editdata = cartRepo.findById(id).orElse(null);
        if (editdata != null) {
            editdata.setBook(book.get());
            editdata.setQuantity(cartDTO.getQuantity());
            return cartRepo.save(editdata);
        } else
            throw new CartException("Id:"+id+" is not present ");
    }

    @Override
    public Cart changeQuantity(int cartId, int quantity) {
        Cart cart = cartRepo.findById(cartId).orElse(null);
        if(cart == null){
            throw new CartException("id is not found");
        }
        cart.setQuantity(quantity);
        return cartRepo.save(cart);
    }
}
