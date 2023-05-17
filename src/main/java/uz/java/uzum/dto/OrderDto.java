package uz.java.uzum.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDto {
    private Long id;


//    private User user;
//

//    private Product product;


    private Integer product_amount;

    private Boolean status = false;
}
