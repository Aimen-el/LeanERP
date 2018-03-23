package com.stage.pfe.web;

import com.stage.pfe.dao.RoleRepository;
import com.stage.pfe.dao.UserRepository;
import com.stage.pfe.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @GetMapping("/editUsers")

    public String listUsers(Model model, User currentUser, OAuth2Authentication authentication) throws IOException {
        //UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        LinkedHashMap<String, String> details = (LinkedHashMap<String, String>)authentication.getUserAuthentication().getDetails();
        currentUser=userRepository.findByPrincipalId(details.get(("sub")));
        boolean authorized = currentUser.getRoles().getRole().equals("ADMIN");

        if (authorized) {
            List<User> users=null;
            users=userRepository.findAll();
             model.addAttribute("users", users);

        }
        return "editUsers";
    }
}
