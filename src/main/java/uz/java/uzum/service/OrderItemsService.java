package uz.java.uzum.service;

import org.springframework.data.domain.Page;
import uz.java.uzum.dto.OrderItemDto;
import uz.java.uzum.dto.ResponseDto;

public interface OrderItemsService {
    ResponseDto<OrderItemDto> add(OrderItemDto orderItemsDto);
    ResponseDto<Page<OrderItemDto>> getAll(Integer page, Integer size);
    ResponseDto<OrderItemDto> getById(Integer id);
    ResponseDto<Void> delete(Integer id);
    ResponseDto<OrderItemDto> update(OrderItemDto orderItemsDto);
}
