package com.stage.pfe.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
	
	@RequestMapping(value="/")
    public String home(){
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
