package com.leanovia.ms.noteDeFrais.dao;

import org.springframework.data.jpa.repository.JpaRepository;


import com.leanovia.ms.noteDeFrais.entities.Role;

import javax.transaction.Transactional;

public interface RoleRepository extends JpaRepository<Role, String>{
    @Transactional
    Role findByRole(String role);

}