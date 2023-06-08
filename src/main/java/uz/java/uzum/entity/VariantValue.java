package uz.java.uzum.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "variant_value")
@Getter
@Setter
public class VariantValue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "variant_id")
    private Variant variant;
    @Column(name = "value")
    private String value;
}
