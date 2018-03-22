package com.stage.pfe.web;

import com.stage.pfe.dao.UserRepository;
import com.stage.pfe.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;



@RestController
public class UserController {
    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }
}

