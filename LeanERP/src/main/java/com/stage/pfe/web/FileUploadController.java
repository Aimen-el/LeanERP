package com.stage.pfe.web;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.stage.pfe.dao.UploadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.solr.core.RequestMethod;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.stage.pfe.entities.NoteFrais;
import com.stage.pfe.storage.StorageFileNotFoundException;
import com.stage.pfe.storage.StorageService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class FileUploadController {
    @Autowired
    private UploadRepository uploadRepository;
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
    public String listFiles(Model model) throws IOException {
        List<NoteFrais> noteFrais=null;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth.getName().equals("admin"))
    {
        noteFrais=uploadRepository.findAll();
        model.addAttribute("notefrais",noteFrais);

    }else {
        noteFrais = uploadRepository.findAllByUsername(auth.getName());

        model.addAttribute("notefrais", noteFrais);
    }
            return "editDocument";
        }
    
    
    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") long id) {
    	uploadRepository.delete(uploadRepository.findOne(id));
    	return "redirect:/editDocument";
    }
    
/*    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable("id") long id, Model model) {
    	model.addAttribute("noteFrais", uploadRepository.findOne(id));
    	return "edit";
    }
    
    @PostMapping("/edit/{id}")
    public String edit(@ModelAttribute("noteFrais") NoteFrais noteFrais, ModelMap modelMap) {
    	NoteFrais currentNoteFrais = uploadRepository.findOne(noteFrais.getId());
    	currentNoteFrais.setEtat(noteFrais.getEtat());
    	currentNoteFrais.setMotif(noteFrais.getMotif());
    	
    	uploadRepository.save(noteFrais);
    	return "redirect:/editDocument";
    }*/
    
    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("/uploadForm")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
            RedirectAttributes redirectAttributes,HttpServletRequest request,Model model,Boolean etat, String motif) {

        storageService.store(file);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // Chemin Local
       String absolutePath = new File("upload-dir/"+file.getOriginalFilename()).getAbsolutePath();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        NoteFrais noteFrais =new NoteFrais(etat,motif);
             noteFrais.setDateupload(date);
             noteFrais.setName(file.getOriginalFilename());
            noteFrais.setusername(auth.getName());

        noteFrais.setChemin(absolutePath);
        //Lien de téléchargement
       // nf.setChemin(request.getLocalName()+":"+request.getLocalPort()+"/files/"+file.getOriginalFilename());
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
