package uz.java.uzum.entity;

import jakarta.annotation.security.DenyAll;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Delivery {
    @Id
    @GeneratedValue(generator = "deliveryIdSeq")
    @SequenceGenerator(name = "deliveryIdSeq", sequenceName = "delivery_id_seq", allocationSize = 1)
    private Integer id;
    @ManyToOne
    private User user;
    @ManyToOne
    private Address address;
    @OneToOne
    private Order order;
    private Boolean isActive;
}
