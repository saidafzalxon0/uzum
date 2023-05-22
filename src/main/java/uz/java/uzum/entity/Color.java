package uz.java.uzum.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Color {
    @Id
    @GeneratedValue(generator = "colorIdSeq")
    @SequenceGenerator(name = "colorIdSeq", sequenceName = "color_id_seq", allocationSize = 1)
    private Integer id;
    private String name;
}
