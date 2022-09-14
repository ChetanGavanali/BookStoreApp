package com.example.bookstore.model;

import com.example.bookstore.dto.BookDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String bookName;
    String authorName;
    String  bookDescription;
    String bookImg;
    int price;
    int quantity;


    public Book(BookDTO bookDTO){
        this.bookName= bookDTO.getBookName();
        this.authorName=bookDTO.getAuthorName();
        this.bookDescription= bookDTO.getBookDescription();
        this.bookImg= bookDTO.getBookImg();
        this.price= bookDTO.getPrice();
        this.quantity= bookDTO.getQuantity();
    }
}