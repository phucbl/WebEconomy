package com.example.webeconomy.services.impl;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.webeconomy.data.repositories.*;
import com.example.webeconomy.data.entities.*;
import com.example.webeconomy.dto.request.OrderUpdateDto;
import com.example.webeconomy.dto.response.OrderResponseDto;
import com.example.webeconomy.exceptions.*;
import com.example.webeconomy.services.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, ModelMapper modelMapper){
        this.orderRepository = orderRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Order> getAllOrders(){
        return this.orderRepository.findAll();
    }  
    @Override
    public List<Order> getOrderByCustomerId(Long customerId){
        return this.orderRepository.findByCustomerId(customerId);
    }  
    @Override
    public Order getOrderById(Long id){
        Optional<Order> orderOptional = this.orderRepository.findById(id);
        if (orderOptional.isEmpty()){
            throw new ResourceNotFoundException("Order Not Found with Controller Advice");
        }
        return orderOptional.get();
    }
    @Override
    public OrderResponseDto createOrder(OrderUpdateDto dto){
        dto.setDateCreated(Calendar.getInstance().getTime());
        Order order = modelMapper.map(dto,Order.class);
        Order savedOrder = orderRepository.save(order);
        return modelMapper.map(savedOrder, OrderResponseDto.class);
    }
    @Override
    public OrderResponseDto updateOrder(Long id, OrderUpdateDto dto){
        Optional<Order> OrderOptional = this.orderRepository.findById(id);
        if (OrderOptional.isEmpty()){
            throw new ResourceNotFoundException("Order Not Found");
        }
        Order order = OrderOptional.get();
        modelMapper.map(dto,order);
        order = orderRepository.save(order);
        if (order.getStatus()==2){
            List<OrderDetail> orderDetails = orderDetailRepository.findByIdOrderId(id);
            for (OrderDetail orderDetail : orderDetails) {
                Optional<Product> productOptional = productRepository.findById(orderDetail.getId().getProductId());
                Product product = productOptional.get();
                product.setCount(product.getCount()+orderDetail.getQuantity());
            }
        }
        return modelMapper.map(order, OrderResponseDto.class);
    }
}

