package com.example.webeconomy.services.impl;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.webeconomy.data.repositories.*;
import com.example.webeconomy.data.entities.*;
import com.example.webeconomy.dto.request.*;
import com.example.webeconomy.dto.response.*;
import com.example.webeconomy.exceptions.*;
import com.example.webeconomy.services.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ModelMapper modelMapper;

    private ProductCustomerId productCustomerId;

    private OrderDetail orderDetail;
    private OrderDetailId orderDetailId;
    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper){
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Customer> getAllCustomers(){
        return this.customerRepository.findAll();
    } 

    @Override
    public List<Cart> getCartByCustomerId(Long id){
        return cartRepository.findByIdCustomerId(id);
    }
    @Override
    public List<Order> getOrderByCustomerId(Long id){
        return orderRepository.findByCustomerId(id);
    }

    @Override
    public OrderResponseDto createOrder (CustomerCreateOrderUpdateDto customerCreateOrderUpdateDto){
        OrderUpdateDto dto=customerCreateOrderUpdateDto.getDto();
        List<Cart> carts =customerCreateOrderUpdateDto.getCarts();
        dto.setDateCreated(Calendar.getInstance().getTime());
        Order order = modelMapper.map(dto,Order.class);
        order.setStatus(0);
        Order savedOrder = orderRepository.save(order);
        Long orderId = savedOrder.getId();
        for (Cart cart : carts) {
            orderDetailId = new OrderDetailId(orderId,cart.getId().getProductId());
            Optional<Product> productOptional = productRepository.findById(orderDetailId.getProductId());
            orderDetail = new OrderDetail(orderDetailId,productOptional.get().getPrice(),cart.getQuantity());
            orderDetail = orderDetailRepository.save(orderDetail);
            cartRepository.delete(cart);
        }
        return modelMapper.map(savedOrder, OrderResponseDto.class);
    }


    @Override
    public Customer getCustomerById(Long id){
        Optional<Customer> customerOptional = this.customerRepository.findById(id);

        if (customerOptional.isEmpty()){
            throw new ResourceNotFoundException("Customer Not Found with Controller Advice");
        }
        Customer customer = customerOptional.get();
        return customer;

    }
    @Override
    public CustomerResponseDto createCustomer(CustomerUpdateDto dto){
        Customer customer = modelMapper.map(dto,Customer.class);
        Customer savedCustomer = customerRepository.save(customer);
        return modelMapper.map(savedCustomer, CustomerResponseDto.class);
    }
    @Override
    public CustomerResponseDto updateCustomer(Long id, CustomerUpdateDto dto){
        Optional<Customer> customerOptional = this.customerRepository.findById(id);
        if (customerOptional.isEmpty()){
            throw new ResourceNotFoundException("Customer Not Found");
        }
        
        Customer customer = customerOptional.get();
        modelMapper.map(dto,customer);
        customer = customerRepository.save(customer);
        return modelMapper.map(customer, CustomerResponseDto.class);
    }
    @Override
    public CartResponseDto updateCart(CartUpdateDto dto){
        Optional<Cart> cartOptional = cartRepository.findById(dto.getId());
        if (cartOptional.isEmpty()){
            throw new ResourceNotFoundException("Cart Item Not Found");
        }
        Cart cart = cartOptional.get();
        cart.setQuantity(dto.getQuantity());
        cart = cartRepository.save(cart);
        cart.setId(dto.getId());
        return modelMapper.map(cart, CartResponseDto.class);
    }

    @Override
    public HttpStatus deleteCart(Long productId, Long customerId){        
        productCustomerId.setCustomerId(customerId);
        productCustomerId.setProductId(productId);
        Optional<Cart> cartOptional = cartRepository.findById(productCustomerId);
        if (cartOptional.isEmpty()){
            throw new ResourceNotFoundException("Cart Item Not Found");
        }
        Cart cart = cartOptional.get();
        cartRepository.delete(cart);
        return HttpStatus.ACCEPTED;
    }
}
