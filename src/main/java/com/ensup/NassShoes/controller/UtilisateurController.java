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
	        @RequestParam String email,
	        @RequestParam String adresse,
	        @RequestParam String telephone
	) {
	    System.out.println(nom + ", " + password + ", " + email + ", " + adresse + ", " + telephone);
	    String hashPassword = null;

	    try {
	        hashPassword = Outil.hashMdpSha256(password);
	    } catch (NoSuchAlgorithmException e) {
	        System.out.println("ERREUR - fonction hashMdpSha256");
	        return "erreur";
	    }

	    Utilisateur utilisateur = new Utilisateur();
	    utilisateur.setNom(nom);
	    utilisateur.setEmail(email);
	    utilisateur.setPassword(hashPassword);
	    utilisateur.setAdresse(adresse);
	    utilisateur.setTelephone(telephone);
	    utilisateur.setRole("utilisateur");


	    utilisateurService.creerUtilisateur(utilisateur);

	    return "Connexion";
	}
	 @RequestMapping("/connexion")
	 public String connexion(Model model, HttpServletRequest request) {
	 return "connexion";
	 }
	 
	 @RequestMapping("/connexion-validation")
	    public String nom(String nom, String password, Model model, HttpServletRequest request) {
	    	System.out.println("==== connexion-validation ====");
	    	System.out.println(nom + " / " + password);
	    	String hashPassword = null;
			try {
				hashPassword = Outil.hashMdpSha256(password);
			} catch (NoSuchAlgorithmException e) {
				System.out.println("ERREUR - fonction hashMdpSha256");
			}
	    	System.out.println("hashPassword=" + hashPassword);
	    	Utilisateur utilisateur = utilisateurService.lireUtilisateurParNom(nom);
	    	System.out.println("utilisateur:" + utilisateur);
	    	if(utilisateur.getPassword().equals(hashPassword)) {
	    		System.out.println("Vous êtes connecté");
	    		request.getSession().setAttribute("id",  utilisateur.getId());
				request.getSession().setAttribute("nom",  utilisateur.getNom());
				request.getSession().setAttribute("role",  utilisateur.getRole());
	    	}
	    	else System.out.println("Vous n'êtes pas connecté");
	    	return "accueil";
	    }
		@GetMapping("/deconnexion")
		public String logout(HttpServletRequest request) {
			System.out.println("====  /deconnexion  ====");
			request.getSession().invalidate();
			return "accueil";
		 }

	
}
