package com.glauco.glauco.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.glauco.glauco.models.PosicaoAtivo;
import com.glauco.glauco.models.Ativo;

public interface PosicaoAtivoRepository extends CrudRepository<PosicaoAtivo, Integer>{

	PosicaoAtivo findById(int id);
	PosicaoAtivo findByAtivo(Ativo ativo);
	PosicaoAtivo findFirst1ByOrderByDataDesc();
	
}
