package uz.java.uzum.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.java.uzum.entity.Address;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryDto {
    private Integer id;
    private UserDto userDto;
    private Address address;
    private OrderDto orderDto;
    private Boolean isActive=true;
}
