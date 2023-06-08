package uz.java.uzum.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import uz.java.uzum.dto.PaymentDetailDto;
import uz.java.uzum.dto.ResponseDto;
import uz.java.uzum.entity.PaymentDetail;
import uz.java.uzum.repository.PaymentDetailRepository;
import uz.java.uzum.service.PaymentDetailService;
import uz.java.uzum.service.mapper.PaymentDetailMapper;

import java.util.Optional;

import static uz.java.uzum.service.appStatus.AppStatusCodes.*;
import static uz.java.uzum.service.appStatus.AppStatusMessages.*;
@Service
@RequiredArgsConstructor
public class PaymentDetailServiceImpl implements PaymentDetailService {
    private final PaymentDetailMapper paymentDetailMapper;
    private final PaymentDetailRepository paymentDetailRepository;
    @Override
    public ResponseDto<PaymentDetailDto> add(PaymentDetailDto paymentDetailsDto) {
        try {
            PaymentDetail paymentDetail = paymentDetailMapper.toEntity(paymentDetailsDto);
            paymentDetailRepository.save(paymentDetail);

            return ResponseDto.<PaymentDetailDto>builder()
                    .success(true)
                    .message(OK)
                    .data(paymentDetailMapper.toDto(paymentDetail))
                    .build();
        } catch (Exception e){
            return ResponseDto.<PaymentDetailDto>builder()
                    .code(DATABASE_ERROR_CODE)
                    .message(DATABASE_ERROR + " -> " + e.getMessage())
                    .build();
        }
    }

    @Override
    public ResponseDto<Page<PaymentDetailDto>> getAll(Integer page, Integer size) {
        Long count = paymentDetailRepository.count();

        PageRequest pageRequest = PageRequest.of(
                (count / size) <= page ?
                        (count % size == 0 ? (int) (count / size) - 1
                                : (int) (count / size))
                        : page,
                size);

        Page<PaymentDetailDto> paymentDetailsDtos = paymentDetailRepository.findAll(pageRequest)
                .map(paymentDetailMapper::toDto);

        return ResponseDto.<Page<PaymentDetailDto>>builder()
                .success(true)
                .message(OK)
                .data(paymentDetailsDtos)
                .build();
    }

    @Override
    public ResponseDto<PaymentDetailDto> getById(Integer id) {
        if(id == null){
            return ResponseDto.<PaymentDetailDto>builder()
                    .code(VALIDATION_ERROR_CODE)
                    .message(NULL_VALUE)
                    .build();
        }

        return paymentDetailRepository.findById(id)
                .map(p -> ResponseDto.<PaymentDetailDto>builder()
                        .success(true)
                        .message(OK)
                        .data(paymentDetailMapper.toDto(p))
                        .build())
                .orElse(ResponseDto.<PaymentDetailDto>builder()
                        .code(NOT_FOUND_ERROR_CODE)
                        .message(NOT_FOUND)
                        .build());
    }

    @Override
    public ResponseDto<Void> delete(Integer id) {
        if(id == null){
            return ResponseDto.<Void>builder()
                    .code(VALIDATION_ERROR_CODE)
                    .message(NULL_VALUE)
                    .build();
        }

        paymentDetailRepository.deleteById(id);

        return ResponseDto.<Void>builder()
                .success(true)
                .message(OK)
                .build();
    }

    @Override
    public ResponseDto<PaymentDetailDto> update(PaymentDetailDto paymentDetailsDto) {
        if(paymentDetailsDto.getId() == null){
            return ResponseDto.<PaymentDetailDto>builder()
                    .code(VALIDATION_ERROR_CODE)
                    .message(NULL_VALUE)
                    .build();
        }

        Optional<PaymentDetail> optional = paymentDetailRepository.findById(paymentDetailsDto.getId());

        if(optional.isEmpty()){
            return ResponseDto.<PaymentDetailDto>builder()
                    .code(NOT_FOUND_ERROR_CODE)
                    .message(NOT_FOUND)
                    .build();
        }

        PaymentDetail paymentDetails = optional.get();

        if (paymentDetailsDto.getOrderId() != null){
            paymentDetails.setOrderId(paymentDetailsDto.getOrderId());
        }
        if (paymentDetailsDto.getAmount() != null){
            paymentDetails.setAmount(paymentDetailsDto.getAmount());
        }
        if (paymentDetailsDto.getProvider() != null){
            paymentDetails.setProvider(paymentDetailsDto.getProvider());
        }
        if (paymentDetailsDto.getStatus() != null){
            paymentDetails.setStatus(paymentDetailsDto.getStatus());
        }
        if (paymentDetailsDto.getCreatedAt() != null){
            paymentDetails.setCreatedAt(paymentDetailsDto.getCreatedAt());
        }
        if (paymentDetailsDto.getModifiedAt() != null){
            paymentDetails.setModifiedAt(paymentDetailsDto.getModifiedAt());
        }

        paymentDetailRepository.save(paymentDetails);

        return ResponseDto.<PaymentDetailDto>builder()
                .success(true)
                .message(OK)
                .data(paymentDetailMapper.toDto(paymentDetails))
                .build();
    }
}
