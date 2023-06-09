package uz.java.uzum.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import uz.java.uzum.dto.ColorDto;
import uz.java.uzum.dto.OrderItemDto;
import uz.java.uzum.dto.ResponseDto;
import uz.java.uzum.dto.ReviewDto;
import uz.java.uzum.entity.Color;
import uz.java.uzum.entity.OrderDetail;
import uz.java.uzum.entity.OrderItem;
import uz.java.uzum.repository.OrderItemsRepository;
import uz.java.uzum.repository.OrderRepository;
import uz.java.uzum.service.OrderItemsService;
import uz.java.uzum.service.appStatus.AppStatusCodes;
import uz.java.uzum.service.appStatus.AppStatusMessages;
import uz.java.uzum.service.mapper.OrderDetailMapper;
import uz.java.uzum.service.mapper.OrderItemMapper;
import uz.java.uzum.service.mapper.ProductMapper;

import java.util.Optional;

import static uz.java.uzum.service.appStatus.AppStatusCodes.NOT_FOUND_ERROR_CODE;
import static uz.java.uzum.service.appStatus.AppStatusCodes.VALIDATION_ERROR_CODE;
import static uz.java.uzum.service.appStatus.AppStatusMessages.*;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemsService {

    private final OrderItemMapper orderItemMapper;
    private final OrderItemsRepository orderItemsRepository;
    private final ProductMapper productMapper;
    private final OrderDetailMapper orderDetailMapper;

    @Override
    public ResponseDto<OrderItemDto> add(OrderItemDto orderItemsDto) {
        try{
            return ResponseDto.<OrderItemDto>builder()
                    .data(orderItemMapper.toDto(
                            orderItemsRepository.save(
                                    orderItemMapper.toEntity(orderItemsDto)
                            )
                    ))
                    .message(OK)
                    .success(true)
                    .build();
        }catch (Exception e){
            return ResponseDto.<OrderItemDto>builder()
                    .code(AppStatusCodes.DATABASE_ERROR_CODE)
                    .message(AppStatusMessages.DATABASE_ERROR + ": "+ e.getMessage())
                    .data(orderItemsDto)
                    .build();
        }
    }

    @Override
    public ResponseDto<Page<OrderItemDto>> getAll(Integer page, Integer size) {
        Long count = orderItemsRepository.count();

        PageRequest pageRequest = PageRequest.of(
                (count / size) <= page ?
                        (count % size == 0 ? (int) (count / size) - 1
                                : (int) (count / size))
                        : page,
                size);

        Page<OrderItemDto> orderItemsDtos = orderItemsRepository.findAll(pageRequest)
                .map(orderItemMapper::toDto);

        return ResponseDto.<Page<OrderItemDto>>builder()
                .success(true)
                .message(OK)
                .data(orderItemsDtos)
                .build();

    }

    @Override
    public ResponseDto<OrderItemDto> getById(Integer id) {
        if (id == null){
            return ResponseDto.<OrderItemDto>builder()
                    .message(("Id is null"))
                    .code(VALIDATION_ERROR_CODE)
                    .build();
        }

        return orderItemsRepository.findById(id)
                .map(a -> ResponseDto.<OrderItemDto>builder()
                        .success(true)
                        .message(OK)
                        .data(orderItemMapper.toDto(a))
                        .build())
                .orElse(ResponseDto.<OrderItemDto>builder()
                        .code(NOT_FOUND_ERROR_CODE)
                        .message(NOT_FOUND)
                        .build());
    }

    @Override
    public ResponseDto<Void> delete(Integer id) {
        if (id == null){
            return ResponseDto.<Void>builder()
                    .code(VALIDATION_ERROR_CODE)
                    .message("id is null")
                    .build();
        }
        orderItemsRepository.deleteById(id);
        return ResponseDto.<Void>builder()
                .success(true)
                .message(OK)
                .build();
    }

    @Override
    public ResponseDto<OrderItemDto> update(OrderItemDto orderItemsDto) {

        if (orderItemsDto.getId() == null){
            return ResponseDto.<OrderItemDto>builder()
                    .code(VALIDATION_ERROR_CODE)
                    .message(NULL_VALUE)
                    .build();
        }

        Optional<OrderItem> orderItemOptional = orderItemsRepository.findById(orderItemsDto.getId());

        if (!orderItemOptional.isPresent()){
            return ResponseDto.<OrderItemDto>builder()
                    .code(NOT_FOUND_ERROR_CODE)
                    .message(NOT_FOUND)
                    .build();
        }

        OrderItem orderItem = orderItemOptional.get();

        if (orderItemsDto.getProduct() != null){
            orderItem.setProduct(productMapper.toEntity(orderItemsDto.getProduct()));
        }
        if (orderItemsDto.getOrder() != null){
            orderItem.setOrder(orderDetailMapper.toEntity(orderItemsDto.getOrder()));
        }
        if (orderItemsDto.getQuantity() != null){
            orderItem.setQuantity(orderItemsDto.getQuantity());
        }
        if (orderItemsDto.getCreatedAt() != null){
            orderItem.setCreatedAt(orderItemsDto.getCreatedAt());
        }
        if (orderItemsDto.getModifiedAt() != null){
            orderItem.setModifiedAt(orderItemsDto.getModifiedAt());
        }

        orderItemsRepository.save(orderItem);

        return ResponseDto.<OrderItemDto>builder()
                .success(true)
                .message(OK)
                .data(orderItemMapper.toDto(orderItem))
                .build();
    }
}
