package com.example.bookstore.service;


import com.example.bookstore.dto.OrderDTO;
import com.example.bookstore.model.Order;

import java.util.List;

public interface OrderInterface {

    String addOrder(OrderDTO orderDTO);

    List<com.example.bookstore.model.Order> getall();

    Order FindById(int id);

    void getOrderbyId(int id);

    String editById(int id, OrderDTO orderDTO);


}
