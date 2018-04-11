package com.stage.pfe.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stage.pfe.entities.Role;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface RoleRepository extends JpaRepository<Role, String>{
    Role findByRole(String role);

}
