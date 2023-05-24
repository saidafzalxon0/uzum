package uz.java.uzum.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product_variant")
public class ProductVariant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @Column(name = "price")
    private Double price;
    @Column(name = "sku")
    private String sku;
    @ManyToMany
    @JoinTable(
            name = "product_detail",
            joinColumns = @JoinColumn(name = "productVariant_id"),
            inverseJoinColumns = @JoinColumn(name = "variantValue_id")
    )
    private List<VariantValue> variantValues;

}
