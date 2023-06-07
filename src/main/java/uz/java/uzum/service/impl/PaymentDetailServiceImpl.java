package uz.java.uzum.service.impl;

import org.springframework.data.domain.Page;
import uz.java.uzum.dto.PaymentDetailDto;
import uz.java.uzum.dto.ResponseDto;
import uz.java.uzum.service.PaymentDetailService;

public class PaymentDetailServiceImpl implements PaymentDetailService {
    @Override
    public ResponseDto<PaymentDetailDto> add(PaymentDetailDto paymentDetailsDto) {
        return null;
    }

    @Override
    public ResponseDto<Page<PaymentDetailDto>> getAll(Integer page, Integer size) {
        return null;
    }

    @Override
    public ResponseDto<PaymentDetailDto> getById(Integer id) {
        return null;
    }

    @Override
    public ResponseDto<Void> delete(Integer id) {
        return null;
    }

    @Override
    public ResponseDto<PaymentDetailDto> update(PaymentDetailDto paymentDetailsDto) {
        return null;
    }
}
