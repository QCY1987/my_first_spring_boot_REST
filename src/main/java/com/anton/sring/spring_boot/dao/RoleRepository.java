package com.anton.sring.spring_boot.dao;



import com.anton.sring.spring_boot.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository <Role, Long> {
    Role findByRole(String role);

}
