package uz.java.uzum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import uz.java.uzum.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
}
