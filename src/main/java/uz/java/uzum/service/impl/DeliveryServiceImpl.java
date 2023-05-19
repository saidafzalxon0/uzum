package uz.java.uzum.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.java.uzum.dto.DeliveryDto;
import uz.java.uzum.dto.ResponseDto;
import uz.java.uzum.entity.Delivery;
import uz.java.uzum.repository.DeliveryRepository;
import uz.java.uzum.service.DeliveryService;
import uz.java.uzum.service.mapper.DeliveryMapper;

import java.util.List;
import java.util.Optional;

import static uz.java.uzum.service.appStatus.AppStatusCodes.*;
import static uz.java.uzum.service.appStatus.AppStatusMessages.*;

@Service
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {
    private final DeliveryRepository deliveryRepository;
    private final DeliveryMapper deliveryMapper;
    @Override
    public ResponseDto<DeliveryDto> add(DeliveryDto deliveryDto) {
        return ResponseDto.<DeliveryDto>builder()
                .success(true)
                .code(OK_CODE)
                .message(OK)
                .data(deliveryMapper.toDto(deliveryRepository.save(deliveryMapper.toEntity(deliveryDto))))
                .build();
    }

    @Override
    public ResponseDto<List<DeliveryDto>> getAll() {
        List<Delivery> deliveries = deliveryRepository.findAll();

        return ResponseDto.<List<DeliveryDto>>builder()
                .code(OK_CODE)
                .success(true)
                .message(OK)
                .data(deliveries.stream().map(deliveryMapper::toDto).toList())
                .build();
    }

    @Override
    public ResponseDto<DeliveryDto> getById(Integer id) {
        Optional<Delivery> byId = deliveryRepository.findById(id);

        if (byId.isEmpty()){
            return ResponseDto.<DeliveryDto>builder()
                    .code(NOT_FOUND_ERROR_CODE)
                    .message(NOT_FOUND)
                    .build();
        }
        Delivery delivery = byId.get();
        return ResponseDto.<DeliveryDto>builder()
                .message(OK)
                .code(OK_CODE)
                .success(true)
                .data(deliveryMapper.toDto(delivery))
                .build();
    }

    @Override
    public ResponseDto<DeliveryDto> update(DeliveryDto deliveryDto) {
        if (deliveryDto.getId() == null){
            return ResponseDto.<DeliveryDto>builder()
                    .code(VALIDATION_ERROR_CODE)
                    .message(NULL_VALUE)
                    .build();
        }
        Optional<Delivery> byId = deliveryRepository.findById(deliveryDto.getId());

        if (byId.isEmpty()){
            return ResponseDto.<DeliveryDto>builder()
                    .code(NOT_FOUND_ERROR_CODE)
                    .message(NOT_FOUND)
                    .build();
        }

        return ResponseDto.<DeliveryDto>builder()
                .message(OK)
                .code(OK_CODE)
                .success(true)
                .data(deliveryMapper.toDto(deliveryRepository.save(deliveryMapper.toEntity(deliveryDto))))
                .build();
    }

    @Override
    public ResponseDto<DeliveryDto> delete(Integer id) {
        Optional<Delivery> byId = deliveryRepository.findById(id);

        if (byId.isEmpty()){
            return ResponseDto.<DeliveryDto>builder()
                    .code(NOT_FOUND_ERROR_CODE)
                    .message(NOT_FOUND)
                    .build();
        }

        deliveryRepository.deleteById(id);

        return ResponseDto.<DeliveryDto>builder()
                .message(OK)
                .code(OK_CODE)
                .success(true)
                .data(deliveryMapper.toDto(byId.get()))
                .build();
    }
}
