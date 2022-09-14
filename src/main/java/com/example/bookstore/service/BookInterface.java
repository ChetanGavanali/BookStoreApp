package com.example.bookstore.service;

import com.example.bookstore.dto.BookDTO;
import com.example.bookstore.model.Book;

import java.util.List;

public interface BookInterface {

    String insertBookDetails(BookDTO bookDTO);

    List<Book> getall();

    Book FindById(int id);

    Book getByBook(String bookName);

    void getBookbyId(int id);

    String editById(int id, BookDTO bookDTO);

    List<Book> sortPriceLowToHigh();

    List<Book> sortPriceHighToLow();


    Book changeBookQty(int id, int quantity);
}
