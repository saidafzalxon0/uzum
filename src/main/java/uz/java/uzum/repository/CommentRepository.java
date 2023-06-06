package uz.java.uzum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.java.uzum.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
