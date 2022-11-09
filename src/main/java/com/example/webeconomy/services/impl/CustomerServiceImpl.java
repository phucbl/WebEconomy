package com.example.webeconomy.services.impl;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.webeconomy.data.repositories.*;
import com.example.webeconomy.constants.OrderStatus;
import com.example.webeconomy.data.entities.*;
import com.example.webeconomy.dto.request.*;
import com.example.webeconomy.dto.response.*;
import com.example.webeconomy.exceptions.*;
import com.example.webeconomy.services.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
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
    private OrderDetailid orderDetailId;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper) {
        this.customerRepository = customerRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CustomerResponseDto> getAllCustomers() {
        List<Customer> customers = this.customerRepository.findAll();
        List<CustomerResponseDto> customerResponseDtos = modelMapper.map(customers,
                new TypeToken<List<CustomerResponseDto>>() {
                }.getType());
        for (CustomerResponseDto customerResponseDto : customerResponseDtos) {
            Optional<Account> accountOptional = accountRepository.findById(customerResponseDto.getAccountId());
            if (accountOptional.isEmpty()) {
                throw new ResourceNotFoundException("Account Not Found with Controller Advice");
            }
            customerResponseDto.setPhoneNumber(accountOptional.get().getPhoneNumber());
            customerResponseDto.setStatus(accountOptional.get().isStatus());
        }
        return customerResponseDtos;
    }

    @Override
    public List<CartResponseDto> getCartByCustomerId(Long id) {
        List<Cart> carts = cartRepository.findByIdCustomerId(id);
        List<CartResponseDto> cartResponseDtos = modelMapper.map(carts, new TypeToken<List<CartResponseDto>>() {
        }.getType());
        for (CartResponseDto cartResponseDto : cartResponseDtos) {
            Product product = productRepository.findById(cartResponseDto.getId().getProductId()).get();
            cartResponseDto.setProductImageUrl(product.getImageUrl());
            cartResponseDto.setProductName(product.getName());
            cartResponseDto.setProductPrice(product.getPrice());
        }
        return cartResponseDtos;
    }

    @Override
    public List<Order> getOrdersByCustomerId() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String phoneNumber = userDetails.getUsername();
        Optional<Account> accountOptional = accountRepository.findByPhoneNumber(phoneNumber);
        if (accountOptional.isEmpty()) {
            throw new ResourceNotFoundException("Account Not Found with Controller Advice");
        }

        Optional<Customer> customerOptional = this.customerRepository.findByAccountId(accountOptional.get().getId());

        if (customerOptional.isEmpty()) {
            throw new ResourceNotFoundException("Customer Not Found with Controller Advice");
        }
        Customer customer = customerOptional.get();
        return orderRepository.findByCustomerId(customer.getId());
    }

    @Override
    public OrderResponseDto createOrder(CreateOrderDto createOrderDto) {

        List<Cart> carts = createOrderDto.getCarts();
        LocalDateTime localDateTime = LocalDateTime.now();
        Order order = new Order(localDateTime.toLocalDate().toString(), OrderStatus.CHECKING,
                createOrderDto.getCustomerId());
        Order savedOrder = orderRepository.save(order);
        Long orderId = savedOrder.getId();
        for (Cart cart : carts) {
            orderDetailId = new OrderDetailid(orderId, cart.getId().getProductId());
            Optional<Product> productOptional = productRepository.findById(orderDetailId.getProductId());
            orderDetail = new OrderDetail(orderDetailId, productOptional.get().getPrice(), cart.getQuantity());
            orderDetailRepository.save(orderDetail);
            cartRepository.delete(cart);
        }

        return modelMapper.map(savedOrder, OrderResponseDto.class);
    }

    @Override
    public CustomerResponseDto getCustomerById(Long id) {

        Optional<Customer> customerOptional = this.customerRepository.findById(id);

        if (customerOptional.isEmpty()) {
            throw new ResourceNotFoundException("Customer Not Found with Controller Advice");
        }
        Customer customer = customerOptional.get();
        Optional<Account> accountOptional = accountRepository.findById(customer.getAccountId());
        if (accountOptional.isEmpty()) {
            throw new ResourceNotFoundException("Account Not Found with Controller Advice");
        }

        CustomerResponseDto customerResponseDto = modelMapper.map(customer, CustomerResponseDto.class);
        customerResponseDto.setPhoneNumber(accountOptional.get().getPhoneNumber());
        return customerResponseDto;
    }

    @Override
    public CustomerResponseDto getCustomerByToken() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String phoneNumber = userDetails.getUsername();
        Optional<Account> accountOptional = accountRepository.findByPhoneNumber(phoneNumber);
        if (accountOptional.isEmpty()) {
            throw new ResourceNotFoundException("Account Not Found with Controller Advice");
        }

        Optional<Customer> customerOptional = this.customerRepository.findByAccountId(accountOptional.get().getId());

        if (customerOptional.isEmpty()) {
            throw new ResourceNotFoundException("Customer Not Found with Controller Advice");
        }
        Customer customer = customerOptional.get();

        CustomerResponseDto customerResponseDto = modelMapper.map(customer, CustomerResponseDto.class);
        customerResponseDto.setPhoneNumber(accountOptional.get().getPhoneNumber());
        return customerResponseDto;
    }

    @Override
    public CustomerResponseDto createCustomer(CustomerUpdateDto dto) {
        Customer customer = modelMapper.map(dto, Customer.class);
        customerRepository.save(customer);
        Optional<Account> accountOptional = accountRepository.findById(customer.getAccountId());
        if (accountOptional.isEmpty()) {
            throw new ResourceNotFoundException("Account Not Found with Controller Advice");
        }
        CustomerResponseDto customerResponseDto = modelMapper.map(customer, CustomerResponseDto.class);
        customerResponseDto.setPhoneNumber(accountOptional.get().getPhoneNumber());
        return customerResponseDto;
    }

    @Override
    public CustomerResponseDto updateCustomer(Long id, CustomerUpdateDto dto) {
        Optional<Customer> customerOptional = this.customerRepository.findById(id);
        if (customerOptional.isEmpty()) {
            throw new ResourceNotFoundException("Customer Not Found");
        }

        Customer customer = customerOptional.get();
        modelMapper.map(dto, customer);
        customer = customerRepository.save(customer);
        Optional<Account> accountOptional = accountRepository.findById(customer.getAccountId());
        if (accountOptional.isEmpty()) {
            throw new ResourceNotFoundException("Account Not Found with Controller Advice");
        }
        CustomerResponseDto customerResponseDto = modelMapper.map(customer, CustomerResponseDto.class);
        customerResponseDto.setPhoneNumber(accountOptional.get().getPhoneNumber());
        return customerResponseDto;
    }

    @Override
    public CartResponseDto updateCart(CartUpdateDto dto) {
        Optional<Cart> cartOptional = cartRepository.findById(dto.getId());
        if (cartOptional.isEmpty()) {
            throw new ResourceNotFoundException("Cart Item Not Found");
        }
        Cart cart = cartOptional.get();
        cart.setQuantity(dto.getQuantity());
        cart.setId(dto.getId());
        cart.setCheck(dto.isCheck());
        cart = cartRepository.save(cart);
        return modelMapper.map(cart, CartResponseDto.class);
    }

    @Override
    public ResponseEntity<ResponseDto> deleteCart(ProductCustomerId id) {
        Optional<Cart> cartOptional = cartRepository.findById(id);
        if (cartOptional.isEmpty()) {
            throw new ResourceNotFoundException("Cart Item Not Found");
        }
        Cart cart = cartOptional.get();
        cartRepository.delete(cart);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(null, "Delete Successfully!", "200"));
    }

    @Override
    public ResponseEntity<ResponseDto> changePassword(ChangePasswordDto dto) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String phoneNumber = userDetails.getUsername();
        Optional<Account> accountOptional = accountRepository.findByPhoneNumber(phoneNumber);
        if (accountOptional.isEmpty()) {
            throw new ResourceNotFoundException("Account Not Found with Controller Advice");
        }
        Account account = accountOptional.get();
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!encoder.matches(dto.getPassword(), account.getPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseDto(null, "Invalid password!", "400"));
        }
        account.setPassword(encoder.encode(dto.getNewPassword()));
        accountRepository.save(account);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(null, "Change Password Successfully!", "200"));
    }

    @Override
    public ResponseEntity<ResponseDto> checkAccountExisted(String phoneNumber) {
        Optional<Account> accountOptional = accountRepository.findByPhoneNumber(phoneNumber);
        if (accountOptional.isPresent()) {
            throw new ItemExistException("Phone number is already signed up");
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(null, "Phone number can be register", "200"));
    }
}
