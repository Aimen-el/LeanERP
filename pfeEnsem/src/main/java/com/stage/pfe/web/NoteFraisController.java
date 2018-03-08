package com.stage.pfe.web;

import java.nio.file.Path;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stage.pfe.entities.NoteFrais;
import com.stage.pfe.metier.INoteFrais;

@Controller
public class NoteFraisController {
	@Autowired
	private INoteFrais noteFaisMetier;
	
	/*@RequestMapping(value="/noteFrais")
    public String noteFrais(){
        return "noteFrais";
    }
	
	@RequestMapping(value="/consulterFic")
    public String consulterFic(Model model, Long id){
		NoteFrais nf = noteFaisMetier.consulterNoteFrais(id);
		model.addAttribute("noteFrais", nf);
        return "noteFrais";
    }
	*/
	@RequestMapping(value="/noteFrais")
    public String noteFrais(Model model, String userename, String name, @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateupload, String chemin, Boolean etat, String motif){
		NoteFrais nf= noteFaisMetier.enregister(userename, userename, dateupload, chemin, etat, motif);
				return "noteFrais";
    }
}
