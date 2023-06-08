package uz.java.uzum.service.mapper;

import org.mapstruct.Mapper;
import uz.java.uzum.dto.OrderDetailDto;
import uz.java.uzum.entity.OrderDetail;
@Mapper(componentModel = "spring")
public abstract class OrderDetailMapper implements CommonMapper<OrderDetailDto, OrderDetail> {
    @Override
    public abstract OrderDetailDto toDto(OrderDetail orderDetail);

    @Override
    public abstract OrderDetail toEntity(OrderDetailDto orderDetailDto);
}
