package com.example.webeconomy.services.impl;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.webeconomy.data.repositories.*;
import com.example.webeconomy.constants.OrderStatus;
import com.example.webeconomy.data.entities.*;
import com.example.webeconomy.dto.request.OrderUpdateDto;
import com.example.webeconomy.dto.response.OrderDetailResponseDto;
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
        return orderRepository.findByCustomerId(customerId);
    }  
    @Override
    public OrderResponseDto getOrderById(Long id){
        Optional<Order> orderOptional = this.orderRepository.findById(id);
        if (orderOptional.isEmpty()){
            throw new ResourceNotFoundException("Order Not Found with Controller Advice");
        }
        Order order = orderOptional.get();
        List<OrderDetail> orderDetails = orderDetailRepository.findByIdOrderId(order.getId());
        List<OrderDetailResponseDto> orderDetailResponseDtos = modelMapper.map(orderDetails,new TypeToken<List<OrderDetailResponseDto>>() {}.getType());
        for (OrderDetailResponseDto orderDetailResponseDto : orderDetailResponseDtos) {
            Long productId=orderDetailResponseDto.getId().getProductId();
            Optional<Product> productOptional = productRepository.findById(productId);
            Product product=productOptional.get();
            orderDetailResponseDto.setProductName(product.getName());
            orderDetailResponseDto.setImageUrl(product.getImageUrl());
        }
        OrderResponseDto orderResponseDto = modelMapper.map(order, OrderResponseDto.class);
        orderResponseDto.setOrderDetailResponseDtos(orderDetailResponseDtos);
        return  orderResponseDto;
    }
    @Override
    public OrderResponseDto createOrder(OrderUpdateDto dto){
        Order order = modelMapper.map(dto,Order.class);
        order.setDateCreated(Calendar.getInstance().getTime().toString().split(" ")[0]);
        order.setStatus(OrderStatus.CHECKING);
        Order savedOrder = orderRepository.save(order);
        return modelMapper.map(savedOrder, OrderResponseDto.class);
    }
    @Override
    public OrderResponseDto updateOrder(Long id, OrderUpdateDto dto){
        Optional<Order> orderOptional = this.orderRepository.findById(id);
        if (orderOptional.isEmpty()){
            throw new ResourceNotFoundException("Order Not Found");
        }
        Order order = orderOptional.get();
        OrderStatus lastStatus = order.getStatus();
        if (lastStatus==OrderStatus.CANCELED) throw new BadRequestException("This order was canceled");
        order.setStatus(dto.getStatus());
        order = orderRepository.save(order);
        if (order.getStatus()==OrderStatus.DONE&&lastStatus!=OrderStatus.DONE){
            List<OrderDetail> orderDetails = orderDetailRepository.findByIdOrderId(id);
            for (OrderDetail orderDetail : orderDetails) {
                Optional<Product> productOptional = productRepository.findById(orderDetail.getId().getProductId());
                Product product = productOptional.get();
                product.setCount(product.getCount()+orderDetail.getQuantity());
                product = productRepository.save(product);
            }
        }
        return modelMapper.map(order, OrderResponseDto.class);
    }

    @Override
    public OrderResponseDto cancelOrder (Long id){
        Optional<Order> orderOptional = this.orderRepository.findById(id);
        if (orderOptional.isEmpty()){
            throw new ResourceNotFoundException("Order Not Found");
        }
        Order order = orderOptional.get();
        order.setStatus(OrderStatus.CANCELED);
        order = orderRepository.save(order);
        return modelMapper.map(order, OrderResponseDto.class);
    }
}

