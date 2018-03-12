package com.stage.pfe.entities;

import java.io.Serializable;
import java.nio.file.Path;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="upload")
public class NoteFrais implements Serializable {
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	private String username;
	private String name;
	private Date dateupload;
	private String chemin;
	private Boolean etat=true;
	private String motif;
	@ManyToMany
	@JoinTable(name="USER_UPLOAD")
	private Collection<User> users;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getusername() {
		return username;
	}
	public void setusername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDateupload() {
		return dateupload;
	}
	public void setDateupload(Date dateupload) {
		this.dateupload = dateupload;
	}
	public String getChemin() {
		return chemin;
	}
	public void setChemin(String chemin) {
		this.chemin = chemin;
	}
	public Boolean getEtat() {
		return etat;
	}
	public void setEtat(Boolean etat) {
		this.etat = etat;
	}
	public String getMotif() {
		return motif;
	}
	public void setMotif(String motif) {
		this.motif = motif;
	}
	public NoteFrais() {
		super();
		// TODO Auto-generated constructor stub
	}
	public NoteFrais(String username, String name, Date dateupload, String chemin, Boolean etat) {
		super();
		this.username = username;
		this.name = name;
		this.dateupload = dateupload;
		this.chemin = chemin;
		this.etat = etat;
	}
	public NoteFrais(String username, String name, Date dateupload, Boolean etat, String motif) {
		super();
		this.username = username;
		this.name = name;
		this.dateupload = dateupload;
		this.etat = etat;
		this.motif = motif;
	}
	public NoteFrais(String username, String name, Date dateupload,String chemin, Boolean etat, String motif) {
		super();
		this.username = username;
		this.name = name;
		this.dateupload = dateupload;
		this.chemin=chemin;
		this.etat = etat;
		this.motif = motif;
	}
	public NoteFrais(long id, String username, String name, Date dateupload, String chemin, Boolean etat, String motif,
			Collection<User> users) {
		super();
		this.id = id;
		this.username = username;
		this.name = name;
		this.dateupload = dateupload;
		this.chemin = chemin;
		this.etat = etat;
		this.motif = motif;
		this.users = users;
	}


}
