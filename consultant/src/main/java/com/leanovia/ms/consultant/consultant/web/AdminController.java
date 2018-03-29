package com.leanovia.ms.consultant.consultant.web;

import com.leanovia.ms.consultant.consultant.dao.RoleRepository;
import com.leanovia.ms.consultant.consultant.dao.UserRepository;
import com.leanovia.ms.consultant.consultant.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
        List<User> users=null;
        //UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        LinkedHashMap<String, String> details = (LinkedHashMap<String, String>)authentication.getUserAuthentication().getDetails();
        currentUser=userRepository.findByPrincipalId(details.get(("sub")));
        boolean authorized = currentUser.getRoles().getRole().equals("ADMIN");

        if (authorized) {
            users=userRepository.findAll();
             model.addAttribute("users", users);
        }
        else {
        	users=userRepository.findAllByName(details.get("name"));
            model.addAttribute("users", users);
        }
        return "editUsers";
    }
    
    /********************** Consulter ******************/
    
     @RequestMapping(value = "/consulter/{id}", method = RequestMethod.GET)
    public String consulter(@PathVariable String id, Model model) {
        model.addAttribute("user", this.userRepository.findByPrincipalId(id));
        return "consulter";
    }
     
     /********************** Delete ******************/

     @RequestMapping("/supprimer/{id}")
     public String supprimer(@PathVariable String id) {
    	 userRepository.delete(userRepository.findByPrincipalId(id));
         return "redirect:/editUsers";
     }
     
     /********************** update ******************/

     @RequestMapping(value = "/modifier/{id}", method = RequestMethod.GET)
     public String modifier(@PathVariable String id, ModelMap model) {
         model.addAttribute("user", this.userRepository.findByPrincipalId(id));
         return "modifier";

     }

     @RequestMapping(value = "/modificaion", method = RequestMethod.POST)
     public String modifictaion(@Valid @ModelAttribute("user") User user, BindingResult result) {
         this.userRepository.save(user);
         Authentication authentication = new PreAuthenticatedAuthenticationToken(user,user);
         SecurityContextHolder.getContext().setAuthentication(authentication);
         return "redirect:editUsers";
     }

}
   

