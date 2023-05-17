package uz.java.uzum.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "order")
public class Order {
    @Id
    @GeneratedValue(generator = "id",strategy = GenerationType.IDENTITY)
    private Long id;

//    @OneToMany
//    @Column(name = "user_id",nullable = false)
//    private User user;
//
//    @OneToMany
//    @Column(name = "product_id",nullable = false)
//    private Product product;

    @Column(name = "product_amount",nullable = false)
    private Integer product_amount;
    @Column(name = "status",nullable = false)
    private Boolean status = false;





}
