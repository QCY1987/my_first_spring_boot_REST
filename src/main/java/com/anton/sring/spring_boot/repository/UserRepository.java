package com.anton.sring.spring_boot.repository;

import com.anton.sring.spring_boot.models.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {

}
