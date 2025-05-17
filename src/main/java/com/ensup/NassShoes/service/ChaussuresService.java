package com.ensup.NassShoes.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ensup.NassShoes.entity.Chaussures;
import com.ensup.NassShoes.repository.ChaussuresRepository;


@Service
public class ChaussuresService implements ChaussuresServiceItf {
	
	@Autowired
	private ChaussuresRepository chaussuresRepository;
	
	public List<Chaussures> getAllChaussures() {
	    return chaussuresRepository.findAll();
	}
	
	 @Override
	 public void creerChaussures(Chaussures chaussures) {
	 chaussuresRepository.save(chaussures);
	 }
	
}
