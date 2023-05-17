package uz.java.uzum.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.java.uzum.entity.User;

public interface UserRepository extends JpaRepository<User,Integer> {
}
