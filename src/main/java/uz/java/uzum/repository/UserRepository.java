package uz.java.uzum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import uz.java.uzum.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByIdAndIsActive(Integer id, short i);
}
