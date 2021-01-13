package com.anton.sring.spring_boot.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.anton.sring.spring_boot.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
