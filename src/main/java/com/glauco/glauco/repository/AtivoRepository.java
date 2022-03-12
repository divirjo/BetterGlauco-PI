package com.glauco.glauco.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.glauco.glauco.models.Ativo;



public interface AtivoRepository extends CrudRepository<Ativo, Integer>{

	Ativo findById(int id);
	Ativo findByNome(String nome);
	
	/*
	 * IMPLEMENTA A BUSCA
	 */
	@Query(value = "select u from Ativo u where u.nome like %?1% OR u.sigla like %?1%")
	List<Ativo>findByNomes(String nome);
	
	
}
