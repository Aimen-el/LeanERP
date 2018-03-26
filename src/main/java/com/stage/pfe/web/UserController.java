package com.stage.pfe.web;

import com.stage.pfe.dao.RoleRepository;
import com.stage.pfe.dao.UserRepository;
import com.stage.pfe.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.List;


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



    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @GetMapping("/google/login")

    public String UsersList(Model model, User currentUser, OAuth2Authentication authentication) throws IOException {
        List<User> users=null;
        //UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        LinkedHashMap<String, String> details = (LinkedHashMap<String, String>)authentication.getUserAuthentication().getDetails();
        currentUser=userRepository.findByPrincipalId(details.get(("sub")));
                 model.addAttribute("users", currentUser);
        return "/google/login";
    }
}

