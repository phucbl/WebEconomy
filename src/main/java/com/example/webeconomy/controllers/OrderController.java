package com.example.webeconomy.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.webeconomy.dto.response.OrderResponseDto;
import com.example.webeconomy.data.entities.Order;
import com.example.webeconomy.dto.request.OrderUpdateDto;
import com.example.webeconomy.services.OrderService;

import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("/order")
public class OrderController {
    private OrderService orderService;
    
    @Autowired
    OrderController (OrderService orderService){
        this.orderService = orderService;
    }

    @GetMapping
    List<Order> getOrders(){
        return orderService.getAllOrders();
    }
    @GetMapping("/")
    List<Order> getOrdersByCustomerId(@RequestParam("customerId") Long customerId){
        return orderService.getOrderByCustomerId(customerId);
    }
    @GetMapping("/{id}")
    Order getOrderById(@PathVariable("id") Long id){
        return orderService.getOrderById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    OrderResponseDto createOrder(@RequestBody OrderUpdateDto dto){
        return this.orderService.createOrder(dto);
    }
    
    @PutMapping("/{id}")
    OrderResponseDto updateOrder(@PathVariable("id") Long id, @Valid @RequestBody OrderUpdateDto dto){
        return this.orderService.updateOrder(id,dto);
    }

}

