package com.ensup.NassShoes.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensup.NassShoes.entity.Chaussures;
import com.ensup.NassShoes.repository.ChaussuresRepository;


@Service
public class ChaussuresService implements ChaussuresServiceItf {
	
	@Autowired
	private ChaussuresRepository chaussuresRepository;
	
	 @Override
	 public void creerChaussures(Chaussures chaussures) {
	 chaussuresRepository.save(chaussures);
	 }
	
}
