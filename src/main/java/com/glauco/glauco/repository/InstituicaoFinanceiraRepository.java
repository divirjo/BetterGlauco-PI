package com.glauco.glauco.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.glauco.glauco.models.InstituicaoFinanceira;

public interface InstituicaoFinanceiraRepository extends CrudRepository<InstituicaoFinanceira, Integer>{
	
	InstituicaoFinanceira findById(int id);
	InstituicaoFinanceira findByNome(String nome);
	InstituicaoFinanceira findFirstByOrderByNomeAsc();
	
	/*
	 * IMPLEMENTA A BUSCA
	 */
	@Query(value = "select u from InstituicaoFinanceira u where u.nome like %?1%")
	List<InstituicaoFinanceira>findByNomes(String nome);
	
	
}
