package uz.java.uzum.repository;

import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.java.uzum.entity.ProductVariant;
@Repository
public interface ProductVariantRepository extends JpaRepository<ProductVariant, Integer> {
}
