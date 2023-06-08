package uz.java.uzum.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDetailDto {
    private Integer id;
    private Integer orderId;
    private Integer amount;
    private String provider;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
