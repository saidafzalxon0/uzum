package uz.java.uzum.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.java.uzum.dto.CartDto;
import uz.java.uzum.dto.ProductDto;
import uz.java.uzum.dto.ResponseDto;
import uz.java.uzum.entity.Cart;
import uz.java.uzum.entity.Product;
import uz.java.uzum.entity.User;
import uz.java.uzum.repository.CartRepository;
import uz.java.uzum.repository.ProductRepository;
import uz.java.uzum.repository.UserRepository;
import uz.java.uzum.service.CartService;
import uz.java.uzum.service.mapper.CartMapper;
import uz.java.uzum.service.mapper.ProductMapper;

import java.util.List;
import java.util.Optional;

import static uz.java.uzum.service.appStatus.AppStatusCodes.*;
import static uz.java.uzum.service.appStatus.AppStatusMessages.*;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final CartMapper cartMapper;
    private final UserRepository usersRepository;
    private final ProductMapper productMapper;
    private final ProductRepository productRepository;
    @Override
    public Boolean createCart(User user) {
        try {
            Cart cart = new Cart();
            cart.setUser(user);
            cartRepository.save(cart);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public ResponseDto<CartDto> addToCart(Integer id, Integer productId) {
        try {
            Optional<Cart> byId = cartRepository.findById(id);
            Optional<Product> byId1 = productRepository.findById(productId);

            Cart cart = byId.get();
            cart.getProducts().add(byId1.get());
            Cart save = cartRepository.save(cart);
            return ResponseDto.<CartDto>builder()
                    .code(OK_CODE)
                    .success(true)
                    .message(OK)
                    .data(cartMapper.toDto(save))
                    .build();
        }catch (Exception e){
            return ResponseDto.<CartDto>builder()
                    .message(e.getMessage())
                    .build();
        }
    }

    @Override
    public void removeFromCart(Cart cart, Product product) {

    }

    @Override
    public void clearCart(Cart cart) {
        cartRepository.delete(cart);
    }

    @Override
    public ResponseDto<List<ProductDto>> getUserCart(Integer userId) {
        Optional<User> byId = usersRepository.findById(userId);
        if(byId.isEmpty()){
            return ResponseDto.<List<ProductDto>>builder()
                    .build();
        }
        Optional<Cart> byUserId = cartRepository.findByUserId(userId);
        return ResponseDto.<List<ProductDto>>builder()
                .data(byUserId.get().getProducts().stream().map(pr->productMapper.toDto(pr)).toList())
                .build();
    }
}