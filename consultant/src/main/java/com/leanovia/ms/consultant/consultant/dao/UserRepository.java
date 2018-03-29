package com.leanovia.ms.consultant.consultant.dao;

import com.leanovia.ms.consultant.consultant.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    User findByPrincipalId(String principalId);
    List<User> findAllByName(String name);
    @Transactional
    @Modifying(clearAutomatically = true)
    @Query("UPDATE User user SET user.soldeConges = :soldeConges, user.jourDeRecup = :jourDeRecup,"
    		+ " user.mission = :mission, user.CertificationObtenues = :CertificationObtenues, "
    		+ "user.CertificationEnCours = :CertificationEnCours, user.livre = :livre, user.noteFrais = :noteFrais"
    		+ " WHERE user.principalId = :id")
    int updateUser(@Param("soldeConges") long soldeConges, @Param("jourDeRecup") long jourDeRecup,
                   @Param("mission") String mission,
                   @Param("CertificationObtenues") String CertificationObtenues,
                   @Param("CertificationEnCours") String CertificationEnCours,
                   @Param("livre") String livre,
                   @Param("noteFrais") String noteFrais);
}