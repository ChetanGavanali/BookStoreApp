package com.example.bookstore.service;

import com.example.bookstore.dto.BookDTO;
import com.example.bookstore.exception.BookException;
import com.example.bookstore.model.Book;
import com.example.bookstore.repo.BookRepo;
import com.example.bookstore.util.EmailSenderService;
import com.example.bookstore.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService implements BookInterface {
    @Autowired
    BookRepo bookRepo;
    @Autowired
    TokenUtil tokenUtil;
    @Autowired
    EmailSenderService emailSender;

    @Override
    public String insertBookDetails(BookDTO bookDTO) {
        Book book = new Book(bookDTO);
        Book newBook = bookRepo.save(book);
        String token = tokenUtil.createToken(book.getId());
        return token;
    }

    @Override
    public List<Book> getall() {
        List<Book> book = bookRepo.findAll();
        return book;
    }

    @Override
    public Book FindById(int id) {
        Optional<Book> book = bookRepo.findById(id);
        return book.get();
    }

    @Override
    public Book getByBook(String bookName) {
        Book bookList = bookRepo.findByName(bookName);
        if (bookList != null) {
            bookRepo.findByName("bookname");
            return bookList;

        } else throw new BookException(" Book with name found!");

    }

    @Override
    public void getBookbyId(int id) {
        Optional<Book> book = bookRepo.findById(id);
        if (book.isPresent()) {
            bookRepo.deleteById(id);

        } else throw new BookException("Id:" + id + " not present");

    }

    @Override
    public String editById(int id, BookDTO BookDTO) {
        Book editbook = bookRepo.findById(id).orElse(null);
        if (editbook != null) {
            editbook.setBookName(BookDTO.getBookName());
            editbook.setAuthorName(BookDTO.getAuthorName());
            editbook.setBookDescription(BookDTO.getBookDescription());
            editbook.setBookImg(BookDTO.getBookImg());
            editbook.setPrice(BookDTO.getPrice());
            editbook.setQuantity(BookDTO.getQuantity());
            bookRepo.save(editbook);
            String token = tokenUtil.createToken(editbook.getId());
            return token;
        } else
            throw new BookException("Id:" + id + " is not present ");
    }

    @Override
    public List<Book> sortPriceLowToHigh() {
        List<Book> getSortedList = bookRepo.getSortedListOfBooksInAsc();
        return getSortedList;

    }

    @Override
    public List<Book> sortPriceHighToLow() {
        List<Book> getSortedListInDesc = bookRepo.getSortedListOfBooksInDesc();
        return getSortedListInDesc;

    }

    @Override
    public Book changeBookQty(int id, int qunatity) {
        Book book = bookRepo.findById(id).orElse(null);
        if(book == null){
            throw new BookException("Id is not found");
        }
        book.setQuantity(qunatity);
        return bookRepo.save(book);
    }


}