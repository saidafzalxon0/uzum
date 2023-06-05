package uz.java.uzum.dto;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.java.uzum.entity.Variant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VariantValueDto {
    private Integer valueId;
    private String value;
    private VariantDto variant;
}