package uz.java.uzum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.java.uzum.entity.Review;
@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
}
