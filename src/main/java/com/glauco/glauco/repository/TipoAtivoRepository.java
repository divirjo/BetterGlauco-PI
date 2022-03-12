package com.glauco.glauco.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.glauco.glauco.models.TipoAtivo;


public interface TipoAtivoRepository extends CrudRepository<TipoAtivo, Integer>{

	TipoAtivo findById(int id);
	TipoAtivo findByNome(String nome);
	
	/*
	 * IMPLEMENTA A BUSCA
	 */
	@Query(value = "select u from TipoAtivo u where u.nome like %?1% OR u.sigla like %?1%")
	List<TipoAtivo>findByNomes(String nome);
	
}
