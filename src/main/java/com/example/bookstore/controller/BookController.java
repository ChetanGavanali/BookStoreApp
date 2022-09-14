package com.example.bookstore.controller;

import com.example.bookstore.dto.BookDTO;
import com.example.bookstore.dto.ResponseDTO;
import com.example.bookstore.model.Book;
import com.example.bookstore.service.BookInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    BookInterface bookInterface;

    @PostMapping("/insert")
    public ResponseEntity<ResponseDTO> insertBookDetails(@RequestBody BookDTO bookDTO) {
        String response = bookInterface.insertBookDetails(bookDTO);
        ResponseDTO responseDTO = new ResponseDTO("Book Details Added successfully", response);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/getall")
    public ResponseEntity<ResponseDTO> GetAllBookDetails() {
        List<Book> response = bookInterface.getall();
        ResponseDTO responseDTO = new ResponseDTO(" All Book Details", response);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/get/{Id}")
    public ResponseEntity<ResponseDTO> FindById(@PathVariable int Id) {
        Book response = bookInterface.FindById(Id);
        ResponseDTO responseDTO = new ResponseDTO("All Details of Book using Id", response);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/Book/{bookName}")
    public ResponseEntity<ResponseDTO> getDataByBook(@PathVariable String bookName) {
        Book response = bookInterface.getByBook(bookName);
        ResponseDTO responseDTO = new ResponseDTO("Book by Book Name", response);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteById(@PathVariable int id) {
        bookInterface.getBookbyId(id);
        ResponseDTO responseDTO = new ResponseDTO("Data deleted successfully", "Id:" + id + " is deleted");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }


    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDTO> editData(@PathVariable int id, @Valid @RequestBody BookDTO bookDTO) {
        String response = bookInterface.editById(id, bookDTO);
        ResponseDTO responseDTO = new ResponseDTO("Updated Book Details Successfully", response);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/sortByPriceAsc")
    public ResponseEntity<ResponseDTO> getBookByPriceAsc() {
        List<Book> bookData = bookInterface.sortPriceLowToHigh();
        ResponseDTO responseDTO = new ResponseDTO("Sorted all books by price low to high ", bookData);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/sortByPriceDsc")
    public ResponseEntity<ResponseDTO> getBookByPriceDsc() {
        List<Book> bookData = bookInterface.sortPriceHighToLow();
        ResponseDTO responseDTO = new ResponseDTO("Sorted all books by price high to low ", bookData);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PostMapping("/change-qty")
    public ResponseEntity<ResponseDTO> changeBookQuantity(@RequestParam int id, @RequestParam int quantity) {
        Book response =bookInterface.changeBookQty(id, quantity);
        ResponseDTO responseDTO = new ResponseDTO("Book quantity changed successfully", response);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }

}