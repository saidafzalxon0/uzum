package uz.java.uzum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.java.uzum.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
}
