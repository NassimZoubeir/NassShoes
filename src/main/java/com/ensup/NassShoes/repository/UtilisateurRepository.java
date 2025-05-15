package com.ensup.NassShoes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ensup.NassShoes.entity.Utilisateur;



public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
	Utilisateur findByNom(String nom);
}