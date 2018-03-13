package com.stage.pfe.dao;

import com.stage.pfe.entities.NoteFrais;
import org.springframework.data.jpa.repository.JpaRepository;

import com.stage.pfe.entities.User;

import java.util.List;

public interface UserReository extends JpaRepository<User, String> {


}
