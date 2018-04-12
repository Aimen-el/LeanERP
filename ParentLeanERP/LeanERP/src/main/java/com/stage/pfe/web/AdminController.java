package com.stage.pfe.web;

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

import com.stage.pfe.dao.UserRepository;
import com.stage.pfe.entities.User;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

import javax.validation.Valid;

@Controller
public class AdminController {
    @Autowired
    private UserRepository userRepository;
      
      
    /********************** Edit Users ******************/
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
 	
    /********************** Consulter *****************/
    
   @RequestMapping(value = "/consulter/{id}", method = RequestMethod.GET)
   public String consulter(@PathVariable String id, Model model) {
      model.addAttribute("user", this.userRepository.findByPrincipalId(id));
      return "consulter";
  }
   
   /********************** Delete *****************/

   @RequestMapping("/supprimer/{id}")
   public String supprimer(@PathVariable String id) {
  	 userRepository.delete(userRepository.findByPrincipalId(id));
       return "redirect:/editUsers";
   }

	
	/********************** update *****************

    @RequestMapping(value = "/modifier/{id}", method = RequestMethod.GET)
    public String modifier(@PathVariable Integer id, ModelMap model) {
        model.addAttribute("consultant", this.consultantRepository.findOne(id));
        return "modifier";

    }

    @RequestMapping(value = "/modificaion", method = RequestMethod.POST)
    public String modifictaion(@Valid @ModelAttribute("consultant") Consultant consultant, BindingResult result) {
        this.consultantRepository.save(consultant);
        User user = new User();
        Authentication authentication = new PreAuthenticatedAuthenticationToken(user,user);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return "redirect:editUsers";
    }
*/
    
     
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
   


