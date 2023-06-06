package uz.java.uzum.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static uz.java.uzum.service.appStatus.AppStatusMessages.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Long id;
    @NotBlank(message = EMPTY_STRING)
    private String name;
    @NotBlank(message = EMPTY_STRING)
    private String description;
    private Integer price;
    private BrandDto brand;
    private Integer amount;
    private CategoryDto categoryDto;
    private List<ProductVariantDto> productVariants;
    private Boolean isAvailable;
}
