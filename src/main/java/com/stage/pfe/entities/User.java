package com.stage.pfe.entities;

import org.springframework.data.repository.Repository;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import javax.persistence.*;

@Entity
@Table(name="users")
public class User implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int user_id;
	private String name;
	private String email;
	private String principalId;
	private String photo;
	private String password;
	private long telephone;
	private long soldeConges;
	private long jourDeRecup;
	private String mission;
	private String CertificationObtenues;
	private String CertificationEnCours;
	private int livre;
	private int noteFrais;

	@ManyToOne
	@JoinColumn(name = "role_id")
	private Role roles;
	
	public User(String name, String email, String principalId, String photo, String password, long telephone,
			long soldeConges, long jourDeRecup, String mission, String certificationObtenues,
			String certificationEnCours, int livre, int noteFrais, Role roles) {
		super();
		this.name = name;
		this.email = email;
		this.principalId = principalId;
		this.photo = photo;
		this.password = password;
		this.telephone = telephone;
		this.soldeConges = soldeConges;
		this.jourDeRecup = jourDeRecup;
		this.mission = mission;
		CertificationObtenues = certificationObtenues;
		CertificationEnCours = certificationEnCours;
		this.livre = livre;
		this.noteFrais = noteFrais;
		this.roles = roles;
	}

	public long getTelephone() {
		return telephone;
	}

	public void setTelephone(long telephone) {
		this.telephone = telephone;
	}

	public long getSoldeConges() {
		return soldeConges;
	}

	public void setSoldeConges(long soldeConges) {
		this.soldeConges = soldeConges;
	}

	public long getJourDeRecup() {
		return jourDeRecup;
	}

	public void setJourDeRecup(long jourDeRecup) {
		this.jourDeRecup = jourDeRecup;
	}

	public String getMission() {
		return mission;
	}

	public void setMission(String mission) {
		this.mission = mission;
	}

	public String getCertificationObtenues() {
		return CertificationObtenues;
	}

	public void setCertificationObtenues(String certificationObtenues) {
		CertificationObtenues = certificationObtenues;
	}

	public String getCertificationEnCours() {
		return CertificationEnCours;
	}

	public void setCertificationEnCours(String certificationEnCours) {
		CertificationEnCours = certificationEnCours;
	}

	public int getLivre() {
		return livre;
	}

	public void setLivre(int livre) {
		this.livre = livre;
	}

	public int getNoteFrais() {
		return noteFrais;
	}

	public void setNoteFrais(int noteFrais) {
		this.noteFrais = noteFrais;
	}

	public Role getRoles() {
		return roles;
	}

	public void setRoles(Role roles) {
		this.roles = roles;
	}

	public User(String name, String email, String principalId, String photo, String password, Role roles) {
		this.name = name;
		this.email = email;
		this.principalId = principalId;
		this.photo = photo;
		this.password = password;
		this.roles = roles;
	}

	public String getPhoto() {

		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}


	public String getPrincipalId() {

		return principalId;
	}

	public void setPrincipalId(String principalId) {
		this.principalId = principalId;
	}


	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}






	public User() {
		super();
	}

}