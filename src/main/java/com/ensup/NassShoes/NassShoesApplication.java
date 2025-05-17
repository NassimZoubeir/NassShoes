package com.ensup.NassShoes;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.ensup.NassShoes.entity.Utilisateur;
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
	    
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

		String hashPassword1 = passwordEncoder.encode("nass");
		Utilisateur utilisateur1 = new Utilisateur("nass", "nass@gmail.com", hashPassword1, "1 rue de la paix", "0600000000", "ROLE_UTILISATEUR");
		utilisateurRepository.save(utilisateur1);

		String hashPassword2 = passwordEncoder.encode("admin");
		Utilisateur utilisateur2 = new Utilisateur("admin", "admin@gmail.com", hashPassword2, "2 avenue des Champs", "0611111111", "ROLE_ADMINISTRATEUR");
		utilisateurRepository.save(utilisateur2);

	}


}