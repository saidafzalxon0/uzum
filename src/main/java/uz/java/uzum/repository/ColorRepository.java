package uz.java.uzum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.java.uzum.entity.Color;
@Repository
public interface ColorRepository extends JpaRepository<Color, Integer> {
}
