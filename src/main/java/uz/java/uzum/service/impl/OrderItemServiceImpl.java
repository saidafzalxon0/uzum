package uz.java.uzum.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import uz.java.uzum.dto.OrderItemDto;
import uz.java.uzum.dto.ResponseDto;
import uz.java.uzum.service.OrderItemsService;

@Service
public class OrderItemServiceImpl implements OrderItemsService {
    @Override
    public ResponseDto<OrderItemDto> add(OrderItemDto orderItemsDto) {
        return null;
    }

    @Override
    public ResponseDto<Page<OrderItemDto>> getAll(Integer page, Integer size) {
        return null;
    }

    @Override
    public ResponseDto<OrderItemDto> getById(Integer id) {
        return null;
    }

    @Override
    public ResponseDto<Void> delete(Integer id) {
        return null;
    }

    @Override
    public ResponseDto<OrderItemDto> update(OrderItemDto orderItemsDto) {
        return null;
    }
}
