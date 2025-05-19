package com.ensup.NassShoes.service;


import java.util.List;

import com.ensup.NassShoes.entity.Chaussures;

public interface ChaussuresServiceItf {
	
	void creerChaussures(Chaussures chaussures);
	List<Chaussures> getChaussureAcheterListParIdList(List<Long> ids);
}
