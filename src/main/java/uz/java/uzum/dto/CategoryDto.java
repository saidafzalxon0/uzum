package uz.java.uzum.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.java.uzum.service.appStatus.AppStatusMessages;

import static uz.java.uzum.service.appStatus.AppStatusMessages.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    private Integer id;
    @NotBlank(message = EMPTY_STRING)
    @Size(max = 100, min = 3, message = SIZE_MISMATCH)
    private String name;
    @Positive(message = NEGATIVE_VALUE)
    private Integer parentId;
}
