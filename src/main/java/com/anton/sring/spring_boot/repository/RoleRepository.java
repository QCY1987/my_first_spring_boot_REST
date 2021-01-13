package com.anton.sring.spring_boot.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.anton.sring.spring_boot.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRole(String role);
}
