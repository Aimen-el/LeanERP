package com.stage.pfe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stage.pfe.dao.RoleRepository;
import com.stage.pfe.dao.UserRepository;
import com.stage.pfe.entities.Role;
import com.stage.pfe.entities.User;
@RestController
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value="/addUser")
	public User save(User u) {
		return userRepository.save(u);
	}
	//@Secured(value={"ROLE_ADMIN"})
//	@RequestMapping(value="/findUsers")
//	public List<User> findAll(){
//		return userRepository.findAll();
//		}
	
	@Autowired
	private RoleRepository roleRepository;	
	@RequestMapping(value="/addRole")
	public Role saveRole(Role r) {
		return roleRepository.save(r);
	}
	
	@RequestMapping(value="/findRole")
	public List<Role> findRole(){
		return roleRepository.findAll();
		}
	@RequestMapping(value="/addRoleToUser")
	public User addRoleToUser(String principalId, String role) {
		User u= userRepository.findOne(principalId);
		userRepository.save(u);
		return u;
		
		
	}
}
	
