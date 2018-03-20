package com.stage.pfe.web;

import com.stage.pfe.dao.UserReository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/")
public class UserController {

    @RequestMapping(
            value = "user",
            method = RequestMethod.GET)
    public Principal user(Principal user) {
        return user;
    }
}