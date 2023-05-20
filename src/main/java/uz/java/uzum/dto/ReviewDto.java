package uz.java.uzum.dto;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Negative;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.CreatedDate;
import uz.java.uzum.entity.Review;
import uz.java.uzum.entity.User;

import java.util.Date;


import static uz.java.uzum.service.appStatus.AppStatusMessages.NEGATIVE_VALUE;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {
    private Integer id;
    private String body;
    @Range(min = 1,max = 5)
    private Integer rank;
    @Positive(message = NEGATIVE_VALUE)
    private Integer userId;
    private Integer parentId;
    private Date createdAt;
}
