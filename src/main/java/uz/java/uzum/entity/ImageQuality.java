package uz.java.uzum.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImageQuality {
    @Id
    @GeneratedValue(generator = "imageQualityIdSeq")
    @SequenceGenerator(name = "imageQualityIdSeq", sequenceName = "image_quality_id_seq", allocationSize = 1)
    private Integer id;
    private Integer imageId;
    private String name;
    private String imageUrl;
}
