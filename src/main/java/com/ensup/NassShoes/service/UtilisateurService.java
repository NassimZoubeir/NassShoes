package com.ensup.NassShoes.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensup.NassShoes.entity.Utilisateur;
import com.ensup.NassShoes.repository.UtilisateurRepository;



@Service
public  class UtilisateurService implements UtilisateurServiceItf {
	
	@Autowired
	private UtilisateurRepository utilisateurRepository;
	
	@Override
	public void creerUtilisateur(Utilisateur utilisateur) {
		utilisateurRepository.save(utilisateur);	
	}
	
	@Override
	public Utilisateur lireUtilisateurParNom(String nom) {
		return utilisateurRepository.findByNom(nom);
	}
	
}
