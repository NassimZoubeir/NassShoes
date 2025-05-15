package com.ensup.NassShoes.controller;


import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ensup.NassShoes.entity.Utilisateur;
import com.ensup.NassShoes.outil.Outil;
import com.ensup.NassShoes.service.UtilisateurServiceItf;

import jakarta.servlet.http.HttpServletRequest;


@Controller
public  class  UtilisateurController  {
	
	@Autowired
	private UtilisateurServiceItf utilisateurService;

	@GetMapping("/creer-compte")
	public  String  creerUtilisateur()  {
		return  "creer-utilisateur";
	}
	
	@RequestMapping("/creer-compte-validation")
	public String creerUtilisateurValidation(
	        @RequestParam String nom,
	        @RequestParam String password,
	        @RequestParam String mail,
	        @RequestParam String adresse,
	        @RequestParam String telephone
	) {
	    System.out.println(nom + ", " + password + ", " + mail + ", " + adresse + ", " + telephone);
	    String hashPassword = null;

	    try {
	        hashPassword = Outil.hashMdpSha256(password);
	    } catch (NoSuchAlgorithmException e) {
	        System.out.println("ERREUR - fonction hashMdpSha256");
	        return "erreur"; // à remplacer par une vraie page d'erreur si besoin
	    }

	    // Création de l'utilisateur avec les bons champs
	    Utilisateur utilisateur = new Utilisateur();
	    utilisateur.setNom(nom);
	    utilisateur.setEmail(mail);
	    utilisateur.setPassword(hashPassword);
	    utilisateur.setAdresse(adresse);
	    utilisateur.setTelephone(telephone);

	    utilisateurService.creerUtilisateur(utilisateur);

	    return "Connexion"; // redirection vers la page de connexion
	}
	 @RequestMapping("/connexion")
	 public String connexion(Model model, HttpServletRequest request) {
	 return "connexion";
	 }

	
}
