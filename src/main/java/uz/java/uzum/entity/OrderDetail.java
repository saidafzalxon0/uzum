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
public class OrderDetail {
    @Id
    @GeneratedValue(generator = "id",strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private User user;
    private Double total;
    @OneToOne
    private PaymentDetail paymentDetail;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
}
