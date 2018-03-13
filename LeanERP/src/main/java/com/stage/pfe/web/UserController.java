package com.stage.pfe.web;

import com.stage.pfe.dao.UserReository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
	
	@RequestMapping(value="/")
    public String home(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("user",auth);
        return "home";
    }
	
	@RequestMapping(value="/login")
    public String login(){
        return "login";
    }

	/*@RequestMapping(value="/uploadForm")
    public String uploadForm(){
        return "uploadForm";
    }
*/
}
