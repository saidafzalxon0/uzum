package uz.java.uzum.entity;

import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "user")
public class User {
    @Id
    Long id;
}
