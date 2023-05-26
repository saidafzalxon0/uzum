package uz.java.uzum.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "body")
    private String body;
    @Column(name = "rank")
    private Integer rank;
    @ManyToOne
    @Column(name = "user_id")
    private User userId;
    @OneToOne
    @Column(name = "parent_id")
    private Review parentId;
    @Column(name = "created_at")
    private Date createdAt;
}
