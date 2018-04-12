package com.leanovia.ms.consultant.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;

import com.leanovia.ms.consultant.entities.Role;

import javax.transaction.Transactional;
import java.util.List;

public interface RoleRepository extends JpaRepository<Role, String>{
    @Transactional
    Role findByRole(String role);

}