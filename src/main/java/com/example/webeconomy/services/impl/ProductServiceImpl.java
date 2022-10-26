package com.example.webeconomy.services.impl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.webeconomy.data.repositories.*;
import com.example.webeconomy.data.entities.*;
import com.example.webeconomy.dto.request.*;
import com.example.webeconomy.dto.response.*;
import com.example.webeconomy.exceptions.*;
import com.example.webeconomy.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
    Date date;
    LocalDate currentDate = LocalDate.now();
    
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private ModelMapper modelMapper;

    private ProductCustomerId productCustomerId;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper){
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<Product> getAllProducts(){
        return this.productRepository.findAll();
    }

    @Override
    public List<Product> getAllProductsByCategoryId (int categoryId){
        return this.productRepository.findByCategoryId(categoryId);
    }

    @Override
    public CartResponseDto addToCart(CartUpdateDto dto){        
        Cart cart;
        productCustomerId=dto.getId();
        Optional<Cart> cartOptional = cartRepository.findById(productCustomerId);
        if (cartOptional.isEmpty()){
            cart = new Cart(productCustomerId,dto.getQuantity());
        } else 
            {
                cart=cartOptional.get();
                cart.setQuantity(cart.getQuantity()+dto.getQuantity());
            }
        cart = cartRepository.save(cart);
        return modelMapper.map(cart, CartResponseDto.class);
    }
    @Override
    public RatingResponseDto addRating (RatingUpdateDto dto){        
        Rating saveRating;
        Boolean isBought = false;
        productCustomerId=dto.getId();
        List<Order> order = orderRepository.findByCustomerId(dto.getId().getCustomerId());
        for (Order findProduct : order) {
            List<OrderDetail> orderDetails = orderDetailRepository.findByIdOrderId(findProduct.getId());
            for (OrderDetail orderDetail : orderDetails) {
                Long productId=orderDetail.getId().getProductId();
                if (productId==dto.getId().getProductId()){
                    isBought = true;
                }
            }
        }
        if (isBought==false){
            throw new ValidationException("Haven't bought this product yet");
        }
        Optional<Rating> ratingOptional = ratingRepository.findById(productCustomerId);
        if (ratingOptional.isEmpty()){
            saveRating = new Rating(productCustomerId,dto.getPoint());
        } else {
            saveRating=ratingOptional.get();
            saveRating.setPoint(dto.getPoint());
        }
        saveRating = ratingRepository.save(saveRating);
        List<Rating> listRatings = ratingRepository.findByIdProductId(productCustomerId.getProductId());
        int countRating=0;
        int sumRating=0;
        for (Rating rating : listRatings) {
            countRating+=1;
            sumRating+=rating.getPoint();
        }
        Optional<Product> productOptional = productRepository.findById(productCustomerId.getProductId());
        Product saveProduct = productOptional.get();
        saveProduct.setRate(sumRating/countRating);
        saveProduct=productRepository.save(saveProduct);
        return modelMapper.map(saveRating, RatingResponseDto.class);
    }  

    @Override
    public Product getProductById(Long id){
        Optional<Product> productOptional = this.productRepository.findById(id);

        if (productOptional.isEmpty()){
            throw new ResourceNotFoundException("Product Not Found with Controller Advice");
        }
        Product product = productOptional.get();
        return product;

    }
    @Override
    public ProductResponseDto createProduct(ProductUpdateDto dto){
        Product product = modelMapper.map(dto,Product.class);
        product.setCount(0);
        product.setCreateDate(Date.valueOf(currentDate));
        product.setUpdateDate(Date.valueOf(currentDate));
        product.setRate(0);
        product.setStatus(true);
        Product savedProduct = productRepository.save(product);
        return modelMapper.map(savedProduct, ProductResponseDto.class);
    }
    @Override
    public ProductResponseDto updateProduct(Long id, ProductUpdateDto dto){
        Optional<Product> productOptional = this.productRepository.findById(id);
        if (productOptional.isEmpty()){
            throw new ResourceNotFoundException("Product Not Found");
        }
        
        Product product = productOptional.get();
        product.setUpdateDate(Date.valueOf(currentDate));
        modelMapper.map(dto,product);
        Product savedProduct = productRepository.save(product);
        return modelMapper.map(savedProduct, ProductResponseDto.class);
    }
}
