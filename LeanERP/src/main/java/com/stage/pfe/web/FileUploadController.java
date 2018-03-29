package com.stage.pfe.web;

import com.stage.pfe.entities.User;
import org.springframework.security.core.Authentication;

import java.io.File;
import java.io.IOException;


import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import com.stage.pfe.dao.UploadRepository;
import com.stage.pfe.dao.UserRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;


import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.stage.pfe.entities.NoteFrais;
import com.stage.pfe.storage.StorageFileNotFoundException;
import com.stage.pfe.storage.StorageService;



import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import javax.validation.Valid;

@Controller
public class FileUploadController {
    @Autowired
    private UploadRepository uploadRepository;
    @Autowired
    private UserRepository userRepository;
    private final StorageService storageService;

    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/uploadForm")
    public String listUploadedFiles(Model model) throws IOException {

        return "uploadForm";
    }

    @GetMapping("/editDocument")
    public String listFiles(Model model, User currentUser, OAuth2Authentication authentication) throws IOException {
        List<NoteFrais> noteFrais = null;
        //UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        LinkedHashMap<String, String> details = (LinkedHashMap<String, String>)authentication.getUserAuthentication().getDetails();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        currentUser=userRepository.findByPrincipalId(details.get(("sub")));
        boolean authorized = currentUser.getRoles().getRole().equals("ADMIN");

        if (authorized) {
            noteFrais = uploadRepository.findAll();
            model.addAttribute("notefrais", noteFrais);

        } else {
            noteFrais = uploadRepository.findAllByUsername(details.get("name"));

            model.addAttribute("notefrais", noteFrais);
        }
        return "editDocument";
    }



    
    /********************** Delete ******************/

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id) {
        uploadRepository.delete(uploadRepository.findOne(id));
        return "redirect:/editDocument";
    }


    @RequestMapping(value = "/download/{id}")
    public String download(@PathVariable long id, HttpServletRequest request, HttpServletResponse http) {
        NoteFrais noteFrais = this.uploadRepository.findOne(id);
        String lien = "http://" + request.getLocalName() + ":" + request.getLocalPort() + "/files/" + noteFrais.getName();
        return "redirect:" + lien;
    }


    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable("id") long id, ModelMap model) {
        model.addAttribute("noteFrais", this.uploadRepository.findOne(id));
        return "edit";

    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@Valid @ModelAttribute("noteFrais") NoteFrais noteFrais, BindingResult result) {
        Date date =new Date();
        noteFrais.setDateupload(date);
        this.uploadRepository.save(noteFrais);
        return "redirect:editDocument";
    }


    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }


    @PostMapping("/uploadForm")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes, OAuth2Authentication authentication,HttpServletRequest request, Model model, Boolean etat, String motif) {

        storageService.store(file);
        LinkedHashMap<String, String> details = (LinkedHashMap<String, String>)authentication.getUserAuthentication().getDetails();

        // Chemin Local
        String absolutePath = new File("upload-dir/" + file.getOriginalFilename()).getAbsolutePath();
        Date date = new Date();

        NoteFrais noteFrais = new NoteFrais(etat, motif);
        noteFrais.setDateupload(date);
        noteFrais.setName(file.getOriginalFilename());
        noteFrais.setusername(details.get("name"));

        noteFrais.setChemin(absolutePath);

        uploadRepository.save(noteFrais);

        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/uploadForm";
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}