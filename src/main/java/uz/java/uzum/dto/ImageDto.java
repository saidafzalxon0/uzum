package uz.java.uzum.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImageDto {
    private Integer id;
    private List<ImageQualityDto> imageQualitys;
}
