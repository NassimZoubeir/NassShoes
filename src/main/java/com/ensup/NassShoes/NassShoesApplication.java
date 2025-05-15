package com.ensup.NassShoes;

import java.security.NoSuchAlgorithmException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.ensup.NassShoes.entity.Utilisateur;
import com.ensup.NassShoes.outil.Outil;
import com.ensup.NassShoes.repository.UtilisateurRepository;


@SpringBootApplication
public class NassShoesApplication {
	private static UtilisateurRepository utilisateurRepository = null;

	public static void main(String[] args) {
		ApplicationContext  ctx = SpringApplication.run(NassShoesApplication.class, args);
		utilisateurRepository = ctx.getBean(UtilisateurRepository.class);
		initialiser();
	}
	public static void initialiser() {
	    Utilisateur utilisateur = null;
	    
	    try {
	        String hashPassword = Outil.hashMdpSha256("nass");
	        Utilisateur utilisateur1 = new Utilisateur("nass","nass@gmail.com",hashPassword,"1 rue de la paix","0600000000","utilisateur");
	        utilisateurRepository.save(utilisateur1);
	    } catch (NoSuchAlgorithmException e) {
	        System.out.println("❌ Impossible de créer l'utilisateur 'nass'");
	    }

	    try {
	        String hashPassword = Outil.hashMdpSha256("admin");
	        Utilisateur utilisateur2 = new Utilisateur("admin","admin@gmail.com", hashPassword,"2 avenue des Champs","0611111111","administrateur");
	        utilisateurRepository.save(utilisateur2);
	    } catch (NoSuchAlgorithmException e) {
	        System.out.println("❌ Impossible de créer l'utilisateur 'admin'");
	    }


	}


}