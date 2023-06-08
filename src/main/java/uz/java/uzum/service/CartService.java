package uz.java.uzum.service;

import uz.java.uzum.dto.CartDto;
import uz.java.uzum.dto.ProductDto;
import uz.java.uzum.dto.ResponseDto;
import uz.java.uzum.entity.Cart;
import uz.java.uzum.entity.Product;
import uz.java.uzum.entity.User;

import java.util.List;

public interface CartService {
    Boolean createCart(User user);
    ResponseDto<CartDto> addToCart(Integer cartId, Integer productId);
    void removeFromCart(Cart cart, Product product);
    void clearCart(Cart cart);
    ResponseDto<List<ProductDto>> getUserCart(Integer userId);
}
