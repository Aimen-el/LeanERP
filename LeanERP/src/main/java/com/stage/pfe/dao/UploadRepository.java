package com.stage.pfe.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stage.pfe.entities.NoteFrais;

import java.util.List;

public interface UploadRepository extends JpaRepository<NoteFrais, Long>{
    List<NoteFrais> findAllByUsername(String Username);

}
