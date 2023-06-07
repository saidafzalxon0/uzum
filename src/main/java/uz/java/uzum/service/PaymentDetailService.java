package uz.java.uzum.service;

import org.springframework.data.domain.Page;
import uz.java.uzum.dto.PaymentDetailDto;
import uz.java.uzum.dto.ResponseDto;

public interface PaymentDetailService {
    ResponseDto<PaymentDetailDto> add(PaymentDetailDto paymentDetailsDto);
    ResponseDto<Page<PaymentDetailDto>> getAll(Integer page, Integer size);
    ResponseDto<PaymentDetailDto> getById(Integer id);
    ResponseDto<Void> delete(Integer id);
    ResponseDto<PaymentDetailDto> update(PaymentDetailDto paymentDetailsDto);

}
