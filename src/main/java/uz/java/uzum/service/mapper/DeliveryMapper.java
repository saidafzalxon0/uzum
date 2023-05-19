package uz.java.uzum.service.mapper;

import org.mapstruct.Mapper;
import uz.java.uzum.dto.DeliveryDto;
import uz.java.uzum.entity.Delivery;

@Mapper(componentModel = "spring")
public abstract class DeliveryMapper implements CommonMapper<DeliveryDto, Delivery>{
    @Override
    public abstract DeliveryDto toDto(Delivery delivery);

    @Override
    public abstract Delivery toEntity(DeliveryDto deliveryDto);
}
