package uz.java.uzum.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Cart {
    @Id
    @GeneratedValue(generator = "id",strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer totalPrice;
    @ManyToMany

    private List<Product> products;
    @OneToOne
    private User user;
    @CreationTimestamp
    @CreatedDate
    private LocalDateTime createdAt;



}
