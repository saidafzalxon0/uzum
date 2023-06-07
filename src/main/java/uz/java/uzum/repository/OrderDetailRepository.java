package uz.java.uzum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.java.uzum.entity.OrderDetail;
@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
}
