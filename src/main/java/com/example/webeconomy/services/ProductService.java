package com.example.webeconomy.services;
import java.util.List;
import org.springframework.stereotype.Service;
import com.example.webeconomy.data.entities.*;
import com.example.webeconomy.dto.request.*;
import com.example.webeconomy.dto.response.*;

@Service
public interface ProductService {

    public List<Product> getAllProducts();
    public List<Product> getAllProductsByCategoryId(int categoryId);
    public Product getProductById(Long id);
    public CartResponseDto addToCart(CartUpdateDto dto);
    public RatingResponseDto addRating(RatingUpdateDto dto);
    public ProductResponseDto createProduct(ProductUpdateDto dto);
    public ProductResponseDto updateProduct(Long id, ProductUpdateDto dto);

}
