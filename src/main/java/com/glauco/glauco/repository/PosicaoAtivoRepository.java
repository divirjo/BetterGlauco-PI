package com.glauco.glauco.repository;

import java.util.List;
import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.glauco.glauco.models.PosicaoAtivo;
import com.glauco.glauco.models.Ativo;
import com.glauco.glauco.models.Caixa;
import com.glauco.glauco.models.InstituicaoFinanceira;

public interface PosicaoAtivoRepository extends CrudRepository<PosicaoAtivo, Integer>{

	PosicaoAtivo findById(int id);
	PosicaoAtivo findTop1ByAtivoOrderByDataDesc(Ativo ativo);
	List<PosicaoAtivo> findByAtivoOrderByDataDesc(Ativo ativo);
	
	List<PosicaoAtivo> findAtivoByAtivoCorretoraOrderByAtivoSigla(InstituicaoFinanceira corretora);

	List<PosicaoAtivo> findAtivoByAtivoCaixaOrderByAtivoSigla(Caixa caixa);
	
	/*
	 * Corretoras que possuem ativos
	
	@Query(value = "SELECT DISTINCT ps.ativo FROM PosicaoAtivo ps WHERE ps.ativo.corretora.id = :idCorretora GROUP BY ps.ativo ")
	List<Ativo> findDistinctByCorretora(@Param("idCorretora") int corretora);
	
	/*	
	@Query(value = "SELECT ps.ativo, ps.cotas, ps.valor_cota, ps.data \r\n"
			+ "FROM ativo at \r\n"
			+ "INNER JOIN posicao_ativo ps\r\n"
			+ "ON at = ps.ativo\r\n"
			+ "WHERE at.corretora = %?1%\r\n"
			+ "ORDER BY ps.data desc\r\n"
			+ "limit 1;")
	List<PosicaoAtivo> findTop1ByAtivoCorretoraOrderByDataDesc(InstituicaoFinanceira Corretora);
*/

}


