package uz.java.uzum.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import uz.java.uzum.dto.OrderDetailDto;
import uz.java.uzum.dto.OrderItemDto;
import uz.java.uzum.dto.ResponseDto;
import uz.java.uzum.entity.OrderDetail;
import uz.java.uzum.entity.OrderItem;
import uz.java.uzum.repository.OrderDetailRepository;
import uz.java.uzum.service.OrderDetailService;
import uz.java.uzum.service.appStatus.AppStatusCodes;
import uz.java.uzum.service.appStatus.AppStatusMessages;
import uz.java.uzum.service.mapper.OrderDetailMapper;
import uz.java.uzum.service.mapper.PaymentDetailMapper;
import uz.java.uzum.service.mapper.UsersMapper;

import java.util.Optional;

import static uz.java.uzum.service.appStatus.AppStatusCodes.NOT_FOUND_ERROR_CODE;
import static uz.java.uzum.service.appStatus.AppStatusCodes.VALIDATION_ERROR_CODE;
import static uz.java.uzum.service.appStatus.AppStatusMessages.*;

@Service
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;
    private final OrderDetailMapper orderDetailMapper;
    private final UsersMapper usersMapper;
    private final PaymentDetailMapper paymentDetailMapper;

    @Override
    public ResponseDto<OrderDetailDto> add(OrderDetailDto orderDetailsDto) {
        try {
            return ResponseDto.<OrderDetailDto>builder()
                    .data(orderDetailMapper.toDto(
                            orderDetailRepository.save(
                                    orderDetailMapper.toEntity(orderDetailsDto)
                            )
                    ))
                    .message(OK)
                    .success(true)
                    .build();
        }catch (Exception e){
            return ResponseDto.<OrderDetailDto>builder()
                    .code(AppStatusCodes.DATABASE_ERROR_CODE)
                    .message(AppStatusMessages.DATABASE_ERROR+ ": "+ e.getMessage())
                    .data(orderDetailsDto)
                    .build();
        }
    }

    @Override
    public ResponseDto<Page<OrderDetailDto>> getAll(Integer page, Integer size) {
        Long count = orderDetailRepository.count();

        PageRequest pageRequest = PageRequest.of(
                (count / size) <= page ?
                        (count % size == 0 ? (int) (count / size) - 1
                                : (int) (count / size))
                        : page,
                size);

        Page<OrderDetailDto> orderDetailDtos = orderDetailRepository.findAll(pageRequest)
                .map(orderDetailMapper::toDto);

        return ResponseDto.<Page<OrderDetailDto>>builder()
                .success(true)
                .message(OK)
                .data(orderDetailDtos)
                .build();
    }

    @Override
    public ResponseDto<OrderDetailDto> getById(Integer id) {
        if (id == null){
            return ResponseDto.<OrderDetailDto>builder()
                    .message(("Id is null"))
                    .code(VALIDATION_ERROR_CODE)
                    .build();
        }

        return orderDetailRepository.findById(id)
                .map(a -> ResponseDto.<OrderDetailDto>builder()
                        .success(true)
                        .message(OK)
                        .data(orderDetailMapper.toDto(a))
                        .build())
                .orElse(ResponseDto.<OrderDetailDto>builder()
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
        orderDetailRepository.deleteById(id);
        return ResponseDto.<Void>builder()
                .success(true)
                .message(OK)
                .build();
    }

    @Override
    public ResponseDto<OrderDetailDto> update(OrderDetailDto orderDetailsDto) {
        if (orderDetailsDto.getId() == null){
            return ResponseDto.<OrderDetailDto>builder()
                    .code(VALIDATION_ERROR_CODE)
                    .message(NULL_VALUE)
                    .build();
        }

        Optional<OrderDetail> orderDetailOptional = orderDetailRepository.findById(orderDetailsDto.getId());

        if (!orderDetailOptional.isPresent()){
            return ResponseDto.<OrderDetailDto>builder()
                    .code(NOT_FOUND_ERROR_CODE)
                    .message(NOT_FOUND)
                    .build();
        }

        OrderDetail orderDetail = orderDetailOptional.get();

        if(orderDetailsDto.getUser() != null){
            orderDetail.setUser(usersMapper.toEntity(orderDetailsDto.getUser()));
        }
        if(orderDetailsDto.getTotal() != null){
            orderDetail.setTotal(orderDetail.getTotal());
        }
        if(orderDetailsDto.getPayment() != null){
            orderDetail.setPaymentDetail(paymentDetailMapper.toEntity(orderDetailsDto.getPayment()));
        }
        if(orderDetailsDto.getCreatedAt() != null){
            orderDetail.setCreatedAt(orderDetail.getCreatedAt());
        }
        if(orderDetailsDto.getModifiedAt() != null){
            orderDetail.setModifiedAt(orderDetail.getModifiedAt());
        }


        orderDetailRepository.save(orderDetail);

        return ResponseDto.<OrderDetailDto>builder()
                .success(true)
                .message(OK)
                .data(orderDetailMapper.toDto(orderDetail))
                .build();
    }
}

