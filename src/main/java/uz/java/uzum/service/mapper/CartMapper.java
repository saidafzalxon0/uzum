package uz.java.uzum.service.mapper;

import org.mapstruct.Mapper;
import uz.java.uzum.dto.CartDto;
import uz.java.uzum.entity.Cart;

@Mapper(componentModel = "spring")
public abstract class CartMapper implements CommonMapper<CartDto, Cart>{
    @Override
    public abstract CartDto toDto(Cart cart);

    @Override
    public abstract Cart toEntity(CartDto cartDto);
}
