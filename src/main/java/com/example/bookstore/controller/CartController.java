package com.example.bookstore.controller;

import com.example.bookstore.dto.CartDTO;
import com.example.bookstore.dto.ResponseDTO;
import com.example.bookstore.model.Cart;
import com.example.bookstore.service.CartInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartInterface cartInterface;

    @PostMapping("/add")
    public ResponseEntity<ResponseDTO> addBook(@Valid @RequestBody CartDTO cartDTO) {
        Cart cart = cartInterface.addCart(cartDTO);
        ResponseDTO responseDTO = new ResponseDTO("cart details added", cart);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/getall")
    public ResponseEntity<ResponseDTO> GetAllBookDetails() {
        List<Cart> response = cartInterface.getall();
        ResponseDTO responseDto = new ResponseDTO(" All Book with Details", response);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @GetMapping("/get/{Id}")
    public ResponseEntity<ResponseDTO> FindById(@PathVariable int Id) {
        Cart response = cartInterface.FindById(Id);
        ResponseDTO responseDto = new ResponseDTO("All Details of Book using Id", response);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{Id}")
    public ResponseEntity<ResponseDTO> deleteById(@PathVariable int Id) {
        cartInterface.deleteById(Id);
        ResponseDTO reponseDTO = new ResponseDTO("Cart Data deleted successfully", "Id:" + Id + " is deleted");
        return new ResponseEntity(reponseDTO, HttpStatus.ACCEPTED);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDTO> editData(@PathVariable int id, @Valid @RequestBody CartDTO cardDto) {
        Cart response = cartInterface.editById(id, cardDto);
        ResponseDTO responseDTO = new ResponseDTO("Updated Book Details Successfully", response);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @PostMapping("/update-qty")
    public ResponseEntity<ResponseDTO> changeBookQuantity(@RequestParam int cartId, @RequestParam int quantity) {
        Cart cart = cartInterface.changeQuantity(cartId, quantity);
        ResponseDTO responseDTO = new ResponseDTO("Cart quantity changed successfully", cart);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }


}
