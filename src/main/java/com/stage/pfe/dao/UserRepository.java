package com.stage.pfe.dao;

import com.stage.pfe.entities.NoteFrais;
import org.springframework.data.jpa.repository.JpaRepository;

import com.stage.pfe.entities.User;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UserRepository extends PagingAndSortingRepository<User, Integer> {
    User findByPrincipalId(String principalId);

}
