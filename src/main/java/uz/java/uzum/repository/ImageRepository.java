package uz.java.uzum.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.java.uzum.entity.Image;
@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {
}
