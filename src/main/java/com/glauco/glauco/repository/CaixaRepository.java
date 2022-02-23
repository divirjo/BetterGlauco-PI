package com.glauco.glauco.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.glauco.glauco.models.Caixa;

public interface CaixaRepository extends CrudRepository<Caixa, Integer>{
	
	Caixa findById(int id);
	Caixa findByNome(String nome);
	
	/*
	 * IMPLEMENTA A BUSCA
	 */
	@Query(value = "select u from Caixa u where u.nome like %?1%")
	List<Caixa>findByNomes(String nome);
	
	
}
