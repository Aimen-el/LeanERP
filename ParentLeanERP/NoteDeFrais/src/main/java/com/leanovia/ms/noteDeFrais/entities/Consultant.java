package com.leanovia.ms.noteDeFrais.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="consultants")
public class Consultant implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int consultant_id;
	private long telephone;
	private long soldeConges;
	private long jourDeRecup;
	private String mission;
	private String CertificationObtenues;
	private String CertificationEnCours;
	private int livre;
	private int noteFrais;
	public int getConsultant_id() {
		return consultant_id;
	}
	public void setConsultant_id(int consultant_id) {
		this.consultant_id = consultant_id;
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
	public Consultant(long telephone, long soldeConges, long jourDeRecup, String mission, String certificationObtenues,
			String certificationEnCours, int livre, int noteFrais) {
		super();
		this.telephone = telephone;
		this.soldeConges = soldeConges;
		this.jourDeRecup = jourDeRecup;
		this.mission = mission;
		CertificationObtenues = certificationObtenues;
		CertificationEnCours = certificationEnCours;
		this.livre = livre;
		this.noteFrais = noteFrais;
	}
	public Consultant() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
