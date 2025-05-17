package com.ensup.NassShoes.service;

import com.ensup.NassShoes.entity.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UtilisateurServiceItf utilisateurService;

    @Override
    public UserDetails loadUserByUsername(String nom) throws UsernameNotFoundException {
        Utilisateur utilisateur = utilisateurService.lireUtilisateurParNom(nom);
        if (utilisateur == null) {
            throw new UsernameNotFoundException("Utilisateur non trouvé");
        }

        return new org.springframework.security.core.userdetails.User(
                utilisateur.getNom(),
                utilisateur.getPassword(),
                List.of(new SimpleGrantedAuthority(utilisateur.getRole())) // Exemple : ROLE_administrateur
        );
    }
}
