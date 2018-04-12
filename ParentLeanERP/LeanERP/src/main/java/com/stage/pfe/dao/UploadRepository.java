package com.stage.pfe.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.stage.pfe.entities.NoteFrais;

import java.util.Date;
import java.util.List;

public interface UploadRepository extends JpaRepository<NoteFrais, Long>{
    List<NoteFrais> findAllByUsername(String Username);
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE NoteFrais nf SET nf.etat = :etat, nf.motif = :motif  WHERE nf.id = :id")
    int updateNoteFrais(@Param("etat") Boolean etat, @Param("motif") String motif);
    

    }
