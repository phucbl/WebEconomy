package com.example.webeconomy.services.impl;
import org.springframework.stereotype.Service;
import com.example.webeconomy.services.CartService;


@Service
public class CartServiceImpl implements CartService {
    // private CartRepository cartRepository;
    // @Autowired
    // private ProductCustomerId productCustomerId;
    // @Autowired
    // private ModelMapper modelMapper;

    // @Autowired
    // public CartServiceImpl (CartRepository cartRepository){
    //     this.cartRepository=cartRepository;
    // }


    // @Override
    // public CartResponseDto createCart(CartUpdateDto dto){
    //     Cart cart = modelMapper.map(dto,Cart.class);
    //     Cart savedCart = cartRepository.save(cart);
    //     return modelMapper.map(savedCart, CartResponseDto.class);
    // }

    // @Override
    // public CartResponseDto updateCart(CartUpdateDto dto){
    //     Optional<Cart> cartOptional = cartRepository.findById(dto.getId());
    //     if (cartOptional.isEmpty()){
    //         throw new ResourceNotFoundException("Cart Item Not Found");
    //     }
    //     Cart cart = cartOptional.get();
    //     cart.setQuantity(dto.getQuantity());
    //     cart = cartRepository.save(cart);
    //     cart.setId(dto.getId());
    //     return modelMapper.map(cart, CartResponseDto.class);
    // }

    // @Override
    // public HttpStatus deleteCart(Long productId, Long customerId){        
    //     productCustomerId.setCustomerId(customerId);
    //     productCustomerId.setProductId(productId);
    //     Optional<Cart> cartOptional = cartRepository.findById(productCustomerId);
    //     if (cartOptional.isEmpty()){
    //         throw new ResourceNotFoundException("Cart Item Not Found");
    //     }
    //     Cart cart = cartOptional.get();
    //     cartRepository.delete(cart);
    //     return HttpStatus.ACCEPTED;
    // }

        
}
