package com.example.webeconomy.services.impl;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.webeconomy.data.repositories.*;
import com.example.webeconomy.constants.OrderStatus;
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
    private AccountRepository accountRepository;
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
    
    private UserDetails userDetails;

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
    public OrderResponseDto createOrder (CreateOrderDto createOrderDto){
        List<Cart> carts = createOrderDto.getCarts();
        Order order = new Order(Calendar.getInstance().getTime(),OrderStatus.CHECKING,1);
        Order savedOrder = orderRepository.save(order);
        // Long orderId = savedOrder.getId();
        // for (Cart cart : carts) {
        //     orderDetailId = new OrderDetailId(orderId,cart.getId().getProductId());
        //     Optional<Product> productOptional = productRepository.findById(orderDetailId.getProductId());
        //     orderDetail = new OrderDetail(orderDetailId,productOptional.get().getPrice(),cart.getQuantity());
        //     orderDetail = orderDetailRepository.save(orderDetail);
        //     // cartRepository.delete(cart);
        // }
        
        return modelMapper.map(savedOrder, OrderResponseDto.class);
    }


    @Override
    public CustomerResponseDto getCustomerById(Long id){

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                        .getPrincipal();
        String phoneNumber = userDetails.getUsername();
        Optional<Account> accountOptional = accountRepository.findByPhoneNumber(phoneNumber);
        if (accountOptional.isEmpty()){
            throw new ResourceNotFoundException("Account Not Found with Controller Advice");
        }

        Optional<Customer> customerOptional = this.customerRepository.findByAccountId(accountOptional.get().getId());

        if (customerOptional.isEmpty()){
            throw new ResourceNotFoundException("Customer Not Found with Controller Advice");
        }
        Customer customer = customerOptional.get();
        
        
        CustomerResponseDto customerResponseDto = modelMapper.map(customer, CustomerResponseDto.class);
        customerResponseDto.setPhoneNumber(accountOptional.get().getPhoneNumber());
        return customerResponseDto;
    }
    @Override
    public CustomerResponseDto createCustomer(CustomerUpdateDto dto){
        Customer customer = modelMapper.map(dto,Customer.class);
        customerRepository.save(customer);
        Optional<Account> accountOptional = accountRepository.findById(customer.getAccountId());
        if (accountOptional.isEmpty()){
            throw new ResourceNotFoundException("Account Not Found with Controller Advice");
        }
        CustomerResponseDto customerResponseDto = modelMapper.map(customer, CustomerResponseDto.class);
        customerResponseDto.setPhoneNumber(accountOptional.get().getPhoneNumber());
        return customerResponseDto;
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
        Optional<Account> accountOptional = accountRepository.findById(customer.getAccountId());
        if (accountOptional.isEmpty()){
            throw new ResourceNotFoundException("Account Not Found with Controller Advice");
        }
        CustomerResponseDto customerResponseDto = modelMapper.map(customer, CustomerResponseDto.class);
        customerResponseDto.setPhoneNumber(accountOptional.get().getPhoneNumber());
        return customerResponseDto;
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
        cart.setCheck(dto.isCheck());
        cartRepository.save(cart);
        return modelMapper.map(cart, CartResponseDto.class);
    }

    @Override
    public ResponseEntity<ResponseDto> deleteCart(ProductCustomerId id){    
        Optional<Cart> cartOptional = cartRepository.findById(id);
        if (cartOptional.isEmpty()){
            throw new ResourceNotFoundException("Cart Item Not Found");
        }
        Cart cart = cartOptional.get();
        cartRepository.delete(cart);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(null, "Delete Successfully!","200"));
    }
}
