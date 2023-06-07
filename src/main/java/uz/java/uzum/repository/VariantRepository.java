package uz.java.uzum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.java.uzum.entity.Variant;

@Repository
public interface VariantRepository extends JpaRepository<Variant, Integer> {
}
