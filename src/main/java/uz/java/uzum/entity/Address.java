package uz.java.uzum.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Address {
    @Id
    @GeneratedValue(generator = "addressIdSeq")
    @SequenceGenerator(name = "addressIdSeq",sequenceName = "address_id_seq",allocationSize = 1)
    private Integer id;
    private String info;
    private String code;
    private String phoneNumber;
    @ManyToMany

    private List<User> users;
}
