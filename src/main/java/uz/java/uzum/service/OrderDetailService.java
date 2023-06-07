package uz.java.uzum.service;

import org.springframework.data.domain.Page;
import uz.java.uzum.dto.OrderDetailDto;
import uz.java.uzum.dto.ResponseDto;

public interface OrderDetailService {
    ResponseDto<OrderDetailDto> add(OrderDetailDto orderDetailsDto);
    ResponseDto<Page<OrderDetailDto>> getAll(Integer page, Integer size);
    ResponseDto<OrderDetailDto> getById(Integer id);
    ResponseDto<Void> delete(Integer id);
    ResponseDto<OrderDetailDto> update(OrderDetailDto orderDetailsDto);
}
