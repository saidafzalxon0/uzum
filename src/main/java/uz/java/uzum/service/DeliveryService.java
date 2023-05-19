package uz.java.uzum.service;

import uz.java.uzum.dto.DeliveryDto;
import uz.java.uzum.dto.ResponseDto;

import java.util.List;

public interface DeliveryService {
    ResponseDto<DeliveryDto> add(DeliveryDto deliveryDto);
    ResponseDto<List<DeliveryDto>> getAll();
    ResponseDto<DeliveryDto> getById(Integer id);
    ResponseDto<DeliveryDto> update(DeliveryDto deliveryDto);
    ResponseDto<DeliveryDto> delete(Integer id);
}
