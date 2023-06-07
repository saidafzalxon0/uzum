package uz.java.uzum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.java.uzum.entity.ImageQuality;

@Repository
public interface ImageQualityRepository extends JpaRepository<ImageQuality, Integer> {
}
