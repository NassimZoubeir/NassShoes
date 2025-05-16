package com.ensup.NassShoes.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.ensup.NassShoes.entity.Chaussures;



public interface ChaussuresRepository extends JpaRepository<Chaussures, Long>  {

}