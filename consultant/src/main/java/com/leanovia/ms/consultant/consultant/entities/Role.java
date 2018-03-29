package com.leanovia.ms.consultant.consultant.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="user_roles")
public class Role implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int role_id;
	private String role;


	public Role(String role) {
		this.role = role;
	}

	public Role() {
	}

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}