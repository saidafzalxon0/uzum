package uz.java.uzum.entity;

import jakarta.persistence.*;
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
    @ManyToOne
    private Image imageId;
    private String name;
    private String imageUrl;
}
