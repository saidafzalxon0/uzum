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
    @GeneratedValue(generator = "reviewIdSeq")
    @SequenceGenerator(name = "reviewIdSeq", sequenceName = "review_id_seq", allocationSize = 1)
    private Integer id;
    private String body;
    private Integer rank;
    @ManyToOne
    private User userId;
    @OneToOne
    private Review parentId;
    private Date createdAt;
}
