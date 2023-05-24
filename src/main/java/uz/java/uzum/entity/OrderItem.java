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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @ManyToOne
    @Column(name = "orderDetail_id")
    private OrderDetail order;
    @OneToOne
    @Column(name = "product_id")
    private Product product;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "create_at")
    private LocalDateTime createdAt;
    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;
}