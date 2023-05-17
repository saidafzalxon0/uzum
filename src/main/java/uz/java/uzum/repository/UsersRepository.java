package uz.java.uzum.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import uz.java.uzum.entity.Users;

public interface UsersRepository extends JpaRepository<Users,Integer> {
}
