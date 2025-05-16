package com.ensup.NassShoes.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.ensup.NassShoes.entity.Chaussures;
import com.ensup.NassShoes.entity.Categorie;

import com.ensup.NassShoes.repository.CategorieRepository;
import com.ensup.NassShoes.service.ChaussuresService;

import jakarta.persistence.criteria.Path;
import jakarta.servlet.ServletContext;

@Controller
public class ChaussuresController {
	
	 @RequestMapping("/creer-chaussures")
	 public String creerChaussures(Model model) {
	 model.addAttribute("titre", "Créer chaussures");
	 return "creer-chaussures";
	 }
	 
	 @Autowired
	 private ServletContext context;

	 @Value("${dir.images}")
	 private String imageDir;

	 @Autowired
	 private CategorieRepository categorieRepository;

	 @Autowired
	 private ChaussuresService chaussureService;

	 @RequestMapping("/creer-chaussure-validation")
	 public String creerValidationChaussure(
	         String nom,
	         String description,
	         double prix,
	         int stock,
	         String couleur,
	         MultipartFile image,
	         Long id_categorie) {

	     String imageName = null;

	     if (image != null && !image.isEmpty()) {
	         try {
	             imageName = image.getOriginalFilename();
	             java.nio.file.Path pathFile = Paths.get(imageDir, imageName);

	             System.out.println("Chemin de l'image : " + pathFile.toString());

	             File directory = new File(imageDir);
	             if (!directory.exists()) {
	                 directory.mkdirs();
	             }

	             image.transferTo(((java.nio.file.Path) pathFile).toFile());
	         } catch (IllegalStateException | IOException e) {
	             e.printStackTrace();
	             System.err.println("Erreur lors de l'enregistrement de l'image : " + e.getMessage());
	             return "error";
	         }
	     }

	     Categorie categorie = categorieRepository.findById(id_categorie).orElse(null);
	     if (categorie == null) {
	         System.err.println("Erreur : Catégorie non trouvée pour id=" + id_categorie);
	         return "error";
	     }

	     Chaussures chaussures = new Chaussures();
	     chaussures.setNom(nom);
	     chaussures.setDescription(description);
	     chaussures.setPrix(prix);
	     chaussures.setStock(stock);
	     chaussures.setCouleur(couleur);
	     chaussures.setImageURL(imageName);
	     chaussures.setCategorie(categorie);

	     chaussureService.creerChaussures(chaussures);

	     return "redirect:/catalogue";
	 }

	
}