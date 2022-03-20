package com.glauco.glauco.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.glauco.glauco.models.PosicaoAtivo;
import com.glauco.glauco.models.Ativo;

public interface PosicaoAtivoRepository extends CrudRepository<PosicaoAtivo, Integer>{

	PosicaoAtivo findById(int id);
	List<PosicaoAtivo> findByAtivoOrderByDataDesc(Ativo ativo);
	PosicaoAtivo findTop1ByAtivoOrderByDataDesc(Ativo ativo);
	/*
	
	@Query(value = "SELECT at.nome, ps.cotas, ps.valor_cota, ps.data \r\n"
			+ "FROM ativo at \r\n"
			+ "INNER JOIN posicao_ativo ps\r\n"
			+ "ON at.id = ps.ativo_id\r\n"
			+ "WHERE at.corretora_id = %?1%\r\n"
			+ "ORDER BY ps.data desc\r\n"
			+ "limit 1;")
	PosicaoAtivo BuscaInvestimentos(int idAtivo);
	*/
}
