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
	@ManyToOne
	@JoinColumn(name = "role_id")
	private Role roles;

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

