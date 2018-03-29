package com.leanovia.ms.consultant.consultant.dao;

import com.leanovia.ms.consultant.consultant.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, String> {
    Role findByRole(String role);

}
