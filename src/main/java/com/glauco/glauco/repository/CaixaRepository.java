package com.glauco.glauco.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.glauco.glauco.models.Caixa;

public interface CaixaRepository extends CrudRepository<Caixa, Integer>{
	
	Caixa findById(int id);
	
}
