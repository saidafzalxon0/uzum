package uz.java.uzum.service.mapper;

import org.mapstruct.Mapper;
import uz.java.uzum.dto.PaymentDetailDto;
import uz.java.uzum.entity.PaymentDetail;
@Mapper(componentModel = "spring")
public abstract class PaymentDetailMapper implements CommonMapper<PaymentDetailDto, PaymentDetail>{
    @Override
    public abstract PaymentDetailDto toDto(PaymentDetail paymentDetail);

    @Override
    public abstract PaymentDetail toEntity(PaymentDetailDto paymentDetailDto);
}
