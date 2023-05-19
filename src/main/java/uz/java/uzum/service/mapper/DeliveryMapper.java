package uz.java.uzum.service.mapper;

import uz.java.uzum.dto.DeliveryDto;
import uz.java.uzum.entity.Delivery;

public abstract class DeliveryMapper implements CommonMapper<DeliveryDto, Delivery>{
    @Override
    public abstract DeliveryDto toDto(Delivery delivery);

    @Override
    public abstract Delivery toEntity(DeliveryDto deliveryDto);
}
