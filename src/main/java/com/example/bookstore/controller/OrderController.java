package com.example.bookstore.controller;


import com.example.bookstore.dto.OrderDTO;
import com.example.bookstore.dto.ResponseDTO;
import com.example.bookstore.model.Order;
import com.example.bookstore.service.OrderInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderInterface orderInterface;

    @PostMapping("/add")
    public ResponseEntity<ResponseDTO> addBook(@Valid @RequestBody OrderDTO OrderDTO) {
        String response = orderInterface.addOrder(OrderDTO);
        ResponseDTO responseDTO = new ResponseDTO("order details added", response);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
    @GetMapping("/getall")
    public ResponseEntity<ResponseDTO> GetAllDetails() {
        List<Order> response = orderInterface.getall();
        ResponseDTO responseDto = new ResponseDTO(" All  order Details", response);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
    @GetMapping("/get/{Id}")
    public ResponseEntity<ResponseDTO> FindById(@PathVariable int Id) {
        Order response = orderInterface.FindById(Id);
        ResponseDTO responseDto = new ResponseDTO("All Details of order on this id using Id", response);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteById(@PathVariable int id) {
        orderInterface.getOrderbyId(id);
        ResponseDTO responseDTO = new ResponseDTO("Data deleted successfully", "Id:" + id + " is deleted");
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);

    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDTO> editData(@PathVariable int id, @Valid @RequestBody OrderDTO orderDTO) {
        String response = orderInterface.editById(id, orderDTO);
        ResponseDTO responseDTO = new ResponseDTO("Updated Book Details Successfully", response);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

}
