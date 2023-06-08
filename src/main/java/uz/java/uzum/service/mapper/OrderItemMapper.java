package uz.java.uzum.service.mapper;

import org.mapstruct.Mapper;
import uz.java.uzum.dto.OrderItemDto;
import uz.java.uzum.entity.OrderItem;

@Mapper(componentModel = "spring0")
public abstract class OrderItemMapper implements CommonMapper<OrderItemDto, OrderItem>{
    @Override
    public abstract OrderItemDto toDto(OrderItem orderItem);

    @Override
    public abstract OrderItem toEntity(OrderItemDto orderItemDto);
}
