package uz.java.uzum.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(generator = "id",strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private OrderDetail order;
    @OneToOne
    private Product product;
    private Integer quantity;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}