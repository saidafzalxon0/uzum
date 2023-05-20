package uz.java.uzum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.java.uzum.entity.OrderItem;

@Repository
public interface OrderRepository extends JpaRepository<OrderItem,Long> {
}
