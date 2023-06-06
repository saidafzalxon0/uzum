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
public class OrderItemDto {
    private Integer id;
    private OrderDetailDto order;
    private ProductDto product;
    private Integer quantity;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
