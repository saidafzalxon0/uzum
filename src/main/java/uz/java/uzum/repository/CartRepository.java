package uz.java.uzum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.java.uzum.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
}
