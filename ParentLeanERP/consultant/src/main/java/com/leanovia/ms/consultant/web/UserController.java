package com.leanovia.ms.consultant.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;



@RestController
public class UserController {
    @RequestMapping("/user")
    public Principal user(Principal user) {

        return user;
    }
    @RequestMapping("/unauthenticated")
    public String unauthenticated() {
        return "redirect:/?error=true";
    }

}