package com.stage.pfe.service;

import java.net.NoRouteToHostException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stage.pfe.dao.UploadRepository;
import com.stage.pfe.entities.NoteFrais;
import com.stage.pfe.entities.User;


@RestController
public class UploadFileService {
	@Autowired
	private UploadRepository uploadRepository;
	//@Secured(value={"CONSULTANT","ADMIN"})
	@RequestMapping(value="/saveFile", method=RequestMethod.POST)
	public  NoteFrais saveFile(@RequestBody NoteFrais nf) {
		return uploadRepository.save(nf);
	}
	//@Secured(value={"ADMIN"})
	@RequestMapping(value="/File")
	public Page<NoteFrais> listFile(int page, int size){
		return uploadRepository.findAll(new PageRequest(page, size));
	}
	
	@RequestMapping(value="/findFile")
	public List<NoteFrais> findAll(){
		return uploadRepository.findAll();
		}
	@RequestMapping(value="/getUserLoger")
	public Map<String, Object> getLoggedUser(HttpSession session){
		SecurityContext securityContext= (SecurityContext) 
				session.getAttribute("SPRING_SECURITE_CONTEXT");
		String username= securityContext.getAuthentication().getName();
		List<String> roles= new ArrayList<>();
		for(GrantedAuthority ga: securityContext.getAuthentication().getAuthorities()) {
			roles.add(ga.getAuthority());
		}
		Map<String, Object> params= new HashMap<>();
		params.put("username", username);
		params.put("roles", roles);
		return params;
	}
} 
