package com.stage.pfe.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stage.pfe.entities.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    User findByPrincipalId(String principalId);
    List<User> findAllByName(String name);
}