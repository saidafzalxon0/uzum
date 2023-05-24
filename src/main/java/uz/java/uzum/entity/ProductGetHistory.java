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
public class ProductGetHistory {

    @Id
    @GeneratedValue(generator = "productGetID")
    @SequenceGenerator(name = "productGetID", sequenceName = "product_Get_Id", allocationSize = 1)
    private Integer id;
    private Integer userId;
    private String productName;

}
