package uz.java.uzum.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "username")
    private String username;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "isActive")
    private Short isActive;
    @Column(name = "enabled")
    private boolean enabled;
    @Column(name = "role",columnDefinition = "text default('USER')")
    private String role;
    @OneToMany
    @Column(name = "favorite")
    private List<Product> favorite;
    @CreatedDate
    @CreationTimestamp
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}