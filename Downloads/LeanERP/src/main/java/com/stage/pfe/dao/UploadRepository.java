package com.stage.pfe.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.stage.pfe.entities.NoteFrais;

public interface UploadRepository extends JpaRepository<NoteFrais, Long>{
	

}
