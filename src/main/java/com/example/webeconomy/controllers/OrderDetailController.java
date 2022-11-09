package com.example.webeconomy.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.webeconomy.dto.response.OrderDetailResponseDto;
import com.example.webeconomy.data.entities.OrderDetail;
import com.example.webeconomy.dto.request.OrderDetailUpdateDto;
import com.example.webeconomy.services.OrderDetailService;

import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("/orderDetail")
public class OrderDetailController {
    private OrderDetailService orderDetailService;
    
    @Autowired
    OrderDetailController (OrderDetailService orderDetailService){
        this.orderDetailService = orderDetailService;
    }

    @GetMapping
    List<OrderDetail> getOrderDetails(){
        return orderDetailService.getAllOrderDetails();
    }
    @GetMapping("/")
    List<OrderDetailResponseDto> getOrderDetailsByCustomerId(@RequestParam("orderId") Long orderId){
        return orderDetailService.getOrderDetailByOrderId(orderId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    OrderDetailResponseDto createOrderDetail(@RequestBody OrderDetailUpdateDto dto){
        return this.orderDetailService.createOrderDetail(dto);
    }
    
    @PutMapping()
    OrderDetailResponseDto updateOrderDetail(@Valid @RequestBody OrderDetailUpdateDto dto){
        return this.orderDetailService.updateOrderDetail(dto);
    }

}

