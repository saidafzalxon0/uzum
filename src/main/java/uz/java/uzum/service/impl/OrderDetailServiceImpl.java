package uz.java.uzum.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import uz.java.uzum.dto.OrderDetailDto;
import uz.java.uzum.dto.ResponseDto;
import uz.java.uzum.service.OrderDetailService;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Override
    public ResponseDto<OrderDetailDto> add(OrderDetailDto orderDetailsDto) {
        return null;
    }

    @Override
    public ResponseDto<Page<OrderDetailDto>> getAll(Integer page, Integer size) {
        return null;
    }

    @Override
    public ResponseDto<OrderDetailDto> getById(Integer id) {
        return null;
    }

    @Override
    public ResponseDto<Void> delete(Integer id) {
        return null;
    }

    @Override
    public ResponseDto<OrderDetailDto> update(OrderDetailDto orderDetailsDto) {
        return null;
    }
}
