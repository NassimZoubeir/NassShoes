package com.ensup.NassShoes.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

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

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class ChaussuresController {
		 
	 @Autowired
	 private ServletContext context;

	 @Value("${dir.images}")
	 private String imageDir;

	 @Autowired
	 private CategorieRepository categorieRepository;

	 @Autowired
	 private ChaussuresService chaussureService;
	 
	 @RequestMapping("/creer-chaussures")
	 public String creerChaussures(Model model) {
	 model.addAttribute("titre", "Créer chaussures");
	 model.addAttribute("categories", categorieRepository.findAll());
	 return "creer-chaussures";
	 }

	 @RequestMapping("/creer-chaussures-validation")
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
	             Path pathFile = Paths.get(imageDir, imageName);
	             System.out.println("Chemin de l'image : " + pathFile.toString());

	             File directory = new File(imageDir);
	             if (!directory.exists()) {
	                 directory.mkdirs();
	             }

	             image.transferTo(pathFile.toFile());
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
	 @RequestMapping("/afficher-panier")
	 public String afficherPanier(Model model, HttpServletRequest request) {
	     System.out.println("==== /afficher-panier ====");

	     // Récupération de la liste des IDs des chaussures en session
	     List<Long> chaussureAcheterListId = (List<Long>) request.getSession().getAttribute("chaussureAcheterListId");
	     System.out.println("chaussureAcheterListId=" + chaussureAcheterListId);

	     if (chaussureAcheterListId != null && !chaussureAcheterListId.isEmpty()) {
	         // Appel au service pour récupérer la liste des chaussures à partir des IDs
	         List<Chaussures> chaussureAcheterList = chaussureService.getChaussureAcheterListParIdList(chaussureAcheterListId);
	         model.addAttribute("chaussureAcheterList", chaussureAcheterList);
	     } else {
	         System.out.println("Pas de chaussures dans le panier");
	     }

	     model.addAttribute("denomination", "Achat de chaussures");

	     return "panier";  // Nom de la vue Thymeleaf (panier.html)
	 }


	
}