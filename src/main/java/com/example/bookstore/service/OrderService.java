package com.example.bookstore.service;

import com.example.bookstore.model.Order;
import com.example.bookstore.model.User;
import com.example.bookstore.dto.OrderDTO;
import com.example.bookstore.exception.OrderException;
import com.example.bookstore.model.Book;
import com.example.bookstore.repo.BookRepo;
import com.example.bookstore.repo.CartRepo;
import com.example.bookstore.repo.OrderRepo;
import com.example.bookstore.repo.UserRepo;
import com.example.bookstore.util.EmailSenderService;
import com.example.bookstore.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements OrderInterface {

    @Autowired
    OrderRepo orderRepo;

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
    public String addOrder(OrderDTO orderDTO) {
        Optional<User> user = userRepo.findById(orderDTO.getUserid());
        Optional<Book> book = bookRepo.findById(orderDTO.getBookId());
        if (user.isPresent() && book.isPresent()) {
            Order orderDetails = new Order(orderDTO.getDate(),orderDTO.getPrice(), orderDTO.getQuantity(),
                    orderDTO.getAddress(),user.get(), book.get()
                    ,orderDTO.isCancel());
            orderRepo.save(orderDetails);
            String token = tokenUtil.createToken(orderDetails.getOrderID());
            emailSender.sendEmail(user.get().getEmail(), "Added Your Details", "token" + token);
            return token;

        } else
            throw new OrderException(" userid and bookid is invalid");
    }

    @Override
    public List<Order> getall() {
        List<Order> order = orderRepo.findAll();
        return order;
    }

    @Override
    public Order FindById(int id) {
        Optional<Order> order = orderRepo.findById(id);
        return order.get();
    }

    @Override
    public void getOrderbyId(int id) {
        Optional<Order> findById = orderRepo.findById(id);
        if (findById.isPresent()){
            orderRepo.deleteById(id);
        } else throw new OrderException("Id:"+id+" not present");
    }

    @Override
    public String editById(int id, OrderDTO orderDTO) {
        Order editorder = orderRepo.findById(id).orElse(null);
        Optional<Book> book =bookRepo.findById(orderDTO.getBookId());
        Optional<User> user =userRepo.findById(orderDTO.getUserid());
        if (editorder != null) {
            editorder.setPrice(orderDTO.getPrice());
            editorder.setQuantity(orderDTO.getQuantity());
            editorder.setAddress(orderDTO.getAddress());
            editorder.setUser(user.get());
            editorder.setBook(book.get());
            editorder.setCancel(orderDTO.isCancel());
            orderRepo.save(editorder);
            String token = tokenUtil.createToken(editorder.getOrderID());
            emailSender.sendEmail(user.get().getEmail(), "Added Your Details", "http://localhost:8080/user/retrieve/" + token);
            return token;
        } else
            throw new OrderException("Id:" + id + " is not present ");
    }


}